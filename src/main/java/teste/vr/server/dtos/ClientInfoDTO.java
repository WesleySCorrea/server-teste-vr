package teste.vr.server.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class ClientInfoDTO {

    private BigDecimal creditLimit;
    private Integer dueDay;
}
