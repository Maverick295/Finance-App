package com.example.financeapp.patterns;

import java.util.regex.Pattern;

public class Patterns {
    public static String PHONE = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    public static Pattern PHONE_PATTERN = Pattern.compile(PHONE);
}
