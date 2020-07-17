package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.controller;


import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.DeleteAnnouncementDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.DeleteBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.IncreaseStockDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.exception.BadRequestException;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Book;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BookInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.BookInfoRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.BookRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.response.Response;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookServices bookServices;

    @Autowired
    BookInfoRepository bookInfoRepository;

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/add")
    public ResponseEntity<Response> addBook(@RequestBody AddBookDto addBookDto) {
        BookInfo bookInfo = bookServices.getBookAdd(addBookDto);
        bookInfoRepository.save(bookInfo);
        Response response = new Response("Book added successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteBook(@RequestBody DeleteBookDto deleteBookDto){
        Book book = bookServices.getBookDelete(deleteBookDto);
        if(book!= null){
            bookRepository.delete(book);
            Response response = new Response("Book deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            throw new BadRequestException("There is no book with this id");
        }
    }

    @PostMapping("/increaseStock")
    public ResponseEntity<Response> increaseStockNumber(@RequestBody IncreaseStockDto increaseStockDto){
        bookServices.increaseBookStock(increaseStockDto);
        Response response = new Response("Book deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
