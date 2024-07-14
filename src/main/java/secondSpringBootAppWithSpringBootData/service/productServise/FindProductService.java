package secondSpringBootAppWithSpringBootData.service.productServise;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Category;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.exception.NotFoundException;
import secondSpringBootAppWithSpringBootData.repository.ProductRepository;
import secondSpringBootAppWithSpringBootData.service.util.ProductConverter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FindProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public List<ProductResponseDto> findAllProducts() {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            throw new NotFoundException("No products found");
        }
       return productRepository.findAll().stream()
               .map(productConverter::toDto)
               .toList();
    }

    public ResponseEntity<ProductResponseDto> findProductById(int productId) {

        return productRepository.findById(productId)
                .map(productConverter::toDto)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseThrow(() -> new NotFoundException("No product with id: " + productId));
    }

    public ResponseEntity<List<ProductResponseDto>> findProductByName(String name) {
        List<Product> products = productRepository.findAllByName(name);

        if (products.isEmpty()) {
            throw new NotFoundException("List of products with name '" + name + "' not found");
        }

        List<ProductResponseDto> dtos = products.stream()
                .map(productConverter::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductResponseDto>> findProductByCategory(String category) {
        Category cat;
        try {
            cat = Category.valueOf(category);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Category not found");
        }

        List<Product> products = productRepository.findAllByCategory(cat);
        if (products.isEmpty()) {
            throw new NotFoundException("No products found");
        }

        List<ProductResponseDto> dtos = products.stream()
                .map(productConverter::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductResponseDto>> findProductByCategoryAndName(String category, String name) {
        Category categoryName;
        try {
            categoryName = Category.valueOf(category);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Category not found");
        }

        List<Product> products = productRepository.findAllByCategoryAndName(categoryName, name);
        if (products.isEmpty()) {
            throw new NotFoundException("No products found for the specified category and name");
        }

        List<ProductResponseDto> dtos = products.stream()
                .map(productConverter::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
