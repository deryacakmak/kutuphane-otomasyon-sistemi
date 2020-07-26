package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.controller.AuthController;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.EnumRole;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Role;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.User;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.UserInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.BookInfoRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.RoleRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.UserInfoRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.UserRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.response.MessageResponse;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
public class SignUpTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    UserInfoRepository userInfoRepository;

    public void testSignUp(){

        // Create new user's account
        User user = new User();
        user.setFirstName("derya");
        user.setLastName("cakmak");
        user.setUserIdentifier((long)1234);
        user.setPassword(encoder.encode("12345"));
        user.setEmail("deryacakmak6262@gmail.com");
        Set<String> strRoles = new HashSet<>();
        strRoles.add("member");
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


        assertTrue(userRepository.existsByEmail("deryacakmak6262@gmail.com"));
    }
    }






