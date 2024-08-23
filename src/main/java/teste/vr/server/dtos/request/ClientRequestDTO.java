package teste.vr.server.dtos.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import teste.vr.server.dtos.response.ClientResponseDTO;
import teste.vr.server.entities.Clients;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientRequestDTO {

    private String name;
    private String lastName;
    private BigDecimal creditLimit;
    @Min(1)
    @Max(31)
    private Integer dueDate;

    public Clients converterDTO() {
        Clients clients = new Clients();
        clients.setName(this.getName());
        clients.setLastName(this.getLastName());
        clients.setCreditLimit(this.getCreditLimit());
        clients.setDueDay(this.getDueDate());
        return clients;
    }

    public Clients converterPersisted(ClientResponseDTO clientPersisted) {
        Clients clients = new Clients();
        clients.setId(clientPersisted.getId());

        if (this.getName() != null) {
            clients.setName(this.getName());
        } else {
            clients.setName(clientPersisted.getName());
        }

        if (this.getLastName() != null) {
            clients.setLastName(this.getLastName());
        } else {
            clients.setLastName(clientPersisted.getLastName());
        }

        if (this.getCreditLimit() != null) {
            clients.setCreditLimit(this.getCreditLimit());
        } else {
            clients.setCreditLimit(clientPersisted.getCreditLimit());
        }

        if (this.getDueDate() != null) {
            clients.setDueDay(this.getDueDate());
        } else {
            clients.setDueDay(clientPersisted.getDueDate());
        }

        return clients;
    }
}
