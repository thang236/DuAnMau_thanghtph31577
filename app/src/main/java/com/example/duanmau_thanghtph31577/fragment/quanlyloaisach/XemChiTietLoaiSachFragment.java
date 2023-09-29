package com.example.duanmau_thanghtph31577.fragment.quanlyloaisach;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau_thanghtph31577.controller.LoaiSachDAO;
import com.example.duanmau_thanghtph31577.databinding.FragmentXemChiTietLoaiSachBinding;
import com.example.duanmau_thanghtph31577.model.LoaiSachModel;

import java.util.ArrayList;

public class XemChiTietLoaiSachFragment extends Fragment {
    FragmentXemChiTietLoaiSachBinding binding;
    LoaiSachDAO loaiSachDAO ;
    ArrayList<LoaiSachModel> arrayList;
    private int id;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentXemChiTietLoaiSachBinding.inflate(inflater, container, false);
        loaiSachDAO = new LoaiSachDAO(getContext());
        arrayList = new ArrayList<>();
        loadData();
        listener();
        return binding.getRoot();
    }

    private void loadData() {
        Bundle bundle = getArguments();
        if (bundle != null){
            id = bundle.getInt("id");
        }
        LoaiSachModel loaiSachModel = loaiSachDAO.getByID(id);
        binding.txtNamLoaiSach.setText(loaiSachModel.getTenLoaiSach());
        if (loaiSachModel.getTrangThai() == 1){
            binding.txtTrangThai.setText("Còn hoạt động");
        } else {
            binding.txtTrangThai.setText("Không hoạt động");
        }

    }

    private void listener(){
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
}