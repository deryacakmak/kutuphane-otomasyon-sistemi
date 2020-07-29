package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddAnnouncementDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Announcement;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.User;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.AnnouncementRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnnouncementServices implements IAnnouncementServices {

    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private UserRepository userRepository;


    public Announcement addAnnouncement(AddAnnouncementDto addAnnouncementDto) {
        Announcement announcement = new Announcement();
        announcement.setContent(addAnnouncementDto.getContent());
        announcement.setTitle(addAnnouncementDto.getTitle());
        announcement.setPublishingDate(new Date());
        Optional<User> publisher = userRepository.findById(addAnnouncementDto.getPublisherId());
        if (publisher.isPresent()) {
            announcement.setPublisher(publisher.get());
            announcementRepository.save(announcement);
            return announcement;

        } else {
            return null;
        }
    }

    public List<Announcement> deleteAnnouncement(List<Long> ids) {
        List<Announcement> announcementList = new ArrayList<>();
        if(!(ids.isEmpty())) {
            for (Long id : ids) {
                Optional<Announcement> announcement = announcementRepository.findById(id);
                if(announcement.isPresent()){
                    announcementList.add(announcement.get());
                }
            }
            for (Announcement announcement : announcementList) {
                announcementRepository.delete(announcement);
            }
        }
            return announcementList;

    }

    public Page<Announcement> getAllAnnouncement(int page, int pageSize){
        Pageable pageable = PageRequest.of(page,  pageSize);
        Page<Announcement> announcements = announcementRepository.findAllByOrderByPublishingDateDesc(pageable);
        return announcements;
    }

    public Optional<Announcement> getAnnouncement(Long id){
        return announcementRepository.findById(id);
    }

}
