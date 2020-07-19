package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BorrowingInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Book book;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date borrowingDate;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;

    @Column
    private boolean returnSituation;

    public BorrowingInfo(Long id, User user, Book book, Date borrowingDate, Date returnDate, boolean returnSituation) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.returnSituation = returnSituation;
    }

    public BorrowingInfo() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(Date borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturnSituation() {
        return returnSituation;
    }

    public void setReturnSituation(boolean returnSituation) {
        this.returnSituation = returnSituation;
    }
}
