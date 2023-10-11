package com.example.duanmau_thanghtph31577.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau_thanghtph31577.database.DBHelper;

public class AccountDao {
    private  final DBHelper dbHelper;

    public AccountDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public boolean checkUsername(String username){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("select * from Account where username=?",
                new String[]{username});
        if (c.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
    public void register(String username, String password, String name, String email){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("password",password);
        cv.put("name",name);
        cv.put("email",email);
        cv.put("vaiTro", 0);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert("Account",null,cv);
        db.close();
    }
    public void registerNV(String username, String password, String name, String email){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("password",password);
        cv.put("name",name);
        cv.put("email",email);
        cv.put("vaiTro", 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert("Account",null,cv);
        db.close();
    }

    public int login(String username, String password){
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from Account where username=? and password=?",str);
        if (c.moveToNext()){
            result = 1;
        }
        return result;
    }

    public void changePassword(String username, String newPassword){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password", newPassword);
        db.update("Account", cv, "username=?", new String[]{username});
        db.close();
    }

    @SuppressLint("Range")
    public String getPasswordByUsername(String username) {
        String password = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT password FROM Account WHERE username=?", new String[]{username});
        if (cursor.moveToFirst()) {
            // Lấy mật khẩu từ cột "password" trong kết quả truy vấn
            password = cursor.getString(cursor.getColumnIndex("password"));
        }
        cursor.close();
        db.close();
        return password;
    }

    @SuppressLint("Range")
    public String getNameByUsername(String username) {
        String name = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT name FROM Account WHERE username = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        if (cursor != null && cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex("name"));
        }

        if (cursor != null) {
            cursor.close();
        }

        return name;
    }




}
