package com.example.duanmau_thanghtph31577.fragment.quanlysach;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duanmau_thanghtph31577.R;
import com.example.duanmau_thanghtph31577.controller.SachDAO;
import com.example.duanmau_thanghtph31577.databinding.FragmentSuaSachBinding;
import com.example.duanmau_thanghtph31577.model.SachModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class SuaSachFragment extends Fragment {
    FragmentSuaSachBinding binding;
    ArrayList<SachModel> arrayList;
    SachDAO sachDAO;
    private String tenSach, loaiSach, tg, moTa, imgSach, soLuong, gia;
    private int trangThai;
    private Uri img_uri;
    public static final String TAG = "SuaSachFragment";
    private int id;
    private String img;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSuaSachBinding.inflate(inflater, container, false);
        sachDAO = new SachDAO(getContext());
        arrayList = new ArrayList<>();
        loadDataFromSQL();
        listener();
        // Inflate the layout for this fragment

        return binding.getRoot();
    }


    private void loadDataFromSQL() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
            SachModel sachModel = sachDAO.getByID(id);
            binding.edGia.setText("" + sachModel.getGia());
            binding.edLoaiSach.setText(sachModel.getLoaiSach());
            binding.edTenSach.setText(sachModel.getTenSach());
            binding.edSoLuong.setText("" + sachModel.getSoLuong());
            binding.edMoTa.setText(sachModel.getMoTa());
            binding.edTG.setText(sachModel.getTacGia());
            if (sachModel.getTrangThai()==1){
                binding.swTrangThai.setChecked(true);
            }else {
                binding.swTrangThai.setChecked(false);
            }
            img = sachModel.getImgSach();


            Log.e("TAG", "onBindViewHolder: " + img);

            try {
                Picasso.get().load(img).placeholder(R.drawable.ic_camera).error(R.drawable.ic_camera)
                        .into(binding.cardPickerCamera);


            } catch (Exception e) {
                Log.e("PicassoError", "Error loading image: " + e.getMessage());
                Toast.makeText(getContext(), "Không thể load ảnh", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getContext(), "Đã xảy ra lỗi vui lòng thử lai", Toast.LENGTH_SHORT).show();
        }

    }

    private void listener() {
        binding.edLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaglogChonLoaiSach();
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
        binding.cardPickerCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogChonAnh();
            }
        });
    }
    private void showDialogChonAnh() {
        String[] options = {"Máy Ảnh", "Thư Viện"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Chọn").setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    // người dùng chọn máy ảnh
                    pickMayAnhFuntion();
                } else if (i == 1) {
                    // người dùng chọn thư viện
                    pickThuVienFuntion();
                }
            }
        }).show();
    }

    private void pickMayAnhFuntion() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Máy ảnh");
        contentValues.put(MediaStore.Images.Media.TITLE, "Máy ảnh");
        img_uri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, img_uri);
        cameraActivityResult.launch(intent);
    }

    private void pickThuVienFuntion() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galleryActivityResult.launch(intent);
    }

    private ActivityResultLauncher<Intent> cameraActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent intent = result.getData();

                try {
                    Picasso.get().load(img_uri).placeholder(R.drawable.ic_camera).error(R.drawable.ic_camera).into(binding.cardPickerCamera);
                } catch (Exception e) {
                    Log.d(TAG, "onActivityResult: Không thể load ảnh " + e.getMessage());
                }
            }
        }
    });

    private ActivityResultLauncher<Intent> galleryActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent intent = result.getData();

                img_uri = intent.getData();

                try {
                    Picasso.get().load(img_uri).placeholder(R.drawable.ic_camera).error(R.drawable.ic_camera).into(binding.cardPickerCamera);
                } catch (Exception e) {
                    Log.d(TAG, "onActivityResult: Không thể load ảnh " + e.getMessage());
                }
            }
        }
    });

    private void validate() {
        tenSach = binding.edTenSach.getText().toString().trim();
        soLuong = binding.edSoLuong.getText().toString().trim();
        gia = binding.edGia.getText().toString().trim();
        loaiSach = binding.edLoaiSach.getText().toString().trim();
        tg = binding.edGia.getText().toString().trim();
        moTa = binding.edMoTa.getText().toString().trim();
        if (binding.swTrangThai.isChecked()) {
            trangThai = 1;
        } else {
            trangThai = 0;
        }

        if (tenSach.isEmpty() || soLuong.isEmpty() || gia.isEmpty() || loaiSach.isEmpty() || tg.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng nhập dủ dữ liệu các trường", Toast.LENGTH_SHORT).show();
        } else {
            updateData();
        }

    }


    private void updateData() {


        SachModel objUpdate = new SachModel();
        objUpdate.setGia(Integer.parseInt(gia));

        objUpdate.setLoaiSach(loaiSach);
        objUpdate.setTenSach(tenSach);
        objUpdate.setMoTa(moTa);
        objUpdate.setSoLuong(Integer.parseInt(soLuong));
        objUpdate.setTacGia(tg);
        objUpdate.setTrangThai(trangThai);
        objUpdate.setId(id);

        if (img_uri != null) {
            imgSach = img_uri.toString();
            objUpdate.setImgSach(imgSach);
        } else {
            objUpdate.setImgSach(img);
        }

        sachDAO.updateSach(objUpdate);
        Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().popBackStack();

    }
    private void diaglogChonLoaiSach() {
        String[] type =sachDAO.getLoaiSachByTrangThai1().toArray(new String[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Chọn loại sách");
        builder.setItems(type, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                binding.edLoaiSach.setText(type[i]);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}