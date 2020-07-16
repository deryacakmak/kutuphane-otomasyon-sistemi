package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto;

import java.util.Date;

public class AddBookDto {
    private String name;
    private String author;
    private String location;
    private Long isbn;
    private Long stock;
    private Date publicationDate;
    private String category;
    private String publisher;

    public AddBookDto(){}

    public AddBookDto(String name, String author, String location, Long ISBN, Long stock, Date publicationDate, String category, String publisher) {
        this.name = name;
        this.author = author;
        this.location = location;
        this.isbn = ISBN;
        this.stock = stock;
        this.publicationDate = publicationDate;
        this.category = category;
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getISBN() {
        return isbn;
    }

    public void setISBN(Long ISBN) {
        this.isbn = ISBN;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}
