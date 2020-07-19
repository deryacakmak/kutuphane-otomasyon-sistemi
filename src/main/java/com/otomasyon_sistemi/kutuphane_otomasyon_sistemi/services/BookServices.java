package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.UpdateBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.*;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.BookInfoRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServices {



    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookInfoRepository bookInfoRepository;



    public BookInfo getBookAdd(AddBookDto addBookDto){
        Book book = saveBook(addBookDto);
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBorrowingNumber(addBookDto.getStock());
        bookInfo.setStock(addBookDto.getStock());
        bookInfo.setISBN(addBookDto.getISBN());
        bookInfo.setPublisher(addBookDto.getPublisher());
        bookInfo.setPublicationDate(addBookDto.getPublicationDate());
        bookInfo.setBook(book);
        return bookInfo;
    }

    private Book saveBook(AddBookDto addBookDto){
        Book book = new Book();
        book.setAuthor(addBookDto.getAuthor());
        book.setCategory(addBookDto.getCategory());
        book.setName(addBookDto.getName());
        book.setLocation(addBookDto.getLocation());
        bookRepository.saveAndFlush(book);
        return book;

    }

    public List<Book> getBookDelete(List<Long> ids) {
        List<Book> bookList = new ArrayList<>();
        for(Long id: ids){
            Optional<BookInfo> bookInfo = bookInfoRepository.findByBookId(id);
            if(bookInfo.isPresent()) {
                bookInfoRepository.delete(bookInfo.get());
                Optional<Book> book = bookRepository.findById(id);
                bookList.add(book.get());
            }
        }
        return bookList;

    }

    public  void updatedBook(UpdateBookDto updateBookDto){
        Optional<Book> book = bookRepository.findById(updateBookDto.getId());
        if(book.isPresent()){
            book.get().setAuthor(updateBookDto.getAuthor());
            book.get().setLocation(updateBookDto.getLocation());
            book.get().setCategory(updateBookDto.getCategory());
            book.get().setName(updateBookDto.getName());
            updateBookInfo(updateBookDto);
            bookRepository.save(book.get());
        }

    }

    private void updateBookInfo(UpdateBookDto updateBookDto){
        Optional<BookInfo> bookInfo = bookInfoRepository.findByBookId(updateBookDto.getId());
        if(bookInfo.isPresent()) {
            bookInfo.get().setStock(updateBookDto.getStock());
            bookInfo.get().setISBN(updateBookDto.getIsbn());
            bookInfo.get().setPublisher(updateBookDto.getPublisher());
            bookInfo.get().setPublicationDate(updateBookDto.getPublicationDate());
            bookInfo.get().setBorrowingNumber(updateBookDto.getStock());
            Optional<Book> book = bookRepository.findById(updateBookDto.getId());
            bookInfo.get().setBook(book.get());
            bookInfoRepository.save(bookInfo.get());
        }
    }

    }


