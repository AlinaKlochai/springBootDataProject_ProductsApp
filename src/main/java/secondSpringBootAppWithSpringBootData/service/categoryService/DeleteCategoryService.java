package secondSpringBootAppWithSpringBootData.service.categoryService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.exception.NotFoundException;
import secondSpringBootAppWithSpringBootData.repository.CategoryRepository;

@Service
@AllArgsConstructor
public class DeleteCategoryService {

    private CategoryRepository categoryRepository;

    public void deleteCategory(Integer id) {
        if (categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
        } else{
            throw new NotFoundException("Category not found with id: " + id);
        }
    }
}
