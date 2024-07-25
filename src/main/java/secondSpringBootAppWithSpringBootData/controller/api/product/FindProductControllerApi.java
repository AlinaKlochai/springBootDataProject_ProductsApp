package secondSpringBootAppWithSpringBootData.controller.api.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;

import java.util.List;

@RestController
@RequestMapping("/rents")
public interface FindProductControllerApi {

    @Operation(summary = "Find all products", description = "Retrieves a list of all products.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No products found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    ResponseEntity<List<ProductResponseDto>> findAllProducts();

    @Operation(summary = "Find a product by ID", description = "Retrieves a product by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Product not found with the given ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    ResponseEntity<ProductResponseDto> findProductById(@PathVariable Long id);

    @Operation(summary = "Find products by name", description = "Retrieves a list of products matching the given name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products with the specified name",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No products found with the given name"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-name")
    ResponseEntity<List<ProductResponseDto>> findProductByName(@RequestParam String name);

    @Operation(summary = "Find products by category", description = "Retrieves a list of products belonging to the specified category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products in the specified category",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Category not found or no products in the category"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-category")
    ResponseEntity<List<ProductResponseDto>> findProductByCategory(@RequestParam String category);

    @Operation(summary = "Find products by category and name", description = "Retrieves a list of products that match the specified category and name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products matching the specified category and name",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No products found matching the category and name"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-category-and-name")
    ResponseEntity<List<ProductResponseDto>> findProductByCategoryAndName(
            @RequestParam String category,
            @RequestParam String name);

    @Operation(summary = "Find all products by user ID", description = "Retrieves a list of products associated with the specified user ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products for the specified user",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No products found for the given user ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-user")
    ResponseEntity<List<ProductResponseDto>> findAllProductsByUser(@RequestParam Long userId);

}
