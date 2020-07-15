package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
