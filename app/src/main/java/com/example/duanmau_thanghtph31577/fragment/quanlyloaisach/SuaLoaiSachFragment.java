package com.example.duanmau_thanghtph31577.fragment.quanlyloaisach;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.controller.LoaiSachDAO;
import com.example.duanmau_thanghtph31577.databinding.FragmentSuaLoaiSachBinding;
import com.example.duanmau_thanghtph31577.model.LoaiSachModel;

import java.util.ArrayList;


public class SuaLoaiSachFragment extends Fragment {
    private FragmentSuaLoaiSachBinding binding;
    private LoaiSachDAO loaiSachDAO;
    private ArrayList<LoaiSachModel> arrayList;
    private int id;
    private final String TAG = "SuaLoaiSachFragment";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSuaLoaiSachBinding.inflate(inflater, container, false);
        loaiSachDAO = new LoaiSachDAO(getContext());
        arrayList = new ArrayList<>();


        loadDataFormSQL();
        listener();
        return binding.getRoot();
    }

    private void loadDataFormSQL() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
        }
        LoaiSachModel loaiSachModel = loaiSachDAO.getByID(id);
        binding.edTenLoaiSach.setText(loaiSachModel.getTenLoaiSach());
        Log.e(TAG ,"trangthai: "+ loaiSachModel.getTrangThai() );
        if (loaiSachModel.getTrangThai() == 0){
            binding.swTrangThai.setChecked(false);
        } else {
            binding.swTrangThai.setChecked(true);
        }
    }

    private void listener() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        binding.btnHoanTat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

    }

    String nameLoai;
    private void validate() {
        nameLoai = binding.edTenLoaiSach.getText().toString();
        if (TextUtils.isEmpty(nameLoai)){
            binding.edTenLoaiSach.setError("Vui lòng nhập tên loại sách");
            binding.edTenLoaiSach.requestFocus();
            return;
        }
        else {
            update();

        }
    }

    int trangThai = -1;

    private void update() {
        LoaiSachModel objUpdate = new LoaiSachModel();
        objUpdate.setId(id);
        objUpdate.setTenLoaiSach(nameLoai);
        if (binding.swTrangThai.isChecked()) {
            trangThai = 1;
        }else {
            trangThai = 0;
        }
        objUpdate.setTrangThai(trangThai);
        loaiSachDAO.updateLoaiSach(objUpdate);
        Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new QuanLyLoaiSachFragment()).commit();
    }


}