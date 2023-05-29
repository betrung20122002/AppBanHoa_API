package com.example.btl_api.Activity.QLBaiDang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Activity.GiaoDienQLActivity;
import com.example.btl_api.Activity.HistoryActivity;
import com.example.btl_api.Activity.QLLoaiSP.LoaiActivity;
import com.example.btl_api.Activity.QLLoaiSP.MainActivityThemLoaiSP;
import com.example.btl_api.Adapter.BaiDang_Adapter;
import com.example.btl_api.Adapter.LoaiSP;
import com.example.btl_api.Adapter.Post_Adapter;
import com.example.btl_api.Model.Category;
import com.example.btl_api.Model.Tintuc;
import com.example.btl_api.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiDangActivity extends AppCompatActivity {
    private EditText timkiem;
    private ImageButton them, cn;
    private ImageView ImageView_OnBack;
    private RecyclerView loai;
    ArrayList<Tintuc> tintucArrayList=new ArrayList<>();
    BaiDang_Adapter post_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_dang);
        anhXa();
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BaiDangActivity.this, GiaoDienQLActivity.class));
                finish();
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BaiDangActivity.this, MainActivityDangBai.class));
            }
        });
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaiDang_Adapter baiDang_adapter=new BaiDang_Adapter(tintucArrayList,BaiDangActivity.this);
                loai.setAdapter(baiDang_adapter);
            }
        });
        APIInterface.API.GetTinTuc().enqueue(new Callback<List<Tintuc>>() {
            @Override
            public void onResponse(Call<List<Tintuc>> call, Response<List<Tintuc>> response) {
                if(response.isSuccessful()){
                    tintucArrayList = (ArrayList<Tintuc>) response.body();
                    post_adapter =new BaiDang_Adapter(tintucArrayList, BaiDangActivity.this);
                    loai.setLayoutManager(new LinearLayoutManager(BaiDangActivity.this,RecyclerView.VERTICAL,false));
                    loai.setAdapter(post_adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Tintuc>> call, Throwable t) {
                Toast.makeText(BaiDangActivity.this, "API ERRO", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void anhXa() {
        loai = findViewById(R.id.loaisp);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        timkiem = findViewById(R.id.timkiem);
        them = findViewById(R.id.them);
        cn = findViewById(R.id.cn);
    }
}