package secondSpringBootAppWithSpringBootData.controller.productController;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;
import secondSpringBootAppWithSpringBootData.service.productServise.UpdateProductService;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class UpdateProductController {

    private final UpdateProductService updateProductService;

    @PutMapping("/updateProductById/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductCreateRequestDto productCreateRequestDto) {
        return updateProductService.updateProduct(id, productCreateRequestDto);
    }
}
