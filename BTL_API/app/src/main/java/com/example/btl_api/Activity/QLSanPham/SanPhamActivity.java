package com.example.btl_api.Activity.QLSanPham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Activity.GiaoDienQLActivity;
import com.example.btl_api.Activity.MainActivity;
import com.example.btl_api.Activity.QLLoaiSP.LoaiActivity;
import com.example.btl_api.Activity.QLLoaiSP.MainActivityThemLoaiSP;
import com.example.btl_api.Activity.SearchActivity;
import com.example.btl_api.Adapter.LoaiSP;
import com.example.btl_api.Adapter.Product_Adapter;
import com.example.btl_api.Adapter.SanPham_Adapter;
import com.example.btl_api.Model.Category;
import com.example.btl_api.Model.Product;
import com.example.btl_api.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SanPhamActivity extends AppCompatActivity {
    private EditText timkiem;
    private ImageButton them,cn;
    private ImageView ImageView_OnBack;
    private RecyclerView sanpham;
    ArrayList<Product> productsArrayList = new ArrayList<>();
    SanPham_Adapter product_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        AnhXa();
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SanPhamActivity.this, GiaoDienQLActivity.class));
                finish();
            }
        });
        timkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getdata(timkiem.getText().toString().trim());
            }


        });
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham_Adapter  loaiSP=new SanPham_Adapter(productsArrayList,SanPhamActivity.this);
                sanpham.setAdapter(loaiSP);
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SanPhamActivity.this, MainActivityThemSP.class));
            }
        });
        APIInterface.API.Get_Product().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    productsArrayList = (ArrayList<Product>) response.body();
                    product_adapter =new SanPham_Adapter(productsArrayList, SanPhamActivity.this);
                    sanpham.setLayoutManager(new LinearLayoutManager(SanPhamActivity.this,RecyclerView.VERTICAL,false));
                    sanpham.setAdapter(product_adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
    private void getdata(String tikiem) {
        APIInterface.API.GetSearchProduct(tikiem).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()){
                    productsArrayList.clear();
                    productsArrayList= (ArrayList<Product>) response.body();
                    sanpham.setLayoutManager(new GridLayoutManager(SanPhamActivity.this,2,RecyclerView.HORIZONTAL,false));
                    product_adapter =new SanPham_Adapter(productsArrayList, SanPhamActivity.this);
                    sanpham.setAdapter(product_adapter);
                    sanpham.post(new Runnable() {
                        @Override
                        public void run() {
                            product_adapter.notifyDataSetChanged();
                        }
                    });
                }else {
                    productsArrayList.clear();
                    product_adapter =new SanPham_Adapter(productsArrayList, SanPhamActivity.this);
                    sanpham.setAdapter(product_adapter);
                    sanpham.post(new Runnable() {
                        @Override
                        public void run() {
                            product_adapter.notifyDataSetChanged();
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }
    private void AnhXa() {
        sanpham=findViewById(R.id.loaisp);
        ImageView_OnBack=findViewById(R.id.ImageView_OnBack);
        timkiem=findViewById(R.id.timkiem);
        them=findViewById(R.id.them);
        cn=findViewById(R.id.cn);
    }
}