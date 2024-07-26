package secondSpringBootAppWithSpringBootData.controller.productController;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import secondSpringBootAppWithSpringBootData.controller.api.product.UpdateProductControllerApi;
import secondSpringBootAppWithSpringBootData.dto.appDTO.OneMessageDTO;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;
import secondSpringBootAppWithSpringBootData.service.productServise.UpdateProductService;

@RestController
@AllArgsConstructor
@RequestMapping("/rent")
public class UpdateProductController implements UpdateProductControllerApi {

    private final UpdateProductService updateProductService;

    @Override
    @PutMapping("{id}")
    public ResponseEntity<OneMessageDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductCreateRequestDto productCreateRequestDto) {
        return updateProductService.updateProduct(id, productCreateRequestDto);
    }
}
