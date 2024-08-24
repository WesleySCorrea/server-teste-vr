package teste.vr.server.exception.runtime;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderIsEmptyException extends RuntimeException {

    public OrderIsEmptyException(String msg){
        super(msg);
    }
}