package com.example.duanmau_thanghtph31577.fragment.quanlyphieumuon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.adapter.PhieuAdapter;
import com.example.duanmau_thanghtph31577.controller.PhieuDao;
import com.example.duanmau_thanghtph31577.databinding.FragmentChuaTraBinding;
import com.example.duanmau_thanghtph31577.model.PhieuModel;

import java.util.ArrayList;


public class ChuaTraFragment extends Fragment {
    FragmentChuaTraBinding binding;
    PhieuDao dao;
    ArrayList<PhieuModel> arrayList;
    PhieuAdapter.ChucNanginterfacePhieu chucNanginterfacePhieu;

    PhieuAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChuaTraBinding.inflate(inflater, container, false);
        dao = new PhieuDao(getContext());
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
        arrayList = dao.getPhieuMuonByTrangThai(0);
        adapter = new PhieuAdapter(getContext(), arrayList, chucNanginterfacePhieu);
        binding.rcvPm.setAdapter(adapter);

    }

}