package com.k.t.phane_otomasyon.kutuphane_otomasyonsistemi.model;

import javax.persistence.*;

@Entity
@Table(name = "yayınEvi")
public class YayınEvi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ad;

}
