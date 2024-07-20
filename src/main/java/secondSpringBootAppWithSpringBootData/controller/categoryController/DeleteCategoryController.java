package secondSpringBootAppWithSpringBootData.controller.categoryController;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secondSpringBootAppWithSpringBootData.service.categoryService.DeleteCategoryService;

@RestController
@RequestMapping("/categories/deleteCategory")
@AllArgsConstructor
public class DeleteCategoryController {


    private final DeleteCategoryService deleteCategoryService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        deleteCategoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
