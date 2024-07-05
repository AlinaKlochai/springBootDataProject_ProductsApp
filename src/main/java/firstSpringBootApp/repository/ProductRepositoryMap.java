package firstSpringBootApp.repository;

import secondSpringBootAppWithSpringBootData.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepositoryMap implements ProductRepositoryInterface{

    private Integer productId = 0;
    private Map<Integer, Product> database = new HashMap<>();

    @Override
    public Integer addProduct(Product product) {
        product.setId(++productId);
        database.put(productId, product);
        return productId;
    }

    @Override
    public List<Product> findAllProducts() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<Product> findProductById(Integer id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Product> findProductByName(String name) {
        return database.values().stream()
                .filter(product -> product.getName().equals(name))
                .toList();
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return database.values().stream()
                .filter(product -> product.getCategory().name().equalsIgnoreCase(category))
                .toList();
    }

    @Override
    public List<Product> findProductByCategoryAndName(String category, String name) {
        return database.values().stream()
                .filter(product -> product.getCategory().name().equalsIgnoreCase(category) && product.getName().equals(name))
                .toList();
    }

    @Override
    public boolean updateProduct(Product productForUpdate) {
        if(database.get(productForUpdate.getId()) != null) {
            database.put(productForUpdate.getId(), productForUpdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(Integer id) {
        return database.remove(id) != null;
    }
}
