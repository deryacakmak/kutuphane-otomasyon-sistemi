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
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    IMember memberService;


    @PostMapping("/search/{pageSize}/{page}")
    public ResponseEntity<Page<UserInfo>> getAllMembers(@PathVariable ("pageSize") int pageSize,@PathVariable ("page") int page, @RequestBody SearchMemberDto searchMemberDto) {
        Page<UserInfo> users = memberService.getMemberInfo(searchMemberDto,page,pageSize);
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
    public ResponseEntity<Page<BorrowingInfo>> getBorrowedBook(@PathVariable ("pageSize") int pageSize, @PathVariable Long id, @PathVariable int page){
        Page<BorrowingInfo> books = memberService.getBorrowedBook(id,page,pageSize);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/borrowed/{pageSize}/{page}")
    public ResponseEntity<Page<BorrowingInfo>> getBorrowedBook(@PathVariable ("pageSize") int pageSize, @PathVariable int page, @RequestBody SearchMemberDto searchMemberDto){
        Page<BorrowingInfo> books = memberService.getBorrowedBooks(searchMemberDto.getUserIdentifier(),page,pageSize);
       if (!(books.isEmpty())) {
           return new ResponseEntity<>(books, HttpStatus.OK);
       }
       else{
           throw new BadRequestException("There is no member with this id");
       }
    }

    @PostMapping("/leadBook/{id}")
    public ResponseEntity<Response> leadBook(@PathVariable ("id") Long id,@RequestBody SearchMemberDto searchMemberDto){
         return memberService.leadingBook(id, searchMemberDto);

    }

    @GetMapping("/deliver/{id}")
    public ResponseEntity<Response> deliverBook(@PathVariable("id") Long id){
        return memberService.deliverBook(id);
    }

}
