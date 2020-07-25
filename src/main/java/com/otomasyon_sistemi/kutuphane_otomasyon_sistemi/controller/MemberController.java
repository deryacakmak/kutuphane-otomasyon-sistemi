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


    @PostMapping("/search/{pageSize}/{page}")
    @PreAuthorize("hasRole('USER') or hasRole('OFFICER')")
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
    @PreAuthorize("hasRole('USER') or hasRole('OFFICER')")
    public ResponseEntity<UserInfo> getMember(@PathVariable Long id) {
        Optional<UserInfo> member = memberService.getMember(id);
        if(member.isPresent()) {
            return new ResponseEntity<>(member.get(), HttpStatus.OK);
        }else{
            throw new BadRequestException("There is no member with this id");
        }
    }
    @GetMapping("/borrowed/{pageSize}/{id}/{page}")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<Page<BorrowingInfo>> getBorrowedBook(@PathVariable ("pageSize") int pageSize, @PathVariable("id)") Long id, @PathVariable("page") int page){
        Page<BorrowingInfo> books = memberService.getBorrowedBook(id,page,pageSize);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/borrowed/{pageSize}/{page}")
    @PreAuthorize("hasRole('USER') or hasRole('OFFICER')")
    public ResponseEntity<Page<BorrowingInfo>> getMembersBorrowedBooks(@PathVariable ("pageSize") int pageSize, @PathVariable int page, @RequestBody SearchMemberDto searchMemberDto){
        Page<BorrowingInfo> books = memberService.getBorrowedBooks(searchMemberDto.getUserIdentifier(),page,pageSize);
       if (!(books.isEmpty())) {
           return new ResponseEntity<>(books, HttpStatus.OK);
       }
       else{
           throw new BadRequestException("There is no member with this id");
       }
    }

    @PostMapping("/leadBook/{MemberId}/{BookId}")
    @PreAuthorize("hasRole('USER') or hasRole('OFFICER')")
    public ResponseEntity<Response> lendBook(@PathVariable ("BookId") Long id,@PathVariable ("MemberId") Long memberId){
         return memberService.lendBook(id, memberId);

    }

    @PostMapping("/deliver/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('OFFICER')")
    public ResponseEntity<Response> deliverBook(@PathVariable("id") Long id){

        return memberService.deliverBook(id);
    }

    @GetMapping("/extend/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('OFFICER') or hasRole('MEMBER')")
    public ResponseEntity<Response> extendBookDate(@PathVariable("id") Long id){
        return memberService.extendBookDate(id);
    }

}
