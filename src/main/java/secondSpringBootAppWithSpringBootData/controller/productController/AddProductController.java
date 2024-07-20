package secondSpringBootAppWithSpringBootData.controller.productController;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.repository.CategoryRepository;
import secondSpringBootAppWithSpringBootData.service.productServise.AddProductService;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class AddProductController {

    private final AddProductService addProductService;
    private final CategoryRepository categoryRepository;

    @PostMapping("/addNewProduct")
    public ResponseEntity<Long> addNewProduct(@Valid @RequestBody ProductCreateRequestDto request){
        return addProductService.addProduct(request);
    }
}
