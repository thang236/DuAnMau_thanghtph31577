package com.example.duanmau_thanghtph31577.model;

public class LoaiSachModel {
    private int id;
    private String tenLoaiSach;
    private int trangThai;

    public LoaiSachModel() {
    }

    public LoaiSachModel(int id, String tenLoaiSach, int trangThai) {
        this.id = id;
        this.tenLoaiSach = tenLoaiSach;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoaiSach() {
        return tenLoaiSach;
    }

    public void setTenLoaiSach(String tenLoaiSach) {
        this.tenLoaiSach = tenLoaiSach;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
