package hu.me.microservice.order.services.impl;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import hu.me.microservice.order.exception.BadOrderException;
import hu.me.microservice.order.models.UserDto;
import hu.me.microservice.order.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    private String getTokenFromHeader(Map<String, String> headers) throws Unauthorized {
        String auth = headers.get("Authorization");
        if (auth == null) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }

        String[] splitAuth = auth.split(auth);
        if (!splitAuth[0].equals("Bearer ")) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
        
        return splitAuth[1];
    }

    //TODO: Meghívni a User Service-t és tőle lekérdezni az érvényességet és az adatot, de addig is
    private UserDto checkToken(String token) throws BadOrderException, Unauthorized {
        if (token.equals("error")) {
            throw new BadOrderException();
        }

        if (token.equals("unauthorized")) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
        
        return new UserDto(0L, "test0", "Test Bob", LocalDate.now());
    }

    @Override
    public Long getUserId(Map<String, String> headers) throws BadOrderException, Unauthorized {
        try {
            String token = this.getTokenFromHeader(headers);
            UserDto user = this.checkToken(token);

            return user == null ? null : user.getId();
        } catch (HttpClientErrorException exc) {
            return null;
        }
    }

}
