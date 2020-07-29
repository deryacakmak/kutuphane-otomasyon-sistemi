package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddAnnouncementDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Announcement;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IAnnouncementServices {

    Announcement addAnnouncement(AddAnnouncementDto addAnnouncementDto);
    List<Announcement> deleteAnnouncement(List<Long> ids);
    Page<Announcement> getAllAnnouncement(int page, int pageSize);
    Optional<Announcement> getAnnouncement(Long id);
}
