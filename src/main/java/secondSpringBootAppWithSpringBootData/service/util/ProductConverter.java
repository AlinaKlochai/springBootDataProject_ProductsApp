package secondSpringBootAppWithSpringBootData.service.util;



import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.RegionDto;
import secondSpringBootAppWithSpringBootData.dto.categoryDto.CategoryCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;
import secondSpringBootAppWithSpringBootData.dto.userDto.UserJustWithNameDto;
import secondSpringBootAppWithSpringBootData.entity.Category;
import secondSpringBootAppWithSpringBootData.entity.Region;
import secondSpringBootAppWithSpringBootData.entity.User;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.repository.CategoryRepository;
import secondSpringBootAppWithSpringBootData.repository.RegionRepository;
import secondSpringBootAppWithSpringBootData.service.user.UserFindService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductConverter {

    public final CategoryRepository categoryRepository;
    public final RegionRepository regionRepository;
    public final UserFindService userFindService;


    public Product fromDto(ProductCreateRequestDto dto){

        Product product = new Product();

        // Прямое присвоение значений без проверки
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setIsInStock(dto.getIsInStock() != null ? dto.getIsInStock() : true);
        product.setDescription(dto.getDescription());

        // Возвращаем null, если не можем найти категорию или регион
        if (dto.getCategory() != null) {
            product.setCategory(categoryRepository.findByName(dto.getCategory().getName()).orElse(null));
        }

        if (dto.getRegion() != null) {
            product.setRegion(regionRepository.findByRegionName(dto.getRegion().getRegionName()).orElse(null));
        }

        // Получаем пользователя из контекста безопасности
        User user = userFindService.getUserFromContext();
        product.setUser(user);

        return product;
    }

    public ProductResponseDto toDto(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        UserJustWithNameDto userDto = new UserJustWithNameDto(product.getUser().getName());

        if(product.getName() != null){
            productResponseDto.setName(product.getName());
        }
        if (product.getCategory() != null) {
            CategoryCreateRequestDto categoryDTO = new CategoryCreateRequestDto(product.getCategory().getName());
            productResponseDto.setCategory(categoryDTO);
        }
        if(product.getPrice() != null){
            productResponseDto.setPrice(product.getPrice());
        }
        if(product.getIsInStock() != null){
            productResponseDto.setIsInStock(product.getIsInStock());
        }

        if(product.getDescription() != null){
            productResponseDto.setDescription(product.getDescription());
        }

        if (product.getRegion() != null) {
            RegionDto regionDto = new RegionDto(product.getRegion().getRegionName());
            productResponseDto.setRegion(regionDto);
        }
        if(product.getUser() !=null){
            productResponseDto.setOwner(userDto);
        }

        return productResponseDto;
    }
}
