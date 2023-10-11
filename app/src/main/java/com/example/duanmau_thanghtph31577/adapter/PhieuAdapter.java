package com.example.duanmau_thanghtph31577.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_thanghtph31577.databinding.DiaglogChucnangBinding;
import com.example.duanmau_thanghtph31577.databinding.DiaglogPhieuBinding;
import com.example.duanmau_thanghtph31577.databinding.ItemPhieuBinding;
import com.example.duanmau_thanghtph31577.model.PhieuModel;

import java.util.ArrayList;

public class PhieuAdapter extends RecyclerView.Adapter<PhieuAdapter.ViewHolder> {
    Context context;
    ArrayList<PhieuModel> arrayList;
    private ChucNanginterfacePhieu chucNanginterfacePhieu;

    public PhieuAdapter(Context context, ArrayList<PhieuModel> arrayList, ChucNanginterfacePhieu chucNanginterfacePhieu) {
        this.context = context;
        this.arrayList = arrayList;
        this.chucNanginterfacePhieu = chucNanginterfacePhieu;
    }

    public interface ChucNanginterfacePhieu {
        void traSach(int id);
        void update(int id);
        void  delete(int id);
    }

    @NonNull
    @Override
    public PhieuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPhieuBinding binding = ItemPhieuBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuAdapter.ViewHolder holder, int position) {
        PhieuModel obj = arrayList.get(position);
        holder.binding.tvId.setText("Mã phiếu: " + obj.getId());
        holder.binding.tvNgayMuon.setText("Ngày mượn: " + obj.getNgayMuon());
        holder.binding.tvNgayTra.setText("Ngày trả: " + obj.getNgayTra());
        if (obj.getTrangThai() == 0){
            holder.binding.tvTrangThai.setText("Trạng thái: Chưa trả");
            holder.binding.tvTrangThai.setTextColor(Color.GRAY);
        } else if (obj.getTrangThai() == 1) {
            holder.binding.tvTrangThai.setTextColor(Color.GREEN);
            holder.binding.tvTrangThai.setText("Trạng thái: Đã trả");
        }else {
            holder.binding.tvTrangThai.setTextColor(Color.RED);
            holder.binding.tvTrangThai.setText("Trạng thái: quá hạn");
        }
        holder.binding.btnChucNang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(obj.getId());
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemPhieuBinding binding;
        public ViewHolder(@NonNull ItemPhieuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    private void openDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        DiaglogPhieuBinding binding = DiaglogPhieuBinding.inflate(inflater);
        View view = binding.getRoot();
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chucNanginterfacePhieu.update(id);
                dialog.dismiss();
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chucNanginterfacePhieu.delete(id);
                dialog.dismiss();
            }
        });

        binding.btnTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chucNanginterfacePhieu.traSach(id);
                dialog.dismiss();
            }
        });
    }
}
