package hu.me.microservice.user.services;

import hu.me.microservice.user.entity.User;
import hu.me.microservice.user.models.UserDTO;

public interface UserService
{
    String login(String username, String password);
    String signUp(UserDTO newUser);
    User checkToken(String token);
}
