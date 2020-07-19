package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;



import java.util.Optional;


public interface AnnouncementRepository extends JpaRepository<Announcement, Long>, PagingAndSortingRepository<Announcement, Long> {

    Optional<Announcement> findById(Long id);

    Page<Announcement> findAllByOrderByPublishingDateDesc(Pageable pageable);

}
