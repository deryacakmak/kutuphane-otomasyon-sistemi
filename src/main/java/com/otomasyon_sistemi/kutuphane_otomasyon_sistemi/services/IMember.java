package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services;

import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.MemberDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.BorrowingInfo;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.UserInfo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IMember {

    Page<UserInfo> getMemberInfo(MemberDto memberDto, int page, int pageSize);
    List<BorrowingInfo> getBorrowedBook(Long id, int page, int pageSize);
    Optional<UserInfo> getMember(Long id);

}
