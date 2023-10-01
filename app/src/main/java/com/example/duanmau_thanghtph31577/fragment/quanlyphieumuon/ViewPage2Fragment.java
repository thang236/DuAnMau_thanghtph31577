package com.example.duanmau_thanghtph31577.fragment.quanlyphieumuon;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPage2Fragment extends FragmentStateAdapter {
    public ViewPage2Fragment(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new TongPhieuFragment();

        } else if (position == 1) {
            return new ChuaTraFragment();
        } else if (position == 2) {
            return new DaTraFragment();
        } else
            return new QuaHanFragment();

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
