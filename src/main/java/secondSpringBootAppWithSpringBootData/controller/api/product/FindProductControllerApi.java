package secondSpringBootAppWithSpringBootData.controller.api.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import secondSpringBootAppWithSpringBootData.dto.ProductSearchResponse;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;

import java.util.List;

@RestController
@RequestMapping("/rents")

public interface FindProductControllerApi {
    /**
     * Finds products based on the specified criteria.
     *
     * @param region   the name of the region (optional).
     * @param category the name of the category (optional).
     * @param name     the name of the product (optional).
     * @return ResponseEntity with the response containing found products or errors.
     */
    @Operation(summary = "Search Products",
            description = "Operation to search for products by region, category, and name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{ \"products\": [...], \"error\": null }"))),
            @ApiResponse(responseCode = "400", description = "Validation error or no products found for criteria",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{ \"error\": {\"message\": \"Errors occurred\", \"fieldErrors\": [{\"field\": \"region\", \"message\": \"Region not found\"}] }} }"))),
            @ApiResponse(responseCode = "404", description = "Region or category not found",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{ \"error\": {\"message\": \"Errors occurred\", \"fieldErrors\": [{\"field\": \"category\", \"message\": \"Category not found\"}] }} }")))
    })
    @GetMapping
    ResponseEntity<ProductSearchResponse> findProducts(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page);
}
