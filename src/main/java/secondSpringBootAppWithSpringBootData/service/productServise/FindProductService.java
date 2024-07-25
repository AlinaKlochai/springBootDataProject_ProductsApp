package secondSpringBootAppWithSpringBootData.service.productServise;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Category;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.entity.Region;
import secondSpringBootAppWithSpringBootData.exception.NotFoundException;
import secondSpringBootAppWithSpringBootData.repository.CategoryRepository;
import secondSpringBootAppWithSpringBootData.repository.ProductRepository;
import secondSpringBootAppWithSpringBootData.repository.RegionRepository;
import secondSpringBootAppWithSpringBootData.service.util.ProductConverter;

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

    public List<ProductResponseDto> findAllProducts() {
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

    public ResponseEntity<List<ProductResponseDto>> findProductByName(String name) {

        List<Product> products = productRepository.findByNameStartingWithIgnoreCase(name);

        if (products.isEmpty()) {
            throw new NotFoundException("List of products with name '" + name + "' not found");
        }

        List<ProductResponseDto> dtos = products.stream()
                .map(productConverter::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductResponseDto>> findProductByCategory(String categoryName) {
        Optional<Category> categoryOpt = categoryRepository.findByName(categoryName);

        if (categoryOpt.isEmpty()) {
            throw new NotFoundException("Category not found");
        }

        Category category = categoryOpt.get();
        List<Product> products = productRepository.findAllByCategory(category);
        if (products.isEmpty()) {
            throw new NotFoundException("No products found");
        }

        List<ProductResponseDto> dtos = products.stream()
                .map(productConverter::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductResponseDto>> findProductByCategoryAndName(String categoryName, String name) {
        Optional<Category> categoryOpt = categoryRepository.findByName(categoryName);
        if (categoryOpt.isEmpty()) {
            throw new NotFoundException("Category not found");
        }

        Category category = categoryOpt.get();
        // List<Product> products = productRepository.findAllByCategoryAndName(category, name);
//        if (products.isEmpty()) {
//            throw new NotFoundException("No products found for the specified category and name");
//        }
//
//        List<ProductResponseDto> dtos = products.stream()
//                .map(productConverter::toDto)
//                .collect(Collectors.toList());
//
//        return new ResponseEntity<>(dtos, HttpStatus.OK);

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

        // Преобразовать отфильтрованные продукты в DTO
        List<ProductResponseDto> dtos = filteredProducts.stream()
                .map(productConverter::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
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

    public ResponseEntity<List<ProductResponseDto>> findAllByRegion(String regionName) {
        Optional<Region> regionOpt = regionRepository.findByRegionName(regionName);

        if (regionOpt.isEmpty()) {
            throw new NotFoundException("Region with name " + regionName + " not found.");
        }

        Region region = regionOpt.get();
        List<Product> products = productRepository.findAllByRegion(region);

        if (products.isEmpty()) {
            throw new NotFoundException("No products found for the specified region");
        }

        List<ProductResponseDto> dtos = products.stream()
                .map(productConverter::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductResponseDto>> findAllByRegionAndCategory(String regionName, String categoryName) {
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

        List<ProductResponseDto> dtos = products.stream()
                .map(productConverter::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductResponseDto>> findAllByRegionAndName(String regionName, String name) {
        Optional<Region> regionOpt = regionRepository.findByRegionName(regionName);
        if (regionOpt.isEmpty()) {
            throw new NotFoundException("Region with name " + regionName + " not found.");
        }

        Region region = regionOpt.get();
        List<Product> products = productRepository.findAllByRegionAndName(region, name);

        if (products.isEmpty()) {
            throw new NotFoundException("No products found for the specified region and name");
        }

        List<ProductResponseDto> dtos = products.stream()
                .map(productConverter::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductResponseDto>> findAllByRegionAndCategoryAndName(String regionName, String categoryName, String name) {
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
        List<Product> products = productRepository.findAllByRegionAndCategoryAndName(region, category, name);

        if (products.isEmpty()) {
            throw new NotFoundException("No products found for the specified region, category, and name");
        }

        List<ProductResponseDto> dtos = products.stream()
                .map(productConverter::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }



//    public ResponseEntity<List<ProductResponseDto>> findLatestProducts(int limit) {
//        List<Product> products = productRepository.findTop10ByOrderByCreationDateDesc(PageRequest.of(0, limit));
//        List<ProductResponseDto> dtos = products.stream()
//                .map(productConverter::toDto)
//                .collect(Collectors.toList());
//        return new ResponseEntity<>(dtos, HttpStatus.OK);
//    }

}
