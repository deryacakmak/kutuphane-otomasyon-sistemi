package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.SearchBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.UpdateBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.*;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.BookInfoRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServices implements IBookServices {



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
        bookInfoRepository.save(bookInfo);
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
            for(Book book: bookList){
                bookRepository.delete(book);
            }
        }
        return bookList;

    }

    public  void updatedBook(UpdateBookDto updateBookDto, Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            book.get().setAuthor(updateBookDto.getAuthor());
            book.get().setLocation(updateBookDto.getLocation());
            book.get().setCategory(updateBookDto.getCategory());
            book.get().setName(updateBookDto.getName());
            updateBookInfo(updateBookDto,id);
            bookRepository.save(book.get());
        }

    }

    private void updateBookInfo(UpdateBookDto updateBookDto, Long id){
        Optional<BookInfo> bookInfo = bookInfoRepository.findByBookId(id);
        if(bookInfo.isPresent()) {
            bookInfo.get().setStock(updateBookDto.getStock());
            bookInfo.get().setISBN(updateBookDto.getIsbn());
            bookInfo.get().setPublisher(updateBookDto.getPublisher());
            bookInfo.get().setPublicationDate(updateBookDto.getPublicationDate());
            bookInfo.get().setBorrowingNumber(updateBookDto.getStock());
            Optional<Book> book = bookRepository.findById(id);
            bookInfo.get().setBook(book.get());
            bookInfoRepository.save(bookInfo.get());
        }
    }

    public Page<BookInfo> getSearchForBook(SearchBookDto searchBookDto, int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
       if(searchBookDto.getIsbn() != null){
           return bookInfoRepository.findAllByISBN(searchBookDto.getIsbn(),pageable);
       }
       else if(searchBookDto.getAuthor() != null && searchBookDto.getName() != null){
           return bookInfoRepository.findAllByBookNameAndBookAuthor(searchBookDto.getName(), searchBookDto.getAuthor(),pageable);
       }
       else{
           return bookInfoRepository.findAllByBookNameOrBookAuthor(searchBookDto.getName(), searchBookDto.getAuthor(),pageable);
       }

    }

    public  Optional<BookInfo> getBook(Long id){
        return bookInfoRepository.findByBookId(id);
    }

    }


