package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model;


import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;


    @Column(unique = true)
    private String email;

    @Column
    private String userType;

    @Column
    private String password;

    @Column
    private Long userIdentifier;

    public User() {}

    public User(Long id, String firstName, String lastName, String email, String userType, String password, Long userIdentifier) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
        this.password = password;
        this.userIdentifier = userIdentifier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(Long userIdentifier) {
        this.userIdentifier = userIdentifier;
    }
}