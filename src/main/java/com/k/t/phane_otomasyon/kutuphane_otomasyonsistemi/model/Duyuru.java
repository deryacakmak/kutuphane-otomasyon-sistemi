package com.k.t.phane_otomasyon.kutuphane_otomasyonsistemi.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "duyuru")
public class Duyuru {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String başlık;

    @Column
    private String içerik;

    @Column
    private Date yayınlama_tarihi;

    @OneToOne
    private Kullanıcı yayınlayan;

    public Duyuru(){}

    public Duyuru(Long id, String başlık, String içerik, Date yayınlama_tarihi, Kullanıcı yayınlayan) {
        this.id = id;
        this.başlık = başlık;
        this.içerik = içerik;
        this.yayınlama_tarihi = yayınlama_tarihi;
        this.yayınlayan = yayınlayan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaşlık() {
        return başlık;
    }

    public void setBaşlık(String başlık) {
        this.başlık = başlık;
    }

    public String getIçerik() {
        return içerik;
    }

    public void setIçerik(String içerik) {
        this.içerik = içerik;
    }

    public Date getYayınlama_tarihi() {
        return yayınlama_tarihi;
    }

    public void setYayınlama_tarihi(Date yayınlama_tarihi) {
        this.yayınlama_tarihi = yayınlama_tarihi;
    }

    public Kullanıcı getYayınlayan() {
        return yayınlayan;
    }

    public void setYayınlayan(Kullanıcı yayınlayan) {
        this.yayınlayan = yayınlayan;
    }
}
