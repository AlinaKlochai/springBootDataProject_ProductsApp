package secondSpringBootAppWithSpringBootData.controller.productController;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;
import secondSpringBootAppWithSpringBootData.service.productServise.FindProductService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class FindProductController {

    private final FindProductService findProductService;

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductResponseDto>> findAllProducts() {
        return new ResponseEntity<>(findProductService.findAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<ProductResponseDto> findProductById(@RequestParam Integer id) {
        ResponseEntity<ProductResponseDto> responseEntity = findProductService.findProductById(id);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<ProductResponseDto>> findProductByName(@RequestParam String name) {
        ResponseEntity<List<ProductResponseDto>> responseEntity = findProductService.findProductByName(name);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }

    @GetMapping("/findByCategory")
    public ResponseEntity<List<ProductResponseDto>> findProductByCategory(@RequestParam String category) {
        ResponseEntity<List<ProductResponseDto>> responseEntity = findProductService.findProductByCategory(category);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }

    @GetMapping("/findByCategoryAndName")
    public ResponseEntity<List<ProductResponseDto>> findProductByCategoryAndName(
            @RequestParam String category,
            @RequestParam String name) {
        ResponseEntity<List<ProductResponseDto>> responseEntity = findProductService.findProductByCategoryAndName(category, name);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }
}
