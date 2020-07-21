package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.controller;


import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.MemberDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.exception.BadRequestException;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BorrowingInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.UserInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services.IMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    IMember memberService;


    @PostMapping("/search/{pageSize}/{page}")
    public ResponseEntity<Page<UserInfo>> getAllMembers(@PathVariable ("pageSize") int pageSize,@PathVariable ("page") int page, @RequestBody MemberDto memberDto) {
        Page<UserInfo> users = memberService.getMemberInfo(memberDto,page,pageSize);
        if(!(users.isEmpty())){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        else {
            throw new BadRequestException("There is no member with this information");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> getMember(@PathVariable Long id) {
        Optional<UserInfo> member = memberService.getMember(id);
        if(member.isPresent()) {
            return new ResponseEntity<>(member.get(), HttpStatus.OK);
        }else{
            throw new BadRequestException("There is no member with this id");
        }
    }
    @GetMapping("/borrowed/{pageSize}/{id}/{page}")
    public ResponseEntity<List<BorrowingInfo>> getBorrowedBooks(@PathVariable ("pageSize") int pageSize, @PathVariable Long id, @PathVariable int page){
        List<BorrowingInfo> books = memberService.getBorrowedBook(id,page,pageSize);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
