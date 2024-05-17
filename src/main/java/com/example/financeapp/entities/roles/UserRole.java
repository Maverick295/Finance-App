package com.example.financeapp.entities.roles;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ADMIN, USER, BANNED;

    @Override
    public String getAuthority() {
        return name();
    }
}
