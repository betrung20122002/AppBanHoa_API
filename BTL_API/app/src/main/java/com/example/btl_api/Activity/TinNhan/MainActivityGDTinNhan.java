package com.example.btl_api.Activity.TinNhan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Adapter.Messeger_Adapter;
import com.example.btl_api.Adapter.UserAdaper;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.Model.Messege;
import com.example.btl_api.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityGDTinNhan extends AppCompatActivity {
    RecyclerView mainUserRecyclerView;
    UserAdaper messeger_adapter;
    ArrayList<Accounts> messegeArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gdtin_nhan);
        mainUserRecyclerView = findViewById(R.id.mainUserRecyclerView);
        APIInterface.API.GetAccount().enqueue(new Callback<List<Accounts>>() {
            @Override
            public void onResponse(Call<List<Accounts>> call, Response<List<Accounts>> response) {
                messegeArrayList= (ArrayList<Accounts>) response.body();
                mainUserRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivityGDTinNhan.this,RecyclerView.VERTICAL,false));
                messeger_adapter=new UserAdaper(MainActivityGDTinNhan.this,messegeArrayList);
                mainUserRecyclerView.setAdapter(messeger_adapter);
            }

            @Override
            public void onFailure(Call<List<Accounts>> call, Throwable t) {

            }
        });
    }
}