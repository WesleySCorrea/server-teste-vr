package teste.vr.server.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import teste.vr.server.dtos.request.ClientRequestDTO;
import teste.vr.server.dtos.response.ClientResponseDTO;

public interface ClientService {

    Page<ClientResponseDTO> findAllClients(Pageable pageable);
    ClientResponseDTO findClientById(Long id);
    ClientResponseDTO saveClient(ClientRequestDTO clientRequestDTO);
    ClientResponseDTO updateClient(Long id, ClientRequestDTO clientRequestDTO);
    void deleteClient(Long id);
}
