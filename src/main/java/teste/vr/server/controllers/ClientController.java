package teste.vr.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import teste.vr.server.dtos.request.ClientRequestDTO;
import teste.vr.server.dtos.response.ClientResponseDTO;
import teste.vr.server.services.ClientService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<Page<ClientResponseDTO>> findAllPublic(Pageable pageable) {

        Page<ClientResponseDTO> clients = this.clientService.findAllClients(pageable);

        return ResponseEntity.ok().body(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> findClientById(@PathVariable Long id) {

        ClientResponseDTO client = this.clientService.findClientById(id);

        return ResponseEntity.ok().body(client);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> saveClient(@Validated @RequestBody ClientRequestDTO clientRequestDTO) {

        ClientResponseDTO clientPersisted = this.clientService.saveClient(clientRequestDTO);
        URI location = URI.create("/api/your-entities/" + clientPersisted.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(clientPersisted);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Long id,@RequestBody ClientRequestDTO clientResponseDTO) {

        ClientResponseDTO clientPersisted = clientService.updateClient(id, clientResponseDTO);

        return ResponseEntity.ok().body(clientPersisted);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {

        this.clientService.deleteClient(id);

        return ResponseEntity.noContent().build();
    }
}
