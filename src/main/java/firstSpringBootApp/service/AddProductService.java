package firstSpringBootApp.service;

import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import firstSpringBootApp.repository.ProductRepositoryInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddProductService {

    private final ProductRepositoryInterface repository;


    public AddProductService(ProductRepositoryInterface repository) {
        this.repository = repository;
    }

    public ResponseEntity<Integer> addProduct(ProductCreateRequestDto request) {
        Product newProduct = new Product(request.getName(),request.getCategory(),request.getPrice(),request.getIsInStock());
        Integer idNewProduct = repository.addProduct(newProduct);
        return new ResponseEntity<>(idNewProduct, HttpStatus.CREATED);
    }
}
