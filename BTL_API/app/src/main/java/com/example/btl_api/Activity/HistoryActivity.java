package com.example.btl_api.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Activity.QLSanPham.SanPhamActivity;
import com.example.btl_api.Adapter.Post_Adapter;
import com.example.btl_api.Adapter.Product_Adapter;
import com.example.btl_api.Adapter.SanPham_Adapter;
import com.example.btl_api.Model.Category;
import com.example.btl_api.Model.Product;
import com.example.btl_api.Model.Tintuc;
import com.example.btl_api.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    FloatingActionButton shopping;
    RecyclerView recyclerView;
    ArrayList<Tintuc> tintucArrayList=new ArrayList<>();
    Post_Adapter post_adapter;
    LinearLayout homeBtn,homeBtn3,homeBtn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        AnhXa();
        getData();
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HistoryActivity.this, MainActivity.class));
            }
        });
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HistoryActivity.this, ShoppingActivity.class));
            }
        });
        homeBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HistoryActivity.this, NotificationActivity.class));
            }
        });
        homeBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HistoryActivity.this, SettingActivity.class));
            }
        });
    }

    private void getData() {
        APIInterface.API.GetTinTuc().enqueue(new Callback<List<Tintuc>>() {
            @Override
            public void onResponse(Call<List<Tintuc>> call, Response<List<Tintuc>> response) {
                if(response.isSuccessful()){
                    tintucArrayList = (ArrayList<Tintuc>) response.body();
                    post_adapter =new Post_Adapter(tintucArrayList, HistoryActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this,RecyclerView.VERTICAL,false));
                    recyclerView.setAdapter(post_adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Tintuc>> call, Throwable t) {
                Toast.makeText(HistoryActivity.this, "API ERRO", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AnhXa() {
        homeBtn = findViewById(R.id.homeBtnn);
        homeBtn3 = findViewById(R.id.homeBtn3);
        homeBtn4 = findViewById(R.id.homeBtn4);
        shopping = findViewById(R.id.shopping);
        recyclerView = findViewById(R.id.recycleview_post);

    }
}