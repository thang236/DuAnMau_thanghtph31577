package com.example.duanmau_thanghtph31577.fragment.quanlyphieumuon;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.adapter.PhieuAdapter;
import com.example.duanmau_thanghtph31577.controller.PhieuDao;
import com.example.duanmau_thanghtph31577.databinding.FragmentQuaHanBinding;
import com.example.duanmau_thanghtph31577.model.PhieuModel;

import java.util.ArrayList;


public class QuaHanFragment extends Fragment implements PhieuAdapter.ChucNanginterfacePhieu{
    FragmentQuaHanBinding binding;
    PhieuDao dao;
    PhieuAdapter.ChucNanginterfacePhieu chucNanginterfacePhieu;
    ArrayList<PhieuModel> arrayList;
    PhieuAdapter adapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuaHanBinding.inflate(inflater, container, false);
        dao = new PhieuDao(getContext());
        chucNanginterfacePhieu = this;
        loaData();
        refresh();

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
    private void  refresh() {
        binding.swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loaData();
                binding.swiperefreshlayout.setRefreshing(false);
            }
        });
    }

    private void loaData() {
        arrayList = dao.getPhieuMuonByTrangThai(2);
        adapter = new PhieuAdapter(getContext(), arrayList, chucNanginterfacePhieu);
        binding.rcvPm.setAdapter(adapter);
    }



    @Override
    public void traSach(int id) {
        dao.updateTrangThaiTraSach(id);
        Toast.makeText(getContext(), "Trả sách thành công", Toast.LENGTH_SHORT).show();
        loaData();
    }

    @Override
    public void update(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        SuaPhieuFragment suaPhieuFragment = new SuaPhieuFragment();
        suaPhieuFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, suaPhieuFragment).addToBackStack(null).commit();

    }

    @Override
    public void delete(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Bạn có chắc muốn xóa không ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dao.removePhieu(id);
                Toast.makeText(getContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                loaData();
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