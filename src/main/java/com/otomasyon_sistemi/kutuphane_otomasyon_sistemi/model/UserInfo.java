package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model;


import javax.persistence.*;

@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @Column
    private Long phoneNumber;

    @Column
    private boolean suspendedSituation;

    @Column
    private boolean bookBorrowingSituation;

    public UserInfo() {}

    public UserInfo(Long id, User user, Long phoneNumber, boolean suspendedSituation, boolean bookBorrowingSituation) {
        this.id = id;
        this.user = user;
        this.phoneNumber = phoneNumber;
        this.suspendedSituation = suspendedSituation;
        this.bookBorrowingSituation = bookBorrowingSituation;
    }


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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isSuspendedSituation() {
        return suspendedSituation;
    }

    public void setSuspendedSituation(boolean suspendedSituation) {
        this.suspendedSituation = suspendedSituation;
    }

    public boolean bookBorrowingSituation() {
        return bookBorrowingSituation;
    }

    public void bookBorrowingSituation(boolean bookBarowingSituation) {
        this.bookBorrowingSituation = bookBarowingSituation;
    }
}
