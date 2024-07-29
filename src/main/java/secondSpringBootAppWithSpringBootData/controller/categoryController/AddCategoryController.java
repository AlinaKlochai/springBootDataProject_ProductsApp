package secondSpringBootAppWithSpringBootData.controller.categoryController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secondSpringBootAppWithSpringBootData.controller.api.category.AddCategoryControllerApi;
import secondSpringBootAppWithSpringBootData.dto.categoryDto.CategoryCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.categoryDto.CategoryResponseDto;
import secondSpringBootAppWithSpringBootData.service.categoryService.AddCategoryService;

@RestController
@RequestMapping("/admin/rent/categories")
@AllArgsConstructor
public class AddCategoryController implements AddCategoryControllerApi {

  private final AddCategoryService addCategoryService;

    @PostMapping
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryCreateRequestDto requestDto) {
        return addCategoryService.addCategory(requestDto.getName());
    }
}
