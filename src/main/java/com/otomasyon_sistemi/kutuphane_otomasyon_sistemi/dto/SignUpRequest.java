package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

public class SignUpRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String LastName;

    @NotBlank
    private Long userIdentifier;

    private Set<String> role;

    public SignUpRequest(){}

    public SignUpRequest(@NotBlank String firstName, @NotBlank @Email String email, @NotBlank String password, @NotBlank String lastName, @NotBlank Long userIdentifier, Set<String> role) {
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.LastName = lastName;
        this.userIdentifier = userIdentifier;
        this.role = role;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public Long getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(Long userIdentifier) {
        this.userIdentifier = userIdentifier;
    }
}