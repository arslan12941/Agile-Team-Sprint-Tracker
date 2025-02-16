package SCD.Sprint_Tracker.Entity;

import SCD.Sprint_Tracker.Entity.Enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String githubId;

    private String username;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
