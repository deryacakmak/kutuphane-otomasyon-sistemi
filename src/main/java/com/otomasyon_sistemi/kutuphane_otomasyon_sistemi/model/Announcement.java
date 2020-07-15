package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String context;

    @Column
    private Date publishingDate;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id")
    private User publisher;

    public Announcement(String title, String context, Date publishingDate) {

        this.title = title;
        this.context = context;
        this.publishingDate = publishingDate;
    }
    public Announcement(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }
}
