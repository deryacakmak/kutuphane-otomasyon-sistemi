package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.controller;


import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.SearchMemberDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.exception.BadRequestException;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BorrowingInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.UserInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.response.Response;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services.IMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    IMember memberService;



    @GetMapping("/borrowed/{pageSize}/{id}/{page}")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<Page<BorrowingInfo>> getBorrowedBook(@PathVariable ("pageSize") int pageSize, @PathVariable("id)") Long id, @PathVariable("page") int page){
        Page<BorrowingInfo> books = memberService.getBorrowedBook(id,page,pageSize);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    @GetMapping("/extend/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('OFFICER') or hasRole('MEMBER')")
    public ResponseEntity<Response> extendBookDate(@PathVariable("id") Long id){
        return memberService.extendBookDate(id);
    }



}
