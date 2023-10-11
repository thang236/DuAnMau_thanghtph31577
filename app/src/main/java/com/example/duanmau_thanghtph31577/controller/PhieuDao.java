package com.example.duanmau_thanghtph31577.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.duanmau_thanghtph31577.database.DBHelper;
import com.example.duanmau_thanghtph31577.model.LoaiSachModel;
import com.example.duanmau_thanghtph31577.model.PhieuModel;
import com.example.duanmau_thanghtph31577.model.ThuThuModel;

import java.util.ArrayList;
import java.util.List;

public class PhieuDao {
    private final DBHelper dbHelper;

    public PhieuDao(Context context) {
        dbHelper = new DBHelper(context);
    }
    public ArrayList<PhieuModel> getListALL(){

        ArrayList<PhieuModel> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Cursor c = database.rawQuery("select * from PhieuMuon",null);
            if (c.getCount() > 0){
                c.moveToFirst();
                do {
                    list.add(new PhieuModel(
                            c.getInt(0),
                            c.getString(1),
                            c.getString(2),
                            c.getString(3),
                            c.getInt(4),
                            c.getInt(5),
                            c.getInt(6),
                            c.getString(7)));
                }while (c.moveToNext());
                database.setTransactionSuccessful();
            }
        }catch (Exception e){
            Log.e("Error", "getListALL: " + e.getMessage());
        }finally {
            database.endTransaction();
        }
        return list;
    }

    public boolean addPM(PhieuModel phieuModel) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();

        ContentValues values = new ContentValues();

        values.put("tenSach", phieuModel.getTenSach());
        values.put("ngayTra", phieuModel.getNgayTra());
        values.put("ngayMuon", phieuModel.getNgayMuon());
        values.put("soLuong", phieuModel.getSoluong());
        values.put("gia", phieuModel.getGia());
        values.put("trangThai", phieuModel.getTrangThai());
        values.put("tenTV", phieuModel.getTenTV());



        database.setTransactionSuccessful();
        database.endTransaction();

        long check = database.insert("PhieuMuon", null, values);

        return check != -1;
    }

    public void updateTrangThaiTraSach(int idPhieuMuon) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("trangThai", 1);

        String whereClause = "idPM = ?";
        String[] whereArgs = { String.valueOf(idPhieuMuon) };

        db.update("PhieuMuon", values, whereClause, whereArgs);

        db.close();
    }
    public void updateTrangThaiTraSachchinh(int idPhieuMuon, int trangThai) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("trangThai", trangThai);

        String whereClause = "idPM = ?";
        String[] whereArgs = { String.valueOf(idPhieuMuon) };

        db.update("PhieuMuon", values, whereClause, whereArgs);

        db.close();
    }


    public List<String> getTenSachList() {
            List<String> tenSachList = new ArrayList<>();
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String query = "SELECT tenSach FROM Sach WHERE trangThai = 1"; //  điều kiện trạng thái = 1
            Cursor cursor = db.rawQuery(query, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String tenSach = cursor.getString(cursor.getColumnIndex("tenSach"));
                    tenSachList.add(tenSach);
                } while (cursor.moveToNext());
            }

            if (cursor != null) {
                cursor.close();
            }

            return tenSachList;
        }



        @SuppressLint("Range")
        public int getGiaByTenSach(String tenSach) {
            int gia = -1; // Giá mặc định nếu không tìm thấy tên sách

            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String query = "SELECT gia FROM Sach WHERE tenSach=?";
            Cursor cursor = db.rawQuery(query, new String[]{tenSach});

            if (cursor != null && cursor.moveToFirst())
                gia = cursor.getInt(cursor.getColumnIndex("gia"));

            if (cursor != null) {
                cursor.close();
            }

            return gia;
        }

    public List<String> getAllTenThanhVien() {
        List<String> tenThanhVienList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT tenTV FROM ThanhVien", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String tenThanhVien = cursor.getString(cursor.getColumnIndex("tenTV"));
                    tenThanhVienList.add(tenThanhVien);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        db.close();
        return tenThanhVienList;
    }

    public boolean removePhieu(int id){
        SQLiteDatabase sqLiteDatabase =dbHelper.getWritableDatabase();
        int row = sqLiteDatabase.delete("PhieuMuon", "idPM = ?", new String[]{String.valueOf(id)});
        return  row!=-1;
    }

    public PhieuModel getPhieuMuonById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = {
                "idPM",
                "tenSach",
                "ngayTra",
                "ngayMuon",
                "soLuong",
                "gia",
                "trangThai",
                "tenTV"
        };

        String selection = "idPM=?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query("PhieuMuon", columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int idPhieuMuon = cursor.getInt(cursor.getColumnIndex("idPM"));
            @SuppressLint("Range") String tenSach = cursor.getString(cursor.getColumnIndex("tenSach"));
            @SuppressLint("Range") String ngayTra = cursor.getString(cursor.getColumnIndex("ngayTra"));
            @SuppressLint("Range") String ngayMuon = cursor.getString(cursor.getColumnIndex("ngayMuon"));
            @SuppressLint("Range") int soLuong = cursor.getInt(cursor.getColumnIndex("soLuong"));
            @SuppressLint("Range") int gia = cursor.getInt(cursor.getColumnIndex("gia"));
            @SuppressLint("Range") int trangThai = cursor.getInt(cursor.getColumnIndex("trangThai"));
            @SuppressLint("Range") String tenTV = cursor.getString(cursor.getColumnIndex("tenTV"));

            cursor.close();
            db.close();

            return new PhieuModel(idPhieuMuon, tenSach, ngayTra, ngayMuon, soLuong, gia, trangThai, tenTV);
        } else {
            db.close();
            return null; // Phiếu mượn không tồn tại
        }
    }


    public void updatePhieuMuon(PhieuModel phieuMuon) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("tenSach", phieuMuon.getTenSach());
        values.put("ngayTra", phieuMuon.getNgayTra());
        values.put("ngayMuon", phieuMuon.getNgayMuon());
        values.put("soLuong", phieuMuon.getSoluong());
        values.put("gia", phieuMuon.getGia());
        values.put("tenTV", phieuMuon.getTenTV());

        String whereClause = "idPM = ?";
        String[] whereArgs = { String.valueOf(phieuMuon.getId()) };

        db.update("PhieuMuon", values, whereClause, whereArgs);

        db.close();
    }

    public ArrayList<PhieuModel> getPhieuMuonByTrangThai(int trangThai) {
        ArrayList<PhieuModel> phieuMuonList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = {
                "idPM",
                "tenSach",
                "ngayTra",
                "ngayMuon",
                "soLuong",
                "gia",
                "trangThai",
                "tenTV"
        };

        String selection = "trangThai=?";
        String[] selectionArgs = { String.valueOf(trangThai) };

        Cursor cursor = db.query("PhieuMuon", columns, selection, selectionArgs, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int idPhieuMuon = cursor.getInt(cursor.getColumnIndex("idPM"));
                @SuppressLint("Range") String tenSach = cursor.getString(cursor.getColumnIndex("tenSach"));
                @SuppressLint("Range") String ngayTra = cursor.getString(cursor.getColumnIndex("ngayTra"));
                @SuppressLint("Range") String ngayMuon = cursor.getString(cursor.getColumnIndex("ngayMuon"));
                @SuppressLint("Range") int soLuong = cursor.getInt(cursor.getColumnIndex("soLuong"));
                @SuppressLint("Range") int gia = cursor.getInt(cursor.getColumnIndex("gia"));
                @SuppressLint("Range") int trangThaiPhieu = cursor.getInt(cursor.getColumnIndex("trangThai"));
                @SuppressLint("Range") String tenTV = cursor.getString(cursor.getColumnIndex("tenTV"));

                PhieuModel phieuMuon = new PhieuModel(idPhieuMuon, tenSach, ngayTra, ngayMuon, soLuong, gia, trangThaiPhieu, tenTV);

                phieuMuonList.add(phieuMuon);
            }

            cursor.close();
        }

        db.close();
        return phieuMuonList;
    }






}
