package secondSpringBootAppWithSpringBootData.service.productServise;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import secondSpringBootAppWithSpringBootData.dto.ProductSearchResponse;
import secondSpringBootAppWithSpringBootData.dto.errorDto.ErrorResponseDto;
import secondSpringBootAppWithSpringBootData.dto.errorDto.FieldErrorDto;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Category;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.entity.Region;
import secondSpringBootAppWithSpringBootData.exception.NotFoundException;
import secondSpringBootAppWithSpringBootData.repository.CategoryRepository;
import secondSpringBootAppWithSpringBootData.repository.ProductRepository;
import secondSpringBootAppWithSpringBootData.repository.RegionRepository;
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
    private final CategoryRepository categoryRepository;
    private final RegionRepository regionRepository;

    public ProductSearchResponse findProducts(String regionName, String categoryName, String name) {
        List<FieldErrorDto> fieldErrors = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        Optional<Region> regionOpt = regionName != null ? regionRepository.findByRegionName(regionName) : Optional.empty();
        Optional<Category> categoryOpt = categoryName != null ? categoryRepository.findByName(categoryName) : Optional.empty();

        if (regionName != null && categoryName != null && name != null) {
            products = findByRegionAndCategoryAndName(regionOpt, categoryOpt, name, fieldErrors);
        } else if (regionName != null && categoryName != null) {
            products = findByRegionAndCategory(regionOpt, categoryOpt, fieldErrors);
        } else if (regionName != null && name != null) {
            products = findByRegionAndName(regionOpt, name, fieldErrors);
        } else if (categoryName != null && name != null) {
            products = findByCategoryAndName(categoryOpt, name, fieldErrors);
        } else if (regionName != null) {
            products = findByRegion(regionOpt, fieldErrors);
        } else if (categoryName != null) {
            products = findByCategory(categoryOpt, fieldErrors);
        } else if (name != null) {
            products = findByName(name, fieldErrors);
        } else {
            products = productRepository.findAll();
        }

        if (products.isEmpty() && fieldErrors.isEmpty()) {
            fieldErrors.add(new FieldErrorDto("criteria", "No products found for the specified criteria", null));
        }

        List<ProductResponseDto> dtos = products.stream()
                .map(productConverter::toDto)
                .collect(Collectors.toList());

        return new ProductSearchResponse(dtos, fieldErrors.isEmpty() ? null : new ErrorResponseDto("Errors occurred", fieldErrors));
    }


    private List<Product> findByRegionAndCategoryAndName(Optional<Region> regionOpt, Optional<Category> categoryOpt, String name, List<FieldErrorDto> fieldErrors) {
        List<Product> products = new ArrayList<>();
        if (regionOpt.isEmpty()) {
            fieldErrors.add(new FieldErrorDto("region", "Region not found", null));
        }
        if (categoryOpt.isEmpty()) {
            fieldErrors.add(new FieldErrorDto("category", "Category not found", null));
        }
        if (regionOpt.isPresent() && categoryOpt.isPresent()) {
            products = productRepository.findAllByRegionAndCategoryAndNameStartingWithIgnoreCase(regionOpt.get(), categoryOpt.get(), name);
        }
        return products;
    }

    private List<Product> findByRegionAndCategory(Optional<Region> regionOpt, Optional<Category> categoryOpt, List<FieldErrorDto> fieldErrors) {
        List<Product> products = new ArrayList<>();
        if (regionOpt.isEmpty()) {
            fieldErrors.add(new FieldErrorDto("region", "Region not found", null));
        }
        if (categoryOpt.isEmpty()) {
            fieldErrors.add(new FieldErrorDto("category", "Category not found", null));
        }
        if (regionOpt.isPresent() && categoryOpt.isPresent()) {
            products = productRepository.findAllByRegionAndCategory(regionOpt.get(), categoryOpt.get());
        }
        return products;
    }

    private List<Product> findByRegionAndName(Optional<Region> regionOpt, String name, List<FieldErrorDto> fieldErrors) {
        List<Product> products = new ArrayList<>();
        if (regionOpt.isEmpty()) {
            fieldErrors.add(new FieldErrorDto("region", "Region not found", null));
        } else {
            products = productRepository.findAllByRegionAndNameStartingWithIgnoreCase(regionOpt.get(), name);
        }
        return products;
    }

    private List<Product> findByCategoryAndName(Optional<Category> categoryOpt, String name, List<FieldErrorDto> fieldErrors) {
        List<Product> products = new ArrayList<>();
        if (categoryOpt.isEmpty()) {
            fieldErrors.add(new FieldErrorDto("category", "Category not found", null));
        } else {
            products = productRepository.findAllByCategoryAndNameStartingWithIgnoreCase(categoryOpt.get(), name);
        }
        return products;
    }

    private List<Product> findByRegion(Optional<Region> regionOpt, List<FieldErrorDto> fieldErrors) {
        List<Product> products = new ArrayList<>();
        if (regionOpt.isEmpty()) {
            fieldErrors.add(new FieldErrorDto("region", "Region not found", null));
        } else {
            products = productRepository.findAllByRegion(regionOpt.get());
        }
        return products;
    }

    private List<Product> findByCategory(Optional<Category> categoryOpt, List<FieldErrorDto> fieldErrors) {
        List<Product> products = new ArrayList<>();
        if (categoryOpt.isEmpty()) {
            fieldErrors.add(new FieldErrorDto("category", "Category not found", null));
        } else {
            products = productRepository.findAllByCategory(categoryOpt.get());
        }
        return products;
    }

    private List<Product> findByName(String name, List<FieldErrorDto> fieldErrors) {
        List<Product> products = productRepository.findByNameStartingWithIgnoreCase(name);
        if (products.isEmpty()) {
            fieldErrors.add(new FieldErrorDto("name", "No products found with name starting with '" + name + "'", null));
        }
        return products;
    }


    private List<ProductResponseDto> findAllProducts() {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            throw new NotFoundException("No products found");
        }
       return productRepository.findAll().stream()
               .map(productConverter::toDto)
               .toList();
    }

    public ResponseEntity<ProductResponseDto> findProductById(Long productId) {

        return productRepository.findById(productId)
                .map(productConverter::toDto)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseThrow(() -> new NotFoundException("No product with id: " + productId));
    }

    public List<Product> findAllByCategoryAndName(String categoryName, String name) {
        Optional<Category> categoryOpt = categoryRepository.findByName(categoryName);
        if (categoryOpt.isEmpty()) {
            throw new NotFoundException("Category not found");
        }

        Category category = categoryOpt.get();

        List<Product> products = productRepository.findAllByCategory(category);
        System.out.println("Found products by category: " + products);

        // Фильтровать продукты по имени
        List<Product> filteredProducts = products.stream()
                .filter(product -> product.getName().toLowerCase().startsWith(name.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("Filtered products: " + filteredProducts);

        // Проверить, есть ли отфильтрованные продукты
        if (filteredProducts.isEmpty()) {
            throw new NotFoundException("No products found for the specified category and name");
        }

        return filteredProducts;
    }


    public ResponseEntity<List<ProductResponseDto>> findAllByUserId(Long userId){
        List<ProductResponseDto> allProductsByUser = productRepository.findAllByUserId(userId).stream()
                .map(productConverter::toDto)
                .toList();
        if (allProductsByUser.isEmpty()) {
            throw new NotFoundException("No products found by userId: " + userId);
        }
        return ResponseEntity.ok(allProductsByUser);
    }

    public List<Product> findAllByRegion(String regionName) {
        Optional<Region> regionOpt = regionRepository.findByRegionName(regionName);

        if (regionOpt.isEmpty()) {
            throw new NotFoundException("Region with name " + regionName + " not found.");
        }

        Region region = regionOpt.get();
        List<Product> products = productRepository.findAllByRegion(region);

        if (products.isEmpty()) {
            throw new NotFoundException("No products found for the specified region");
        }

        return products;
    }

    public List<Product> findAllByRegionAndCategory(String regionName, String categoryName) {
        Optional<Region> regionOpt = regionRepository.findByRegionName(regionName);
        if (regionOpt.isEmpty()) {
            throw new NotFoundException("Region with name " + regionName + " not found.");
        }

        Optional<Category> categoryOpt = categoryRepository.findByName(categoryName);
        if (categoryOpt.isEmpty()) {
            throw new NotFoundException("Category with name " + categoryName + " not found.");
        }

        Region region = regionOpt.get();
        Category category = categoryOpt.get();
        List<Product> products = productRepository.findAllByRegionAndCategory(region, category);

        if (products.isEmpty()) {
            throw new NotFoundException("No products found for the specified region and category");
        }

        return products;
    }

    public List<Product> findAllByRegionAndName(String regionName, String name) {

        Region region = regionRepository.findByRegionName(regionName)
                .orElseThrow(() -> new NotFoundException("Region with name " + regionName + " not found."));

        if (name == null || name.isEmpty()) {
            List<Product> products = productRepository.findAllByRegion(region);
            if (products.isEmpty()) {
                throw new NotFoundException("No products found for the specified region");
            }
            return products;
        }

        List<Product> products = productRepository.findAllByRegionAndNameStartingWithIgnoreCase(region, name);
        if (products.isEmpty()) {
            throw new NotFoundException("No products found for the specified region and name starting with '" + name + "'");
        }
        return products;
    }

    public List<Product> findAllByRegionAndCategoryAndName(String regionName, String categoryName, String name) {

        Region region = regionRepository.findByRegionName(regionName)
                .orElseThrow(() -> new NotFoundException("Region with name " + regionName + " not found."));

        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new NotFoundException("Category with name " + categoryName + " not found."));

        if (name == null || name.isEmpty()) {
            List<Product> products = productRepository.findAllByRegionAndCategory(region, category);
            if (products.isEmpty()) {
                throw new NotFoundException("No products found for the specified region and category");
            }
            return products;
        }

        List<Product> products = productRepository.findAllByRegionAndCategoryAndNameStartingWithIgnoreCase(region, category, name);
        if (products.isEmpty()) {
            throw new NotFoundException("No products found for the specified region, category, and name starting with '" + name + "'");
        }

        return products;
    }
}
