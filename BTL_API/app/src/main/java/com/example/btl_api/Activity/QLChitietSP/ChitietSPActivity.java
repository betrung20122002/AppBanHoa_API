package com.example.btl_api.Activity.QLChitietSP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Activity.GiaoDienQLActivity;
import com.example.btl_api.Activity.QLLoaiSP.LoaiActivity;
import com.example.btl_api.Activity.QLSanPham.SanPhamActivity;
import com.example.btl_api.Adapter.ChiTietSP_Adapter;
import com.example.btl_api.Adapter.SanPham_Adapter;
import com.example.btl_api.Model.Category;
import com.example.btl_api.Model.ProductDetails;
import com.example.btl_api.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChitietSPActivity extends AppCompatActivity {
    private EditText timkiem;
    private ImageButton them,cn;
    private ImageView ImageView_OnBack;
    ArrayList<ProductDetails> productDetailsArrayList=new ArrayList<>();
    private RecyclerView chitietsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_spactivity);
        AnhXa();
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChitietSPActivity.this, GiaoDienQLActivity.class));
                finish();
            }
        });
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChiTietSP_Adapter loaiSP=new ChiTietSP_Adapter(productDetailsArrayList,ChitietSPActivity.this);
                chitietsp.setAdapter(loaiSP);
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChitietSPActivity.this, MainActivityThemChiTietSP.class));
            }
        });
        APIInterface.API.GetProductDetail().enqueue(new Callback<List<ProductDetails>>() {
            @Override
            public void onResponse(Call<List<ProductDetails>> call, Response<List<ProductDetails>> response) {
                productDetailsArrayList= (ArrayList<ProductDetails>) response.body();
                chitietsp.setLayoutManager(new LinearLayoutManager(ChitietSPActivity.this,RecyclerView.VERTICAL,false));
                ChiTietSP_Adapter chiTietSP_adapter=new ChiTietSP_Adapter(productDetailsArrayList,ChitietSPActivity.this);
                chitietsp.setAdapter(chiTietSP_adapter);
            }

            @Override
            public void onFailure(Call<List<ProductDetails>> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        chitietsp=findViewById(R.id.chitietsp);
        ImageView_OnBack=findViewById(R.id.ImageView_OnBack);
        timkiem=findViewById(R.id.timkiem);
        them=findViewById(R.id.them);
        cn=findViewById(R.id.cn);
    }
}