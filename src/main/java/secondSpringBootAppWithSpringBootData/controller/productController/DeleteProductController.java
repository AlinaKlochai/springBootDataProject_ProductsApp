package secondSpringBootAppWithSpringBootData.controller.productController;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import secondSpringBootAppWithSpringBootData.service.productServise.DeleteProductService;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class DeleteProductController {

    private final DeleteProductService deleteProductService;

    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<Boolean> deleteProductById(@PathVariable Integer id) {
        return deleteProductService.deleteProduct(id);
    }


}
