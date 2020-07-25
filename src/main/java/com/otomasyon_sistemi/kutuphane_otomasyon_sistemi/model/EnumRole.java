package com.otomasyon_sistemi.kutuphane_otomasyon_sistemi.model;

public enum EnumRole {
    ROLE_OFFICER("officer"),
    ROLE_MEMBER("member"),
    ROLE_USER("user");

    public  final String  label;

    EnumRole(String label) {
        this.label = label;
    }


}