package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi;


import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.controller.AuthController;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.SignUpRequest;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.EnumRole;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Role;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.User;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.UserInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.RoleRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.UserInfoRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class SignUpTest {


    @Autowired
    private AuthController authController;

    @Autowired
     private UserRepository userRepository;

    @Test
    public void testSignUp() {

        SignUpRequest signUpRequest = new SignUpRequest();

        signUpRequest.setEmail("example@gmail.com");
        signUpRequest.setFirstName("Derya");
        signUpRequest.setLastName("Cakmak");
        signUpRequest.setPassword("123456");
        signUpRequest.setUserIdentifier((long)1234567);
        Set<String> roles = new HashSet<>();
        roles.add("member");
        signUpRequest.setRole(roles);
        authController.registerUser(signUpRequest);

        assertEquals(userRepository.existsByEmail("example@gmail.com"),true); ;

    }



}




