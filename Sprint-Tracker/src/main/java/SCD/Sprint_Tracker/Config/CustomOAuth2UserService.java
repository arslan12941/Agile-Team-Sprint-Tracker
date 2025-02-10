package SCD.Sprint_Tracker.Config;

import SCD.Sprint_Tracker.Entity.User;
import SCD.Sprint_Tracker.Entity.Enums.UserRole;
import SCD.Sprint_Tracker.Repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final SCD.Sprint_Tracker.Config.OAuth2Properties oAuth2Properties;

    public CustomOAuth2UserService(UserRepository userRepository, OAuth2Properties oAuth2Properties) {
        this.userRepository = userRepository;
        this.oAuth2Properties = oAuth2Properties;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String githubId = String.valueOf(attributes.get("id"));
        String username = String.valueOf(attributes.get("login"));

        User user = userRepository.findByGithubId(githubId)
                .orElseGet(User::new);

        user.setGithubId(githubId);
        user.setUsername(username);
        user.setRole(githubId.equals(oAuth2Properties.getGithubId()) ? UserRole.ADMIN : UserRole.DEVELOPER);
        userRepository.save(user);

        return new DefaultOAuth2User(
                Set.of(() -> "ROLE_" + user.getRole().name()),
                attributes,
                "login"
        );
    }
}
