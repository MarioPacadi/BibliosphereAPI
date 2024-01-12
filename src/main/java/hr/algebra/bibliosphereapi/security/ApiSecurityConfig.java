package hr.algebra.bibliosphereapi.security;

import hr.algebra.bibliosphereapi.models.Roles;
import hr.algebra.bibliosphereapi.security.handler.CustomAccessDeniedHandler;
import hr.algebra.bibliosphereapi.security.jwt.AuthEntryPointJwt;
import hr.algebra.bibliosphereapi.security.jwt.AuthTokenFilter;
import hr.algebra.bibliosphereapi.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Order(2)
public class ApiSecurityConfig {

    UserDetailsServiceImpl userDetailsService;

    private AuthEntryPointJwt unauthorizedHandler;

    private final AuthTokenFilter authenticationJwtTokenFilter;

    @Autowired
    public ApiSecurityConfig(UserDetailsServiceImpl userDetailsService, AuthEntryPointJwt unauthorizedHandler, AuthTokenFilter authenticationJwtTokenFilter) {
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.authenticationJwtTokenFilter = authenticationJwtTokenFilter;
    }


    @Bean
    public DaoAuthenticationProvider apiAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager apiAuthenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

//    @Bean
//    public PasswordEncoder apiPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        String adminAuth= Roles.ADMIN.getAuthority();
        String userAuth= Roles.USER.getAuthority();

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/csrf/v1").permitAll()
//                        .requestMatchers("/api/auth/signin").hasAnyAuthority(adminAuth,userAuth)
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex-> ex
                        .accessDeniedHandler(apiAccessDeniedHandler())
                )
                .formLogin(login -> login
                        .defaultSuccessUrl("/books", true)
                        .failureUrl("/login.html?error=true")
                )
                .logout((logout) ->
                        logout.deleteCookies("remove")
                                .invalidateHttpSession(false)
                                .logoutSuccessUrl("/login.html"))
        ;

        http.authenticationProvider(apiAuthenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AccessDeniedHandler apiAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}