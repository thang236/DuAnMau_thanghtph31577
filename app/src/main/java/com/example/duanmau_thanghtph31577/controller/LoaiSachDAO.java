package com.example.duanmau_thanghtph31577.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau_thanghtph31577.database.DBHelper;
import com.example.duanmau_thanghtph31577.model.LoaiSachModel;

import java.util.ArrayList;

public class LoaiSachDAO {
    private final DBHelper dbHelper;
    public LoaiSachDAO(Context context) {
        dbHelper = new DBHelper(context);
    }
    public ArrayList<LoaiSachModel> getListLoaiSach(){
        //tạo một danh sách để add dữ liệu vào SanPham
        ArrayList<LoaiSachModel> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Cursor c = database.rawQuery("select * from LoaiSach",null);
            if (c.getCount() > 0){
                c.moveToFirst();
                do {
                    list.add(new LoaiSachModel(
                            c.getInt(0),
                            c.getString(1),
                            c.getInt(2)));
                }while (c.moveToNext());
                database.setTransactionSuccessful();
            }
        }catch (Exception e){
            Log.e("Error", "getListLoaiSach: " + e.getMessage());
        }finally {
            database.endTransaction();
        }
        return list;
    }

    public boolean addLoaiSach(LoaiSachModel loaiSachModel) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();

        ContentValues values = new ContentValues();


        values.put("tenLoaiSach", loaiSachModel.getTenLoaiSach());
        values.put("trangThai", loaiSachModel.getTrangThai());

        database.setTransactionSuccessful();
        database.endTransaction();

        long check = database.insert("LoaiSach", null, values);

        return check != -1;
    }


    public boolean removeLoaiSach(int id){
        SQLiteDatabase sqLiteDatabase =dbHelper.getWritableDatabase();
        int row = sqLiteDatabase.delete("LoaiSach", "idLoaiSach = ?", new String[]{String.valueOf(id)});
        return  row!=-1;
    }

    public  boolean updateLoaiSach ( LoaiSachModel loaiSachModel ){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tenLoaiSach", loaiSachModel.getTenLoaiSach());
        values.put("trangThai", loaiSachModel.getTrangThai());

        int check = database.update("LoaiSach", values, "idLoaiSach = ?", new String[]{String.valueOf(loaiSachModel.getId())});
        return  check!=-1;
    }

    public LoaiSachModel getByID(int id) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("LoaiSach", new String[]{"idLoaiSach", "tenLoaiSach", "trangThai"}, "idLoaiSach = ?",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor.moveToFirst()) {
            int maspIndex = cursor.getColumnIndex("idLoaiSach");
            int tenspIndex = cursor.getColumnIndex("tenLoaiSach");
            int trangThaiIndex = cursor.getColumnIndex("trangThai");


            int maspValue = cursor.getInt(maspIndex);
            String tenspValue = cursor.getString(tenspIndex);
            int trangThaiValue = cursor.getInt(trangThaiIndex);

            return new LoaiSachModel(maspValue, tenspValue, trangThaiValue);

        }
        return null;
    }



}


