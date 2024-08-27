package teste.vr.server.exception.runtime;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderIsFinishedException extends RuntimeException {

    public OrderIsFinishedException(String msg){
        super(msg);
    }
}