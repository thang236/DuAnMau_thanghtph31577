package com.example.duanmau_thanghtph31577;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.duanmau_thanghtph31577.controller.AccountDao;
import com.example.duanmau_thanghtph31577.databinding.ActivityMainBinding;
import com.example.duanmau_thanghtph31577.databinding.FragmentManHinhChinhBinding;
import com.example.duanmau_thanghtph31577.fragment.ChangePasswordFragment;
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
    AccountDao dao;
    String name;
    String TAG = "zzzzzzzzz";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        dao = new AccountDao(this);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String data = bundle.getString("key");
             name = dao.getNameByUsername(data);
        }


        setContentView(binding.getRoot());
        setNavi();

        NavigationView navigationView = findViewById(R.id.navigationView);
        View headerView = navigationView.getHeaderView(0);

        TextView headerTextView = headerView.findViewById(R.id.tb_name);
        headerTextView.setText("Xin chào " + name);

//        phanQuyen();

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
                    setTitle("Quản lý thành viên");
                    fragment = new QuanLyThanhVienFragment();
                }else if (item.getItemId() == R.id.phieu){
                    setTitle("Quản lý phiếu muợn");
                    fragment = new QuanLyPhieuMuon();
                }else if (item.getItemId() == R.id.thuThu){
                    setTitle("Quản lý thủ thư");
                    fragment = new QuanLyThuThuFragment();
                }else if (item.getItemId() == R.id.thongKe){
                    setTitle("Thống kê");
                    fragment = new ThongKeFragment();
                } else if (item.getItemId() == R.id.matkhau) {
                    setTitle("Đổi mật khẩu");
                    fragment = new ChangePasswordFragment();
                } else if (item.getItemId() == R.id.loguot){
                    Intent loginIntent = new Intent(MainActivity.this, Welcome.class);
                    startActivity(loginIntent);
                    Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();

                binding.drawerLayout.close();
                return true;
            }
        });
        phanQuyen();
    }
    private void phanQuyen() {
        int trangThai;
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                String userName = bundle.getString("key");
                trangThai = dao.getVaiTroByUsername(userName);

                Bundle bundle1 = new Bundle();
                bundle1.putInt("trangThai", trangThai);
                ManHinhChinhFragment fragment = new ManHinhChinhFragment();
                fragment.setArguments(bundle1);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();


                Log.d(TAG, "phanQuyenMan: "+ trangThai);
                if (trangThai == -1) {
                    Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT).show();
                }else if (trangThai == 1){

                    binding.navigationView.getMenu().findItem(R.id.thuThu).setVisible(false);
                    binding.navigationView.getMenu().findItem(R.id.thongKe).setVisible(false);


            }
        }


        }

    }

}