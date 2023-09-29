package com.example.duanmau_thanghtph31577.fragment.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duanmau_thanghtph31577.MainActivity;
import com.example.duanmau_thanghtph31577.controller.AccountDao;
import com.example.duanmau_thanghtph31577.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    String userName, Password;
    AccountDao accountDao;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        accountDao = new AccountDao(getContext());
        listener();
        return binding.getRoot();
    }
    private void listener() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }
    private void validate() {
        userName = binding.edUsername.getText().toString().trim();
        Password = binding.edPassword.getText().toString().trim();
        if (TextUtils.isEmpty(userName)){
            binding.edUsername.setError("Bạn cần nhập tài khoản");
            binding.edUsername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Password)){
            binding.edPassword.setError("Bạn cần nhập mật khẩu");
            binding.edPassword.requestFocus();
            return;
        }else if (accountDao.login(userName, Password) == 1){
            Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), MainActivity.class));
        }else {
            Toast.makeText(getContext(), "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
        }
    }
}