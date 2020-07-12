package com.k.t.phane_otomasyon.kutuphane_otomasyonsistemi.model;

import javax.persistence.*;

@Entity
@Table(name = "kitap")
public class Kitap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ISBN;

    @Column
    private String ad;

    @Column
    private String yazar;


    @Column
    private String kategori;


    @Column
    private String çevirmen;


    @Column
    private Long stok;


    @Column
    private Long basım_yılı;

    @Column
    private String raf;

    public Kitap() {}

    public Kitap(Long ISBN, String ad, String yazar, String kategori, String çevirmen, Long stok, Long basım_yılı, String raf) {
        this.ISBN = ISBN;
        this.ad = ad;
        this.yazar = yazar;
        this.kategori = kategori;
        this.çevirmen = çevirmen;
        this.stok = stok;
        this.basım_yılı = basım_yılı;
        this.raf = raf;
    }

    public Long getId() {
        return ISBN;
    }

    public void setId(Long ISBN) {
        this.ISBN = ISBN;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getYazar() {
        return yazar;
    }

    public void setYazar(String yazar) {
        this.yazar = yazar;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getÇevirmen() {
        return çevirmen;
    }

    public void setÇevirmen(String çevirmen) {
        this.çevirmen = çevirmen;
    }

    public Long getStok() {
        return stok;
    }

    public void setStok(Long stok) {
        this.stok = stok;
    }

    public Long getBasım_yılı() {
        return basım_yılı;
    }

    public void setBasım_yılı(Long basım_yılı) {
        this.basım_yılı = basım_yılı;
    }

    public String getRaf() {
        return raf;
    }

    public void setRaf(String raf) {
        this.raf = raf;
    }
}

