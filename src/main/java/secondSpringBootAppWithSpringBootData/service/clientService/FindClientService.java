package secondSpringBootAppWithSpringBootData.service.clientService;

import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.clientDto.ClientResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Client;
import secondSpringBootAppWithSpringBootData.repository.ClientRepository;
import secondSpringBootAppWithSpringBootData.service.util.ClientConverter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FindClientService {

    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;

    public FindClientService(ClientRepository clientRepository, ClientConverter clientConverter) {
        this.clientRepository = clientRepository;
        this.clientConverter = clientConverter;
    }

    public List<ClientResponseDto> findAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(clientConverter::clientToDto).collect(Collectors.toList());
    }


    public ClientResponseDto findClientById(Integer id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            return clientConverter.clientToDto(clientOptional.get());
        } else {
            throw new RuntimeException("Client not found with id: " + id);
        }
    }

    public List<ClientResponseDto> findClientsByLastName(String lastName) {
        List<Client> clients = clientRepository.findAllByLastName(lastName);
        return clients.stream().map(clientConverter::clientToDto).collect(Collectors.toList());
    }

    public ClientResponseDto findClientByEmail(String email) {
        Optional<Client> clientOptional = clientRepository.findAllByEmail(email);
        if (clientOptional.isPresent()) {
            return clientConverter.clientToDto(clientOptional.get());
        } else {
            throw new RuntimeException("Client not found with email: " + email);
        }
    }

    public List<ClientResponseDto> findClientsByProductId(Integer productId) {
        List<Client> clients = clientRepository.findAllByProductsByClientID(productId);
        return clients.stream().map(clientConverter::clientToDto).collect(Collectors.toList());
    }

}