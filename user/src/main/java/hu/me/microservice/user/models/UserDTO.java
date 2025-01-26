package hu.me.microservice.user.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private Boolean isActive;
    private Integer rights;
}
