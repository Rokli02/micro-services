package hu.me.microservice.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long Id;
    @Column ( name = "username" )
    private String username;
    @Column( name = "password" )
    private String password;
    @Column ( name = "name" )
    private String name;
    @Column ( name = "email" )
    private String email;
    @Column ( name = "created_at" )
    private LocalDateTime createdAt;
    @Column ( name = "is_active" )
    private Boolean isActive;
    @Column ( name = "rights" )
    private Integer rights;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
