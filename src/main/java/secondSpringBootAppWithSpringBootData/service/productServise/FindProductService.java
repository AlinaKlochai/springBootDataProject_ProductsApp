package secondSpringBootAppWithSpringBootData.service.productServise;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.ProductSearchResponse;
import secondSpringBootAppWithSpringBootData.dto.errorDto.ErrorResponseDto;
import secondSpringBootAppWithSpringBootData.dto.errorDto.FieldErrorDto;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Category;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.entity.Region;
import secondSpringBootAppWithSpringBootData.exception.NotFoundException;
import secondSpringBootAppWithSpringBootData.repository.ProductRepository;
import secondSpringBootAppWithSpringBootData.service.FindRegionService;
import secondSpringBootAppWithSpringBootData.service.categoryService.FindCategoryService;
import secondSpringBootAppWithSpringBootData.service.util.ProductConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FindProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final FindRegionService findRegionService;
    private final FindCategoryService findCategoryService;
    private final int defaultPageSize = 10;

    public ProductSearchResponse findProducts(String regionName, String categoryName, String productName, int page) {
        List<FieldErrorDto> fieldErrors = new ArrayList<>();

        // Поиск региона и категории, если они указаны
        Optional<Region> regionOpt = Optional.ofNullable(regionName)
                .filter(name -> !name.isEmpty())
                .flatMap(findRegionService::findRegionByNameOptional);
        Optional<Category> categoryOpt = Optional.ofNullable(categoryName)
                .filter(catName -> !catName.isEmpty())
                .flatMap(findCategoryService::findByName);

        // Проверка наличия ошибок только для указанных параметров
        if (regionName != null && !regionName.isEmpty()) {
            validateRegion(regionOpt, fieldErrors);
        }
        if (categoryName != null && !categoryName.isEmpty()) {
            validateCategory(categoryOpt, fieldErrors);
        }

        // Если есть ошибки, возвращаем ответ с пустым списком продуктов
        if (!fieldErrors.isEmpty()) {
            return new ProductSearchResponse(new ArrayList<>(),
                    new ErrorResponseDto("Errors occurred", fieldErrors), 0, 0);
        }

        // Установка пагинации
        Pageable pageable = PageRequest.of(page, defaultPageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Product> productPage = findProductsByCriteria(regionOpt, categoryOpt, productName, pageable);

        List<ProductResponseDto> dtos = productPage.getContent().stream()
                .map(productConverter::toDto)
                .collect(Collectors.toList());

        // Если список продуктов пуст, добавить сообщение об ошибке
        if (dtos.isEmpty()) {
            throw new NotFoundException("No products found for the given criteria");
        }


        // Создаем объект ProductSearchResponse, включая totalElements и totalPages
        return new ProductSearchResponse(
                dtos,
                fieldErrors.isEmpty() ? null : new ErrorResponseDto("Errors occurred", fieldErrors),
                productPage.getTotalElements(),
                productPage.getTotalPages()
        );
    }

    private void validateRegion(Optional<Region> regionOpt, List<FieldErrorDto> fieldErrors) {
        if (regionOpt.isEmpty()) {
            fieldErrors.add(new FieldErrorDto("region", "Region not found"));
        }
    }

    private void validateCategory(Optional<Category> categoryOpt, List<FieldErrorDto> fieldErrors) {
        if (categoryOpt.isEmpty()) {
            fieldErrors.add(new FieldErrorDto("category", "Category not found"));
        }
    }

    private Page<Product> findProductsByCriteria(Optional<Region> regionOpt, Optional<Category> categoryOpt, String productName, Pageable pageable) {
        // Здесь реализуем логику поиска в зависимости от наличия параметров
        if (regionOpt.isPresent() && categoryOpt.isPresent() && productName != null) {
            return productRepository.findAllByRegionAndCategoryAndNameStartingWithIgnoreCase(regionOpt.get(), categoryOpt.get(), productName, pageable);
        } else if (regionOpt.isPresent() && categoryOpt.isPresent()) {
            return productRepository.findAllByRegionAndCategory(regionOpt.get(), categoryOpt.get(), pageable);
        } else if (regionOpt.isPresent() && productName != null) {
            return productRepository.findAllByRegionAndNameStartingWithIgnoreCase(regionOpt.get(), productName, pageable);
        } else if (categoryOpt.isPresent() && productName != null) {
            return productRepository.findAllByCategoryAndNameStartingWithIgnoreCase(categoryOpt.get(), productName, pageable);
        } else if (regionOpt.isPresent()) {
            return productRepository.findAllByRegion(regionOpt.get(), pageable);
        } else if (categoryOpt.isPresent()) {
            return productRepository.findAllByCategory(categoryOpt.get(), pageable);
        } else if (productName != null) {
            return productRepository.findByNameStartingWithIgnoreCase(productName, pageable);
        } else {
            return productRepository.findAll(pageable);
        }
    }

    public ProductResponseDto findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
        return productConverter.toDto(product);
    }

}
