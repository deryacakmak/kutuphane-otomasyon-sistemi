package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi;


import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.Book;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BookInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.BookInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookServicesTest {

    @Autowired
    private BookInfoRepository bookInfoRepository;

    @Test
    public void testSaveBook(){
        Book book = new Book((long) 15,"Bab-ı Esrar","2bx","Ahmet Ümit","Roman");
        BookInfo bookInfo = new BookInfo((long)1,"2-xb45",(long)15,(long)15,new Date(),book,"everest");
        bookInfoRepository.save(bookInfo);
        BookInfo bookInfo1 = bookInfoRepository.findByBookId((long)15).get();
        assertNotNull(bookInfo1);
        assertEquals(bookInfo.getISBN(),bookInfo1.getISBN());

    }

}
