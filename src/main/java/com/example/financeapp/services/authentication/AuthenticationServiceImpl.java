package com.example.financeapp.services.authentication;

import com.example.financeapp.services.detail.CustomUserDetailsService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = Logger.getLogger(AuthenticationServiceImpl.class.getName());

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

        logger.log(Level.INFO, "Аутентификация пользователя {0} успешно установлена в ContextHolder", username);
    }

    @Override
    public void setAuthentication(@NotNull Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
