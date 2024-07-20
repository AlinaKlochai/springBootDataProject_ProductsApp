package secondSpringBootAppWithSpringBootData.service.categoryService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.categoryDto.CategoryResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Category;
import secondSpringBootAppWithSpringBootData.repository.CategoryRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FindCategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponseDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDto> dtoList = categories.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        Collections.reverse(dtoList);
        return dtoList;
    }

    private CategoryResponseDto toDto(Category category) {
        return new CategoryResponseDto(category.getId(), category.getName());
    }
}
