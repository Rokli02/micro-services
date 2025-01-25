package hu.me.microservice.order.services.impl;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import hu.me.microservice.order.exception.BadOrderException;
import hu.me.microservice.order.models.UserDTO;
import hu.me.microservice.order.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    private String getTokenFromHeader(Map<String, String> headers) throws Unauthorized {
        String auth = headers.get("authorization");
        if (auth == null) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }

        String[] splitAuth = auth.split(" ", 2);
        if (!splitAuth[0].equals("Bearer")) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
        
        return splitAuth[1];
    }

    //TODO: Meghívni a User Service-t és tőle lekérdezni az érvényességet és az adatot, de addig is
    private UserDTO checkToken(String token) throws BadOrderException, Unauthorized {
        if (token.equals("error")) {
            throw new BadOrderException("Default Error token");
        }

        if (token.equals("unauthorized")) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Gatya");
        }
        
        return new UserDTO(1L, "t_unknown", "Unknown Test", LocalDate.now());
    }

    @Override
    public Long getUserId(Map<String, String> headers) throws BadOrderException, Unauthorized {
        try {
            String token = this.getTokenFromHeader(headers);
            UserDTO user = this.checkToken(token);

            return user == null ? null : user.getId();
        } catch (HttpClientErrorException exc) {
            return null;
        }
    }

}
