package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto;

public class SearchMemberDto {

    private String firstName;
    private String lastName;
    private Long userIdentifier;

    public SearchMemberDto(){}

    public SearchMemberDto(String firstName, String lastName, Long userIdentifier) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userIdentifier = userIdentifier;
    }

    public Long getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(Long userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
