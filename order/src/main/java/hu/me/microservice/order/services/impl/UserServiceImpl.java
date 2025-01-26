package hu.me.microservice.order.services.impl;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
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

    private UserDTO checkToken(String token) throws BadOrderException, Unauthorized {
        try {

            String uri = "http://localhost:3001/api/auth/checkToken?token={token}";
            RestTemplate request = new RestTemplate();
            
            UserDTO user = request.getForObject(uri, UserDTO.class, token);
            
            return user;
        } catch (RuntimeException exc) {
            exc.printStackTrace();
            return null;
        }
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
