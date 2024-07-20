package secondSpringBootAppWithSpringBootData.service.productServise;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Category;
import secondSpringBootAppWithSpringBootData.entity.User;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.repository.CategoryRepository;
import secondSpringBootAppWithSpringBootData.repository.UserRepository;
import secondSpringBootAppWithSpringBootData.repository.ProductRepository;
import secondSpringBootAppWithSpringBootData.service.util.ProductConverter;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;


    public ResponseEntity<ProductResponseDto> updateProduct(Long productId, ProductCreateRequestDto productCreateRequestDto) {
        Optional<Product> existingProductOpt = productRepository.findById(productId);

        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();

            if (productCreateRequestDto.getName() != null) {
                existingProduct.setName(productCreateRequestDto.getName());
            }
            if (productCreateRequestDto.getCategory() != null) {
                Optional<Category> categoryOpt = categoryRepository.findByName(productCreateRequestDto.getCategory().getName());
                if (categoryOpt.isPresent()) {
                    existingProduct.setCategory(categoryOpt.get());
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Category not found
                }
            }
            if (productCreateRequestDto.getPrice() != null) {
                existingProduct.setPrice(productCreateRequestDto.getPrice());
            }
            if (productCreateRequestDto.getIsInStock() != null) {
                existingProduct.setIsInStock(productCreateRequestDto.getIsInStock());
            }
            if (productCreateRequestDto.getUser() != null) {
                Optional<User> clientOpt = userRepository.findById(Long.valueOf(productCreateRequestDto.getUser()));
                if (clientOpt.isPresent()) {
                    existingProduct.setUser(clientOpt.get());
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Client not found
                }
            }

            Product updatedProduct = productRepository.save(existingProduct);
            ProductResponseDto productResponseDto = productConverter.toDto(updatedProduct);

            return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
