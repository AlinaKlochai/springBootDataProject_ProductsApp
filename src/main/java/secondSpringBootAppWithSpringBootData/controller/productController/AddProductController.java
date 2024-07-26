package secondSpringBootAppWithSpringBootData.controller.productController;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secondSpringBootAppWithSpringBootData.controller.api.product.AddProductControllerApi;
import secondSpringBootAppWithSpringBootData.dto.appDTO.OneMessageDTO;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.repository.CategoryRepository;
import secondSpringBootAppWithSpringBootData.service.productServise.AddProductService;

@RestController
@RequestMapping("/rent")
@AllArgsConstructor
public class AddProductController implements AddProductControllerApi {

    private final AddProductService addProductService;
    private final CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<?> addNewProduct(@Valid @RequestBody ProductCreateRequestDto request) {
        return addProductService.addProduct(request);
    }
}
