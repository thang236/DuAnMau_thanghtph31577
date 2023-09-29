package com.example.duanmau_thanghtph31577;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.duanmau_thanghtph31577.databinding.ActivityMainBinding;
import com.example.duanmau_thanghtph31577.fragment.ManHinhChinhFragment;
import com.example.duanmau_thanghtph31577.fragment.quanlyloaisach.QuanLyLoaiSachFragment;
import com.example.duanmau_thanghtph31577.fragment.quanlyphieumuon.QuanLyPhieuMuon;
import com.example.duanmau_thanghtph31577.fragment.quanlysach.QuanLySachFragment;
import com.example.duanmau_thanghtph31577.fragment.quanlythanhvien.QuanLyThanhVienFragment;
import com.example.duanmau_thanghtph31577.fragment.quanlythuthu.QuanLyThuThuFragment;
import com.example.duanmau_thanghtph31577.fragment.thongke.ThongKeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setNavi();

        setTitle("Trang chủ");
    }
    private void setNavi(){
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                MainActivity.this, binding.drawerLayout, binding.toolbar, 0,0
        );
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        binding.drawerLayout.addDrawerListener(drawerToggle);
        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = new Fragment();

                if (item.getItemId() == R.id.main){
                    setTitle("Trang chủ");
                    fragment = new ManHinhChinhFragment();

                } else if (item.getItemId() == R.id.loaiSach) {
                    setTitle("Quản lý loại sách");
                    fragment = new QuanLyLoaiSachFragment();
                }else if (item.getItemId() == R.id.sach){
                    setTitle("Quản lý sách");
                    fragment = new QuanLySachFragment();
                }else if (item.getItemId() == R.id.thanhVien){
                    fragment = new QuanLyThanhVienFragment();
                }else if (item.getItemId() == R.id.phieu){
                    fragment = new QuanLyPhieuMuon();
                }else if (item.getItemId() == R.id.thuThu){
                    fragment = new QuanLyThuThuFragment();
                }else if (item.getItemId() == R.id.thongKe){
                    fragment = new ThongKeFragment();
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
                binding.drawerLayout.close();
                return true;
            }
        });
    }
}