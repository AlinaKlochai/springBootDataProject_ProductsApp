package secondSpringBootAppWithSpringBootData.dto.clientDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientCreateRequestDto {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
