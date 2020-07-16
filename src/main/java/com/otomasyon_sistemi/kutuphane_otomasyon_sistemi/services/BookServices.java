package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Book;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BookInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Publisher;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.User;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.BookInfoRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.BookRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServices {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookInfoRepository bookInfoRepository;

    public BookInfo bookAdd(AddBookDto addBookDto){
        Book book = new Book();
        book.setAuthor(addBookDto.getAuthor());
        book.setCategory(addBookDto.getCategory());
        book.setName(addBookDto.getName());
        book.setLocation(addBookDto.getLocation());
        bookRepository.saveAndFlush(book);
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBorrowingNumber(addBookDto.getStock());
        bookInfo.setStock(addBookDto.getStock());
        bookInfo.setISBN(addBookDto.getISBN());
        bookInfo.setPublicationDate(addBookDto.getPublicationDate());
        Optional<Publisher> publisher = publisherRepository.findByName(addBookDto.getPublisher());
        if(publisher.isPresent()){
                bookInfo.setBook(book);
                return bookInfo;
        }
        else{
            return null;
        }
    }
}
