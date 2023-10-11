package com.example.duanmau_thanghtph31577.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duanmau_thanghtph31577.MainActivity;
import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.controller.AccountDao;
import com.example.duanmau_thanghtph31577.databinding.FragmentChangePasswordBinding;


public class ChangePasswordFragment extends Fragment {
    FragmentChangePasswordBinding binding;
    AccountDao accountDao;
    String username, passwordOld, passwordNew1, passwordNew2, passwordData;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false);
        accountDao = new AccountDao(getContext());
        listener();
        return binding.getRoot();
    }

    private void listener() {
        binding.btnHoanTat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void validate() {
        username = binding.edUsername.getText().toString().trim();
        passwordOld = binding.edPassOld.getText().toString().trim();
        passwordNew1 = binding.edPassNew.getText().toString().trim();
        passwordNew2 = binding.edPassNew2.getText().toString().trim();
        passwordData = accountDao.getPasswordByUsername(username);

        if (username.isEmpty() || passwordOld.isEmpty() || passwordNew1.isEmpty() || passwordNew2.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng nhập đủ dữ liệu", Toast.LENGTH_SHORT).show();

            Log.e("zzzzzz", "validate: "+ passwordData );
        } else if (passwordData == null) {
            Toast.makeText(getContext(), "Sai tài khoản", Toast.LENGTH_SHORT).show();
            binding.edUsername.setError("Tài khoản chưa đúng");

        } else if (!passwordOld.equals(passwordData)) {
            Toast.makeText(getContext(), "Sai mật khẩu", Toast.LENGTH_SHORT).show();
            binding.edPassOld.setError("Mật khẩu không đúng");

        } else if (!passwordNew1.equals(passwordNew2)) {
            Toast.makeText(getContext(), "Mật khẩu nhập lại không đúng", Toast.LENGTH_SHORT).show();
            binding.edPassNew.setError("Mật kẩu hoặc mật khẩu hoặc mật khẩu xác nhận lại đã sai");
            binding.edPassNew2.setError("Mật kẩu hoặc mật khẩu hoặc mật khẩu xác nhận lại đã sai");

        }else {
            accountDao.changePassword(username, passwordNew1);
            Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
            binding.edUsername.setText("");
            binding.edPassOld.setText("");
            binding.edPassNew.setText("");
            binding.edPassNew2.setText("");
        }


    }

//    private void dialogdoiMK() {
//        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
//        LayoutInflater inflater = ((Activity) this).getLayoutInflater();
//        DialogDoimkBinding binding1 = DialogDoimkBinding.inflate(inflater);
//        View view = binding1.getRoot();
//        builder.setView(view);
//        Dialog dialog = builder.create();
//        dialog.show();
//
//        binding1.btnHoanTat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                username = binding1.edUsername.getText().toString().trim();
//                passwordOld = binding1.edPassOld.getText().toString().trim();
//                passwordNew1 = binding1.edPassNew.getText().toString().trim();
//                passwordNew2 = binding1.edPassNew2.getText().toString().trim();
//
//                if (username.isEmpty() || passwordOld.isEmpty() || passwordNew1.isEmpty() || passwordNew2.isEmpty()) {
//                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ dữ liệu", Toast.LENGTH_SHORT).show();
//                    passwordData = accountDao.getPasswordByUsername(username);
//                } else if (passwordData ==null) {
//                    Toast.makeText(MainActivity.this, "Sai tài khoản", Toast.LENGTH_SHORT).show();
//                    binding1.edUsername.setError("Tài khoản chưa đúng");
//                } else if (passwordOld != passwordData) {
//                    Toast.makeText(MainActivity.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
//                    binding1.edPassOld.setError("Mật khẩu không đúng");
//                } else if (passwordNew1 != passwordNew2) {
//                    Toast.makeText(MainActivity.this, "Mật khẩu nhập lại không đúng", Toast.LENGTH_SHORT).show();
//                    binding1.edPassNew.setError("Mật kẩu hoặc mật khẩu hoặc mật khẩu xác nhận lại đã sai");
//                    binding1.edPassNew2.setError("Mật kẩu hoặc mật khẩu hoặc mật khẩu xác nhận lại đã sai");
//                }
//            }
//        });
//
//    }
}