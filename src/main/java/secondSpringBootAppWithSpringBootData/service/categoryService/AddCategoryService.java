package secondSpringBootAppWithSpringBootData.service.categoryService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.categoryDto.CategoryResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Category;
import secondSpringBootAppWithSpringBootData.exception.AlreadyExistException;
import secondSpringBootAppWithSpringBootData.repository.CategoryRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddCategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponseDto addCategory(String categoryName) {
        Optional<Category> existingCategory = categoryRepository.findByName(categoryName);

        if (existingCategory.isPresent()) {
            throw new AlreadyExistException("Category with name " + categoryName + " already exists.");
        }

        Category newCategory = new Category();
        newCategory.setName(categoryName);
        Category savedCategory = categoryRepository.save(newCategory);

        return new CategoryResponseDto(savedCategory.getId(), savedCategory.getName());
    }
}
