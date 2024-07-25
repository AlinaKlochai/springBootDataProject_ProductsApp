package secondSpringBootAppWithSpringBootData.controller.productController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import secondSpringBootAppWithSpringBootData.controller.api.product.FindProductControllerApi;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;
import secondSpringBootAppWithSpringBootData.service.productServise.FindProductService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rents")
public class FindProductController implements FindProductControllerApi {

    private final FindProductService findProductService;

//    @GetMapping
//    public ResponseEntity<List<ProductResponseDto>> findLatestProducts(@RequestParam(defaultValue = "10") int limit) {
//        return findProductService.findLatestProducts(limit);
//    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAllProducts() {
        return new ResponseEntity<>(findProductService.findAllProducts(), HttpStatus.OK);
    }


    @GetMapping("/byId")
    public ResponseEntity<ProductResponseDto> findProductById(@RequestParam Long id) {
        ResponseEntity<ProductResponseDto> responseEntity = findProductService.findProductById(id);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }


    @GetMapping("/allByName")
    public ResponseEntity<List<ProductResponseDto>> findProductByName(@RequestParam String name) {
        ResponseEntity<List<ProductResponseDto>> responseEntity = findProductService.findProductByName(name);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }


    @GetMapping("/allByCategory")
    public ResponseEntity<List<ProductResponseDto>> findProductByCategory(@RequestParam String category) {
        ResponseEntity<List<ProductResponseDto>> responseEntity = findProductService.findProductByCategory(category);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }


    @GetMapping("/allByCategoryAndName")
    public ResponseEntity<List<ProductResponseDto>> findProductByCategoryAndName(
            @RequestParam String category,
            @RequestParam String name) {
        ResponseEntity<List<ProductResponseDto>> responseEntity = findProductService.findProductByCategoryAndName(category, name);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }


    @GetMapping("/allProductsByUser")
    public ResponseEntity<List<ProductResponseDto>> findAllProductsByUser(@RequestParam Long userId) {
        ResponseEntity<List<ProductResponseDto>> responseEntity = findProductService.findAllByUserId(userId);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }

    @GetMapping("/allByRegion")
    public ResponseEntity<List<ProductResponseDto>> findAllByRegion(@RequestParam String region) {
        ResponseEntity<List<ProductResponseDto>> responseEntity = findProductService.findAllByRegion(region);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }

     @GetMapping("/allByRegionAndCategory")
    public ResponseEntity<List<ProductResponseDto>> findAllByRegionAndCategory(
            @RequestParam String region,
            @RequestParam String category) {
        ResponseEntity<List<ProductResponseDto>> responseEntity = findProductService.findAllByRegionAndCategory(region, category);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }

    @GetMapping("/allByRegionAndName")
    public ResponseEntity<List<ProductResponseDto>> findAllByRegionAndName(
            @RequestParam String region,
            @RequestParam String name) {
        return findProductService.findAllByRegionAndName(region, name);
    }

    @GetMapping("/allByRegionCategoryAndName")
    public ResponseEntity<List<ProductResponseDto>> findAllByRegionAndCategoryAndName(
            @RequestParam String regionName,
            @RequestParam String categoryName,
            @RequestParam String name) {
        return findProductService.findAllByRegionAndCategoryAndName(regionName, categoryName, name);
    }
}
