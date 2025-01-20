package hu.me.microservice.order.controller.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import hu.me.microservice.order.exception.BadOrderException;

@ControllerAdvice
public class OrderExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ BadOrderException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("status", 400);
        body.put("message", ex.getMessage());

        System.out.println(String.format("'BadOrderException' occured:", ex.getMessage()));

        return handleExceptionInternal(ex, body, null, HttpStatus.BAD_REQUEST, request);
    }
}
