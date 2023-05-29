package com.example.btl_api.Activity.QLBaiDang;

import static com.example.btl_api.Activity.LoginActivity.Id_Account;
import static com.example.btl_api.RetrofitClient.dia;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Activity.QLChitietSP.MainActivityThemChiTietSP;
import com.example.btl_api.Activity.QLLoaiSP.LoaiActivity;
import com.example.btl_api.Activity.QLLoaiSP.MainActivityThemLoaiSP;
import com.example.btl_api.Model.Tintuc;
import com.example.btl_api.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityDangBai extends AppCompatActivity {
    ImageView close;
    EditText image_added,description;
    Dialog dialog;
    TextView post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_bai);
        AnhXa();
        post.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                dialog.show();
                String linkanh = image_added.getText().toString().trim();
                String noidung =description.getText().toString().trim();
                Tintuc tintuc = new Tintuc(0,Id_Account,0,0,linkanh,noidung,java.time.LocalDateTime.now()+"");
                APIInterface.API.PostTinTuc(tintuc).enqueue(new Callback<Tintuc>() {
                    @Override
                    public void onResponse(Call<Tintuc> call, Response<Tintuc> response) {
                        if (response.isSuccessful()) {
                            dialog.dismiss();
                            Toast.makeText(MainActivityDangBai.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            dialog.dismiss();
                            Toast.makeText(MainActivityDangBai.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Tintuc> call, Throwable t) {

                    }
                });
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityDangBai.this, BaiDangActivity.class));
                finish();
            }
        });
    }

    private void AnhXa() {
        close = findViewById(R.id.close);
        post = findViewById(R.id.post);
        image_added = findViewById(R.id.image_added);
        description = findViewById(R.id.description);
        dialog = new Dialog(MainActivityDangBai.this);
        dia(dialog);
    }
}