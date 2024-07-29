package secondSpringBootAppWithSpringBootData.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import secondSpringBootAppWithSpringBootData.dto.errorDto.ErrorResponseDto;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductSearchResponse {
    private List<ProductResponseDto> products;
    private ErrorResponseDto error;
    private long totalElements;
    private int totalPages;
}
