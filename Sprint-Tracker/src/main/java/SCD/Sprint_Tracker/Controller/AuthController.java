package SCD.Sprint_Tracker.Controller;

import SCD.Sprint_Tracker.Entity.User;
import SCD.Sprint_Tracker.Entity.Enums.UserRole;
import SCD.Sprint_Tracker.Service.UserService;
import SCD.Sprint_Tracker.Config.JwtTokenUtil;
import SCD.Sprint_Tracker.DTO.AuthResponseDto;       // DTO import
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(UserService userService,
                          JwtTokenUtil jwtTokenUtil) {
        this.userService   = userService;
        this.jwtTokenUtil  = jwtTokenUtil;
    }

    @GetMapping("/login/success")
    public AuthResponseDto loginSuccess(OAuth2AuthenticationToken authToken) {
        // 1) Extract GitHub attributes
        String githubId = Objects.requireNonNull(authToken.getPrincipal()
                .getAttribute("id")).toString();
        String username = authToken.getPrincipal()
                .getAttribute("login");

        // 2) Build or update our own User entity
        User user = new User();
        user.setGithubId(githubId);
        user.setUsername(username);
        // ADMIN only if matches your configured admin ID
        if (userService.isAdmin(githubId)) {
            user.setRole(UserRole.ADMIN);
        } else {
            user.setRole(UserRole.EMPLOYEE);
        }
        user = userService.saveOrUpdateUser(user);

        // 3) Generate JWT
        String token = jwtTokenUtil.generateToken(user);

        // 4) Return our DTO
        return new AuthResponseDto(
                token,
                user.getUsername(),
                user.getRole().name()
        );
    }
    @GetMapping("/test")
    public String test() {
        return "✅ Backend is up & running!";
    }
}