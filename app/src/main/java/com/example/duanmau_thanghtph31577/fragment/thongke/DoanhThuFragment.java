package com.example.duanmau_thanghtph31577.fragment.thongke;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.controller.ThongKeDao;
import com.example.duanmau_thanghtph31577.databinding.FragmentDoanhThuBinding;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class DoanhThuFragment extends Fragment {
    FragmentDoanhThuBinding binding;
    ThongKeDao dao;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDoanhThuBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        dao = new ThongKeDao(requireContext());

        userClick();

        return binding.getRoot();
    }



    private void userClick() {
            binding.imgTungay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDatePickerDialog(true);
                }
            });

            binding.imgDenngay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDatePickerDialog(false);
                }
            });




            binding.btnCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double doanhthu;
                    doanhthu = dao.thongKeDoanhThuTheoThoiGian(binding.edTungay.getText().toString(), binding.edDenngay.getText().toString());

                    Locale locale = new Locale("nv", "VN");
                    NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
                    String tienfomat = nf.format(doanhthu);
                    Log.e("azzz", "onClick: "+tienfomat );
                    binding.tvHienthikq.setText( tienfomat);
                }
            });
        }

    private void showDatePickerDialog(final boolean isStartDate) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String formattedDay = (dayOfMonth < 10) ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                        String formattedMonth = ((monthOfYear + 1) < 10) ? "0" + (monthOfYear + 1) : String.valueOf(monthOfYear + 1);

                        String selectedDate = formattedDay + "-" + formattedMonth + "-" + year;

                        if (isStartDate) {
                            binding.edTungay.setText(selectedDate);

                        } else {
                            binding.edDenngay.setText(selectedDate);
                        }
                    }
                }, year, month, day);

        datePickerDialog.show();
    }


}