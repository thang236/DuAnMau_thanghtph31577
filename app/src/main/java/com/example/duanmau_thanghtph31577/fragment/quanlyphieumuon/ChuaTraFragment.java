package com.example.duanmau_thanghtph31577.fragment.quanlyphieumuon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.databinding.FragmentChuaTraBinding;


public class ChuaTraFragment extends Fragment {
    FragmentChuaTraBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChuaTraBinding.inflate(inflater, container, false);


        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}