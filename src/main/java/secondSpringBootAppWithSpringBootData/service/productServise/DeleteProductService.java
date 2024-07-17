package secondSpringBootAppWithSpringBootData.service.productServise;

import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.exception.NotFoundException;
import secondSpringBootAppWithSpringBootData.repository.ProductRepository;

@Service
public class DeleteProductService {

    private final ProductRepository productRepository;

    public DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void deleteProduct(Integer id) {
        if (productRepository.existsById(id)){
            productRepository.deleteById(id);
        } else{
            throw new NotFoundException("Product not found with id: " + id);
        }
    }
}
