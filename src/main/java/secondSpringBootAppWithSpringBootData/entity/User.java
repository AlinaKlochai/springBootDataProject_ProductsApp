package secondSpringBootAppWithSpringBootData.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "`user`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
    private State state;
    private String code;

    public User(String name, String password, String email,  Role role, State state, String code) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.state = state;
        this.code = code;
    }

}
