package com.example.duanmau_thanghtph31577.model;

public class PhieuModel {
    private int id;
    private String tenSach, ngayTra, ngayMuon;
    private int soluong, gia;
    private String tenTV;

    public PhieuModel(int id, String tenSach, String ngayTra, String ngayMuon, int soluong, int gia, String tenTV) {
        this.id = id;
        this.tenSach = tenSach;
        this.ngayTra = ngayTra;
        this.ngayMuon = ngayMuon;
        this.soluong = soluong;
        this.gia = gia;
        this.tenTV = tenTV;
    }

    public PhieuModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }
}
