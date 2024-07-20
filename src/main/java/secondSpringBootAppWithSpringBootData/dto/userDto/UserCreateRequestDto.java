package secondSpringBootAppWithSpringBootData.dto.userDto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequestDto {

    @NotBlank(message = "User name must be not blank")
    @NotNull
    @Size(min = 3, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "User name can contain only latin character and digital")
    private String name;

    @NotBlank(message = "User password must be not blank")
    @Size(min = 8, message = "User password must be at least 8 symbols long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$", message = "User password must contain at least one capital letter, one lowercase letter, one number, and one special character (e.g., !@#$%^&*)")
    private String password;

    @NotBlank(message = "Email must be not blank")
    @Email(message = "Invalid email format")
    private String email;
}
