package teste.vr.server.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import teste.vr.server.entities.Clients;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientResponseDTO {

    private Long id;
    private String name;
    private String lastName;
    private BigDecimal creditLimit;
    private Integer dueDate;

    public ClientResponseDTO (Clients client) {
        this.id = client.getId();
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.creditLimit = client.getCreditLimit();
        this.dueDate = client.getDueDay();
    }
}
