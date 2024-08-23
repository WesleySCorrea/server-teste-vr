package teste.vr.server.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import teste.vr.server.dtos.request.ClientRequestDTO;
import teste.vr.server.dtos.response.ClientResponseDTO;
import teste.vr.server.entities.Clients;
import teste.vr.server.repositories.ClientRepository;
import teste.vr.server.services.ClientService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Page<ClientResponseDTO> findAllClients(Pageable pageable) {

        Page<Clients> clients = clientRepository.findAll(pageable);

        return clients.map(ClientResponseDTO::new);
    }

    @Override
    public ClientResponseDTO findClientById(Long id) {

        Optional<Clients> optionalClient = clientRepository.findById(id);

        return optionalClient.map(ClientResponseDTO::new).orElse(null);
    }

    @Override
    public ClientResponseDTO saveClient(ClientRequestDTO clientRequestDTO) {

        Clients clients = clientRequestDTO.converterDTO();

        try {
            Clients clientPersisted = this.clientRepository.save(clients);

            return new ClientResponseDTO(clientPersisted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ClientResponseDTO updateClient(Long id, ClientRequestDTO clientRequestDTO) {

        var clientPersisted = this.findClientById(id);

        if (clientPersisted == null) {
            throw new RuntimeException("Client not found");
        }

        Clients client = clientRequestDTO.converterPersisted(clientPersisted);

        Clients clientUpdated = this.clientRepository.save(client);

        return new ClientResponseDTO(clientUpdated);

    }

    @Override
    public void deleteClient(Long id) {

        var client = this.findClientById(id);

        if (client == null) {
            throw new RuntimeException("Client not found");
        }
        this.clientRepository.deleteById(id);
    }
}
