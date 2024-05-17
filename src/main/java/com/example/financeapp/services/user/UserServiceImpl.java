package com.example.financeapp.services.user;

import com.example.financeapp.entities.User;
import com.example.financeapp.entities.roles.UserRole;
import com.example.financeapp.forms.RegistrationForm;
import com.example.financeapp.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUsername(@NotNull String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(@NotNull User user) {
        userRepository.save(user);
    }

    @Override
    public User create(@NotNull RegistrationForm form) {
        return new User()
                .setUsername(form.getUsername())
                .setPassword(
                        passwordEncoder.encode(form.getPassword())
                )
                .setRole(UserRole.USER.getAuthority())
                .setPhone(form.getPhone())
                .setEnable(true)
                .setLocked(true);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return findByUsername(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("Неудалось найти пользовател с таким именем"));
    }
}
