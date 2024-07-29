package secondSpringBootAppWithSpringBootData.controller.categoryController;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secondSpringBootAppWithSpringBootData.controller.api.category.FindCategoryControllerApi;
import secondSpringBootAppWithSpringBootData.dto.categoryDto.CategoryResponseDto;
import secondSpringBootAppWithSpringBootData.service.categoryService.FindCategoryService;

import java.util.List;

@RestController
@RequestMapping("/rents/categories")
@AllArgsConstructor
public class FindCategoryController implements FindCategoryControllerApi {

    private final FindCategoryService findCategoryService;

    @GetMapping
    @Override
    public ResponseEntity<?> findAll() {
        return findCategoryService.findAll();
    }
}
