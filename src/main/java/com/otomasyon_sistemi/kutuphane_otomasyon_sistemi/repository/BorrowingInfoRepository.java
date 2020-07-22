package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BorrowingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface BorrowingInfoRepository extends JpaRepository<BorrowingInfo, Long>, PagingAndSortingRepository<BorrowingInfo,Long> {


    Page<BorrowingInfo> findAllByUserIdOrderByBorrowingDateDesc(Long id,Pageable page);
    Page<BorrowingInfo> findAllByUserUserIdentifierOrderByBorrowingDateDesc(Long id,Pageable page);
    Optional<BorrowingInfo> findByBookInfoIdAndReturnSituation(Long id, Boolean situation);
}
