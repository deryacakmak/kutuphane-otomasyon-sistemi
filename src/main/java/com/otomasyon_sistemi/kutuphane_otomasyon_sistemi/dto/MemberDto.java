package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto;

public class MemberDto {

    private String firstName;
    private String lastName;

    public MemberDto(){}

    public MemberDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
