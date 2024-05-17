package com.example.financeapp.services.authentication;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    void setUserAuthentication(String username);
    void setAuthentication(Authentication authentication);
}
