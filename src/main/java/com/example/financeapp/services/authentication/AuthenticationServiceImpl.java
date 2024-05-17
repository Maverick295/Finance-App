package com.example.financeapp.services.authentication;

import com.example.financeapp.services.detail.CustomUserDetailsService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public AuthenticationServiceImpl(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public void setUserAuthentication(@NotNull String username) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                )
        );
    }

    @Override
    public void setAuthentication(@NotNull Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
