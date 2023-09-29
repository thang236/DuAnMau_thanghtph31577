package com.example.duanmau_thanghtph31577.model;

public class ThanhVienModel {
    private int id;
    private String tenTV;
    private int soDT;
    private String email, diaChi;

    public ThanhVienModel(int id, String tenTV, int soDT, String email, String diaChi) {
        this.id = id;
        this.tenTV = tenTV;
        this.soDT = soDT;
        this.email = email;
        this.diaChi = diaChi;
    }

    public ThanhVienModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public int getSoDT() {
        return soDT;
    }

    public void setSoDT(int soDT) {
        this.soDT = soDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
