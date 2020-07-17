package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DeleteBookDto {

    private Long isbn;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publicationDate;

    public DeleteBookDto() {}

    public DeleteBookDto(Long isbn, Date publicationDate) {
        this.isbn = isbn;
        this.publicationDate = publicationDate;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}
