package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.controller;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddAnnouncementDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.exception.BadRequestException;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Announcement;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.AnnouncementRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.response.Response;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services.AnnouncementServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
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


    @DeleteMapping("/delete/{ids}")
    public ResponseEntity<Response> deleteAnnouncement(@PathVariable ("ids") List<Long> ids) {
            List<Announcement> announcementList = announcementServices.getDeleteAnnouncement(ids);
            for(Announcement announcement :announcementList) {
                announcementRepository.delete(announcement);
            }
            Response response = new Response("Announcement deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    @GetMapping("/all")
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        List<Announcement> announcements = announcementRepository.findAllByOrderByPublishingDateDesc();
        return new ResponseEntity<>(announcements,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Announcement> getAllAnnouncements(@PathVariable String id) {
        Long idLong = Long.parseLong(id);
        Optional<Announcement> announcement = announcementRepository.findById(idLong);
        if(announcement.isPresent()) {
            return new ResponseEntity<>(announcement.get(), HttpStatus.OK);
        }else{
            throw new BadRequestException("There is no announcement with this id");
        }
    }


}


