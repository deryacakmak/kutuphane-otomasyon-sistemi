package com.k.t.phane_otomasyon.kutuphane_otomasyonsistemi.repository;

import com.k.t.phane_otomasyon.kutuphane_otomasyonsistemi.model.Kitap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitapRepository extends JpaRepository<Kitap, Long> {
}
