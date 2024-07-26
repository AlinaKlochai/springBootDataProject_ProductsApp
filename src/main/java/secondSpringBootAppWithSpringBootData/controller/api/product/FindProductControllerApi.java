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
    @GetMapping("/byId")
    ResponseEntity<ProductResponseDto> findProductById(@RequestParam Long id);

    @Operation(summary = "Find products by name", description = "Retrieves a list of products matching the given name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products with the specified name",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No products found with the given name"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/allByName")
    ResponseEntity<List<ProductResponseDto>> findProductByName(@RequestParam String name);

    @Operation(summary = "Find products by category", description = "Retrieves a list of products belonging to the specified category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products in the specified category",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Category not found or no products in the category"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/allByCategory")
    ResponseEntity<List<ProductResponseDto>> findProductByCategory(@RequestParam String category);

    @Operation(summary = "Find products by category and name", description = "Retrieves a list of products that match the specified category and name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products matching the specified category and name",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No products found matching the category and name"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/allByCategoryAndName")
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
    @GetMapping("/allProductsByUser")
    ResponseEntity<List<ProductResponseDto>> findAllProductsByUser(@RequestParam Long userId);

    @Operation(summary = "Find products by region", description = "Retrieves a list of products belonging to the specified region.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products in the specified region",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Region not found or no products in the region"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/allByRegion")
    ResponseEntity<List<ProductResponseDto>> findAllByRegion(@RequestParam String region);

    @Operation(summary = "Find products by region and category", description = "Retrieves a list of products belonging to the specified region and category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products in the specified region and category",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Region or category not found or no products in the region and category"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/allByRegionAndCategory")
    ResponseEntity<List<ProductResponseDto>> findAllByRegionAndCategory(
            @RequestParam String region,
            @RequestParam String category);

    @Operation(summary = "Find products by region and name", description = "Retrieves a list of products belonging to the specified region and matching the specified name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products in the specified region and matching the specified name",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Region or name not found or no products in the region matching the name"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/allByRegionAndName")
    ResponseEntity<List<ProductResponseDto>> findAllByRegionAndName(
            @RequestParam String region,
            @RequestParam String name);

    @Operation(summary = "Find products by region, category, and name", description = "Retrieves a list of products that match the specified region, category, and name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products matching the specified region, category, and name",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No products found matching the region, category, and name"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/allByRegionCategoryAndName")
    ResponseEntity<List<ProductResponseDto>> findAllByRegionCategoryAndName(
            @RequestParam String region,
            @RequestParam String category,
            @RequestParam String name);

    @Operation(summary = "Find products by region, category, and/or name", description = "Provide region, category, and/or name to search for products. If none of these parameters are provided, it returns all products.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No products found for the specified criteria"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/search")
    ResponseEntity<List<ProductResponseDto>> findProducts(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String name);
}
