package com.example.duanmau_thanghtph31577.fragment.quanlyphieumuon;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.adapter.LoaiSachAdapter;
import com.example.duanmau_thanghtph31577.adapter.PhieuAdapter;
import com.example.duanmau_thanghtph31577.controller.PhieuDao;
import com.example.duanmau_thanghtph31577.databinding.FragmentTongPhieuBinding;
import com.example.duanmau_thanghtph31577.fragment.quanlysach.SuaSachFragment;
import com.example.duanmau_thanghtph31577.model.PhieuModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class TongPhieuFragment extends Fragment implements PhieuAdapter.ChucNanginterfacePhieu{
    FragmentTongPhieuBinding binding;
    ArrayList<PhieuModel> arrayList;
    PhieuDao dao;
    PhieuAdapter adapter;
    PhieuAdapter.ChucNanginterfacePhieu chucNanginterfacePhieu;
    int trangThaic = 0;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTongPhieuBinding.inflate(inflater, container, false);
        dao = new PhieuDao(getContext());
        chucNanginterfacePhieu = this;
        loadDataFromSQL();
        refresh();



        return binding.getRoot();
    }

    private void  refresh() {
        binding.swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDataFromSQL();
                binding.swiperefreshlayout.setRefreshing(false);
            }
        });
    }

    private void loadDataFromSQL() {
        arrayList = dao.getListALL();
        chinhTrangThai();
        arrayList = dao.getListALL();
        adapter = new PhieuAdapter(getContext(), arrayList, chucNanginterfacePhieu);
        binding.rcvPm.setAdapter(adapter);

    }
    private void  chinhTrangThai() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime(); // Ép kiểu Calendar thành Date

            // Định dạng cho chuỗi ngày tháng
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (int i = 0; i < arrayList.size(); i++) {
            try {
                // Ép kiểu chuỗi thành đối tượng Date
                Date date = dateFormat.parse(arrayList.get(i).getNgayTra());
                if (currentDate.compareTo(date) >0 ) {
                    if (arrayList.get(i).getTrangThai() != 1 ) {
                        arrayList.get(i).setTrangThai(2);
                        Log.e("TAG", "chinhTrangThai: " + i );
                        dao.updateTrangThaiTraSachchinh(arrayList.get(i).getId(), 2);
                    }

                }else {
                    if (arrayList.get(i).getTrangThai() != 1 ) {
                        dao.updateTrangThaiTraSachchinh(arrayList.get(i).getId(), 0);
                    }

                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void traSach(int id) {
        dao.updateTrangThaiTraSachchinh(id, 1);
        Toast.makeText(getContext(), "Trả sách thành công", Toast.LENGTH_SHORT).show();
        loadDataFromSQL();
    }

    @Override
    public void update(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        SuaPhieuFragment suaPhieuFragment = new SuaPhieuFragment();
        suaPhieuFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, suaPhieuFragment).addToBackStack(null).commit();
        loadDataFromSQL();

    }

    @Override
    public void delete(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Bạn có chắc muốn xóa không ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dao.removePhieu(id);
                Toast.makeText(getContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                loadDataFromSQL();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "Bạn chọn không xóa", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();

            }
        });
        builder.show();

    }


}