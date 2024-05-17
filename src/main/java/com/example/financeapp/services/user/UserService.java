package com.example.financeapp.services.user;

import com.example.financeapp.entities.User;
import com.example.financeapp.forms.RegistrationForm;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    void save(User user);
    User create(RegistrationForm form);
    User getAuthenticatedUser();
}
