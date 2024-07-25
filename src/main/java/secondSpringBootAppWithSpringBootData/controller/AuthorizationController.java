package secondSpringBootAppWithSpringBootData.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import secondSpringBootAppWithSpringBootData.controller.api.AuthApi;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserNewDTO;
import secondSpringBootAppWithSpringBootData.security.dto.AuthRequest;
import secondSpringBootAppWithSpringBootData.security.dto.AuthResponse;
import secondSpringBootAppWithSpringBootData.security.dto.StandardResponseDto;
import secondSpringBootAppWithSpringBootData.service.user.UserAuthService;

@RestController
@RequiredArgsConstructor
public class AuthorizationController implements AuthApi {
    private final UserAuthService userAuthService;

    @Override
    public ResponseEntity<AuthResponse> auth(AuthRequest request) {
        return new ResponseEntity<>(userAuthService.authentication(request.getEmail(), request.getPassword())
                , HttpStatus.OK);
    }

   @Override
    public ResponseEntity<StandardResponseDto> registration(UserNewDTO user) {
        userAuthService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new StandardResponseDto("Confirmation sanded to your email"));
    }

   @Override
    public ResponseEntity<StandardResponseDto> confirmation(String data, String code) {
       return userAuthService.confirm(data, code);
    }


}
