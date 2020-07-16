package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto;


public class AddAnnouncementDto {
    private String title;
    private String content;
    private Long publisherId;


    public AddAnnouncementDto() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }
}
