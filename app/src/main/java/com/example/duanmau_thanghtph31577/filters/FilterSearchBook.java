package com.example.duanmau_thanghtph31577.filters;

import android.widget.Filter;

import com.example.duanmau_thanghtph31577.adapter.SachAdapter;
import com.example.duanmau_thanghtph31577.model.SachModel;

import java.util.ArrayList;

public class FilterSearchBook extends Filter {
    private ArrayList<SachModel> list;
    private SachAdapter adapter;

    public FilterSearchBook(ArrayList<SachModel> list, SachAdapter adapter) {
        this.list = list;
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults filterResults = new FilterResults();
        if ( charSequence != null && charSequence.length() > 0) {
            charSequence = charSequence.toString().toUpperCase().trim();
            ArrayList<SachModel> establishes = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getTenSach().toUpperCase().contains(charSequence)) {
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
        adapter.arrayList = (ArrayList<SachModel>) results.values;
        adapter.notifyDataSetChanged();
    }
}
