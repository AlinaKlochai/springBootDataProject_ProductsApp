package secondSpringBootAppWithSpringBootData.service.clientService;

import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.clientDto.ClientCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.clientDto.ClientResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Client;
import secondSpringBootAppWithSpringBootData.exception.AlreadyExistException;
import secondSpringBootAppWithSpringBootData.repository.ClientRepository;
import secondSpringBootAppWithSpringBootData.service.util.ClientConverter;

@Service
public class AddClientService {


    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;

    public AddClientService(ClientRepository clientRepository, ClientConverter clientConverter) {
        this.clientRepository = clientRepository;
        this.clientConverter = clientConverter;
    }

//    public ClientResponseDto addClient(ClientCreateRequestDto clientCreateRequestDto) {
//        Client client = new Client();
//        client.setFirstName(clientCreateRequestDto.getFirstName());
//        client.setLastName(clientCreateRequestDto.getLastName());
//        client.setPassword(clientCreateRequestDto.getPassword());
//        client.setEmail(clientCreateRequestDto.getEmail());
//
//        Client savedClient = clientRepository.save(client);
//        return clientConverter.clientToDto(savedClient);
//    }
public ClientResponseDto addClient(ClientCreateRequestDto clientCreateRequestDto) {
    if (clientRepository.findClintByEmail(clientCreateRequestDto.getEmail()).isPresent()) {
        throw new AlreadyExistException("Client with the given email already exists.");
    }

    Client client = new Client();
    client.setFirstName(clientCreateRequestDto.getFirstName());
    client.setLastName(clientCreateRequestDto.getLastName());
    client.setPassword(clientCreateRequestDto.getPassword());
    client.setEmail(clientCreateRequestDto.getEmail());

    Client savedClient = clientRepository.save(client);
    return clientConverter.clientToDto(savedClient);
}
}
