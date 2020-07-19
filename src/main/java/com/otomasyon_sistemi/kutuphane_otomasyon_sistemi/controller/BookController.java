package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.controller;


import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.DenemeDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.UpdateBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Announcement;
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

import java.util.List;
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

    @DeleteMapping("/delete/{ids}")
    public ResponseEntity<Response> deleteBook(@PathVariable ("ids") List<Long> ids){
            List<Book> books = bookServices.getBookDelete(ids);
            for(Book book: books){
                bookRepository.delete(book);
            }
        Response response = new Response("Book deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Response> updateBook(@RequestBody UpdateBookDto updateBookDto){
        bookServices.updatedBook(updateBookDto);
        Response response = new Response("Book updated successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllAnnouncements(DenemeDto denemeDto) {
        System.out.println(denemeDto.getAuthor()+denemeDto.getAuthor());
        List<Book> announcements = bookRepository.findAllByAuthorOrName(null,"Kaşağı33");
        return new ResponseEntity<>(announcements,HttpStatus.OK);
    }

}
