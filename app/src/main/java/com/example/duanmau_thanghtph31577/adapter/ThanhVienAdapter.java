package com.example.duanmau_thanghtph31577.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_thanghtph31577.databinding.DiaglogChucnang2Binding;
import com.example.duanmau_thanghtph31577.databinding.DiaglogChucnangBinding;
import com.example.duanmau_thanghtph31577.databinding.ItemThanhvienBinding;
import com.example.duanmau_thanghtph31577.model.ThanhVienModel;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHoder> {
    private  Context context;
    private ArrayList<ThanhVienModel> arrayList;
    private  ChucNanginterfaceThanhVien chucNanginterfaceThanhVien;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVienModel> arrayList, ChucNanginterfaceThanhVien chucNanginterfaceThanhVien) {
        this.context = context;
        this.arrayList = arrayList;
        this.chucNanginterfaceThanhVien = chucNanginterfaceThanhVien;
    }
    public interface ChucNanginterfaceThanhVien {
        void update(int id);
        void delete(int id);
    }

    @NonNull
    @Override
    public ThanhVienAdapter.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemThanhvienBinding binding = ItemThanhvienBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHoder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienAdapter.ViewHoder holder, int position) {
        ThanhVienModel obj = arrayList.get(position);

        holder.binding.tvName.setText(obj.getTenTV());
        holder.binding.tvDiaChi.setText(obj.getDiaChi());
        holder.binding.tvSdt.setText("0"+obj.getSoDT());

        holder.binding.btnChucNang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaglogChucNang(obj.getId());{

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        ItemThanhvienBinding binding;
        public ViewHoder(@NonNull ItemThanhvienBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    private void openDiaglogChucNang(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        DiaglogChucnang2Binding binding = DiaglogChucnang2Binding.inflate(inflater);
        View view = binding.getRoot();
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chucNanginterfaceThanhVien.update(id);
                dialog.dismiss();
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chucNanginterfaceThanhVien.delete(id);
                dialog.dismiss();
            }
        });

    }
}
