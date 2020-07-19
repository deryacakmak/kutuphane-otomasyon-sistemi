package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.controller;


import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.SearchDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.UpdateBookDto;
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

    @PostMapping("/search/{page}")
    public ResponseEntity<List<BookInfo>> getAllBooks(@PathVariable ("page") int page, @RequestBody SearchDto searchDto){
        List<BookInfo> books = bookServices.getSearchedBook(searchDto,page);
        if(!(books.isEmpty())){
            return new ResponseEntity<>(books, HttpStatus.OK);
        }else{
            throw new BadRequestException("No book is found");
    }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookInfo> getBook(@PathVariable Long id) {
        Optional<BookInfo> book = bookInfoRepository.findByBookId(id);
        if(book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        }else{
            throw new BadRequestException("There is no book with this id");
        }
    }

}
