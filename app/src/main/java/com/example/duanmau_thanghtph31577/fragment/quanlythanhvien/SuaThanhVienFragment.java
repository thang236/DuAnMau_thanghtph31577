package com.example.duanmau_thanghtph31577.fragment.quanlythanhvien;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duanmau_thanghtph31577.controller.ThanhVienDao;
import com.example.duanmau_thanghtph31577.databinding.FragmentSuaThanhVienBinding;
import com.example.duanmau_thanghtph31577.model.ThanhVienModel;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SuaThanhVienFragment extends Fragment {
    FragmentSuaThanhVienBinding binding;
    ThanhVienDao dao;
    ArrayList<ThanhVienModel> arrayList;
    String tenTV, soDT, email, diaChi;
    int id;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSuaThanhVienBinding.inflate(inflater, container, false);
        dao = new ThanhVienDao(getContext());
        arrayList = new ArrayList<>();
        listener();
        loadDataFromSQL();

        return binding.getRoot();
    }
    private void loadDataFromSQL() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");

            ThanhVienModel thanhVienModel = dao.getByID(id);
            binding.edTenTV.setText(thanhVienModel.getTenTV());
            binding.edEmail.setText(thanhVienModel.getEmail());
            binding.edSdt.setText("" + thanhVienModel.getSoDT());
            binding.edDiaChi.setText(thanhVienModel.getDiaChi());

        }else {
            Toast.makeText(getContext(), "Đã xảy ra lỗi vui lòng thử lai", Toast.LENGTH_SHORT).show();
        }
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

        if (tenTV.isEmpty() || soDT.isEmpty() || email.isEmpty() || diaChi.isEmpty()) {
            Toast.makeText(getContext(), "Hãy điền đủ tất cả các trường", Toast.LENGTH_SHORT).show();
        } else if (!validateEmail(email)) {
            binding.edEmail.setError("Vui lòng nhập đúng định dạng email");
        } else {
            updateTV();
        }
    }


    private void updateTV() {
        ThanhVienModel objUpdate = new ThanhVienModel();

        objUpdate.setId(id);
        objUpdate.setSoDT(Integer.parseInt(soDT));
        objUpdate.setTenTV(tenTV);
        objUpdate.setEmail(email);
        objUpdate.setDiaChi(diaChi);

        dao.updateThanhVien(objUpdate);
        Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().popBackStack();
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