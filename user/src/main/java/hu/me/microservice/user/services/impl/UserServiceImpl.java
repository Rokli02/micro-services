package hu.me.microservice.user.services.impl;

import hu.me.microservice.user.entity.User;
import hu.me.microservice.user.models.UserDTO;
import hu.me.microservice.user.repository.UserRepository;
import hu.me.microservice.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(String username, String password) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        if (!user.isActive()) {
            throw new IllegalArgumentException("User account is not active");
        }

        String token = user.getUsername() + ":" + user.getId();
        return Base64.getEncoder().encodeToString(token.getBytes());
    }

    @Override
    public String signUp(UserDTO newUser) {
        if (userRepository.findByUsername(newUser.getUsername()).isPresent()) {
            return "Username is already taken";
        }

        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()));

        user.setActive(true);
        userRepository.save(user);


        return "Registration successful";
    }

    @Override
    public User checkToken(String token) {
        try {
            String decodedToken = new String(Base64.getDecoder().decode(token));
            String[] parts = decodedToken.split(":");

            if (parts.length != 2) {
                return null;
            }

            String username = parts[0];
            Long id = Long.parseLong(parts[1]);

            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (user.getUsername().equals(username) && user.isActive()) {
                    return user;
                }
            }

        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
