package com.example.duanmau_thanghtph31577.fragment.quanlyphieumuon;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.databinding.FragmentQuanLyPhieuMuonBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class QuanLyPhieuMuon extends Fragment {
    FragmentQuanLyPhieuMuonBinding binding;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuanLyPhieuMuonBinding.inflate(inflater, container, false);
        ViewPage2Fragment viewPage2Fragment = new ViewPage2Fragment(getActivity());
        binding.ViewPager.setAdapter(viewPage2Fragment);


        new TabLayoutMediator(binding.TabLayout, binding.ViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Tất cả");
                }else if (position == 1) {
                    tab.setText("Chưa trả");
                } else if (position == 2) {
                    tab.setText("Đã trả");
                }else  {
                    tab.setText("Quá hạn");
                }
            }
        }).attach();

        applyTabSpacing();
        return binding.getRoot();
    }

    private void applyTabSpacing() {
        ViewGroup slidingTabStrip = (ViewGroup) binding.TabLayout.getChildAt(0);
        int tabCount = slidingTabStrip.getChildCount();
        int spacing = 20; // Adjust the spacing value as per your requirement

        for (int i = 0; i < tabCount; i++) {
            View tabView = slidingTabStrip.getChildAt(i);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tabView.getLayoutParams();
            params.setMarginStart(spacing);
            params.setMarginEnd(spacing);
            tabView.requestLayout();
        }
    }


}

