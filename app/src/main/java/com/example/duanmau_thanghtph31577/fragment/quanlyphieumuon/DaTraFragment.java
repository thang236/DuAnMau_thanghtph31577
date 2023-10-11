package com.example.duanmau_thanghtph31577.fragment.quanlyphieumuon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.adapter.PhieuAdapter;
import com.example.duanmau_thanghtph31577.controller.PhieuDao;
import com.example.duanmau_thanghtph31577.databinding.FragmentDaTraBinding;
import com.example.duanmau_thanghtph31577.model.PhieuModel;

import java.util.ArrayList;


public class DaTraFragment extends Fragment {
    FragmentDaTraBinding binding;
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
        // Inflate the layout for this fragment
        binding = FragmentDaTraBinding.inflate(inflater, container, false);
        dao = new PhieuDao(getContext());
        loaData();
        refresh();

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
        arrayList = dao.getPhieuMuonByTrangThai(1);
        adapter = new PhieuAdapter(getContext(), arrayList, chucNanginterfacePhieu);
        binding.rcvPm.setAdapter(adapter);
    }
}