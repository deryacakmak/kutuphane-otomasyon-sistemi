package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book_info")
public class BookInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ISBN;

    @Column
    private Long stock;

    @Column
    private Long borrowingNumber;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publicationDate;

    @OneToOne
    private Book book;

    @Column
    private String publisher;

    public BookInfo() {}

    public BookInfo(Long id, String ISBN, Long stock, Long borrowingNumber, Date publicationDate, Book book, String publisher) {
        this.id = id;
        this.ISBN = ISBN;
        this.stock = stock;
        this.borrowingNumber = borrowingNumber;
        this.publicationDate = publicationDate;
        this.book = book;
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getBorrowingNumber() {
        return borrowingNumber;
    }

    public void setBorrowingNumber(Long borrowingNumber) {
        this.borrowingNumber = borrowingNumber;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
