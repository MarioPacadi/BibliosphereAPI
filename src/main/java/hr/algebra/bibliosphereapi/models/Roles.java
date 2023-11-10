package hr.algebra.bibliosphereapi.models;

import hr.algebra.bibliosphereapi.exception.NotAuthorizedException;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum Roles implements GrantedAuthority {

    ADMIN(1),
    USER(2);

    private final int id;

    Roles(int id) {
        this.id = id;
    }

    public static String get(int number) {
        Optional<Roles> first = Arrays.stream(values()).filter(role -> role.getId() == number).findFirst();
        return first.isPresent() ? first.get().toString() : "";
    }

    public static Roles valueOf(int number) {
        Optional<Roles> first = Arrays.stream(values()).filter(role -> role.getId() == number).findFirst();

        return first.orElseThrow(() -> new NotAuthorizedException("Unauthorized user"));
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
