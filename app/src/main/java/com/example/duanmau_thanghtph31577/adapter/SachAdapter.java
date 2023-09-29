package com.example.duanmau_thanghtph31577.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.databinding.DiaglogChucnangBinding;
import com.example.duanmau_thanghtph31577.databinding.ItemSachBinding;
import com.example.duanmau_thanghtph31577.model.SachModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SachModel> arrayList;
    private ChucNanginterfaceSach chucNanginterfaceSach;


    public SachAdapter(Context context, ArrayList<SachModel> arrayList, ChucNanginterfaceSach chucNanginterfaceSach) {
        this.context = context;
        this.arrayList = arrayList;
        this.chucNanginterfaceSach = chucNanginterfaceSach;
    }
    public interface ChucNanginterfaceSach {
        void update(int id);
        void delete(int id);
        void xemChiTiet(int id);
    }

    @NonNull
    @Override
    public SachAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSachBinding binding = ItemSachBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SachAdapter.ViewHolder holder, int position) {
        SachModel obj = arrayList.get(position);

        holder.binding.tvNameSach.setText(obj.getTenSach());
        holder.binding.tvTG.setText(obj.getTacGia());
        holder.binding.tvGia.setText(""+obj.getGia());
        holder.binding.tvSoLuong.setText(""+obj.getSoLuong());
        String img = obj.getImgSach();

        Log.e("TAG", "onBindViewHolder: " +img );

        try {
            Picasso.get().load(img).placeholder(R.drawable.ic_camera).error(R.drawable.ic_camera)
                    .into(holder.binding.img);


        }catch (Exception e) {
            Log.e("PicassoError", "Error loading image: " + e.getMessage());
            Toast.makeText(context, "Không thể load ảnh", Toast.LENGTH_SHORT).show();
        }



        holder.binding.btnChucNang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogChucNang(obj.getId());
            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemSachBinding binding;
        public ViewHolder(@NonNull ItemSachBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    private void openDialogChucNang(int id) {
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
                chucNanginterfaceSach.update(id);
                dialog.dismiss();
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chucNanginterfaceSach.delete(id);
                dialog.dismiss();
            }
        });

        binding.btnXemChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chucNanginterfaceSach.xemChiTiet(id);
                dialog.dismiss();
            }
        });
    }


}
