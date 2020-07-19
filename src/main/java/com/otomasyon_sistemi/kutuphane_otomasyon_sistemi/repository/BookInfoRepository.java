package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Book;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;



public interface BookInfoRepository extends JpaRepository<BookInfo, Long>, PagingAndSortingRepository<BookInfo,Long> {


    Optional<BookInfo> findByBookId(Long bookId);
    /*List<BookInfo> findAllByISBN(String ISBN , Pageable pageable);
    List<BookInfo> findAllByPublicationDate(Date publicationDate , Pageable pageable);
    List<BookInfo> findAllByPublisher(String publisher , Pageable pageable);*/




}
