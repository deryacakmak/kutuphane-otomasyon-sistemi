package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

     Optional<User> findById(Long id);

     Boolean existsByEmail(String email);

     Optional<User> findByEmail(String email);


}
