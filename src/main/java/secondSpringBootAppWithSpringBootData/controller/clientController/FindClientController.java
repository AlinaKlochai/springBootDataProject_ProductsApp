package secondSpringBootAppWithSpringBootData.controller.clientController;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secondSpringBootAppWithSpringBootData.dto.clientDto.ClientResponseDto;
import secondSpringBootAppWithSpringBootData.service.clientService.FindClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class FindClientController {

    private final FindClientService findClientService;

    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> findAllClients() {
        List<ClientResponseDto> clients = findClientService.findAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> findClientById(@PathVariable Integer id) {
        ClientResponseDto client = findClientService.findClientById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
    @GetMapping("/lastName/{lastName}")
    public ResponseEntity<List<ClientResponseDto>> findClientsByLastName(@PathVariable String lastName) {
        List<ClientResponseDto> clients = findClientService.findClientsByLastName(lastName);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ClientResponseDto> findClientByEmail(@PathVariable String email) {
        ClientResponseDto client = findClientService.findClientByEmail(email);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ClientResponseDto>> findClientsByProductId(@PathVariable Integer productId) {
        List<ClientResponseDto> clients = findClientService.findClientsByProductId(productId);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
}
