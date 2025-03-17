package SCD.Sprint_Tracker.Controller;

import SCD.Sprint_Tracker.DTO.RegistrationDTO;
import SCD.Sprint_Tracker.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationDTO registrationDTO,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(getValidationErrors(bindingResult));
        }

        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }

        if (userService.existsByUsernameOrEmail(registrationDTO.getUsername(), registrationDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Username or Email already exists");
        }

        userService.registerUser(registrationDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    private List<String> getValidationErrors(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }
}
