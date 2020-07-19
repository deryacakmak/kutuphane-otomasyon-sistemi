package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.controller;


import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.MemberDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.exception.BadRequestException;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.UserInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.UserInfoRepository;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    UserInfoRepository userInfoRepository;


    @PostMapping("/search/{page}")
    public ResponseEntity<List<UserInfo>> getAllMembers(@PathVariable ("page") int page, @RequestBody MemberDto memberDto) {

        List<UserInfo> users = memberService.getMemberInfo(memberDto,page);
        if(!(users.isEmpty())){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        else {
            throw new BadRequestException("There is no member with this information");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> getAMember(@PathVariable Long id) {
        Optional<UserInfo> member = userInfoRepository.findByUserId(id);
        if(member.isPresent()) {
            return new ResponseEntity<>(member.get(), HttpStatus.OK);
        }else{
            throw new BadRequestException("There is no member with this id");
        }
    }
}
