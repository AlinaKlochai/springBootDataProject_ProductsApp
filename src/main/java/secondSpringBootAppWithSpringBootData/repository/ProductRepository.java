package secondSpringBootAppWithSpringBootData.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import secondSpringBootAppWithSpringBootData.entity.Category;
import secondSpringBootAppWithSpringBootData.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByName(String name);
    List<Product> findAllByCategory(Category category);

    //   @Query("SELECT p FROM Product p WHERE p.category =: category AND p.name =:name")
    List<Product> findAllByCategoryAndAndName(@Param("category") Category category,@Param("name") String name);

}
