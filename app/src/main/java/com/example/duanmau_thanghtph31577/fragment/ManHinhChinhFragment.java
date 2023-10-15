package com.example.duanmau_thanghtph31577.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.controller.AccountDao;
import com.example.duanmau_thanghtph31577.databinding.FragmentManHinhChinhBinding;
import com.example.duanmau_thanghtph31577.fragment.quanlyloaisach.QuanLyLoaiSachFragment;
import com.example.duanmau_thanghtph31577.fragment.quanlyphieumuon.QuanLyPhieuMuon;
import com.example.duanmau_thanghtph31577.fragment.quanlysach.QuanLySachFragment;
import com.example.duanmau_thanghtph31577.fragment.quanlythanhvien.QuanLyThanhVienFragment;
import com.example.duanmau_thanghtph31577.fragment.quanlythuthu.QuanLyThuThuFragment;
import com.example.duanmau_thanghtph31577.fragment.thongke.ThongKeFragment;


public class ManHinhChinhFragment extends Fragment {
    FragmentManHinhChinhBinding binding;
    AccountDao accountDao;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentManHinhChinhBinding.inflate(inflater, container, false);
        accountDao  = new AccountDao(getContext());
        userClick();
        phanQuyen();
        // Inflate the layout for this fragment
        return binding.getRoot();
    }



    private void phanQuyen() {
        int trangThai;
        Bundle bundle = getArguments();
        if (bundle != null) {
            trangThai = bundle.getInt("trangThai");
            Log.d("TAG", "phanQuyenMan : " + trangThai);
            if (trangThai == 1) {
                binding.tvThongKe.setVisibility(View.GONE);
                binding.tvThuThu.setVisibility(View.GONE);
            }

        }


    }



    private void userClick() {
        binding.tvLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new QuanLyLoaiSachFragment()).addToBackStack(null).commit();
            }
        });

        binding.tvSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new QuanLySachFragment()).addToBackStack(null).commit();
            }
        });

        binding.tvPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new QuanLyPhieuMuon()).addToBackStack(null).commit();

            }
        });

        binding.tvThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.frameLayout, new QuanLyThanhVienFragment()).addToBackStack(null).commit();
            }
        });

        binding.tvThuThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.frameLayout, new QuanLyThuThuFragment()).addToBackStack(null).commit();
            }
        });

        binding.tvThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.frameLayout, new ThongKeFragment()).addToBackStack(null).commit();
            }
        });
    }

}