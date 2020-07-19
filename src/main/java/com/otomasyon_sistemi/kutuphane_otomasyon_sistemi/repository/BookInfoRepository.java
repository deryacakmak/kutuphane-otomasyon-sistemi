package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository;



import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BookInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Optional;
import org.springframework.data.domain.Pageable;


public interface BookInfoRepository extends JpaRepository<BookInfo, Long>, PagingAndSortingRepository<BookInfo,Long> {


    Optional<BookInfo> findByBookId(Long bookId);
    Page<BookInfo> findAllByISBN(String isbn,Pageable pageable);
    Page<BookInfo> findAllByBookNameAndBookAuthor(String name, String author, Pageable pageable);
    Page<BookInfo> findAllByBookNameOrBookAuthor(String name, String author, Pageable pageable);




}
