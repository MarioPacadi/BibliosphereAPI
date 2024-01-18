package hr.algebra.bibliosphereapi.test.security;

import hr.algebra.bibliosphereapi.models.Roles;
import hr.algebra.bibliosphereapi.security.jwt.JwtUtils;
import hr.algebra.bibliosphereapi.test.security.jwt.MockJwtUtils;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@TestConfiguration
public class TestSecurityConfig {

    @Bean
    @Primary  // Ensure that this bean takes precedence over the real JwtUtils
    public JwtUtils jwtUtils() {
        return new MockJwtUtils();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String adminAuth= Roles.ADMIN.getAuthority();

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/actuator/**").permitAll()
                                .requestMatchers("/csrf/v1").permitAll()
                                .requestMatchers("/books/**").permitAll()
                                .anyRequest().authenticated()
                );

        return http.build();
    }
}

