package secondSpringBootAppWithSpringBootData.service.productServise;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.repository.ProductRepository;
import secondSpringBootAppWithSpringBootData.service.util.ProductConverter;


@Service
public class AddProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public AddProductService(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    public ResponseEntity<Integer> addProduct(ProductCreateRequestDto requestDto) {
        Product productForAdd = productConverter.fromDto(requestDto);

         productRepository.save(productForAdd);
        return new ResponseEntity<>(productForAdd.getId(), HttpStatus.CREATED);
    }
}
