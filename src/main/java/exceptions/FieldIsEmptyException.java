package exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
public class FieldIsEmptyException extends RuntimeException {
    public FieldIsEmptyException(String message) {
        super(message);
    }
}
