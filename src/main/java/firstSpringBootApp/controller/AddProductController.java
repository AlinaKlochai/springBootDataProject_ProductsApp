package firstSpringBootApp.controller;

import secondSpringBootAppWithSpringBootData.dto.ProductCreateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import firstSpringBootApp.service.AddProductService;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class AddProductController {

    private final AddProductService addProductService;

    @PostMapping("/addNewProduct")
    public ResponseEntity<Integer> addNewProduct(@RequestBody ProductCreateRequestDto request){
        return addProductService.addProduct(request);
    }
}
