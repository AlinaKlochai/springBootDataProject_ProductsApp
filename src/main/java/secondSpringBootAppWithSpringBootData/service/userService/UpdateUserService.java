package secondSpringBootAppWithSpringBootData.service.userService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserResponseDto;
import secondSpringBootAppWithSpringBootData.entity.User;
import secondSpringBootAppWithSpringBootData.exception.NotFoundException;
import secondSpringBootAppWithSpringBootData.repository.UserRepository;
import secondSpringBootAppWithSpringBootData.service.util.UserConverter;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UpdateUserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;


    public UserResponseDto updateUser(Long userId, UserCreateRequestDto userCreateRequestDto) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userCreateRequestDto.getName());
            user.setPassword(userCreateRequestDto.getPassword());
            user.setEmail(userCreateRequestDto.getEmail());

            User updatedUser = userRepository.save(user);
            return userConverter.userToDto(updatedUser);
        } else {
            throw new NotFoundException("User not found with id: " + userId);
        }
    }
}

