package com.example.duanmau_thanghtph31577.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.databinding.ItemTop10Binding;
import com.example.duanmau_thanghtph31577.fragment.thongke.Top10Fragment;
import com.example.duanmau_thanghtph31577.model.SachModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.ViewHolder> {
    Context context;
    ArrayList<SachModel> arrayList;

    public Top10Adapter(Context context, ArrayList<SachModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Top10Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTop10Binding binding = ItemTop10Binding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Top10Adapter.ViewHolder holder, int position) {
        SachModel obj = arrayList.get(position);

        holder.binding.tvTt.setText("" + (position+1));
        holder.binding.tvNameSach.setText(obj.getTenSach());
        holder.binding.tvTG.setText(obj.getTacGia());
        holder.binding.tvGia.setText(""+obj.getGia());
        holder.binding.tvSoLuong.setText(""+obj.getSoLuong());
        String img = obj.getImgSach();

        try {
            Picasso.get().load(img).placeholder(R.drawable.ic_camera).error(R.drawable.ic_camera)
                    .into(holder.binding.img);


        }catch (Exception e) {
            Log.e("PicassoError", "Error loading image: " + e.getMessage());
            Toast.makeText(context, "Không thể load ảnh", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemTop10Binding binding;
        public ViewHolder(ItemTop10Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
