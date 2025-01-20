package hu.me.microservice.order.services;

import java.util.Map;

import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import hu.me.microservice.order.exception.BadOrderException;

public interface UserService {
    Long getUserId(Map<String, String> headers) throws BadOrderException, Unauthorized;
}
