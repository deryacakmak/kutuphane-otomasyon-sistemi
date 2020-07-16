package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto;

public class DeleteBookDto {

    private Long isbn;

    public DeleteBookDto() {}

    public DeleteBookDto(Long isbn) {
        this.isbn = isbn;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }
}
