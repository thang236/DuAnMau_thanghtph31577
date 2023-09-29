package com.example.duanmau_thanghtph31577.model;

public class ThuThuModel {
    private int idTT;
    private String tenTT;
    private int soDT;
    private String email, diaChi, username;

    public ThuThuModel(int idTT, String tenTT, int soDT, String email, String diaChi, String username) {
        this.idTT = idTT;
        this.tenTT = tenTT;
        this.soDT = soDT;
        this.email = email;
        this.diaChi = diaChi;
        this.username = username;
    }

    public ThuThuModel() {
    }

    public int getIdTT() {
        return idTT;
    }

    public void setIdTT(int idTT) {
        this.idTT = idTT;
    }

    public String getTenTT() {
        return tenTT;
    }

    public void setTenTT(String tenTT) {
        this.tenTT = tenTT;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
