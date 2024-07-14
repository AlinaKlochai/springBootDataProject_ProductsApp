package secondSpringBootAppWithSpringBootData.service.clientService;

import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.entity.Client;
import secondSpringBootAppWithSpringBootData.exception.NotFoundException;
import secondSpringBootAppWithSpringBootData.repository.ClientRepository;

import java.util.Optional;

@Service
public class DeleteClientService {

    private final ClientRepository clientRepository;


    public DeleteClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void deleteClient(Integer id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        } else {
            throw new NotFoundException("Client not found with id: " + id);
        }
    }

}
