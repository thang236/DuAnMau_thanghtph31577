package com.example.duanmau_thanghtph31577.fragment.thongke;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau_thanghtph31577.adapter.Top10Adapter;
import com.example.duanmau_thanghtph31577.controller.SachDAO;
import com.example.duanmau_thanghtph31577.databinding.FragmentTop10Binding;
import com.example.duanmau_thanghtph31577.model.SachModel;

import java.util.ArrayList;
import java.util.List;

public class Top10Fragment extends Fragment {
    FragmentTop10Binding binding;
    SachDAO dao;
    ArrayList<SachModel> arrayList;
    Top10Adapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTop10Binding.inflate(inflater, container, false);
        dao = new SachDAO(getContext());
        listener();



        // Inflate the layout for this fragment
        return binding.getRoot();
    }


    private void  listener() {

        binding.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTop10();
            }
        });
    }
    private void showTop10() {
        arrayList = dao.getTop10MostBorrowedBooks();
        adapter = new Top10Adapter(getContext(), arrayList);
        binding.rcvTop10.setAdapter(adapter);

    }


}