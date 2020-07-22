package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto;

import java.util.Date;

public class SearchBookDto {
    private String name;
    private String author;
    private String isbn;

    public SearchBookDto(){}

    public SearchBookDto(String name, String author, String isbn) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}



