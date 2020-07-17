package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.DeleteBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.IncreaseStockDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.*;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.BookInfoRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.BookRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServices {



    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookInfoRepository bookInfoRepository;



    public BookInfo getBookAdd(AddBookDto addBookDto){
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
        bookInfo.setPublisher(addBookDto.getPublisher());
        bookInfo.setPublicationDate(addBookDto.getPublicationDate());
        bookInfo.setBook(book);
        return bookInfo;
    }

    public Book getBookDelete(DeleteBookDto deleteBookDto) {
        List<BookInfo> books = bookInfoRepository.findByISBN(deleteBookDto.getIsbn());
        if(!(books.isEmpty())){
            for(BookInfo book: books){
                if(book.getPublicationDate().getYear() == (deleteBookDto.getPublicationDate().getYear())){
                    Book deletedBook = book.getBook();
                    System.out.println(book.getBook().getId());
                    bookInfoRepository.delete(book);
                    return deletedBook;
                }
            }
        }
        return null;

    }

    public void increaseBookStock(IncreaseStockDto increaseStockDto) {
        List<BookInfo> books = bookInfoRepository.findByISBN(increaseStockDto.getIsbn());
        if (!(books.isEmpty())) {
            for (BookInfo book : books) {
                if (book.getPublicationDate().getYear() == (increaseStockDto.getPublicationYear().getYear())) {
                        book.setStock(book.getStock()+increaseStockDto.getStockNumber());
                        bookInfoRepository.save(book);

                        return;
                }
            }

        }
    }

}
