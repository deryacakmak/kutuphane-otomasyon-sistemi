package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto;

public class DeletePublisherDto {

    private Long id;

    public  DeletePublisherDto(){}

    public DeletePublisherDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
