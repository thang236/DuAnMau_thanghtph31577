package com.example.duanmau_thanghtph31577.fragment.quanlyloaisach;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.adapter.LoaiSachAdapter;
import com.example.duanmau_thanghtph31577.controller.LoaiSachDAO;
import com.example.duanmau_thanghtph31577.databinding.FragmentQuanLyLoaiSachBinding;
import com.example.duanmau_thanghtph31577.model.LoaiSachModel;

import java.util.ArrayList;


public class QuanLyLoaiSachFragment extends Fragment implements LoaiSachAdapter.ChucNanginterfaceLoaiSach {
    private ArrayList<LoaiSachModel> arrayList;
    private LoaiSachAdapter adapter;
    private FragmentQuanLyLoaiSachBinding binding;
    private LoaiSachAdapter.ChucNanginterfaceLoaiSach chucNanginterfaceLoaiSach;
    private LoaiSachDAO loaiSachDAO;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuanLyLoaiSachBinding.inflate(inflater, container, false);
        arrayList = new ArrayList<>();
        chucNanginterfaceLoaiSach = this;
        adapter = new LoaiSachAdapter(getContext(), arrayList, chucNanginterfaceLoaiSach);
        loaiSachDAO = new LoaiSachDAO(getContext());
        loadData();
        listener();

        return binding.getRoot();
    }
    private void listener() {

        binding.edTimKiemLoaiSach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    adapter.getFilter().filter(s);

                }catch (Exception e) {
                    Log.d("TAG", "onTextChanged: loi search"+ e.getMessage());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new ThemLoaiSachFragment()).addToBackStack(null).commit();
            }
        });

    }
    private void loadData(){
        arrayList = loaiSachDAO.getListLoaiSach();
        Log.e("zzzzz", "loadData: " + arrayList.size() );
        adapter = new LoaiSachAdapter(getContext(), arrayList, chucNanginterfaceLoaiSach);
        binding.rcvLoaiSach.setAdapter(adapter);

    }

    @Override
    public void update(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        SuaLoaiSachFragment suaLoaiSachFragment = new SuaLoaiSachFragment();
        suaLoaiSachFragment.setArguments(bundle);;
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, suaLoaiSachFragment).addToBackStack(null).commit();
    }

    @Override
    public void delete(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Bạn có chắc muốn xóa không ?");
        builder.setPositiveButton("chắc chắn", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                loaiSachDAO.removeLoaiSach(id);
                Toast.makeText(getContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                loadData();
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

    @Override
    public void xemChiTiet(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        XemChiTietLoaiSachFragment xemChiTietLoaiSachFragment = new XemChiTietLoaiSachFragment();
        xemChiTietLoaiSachFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, xemChiTietLoaiSachFragment).addToBackStack(null).commit();

    }
}