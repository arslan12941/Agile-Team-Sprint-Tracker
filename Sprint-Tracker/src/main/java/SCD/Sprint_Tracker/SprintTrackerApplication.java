package SCD.Sprint_Tracker;

import SCD.Sprint_Tracker.Entity.User;
import SCD.Sprint_Tracker.Entity.Enums.UserRole;
import SCD.Sprint_Tracker.Repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SprintTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintTrackerApplication.class, args);
	}

	@Bean
	public ApplicationRunner dataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepository.findByEmail("admin@example.com").isEmpty()) {
				User admin = new User();
				admin.setUsername("admin");
				admin.setEmail("arslannawaz293@example.com");
				admin.setPassword(passwordEncoder.encode("Lucky@12941"));
				admin.setRole(UserRole.ADMIN);
				userRepository.save(admin);
			}
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}