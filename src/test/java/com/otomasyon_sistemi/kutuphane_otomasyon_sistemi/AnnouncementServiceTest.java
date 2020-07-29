package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi;


import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.AddAnnouncementDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Announcement;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.AnnouncementRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services.IAnnouncementServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class AnnouncementServiceTest {

    @Autowired
    private IAnnouncementServices iAnnouncementServices;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Test
    public void testAnnouncement(){
        AddAnnouncementDto addAnnouncementDto = new AddAnnouncementDto();
        addAnnouncementDto.setContent("Test Duyurusu!");
        addAnnouncementDto.setTitle("Test!");
        addAnnouncementDto.setPublisherId((long)1);
        iAnnouncementServices.addAnnouncement(addAnnouncementDto);

        List<Announcement> announcements = announcementRepository.findAll();

        assertNotEquals(announcements.size(),0); // to check add

        List<Announcement> announcementList = iAnnouncementServices.getAllAnnouncement(0,1).toList();

       assertNotEquals(announcementList.size(),0); // to check getAllAnnouncement

        List<Long> ids = new ArrayList<>();

        ids.add(announcementList.get(0).getId());

       iAnnouncementServices.deleteAnnouncement(ids);

       List<Announcement> announcementList2 = iAnnouncementServices.getAllAnnouncement(0,1).toList();

       assertEquals(announcementList2.size(),0); // to check delete
    }


}
