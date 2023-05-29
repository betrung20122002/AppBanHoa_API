package com.example.btl_api.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.R;
import com.example.btl_api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.example.btl_api.RetrofitClient.dia;
public class LoginActivity extends AppCompatActivity {
   private EditText edtname,edtpass;
    Button btnlogin;
    TextView register;
    public static int Id_Account = 0;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.
                        this, RegisterActivity.class));
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                String email1=edtname.getText().toString().trim();
                String mk1=edtpass.getText().toString().trim();
                if (email1.isEmpty()){
                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    APIInterface.API.kiemtra(email1,mk1).enqueue(new Callback<List<Accounts>>() {
                         @Override
                        public void onResponse(Call<List<Accounts>> call, Response<List<Accounts>> response) {
                            if (response.isSuccessful()){
                                if(email1.equals("admin")&&mk1.equals("admin")){
                                    dialog.dismiss();
                                    Id_Account = response.body().get(0).getId_Account();
                                    Intent intent=new Intent(LoginActivity.this,GiaoDienQLActivity.class);
                                startActivity(intent);startActivity(intent);
                                }else {
                                    dialog.dismiss();
                                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intent);startActivity(intent);
                                    Id_Account = response.body().get(0).getId_Account();
                                }
                            }else {
                                dialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Tài khoản mật khẩu ko tồn tại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Accounts>> call, Throwable t) {
                            dialog.dismiss();
                            Log.e("Lỗi đăng nhập",""+t);
                        }
                    });
                }
            }
        });
    }

    private void AnhXa() {
        edtname = findViewById(R.id.editTextEmail);
        edtpass = findViewById(R.id.editTextPassword);
        btnlogin = findViewById(R.id.buttonLoginn);
        register = findViewById(R.id.register);
        dialog=new Dialog(LoginActivity.this);
        dia(dialog);
    }
}