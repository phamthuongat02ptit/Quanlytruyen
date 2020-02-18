package com.example.quanlytruyen.model;

import java.io.Serializable;

public class Truyen implements Serializable {
    private int IdTruyen;
    private String TenTruyen;
    private String TheLoaiTruyen;
    private String MoTaTruyen;
    private String LinkTruyen;
    private int DanhGiaTruyen;

    public Truyen(int idTruyen, String tenTruyen, String theLoaiTruyen, String moTaTruyen, String linkTruyen, int danhGiaTruyen) {
        IdTruyen = idTruyen;
        TenTruyen = tenTruyen;
        TheLoaiTruyen = theLoaiTruyen;
        MoTaTruyen = moTaTruyen;
        LinkTruyen = linkTruyen;
        DanhGiaTruyen = danhGiaTruyen;
    }

    public int getIdTruyen() {
        return IdTruyen;
    }

    public void setIdTruyen(int idTruyen) {
        IdTruyen = idTruyen;
    }

    public String getTenTruyen() {
        return TenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        TenTruyen = tenTruyen;
    }

    public String getTheLoaiTruyen() {
        return TheLoaiTruyen;
    }

    public void setTheLoaiTruyen(String theLoaiTruyen) {
        TheLoaiTruyen = theLoaiTruyen;
    }

    public String getMoTaTruyen() {
        return MoTaTruyen;
    }

    public void setMoTaTruyen(String moTaTruyen) {
        MoTaTruyen = moTaTruyen;
    }

    public String getLinkTruyen() {
        return LinkTruyen;
    }

    public void setLinkTruyen(String linkTruyen) {
        LinkTruyen = linkTruyen;
    }

    public int getDanhGiaTruyen() {
        return DanhGiaTruyen;
    }

    public void setDanhGiaTruyen(int danhGiaTruyen) {
        DanhGiaTruyen = danhGiaTruyen;
    }
}
