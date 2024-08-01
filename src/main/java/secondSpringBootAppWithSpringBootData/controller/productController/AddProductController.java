package secondSpringBootAppWithSpringBootData.controller.productController;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import secondSpringBootAppWithSpringBootData.controller.api.product.AddProductControllerApi;
import secondSpringBootAppWithSpringBootData.dto.appDTO.OneMessageDTO;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.repository.CategoryRepository;
import secondSpringBootAppWithSpringBootData.service.productServise.AddProductService;

@RestController
@RequestMapping("/rent")
@AllArgsConstructor
public class AddProductController {

    private final AddProductService addProductService;
    private final ObjectMapper objectMapper;


    // Метод для добавления продукта с изображением
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> addNewProduct(
            @RequestPart("product") String productJson,
            @RequestPart(required = false, value = "image") MultipartFile image) {
        try {
            // Преобразуем JSON в объект ProductCreateRequestDto
            ProductCreateRequestDto request = objectMapper.readValue(productJson, ProductCreateRequestDto.class);
            // Добавляем продукт с изображением или без него
            return addProductService.addProduct(request, image);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid JSON format or error during product creation: " + e.getMessage());
        }
    }

    // Метод для добавления продукта без изображения
    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> addNewProduct(@Valid @RequestBody ProductCreateRequestDto request) {
        // Вызываем метод сервиса для добавления продукта без изображения
        return addProductService.addProductWithoutImage(request);
    }
}
