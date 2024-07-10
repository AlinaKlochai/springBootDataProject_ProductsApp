package secondSpringBootAppWithSpringBootData.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Product name must be not blank.")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Product name can contain only latin character and digital.")
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @NotNull(message = "Product price must be not null.")
    @NotBlank(message = "Product price must be not blank.")
    private Double price;
    private Boolean isInStock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

    public Product(String name, Category category, Double price, Boolean isInStock) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.isInStock = isInStock;
    }
}
