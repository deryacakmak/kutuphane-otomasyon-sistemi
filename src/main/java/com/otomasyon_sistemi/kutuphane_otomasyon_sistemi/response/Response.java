package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.response;


import java.io.Serializable;

public class Response implements Serializable {
    private String message;

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
