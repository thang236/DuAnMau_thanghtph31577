package com.example.duanmau_thanghtph31577.fragment.quanlythuthu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duanmau_thanghtph31577.controller.AccountDao;
import com.example.duanmau_thanghtph31577.controller.ThuThuDao;
import com.example.duanmau_thanghtph31577.databinding.FragmentThemThuThuBinding;
import com.example.duanmau_thanghtph31577.model.ThuThuModel;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ThemThuThuFragment extends Fragment {
    FragmentThemThuThuBinding binding;
    ArrayList<ThuThuModel> arrayList;
    ThuThuDao thuThuDao;
    AccountDao accountDao;

    String tenTV, soDT, email, diaChi, username;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentThemThuThuBinding.inflate(inflater, container, false);

        thuThuDao = new ThuThuDao(getContext());
        accountDao = new AccountDao(getContext());
        arrayList = new ArrayList<>();

        listener();

        return binding.getRoot();
    }
    private void listener() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        binding.btnHoanTat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void validate() {
        tenTV = binding.edTenTV.getText().toString().trim();
        soDT = binding.edSdt.getText().toString().trim();
        email = binding.edEmail.getText().toString().trim();
        diaChi = binding.edDiaChi.getText().toString().trim();
        username = binding.edUserName.getText().toString().trim();

        boolean check = accountDao.checkUsername(username);

        if (tenTV.isEmpty() || soDT.isEmpty() || email.isEmpty() || diaChi.isEmpty()) {
            Toast.makeText(getContext(), "Hãy điền đủ tất cả các trường", Toast.LENGTH_SHORT).show();
        } else if (!validateEmail(email)) {
            binding.edEmail.setError("Vui lòng nhập đúng định dạng email");
        } else if (check) {
            binding.edSdt.setError("Tài khoản đã có người sử dụng");
        }else {
                saveTT();
                creatAccount();
            }
        }

    private void saveTT() {
        ThuThuModel objNew = new ThuThuModel();
        objNew.setSoDT(Integer.parseInt(soDT));
        objNew.setTenTT(tenTV);
        objNew.setEmail(email);
        objNew.setDiaChi(diaChi);

        thuThuDao.addTT(objNew);
        Toast.makeText(getContext(), "Thêm mới thành công", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().popBackStack();

    }
    private void creatAccount() {
        accountDao.registerNV(username,"1",tenTV,email);
        getActivity().getSupportFragmentManager().popBackStack();
        Toast.makeText(getContext(), "Thêm mới thành công", Toast.LENGTH_SHORT).show();
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


