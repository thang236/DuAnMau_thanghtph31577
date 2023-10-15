package com.example.duanmau_thanghtph31577.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau_thanghtph31577.database.DBHelper;

public class ThongKeDao {

    private final DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public ThongKeDao(Context context) {
        dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public double thongKeDoanhThuTheoThoiGian(String tungay, String dengay) {
        double totalRevenue = 0.0;
        try {

            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String query = "SELECT SUM(gia) FROM PhieuMuon WHERE ngayTra BETWEEN ? AND ? ;";
            Cursor cursor = db.rawQuery(query, new String[]{tungay, dengay});

            if (cursor != null && cursor.moveToFirst()) {
                totalRevenue = cursor.getDouble(0);
            }

            if (cursor != null) {
                cursor.close();
            }
        }catch (Exception e) {
            Log.d("TAG", "thongKeDoanhThuTheoThoiGian: "+ e.getMessage());
        }


        return totalRevenue;
    }



}
