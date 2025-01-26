package hu.me.microservice.user.controller;

import hu.me.microservice.user.entity.User;
import hu.me.microservice.user.models.UserDTO;
import hu.me.microservice.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody UserDTO newUser) {
        return userService.signUp(newUser);
    }

    @GetMapping("/checkToken")
    public User checkToken(@RequestParam String token) {
        return userService.checkToken(token);
    }
}