package firstSpringBootApp.controller;

import secondSpringBootAppWithSpringBootData.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import firstSpringBootApp.service.FindProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class FindProductController {

    private FindProductService findProductService;

    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts() {
        return findProductService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable int id) {
        return findProductService.findProductById(id);
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<Product>> findProductByName(@RequestParam(value = "nameProduct") String name) {
        return findProductService.findProductByName(name);
    }

    @GetMapping("/findProductByCategory")
    public ResponseEntity<List<Product>> findProductByCategory(@RequestParam(value = "category") String category) {
        return findProductService.findProductByCategory(category);
    }

    @GetMapping("/findProductByCategoryAndName")
    public ResponseEntity<List<Product>> findProductByCategoryAndProduct(@RequestParam(value = "category") String category, @RequestParam(value = "product") String productName) {
        return findProductService.findProductByCategoryAndName(category, productName);
    }
}
