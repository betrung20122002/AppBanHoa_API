package com.example.btl_api.Activity.QLLoaiSP;

import static com.example.btl_api.RetrofitClient.dia;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Activity.LoginActivity;
import com.example.btl_api.Activity.RegisterActivity;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.Model.Category;
import com.example.btl_api.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityThemLoaiSP extends AppCompatActivity {
    private ImageView  ImageView_OnBack;
    private EditText editTextmsp, editTextPicture, Ghichu;
    private Button btnthem;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_loai_sp);
        AnhXa();
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityThemLoaiSP.this,LoaiActivity.class));
                finish();
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                dialog.show();
                String ten = editTextmsp.getText().toString().trim();
                String anh = editTextPicture.getText().toString().trim();
                String ghichu = Ghichu.getText().toString().trim();
                if (ten.isEmpty() && anh.isEmpty() && ghichu.isEmpty()) {
                    dialog.dismiss();
                    Toast.makeText(MainActivityThemLoaiSP.this, "Vui lòng ko để trống thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    Category category = new Category(0,ten,anh,java.time.LocalDateTime.now()+"",ghichu);
                    APIInterface.API.PostCategory(category).enqueue(new Callback<Category>() {
                        @Override
                        public void onResponse(Call<Category> call, Response<Category> response) {
                            if (response.isSuccessful()) {
                                dialog.dismiss();
                                Toast.makeText(MainActivityThemLoaiSP.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                dialog.dismiss();
                                Toast.makeText(MainActivityThemLoaiSP.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Category> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    private void AnhXa() {
        editTextmsp = findViewById(R.id.editTextttsp);
        editTextPicture = findViewById(R.id.editTextPicture);
        Ghichu = findViewById(R.id.Ghichu);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        btnthem = findViewById(R.id.btnthem);
        dialog = new Dialog(MainActivityThemLoaiSP.this);
        dia(dialog);
    }
}