package secondSpringBootAppWithSpringBootData.dto.userDto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import secondSpringBootAppWithSpringBootData.entity.Product;

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
}
