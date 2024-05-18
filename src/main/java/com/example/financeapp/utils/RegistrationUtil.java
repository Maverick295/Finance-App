package com.example.financeapp.utils;

import com.example.financeapp.patterns.Patterns;
import io.micrometer.common.util.StringUtils;

public class RegistrationUtil {
    public static boolean validatePhone(String phone) {
        if (StringUtils.isBlank(phone)) {
            return false;
        }

        if (!Patterns.PHONE_PATTERN.matcher(phone).matches()) {
            return false;
        }

        return true;
    }
}
