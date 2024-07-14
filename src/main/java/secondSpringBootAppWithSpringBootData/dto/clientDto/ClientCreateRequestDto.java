package secondSpringBootAppWithSpringBootData.dto.clientDto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientCreateRequestDto {

    @NotBlank(message = "Client firstname must be not blank")
    private String firstName;

    @NotBlank(message = "Client lastname must be not blank")
    private String lastName;

    @NotBlank(message = "Client password must be not blank")
    private String password;

    @NotBlank(message = "Email must be not blank")
    private String email;
}
