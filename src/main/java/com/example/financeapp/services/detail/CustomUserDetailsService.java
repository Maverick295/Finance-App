package com.example.financeapp.services.detail;

import com.example.financeapp.entities.User;
import com.example.financeapp.entities.roles.UserRole;
import com.example.financeapp.services.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(@NotNull String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Неудалось найти пользователя с таким именем"));

        List<GrantedAuthority> roles = getAuthority(user);

        return CustomUserDetails.build(
                user,
                roles
        );
    }

    private List<GrantedAuthority> getAuthority(@NotNull User user) {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.isLocked() ? user.getRole() : UserRole.BANNED.getAuthority()));

        return roles;
    }
}
