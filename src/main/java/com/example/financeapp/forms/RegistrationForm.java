package com.example.financeapp.forms;

public class RegistrationForm {
    private String username;
    private String password;
    private String phone;
    public String getUsername() {
        return username;
    }

    public RegistrationForm setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegistrationForm setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public RegistrationForm setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
