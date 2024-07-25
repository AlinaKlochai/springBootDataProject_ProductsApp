package secondSpringBootAppWithSpringBootData.dto.productDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import secondSpringBootAppWithSpringBootData.dto.RegionDto;
import secondSpringBootAppWithSpringBootData.dto.categoryDto.CategoryCreateRequestDto;
import secondSpringBootAppWithSpringBootData.entity.Region;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequestDto {
    @NotEmpty(message = "Product name cannot be empty")
    @NotBlank(message = "Product name must be not blank.")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Product name can contain only latin characters, digits, and spaces.")
    private String name;

    @NotNull(message = "Product category must be not null.")
    private CategoryCreateRequestDto category;

    @NotNull(message = "Product price must be not null.")
    private Double price;

    private String description;
    private Boolean isInStock;

    @NotNull(message = "Region by product must be not null.")
    private RegionDto region;
    private Long user;

}
