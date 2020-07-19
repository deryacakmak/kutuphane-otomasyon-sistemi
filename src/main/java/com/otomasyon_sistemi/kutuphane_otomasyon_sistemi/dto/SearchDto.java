package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto;

import java.util.Date;

public class SearchDto {
    private String name;
    private String author;
    private String isbn;
    private Date publicationDate;
    private String category;
    private String publisher;

    public SearchDto(){}

    public SearchDto(String name, String author, String isbn, Date publicationDate, String category, String publisher) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.category = category;
        this.publisher = publisher;
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

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}



