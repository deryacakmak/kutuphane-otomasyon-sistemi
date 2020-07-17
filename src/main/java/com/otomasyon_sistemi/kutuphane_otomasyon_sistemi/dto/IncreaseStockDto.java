package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.dto;

import java.util.Date;

public class IncreaseStockDto {

    private Long isbn;
    private Date publicationYear;
    private  Long stockNumber;

    public IncreaseStockDto(){}

    public IncreaseStockDto(Long isbn, Date publicationYear, Long stockNumber) {
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.stockNumber = stockNumber;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Date getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Date publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Long getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(Long stockNumber) {
        this.stockNumber = stockNumber;
    }
}
