package com.k.t.phane_otomasyon.kutuphane_otomasyonsistemi.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "kullanıcı")
public class Kullanıcı {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String isim_soyisim;

    @Column(nullable = false)
    private String email;

    private String password;

    @Column
    private String userType;

    @Column
    private Boolean yeni_kitap_alma_durumu;

    public Kullanıcı() {
    }

    public Kullanıcı(Long id, String isim_soyisim, String email, String password, String userType, Boolean yeni_kitap_alma_durumu) {
        this.id = id;
        this.isim_soyisim = isim_soyisim;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.yeni_kitap_alma_durumu = yeni_kitap_alma_durumu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsim_soyisim() {
        return isim_soyisim;
    }

    public void setIsim_soyisim(String isim_soyisim) {
        this.isim_soyisim = isim_soyisim;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Boolean getYeni_kitap_alma_durumu() {
        return yeni_kitap_alma_durumu;
    }

    public void setYeni_kitap_alma_durumu(Boolean yeni_kitap_alma_durumu) {
        this.yeni_kitap_alma_durumu = yeni_kitap_alma_durumu;
    }
}