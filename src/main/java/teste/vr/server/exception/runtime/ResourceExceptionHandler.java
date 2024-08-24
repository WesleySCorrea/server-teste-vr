package teste.vr.server.exception.runtime;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import teste.vr.server.exception.CreditError;
import teste.vr.server.exception.FieldMessage;
import teste.vr.server.exception.ValidationError;

import java.time.Instant;
import java.util.Collections;

@EnableWebMvc
@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CreditLimitExceededException.class)
    public ResponseEntity<Object> creditLimitExceededException(CreditLimitExceededException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        CreditError err = new CreditError(e);
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Object> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ValidationError err = new ValidationError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError(e.getMessage());
        err.setPath(request.getRequestURI());
        err.setErrors(Collections.singletonList(new FieldMessage("ObjectNotFoundException",e.getMessage())));
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(PersistFailedException.class)
    public ResponseEntity<Object> persistFailedException(PersistFailedException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ValidationError err = new ValidationError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError(e.getMessage());
        err.setPath(request.getRequestURI());
        err.setErrors(Collections.singletonList(new FieldMessage("PersistFailedException",e.getMessage())));
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DuplicateProductAttemptException.class)
    public ResponseEntity<Object> duplicateVoteAttemptException(DuplicateProductAttemptException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        ValidationError err = new ValidationError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError(e.getMessage());
        err.setPath(request.getRequestURI());
        err.setErrors(Collections.singletonList(new FieldMessage("DuplicateProductAttemptException",e.getMessage())));
        return ResponseEntity.status(status).body(err);
    }
}