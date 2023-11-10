package hr.algebra.bibliosphereapi.service;

import hr.algebra.bibliosphereapi.models.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Account login(String email, String password);
    Account signIn(Account account);
    Optional<Account> getUser();
    List<Account> getAllUsers();
}
