package secondSpringBootAppWithSpringBootData.service.categoryService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.appDTO.OneMessageDTO;
import secondSpringBootAppWithSpringBootData.entity.User;
import secondSpringBootAppWithSpringBootData.exception.NotFoundException;
import secondSpringBootAppWithSpringBootData.repository.CategoryRepository;
import secondSpringBootAppWithSpringBootData.service.user.UserFindService;

@Service
@AllArgsConstructor
public class DeleteCategoryService {

    private final CategoryRepository categoryRepository;
    private final UserFindService userFindService;

    public ResponseEntity<?> deleteCategory(Long id) {
        User user = userFindService.getUserFromContext();

        if (user.getRole().getRole().equals("ADMIN")) {
            if (categoryRepository.existsById(id)) {
                categoryRepository.deleteById(id);
                return new ResponseEntity<>(new OneMessageDTO("Category was successfully deleted"), HttpStatus.NO_CONTENT);
            } else {
                throw new NotFoundException("Category not found with id: " + id);
            }
        } else {
            throw new AccessDeniedException("You do not have permission to add a category.");
        }
    }
}
