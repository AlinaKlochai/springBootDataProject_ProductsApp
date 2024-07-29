package secondSpringBootAppWithSpringBootData.service.categoryService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.appDTO.OneMessageDTO;
import secondSpringBootAppWithSpringBootData.dto.categoryDto.CategoryResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Category;
import secondSpringBootAppWithSpringBootData.entity.User;
import secondSpringBootAppWithSpringBootData.exception.AlreadyExistException;
import secondSpringBootAppWithSpringBootData.repository.CategoryRepository;
import secondSpringBootAppWithSpringBootData.service.user.UserFindService;

import java.util.Optional;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class AddCategoryService {

    private final CategoryRepository categoryRepository;
    private final UserFindService userFindService;
    private static final Logger logger = Logger.getLogger(AddCategoryService.class.getName());


    public ResponseEntity<?> addCategory(String categoryName) {
        User user = userFindService.getUserFromContext();
        logger.info("User role: " + user.getRole());

        if (user.getRole().getRole().equals("ADMIN")) {
            Optional<Category> existingCategory = categoryRepository.findByName(categoryName);

            if (existingCategory.isPresent()) {
                throw new AlreadyExistException("Category with name " + categoryName + " already exists.");
            }

            Category newCategory = new Category();
            newCategory.setName(categoryName);
            Category savedCategory = categoryRepository.save(newCategory);

            CategoryResponseDto responseDto = new CategoryResponseDto(savedCategory.getName());
            return new ResponseEntity<>(new OneMessageDTO("New category with name" + savedCategory.getName() + "successfully created"), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new OneMessageDTO("You do not have the rights to add a category."), HttpStatus.FORBIDDEN);
        }
    }
}
