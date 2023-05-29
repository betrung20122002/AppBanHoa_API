package com.example.btl_api.Activity.QLĐonHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Activity.GiaoDienQLActivity;
import com.example.btl_api.Activity.QLChitietSP.ChitietSPActivity;
import com.example.btl_api.Activity.QLChitietSP.MainActivityThemChiTietSP;
import com.example.btl_api.Adapter.ChiTietSP_Adapter;
import com.example.btl_api.Adapter.ChinhTrangThai_Adapter;
import com.example.btl_api.Model.ProductDetails;
import com.example.btl_api.Model.order_;
import com.example.btl_api.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonHangActivity extends AppCompatActivity {
    ImageView ImageView_OnBack;
    RecyclerView loaisp;
    ArrayList<order_> orderArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        AnhXa();
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonHangActivity.this, GiaoDienQLActivity.class));
                finish();
            }
        });
        APIInterface.API.Getorder().enqueue(new Callback<List<order_>>() {
            @Override
            public void onResponse(Call<List<order_>> call, Response<List<order_>> response) {
                orderArrayList= (ArrayList<order_>) response.body();
                loaisp.setLayoutManager(new LinearLayoutManager(DonHangActivity.this,RecyclerView.VERTICAL,false));
                ChinhTrangThai_Adapter chinhTrangThai_adapter=new ChinhTrangThai_Adapter(orderArrayList,DonHangActivity.this);
                loaisp.setAdapter(chinhTrangThai_adapter);
            }

            @Override
            public void onFailure(Call<List<order_>> call, Throwable t) {
                Toast.makeText(DonHangActivity.this, "ERRO", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AnhXa() {
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        loaisp = findViewById(R.id.loaisp);
    }
}