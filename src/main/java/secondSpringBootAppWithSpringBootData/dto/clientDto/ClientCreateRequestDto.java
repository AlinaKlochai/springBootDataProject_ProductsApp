package secondSpringBootAppWithSpringBootData.dto.clientDto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientCreateRequestDto {

    @NotBlank(message = "Client firstname must be not blank")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Client firstname can contain only latin character and digital")
    private String firstName;

    @NotBlank(message = "Client lastname must be not blank")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Client lastname can contain only latin character and digital")
    private String lastName;

    @NotBlank(message = "Client password must be not blank")
    @Size(min = 8, message = "Client password must be at least 8 symbols long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$", message = "Client password must contain at least one capital letter, one lowercase letter, one number, and one special character (e.g., !@#$%^&*)")
    private String password;

    @NotBlank(message = "Email must be not blank")
    @Email(message = "Invalid email format")
    private String email;
}
