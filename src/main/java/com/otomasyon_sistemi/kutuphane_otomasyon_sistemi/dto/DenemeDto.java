package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto;

public class DenemeDto {
    private String author;
    private String name;

    public DenemeDto() {}

    public DenemeDto(String author, String name) {
        this.author = author;
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
