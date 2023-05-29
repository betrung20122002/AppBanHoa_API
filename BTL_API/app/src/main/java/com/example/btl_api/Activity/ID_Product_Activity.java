package com.example.btl_api.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.btl_api.Adapter.Category_Adapter.Id_Category;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Adapter.Product_Adapter;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.Model.Product;
import com.example.btl_api.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ID_Product_Activity extends AppCompatActivity {
    ImageView image;
    Product_Adapter product_adapter;
    ArrayList<Product> productsArrayList = new ArrayList<>();
    RecyclerView recy_loaiSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_product);
        AnhXa();
        getData();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ID_Product_Activity.this, MainActivity.class));
            }
        });
    }

    private void getData() {
        APIInterface.API.GetId_Product(Id_Category).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productsArrayList = (ArrayList<Product>) response.body();
                product_adapter =new Product_Adapter(productsArrayList,ID_Product_Activity.this);
                recy_loaiSP.setLayoutManager(new LinearLayoutManager(ID_Product_Activity.this,RecyclerView.VERTICAL,false));
                recy_loaiSP.setAdapter(product_adapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        image = findViewById(R.id.image);
        recy_loaiSP = findViewById(R.id.recy_loaiSP);
    }
}