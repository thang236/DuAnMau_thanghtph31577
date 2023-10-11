package com.example.duanmau_thanghtph31577.fragment.thongke;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duanmau_thanghtph31577.fragment.quanlyphieumuon.ChuaTraFragment;
import com.example.duanmau_thanghtph31577.fragment.quanlyphieumuon.DaTraFragment;
import com.example.duanmau_thanghtph31577.fragment.quanlyphieumuon.QuaHanFragment;
import com.example.duanmau_thanghtph31577.fragment.quanlyphieumuon.TongPhieuFragment;

public class ViewPageFragment extends FragmentStateAdapter {

    public ViewPageFragment(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new Top10Fragment();
        } else  {
            return new DoanhThuFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
