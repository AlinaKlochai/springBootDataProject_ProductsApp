package secondSpringBootAppWithSpringBootData.service.productServise;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Category;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.repository.ProductRepository;
import secondSpringBootAppWithSpringBootData.service.util.ProductConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FindProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public List<ProductResponseDto> findAllProducts() {
       return productRepository.findAll().stream()
               .map(productConverter::toDto)
               .toList();
    }

    public ResponseEntity<ProductResponseDto> findProductById(int productId) {
        return productRepository.findById(productId)
                .map(productConverter::toDto)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<ProductResponseDto>> findProductByName(String name) {
        List<Product> products = productRepository.findAllByName(name);
        List<ProductResponseDto> dtos = products.stream()
                .map(productConverter::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductResponseDto>> findProductByCategory(String category) {
        Category cat = Category.valueOf(category);
        List<Product> products = productRepository.findAllByCategory(cat);
        List<ProductResponseDto> dtos = products.stream()
                .map(productConverter::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductResponseDto>> findProductByCategoryAndName(String category, String name) {
        Category categoryName = Category.valueOf(category);
        List<Product> products = productRepository.findAllByCategoryAndAndName(categoryName, name);
        List<ProductResponseDto> dtos = products.stream()
                .map(productConverter::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
