package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services;


import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.MemberDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BorrowingInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.UserInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService implements IMember{

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private BorrowingInfoRepository borrowingInfoRepository;

    @Autowired
    private BookInfoRepository bookInfoRepository;

    public Page<UserInfo> getMemberInfo(MemberDto memberDto, int page, int pageSize)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<UserInfo> users = userInfoRepository.findByUserFirstNameAndUserLastName(memberDto.getFirstName(),memberDto.getLastName(),pageable);
        if(!(users.isEmpty())){
            return users;
        }
        else{
            return null;
        }
    }

    public List<BorrowingInfo> getBorrowedBook(Long id,int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<BorrowingInfo> borrowingInfos = borrowingInfoRepository.findAllByUserId(id,pageable);
        if(!(borrowingInfos.isEmpty())) {
            return borrowingInfos.toList();
        }
        return null;
    }

    public Optional<UserInfo> getMember( Long id){
        return userInfoRepository.findByUserId(id);
    }
}
