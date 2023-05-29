package com.example.btl_api.Activity.QLSanPham;

import static com.example.btl_api.RetrofitClient.dia;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.btl_api.Adapter.SanPham_Adapter.Id_product;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Activity.QLLoaiSP.MainActivitySuaLoaiSP;
import com.example.btl_api.Model.Product;
import com.example.btl_api.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivitySuaSP extends AppCompatActivity {
    Dialog dialog;
    EditText editTextmsp,editTextttsp,editTextluotban,editTextluotxem,editTextmotasanpham,editTextlinkanh,editTextngaynhap;
    ImageView ImageView_OnBack;
    Button btnsua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sua_sp);
        AnhXa();
        getData();
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivitySuaSP.this, SanPhamActivity.class));
                finish();
            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                Product product  = new Product(Id_product,Integer.parseInt(editTextmsp.getText().toString()),Integer.parseInt(editTextluotban.getText().toString()),Integer.parseInt(editTextluotxem.getText().toString()),
                        editTextttsp.getText().toString(),editTextmotasanpham.getText().toString(),editTextlinkanh.getText().toString(),
                        editTextngaynhap.getText().toString());
                APIInterface.API.PutProduct(Id_product,product).enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        if (response.isSuccessful()) {
                            dialog.dismiss();
                            Toast.makeText(MainActivitySuaSP.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void getData() {
        APIInterface.API.getchitiet(Id_product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                editTextmsp.setText(response.body().getId_danhmuc()+"");
                editTextttsp.setText(response.body().getNameProduct());
                editTextluotban.setText(response.body().getSales()+"");
                editTextluotxem.setText(response.body().getViews()+"");
                editTextmotasanpham.setText(response.body().getContent());
                editTextlinkanh.setText(response.body().getImagelinks());
                editTextngaynhap.setText(response.body().getJoinDate());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        editTextmsp = findViewById(R.id.editTextmsp);
        editTextttsp = findViewById(R.id.editTextttsp);
        editTextluotban = findViewById(R.id.editTextluotban);
        editTextngaynhap = findViewById(R.id.editTextngaynhap);
        editTextluotxem = findViewById(R.id.editTextluotxem);
        editTextmotasanpham = findViewById(R.id.editTextmotasanpham);
        editTextlinkanh = findViewById(R.id.editTextlinkanh);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        btnsua = findViewById(R.id.btnsua);
        dialog = new Dialog(MainActivitySuaSP.this);
        dia(dialog);
    }
}