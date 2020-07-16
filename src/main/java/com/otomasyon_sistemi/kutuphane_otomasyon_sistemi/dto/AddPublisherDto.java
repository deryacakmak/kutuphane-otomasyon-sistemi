package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto;

public class AddPublisherDto {

    private String name;

    public AddPublisherDto(){}

    public AddPublisherDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
