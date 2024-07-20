package secondSpringBootAppWithSpringBootData.service.util;

import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserResponseDto;
import secondSpringBootAppWithSpringBootData.entity.User;

@Service
public class UserConverter {

    public UserResponseDto userToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setEmail(user.getEmail());
        //userResponseDto.setProducts(client.getProducts());
        return userResponseDto;
    }
}
