package com.example.duanmau_thanghtph31577.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "du_an_mau1", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Account = "create table Account (username text primary key, password text , name text, email text, vaiTro INTEGER)";
        db.execSQL(Account);

        String loaiSach = "CREATE TABLE LoaiSach ( idLoaiSach INTEGER PRIMARY KEY AUTOINCREMENT, tenLoaiSach TEXT, trangThai INTEGER DEFAULT 0)";
        db.execSQL(loaiSach);

        String Sach = "CREATE TABLE Sach (idSach INTEGER PRIMARY KEY AUTOINCREMENT, tenSach TEXT, soLuong integer, gia integer, loaiSach TEXT, tacGia TEXT, moTa Text, imgSach text, trangThai INTEGER DEFAULT 0)";
        db.execSQL(Sach);

        String thanhVien = "CREATE TABLE ThanhVien (idTV INTEGER PRIMARY KEY AUTOINCREMENT, tenTV TEXT, soDT INTEGER, email TEXT, diaChi TEXT)";
        db.execSQL(thanhVien);

        String pM = "CREATE TABLE PhieuMuon (idPM INTEGER PRIMARY KEY AUTOINCREMENT, tenSach TEXT, ngayTra TEXT, ngayMuon TEXT, soLuong INTEGER ,gia INTEGER, tenTV TEXT)";
        db.execSQL(pM);

        String thuThu = "CREATE TABLE ThuThu (idTT INTEGER PRIMARY KEY AUTOINCREMENT, tenTT TEXT, soDT INTEGER, email TEXT, diaChi TEXT, username TEXT)";
        db.execSQL(thuThu);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Account");
            db.execSQL("DROP TABLE IF EXISTS loaiSach");
            db.execSQL("DROP TABLE IF EXISTS Sach");
            db.execSQL("DROP TABLE IF EXISTS ThanhVien");
            db.execSQL("DROP TABLE IF EXISTS pM");
            db.execSQL("DROP TABLE IF EXISTS thuThu");
            onCreate(db);
        }


    }
}
