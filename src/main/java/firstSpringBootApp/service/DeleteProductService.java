package firstSpringBootApp.service;

import firstSpringBootApp.repository.ProductRepositoryInterface;
import firstSpringBootApp.repository.ProductRepositoryMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductService {
    private final ProductRepositoryInterface repository;


    public DeleteProductService(ProductRepositoryInterface repository) {
        this.repository = repository;
    }

    public ResponseEntity<Boolean> deleteProduct(Integer id) {
        boolean deleteResult = repository.deleteProduct(id);

        if (deleteResult) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
