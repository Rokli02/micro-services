package hu.me.microservice.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadOrderException extends RuntimeException {
    public BadOrderException() {
        super("A rendelés szervíz nem megfelelően volt használva!");
    }

    public BadOrderException(String arg0) {
        super(arg0);
    }
}
