package com.example.financeapp.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String phone;
    private String apiToken;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private boolean enable;

    @Column(nullable = false)
    private boolean locked;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getApiToken() {
        return apiToken;
    }

    public User setApiToken(String apiToken) {
        this.apiToken = apiToken;
        return this;
    }

    public String getRole() {
        return role;
    }

    public User setRole(String role) {
        this.role = role;
        return this;
    }

    public boolean isEnable() {
        return enable;
    }

    public User setEnable(boolean enable) {
        this.enable = enable;
        return this;
    }

    public boolean isLocked() {
        return locked;
    }

    public User setLocked(boolean locked) {
        this.locked = locked;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return enable == user.enable &&
                locked == user.locked &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(apiToken, user.apiToken) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                username,
                password,
                phone,
                apiToken,
                role,
                enable,
                locked
        );
    }
}
