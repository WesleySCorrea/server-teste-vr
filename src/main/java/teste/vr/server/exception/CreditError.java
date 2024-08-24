package teste.vr.server.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import teste.vr.server.exception.runtime.CreditLimitExceededException;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditError extends StandardError {
    private BigDecimal creditLimitTotal;
    private BigDecimal totalValueByOrder;
    private BigDecimal limitExceeded;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate expirationDate;
    private String message;

    public CreditError(CreditLimitExceededException e) {
        this.creditLimitTotal = e.getCreditLimitTotal();
        this.totalValueByOrder = e.getTotalValueByOrder();
        this.limitExceeded = e.getLimitExceeded();
        this.expirationDate = e.getExpirationDate();
        this.message = e.getMessage();
    }
}
