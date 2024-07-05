package firstSpringBootApp.service;

import secondSpringBootAppWithSpringBootData.entity.Product;
import firstSpringBootApp.repository.ProductRepositoryInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductService {

    private final ProductRepositoryInterface repository;


    public UpdateProductService(ProductRepositoryInterface repository) {
        this.repository = repository;
    }

    public ResponseEntity<Boolean> updateProduct(Integer id, Product product) {
        Optional<Product> existingProductOpt = repository.findProductById(id);

        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();

            if (product.getName() != null) {
                existingProduct.setName(product.getName());
            }
            if (product.getCategory() != null) {
                existingProduct.setCategory(product.getCategory());
            }
            if (product.getPrice() != null) {
                existingProduct.setPrice(product.getPrice());
            }
            if (product.getIsInStock() != null) {
                existingProduct.setIsInStock(product.getIsInStock());
            }

            boolean resultByUpdate = repository.updateProduct(existingProduct);
            return new ResponseEntity<>(resultByUpdate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

}
