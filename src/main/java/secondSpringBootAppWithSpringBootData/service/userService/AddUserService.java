package secondSpringBootAppWithSpringBootData.service.userService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserResponseDto;
import secondSpringBootAppWithSpringBootData.entity.User;
import secondSpringBootAppWithSpringBootData.exception.AlreadyExistException;
import secondSpringBootAppWithSpringBootData.repository.UserRepository;
import secondSpringBootAppWithSpringBootData.service.util.UserConverter;

@Service
@AllArgsConstructor
public class AddUserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;


public UserResponseDto addUser(UserCreateRequestDto userCreateRequestDto) {
    if (userRepository.findUserByEmail(userCreateRequestDto.getEmail()).isPresent()) {
        throw new AlreadyExistException("User with the given email already exists.");
    }

    User user = new User();
    user.setName(userCreateRequestDto.getName());
    user.setPassword(userCreateRequestDto.getPassword());
    user.setEmail(userCreateRequestDto.getEmail());

    User savedUser = userRepository.save(user);
    return userConverter.userToDto(savedUser);
}
}
