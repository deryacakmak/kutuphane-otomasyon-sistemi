package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.SearchBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.UpdateBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Book;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BookInfo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IBookServices {

    BookInfo bookAdd(AddBookDto addBookDto);
    List<Book> bookDelete(List<Long> ids);
    void updateBookInfo(UpdateBookDto updateBookDto, Long id);
    Page<BookInfo> searchForBook(SearchBookDto searchBookDto, int page, int pageSize);
    Optional<BookInfo> getBook(Long id);
}
