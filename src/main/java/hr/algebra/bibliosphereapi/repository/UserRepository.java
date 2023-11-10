package hr.algebra.bibliosphereapi.repository;

import hr.algebra.bibliosphereapi.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {

    @Query("SELECT u FROM Account u WHERE u.email = :email AND u.password = :password")
    Account findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("SELECT u FROM Account u WHERE u.username = :username")
    Optional<Account> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM Account u WHERE u.email = :email")
    Optional<Account> existsByEmail(@Param("email") String email);
}

