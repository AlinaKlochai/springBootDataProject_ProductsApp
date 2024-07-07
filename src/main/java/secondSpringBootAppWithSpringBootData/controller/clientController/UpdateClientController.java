package secondSpringBootAppWithSpringBootData.controller.clientController;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import secondSpringBootAppWithSpringBootData.dto.clientDto.ClientCreateRequestDto;
import secondSpringBootAppWithSpringBootData.dto.clientDto.ClientResponseDto;
import secondSpringBootAppWithSpringBootData.service.clientService.UpdateClientService;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class UpdateClientController {

    private final UpdateClientService updateClientService;

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDto> updateClient(@PathVariable Integer id, @RequestBody ClientCreateRequestDto clientCreateRequestDto) {
        ClientResponseDto updatedClient = updateClientService.updateClient(id, clientCreateRequestDto);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }
}
