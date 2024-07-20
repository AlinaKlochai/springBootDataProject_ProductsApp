package secondSpringBootAppWithSpringBootData.controller.userController;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserResponseDto;
import secondSpringBootAppWithSpringBootData.service.userService.FindUserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class FindUserController {

    private final FindUserService findUserService;

    @GetMapping("/findAll")
    public ResponseEntity<List<UserResponseDto>> findAllUsers() {
        List<UserResponseDto> users = findUserService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        UserResponseDto user = findUserService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<UserResponseDto> findUserByUsername(@PathVariable String name) {
        UserResponseDto user = findUserService.findUserByName(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> findUserByEmail(@PathVariable String email) {
        UserResponseDto user = findUserService.findUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
