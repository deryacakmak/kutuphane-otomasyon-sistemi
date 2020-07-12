package com.k.t.phane_otomasyon.kutuphane_otomasyonsistemi.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "yayınEviKitap")
public class YayınEviKitap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Kitap kitap;

    @OneToOne
    private YayınEvi yayınEvi;

    public YayınEviKitap(Long id, Kitap kitap_id, YayınEvi yayınEviId) {
        this.id = id;
        this.kitap = kitap_id;
        this.yayınEvi = yayınEviId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kitap getKitap_id() {
        return kitap;
    }

    public void setKitap_id(Kitap kitap_id) {
        this.kitap = kitap_id;
    }

    public YayınEvi getYayınEviId() {
        return yayınEvi;
    }

    public void setYayınEviId(YayınEvi yayınEviId) {
        this.yayınEvi= yayınEviId;
    }
}
