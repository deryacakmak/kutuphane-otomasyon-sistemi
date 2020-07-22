package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.controller;


import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.SearchBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.UpdateBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.exception.BadRequestException;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Book;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BookInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.response.Response;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services.IBookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    IBookServices bookServices;


    @PostMapping("/add")
    public ResponseEntity<Response> addBook(@RequestBody AddBookDto addBookDto) {
        BookInfo bookInfo = bookServices.getBookAdd(addBookDto);
        Response response = new Response("Book added successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{ids}")
    public ResponseEntity<Response> deleteBook(@PathVariable ("ids") List<Long> ids){
        List<Book> books = bookServices.getBookDelete(ids);
        Response response = new Response("Book deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Response> updateBook(@PathVariable ("id") Long id, @RequestBody UpdateBookDto updateBookDto){
        bookServices.updatedBook(updateBookDto, id);
        Response response = new Response("Book updated successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/search/{pageSize}/{page}")
    public ResponseEntity<Page<BookInfo>> getAllSearchForBooks(@PathVariable ("pageSize") int pageSize,@PathVariable ("page") int page, @RequestBody SearchBookDto searchBookDto){
        Page<BookInfo> books = bookServices.getSearchForBook(searchBookDto,page, pageSize);
        if(!(books.isEmpty())){
            return new ResponseEntity<>(books, HttpStatus.OK);
        }else{
            throw new BadRequestException("No book is found");
    }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookInfo> getBook(@PathVariable Long id) {
        Optional<BookInfo> book = bookServices.getBook(id);
        if(book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        }else{
            throw new BadRequestException("There is no book with this id");
        }
    }

}
