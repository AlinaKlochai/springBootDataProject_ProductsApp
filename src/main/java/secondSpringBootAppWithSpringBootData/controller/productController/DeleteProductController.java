package secondSpringBootAppWithSpringBootData.controller.productController;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secondSpringBootAppWithSpringBootData.controller.api.product.DeleteProductControllerApi;
import secondSpringBootAppWithSpringBootData.dto.appDTO.OneMessageDTO;
import secondSpringBootAppWithSpringBootData.service.productServise.DeleteProductService;
import secondSpringBootAppWithSpringBootData.service.user.UserFindService;

@RestController
@AllArgsConstructor
@RequestMapping("/rent")
public class DeleteProductController implements DeleteProductControllerApi {

    private final DeleteProductService deleteProductService;

    @DeleteMapping("/{id}")
    public ResponseEntity<OneMessageDTO> deleteProductById(@PathVariable Long id) {
        System.out.println("Received request to delete product with id: " + id);
        return deleteProductService.deleteProduct(id);
    }


}
