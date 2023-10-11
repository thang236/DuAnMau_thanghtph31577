package com.example.duanmau_thanghtph31577.filters;

import android.widget.Filter;

import com.example.duanmau_thanghtph31577.adapter.ThuThuAdapter;
import com.example.duanmau_thanghtph31577.model.LoaiSachModel;
import com.example.duanmau_thanghtph31577.model.ThuThuModel;

import java.util.ArrayList;

public class FilterSearchStaff extends Filter {
    ArrayList<ThuThuModel> list;
    ThuThuAdapter adapter;

    public FilterSearchStaff(ArrayList<ThuThuModel> list, ThuThuAdapter adapter) {
        this.list = list;
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults filterResults = new FilterResults();
        if ( charSequence != null && charSequence.length() > 0) {
            charSequence = charSequence.toString().toUpperCase().trim();
            ArrayList<ThuThuModel> establishes = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getTenTT().toUpperCase().contains(charSequence)) {
                    establishes.add(list.get(i));
                }
            }

            filterResults.count = establishes.size();
            filterResults.values = establishes;
        } else {
            filterResults.count = list.size();
            filterResults.values = list;
        }

        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.arrayList = (ArrayList<ThuThuModel>) results.values;
        adapter.notifyDataSetChanged();
    }
}
