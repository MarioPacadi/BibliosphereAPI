package hr.algebra.bibliosphereapi.controller;

import hr.algebra.bibliosphereapi.dto.LoginUserDto;
import hr.algebra.bibliosphereapi.dto.TokensDto;
import hr.algebra.bibliosphereapi.dto.UserDto;
import hr.algebra.bibliosphereapi.models.Account;
import hr.algebra.bibliosphereapi.models.RefreshToken;
import hr.algebra.bibliosphereapi.models.Roles;
import hr.algebra.bibliosphereapi.repository.UserRepository;
import hr.algebra.bibliosphereapi.security.jwt.JwtUtils;
import hr.algebra.bibliosphereapi.security.service.RefreshTokenService;
import hr.algebra.bibliosphereapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    AuthenticationManager authenticationManager;

    UserRepository userRepository;

    UserService userService;

    PasswordEncoder encoder;

    JwtUtils jwtUtils;

    RefreshTokenService refreshTokenService;

    @Value("${booksapi.app.jwtRefreshCookieName}")
    private String jwtRefreshCookie;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder encoder, JwtUtils jwtUtils, RefreshTokenService refreshTokenService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<TokensDto> authenticateUser(@Valid @RequestBody LoginUserDto loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Account accountDetails = (Account) authentication.getAuthorities();

        String jwt = jwtUtils.generateTokenFromUsername(accountDetails.getUsername(), Collections.singletonList(Roles.get(accountDetails.getRoleId())));
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(accountDetails.getId());

        return ResponseEntity.ok().body(new TokensDto(jwt, refreshToken.getToken()));
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> registerUser(@RequestBody UserDto userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));

        Optional<Account> userExists=userRepository.findByUsername(userDto.getUsername());
        if (userExists.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        userService.signIn(userDto.getUser());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/signout")
    public ResponseEntity<Void> logoutUser() {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!principle.toString().equals("anonymousUser")) {
            Long userId = ((Account) principle).getId();
            refreshTokenService.deleteByUserId(userId);
        }

        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<TokensDto> refreshtoken(HttpServletRequest request) {
        String refreshToken = jwtUtils.getToken(request, jwtRefreshCookie);

        if ((refreshToken != null) && (refreshToken.length() > 0)) {
            Optional<RefreshToken> refreshTokenFromDatabase = refreshTokenService.findByToken(refreshToken);

            Optional<Account> user = refreshTokenFromDatabase.map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUser);

            if (user.isPresent()) {
                String jwt = jwtUtils.generateTokenFromUsername(user.get().getUsername(), Arrays.asList(Roles.get(user.get().getRoleId())));

                return ResponseEntity.ok().body(new TokensDto(jwt, refreshToken));
            }
        }

        return ResponseEntity.badRequest().build();
    }
}
