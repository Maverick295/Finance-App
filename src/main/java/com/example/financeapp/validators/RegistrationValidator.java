package com.example.financeapp.validators;

import com.example.financeapp.forms.RegistrationForm;
import com.example.financeapp.services.user.UserService;
import com.example.financeapp.utils.RegistrationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class RegistrationValidator implements Validator {
    private final UserService userService;

    @Autowired
    public RegistrationValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return RegistrationForm.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        RegistrationForm form = (RegistrationForm) target;

        if (form.getUsername().isEmpty() || form.getPassword().isEmpty()) {
            errors.rejectValue(
                    "username",
                    "error.registration.username.empty"
            );
        }

        if (form.getUsername().length() < 3 || form.getUsername().length() > 20) {
            errors.rejectValue(
                    "username",
                    "error.registration.username.length"
            );
        }

        if (RegistrationUtil.validatePhone(form.getPhone())) {
            errors.rejectValue(
                    "phone",
                    "error.registration.phone.pattern"
            );
        }

        if (userService.findByUsername(form.getUsername()).isPresent()) {
            errors.rejectValue(
                    "username",
                    "error.registration.username.exist"
            );
        }
    }
}
