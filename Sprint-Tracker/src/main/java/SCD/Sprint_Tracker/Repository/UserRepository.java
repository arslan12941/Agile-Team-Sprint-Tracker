package SCD.Sprint_Tracker.Repository;

import SCD.Sprint_Tracker.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
    User findByEmail(String email);

    Optional<User> findByGithubId(String githubId);
}
