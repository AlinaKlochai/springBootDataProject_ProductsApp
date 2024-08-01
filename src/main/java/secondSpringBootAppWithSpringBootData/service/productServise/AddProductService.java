package secondSpringBootAppWithSpringBootData.service.productServise;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import secondSpringBootAppWithSpringBootData.dto.RegionDto;
import secondSpringBootAppWithSpringBootData.dto.appDTO.OneMessageDTO;
import secondSpringBootAppWithSpringBootData.dto.categoryDto.CategoryCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.validationErrorDto.ValidationErrorDto;
import secondSpringBootAppWithSpringBootData.dto.validationErrorDto.ValidationErrorsDto;
import secondSpringBootAppWithSpringBootData.entity.Category;
import secondSpringBootAppWithSpringBootData.entity.Region;
import secondSpringBootAppWithSpringBootData.entity.User;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.exception.NotFoundException;
import secondSpringBootAppWithSpringBootData.repository.CategoryRepository;
import secondSpringBootAppWithSpringBootData.repository.RegionRepository;
import secondSpringBootAppWithSpringBootData.repository.UserRepository;
import secondSpringBootAppWithSpringBootData.repository.ProductRepository;
import secondSpringBootAppWithSpringBootData.service.user.UserFindService;
import secondSpringBootAppWithSpringBootData.service.util.ProductConverter;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class AddProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductConverter productConverter;
    private final RegionRepository regionRepository;
    private final UserFindService userFindService;
    private final SupabaseService supabaseService;

    @Transactional
    public ResponseEntity<?> addProduct(ProductCreateRequestDto requestDto, MultipartFile image) {
        List<ValidationErrorDto> errors = new ArrayList<>();

        // Validate and fetch the Category entity
        Category category = validateAndFetchCategory(requestDto.getCategory(), errors);
        // Validate and fetch the Region entity
        Region region = validateAndFetchRegion(requestDto.getRegion(), errors);

        // If there are any validation errors, return them
        if (!errors.isEmpty()) {
            return new ResponseEntity<>(new ValidationErrorsDto(errors), HttpStatus.BAD_REQUEST);
        }

        // Proceed with product creation
        try {
            Product productForAdd = productConverter.fromDto(requestDto);

            productForAdd.setIsInStock(requestDto.getIsInStock() != null ? requestDto.getIsInStock() : true);

            User user = userFindService.getUserFromContext();
            productForAdd.setUser(user);

            // Set fetched category and region
            productForAdd.setCategory(category);
            productForAdd.setRegion(region);
            productForAdd.setDescription(requestDto.getDescription());

            String imageUrl = supabaseService.uploadImage(image);
            productForAdd.setLink(imageUrl);

            productRepository.save(productForAdd);
            return new ResponseEntity<>(new OneMessageDTO("Product successfully created"), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new OneMessageDTO("An unexpected error occurred. Please try again later."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Category validateAndFetchCategory(CategoryCreateRequestDto categoryDto, List<ValidationErrorDto> errors) {
        if (categoryDto == null) {
            errors.add(new ValidationErrorDto("category", "Category not found"));
            return null;
        }

        return categoryRepository.findByName(categoryDto.getName())
                .orElseGet(() -> {
                    errors.add(new ValidationErrorDto("category", "Category with name " + categoryDto.getName() + " not found."));
                    return null; // Return null if category is not found
                });
    }

    private Region validateAndFetchRegion(RegionDto regionDto, List<ValidationErrorDto> errors) {
        if (regionDto == null || regionDto.getRegionName() == null) {
            errors.add(new ValidationErrorDto("region", "Region not found"));
            return null;
        }

        return regionRepository.findByRegionName(regionDto.getRegionName())
                .orElseGet(() -> {
                    errors.add(new ValidationErrorDto("region", "Region with name " + regionDto.getRegionName() + " not found."));
                    return null; // Return null if region is not found
                });
    }

    @Transactional
    public ResponseEntity<?> addProductWithoutImage(ProductCreateRequestDto requestDto) {
        List<ValidationErrorDto> errors = new ArrayList<>();

        // Валидация и получение категории и региона
        Category category = validateAndFetchCategory(requestDto.getCategory(), errors);
        Region region = validateAndFetchRegion(requestDto.getRegion(), errors);

        // Если есть ошибки валидации, возвращаем их
        if (!errors.isEmpty()) {
            return new ResponseEntity<>(new ValidationErrorsDto(errors), HttpStatus.BAD_REQUEST);
        }

        // Процесс создания продукта
        try {
            Product productForAdd = productConverter.fromDto(requestDto);

            productForAdd.setIsInStock(requestDto.getIsInStock() != null ? requestDto.getIsInStock() : true);
            User user = userFindService.getUserFromContext();
            productForAdd.setUser(user);
            productForAdd.setCategory(category);
            productForAdd.setRegion(region);
            productForAdd.setDescription(requestDto.getDescription());

            // Сохраняем продукт без изображения
            productRepository.save(productForAdd);
            return new ResponseEntity<>(new OneMessageDTO("Product successfully created"), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new OneMessageDTO("An unexpected error occurred. Please try again later."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
