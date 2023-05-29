package com.example.btl_api.Activity.TinNhan;

import static com.example.btl_api.Activity.LoginActivity.Id_Account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Adapter.Messeger_Adapter;
import com.example.btl_api.Model.Messege;
import com.example.btl_api.Model.Product;
import com.example.btl_api.Model.order_;
import com.example.btl_api.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessegeActivity extends AppCompatActivity {
    ImageView profileimgg;
    TextView recivername;
    RecyclerView msgadpter;
    TextView textmsg;
    CardView sendbtnn;
    Messeger_Adapter messeger_adapter;
    ArrayList<Messege>messegeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messege);
        AnhXa();
        APIInterface.API.GetTinNhan().enqueue(new Callback<List<Messege>>() {
            @Override
            public void onResponse(Call<List<Messege>> call, Response<List<Messege>> response) {
                messegeArrayList= (ArrayList<Messege>) response.body();
                msgadpter.setLayoutManager(new LinearLayoutManager(MessegeActivity.this,RecyclerView.VERTICAL,false));
                messeger_adapter=new Messeger_Adapter(MessegeActivity.this,messegeArrayList);
                msgadpter.setAdapter(messeger_adapter);
            }
            @Override
            public void onFailure(Call<List<Messege>> call, Throwable t) {

            }
        });
                sendbtnn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Text = textmsg.getText().toString().trim();
                        Messege messege = new Messege(0, Id_Account, 2005, Text);
                        APIInterface.API.PostTinNhan(messege).enqueue(new Callback<Product>() {
                            @Override
                            public void onResponse(Call<Product> call, Response<Product> response) {
                                Messeger_Adapter loaiSP = new Messeger_Adapter(MessegeActivity.this, messegeArrayList);
                                msgadpter.setAdapter(loaiSP);
                            }

                            @Override
                            public void onFailure(Call<Product> call, Throwable t) {

                            }
                        });

                    }
                });
    }

    private void AnhXa() {
        profileimgg = findViewById(R.id.profileimgg);
        recivername = findViewById(R.id.recivername);
        msgadpter = findViewById(R.id.msgadpter);
        textmsg = findViewById(R.id.textmsg);
        sendbtnn = findViewById(R.id.sendbtnn);
        messegeArrayList = new ArrayList<>();
    }
}