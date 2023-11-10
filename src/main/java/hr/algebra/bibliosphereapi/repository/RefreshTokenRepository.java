package hr.algebra.bibliosphereapi.repository;


import hr.algebra.bibliosphereapi.models.Account;
import hr.algebra.bibliosphereapi.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(Account user);
}