package secondSpringBootAppWithSpringBootData.dto.productDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import secondSpringBootAppWithSpringBootData.dto.categoryDto.CategoryCreateRequestDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private String name;
    private CategoryCreateRequestDto category;
    private Double price;
    private Boolean isInStock;

}
