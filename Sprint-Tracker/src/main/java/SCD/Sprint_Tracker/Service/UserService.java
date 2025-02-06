package SCD.Sprint_Tracker.Service;

import SCD.Sprint_Tracker.Entity.User;
import SCD.Sprint_Tracker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Value("${admin.github.id}")
    private String adminGithubId;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveOrUpdateUser(User user) {
        Optional<User> existing = userRepository.findByGithubId(user.getGithubId());
        if (existing.isPresent()) {
            User existingUser = existing.get();
            existingUser.setUsername(user.getUsername());
            return userRepository.save(existingUser);
        }
        return userRepository.save(user);
    }

    public User getByGithubId(String githubId) {
        return userRepository.findByGithubId(githubId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public boolean isAdmin(String githubId) {
        return githubId.equals(adminGithubId);
    }
}