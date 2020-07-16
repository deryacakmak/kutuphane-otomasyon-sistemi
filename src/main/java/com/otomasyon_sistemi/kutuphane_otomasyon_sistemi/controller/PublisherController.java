package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.controller;


import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddPublisherDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.DeleteAnnouncementDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.DeletePublisherDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.exception.BadRequestException;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Announcement;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Publisher;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.PublisherRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.response.Response;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services.PublisherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    PublisherServices publisherServices;
    @Autowired
    private PublisherRepository publisherRepository;

    @PostMapping("/add")
    public ResponseEntity<Response> addPublisher(@RequestBody AddPublisherDto publisherDto) {
        Publisher publisher = publisherServices.getAddPublisher(publisherDto);
        publisherRepository.save(publisher);
        Response response = new Response("Publisher is added successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> deletePublisher(@RequestBody DeletePublisherDto deletePublisherDto) {
        Optional<Publisher> publisher = publisherServices.getDeletePublisher(deletePublisherDto);
        publisherRepository.delete(publisher.get());
        Response response = new Response("Publisher deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    }


