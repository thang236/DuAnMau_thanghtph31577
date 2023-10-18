package com.example.duanmau_thanghtph31577.fragment.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau_thanghtph31577.R;


public class SplashFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                replaceFragment();
            }
        }, 3500);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    private void replaceFragment() {
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new WelcomeFragment()).commit();
    }
}