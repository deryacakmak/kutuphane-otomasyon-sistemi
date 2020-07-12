package com.k.t.phane_otomasyon.kutuphane_otomasyonsistemi.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ödünçTeslim")
public class ÖdüçTeslim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean teslim_durumu;


    @Column
    private Date teslim_etme_tarihi;

    @Column
    private Date ödünç_alma_tarihi;

    @OneToOne
    private Kullanıcı ödünç_alan;

    @OneToOne
    private Kitap kitap;

    public ÖdüçTeslim(Long id, Boolean teslim_durumu, Date teslim_etme_tarihi, Date ödünç_alma_tarihi, Kullanıcı ödünç_alan, Kitap kitap) {
        this.id = id;
        this.teslim_durumu = teslim_durumu;
        this.teslim_etme_tarihi = teslim_etme_tarihi;
        this.ödünç_alma_tarihi = ödünç_alma_tarihi;
        this.ödünç_alan = ödünç_alan;
        this.kitap = kitap;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getTeslim_durumu() {
        return teslim_durumu;
    }

    public void setTeslim_durumu(Boolean teslim_durumu) {
        this.teslim_durumu = teslim_durumu;
    }

    public Date getTeslim_etme_tarihi() {
        return teslim_etme_tarihi;
    }

    public void setTeslim_etme_tarihi(Date teslim_etme_tarihi) {
        this.teslim_etme_tarihi = teslim_etme_tarihi;
    }

    public Date getÖdünç_alma_tarihi() {
        return ödünç_alma_tarihi;
    }

    public void setÖdünç_alma_tarihi(Date ödünç_alma_tarihi) {
        this.ödünç_alma_tarihi = ödünç_alma_tarihi;
    }

    public Kullanıcı getÖdünç_alan() {
        return ödünç_alan;
    }

    public void setÖdünç_alan(Kullanıcı ödünç_alan) {
        this.ödünç_alan = ödünç_alan;
    }

    public Kitap getKitap() {
        return kitap;
    }

    public void setKitap(Kitap kitap) {
        this.kitap = kitap;
    }
}
