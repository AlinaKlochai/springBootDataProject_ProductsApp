package secondSpringBootAppWithSpringBootData.controller.productController;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secondSpringBootAppWithSpringBootData.controller.api.product.DeleteProductControllerApi;
import secondSpringBootAppWithSpringBootData.service.productServise.DeleteProductService;

@RestController
@AllArgsConstructor
@RequestMapping("/rent")
public class DeleteProductController implements DeleteProductControllerApi {

    private final DeleteProductService deleteProductService;


    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        deleteProductService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
