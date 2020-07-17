package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Book;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookInfoRepository extends JpaRepository<BookInfo, Long> {


    Optional<BookInfo> findByBookId(Long bookId);

    List<BookInfo> findByISBN(Long ISBN);



}
