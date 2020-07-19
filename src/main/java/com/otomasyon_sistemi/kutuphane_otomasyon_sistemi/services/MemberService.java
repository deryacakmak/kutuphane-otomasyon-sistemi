package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services;


import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.MemberDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BookInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BorrowingInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.User;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.UserInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private BorrowingInfoRepository borrowingInfoRepository;

    @Autowired
    private BookInfoRepository bookInfoRepository;

    public List<UserInfo> getMemberInfo(MemberDto memberDto, int page)
    {
        Pageable pageable = PageRequest.of(page, 2);
        Page<UserInfo> users = userInfoRepository.findByUserFirstNameAndUserLastName(memberDto.getFirstName(),memberDto.getLastName(),pageable);
        if(!(users.isEmpty())){
            return users.toList();
        }
        else{
            return null;
        }
    }

    public List<BorrowingInfo> getBorrowedBook(Long id,int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<BorrowingInfo> borrowingInfos = borrowingInfoRepository.findAllByUserId(id,pageable);
        if(!(borrowingInfos.isEmpty())) {
            return borrowingInfos.toList();
        }
        return null;
    }
}