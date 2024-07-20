package secondSpringBootAppWithSpringBootData.controller.userController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserResponseDto;
import secondSpringBootAppWithSpringBootData.service.userService.AddUserService;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class AddUserController {

    private final AddUserService addUserService;

    @PostMapping("/addNewUser")
    public ResponseEntity<Long> createUser(@Valid @RequestBody UserCreateRequestDto userCreateRequestDto) {
        UserResponseDto createdUser = addUserService.addUser(userCreateRequestDto);
        return new ResponseEntity<>(createdUser.getId(), HttpStatus.CREATED);
    }
}
