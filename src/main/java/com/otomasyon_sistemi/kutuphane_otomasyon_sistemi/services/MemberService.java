package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.services;


import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto.SearchMemberDto;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.exception.BadRequestException;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model.*;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.repository.*;
import com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Date;
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

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private JavaMailSender javaMailSender;

    public Page<UserInfo> getMemberInfo(SearchMemberDto searchMemberDto, int page, int pageSize)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        Optional<Role> role = roleRepository.findByName(EnumRole.ROLE_MEMBER);
        Page<UserInfo> users;
        if(searchMemberDto.getUserIdentifier() != null){
            users = userInfoRepository.findByUserUserIdentifierAndUserRoles(searchMemberDto.getUserIdentifier(), pageable,role.get());

        }
        else {
            users = userInfoRepository.findByUserFirstNameAndUserLastNameAndUserRoles(searchMemberDto.getFirstName(), searchMemberDto.getLastName(), pageable, role.get());
        }
        return users;
    }

    public Page<BorrowingInfo> getBorrowedBook(Long id, int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<BorrowingInfo> borrowingInfos = borrowingInfoRepository.findAllByUserIdOrderByBorrowingDateDesc(id,pageable);
       return borrowingInfos;
    }

    public Page<BorrowingInfo> getBorrowedBooks(Long id, int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<BorrowingInfo> borrowingInfos = borrowingInfoRepository.findAllByUserUserIdentifierOrderByBorrowingDateDesc(id,pageable);
        return borrowingInfos;
    }

    public Optional<UserInfo> getMember( Long id){
        return userInfoRepository.findByUserId(id);
    }

    public ResponseEntity<Response> lendBook(Long id, Long  memberId){
        Optional<UserInfo> member = userInfoRepository.findById(memberId);
        if(member.isPresent()){
            if(member.get().bookBorrowingSituation()) {
                BorrowingInfo borrowingInfo = new BorrowingInfo();
                BookInfo book = bookInfoRepository.findById(id).get();
                book.setBorrowingNumber(book.getBorrowingNumber()-1);
                borrowingInfo.setBookInfo(book);
                borrowingInfo.setUser(member.get().getUser());
                borrowingInfo.setBorrowingDate(new Date());
                borrowingInfo.setReturnSituation(false);
                borrowingInfo.setExtendDate(false);
                member.get().setBookBorrowingSituation(false);
                borrowingInfoRepository.save(borrowingInfo);
                bookInfoRepository.save(book);
                Response response = new Response("Book is lend successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else{
                throw new BadRequestException("Deliver book that is borrowed before ");
            }
        }
        throw new BadRequestException("There is no member with this id");
    }

    public ResponseEntity<Response> deliverBook(Long id){
        Optional<BorrowingInfo> borrowingInfo = borrowingInfoRepository.findByIdAndReturnSituation(id,false);
        if(borrowingInfo.isPresent()){
            UserInfo userInfo = userInfoRepository.findByUserId(borrowingInfo.get().getUser().getId()).get();
            borrowingInfo.get().setReturnSituation(true);
            borrowingInfo.get().setReturnDate(new Date());
            borrowingInfo.get().getBookInfo().setBorrowingNumber(borrowingInfo.get().getBookInfo().getBorrowingNumber()+1);
            if(!(userInfo.getSuspendedSituation())){
                userInfo.setBookBorrowingSituation(true);
            }
            Response response = new Response("Book is delivered successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            throw new BadRequestException("Book not found!");
        }
    }
    public ResponseEntity<Response> extendBookDate(Long borrowingInfoId){
        BorrowingInfo borrowingInfo = borrowingInfoRepository.findById(borrowingInfoId).get();
        UserInfo userInfo  = userInfoRepository.findByUserId(borrowingInfo.getUser().getId()).get();
        if(!(borrowingInfo.isReturnSituation()) && !(userInfo.getSuspendedSituation()) && !(borrowingInfo.isExtendDate())){
            borrowingInfo.setExtendDate(true);
            Response response = new Response("Your new delivery date has been extended for 5 days.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            Response response = new Response("You cannot extend date!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    public void suspendMail(){
        List<BorrowingInfo> users = borrowingInfoRepository.findByReturnSituation(false);
        for(BorrowingInfo borrowingInfo : users){
            Optional<UserInfo> user = userInfoRepository.findByUserId(borrowingInfo.getUser().getId());
            if(!(user.get().getSuspendedSituation())){
                Date today = new Date();
                int diff = today.getDay()-borrowingInfo.getBorrowingDate().getDay();
                if(borrowingInfo.isExtendDate() && diff == 15 ||!(borrowingInfo.isExtendDate()) && diff == 10){
                    send("You have been suspended for not delivering the book.","suspended situation", user.get().getUser().getEmail());
                    user.get().setSuspendedSituation(true);
                }

            }
        }
    }

    public void checkSuspend(){
        List<UserInfo> users = userInfoRepository.findBySuspendSituation(true);
        for(UserInfo user : users){
            BorrowingInfo borrowingInfo = borrowingInfoRepository.findByUserId(user.getUser().getId()).get();
            if(borrowingInfo.isReturnSituation() && user.getSuspendedSituation()){
                int diff = new Date().getDay() - borrowingInfo.getReturnDate().getDay();
                if(diff == 3){
                    user.setSuspendedSituation(false);
                    user.setBookBorrowingSituation(true);
                    send("suspended situation", "You can borrow new books",user.getUser().getEmail());
                }
            }
        }
    }



    private void send(String subject, String content, String email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        try {
            messageHelper.setTo(email);
            messageHelper.setText(content);
            messageHelper.setSubject(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw  new BadRequestException("Mail error");
        }
        javaMailSender.send(mimeMessage);

    }


}
