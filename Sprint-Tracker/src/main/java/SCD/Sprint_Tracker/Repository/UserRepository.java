package SCD.Sprint_Tracker.Repository;

import SCD.Sprint_Tracker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);
    boolean existsByUsernameOrEmail(String username, String email);
}