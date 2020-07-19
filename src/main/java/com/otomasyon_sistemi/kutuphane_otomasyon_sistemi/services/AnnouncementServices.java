package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddAnnouncementDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Announcement;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.User;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.AnnouncementRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public List<Announcement> getDeleteAnnouncement(List<Long> ids) {
        List<Announcement> announcementList = new ArrayList<>();
        for(Long id: ids){
            announcementList.add(announcementRepository.findById(id).get());
        }
        return announcementList;
    }

    public List<Announcement> getAnnouncement(int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Announcement> announcements = announcementRepository.findAllByOrderByPublishingDateDesc(pageable);
        return announcements.toList();
    }
}
