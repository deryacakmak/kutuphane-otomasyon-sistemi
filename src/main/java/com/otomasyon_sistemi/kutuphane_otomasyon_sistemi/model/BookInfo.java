package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book_info")
public class BookInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long ISBN;

    @Column
    private Long stock;

    @Column
    private Long borrowingNumber;

    @Column
    private Date publicationDate;

    @OneToOne
    private Book book;

    @OneToOne
    private Publisher publisher;

    public BookInfo() {}


    public BookInfo(Long id, Long ISBN, Long stock, Long borrowingNumber, Date publicationDate, Book book, Publisher publisher) {
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

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
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

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
