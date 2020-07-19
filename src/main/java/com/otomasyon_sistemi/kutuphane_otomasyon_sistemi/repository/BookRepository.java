package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long>, PagingAndSortingRepository<Book, Long> {

    void delete(Book book);
    List<Book> findAllByAuthorOrName(String author , String  name);
   /* List<Book> findAllByName(String name , Pageable pageable);
    List<Book> findAllByCategory(String category , Pageable pageable);*/



}
