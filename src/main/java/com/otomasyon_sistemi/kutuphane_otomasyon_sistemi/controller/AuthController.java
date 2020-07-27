package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.LoginRequest;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.SignUpRequest;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.EnumRole;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Role;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.User;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.UserInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.RoleRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.UserInfoRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.UserRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.response.JwtResponse;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.response.MessageResponse;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.security.jwt.JwtUtils;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail()
                ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setUserIdentifier(signUpRequest.getUserIdentifier());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        EnumRole allRoles[] = EnumRole.values();

        for(String role: strRoles){
            for (EnumRole enumRole : allRoles){
                if(enumRole.label.equals(role)){
                    Role adminRole = roleRepository.findByName(enumRole).get();
                    roles.add(adminRole);
                    break;
                }
            }
        }

        user.setRole(roles);
        userRepository.save(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);
        userInfo.setBookBorrowingSituation(true);
        userInfo.setSuspendedSituation(false);
        userRepository.save(user);
        userInfoRepository.save(userInfo);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}