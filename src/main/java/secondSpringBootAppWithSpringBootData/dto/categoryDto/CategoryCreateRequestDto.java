package secondSpringBootAppWithSpringBootData.dto.categoryDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateRequestDto {

        @NotEmpty(message = "Category cannot be empty")
        @NotBlank(message = "Category must be not blank.")
        @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Category name can contain only latin characters, digits, and spaces.")
        private String name;

}
