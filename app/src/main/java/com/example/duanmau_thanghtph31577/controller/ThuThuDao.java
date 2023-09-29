package com.example.duanmau_thanghtph31577.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau_thanghtph31577.database.DBHelper;
import com.example.duanmau_thanghtph31577.model.ThuThuModel;

import java.util.ArrayList;

public class ThuThuDao {
    private final DBHelper dbHelper;
    public ThuThuDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<ThuThuModel> getListTT(){
        ArrayList<ThuThuModel> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Cursor c = database.rawQuery("select * from ThuThu",null);
            if (c.getCount() > 0){
                c.moveToFirst();
                do {
                    list.add(new ThuThuModel(
                            c.getInt(0),
                            c.getString(1),
                            c.getInt(2),
                            c.getString(3),
                            c.getString(4),
                            c.getString(5)
                    ));
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


    public boolean addTT(ThuThuModel thuThuModel) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();

        ContentValues values = new ContentValues();

        values.put("tenTT", thuThuModel.getTenTT());
        values.put("soDT", thuThuModel.getSoDT());
        values.put("email", thuThuModel.getEmail());
        values.put("diaChi", thuThuModel.getDiaChi());
        values.put("username", thuThuModel.getUsername());


        database.setTransactionSuccessful();
        database.endTransaction();

        long check = database.insert("ThuThu", null, values);

        return check != -1;
    }

    public boolean removeTT(int id){
        SQLiteDatabase sqLiteDatabase =dbHelper.getWritableDatabase();
        int row = sqLiteDatabase.delete("ThuThu", "idTT = ?", new String[]{String.valueOf(id)});
        return  row!=-1;
    }

    public  boolean updateTT ( ThuThuModel thuThuModel ){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tenTT", thuThuModel.getTenTT());
        values.put("soDT", thuThuModel.getSoDT());
        values.put("email", thuThuModel.getEmail());
        values.put("diaChi", thuThuModel.getDiaChi());

        int check = database.update("ThuThu", values, "idTT = ?", new String[]{String.valueOf(thuThuModel.getIdTT())});
        return  check!=-1;
    }


    public ThuThuModel getByID(int id) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("ThuThu", new String[]{"idTT", "tenTT", "soDT", "email","diaChi","username"}, "idTT = ?",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor.moveToFirst()) {
            int maTVIndex = cursor.getColumnIndex("idTT");
            int tenTVIndex = cursor.getColumnIndex("tenTT");
            int soDTIndex = cursor.getColumnIndex("soDT");
            int emailIndex = cursor.getColumnIndex("email");
            int diaChiIndex = cursor.getColumnIndex("diaChi");
            int usernameIndex = cursor.getColumnIndex("username");


            int maTVValue = cursor.getInt(maTVIndex);
            String tenTVValue = cursor.getString(tenTVIndex);
            int soDTValue = cursor.getInt(soDTIndex);
            String emailValue = cursor.getString(emailIndex);
            String diaChiValue = cursor.getString(diaChiIndex);
            String usernameValue = cursor.getString(usernameIndex);



            return new ThuThuModel(maTVValue, tenTVValue, soDTValue, emailValue, diaChiValue, usernameValue);

        }
        return null;
    }


}
