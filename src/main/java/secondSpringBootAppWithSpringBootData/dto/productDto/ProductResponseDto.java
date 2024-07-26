package secondSpringBootAppWithSpringBootData.dto.productDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import secondSpringBootAppWithSpringBootData.dto.RegionDto;
import secondSpringBootAppWithSpringBootData.dto.categoryDto.CategoryCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserJustWithNameDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private String name;
    private CategoryCreateRequestDto category;
    private Double price;
    private String description;
    private RegionDto region;
    private Boolean isInStock;
    private UserJustWithNameDto owner;

}
