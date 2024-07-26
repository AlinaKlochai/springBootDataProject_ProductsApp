package secondSpringBootAppWithSpringBootData.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import secondSpringBootAppWithSpringBootData.entity.Category;
import secondSpringBootAppWithSpringBootData.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import secondSpringBootAppWithSpringBootData.entity.Region;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByName(String name);
    List<Product> findAllByCategory(Category category);
    List<Product> findAllByUserId(Long userId);

    List<Product> findAllByRegion(Region region);

    List<Product> findAllByRegionAndCategory(Region region, Category category);

    //игнорируя регистр
    List<Product> findByNameStartingWithIgnoreCase(String name);

    List<Product> findAllByRegionAndNameStartingWithIgnoreCase(Region region, String name);

    List<Product> findAllByRegionAndCategoryAndNameStartingWithIgnoreCase(Region region, Category category, String name);

    List<Product> findAllByCategoryAndNameStartingWithIgnoreCase(Category category, String name);
}
