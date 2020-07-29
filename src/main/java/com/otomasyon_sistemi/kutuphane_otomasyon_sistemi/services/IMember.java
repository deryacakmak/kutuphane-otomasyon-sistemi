package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.SearchMemberDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BorrowingInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.UserInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.response.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IMember {

    Page<UserInfo> getMemberInfo(SearchMemberDto searchMemberDto, int page, int pageSize);
    Page<BorrowingInfo> getBorrowedBook(Long id, int page, int pageSize);
    Optional<UserInfo> getMember(Long id);
    ResponseEntity<Response> lendBook(Long id, Long memberId);
    Page<BorrowingInfo> getBorrowedBooks(Long id, int page, int pageSize);
    ResponseEntity<Response> deliverBook(Long id);
    ResponseEntity<Response> extendBookDate(Long bookInfoId);
    void suspendMail();
    void checkSuspend();


}
