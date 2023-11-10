package hr.algebra.bibliosphereapi.service.impl;


import hr.algebra.bibliosphereapi.models.Account;
import hr.algebra.bibliosphereapi.repository.UserRepository;
import hr.algebra.bibliosphereapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public Account login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Account signIn(Account account) {
        if (userRepository.existsByEmail(account.getEmail()).isEmpty()) {
            return userRepository.saveAndFlush(account);
        }
        return null;
    }

    @Override
    public Optional<Account> getUser() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername());
    }

    @Override
    public List<Account> getAllUsers() {
        return userRepository.findAll();
    }
}
