package secondSpringBootAppWithSpringBootData.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import secondSpringBootAppWithSpringBootData.entity.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private String name;
    private Category category;
    private Double price;
    private Boolean isInStock;

}
