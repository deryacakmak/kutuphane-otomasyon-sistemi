package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddAnnouncementDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.DeleteAnnouncementDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.exception.BadRequestException;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Announcement;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.User;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.AnnouncementRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.UserRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.Optional;

@Service
public class AnnouncementServices {

    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private UserRepository userRepository;


    public Announcement getAddAnnouncement(AddAnnouncementDto addAnnouncementDto) {
        Announcement announcement = new Announcement();
        announcement.setContent(addAnnouncementDto.getContent());
        announcement.setTitle(addAnnouncementDto.getTitle());
        announcement.setPublishingDate(new Date());
        Optional<User> publisher = userRepository.findById(addAnnouncementDto.getPublisherId());
        if (publisher.isPresent()) {
            announcement.setPublisher(publisher.get());
            return announcement;

        } else {
            return null;
        }
    }


    public Optional<Announcement> getDeleteAnnouncement(DeleteAnnouncementDto deleteAnnouncementDto) {
        Optional<Announcement> announcement = announcementRepository.findById(deleteAnnouncementDto.getId());
        if (announcement.isPresent()) {
            return announcement;
        } else {
            return null;

        }
    }
}
