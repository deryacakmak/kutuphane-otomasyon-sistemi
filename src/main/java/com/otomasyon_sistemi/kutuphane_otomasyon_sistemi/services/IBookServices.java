package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.SearchDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.UpdateBookDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Book;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BookInfo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IBookServices {

    BookInfo getBookAdd(AddBookDto addBookDto);
    List<Book> getBookDelete(List<Long> ids);
    void updatedBook(UpdateBookDto updateBookDto, Long id);
    Page<BookInfo> getSearchForBook(SearchDto searchDto, int page, int pageSize);
    Optional<BookInfo> getBook(Long id);
}
