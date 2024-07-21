package secondSpringBootAppWithSpringBootData.dto.userDto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.entity.Role;
import secondSpringBootAppWithSpringBootData.entity.State;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    //@Min(0)
    //@NotNull
    private Long id;

    //@Size(min = 2, max = 15)
    private String name;

    //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
    //@NotNull
    private String password;
    //@Email
    //@NotNull
    private String email;

    private Role role;
    private State state;
    private String code;

    public UserResponseDto(Long id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
