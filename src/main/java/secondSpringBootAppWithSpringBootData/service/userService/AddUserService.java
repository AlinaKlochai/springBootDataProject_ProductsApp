package secondSpringBootAppWithSpringBootData.service.userService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.OneMessageDTO;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Role;
import secondSpringBootAppWithSpringBootData.entity.State;
import secondSpringBootAppWithSpringBootData.entity.User;
import secondSpringBootAppWithSpringBootData.exception.AlreadyExistException;
import secondSpringBootAppWithSpringBootData.repository.RoleRepository;
import secondSpringBootAppWithSpringBootData.repository.UserRepository;
import secondSpringBootAppWithSpringBootData.security.dto.AuthResponse;
import secondSpringBootAppWithSpringBootData.security.service.JwtTokenProvider;
import secondSpringBootAppWithSpringBootData.service.mail.MailCreateUtil;
import secondSpringBootAppWithSpringBootData.service.mail.UserMailSender;
import secondSpringBootAppWithSpringBootData.service.util.UserConverter;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AddUserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final MailCreateUtil mailCreateUtil;
    private final UserMailSender userMailSender;
    private final FindUserService findUserService;



    public UserResponseDto addUser(UserCreateRequestDto userCreateRequestDto) {
        if (userRepository.findUserByEmail(userCreateRequestDto.getEmail()).isPresent()) {
            throw new AlreadyExistException("User with the given email already exists.");
        }

        String codeValue = UUID.randomUUID().toString();

        User user = User.builder()
                .email(userCreateRequestDto.getEmail())
                .password(passwordEncoder.encode(userCreateRequestDto.getPassword()))
                .role(roleRepository.findByRole("USER"))
                .code(codeValue)
                .state(State.NOT_CONFIRMED)
                .build();

        User savedUser = userRepository.save(user);

        return userConverter.userToDto(savedUser);
    }

    public AuthResponse authentication(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication.getName());

        return new AuthResponse(jwt);
    }

    public ResponseEntity<OneMessageDTO> confirm(Long id, String code){
        UserResponseDto userResponseDto = findUserService.findUserById(id);
        Role role = roleRepository.findByRole(userResponseDto.getRole().getRole());
        User user = userConverter.fromResponseDtoToUser(userResponseDto, role, userResponseDto.getState());
        if(code.equals(user.getCode())){
            user.setCode("");
            user.setState(State.CONFIRMED);
            repository.save(user);
            return ResponseEntity.ok(new OneMessageDTO("User confirmed successfully"));
        }
        return ResponseEntity.badRequest().body(new OneMessageDTO("Invalid confirmation code"));
    }


    private void sendEmail(User user, String code) {
        String link = "?id=" + (user.getId()) + "&code=" + code;
        String html = mailCreateUtil.createConfirmationMail(user.getName(), link);
        userMailSender.send(user.getEmail(), "Registration", html);
    }
}
