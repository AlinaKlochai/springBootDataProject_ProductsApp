package secondSpringBootAppWithSpringBootData.service.util;

import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Role;
import secondSpringBootAppWithSpringBootData.entity.State;
import secondSpringBootAppWithSpringBootData.entity.User;

@Service
public class UserConverter {

    public UserResponseDto userToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setRole(user.getRole());
        userResponseDto.setState(user.getState());
        userResponseDto.setCode(user.getCode());
        return userResponseDto;
    }


    public User fromResponseDtoToUser(UserResponseDto userResponseDto,  Role role, State state) {
        if (userResponseDto == null) {
            return null;
        }

        User user = new User();
        user.setId(userResponseDto.getId());
        user.setEmail(userResponseDto.getEmail());
        user.setName(userResponseDto.getName());
        user.setPassword(userResponseDto.getPassword());
        user.setRole(role);
        user.setState(state);
        user.setCode("");

        return user;
    }
}
