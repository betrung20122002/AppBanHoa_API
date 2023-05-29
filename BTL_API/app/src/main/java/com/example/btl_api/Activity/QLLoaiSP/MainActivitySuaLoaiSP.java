package com.example.btl_api.Activity.QLLoaiSP;
import static com.example.btl_api.RetrofitClient.dia;
import static com.example.btl_api.Adapter.LoaiSP.Id_Category;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Activity.RegisterActivity;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.Model.Category;
import com.example.btl_api.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivitySuaLoaiSP extends AppCompatActivity {
    private ImageView  ImageView_OnBack;
    private EditText editTextmsp, editTextPicture, Ghichu,Time;
    private Button btnsua;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sua_loai_sp);
        AnhXa();
        getData();
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivitySuaLoaiSP.this,LoaiActivity.class));
                finish();
            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                if (editTextmsp.getText().toString().isEmpty()||editTextPicture.getText().toString().isEmpty()||
                Ghichu.getText().toString().isEmpty()||Time.getText().toString().isEmpty()) {
                    dialog.dismiss();
                    Toast.makeText(MainActivitySuaLoaiSP.this, "Vui lòng ko để trống thông tin", Toast.LENGTH_SHORT).show();
                }
                Category category = new Category(Id_Category,editTextmsp.getText().toString(),editTextPicture.getText().toString(),Time.getText().toString(),Ghichu.getText().toString());
                APIInterface.API.PutCategory(Id_Category,category).enqueue(new Callback<Category>() {
                    @Override
                    public void onResponse(Call<Category> call, Response<Category> response) {
                        if (response.isSuccessful()) {
                            dialog.dismiss();
                            Toast.makeText(MainActivitySuaLoaiSP.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Category> call, Throwable t) {

                    }
                });
            }
        });

    }

    private void getData() {
        APIInterface.API.Get_Category_id(Id_Category).enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                editTextmsp.setText(response.body().getNameCategory());
                editTextPicture.setText(response.body().getPictureCategory());
                Ghichu.setText(response.body().getNote());
                Time.setText(response.body().getJoinDate());
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        editTextmsp = findViewById(R.id.editTextttsp);
        editTextPicture = findViewById(R.id.editTextPicture);
        Ghichu = findViewById(R.id.Ghichu);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        Time = findViewById(R.id.Time);
        btnsua = findViewById(R.id.btnsua);
        dialog = new Dialog(MainActivitySuaLoaiSP.this);
        dia(dialog);
    }
}