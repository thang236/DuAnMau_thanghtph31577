package com.example.duanmau_thanghtph31577.model;

public class SachModel {
    private int id;
    private String tenSach;
    private int soLuong;
    private int gia;
    private String loaiSach;
    private String tacGia;
    private String moTa;
    private String imgSach;
    private int trangThai;

    public SachModel() {
    }

    public SachModel(int id, String tenSach, int soLuong, int gia, String loaiSach, String tacGia, String moTa, String imgSach, int trangThai) {
        this.id = id;
        this.tenSach = tenSach;
        this.soLuong = soLuong;
        this.gia = gia;
        this.loaiSach = loaiSach;
        this.tacGia = tacGia;
        this.moTa = moTa;
        this.imgSach = imgSach;
        this.trangThai = trangThai;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getLoaiSach() {
        return loaiSach;
    }

    public void setLoaiSach(String loaiSach) {
        this.loaiSach = loaiSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getImgSach() {
        return imgSach;
    }

    public void setImgSach(String imgSach) {
        this.imgSach = imgSach;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
