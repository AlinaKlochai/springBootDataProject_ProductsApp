package secondSpringBootAppWithSpringBootData.controller.userController;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserResponseDto;
import secondSpringBootAppWithSpringBootData.service.userService.UpdateUserService;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UpdateUserController {

    private final UpdateUserService updateClientService;

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateClient(@PathVariable Long id, @RequestBody UserCreateRequestDto clientCreateRequestDto) {
        UserResponseDto updatedClient = updateClientService.updateUser(id, clientCreateRequestDto);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }
}
