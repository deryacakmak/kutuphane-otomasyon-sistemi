package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.controller;



import javax.validation.Valid;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.LoginRequest;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.SignUpRequest;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        return authService.signIn(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        return authService.signUp(signUpRequest);
    }
}