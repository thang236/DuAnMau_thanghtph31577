package com.example.duanmau_thanghtph31577.fragment.quanlyphieumuon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.databinding.FragmentQuanLyPhieuMuonBinding;


public class QuanLyPhieuMuon extends Fragment {
    FragmentQuanLyPhieuMuonBinding binding;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentQuanLyPhieuMuonBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


}