package secondSpringBootAppWithSpringBootData.controller.api.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import secondSpringBootAppWithSpringBootData.dto.appDTO.OneMessageDTO;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;

@RestController
@RequestMapping("/rent")
public interface UpdateProductControllerApi {

    @Operation(summary = "Update a product by ID",
            description = "Updates an existing product identified by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Product successfully updated",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{ \"message\": \"Product successfully updated\"}"))),
            @ApiResponse(responseCode = "400",
                    description = "Invalid input data or category/user not found",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{ \"message\": \"Category/region with name ... not found.\"}"))),
            @ApiResponse(responseCode = "404",
                    description = "Product not found with the given ID",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{ \"message\": \"Product with ID ... not found.\"}"))),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{ \"message\": \"Internal server error.\"}")))
    })
    @PutMapping("/{id}")
    ResponseEntity<OneMessageDTO> updateProduct(
            @Parameter(description = "ID of the product to be updated", example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Product data to update",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductCreateRequestDto.class),
                            examples = @ExampleObject(value = "{ \"name\": \"New Product Name\", \"category\": { \"name\": \"New Category\" }, \"price\": 99.99, \"description\": \"Updated description\", \"isInStock\": true, \"region\": { \"regionName\": \"New Region\" }}")))
            @RequestBody ProductCreateRequestDto productCreateRequestDto);
}
