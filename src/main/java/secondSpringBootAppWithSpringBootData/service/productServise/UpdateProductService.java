package secondSpringBootAppWithSpringBootData.service.productServise;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.appDTO.OneMessageDTO;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Category;
import secondSpringBootAppWithSpringBootData.entity.Region;
import secondSpringBootAppWithSpringBootData.entity.User;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.repository.CategoryRepository;
import secondSpringBootAppWithSpringBootData.repository.RegionRepository;
import secondSpringBootAppWithSpringBootData.repository.UserRepository;
import secondSpringBootAppWithSpringBootData.repository.ProductRepository;
import secondSpringBootAppWithSpringBootData.service.user.UserFindService;
import secondSpringBootAppWithSpringBootData.service.util.ProductConverter;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final RegionRepository regionRepository;
    private final UserFindService userFindService;


    public ResponseEntity<OneMessageDTO> updateProduct(Long productId, ProductCreateRequestDto productCreateRequestDto) {
        Optional<Product> existingProductOpt = productRepository.findById(productId);

        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            User currentUser = userFindService.getUserFromContext();

            // Проверка, что текущий пользователь является владельцем продукта
            if (!existingProduct.getUser().getId().equals(currentUser.getId())) {
                return new ResponseEntity<>(new OneMessageDTO("You do not have the rights to update this product."), HttpStatus.FORBIDDEN); // У пользователя нет прав на обновление этого продукта
            }

            if (productCreateRequestDto.getName() != null) {
                existingProduct.setName(productCreateRequestDto.getName());
            }
            if (productCreateRequestDto.getCategory() != null) {
                Optional<Category> categoryOpt = categoryRepository.findByName(productCreateRequestDto.getCategory().getName());
                if (categoryOpt.isPresent()) {
                    existingProduct.setCategory(categoryOpt.get());
                } else {
                    return new ResponseEntity<>(new OneMessageDTO("Category with name " + productCreateRequestDto.getCategory().getName() + " not found."), HttpStatus.NOT_FOUND);
                }
            }
            if (productCreateRequestDto.getPrice() != null) {
                existingProduct.setPrice(productCreateRequestDto.getPrice());
            }
            if (productCreateRequestDto.getIsInStock() != null) {
                existingProduct.setIsInStock(productCreateRequestDto.getIsInStock());
            }
            if (productCreateRequestDto.getDescription() != null) {
                existingProduct.setDescription(productCreateRequestDto.getDescription());
            }
            if (productCreateRequestDto.getRegion() != null) {
                Optional<Region> regionOpt = regionRepository.findByRegionName(productCreateRequestDto.getRegion().getRegionName());
                if (regionOpt.isPresent()) {
                    existingProduct.setRegion(regionOpt.get());
                } else {
                    return new ResponseEntity<>(new OneMessageDTO("Region with name " + productCreateRequestDto.getRegion().getRegionName() + " not found."), HttpStatus.NOT_FOUND);
                }
            }

            productRepository.save(existingProduct);

            return new ResponseEntity<>(new OneMessageDTO("Your product has been successfully updated."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new OneMessageDTO("Product with name " + productId + " not found."), HttpStatus.NOT_FOUND);
        }
    }
}


