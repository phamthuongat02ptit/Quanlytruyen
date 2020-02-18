package com.example.quanlytruyen.model;

public class TheLoaiTruyen {
    private int idTruyen;
    private String tenTheLoai;
    private String moTaTheLoai;

    public TheLoaiTruyen(int idTruyen, String tenTheLoai, String moTaTheLoai) {
        this.idTruyen = idTruyen;
        this.tenTheLoai = tenTheLoai;
        this.moTaTheLoai = moTaTheLoai;
    }

    public int getIdTruyen() {
        return idTruyen;
    }

    public void setIdTruyen(int idTruyen) {
        this.idTruyen = idTruyen;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getMoTaTheLoai() {
        return moTaTheLoai;
    }

    public void setMoTaTheLoai(String moTaTheLoai) {
        this.moTaTheLoai = moTaTheLoai;
    }
}
