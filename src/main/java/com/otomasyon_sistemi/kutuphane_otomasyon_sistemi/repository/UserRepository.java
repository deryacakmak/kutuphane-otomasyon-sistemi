package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
