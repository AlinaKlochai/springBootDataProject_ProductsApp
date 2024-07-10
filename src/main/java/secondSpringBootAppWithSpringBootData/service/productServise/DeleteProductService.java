package secondSpringBootAppWithSpringBootData.service.productServise;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.repository.ProductRepository;

@Service
public class DeleteProductService {

    private final ProductRepository productRepository;

    public DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<Boolean> deleteProduct(Integer productId) {
        if (productRepository.existsById(productId)){
            productRepository.deleteById(productId);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
