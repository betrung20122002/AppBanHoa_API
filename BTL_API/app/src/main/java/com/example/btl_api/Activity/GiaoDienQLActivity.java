package com.example.btl_api.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Activity.QLBaiDang.BaiDangActivity;
import com.example.btl_api.Activity.QLChitietSP.ChitietSPActivity;
import com.example.btl_api.Activity.QLLoaiSP.LoaiActivity;
import com.example.btl_api.Activity.QLSanPham.SanPhamActivity;
import com.example.btl_api.Activity.QLĐonHang.DonHangActivity;
import com.example.btl_api.Activity.ThongKe.QDThongKeActivity;
import com.example.btl_api.Activity.TinNhan.MainActivityGDTinNhan;
import com.example.btl_api.Model.order_;
import com.example.btl_api.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GiaoDienQLActivity extends AppCompatActivity {
    LinearLayout loai,sanpham,donhang,chitietsanpham;
    Button about,messege,txtPer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_qlactivity);
        AnhXa();
        loai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GiaoDienQLActivity.this, LoaiActivity.class));

            }
        });
        sanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GiaoDienQLActivity.this, SanPhamActivity.class));

            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GiaoDienQLActivity.this, BaiDangActivity.class));

            }
        });
        chitietsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GiaoDienQLActivity.this, ChitietSPActivity.class));

            }
        });
        donhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GiaoDienQLActivity.this, DonHangActivity.class));

            }
        });
        txtPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GiaoDienQLActivity.this, QDThongKeActivity.class));

            }
        });
        messege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GiaoDienQLActivity.this, MainActivityGDTinNhan.class));

            }
        });
    }

    private void AnhXa() {
        loai = findViewById(R.id.kienthuc);
        sanpham = findViewById(R.id.tracnghiem);
        donhang = findViewById(R.id.qldonhang);
        chitietsanpham = findViewById(R.id.chitietsanpham);
        about = findViewById(R.id.btnAbout);
        messege = findViewById(R.id.messeger);
        txtPer = findViewById(R.id.txtPer);
    }
    private long time;
    private Toast mtoast;

    @Override
    public void onBackPressed() {
        if (time + 2000 > System.currentTimeMillis()) {
            mtoast.cancel();
            startActivity(new Intent(GiaoDienQLActivity.this,LoginActivity.class));
            finish();
            return;
        } else {
            mtoast = Toast.makeText(GiaoDienQLActivity.this, "Nhấn 2 lần để đồng ý thoát", Toast.LENGTH_SHORT);
            mtoast.show();
        }
        time = System.currentTimeMillis();
    }
}