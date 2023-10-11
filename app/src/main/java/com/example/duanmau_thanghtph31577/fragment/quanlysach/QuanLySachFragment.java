package com.example.duanmau_thanghtph31577.fragment.quanlysach;

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
import com.example.duanmau_thanghtph31577.adapter.SachAdapter;
import com.example.duanmau_thanghtph31577.controller.SachDAO;
import com.example.duanmau_thanghtph31577.databinding.FragmentQuanLySachBinding;
import com.example.duanmau_thanghtph31577.model.SachModel;

import java.util.ArrayList;


public class QuanLySachFragment extends Fragment implements SachAdapter.ChucNanginterfaceSach {
    private FragmentQuanLySachBinding binding;
    private ArrayList<SachModel> arrayList;
    private SachDAO sachDAO;
    private SachAdapter adapter;
    private SachAdapter.ChucNanginterfaceSach chucNanginterfaceSach;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuanLySachBinding.inflate(inflater, container, false);
        arrayList = new ArrayList<>();
        sachDAO = new SachDAO(getContext());
        adapter = new SachAdapter(getContext(), arrayList, chucNanginterfaceSach);
        chucNanginterfaceSach = this;

        loadDataFormSQL();
        listener();






        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void loadDataFormSQL(){
        arrayList = sachDAO.getListSach();
        adapter = new SachAdapter(getContext(), arrayList, chucNanginterfaceSach);
        binding.rcvSach.setAdapter(adapter);
    }

    private void listener() {

        binding.edTimKiemSach.addTextChangedListener(new TextWatcher() {
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
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new ThemSachFragment()).addToBackStack(null).commit();
            }
        });
    }








    @Override
    public void update(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        SuaSachFragment suaSachFragment = new SuaSachFragment();
        suaSachFragment.setArguments(bundle);;
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, suaSachFragment).addToBackStack(null).commit();

    }

    @Override
    public void delete(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Bạn có chắc muốn xóa không ?");
        builder.setPositiveButton("chắc chắn", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sachDAO.removeSach(id);
                Toast.makeText(getContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                loadDataFormSQL();
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

    }
}