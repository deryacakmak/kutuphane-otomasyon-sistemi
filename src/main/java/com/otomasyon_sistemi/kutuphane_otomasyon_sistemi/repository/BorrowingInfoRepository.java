package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BorrowingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingInfoRepository extends JpaRepository<BorrowingInfo, Long> {
}
