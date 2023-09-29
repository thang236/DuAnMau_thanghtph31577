package com.example.duanmau_thanghtph31577.fragment.quanlythuthu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duanmau_thanghtph31577.controller.ThuThuDao;
import com.example.duanmau_thanghtph31577.databinding.FragmentSuaThuThuBinding;
import com.example.duanmau_thanghtph31577.model.ThuThuModel;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SuaThuThuFragment extends Fragment {
    FragmentSuaThuThuBinding binding;
    ArrayList<ThuThuModel> arrayList;
    ThuThuDao dao;
    String tenTV, soDT, email, diaChi;
    int id;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSuaThuThuBinding.inflate(inflater, container, false);
        dao = new ThuThuDao(getContext());

        loadDataFromSQL();
        listener();


        return binding.getRoot();
    }


    private void loadDataFromSQL() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
            ThuThuModel thuThuModel = dao.getByID(id);
            binding.edTenTV.setText(thuThuModel.getTenTT());
            binding.edEmail.setText(thuThuModel.getEmail());
            binding.edSdt.setText("0"+thuThuModel.getSoDT());
            binding.edDiaChi.setText(thuThuModel.getDiaChi());

        }
        else {
            Toast.makeText(getContext(), "Đã có lỗi gì đó vui lòng thử lại", Toast.LENGTH_SHORT).show();
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
            updateTT();
        }
    }
    private void updateTT() {
        ThuThuModel objUpdate = new ThuThuModel();

        objUpdate.setIdTT(id);
        objUpdate.setSoDT(Integer.parseInt(soDT));
        objUpdate.setTenTT(tenTV);
        objUpdate.setEmail(email);
        objUpdate.setDiaChi(diaChi);

        dao.updateTT(objUpdate);
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