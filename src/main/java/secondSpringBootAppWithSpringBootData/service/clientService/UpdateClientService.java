package secondSpringBootAppWithSpringBootData.service.clientService;

import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.clientDto.ClientCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.clientDto.ClientResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Client;
import secondSpringBootAppWithSpringBootData.repository.ClientRepository;
import secondSpringBootAppWithSpringBootData.service.util.ClientConverter;

import java.util.Optional;

@Service
public class UpdateClientService {

    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;

    public UpdateClientService(ClientRepository clientRepository, ClientConverter clientConverter) {
        this.clientRepository = clientRepository;
        this.clientConverter = clientConverter;
    }


    public ClientResponseDto updateClient(Integer id, ClientCreateRequestDto clientCreateRequestDto) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.setFirstName(clientCreateRequestDto.getFirstName());
            client.setLastName(clientCreateRequestDto.getLastName());
            client.setPassword(clientCreateRequestDto.getPassword());
            client.setEmail(clientCreateRequestDto.getEmail());

            Client updatedClient = clientRepository.save(client);
            return clientConverter.clientToDto(updatedClient);
        } else {
            throw new RuntimeException("Client not found with id: " + id);
        }
    }
}

