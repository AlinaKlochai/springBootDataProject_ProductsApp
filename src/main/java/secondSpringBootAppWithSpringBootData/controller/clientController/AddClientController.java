package secondSpringBootAppWithSpringBootData.controller.clientController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secondSpringBootAppWithSpringBootData.dto.clientDto.ClientCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.clientDto.ClientResponseDto;
import secondSpringBootAppWithSpringBootData.service.clientService.AddClientService;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class AddClientController {

    private final AddClientService addClientService;

    @PostMapping("/addNewClient")
    public ResponseEntity<Integer> createClient(@Valid @RequestBody ClientCreateRequestDto clientCreateRequestDto) {
        ClientResponseDto createdClient = addClientService.addClient(clientCreateRequestDto);
        return new ResponseEntity<>(createdClient.getId(), HttpStatus.CREATED);
    }
}
