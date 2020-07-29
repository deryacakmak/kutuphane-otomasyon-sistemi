package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi;


import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.SearchBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.UpdateBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Book;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BookInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.BookRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services.IBookServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class BookServiceTest {

    @Autowired
    private IBookServices iBookServices;

    @Autowired
    private BookRepository bookRepository;


    @Test
    public void testSaveBook(){
        AddBookDto book = new AddBookDto();
        book.setName("DenemeKitap6");
        book.setCategory("Roman");
        book.setLocation("2bx");
        book.setAuthor("DenemeYazar6");
        book.setIsbn("2-bnxv");
        book.setStock((long)50);
        book.setPublicationDate(new Date());
        book.setPublisher("Everest");
        iBookServices.bookAdd(book);

        List<Book> books3 = bookRepository.findAll();

        assertNotEquals(books3.size(),0);

        SearchBookDto searchBookDto = new SearchBookDto();
        searchBookDto.setAuthor("DenemeYazar6");
        searchBookDto.setName("DenemeKitap6");

        List<BookInfo> books = iBookServices.searchForBook(searchBookDto,0,1).toList();
        assertEquals(books.get(0).getBook().getAuthor(),"DenemeYazar6");

    }


    @Test
    public void testUpdateBook(){

        AddBookDto book = new AddBookDto();
        book.setName("DenemeKitap6");
        book.setCategory("Roman");
        book.setLocation("2bx");
        book.setAuthor("DenemeYazar6");
        book.setIsbn("2-bnxv");
        book.setStock((long)50);
        book.setPublicationDate(new Date());
        book.setPublisher("Everest");
        iBookServices.bookAdd(book);

        SearchBookDto searchBookDto = new SearchBookDto();
        searchBookDto.setAuthor("DenemeYazar6");
        searchBookDto.setName("DenemeKitap6");

        List<BookInfo> books = iBookServices.searchForBook(searchBookDto,0,1).toList();
        Long id = books.get(0).getBook().getId();

        UpdateBookDto updateBookDto = new UpdateBookDto();
        updateBookDto.setName("newBookName");
        updateBookDto.setCategory("Roman");
        updateBookDto.setLocation("2bx");
        updateBookDto.setAuthor("newBookAuthor");
        updateBookDto.setIsbn("2-bnxv");
        updateBookDto.setStock((long)55);
        updateBookDto.setPublicationDate(new Date());
        updateBookDto.setPublisher("YKM");


        iBookServices.updateBookInfo(updateBookDto, id);

        searchBookDto.setAuthor("newBookAuthor");
        searchBookDto.setName("newBookName");

        assertNotEquals(iBookServices.searchForBook(searchBookDto,0,1).toList(),0);

    }

    @Test
    public void testDeleteBook(){

        AddBookDto book = new AddBookDto();
        book.setName("DenemeKitap6");
        book.setCategory("Roman");
        book.setLocation("2bx");
        book.setAuthor("DenemeYazar6");
        book.setIsbn("2-bnxv");
        book.setStock((long)50);
        book.setPublicationDate(new Date());
        book.setPublisher("Everest");
        iBookServices.bookAdd(book);

        SearchBookDto searchBookDto = new SearchBookDto();
        searchBookDto.setAuthor("DenemeYazar6");
        searchBookDto.setName("DenemeKitap6");

        List<BookInfo> books = iBookServices.searchForBook(searchBookDto,0,1).toList();
        Long id = books.get(0).getBook().getId();
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        iBookServices.bookDelete(ids);

        assertEquals(iBookServices.searchForBook(searchBookDto,0,1).toList().size(),0);

    }

}
