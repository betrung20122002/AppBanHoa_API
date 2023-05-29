package com.example.btl_api.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Adapter.Product_Adapter;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.Model.Product;
import com.example.btl_api.Model.ProductDetails;
import com.example.btl_api.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    ImageView ImageView_OnBack;
    RecyclerView recy_1;
    EditText edt_timkiem;
    EditText min,max;
    Button timkiemkhoanggia;
    TextView tb;
    List<Product>arrayList ;
    Product_Adapter arrayListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        AnhXa();
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this, MainActivity.class));
            }
        });
        timkiemkhoanggia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int minn = Integer.parseInt(min.getText().toString().trim());
                int maxx = Integer.parseInt(max.getText().toString().trim());
                APIInterface.API.Get_timkiem_khoanggia(minn,maxx).enqueue(new Callback<List<ProductDetails>>() {
                    @Override
                    public void onResponse(Call<List<ProductDetails>> call, Response<List<ProductDetails>> response) {
                        if(response.isSuccessful()){
                            APIInterface.API.getchitiet(response.body().get(0).getId_product()).enqueue(new Callback<Product>() {
                                @Override
                                public void onResponse(Call<Product> call, Response<Product> response) {
                                    if (response.isSuccessful()){
                                        tb.setVisibility(View.GONE);
                                        arrayList.clear();
                                        arrayList.add(response.body());
                                        recy_1.setLayoutManager(new GridLayoutManager(SearchActivity.this,2,RecyclerView.HORIZONTAL,false));
                                        arrayListAdapter =new Product_Adapter((ArrayList<Product>) arrayList,SearchActivity.this);
                                        recy_1.setAdapter(arrayListAdapter);
                                        recy_1.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                arrayListAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }else {
                                        arrayList.clear();
                                        arrayListAdapter=new Product_Adapter((ArrayList<Product>) arrayList,SearchActivity.this);
                                        recy_1.setAdapter(arrayListAdapter);
                                        recy_1.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                arrayListAdapter.notifyDataSetChanged();
                                            }
                                        });

                                        tb.setVisibility(View.VISIBLE);
                                    }
                                }

                                @Override
                                public void onFailure(Call<Product> call, Throwable t) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProductDetails>> call, Throwable t) {

                    }
                });
            }
        });
        edt_timkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getdata(edt_timkiem.getText().toString().trim());
            }


        });
    }
    private void getdata(String tikiem) {
        APIInterface.API.GetSearchProduct(tikiem).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()){
                    tb.setVisibility(View.GONE);
                    arrayList.clear();
                    arrayList= (ArrayList<Product>) response.body();
                    recy_1.setLayoutManager(new GridLayoutManager(SearchActivity.this,2,RecyclerView.HORIZONTAL,false));
                    arrayListAdapter =new Product_Adapter((ArrayList<Product>) arrayList,SearchActivity.this);
                    recy_1.setAdapter(arrayListAdapter);
                    recy_1.post(new Runnable() {
                        @Override
                        public void run() {
                            arrayListAdapter.notifyDataSetChanged();
                        }
                    });
                }else {
                    arrayList.clear();
                    arrayListAdapter=new Product_Adapter((ArrayList<Product>) arrayList,SearchActivity.this);
                    recy_1.setAdapter(arrayListAdapter);
                    recy_1.post(new Runnable() {
                        @Override
                        public void run() {
                            arrayListAdapter.notifyDataSetChanged();
                        }
                    });

                    tb.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }
    private void AnhXa() {
        ImageView_OnBack = findViewById(R.id.image);
        recy_1 = findViewById(R.id.recy_1);
        edt_timkiem= findViewById(R.id.timkiem);
        tb= findViewById(R.id.tb);
        arrayList = new ArrayList<>();
        min = findViewById(R.id.min);
        max = findViewById(R.id.max);
        timkiemkhoanggia = findViewById(R.id.timkiemkhoanggia);
    }
}