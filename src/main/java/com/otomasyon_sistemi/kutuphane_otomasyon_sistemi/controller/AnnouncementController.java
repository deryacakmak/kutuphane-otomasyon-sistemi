package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.controller;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddAnnouncementDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.exception.BadRequestException;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Announcement;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.response.Response;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services.IAnnouncementServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/announcement")
public class AnnouncementController {

    @Autowired
    IAnnouncementServices announcementServices;


    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('OFFICER')")
    public ResponseEntity<Response> addAnnouncement(@RequestBody AddAnnouncementDto addAnnouncementDto) {
        Announcement announcement = announcementServices.getAddAnnouncement(addAnnouncementDto);
        if(announcement != null){
            Response response = new Response("Announcement added successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        else{
            throw new BadRequestException("There is no user with this id");
        }
    }


    @DeleteMapping("/delete/{ids}")
    @PreAuthorize("hasRole('USER') or hasRole('OFFICER')")
    public ResponseEntity<Response> deleteAnnouncement(@PathVariable ("ids") List<Long> ids) {
            List<Announcement> announcementList = announcementServices.getDeleteAnnouncement(ids);
            if(!(announcementList.isEmpty())){
                Response response = new Response("Announcement deleted successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else{
                throw new BadRequestException("There are no announcements with these ids");
            }

        }

    @GetMapping("/all/{pageSize}/{page}")
    public ResponseEntity<Page<Announcement>> getAllAnnouncements(@PathVariable (name = "pageSize") int pageSize,@PathVariable (name = "page") int page) {
        Page<Announcement> announcements = announcementServices.getAllAnnouncement(page, pageSize);
        return new ResponseEntity<>(announcements,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Announcement> getAnnouncement(@PathVariable Long id) {
        Optional<Announcement> announcement = announcementServices.getAnnouncement(id);
        if(announcement.isPresent()) {
            return new ResponseEntity<>(announcement.get(), HttpStatus.OK);
        }else{
            throw new BadRequestException("There is no announcement with this id");
        }
    }


}


