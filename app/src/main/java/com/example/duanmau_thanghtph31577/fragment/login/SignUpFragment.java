package com.example.duanmau_thanghtph31577.fragment.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.controller.AccountDao;
import com.example.duanmau_thanghtph31577.databinding.FragmentSignUpBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpFragment extends Fragment {
    FragmentSignUpBinding binding;
    String edUserName, edPassword, edEmail, edname;
    AccountDao accountDao;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        accountDao = new AccountDao(getContext());
        listener();

        return binding.getRoot();
    }
    private void listener() {
        binding.tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new LoginFragment()).commit();
            }
        });
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }
    private void validate() {
        edname = binding.edName.getText().toString().trim();
        edUserName = binding.edUserName.getText().toString().trim();
        edEmail = binding.edEmail.getText().toString().trim();
        edPassword = binding.edPassword.getText().toString().trim();
        if (TextUtils.isEmpty(edname)){
            binding.edName.setError("Bạn cần nhập đủ thông tin");
            binding.edName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(edUserName)){
            binding.edUserName.setError("Bạn cần nhập đủ thông tin");
            binding.edUserName.requestFocus();
            return;
        }
        boolean check = accountDao.checkUsername(edUserName);

        if (TextUtils.isEmpty(edEmail)){
            binding.edEmail.setError("Bạn cần nhập đủ thông tin");
            binding.edEmail.requestFocus();
            return;
        }
        if (!validateEmail(edEmail)){
            binding.edEmail.setError("Vui lòng nhập đúng định dạng email");
            binding.edEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(edPassword)){
            binding.edPassword.setError("Bạn cần nhập đủ thông tin");
            binding.edPassword.requestFocus();
            return;
        }
        if (check) {
            binding.edUserName.setError("Tên Đang nhập không khả dụng");
        }else {
            dangKy();
        }


    }
    private void dangKy() {
        accountDao.register(edUserName, edPassword, edname, edEmail);
        Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new LoginFragment()).commit();
    }

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean validateEmail(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}