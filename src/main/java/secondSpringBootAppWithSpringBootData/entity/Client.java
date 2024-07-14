package secondSpringBootAppWithSpringBootData.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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


    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Client firstname can contain only latin character and digital")
    private String firstName;


    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Client lastname can contain only latin character and digital")
    private String lastName;

    @Size(min = 8, message = "Client password must be at least 8 symbols long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$", message = "Client password must contain at least one capital letter, one lowercase letter, one number, and one special character (e.g., !@#$%^&*)")
    private String password;


    @Email(message = "Invalid email format")
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonManagedReference
    private List<Product> products;


}
