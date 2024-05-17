package com.example.financeapp.controllers;

import com.example.financeapp.entities.User;
import com.example.financeapp.services.authentication.AuthenticationService;
import com.example.financeapp.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Autowired
    public HomeController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView homeBeforeLogin() {
        return new ModelAndView("home-before");
    }

    @GetMapping("/home")
    public ModelAndView homeAfterLogin() {
        User authenticatedUser = userService.getAuthenticatedUser();
        authenticationService.setUserAuthentication(authenticatedUser.getUsername());

        return new ModelAndView("home-after");
    }
}
