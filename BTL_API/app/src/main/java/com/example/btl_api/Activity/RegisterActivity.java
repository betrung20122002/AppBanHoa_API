package com.example.btl_api.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.R;
import static com.example.btl_api.RetrofitClient.dia;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private TextView NAME,PASSWORD,EMAIL,dangnhap,editlinkhinhanh;
    ImageView buttonLogin;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_register);
        AnhXa();
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                String email1 = EMAIL.getText().toString().trim();
                String hoten1 = NAME.getText().toString().trim();
                String matkhau = PASSWORD.getText().toString().trim();
                String hinhanh = editlinkhinhanh.getText().toString().trim();
                if (hoten1.isEmpty() && email1.isEmpty()&& matkhau.isEmpty()) {
                    dialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Vui lòng ko để trống thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    Accounts accounts1 = new Accounts(0, hoten1,email1,matkhau,"","","",hinhanh);
                    APIInterface.API.PostAccount(accounts1).enqueue(new Callback<Accounts>() {
                        @Override
                        public void onResponse(Call<Accounts> call, Response<Accounts> response) {
                            if (response.isSuccessful()) {
                                dialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));



                            } else {
                                dialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Accounts> call, Throwable t) {

                        }
                    });
                }
            }
        });
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }





    private void AnhXa() {
        NAME = findViewById(R.id.editTextName);
        EMAIL = findViewById(R.id.editTextEmail);
        PASSWORD = findViewById(R.id.password);
        editlinkhinhanh = findViewById(R.id.editlinkhinhanh);
        dangnhap = findViewById(R.id.dangnhap);
        dialog = new Dialog(RegisterActivity.this);
        buttonLogin = findViewById(R.id.buttonLogin);
        dia(dialog);
    }
}