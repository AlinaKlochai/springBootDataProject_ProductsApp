package secondSpringBootAppWithSpringBootData.service.clientService;

import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.repository.ClientRepository;

@Service
public class DeleteClientService {

    private final ClientRepository clientRepository;


    public DeleteClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

}
