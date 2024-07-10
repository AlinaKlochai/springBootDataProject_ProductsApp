package secondSpringBootAppWithSpringBootData.service.util;

import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Client;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.repository.ClientRepository;

@Service
public class ProductConverter {

    private final ClientRepository clientRepository;

    public ProductConverter(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Product fromDto(ProductCreateRequestDto dto){
        Product product = new Product();

        if(dto.getName() != null){
            product.setName(dto.getName());
        }

        if(dto.getCategory() != null){
            product.setCategory(dto.getCategory());
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
        if(product.getCategory() != null){
            productResponseDto.setCategory(product.getCategory());
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
