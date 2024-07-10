package secondSpringBootAppWithSpringBootData.service.util;

import org.springframework.stereotype.Service;
import secondSpringBootAppWithSpringBootData.dto.clientDto.ClientResponseDto;
import secondSpringBootAppWithSpringBootData.entity.Client;

@Service
public class ClientConverter {

    public ClientResponseDto clientToDto(Client client) {
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setId(client.getId());
        clientResponseDto.setFirstName(client.getFirstName());
        clientResponseDto.setLastName(client.getLastName());
        clientResponseDto.setPassword(client.getPassword());
        clientResponseDto.setEmail(client.getEmail());
        clientResponseDto.setProducts(client.getProducts());
        return clientResponseDto;
    }
}
