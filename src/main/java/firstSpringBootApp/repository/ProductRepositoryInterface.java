package firstSpringBootApp.repository;

import secondSpringBootAppWithSpringBootData.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryInterface {

    Integer addProduct(Product product);
    List<Product> findAllProducts();
    Optional<Product> findProductById(Integer id);
    List<Product> findProductByName(String name);
    List<Product> findProductByCategory(String category);
    List<Product> findProductByCategoryAndName(String category, String name);
    boolean updateProduct(Product productForUpdate);
    boolean deleteProduct(Integer id);

}
