package com.example.duanmau_thanghtph31577.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau_thanghtph31577.database.DBHelper;
import com.example.duanmau_thanghtph31577.model.ThanhVienModel;

import java.util.ArrayList;

public class ThanhVienDao {
    private final DBHelper dbHelper;
    public ThanhVienDao(Context context) {
        dbHelper = new DBHelper(context);
    }
    public ArrayList<ThanhVienModel> getListThanhVien(){
        ArrayList<ThanhVienModel> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Cursor c = database.rawQuery("select * from ThanhVien",null);
            if (c.getCount() > 0){
                c.moveToFirst();
                do {
                    list.add(new ThanhVienModel(
                            c.getInt(0),
                            c.getString(1),
                            c.getInt(2),
                            c.getString(3),
                            c.getString(4)));
                }while (c.moveToNext());
                database.setTransactionSuccessful();
            }
        }catch (Exception e){
            Log.e("Error", "getListSach: " + e.getMessage());
        }finally {
            database.endTransaction();
        }
        return list;
    }

    public boolean addThanhVien(ThanhVienModel thanhVienModel) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();

        ContentValues values = new ContentValues();

        values.put("tenTV", thanhVienModel.getTenTV());
        values.put("soDT", thanhVienModel.getSoDT());
        values.put("email", thanhVienModel.getEmail());
        values.put("diaChi", thanhVienModel.getDiaChi());

        database.setTransactionSuccessful();
        database.endTransaction();

        long check = database.insert("ThanhVien", null, values);

        return check != -1;
    }

    public boolean removeThanhVien(int id){
        SQLiteDatabase sqLiteDatabase =dbHelper.getWritableDatabase();
        int row = sqLiteDatabase.delete("ThanhVien", "idTV = ?", new String[]{String.valueOf(id)});
        return  row!=-1;
    }


    public  boolean updateThanhVien ( ThanhVienModel thanhVienModel ){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tenTV", thanhVienModel.getTenTV());
        values.put("soDT", thanhVienModel.getSoDT());
        values.put("email", thanhVienModel.getEmail());
        values.put("diaChi", thanhVienModel.getDiaChi());

        int check = database.update("ThanhVien", values, "idTV = ?", new String[]{String.valueOf(thanhVienModel.getId())});
        return  check!=-1;
    }

    public ThanhVienModel getByID(int id) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("ThanhVien", new String[]{"idTV", "tenTV", "soDT", "email","diaChi"}, "idTV = ?",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor.moveToFirst()) {
            int maTVIndex = cursor.getColumnIndex("idTV");
            int tenTVIndex = cursor.getColumnIndex("tenTV");
            int soDTIndex = cursor.getColumnIndex("soDT");
            int emailIndex = cursor.getColumnIndex("email");
            int diaChiIndex = cursor.getColumnIndex("diaChi");


            int maTVValue = cursor.getInt(maTVIndex);
            String tenTVValue = cursor.getString(tenTVIndex);
            int soDTValue = cursor.getInt(soDTIndex);
            String emailValue = cursor.getString(emailIndex);
            String diaChiValue = cursor.getString(diaChiIndex);



            return new ThanhVienModel(maTVValue, tenTVValue, soDTValue, emailValue, diaChiValue);

        }
        return null;
    }



}
