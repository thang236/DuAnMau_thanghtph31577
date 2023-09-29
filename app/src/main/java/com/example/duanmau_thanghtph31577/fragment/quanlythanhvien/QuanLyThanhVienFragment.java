package com.example.duanmau_thanghtph31577.fragment.quanlythanhvien;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.adapter.ThanhVienAdapter;
import com.example.duanmau_thanghtph31577.controller.ThanhVienDao;
import com.example.duanmau_thanghtph31577.databinding.FragmentQuanLyThanhVienBinding;
import com.example.duanmau_thanghtph31577.model.ThanhVienModel;

import java.util.ArrayList;


public class QuanLyThanhVienFragment extends Fragment implements ThanhVienAdapter.ChucNanginterfaceThanhVien {
     FragmentQuanLyThanhVienBinding binding;
     ArrayList<ThanhVienModel> arrayList;
     ThanhVienAdapter adapter;
     ThanhVienDao dao;
     ThanhVienAdapter.ChucNanginterfaceThanhVien chucNanginterfaceThanhVien;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuanLyThanhVienBinding.inflate(inflater, container, false);
        dao = new ThanhVienDao(getContext());
        chucNanginterfaceThanhVien = this;
        loadDataFromSQL();
        listener();



        return binding.getRoot();
    }

    private void loadDataFromSQL() {
        arrayList = dao.getListThanhVien();
        adapter = new ThanhVienAdapter(getContext(), arrayList, chucNanginterfaceThanhVien);
        binding.rcvTv.setAdapter(adapter);
    }

    private void listener() {
        binding.btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new ThemThanhVienFragment()).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public void update(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        SuaThanhVienFragment suaThanhVienFragment = new SuaThanhVienFragment();
        suaThanhVienFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, suaThanhVienFragment).addToBackStack(null).commit();

    }

    @Override
    public void delete(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Bạn có chắc muốn xóa không ?");
        builder.setPositiveButton("chắc chắn", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dao.removeThanhVien(id);
                Toast.makeText(getContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                loadDataFromSQL();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "Bạn chọn không xóa", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();

            }
        });
        builder.show();


    }
}