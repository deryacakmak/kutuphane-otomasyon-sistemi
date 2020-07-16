package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services;


import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddPublisherDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.DeletePublisherDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Publisher;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublisherServices {


    @Autowired
    private PublisherRepository publisherRepository;


    public Publisher getAddPublisher(AddPublisherDto publisherDto) {
        Publisher publisher = new Publisher();
        publisher.setName(publisherDto.getName());
        return publisher;
    }

    public  Optional<Publisher>  getDeletePublisher(DeletePublisherDto publisherDto){
        Optional<Publisher> publisher = publisherRepository.findById(publisherDto.getId());
        return publisher;
    }
}
