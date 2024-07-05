package secondSpringBootAppWithSpringBootData.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import secondSpringBootAppWithSpringBootData.dto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.ProductResponseDto;
import secondSpringBootAppWithSpringBootData.service.UpdateProductService;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class UpdateProductController {

    private final UpdateProductService updateProductService;

    @PutMapping("/updateProductById/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Integer id, @RequestBody ProductCreateRequestDto productCreateRequestDto) {
        return updateProductService.updateProduct(id, productCreateRequestDto);
    }
}
