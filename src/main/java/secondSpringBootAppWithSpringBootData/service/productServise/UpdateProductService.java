package secondSpringBootAppWithSpringBootData.service.productServise;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.repository.ProductRepository;
import secondSpringBootAppWithSpringBootData.service.util.ProductConverter;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;


    public ResponseEntity<ProductResponseDto> updateProduct(Integer productId, ProductCreateRequestDto productCreateRequestDto) {
        Optional<Product> existingProductOpt = productRepository.findById(productId);

        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();

            if (productCreateRequestDto.getName() != null) {
                existingProduct.setName(productCreateRequestDto.getName());
            }
            if (productCreateRequestDto.getCategory() != null) {
                existingProduct.setCategory(productCreateRequestDto.getCategory());
            }
            if (productCreateRequestDto.getPrice() != null) {
                existingProduct.setPrice(productCreateRequestDto.getPrice());
            }
            if (productCreateRequestDto.getIsInStock() != null) {
                existingProduct.setIsInStock(productCreateRequestDto.getIsInStock());
            }

            Product updatedProduct = productRepository.save(existingProduct);

            ProductResponseDto productResponseDto = productConverter.toDto(updatedProduct);

            return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ProductResponseDto(), HttpStatus.NOT_FOUND);
        }
    }
}
