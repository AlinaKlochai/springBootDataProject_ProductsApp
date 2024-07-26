package secondSpringBootAppWithSpringBootData.controller.categoryController;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secondSpringBootAppWithSpringBootData.dto.categoryDto.CategoryResponseDto;
import secondSpringBootAppWithSpringBootData.service.categoryService.FindCategoryService;

import java.util.List;

@RestController
@RequestMapping("/rents/categories")
@AllArgsConstructor
public class FindCategoryController {

    private final FindCategoryService findCategoryService;

    @GetMapping("/findAll")
    public ResponseEntity<List<CategoryResponseDto>> findAll() {
        List<CategoryResponseDto> allCategories = findCategoryService.findAll();
        return ResponseEntity.ok(allCategories);
    }
}
