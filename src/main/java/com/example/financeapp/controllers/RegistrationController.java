package com.example.financeapp.controllers;

import com.example.financeapp.entities.User;
import com.example.financeapp.forms.RegistrationForm;
import com.example.financeapp.services.user.UserService;
import com.example.financeapp.utils.RedirectUtil;
import com.example.financeapp.validators.RegistrationValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationValidator validator;
    private final UserService userService;

    @Autowired
    public RegistrationController(RegistrationValidator validator, UserService userService) {
        this.validator = validator;
        this.userService = userService;
    }

    @InitBinder("registrationForm")
    public void setRegistrationForm(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping
    public ModelAndView registrationForm() {
        return new ModelAndView("registration")
                .addObject("registrationForm", new RegistrationForm());
    }

    @PostMapping
    public ModelAndView registrationAction(
            @ModelAttribute @Valid RegistrationForm form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return new ModelAndView("registration");
        }
        User authenticatedUser = userService.getAuthenticatedUser();
        userService.save(authenticatedUser);

        return RedirectUtil.redirect("/login");
    }
}
