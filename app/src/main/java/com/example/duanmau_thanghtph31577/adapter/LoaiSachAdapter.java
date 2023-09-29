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

import com.example.duanmau_thanghtph31577.databinding.DiaglogChucnangBinding;
import com.example.duanmau_thanghtph31577.databinding.ItemLoaisachBinding;
import com.example.duanmau_thanghtph31577.model.LoaiSachModel;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LoaiSachModel> loaiSachModelArrayList;
    private ChucNanginterfaceLoaiSach chucNanginterfaceLoaiSach;



    public LoaiSachAdapter(Context context, ArrayList<LoaiSachModel> loaiSachModelArrayList, ChucNanginterfaceLoaiSach chucNanginterfaceLoaiSach) {
        this.context = context;
        this.loaiSachModelArrayList = loaiSachModelArrayList;
        this.chucNanginterfaceLoaiSach = chucNanginterfaceLoaiSach;
    }
    public interface ChucNanginterfaceLoaiSach {
        void update(int id);
        void delete(int id);
        void xemChiTiet(int id);
    }

    @NonNull
    @Override
    public LoaiSachAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLoaisachBinding binding = ItemLoaisachBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSachAdapter.ViewHolder holder, int position) {
        LoaiSachModel object = loaiSachModelArrayList.get(position);

        holder.binding.tvLoaiSach.setText(object.getTenLoaiSach());
        Integer id = object.getId();

        holder.binding.btnChucNang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogChucNang(id);
            }
        });

    }

    @Override
    public int getItemCount() {
        return loaiSachModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemLoaisachBinding binding;
        public ViewHolder(@NonNull ItemLoaisachBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    private void openDialogChucNang(int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        DiaglogChucnangBinding binding = DiaglogChucnangBinding.inflate(inflater);
        View view = binding.getRoot();
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chucNanginterfaceLoaiSach.update(id);
                dialog.dismiss();
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chucNanginterfaceLoaiSach.delete(id);
                dialog.dismiss();
            }
        });

        binding.btnXemChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chucNanginterfaceLoaiSach.xemChiTiet(id);
                dialog.dismiss();
            }
        });
    }
}
