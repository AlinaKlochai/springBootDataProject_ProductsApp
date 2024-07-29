package secondSpringBootAppWithSpringBootData.service.categoryService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.appDTO.OneMessageDTO;
import secondSpringBootAppWithSpringBootData.dto.categoryDto.CategoryResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Category;
import secondSpringBootAppWithSpringBootData.repository.CategoryRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FindCategoryService {

    private final CategoryRepository categoryRepository;

    public ResponseEntity<?> findAll() {
        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            return ResponseEntity.ok(new OneMessageDTO("List of Categories is empty"));
        }

        List<CategoryResponseDto> dtoList = categories.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        Collections.reverse(dtoList);
        return ResponseEntity.ok(dtoList); // Возвращаем список категорий
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    private CategoryResponseDto toDto(Category category) {
        return new CategoryResponseDto(category.getName());
    }
}
