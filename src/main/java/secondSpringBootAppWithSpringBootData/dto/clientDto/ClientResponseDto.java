package secondSpringBootAppWithSpringBootData.dto.clientDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import secondSpringBootAppWithSpringBootData.entity.Product;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private List<Product> tasks;
}
