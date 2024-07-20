package secondSpringBootAppWithSpringBootData.service.productServise;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.entity.User;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.repository.UserRepository;
import secondSpringBootAppWithSpringBootData.repository.ProductRepository;
import secondSpringBootAppWithSpringBootData.service.util.ProductConverter;

import java.util.Optional;


@Service
@AllArgsConstructor
public class AddProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final UserRepository userRepository;

    public ResponseEntity<Long> addProduct(ProductCreateRequestDto requestDto) {
        try {
            Product productForAdd = productConverter.fromDto(requestDto);

            // Проверка, указан ли клиент
            if (requestDto.getUser() != null) {
                Optional<User> userOpt = userRepository.findById(Long.valueOf(requestDto.getUser()));

                if (userOpt.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                productForAdd.setUser(userOpt.get());
                productForAdd.setIsInStock(true);
            }

            productRepository.save(productForAdd);
            return new ResponseEntity<>(productForAdd.getId(), HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Логирование исключения (рекомендуется использовать логгер)
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
