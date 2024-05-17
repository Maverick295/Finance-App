package com.example.financeapp.services.detail;

import com.example.financeapp.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> roles;
    private boolean enable;
    private boolean locked;

    private CustomUserDetails(
            String username,
            String password,
            List<GrantedAuthority> roles,
            boolean enable,
            boolean locked
    ) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.enable = enable;
        this.locked = locked;
    }

    public static CustomUserDetails build(
            User user,
            List<GrantedAuthority> roles
    ) {
        return new CustomUserDetails(
                user.getUsername(),
                user.getPassword(),
                roles,
                user.isEnable(),
                user.isLocked()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
