package secondSpringBootAppWithSpringBootData.service.util;



import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.categoryDto.CategoryDTO;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Category;
import secondSpringBootAppWithSpringBootData.entity.Client;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.repository.CategoryRepository;
import secondSpringBootAppWithSpringBootData.repository.ClientRepository;

@Service
@AllArgsConstructor
public class ProductConverter {

    private final ClientRepository clientRepository;
    public final CategoryRepository categoryRepository


    public Product fromDto(ProductCreateRequestDto dto){
        Product product = new Product();

        if(dto.getName() != null){
            product.setName(dto.getName());
        }

        if (dto.getCategory() != null) {
            Category category = categoryRepository.findByName(dto.getCategory().getName())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            product.setCategory(category);
        }

        if(dto.getPrice() != null){
            product.setPrice(dto.getPrice());
        }

        if(dto.getIsInStock() != null){
            product.setIsInStock(dto.getIsInStock());
        }

        if (dto.getClient() != null) {
            Client client = clientRepository.findById(dto.getClient())
                    .orElseThrow(() -> new IllegalArgumentException("Client not found"));
            product.setClient(client);
        }

        return product;
    }

    public ProductResponseDto toDto(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        if(product.getName() != null){
            productResponseDto.setName(product.getName());
        }
        if (product.getCategory() != null) {
            CategoryDTO categoryDTO = new CategoryDTO(product.getCategory().getName());
            productResponseDto.setCategory(categoryDTO);
        }
        if(product.getPrice() != null){
            productResponseDto.setPrice(product.getPrice());
        }
        if(product.getIsInStock() != null){
            productResponseDto.setIsInStock(product.getIsInStock());
        }

        return productResponseDto;
    }
}
