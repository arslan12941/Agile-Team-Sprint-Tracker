package SCD.Sprint_Tracker.Service;

import SCD.Sprint_Tracker.DTO.RegistrationDTO;
import SCD.Sprint_Tracker.Model.User;
import SCD.Sprint_Tracker.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean existsByUsernameOrEmail(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email);
    }

    public void registerUser(RegistrationDTO registrationDTO) {
        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setEmail(registrationDTO.getEmail());
        user.setPhoneNumber(registrationDTO.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        userRepository.save(user);
    }
}