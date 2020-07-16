package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto;

public class DeleteAnnouncementDto {
    private Long id;

    public DeleteAnnouncementDto() {}

    public DeleteAnnouncementDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
