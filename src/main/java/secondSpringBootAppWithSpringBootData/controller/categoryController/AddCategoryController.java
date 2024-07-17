package secondSpringBootAppWithSpringBootData.controller.categoryController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secondSpringBootAppWithSpringBootData.dto.categoryDto.CategoryCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.categoryDto.CategoryResponseDto;
import secondSpringBootAppWithSpringBootData.service.categoryService.AddCategoryService;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class AddCategoryController {

  private final AddCategoryService addCategoryService;

    @PostMapping("/add")
    public ResponseEntity<CategoryResponseDto> addCategory(@Valid @RequestBody CategoryCreateRequestDto requestDto) {
        CategoryResponseDto responseDto = addCategoryService.addCategory(requestDto.getName());
        return ResponseEntity.ok(responseDto);
    }

}
