package secondSpringBootAppWithSpringBootData.service.productServise;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.appDTO.OneMessageDTO;
import secondSpringBootAppWithSpringBootData.dto.validationErrorDto.ValidationErrorDto;
import secondSpringBootAppWithSpringBootData.dto.validationErrorDto.ValidationErrorsDto;
import secondSpringBootAppWithSpringBootData.entity.Region;
import secondSpringBootAppWithSpringBootData.entity.User;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.exception.NotFoundException;
import secondSpringBootAppWithSpringBootData.repository.CategoryRepository;
import secondSpringBootAppWithSpringBootData.repository.RegionRepository;
import secondSpringBootAppWithSpringBootData.repository.UserRepository;
import secondSpringBootAppWithSpringBootData.repository.ProductRepository;
import secondSpringBootAppWithSpringBootData.service.util.ProductConverter;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AddProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductConverter productConverter;
    private final UserRepository userRepository;
    private final RegionRepository regionRepository;

    public ResponseEntity<?> addProduct(ProductCreateRequestDto requestDto) {
        List<ValidationErrorDto> errors = new ArrayList<>();

        // Валидация user
        if (requestDto.getUser() == null) {
            errors.add(new ValidationErrorDto("user", "User not found"));
        } else {
            userRepository.findById(requestDto.getUser()).orElse(null);
            if (!userRepository.existsById(requestDto.getUser())) {
                errors.add(new ValidationErrorDto("user", "User with id " + requestDto.getUser() + " not found."));
            }
        }

        // Валидация region
        if (requestDto.getRegion() == null || requestDto.getRegion().getRegionName() == null) {
            errors.add(new ValidationErrorDto("region", "Region not found"));
        } else {
            if (!regionRepository.findByRegionName(requestDto.getRegion().getRegionName()).isPresent()) {
                errors.add(new ValidationErrorDto("region", "Region with name " + requestDto.getRegion().getRegionName() + " not found."));
            }
        }

        if (requestDto.getCategory() == null) {
            errors.add(new ValidationErrorDto("category", "Category not found"));
        } else {
            if (!categoryRepository.findByName(requestDto.getCategory().getName()).isPresent()) {
                errors.add(new ValidationErrorDto("category", "Category with name " + requestDto.getCategory().getName() + " not found."));
            }
        }

        if (!errors.isEmpty()) {
            return new ResponseEntity<>(new ValidationErrorsDto(errors), HttpStatus.BAD_REQUEST);
        }

        try {

            Product productForAdd = productConverter.fromDto(requestDto);

            productForAdd.setIsInStock(requestDto.getIsInStock() != null ? requestDto.getIsInStock() : true);

            if (requestDto.getUser() != null) {
                User user = userRepository.findById(requestDto.getUser()).orElseThrow();
                productForAdd.setUser(user);
            }

            if (requestDto.getRegion() != null) {
                Region region = regionRepository.findByRegionName(requestDto.getRegion().getRegionName()).orElseThrow();
                productForAdd.setRegion(region);
            }

            productForAdd.setDescription(requestDto.getDescription());

            productRepository.save(productForAdd);

            return new ResponseEntity<>(new OneMessageDTO("Product successfully created"), HttpStatus.CREATED);

        } catch (NotFoundException e) {
            return new ResponseEntity<>(new OneMessageDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Логируем и обрабатываем остальные исключения
            e.printStackTrace();
            return new ResponseEntity<>(new OneMessageDTO("An unexpected error occurred. Please try again later."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
