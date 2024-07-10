package secondSpringBootAppWithSpringBootData.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "client")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Client firstname must be not blank")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Client firstname can contain only latin character and digital")
    private String firstName;

    @NotBlank(message = "Client lastname must be not blank")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Client lastname can contain only latin character and digital")
    private String lastName;

    @NotBlank(message = "Client password must be not blank")
    private String password;

    @NotBlank(message = "Email must be not blank")
    @Email(message = "Invalid email format")
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonManagedReference
    private List<Product> products;


}
