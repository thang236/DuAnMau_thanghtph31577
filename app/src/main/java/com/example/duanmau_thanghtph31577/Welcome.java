package com.example.duanmau_thanghtph31577;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.duanmau_thanghtph31577.databinding.ActivityWelcomeBinding;
import com.example.duanmau_thanghtph31577.fragment.login.WelcomeFragment;

public class Welcome extends AppCompatActivity {
    ActivityWelcomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment();
    }
    private void replaceFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new WelcomeFragment());
        fragmentTransaction.commit();
    }
}