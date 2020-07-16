package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.controller;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddAnnouncementDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.DeleteAnnouncementDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.exception.BadRequestException;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Announcement;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.User;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.AnnouncementRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.UserRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.response.Response;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services.AnnouncementServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    @Autowired
    AnnouncementServices announcementServices;
    @Autowired
    private AnnouncementRepository announcementRepository;


    @PostMapping("/add")
    public ResponseEntity<Response> addAnnouncement(@RequestBody AddAnnouncementDto addAnnouncementDto) {
        Announcement announcement = announcementServices.getAddAnnouncement(addAnnouncementDto);
        if(announcement != null){
            announcementRepository.save(announcement);
            Response response = new Response("Announcement added successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        else{
            throw new BadRequestException("There is no user with this id");
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteAnnouncement(@RequestBody DeleteAnnouncementDto deleteAnnouncementDto) {
        Optional<Announcement> announcement = announcementServices.getDeleteAnnouncement(deleteAnnouncementDto);
        if(announcement != null){
            announcementRepository.delete(announcement.get());
            Response response = new Response("Announcement deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            throw new BadRequestException("There is no announcement with this id");
        }
    }
}

