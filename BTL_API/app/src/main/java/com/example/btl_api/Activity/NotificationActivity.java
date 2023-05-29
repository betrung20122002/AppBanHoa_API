package com.example.btl_api.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static com.example.btl_api.Activity.LoginActivity.Id_Account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Model.Notify;
import com.example.btl_api.Adapter.Notify_Adapter;
import com.example.btl_api.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {
    private RecyclerView RecyclerView_Notify;
    FloatingActionButton shopping;
    LinearLayout homeBtn1,homeBtnn,homeBtn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        AnhXa();
        getData();
        homeBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NotificationActivity.this, HistoryActivity.class));
            }
        });
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NotificationActivity.this, ShoppingActivity.class));
            }
        });
        homeBtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NotificationActivity.this, MainActivity.class));
            }
        });
        homeBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NotificationActivity.this, SettingActivity.class));
            }
        });
    }

    private void getData() {
        APIInterface.API.GetNotifys(Id_Account).enqueue(new Callback<List<Notify>>() {
            @Override
            public void onResponse(Call<List<Notify>> call, Response<List<Notify>> response) {
                Notify_Adapter notify_adapterBUS = new Notify_Adapter(response.body(), NotificationActivity.this);
                RecyclerView_Notify.setLayoutManager(new LinearLayoutManager(NotificationActivity.this));
                RecyclerView_Notify.setAdapter(notify_adapterBUS);
            }

            @Override
            public void onFailure(Call<List<Notify>> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        RecyclerView_Notify = findViewById(R.id.RecyclerView_Notify);
        homeBtn1 = findViewById(R.id.homeBtn1);
        homeBtnn = findViewById(R.id.homeBtnn);
        homeBtn4 = findViewById(R.id.homeBtn4);
        shopping = findViewById(R.id.shopping);
    }
}