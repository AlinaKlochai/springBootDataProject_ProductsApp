package secondSpringBootAppWithSpringBootData.controller.productController;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import secondSpringBootAppWithSpringBootData.dto.ProductSearchResponse;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;
import secondSpringBootAppWithSpringBootData.service.productServise.FindProductService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rents")
public class FindProductController{

    private final FindProductService findProductService;

    @GetMapping
    public ResponseEntity<ProductSearchResponse> findProducts(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page) {
        ProductSearchResponse response = findProductService.findProducts(region, category, name, page);

        if (response.getError() != null) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        ProductResponseDto product = findProductService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
