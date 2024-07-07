package secondSpringBootAppWithSpringBootData.entity;

import jakarta.persistence.*;
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
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Double price;
    private Boolean isInStock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    public Product(String name, Category category, Double price, Boolean isInStock) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.isInStock = isInStock;
    }
}
