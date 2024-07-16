package secondSpringBootAppWithSpringBootData.dto.productDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import secondSpringBootAppWithSpringBootData.dto.categoryDto.CategoryDTO;
import secondSpringBootAppWithSpringBootData.entity.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequestDto {
    @NotEmpty(message = "Product name cannot be empty")
    @NotBlank(message = "Product name must be not blank.")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Product name can contain only latin characters, digits, and spaces.")
    private String name;

    @NotNull(message = "Product category must be not null.")
    private CategoryDTO category;

    @NotNull(message = "Product price must be not null.")
    private Double price;
    private Boolean isInStock;
    private Integer client;

}
