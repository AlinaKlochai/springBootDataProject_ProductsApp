package secondSpringBootAppWithSpringBootData.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    Optional<Product> findById(Long id);
    Page<Product> findAllByRegionAndCategoryAndNameStartingWithIgnoreCase(Region region, Category category, String name, Pageable pageable);
    Page<Product> findAllByRegionAndCategory(Region region, Category category, Pageable pageable);
    Page<Product> findAllByRegionAndNameStartingWithIgnoreCase(Region region, String name, Pageable pageable);
    Page<Product> findAllByCategoryAndNameStartingWithIgnoreCase(Category category, String name, Pageable pageable);
    Page<Product> findAllByRegion(Region region, Pageable pageable);
    Page<Product> findAllByCategory(Category category, Pageable pageable);
    Page<Product> findByNameStartingWithIgnoreCase(String name, Pageable pageable);
}
