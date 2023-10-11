package com.example.duanmau_thanghtph31577.fragment.quanlyphieumuon;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.controller.PhieuDao;
import com.example.duanmau_thanghtph31577.databinding.FragmentSuaPhieuBinding;
import com.example.duanmau_thanghtph31577.model.PhieuModel;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;


public class SuaPhieuFragment extends Fragment {
    FragmentSuaPhieuBinding binding;
    PhieuDao dao;
    int id;
    PhieuModel object;
    String tenSach, ngayTra, soLuong, thanhVien, giaSach, loaiSach, ngayMuon;
    int price, ngayMuonint, ngayTraint;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSuaPhieuBinding.inflate(inflater, container, false);
        dao = new PhieuDao(getContext());
        // Inflate the layout for this fragment
        loadData();
        listener();

        return binding.getRoot();
    }

    private void loadData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
        }
        object = dao.getPhieuMuonById(id);
        binding.edGia.setText("" + object.getGia());
        binding.edNgayMuon.setText(object.getNgayMuon());
        binding.edTV.setText(object.getTenTV());
        binding.edNgayTra.setText(object.getNgayTra());
        binding.edSoLuong.setText(""+ object.getSoluong());
        binding.edTenSach.setText(object.getTenSach());


    }


    private void listener() {
        binding.edTenSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogChonSach();
            }
        });

        binding.edNgayMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(true);
            }
        });

        binding.edNgayTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(false);
            }
        });
        binding.edTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogChonTV();
            }
        });
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

    private void  openDialogChonTV() {
        String[] thanhVien =dao.getAllTenThanhVien().toArray(new String[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Chọn thành viên");
        builder.setItems(thanhVien, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                binding.edTV.setText(thanhVien[i]);
                price = dao.getGiaByTenSach(thanhVien[i]);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void dialogChonSach() {
        String[] type =dao.getTenSachList().toArray(new String[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Chọn sách");
        builder.setItems(type, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                binding.edTenSach.setText(type[i]);
                price = dao.getGiaByTenSach(type[i]);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    private void showDatePickerDialog(final boolean isStartDate) {
        price = dao.getGiaByTenSach(binding.edTenSach.getText().toString().trim());
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
                            binding.edNgayMuon.setText(selectedDate);
                            ngayMuonint = dayOfMonth;
                        } else {
                            binding.edNgayTra.setText(selectedDate);
                            ngayTraint = dayOfMonth;
                        }
                        Log.d("TAG", "onDateSet: ngay muon va ngay tra " + ngayMuonint + ngayTraint);
                        caculatorTongNgayMuon(ngayMuonint, ngayTraint);

                    }
                }, year, month, day);

        datePickerDialog.show();
    }
    int dayRents = 0;
    int total = 0;



    private void caculatorTongNgayMuon(int ngayMuon, int ngayTra) {
        dayRents = ngayTra - ngayMuon;

        total = (int) (price * dayRents);
        Log.e("TAG", "caculatorTongNgayMuon: " + dayRents + price);

        String priceString = String.valueOf(total);

        // Replace commas with periods (assuming they represent decimal points)
        priceString = priceString.replace(",", ".");

        try {
            double price = Integer.parseInt(priceString);

            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            String formattedPrice = currencyFormatter.format(price);

            binding.edGia.setText(""+total);
        } catch (NumberFormatException e) {
            // Handle the case where the string cannot be parsed to a numerical type
            e.printStackTrace(); // Or log the error, show a message, etc.
        }

    }









    private void validate() {
        tenSach = binding.edTenSach.getText().toString().trim();
        ngayMuon = binding.edNgayMuon.getText().toString().trim();
        ngayTra = binding.edNgayTra.getText().toString().trim();
        soLuong = binding.edSoLuong.getText().toString().trim();
        giaSach = binding.edGia.getText().toString().trim();
        thanhVien = binding.edTV.getText().toString().trim();

        if (TextUtils.isEmpty(tenSach) || TextUtils.isEmpty(ngayTra) || TextUtils.isEmpty(soLuong) || TextUtils.isEmpty(giaSach)
                || TextUtils.isEmpty(thanhVien) || TextUtils.isEmpty(ngayMuon)){
            Toast.makeText(getContext(), "Vui lòng điền đủ các trường", Toast.LENGTH_SHORT).show();
            return;
        }else {
            updateData();
        }
    }

    private void updateData() {
        object.setNgayMuon(ngayMuon);
        object.setNgayTra(ngayTra);
        object.setTenTV(thanhVien);
        object.setGia(Integer.parseInt(giaSach));
        object.setSoluong(Integer.parseInt(soLuong));
        object.setTenSach(tenSach);

        dao.updatePhieuMuon(object);


        getActivity().getSupportFragmentManager().popBackStack();
        ViewPage2Fragment viewPage2Fragment = new ViewPage2Fragment(getActivity());
        Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();

    }




}