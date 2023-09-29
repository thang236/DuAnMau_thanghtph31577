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
import com.example.duanmau_thanghtph31577.databinding.ItemThuthuBinding;
import com.example.duanmau_thanghtph31577.model.ThuThuModel;

import java.util.ArrayList;

public class ThuThuAdapter extends RecyclerView.Adapter<ThuThuAdapter.ViewHoder> {
     Context context;
     ArrayList<ThuThuModel> arrayList;
     ChucNanginterfaceThuThu chucNanginterfaceThuThu;

    public ThuThuAdapter(Context context, ArrayList<ThuThuModel> arrayList, ChucNanginterfaceThuThu chucNanginterfaceThuThu) {
        this.context = context;
        this.arrayList = arrayList;
        this.chucNanginterfaceThuThu = chucNanginterfaceThuThu;
    }
    public interface ChucNanginterfaceThuThu {
        void update(int id);
        void delete(int id);
    }


    @NonNull
    @Override
    public ThuThuAdapter.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemThuthuBinding binding = ItemThuthuBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHoder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ThuThuAdapter.ViewHoder holder, int position) {
        ThuThuModel obj = arrayList.get(position);

        holder.binding.tvName.setText(obj.getTenTT());
        holder.binding.tvSdt.setText("0" + obj.getSoDT());
        holder.binding.tvDiaChi.setText(obj.getDiaChi());
        holder.binding.tvEmail.setText(obj.getEmail());

        holder.binding.btnChucNang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaglogChucNang(obj.getIdTT());
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        ItemThuthuBinding binding;
        public ViewHoder(@NonNull ItemThuthuBinding binding) {
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
                chucNanginterfaceThuThu.update(id);
                dialog.dismiss();
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chucNanginterfaceThuThu.delete(id);
                dialog.dismiss();
            }
        });

    }

}
