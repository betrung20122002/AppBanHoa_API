package com.example.btl_api.Activity.QLLoaiSP;

import static com.example.btl_api.RetrofitClient.dia;
import static com.example.btl_api.RetrofitClient.dia;
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
import com.example.btl_api.Adapter.LoaiSP;
import com.example.btl_api.Model.Category;
import com.example.btl_api.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoaiActivity extends AppCompatActivity {
    private EditText timkiem;
    private ImageButton them,cn;
    ArrayList<Category>Category_arr=new ArrayList<>();
    private ImageView ImageView_OnBack;
    private RecyclerView loai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai);
        AnhXa();
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoaiActivity.this, GiaoDienQLActivity.class));
                finish();
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(LoaiActivity.this, MainActivityThemLoaiSP.class));
            }
        });
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSP loaiSP=new LoaiSP(Category_arr,LoaiActivity.this);
                loai.setAdapter(loaiSP);
            }
        });

        APIInterface.API.Get_Category().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Category_arr= (ArrayList<Category>) response.body();
                loai.setLayoutManager(new LinearLayoutManager(LoaiActivity.this,RecyclerView.VERTICAL,false));
                LoaiSP category_adapter=new LoaiSP(Category_arr,LoaiActivity.this);
                loai.setAdapter(category_adapter);
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });

    }

    private void AnhXa() {
        loai=findViewById(R.id.loaisp);
        ImageView_OnBack=findViewById(R.id.ImageView_OnBack);
        timkiem=findViewById(R.id.timkiem);
        them=findViewById(R.id.them);
        cn=findViewById(R.id.cn);
    }
}