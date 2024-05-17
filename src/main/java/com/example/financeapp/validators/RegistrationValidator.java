package com.example.financeapp.validators;

import com.example.financeapp.forms.RegistrationForm;
import com.example.financeapp.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RegistrationValidator implements Validator {
    private final UserService userService;
    private final Logger logger = Logger.getLogger(RegistrationValidator.class.getName());
    private static final String PHONE_PATTERN = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";

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

        if (form.getUsername().isEmpty() || form.getPhone().isEmpty() || form.getPassword().isEmpty()) {
            logger.log(Level.WARNING, "Поля не могут быть пустыми!");

            errors.rejectValue(
                    "username",
                    "error.registration.username.empty"
            );
        }

        if (form.getUsername().length() < 3 || form.getUsername().length() > 20) {
            logger.log(Level.WARNING, "Некорректная длинна имени!");

            errors.rejectValue(
                    "username",
                    "error.registration.username."
            );
        }

        if (userService.findByUsername(form.getUsername()).isPresent()) {
            logger.log(Level.WARNING, "Такой пользователь уже существует!");

            errors.rejectValue(
                    "username",
                    "error.registration.username.exist"
            );
        }
    }
}
