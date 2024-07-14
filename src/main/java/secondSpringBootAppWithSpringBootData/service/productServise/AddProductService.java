package secondSpringBootAppWithSpringBootData.service.productServise;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.entity.Client;
import secondSpringBootAppWithSpringBootData.entity.Product;
import secondSpringBootAppWithSpringBootData.dto.productDto.ProductCreateRequestDto;
import secondSpringBootAppWithSpringBootData.repository.ClientRepository;
import secondSpringBootAppWithSpringBootData.repository.ProductRepository;
import secondSpringBootAppWithSpringBootData.service.util.ProductConverter;

import java.util.Optional;


@Service
public class AddProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final ClientRepository clientRepository;

    public AddProductService(ProductRepository productRepository, ProductConverter productConverter, ClientRepository clientRepository) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
        this.clientRepository = clientRepository;
    }

//    public ResponseEntity<Integer> addProduct(ProductCreateRequestDto requestDto) {
//        Optional<Client> clientOpt = clientRepository.findById(requestDto.getClient());
//
//        if (clientOpt.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        Product productForAdd = productConverter.fromDto(requestDto);
//        productForAdd.setClient(clientOpt.get());
//
//        productRepository.save(productForAdd);
//        return new ResponseEntity<>(productForAdd.getId(), HttpStatus.CREATED);
//    }

    public ResponseEntity<Integer> addProduct(ProductCreateRequestDto requestDto) {
        try {
            Product productForAdd = productConverter.fromDto(requestDto);

            // Проверка, указан ли клиент
            if (requestDto.getClient() != null) {
                Optional<Client> clientOpt = clientRepository.findById(requestDto.getClient());

                if (clientOpt.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                productForAdd.setClient(clientOpt.get());
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
