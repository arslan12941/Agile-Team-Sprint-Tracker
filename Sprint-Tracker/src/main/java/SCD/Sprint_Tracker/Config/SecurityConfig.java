package SCD.Sprint_Tracker.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home", "/login/**").permitAll()  // Public pages
                        .anyRequest().authenticated()  // All other pages need authentication
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // Optional: custom login page URL, or remove this line to use Spring default login page
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/").permitAll()
                );

        return http.build();
    }
}