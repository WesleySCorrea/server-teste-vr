package teste.vr.server.exception.runtime;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CreditLimitExceededException  extends RuntimeException {
    private BigDecimal creditLimitTotal;
    private BigDecimal totalValueByOrder;
    private BigDecimal limitExceeded;
    private LocalDate expirationDate;
    private String message;

    public CreditLimitExceededException(String msg) {
        super(msg);
    }
}
