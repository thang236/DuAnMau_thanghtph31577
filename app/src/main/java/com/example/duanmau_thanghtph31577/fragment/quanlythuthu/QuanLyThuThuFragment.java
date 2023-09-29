package com.example.duanmau_thanghtph31577.fragment.quanlythuthu;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duanmau_thanghtph31577.R;

import com.example.duanmau_thanghtph31577.adapter.ThuThuAdapter;
import com.example.duanmau_thanghtph31577.controller.ThuThuDao;
import com.example.duanmau_thanghtph31577.databinding.FragmentQuanLyThuThuBinding;
import com.example.duanmau_thanghtph31577.model.ThuThuModel;

import java.util.ArrayList;


public class QuanLyThuThuFragment extends Fragment implements ThuThuAdapter.ChucNanginterfaceThuThu {
    FragmentQuanLyThuThuBinding binding;
    ArrayList<ThuThuModel> arrayList;
    ThuThuDao thuThuDao;
    ThuThuAdapter adapter;
    ThuThuAdapter.ChucNanginterfaceThuThu chucNanginterfaceThuThu;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQuanLyThuThuBinding.inflate(inflater, container, false);
        thuThuDao = new ThuThuDao(getContext());
        chucNanginterfaceThuThu = this;

        loadDataFromSQL();

        listener();


        return binding.getRoot();
    }
    private void listener() {
        binding.btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new ThemThuThuFragment()).addToBackStack(null).commit();
            }
        });
    }

    private void loadDataFromSQL() {
        arrayList = thuThuDao.getListTT();
        adapter = new ThuThuAdapter(getContext(), arrayList, chucNanginterfaceThuThu);
        binding.rcvTv.setAdapter(adapter);
    }


    @Override
    public void update(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        SuaThuThuFragment suaThuThuFragment = new SuaThuThuFragment();
        suaThuThuFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, suaThuThuFragment).addToBackStack(null).commit();

    }

    @Override
    public void delete(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Bạn có chắc muốn xóa không ?");
        builder.setPositiveButton("chắc chắn", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                thuThuDao.removeTT(id);
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