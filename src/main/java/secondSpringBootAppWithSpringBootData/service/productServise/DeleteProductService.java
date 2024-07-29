package secondSpringBootAppWithSpringBootData.service.productServise;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.appDTO.OneMessageDTO;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.entity.User;
import secondSpringBootAppWithSpringBootData.exception.NotFoundException;
import secondSpringBootAppWithSpringBootData.repository.ProductRepository;
import secondSpringBootAppWithSpringBootData.service.user.UserFindService;

@Service
@AllArgsConstructor
public class DeleteProductService {

    private final ProductRepository productRepository;
    private final UserFindService userFindService;

    public ResponseEntity<OneMessageDTO> deleteProduct(Long id) {

        User currentUser = userFindService.getUserFromContext();

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + id));

        if (product.getUser().getId().equals(currentUser.getId())) {
            productRepository.deleteById(id);
            return new ResponseEntity<>(new OneMessageDTO("Product deleted successfully"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new OneMessageDTO("You do not have permission to delete this product"), HttpStatus.CONFLICT);
        }
    }
}
