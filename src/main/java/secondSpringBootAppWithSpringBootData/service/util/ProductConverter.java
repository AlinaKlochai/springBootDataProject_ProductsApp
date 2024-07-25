package secondSpringBootAppWithSpringBootData.service.util;



import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.RegionDto;
import secondSpringBootAppWithSpringBootData.dto.categoryDto.CategoryCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Category;
import secondSpringBootAppWithSpringBootData.entity.Region;
import secondSpringBootAppWithSpringBootData.entity.User;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.exception.NotFoundException;
import secondSpringBootAppWithSpringBootData.repository.CategoryRepository;
import secondSpringBootAppWithSpringBootData.repository.RegionRepository;
import secondSpringBootAppWithSpringBootData.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductConverter {

    private final UserRepository userRepository;
    public final CategoryRepository categoryRepository;
    public final RegionRepository regionRepository;


    public Product fromDto(ProductCreateRequestDto dto){
        Product product = new Product();

        if(dto.getName() != null){
            product.setName(dto.getName());
        }

        if (dto.getCategory() != null) {
            Optional<Category> categoryOpt = categoryRepository.findByName(dto.getCategory().getName());
            categoryOpt.ifPresent(product::setCategory);
            // Category category = categoryRepository.findByName(dto.getCategory().getName())
                    //  .orElseThrow(() -> new NotFoundException("Category not found"));
            //product.setCategory(category);
        }

        if(dto.getPrice() != null){
            product.setPrice(dto.getPrice());
        }

        if(dto.getIsInStock() != null){
            product.setIsInStock(dto.getIsInStock());
        }

        if(dto.getDescription() != null){
            product.setDescription(dto.getDescription());
        }

        if (dto.getRegion() != null) {
            Optional<Region> regionOpt = regionRepository.findByRegionName(dto.getRegion().getRegionName());
            regionOpt.ifPresent(product::setRegion);
        }

        if (dto.getUser() != null) {
            Optional<User> userOpt = userRepository.findById(dto.getUser());
            userOpt.ifPresent(product::setUser);
        }

//        if (dto.getRegion() != null) {
//            Region region = regionRepository.findByRegionName(dto.getRegion().getRegionName());
//                  //  .orElseThrow(() -> new NotFoundException("Region not found"));
//            product.setRegion(region);
//        }
//
//        if (dto.getUser() != null) {
//            User user = userRepository.findById(dto.getUser());
//                    //  .orElseThrow(() -> new NotFoundException("User not found"));
//            product.setUser(user);
//        }

        return product;
    }

    public ProductResponseDto toDto(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();

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

        return productResponseDto;
    }
}
