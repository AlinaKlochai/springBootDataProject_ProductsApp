package secondSpringBootAppWithSpringBootData.dto.productDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import secondSpringBootAppWithSpringBootData.entity.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequestDto {
    private String name;
    private Category category;
    private Double price;
    private Boolean isInStock;
    private Integer client;

}
