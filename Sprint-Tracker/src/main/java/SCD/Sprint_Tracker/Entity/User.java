package SCD.Sprint_Tracker.Entity;

import SCD.Sprint_Tracker.Entity.Enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
public class User {

    // Getters and Setters
    @Id
    private String email;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public String getGithubId() {
            return null;
    }

    public boolean isEmpty() {
        return false;
    }

    public void setGithubId(String githubId) {

    }
}
