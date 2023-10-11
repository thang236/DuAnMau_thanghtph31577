package com.example.duanmau_thanghtph31577.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau_thanghtph31577.database.DBHelper;
import com.example.duanmau_thanghtph31577.model.SachModel;

import java.util.ArrayList;
import java.util.List;


public class SachDAO {
    private final DBHelper dbHelper;
    public SachDAO(Context context) {
        dbHelper = new DBHelper(context);
    }
    public ArrayList<SachModel> getListSach(){
        ArrayList<SachModel> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Cursor c = database.rawQuery("select * from Sach",null);
            if (c.getCount() > 0){
                c.moveToFirst();
                do {
                    list.add(new SachModel(
                            c.getInt(0),
                            c.getString(1),
                            c.getInt(2),
                            c.getInt(3),
                            c.getString(4),
                            c.getString(5),
                            c.getString(6),
                            c.getString(7),
                            c.getInt(8)));
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

    public boolean addSach(SachModel sachModel) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();

        ContentValues values = new ContentValues();


        values.put("tenSach", sachModel.getTenSach());
        values.put("soLuong", sachModel.getSoLuong());
        values.put("gia", sachModel.getGia());
        values.put("loaiSach", sachModel.getLoaiSach());
        values.put("tacGia", sachModel.getTacGia());
        values.put("moTa", sachModel.getMoTa());
        values.put("imgSach", sachModel.getImgSach());
        values.put("trangThai", sachModel.getTrangThai());


        database.setTransactionSuccessful();
        database.endTransaction();

        long check = database.insert("Sach", null, values);

        return check != -1;
    }


    public boolean removeSach(int id){
        SQLiteDatabase sqLiteDatabase =dbHelper.getWritableDatabase();
        int row = sqLiteDatabase.delete("Sach", "idSach = ?", new String[]{String.valueOf(id)});
        return  row!=-1;
    }

    public  boolean updateSach ( SachModel sachModel ){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tenSach", sachModel.getTenSach());
        values.put("soLuong", sachModel.getSoLuong());
        values.put("gia", sachModel.getGia());
        values.put("loaiSach", sachModel.getLoaiSach());
        values.put("tacGia", sachModel.getTacGia());
        values.put("moTa", sachModel.getMoTa());
        values.put("imgSach", sachModel.getImgSach());
        values.put("trangThai", sachModel.getTrangThai());

        int check = database.update("Sach", values, "idSach = ?", new String[]{String.valueOf(sachModel.getId())});
        return  check!=-1;
    }

    public SachModel getByID(int id) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("Sach", new String[]{"idSach", "tenSach", "soLuong", "gia","loaiSach", "tacGia", "moTa", "imgSach","trangThai"}, "idSach = ?",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor.moveToFirst()) {
            int maspIndex = cursor.getColumnIndex("idSach");
            int tenSIndex = cursor.getColumnIndex("tenSach");
            int soLuongIndex = cursor.getColumnIndex("soLuong");
            int giaIndex = cursor.getColumnIndex("gia");
            int loaiSachIndex = cursor.getColumnIndex("loaiSach");
            int tacGiaIndex = cursor.getColumnIndex("tacGia");
            int moTaIndex = cursor.getColumnIndex("moTa");
            int imgSachIndex = cursor.getColumnIndex("imgSach");
            int trangThaiIndex = cursor.getColumnIndex("trangThai");


            int maspValue = cursor.getInt(maspIndex);
            String tenSValue = cursor.getString(tenSIndex);
            int soLuongValue = cursor.getInt(soLuongIndex);
            int giaValue = cursor.getInt(giaIndex);
            String loaiValue = cursor.getString(loaiSachIndex);
            String tacGiaValue = cursor.getString(tacGiaIndex);
            String moTaValue = cursor.getString(moTaIndex);
            String imgSachValue = cursor.getString(imgSachIndex);
            int trangThaiValue = cursor.getInt(trangThaiIndex);


            return new SachModel(maspValue, tenSValue, soLuongValue, giaValue, loaiValue, tacGiaValue, moTaValue, imgSachValue, trangThaiValue);

        }
        return null;
    }

    public List<String> getLoaiSachByTrangThai1() {
        List<String> loaiSachList = new ArrayList<>();

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            String[] columns = {"tenLoaiSach"};
            String selection = "trangThai = ?";
            String[] selectionArgs = {"1"};

            cursor = database.query("LoaiSach", columns, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String tenLoaiSach = cursor.getString(
                            cursor.getColumnIndex("tenLoaiSach"));
                    loaiSachList.add(tenLoaiSach);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("SachDAO", "Error querying LoaiSach: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return loaiSachList;
    }

    public ArrayList<SachModel> getTop10MostBorrowedBooks() {
        ArrayList<SachModel> top10Books = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM Sach ORDER BY soLuong DESC LIMIT 10";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    // Lấy thông tin từ cursor và tạo đối tượng SachModel
                    @SuppressLint("Range") int idSach = cursor.getInt(cursor.getColumnIndex("idSach"));
                    @SuppressLint("Range") String tenSach = cursor.getString(cursor.getColumnIndex("tenSach"));
                    @SuppressLint("Range") int soLuong = cursor.getInt(cursor.getColumnIndex("soLuong"));
                    @SuppressLint("Range") int gia = cursor.getInt(cursor.getColumnIndex("gia"));
                    @SuppressLint("Range") String loaiSach = cursor.getString(cursor.getColumnIndex("loaiSach"));
                    @SuppressLint("Range") String tacGia = cursor.getString(cursor.getColumnIndex("tacGia"));
                    @SuppressLint("Range") String moTa = cursor.getString(cursor.getColumnIndex("moTa"));
                    @SuppressLint("Range") String imgSach = cursor.getString(cursor.getColumnIndex("imgSach"));
                    @SuppressLint("Range") int trangThai = cursor.getInt(cursor.getColumnIndex("trangThai"));

                    SachModel sach = new SachModel(idSach, tenSach, soLuong, gia, loaiSach, tacGia, moTa, imgSach, trangThai);
                    top10Books.add(sach);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        db.close();
        return top10Books;
    }

}




