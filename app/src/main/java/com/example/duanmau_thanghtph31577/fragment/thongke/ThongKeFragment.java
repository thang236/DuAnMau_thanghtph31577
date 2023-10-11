package com.example.duanmau_thanghtph31577.fragment.thongke;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.databinding.FragmentThongKeBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class ThongKeFragment extends Fragment {
    FragmentThongKeBinding binding;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentThongKeBinding.inflate(inflater, container, false);
        ViewPageFragment viewPageFragment = new ViewPageFragment(getActivity());
        binding.ViewPager.setAdapter(viewPageFragment);

        new TabLayoutMediator(binding.TabLayout, binding.ViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Top 10 sách");
                }else {
                    tab.setText("Doanh thu");
                }
            }
        }).attach();

        applyTabSpacing();

        return binding.getRoot();
    }

    private void applyTabSpacing() {
        ViewGroup slidingTabStrip = (ViewGroup) binding.TabLayout.getChildAt(0);
        int tabCount = slidingTabStrip.getChildCount();
        int spacing = 60; // Adjust the spacing value as per your requirement

        for (int i = 0; i < tabCount; i++) {
            View tabView = slidingTabStrip.getChildAt(i);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tabView.getLayoutParams();
            params.setMarginStart(spacing);
            params.setMarginEnd(spacing);
            tabView.requestLayout();
        }
    }
}