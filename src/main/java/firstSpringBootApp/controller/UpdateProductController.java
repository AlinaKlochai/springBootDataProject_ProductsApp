package firstSpringBootApp.controller;


import secondSpringBootAppWithSpringBootData.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import firstSpringBootApp.service.UpdateProductService;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class UpdateProductController {

    private final UpdateProductService updateProductService;

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return updateProductService.updateProduct(id, product);
    }
}
