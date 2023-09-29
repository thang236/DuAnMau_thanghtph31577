package com.example.duanmau_thanghtph31577.fragment.quanlyloaisach;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duanmau_thanghtph31577.controller.LoaiSachDAO;
import com.example.duanmau_thanghtph31577.databinding.FragmentThemLoaiSachBinding;
import com.example.duanmau_thanghtph31577.model.LoaiSachModel;


public class ThemLoaiSachFragment extends Fragment {
    public static final String TAG ="ThemLoaiSachFragment";
    FragmentThemLoaiSachBinding binding;
    Integer trangThai = -1;
    LoaiSachDAO loaiSachDAO;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentThemLoaiSachBinding.inflate(inflater, container, false);
        loaiSachDAO = new LoaiSachDAO(getContext());
        // Inflate the layout for this fragment
        listener();


        return binding.getRoot();

    }
    private void listener() {
        binding.btnHoanTat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    private void validate() {
        String nameLoai = binding.edTenLoaiSach.getText().toString();
        if (TextUtils.isEmpty(nameLoai)){
            binding.edTenLoaiSach.setError("Vui lòng nhập tên loại sách");
            binding.edTenLoaiSach.requestFocus();
            return;
        }
        else {
            saveTenLoai();
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }
    private void saveTenLoai() {
        LoaiSachModel loaiSachModel = new LoaiSachModel();
        loaiSachModel.setTenLoaiSach(binding.edTenLoaiSach.getText().toString().trim());
        if (binding.swTrangThai.isChecked()) {
             trangThai = 1;
        }else {
            trangThai = 0;
        }
        Log.e(TAG, "saveTenLoai: Trang thai1" + trangThai );
        loaiSachModel.setTrangThai(trangThai);
        loaiSachDAO.addLoaiSach(loaiSachModel);
        Toast.makeText(getContext(), "Thêm mới thành công", Toast.LENGTH_SHORT).show();
        
    }
}