package firstSpringBootApp.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import firstSpringBootApp.service.DeleteProductService;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class DeleteProductController {

    private final DeleteProductService deleteProductService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable(name = "id") Integer productId) {
        return deleteProductService.deleteProduct(productId);
    }
}
