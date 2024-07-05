package firstSpringBootApp.service;

import secondSpringBootAppWithSpringBootData.entity.Product;
import firstSpringBootApp.repository.ProductRepositoryInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindProductService {

    private final ProductRepositoryInterface repository;

    public FindProductService(ProductRepositoryInterface repository) {
        this.repository = repository;
    }

    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> allProducts = repository.findAllProducts();

        if (!allProducts.isEmpty()) {
            return new ResponseEntity<>(allProducts, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(allProducts,HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<Product> findProductById(Integer id) {
        Optional<Product> foundProductOpt = repository.findProductById(id);

        if (foundProductOpt.isPresent()) {
            return new ResponseEntity<>(foundProductOpt.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new Product(),HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Product>> findProductByName(String name) {
        List<Product> foundProducts = repository.findProductByName(name);

        if (!foundProducts.isEmpty()) {
            return new ResponseEntity<>(foundProducts, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(foundProducts,HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<List<Product>> findProductByCategory(String category) {
        List<Product> foundProducts = repository.findProductByCategory(category);
        if (!foundProducts.isEmpty()) {
            return new ResponseEntity<>(foundProducts, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(foundProducts,HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<List<Product>> findProductByCategoryAndName(String category, String name) {
        List<Product> foundProducts = repository.findProductByCategoryAndName(category, name);
        if (!foundProducts.isEmpty()) {
            return new ResponseEntity<>(foundProducts, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(foundProducts,HttpStatus.NO_CONTENT);
        }
    }
}
