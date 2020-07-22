package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.User;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface UserInfoRepository extends JpaRepository<UserInfo, Long>, PagingAndSortingRepository<UserInfo,Long> {

    Page<UserInfo> findByUserFirstNameAndUserLastName(String firstName, String lastName ,Pageable pageable);

    Page<UserInfo> findByUserUserIdentifier(Long id,Pageable pageable );
    Optional<UserInfo> findByUserUserIdentifier(Long id);
    Optional<UserInfo> findByUserId(Long userId);
}
