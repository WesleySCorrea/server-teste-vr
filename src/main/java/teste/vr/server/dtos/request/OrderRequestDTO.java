package teste.vr.server.dtos.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderRequestDTO {
    private Long clientId;
}
//
//    public Clients converterPersisted(ClientResponseDTO clientPersisted) {
//        Clients clients = new Clients();
//        clients.setId(clientPersisted.getId());
//
//        if (this.getName() != null) {
//            clients.setName(this.getName());
//        } else {
//            clients.setName(clientPersisted.getName());
//        }
//        if (this.getLastName() != null) {
//            clients.setLastName(this.getLastName());
//        } else {
//            clients.setLastName(clientPersisted.getLastName());
//        }
//        if (this.getCreditLimit() != null) {
//            clients.setCreditLimit(this.getCreditLimit());
//        } else {
//            clients.setCreditLimit(clientPersisted.getCreditLimit());
//        }
//        if (this.getDueDate() != null) {
//            clients.setDueDay(this.getDueDate());
//        } else {
//            clients.setDueDay(clientPersisted.getDueDate());
//        }
//        if (this.getActive() != null) {
//            clients.setActive(this.getActive());
//        } else {
//            clients.setActive(clientPersisted.getActive());
//        }
//
//        return clients;
//    }
//}

