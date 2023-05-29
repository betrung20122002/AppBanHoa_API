package com.example.btl_api.Activity.QLChitietSP;

import static com.example.btl_api.RetrofitClient.dia;
import static com.example.btl_api.Adapter.ChiTietSP_Adapter.Id_ProductDetails;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Model.ProductDetails;
import com.example.btl_api.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivitySuaChiTietSP extends AppCompatActivity {
    EditText editTextmsp,editTextGia,editTextGiaChinh,editTextSize,editTextLinkanh1,editTextlinkanh2,editTextlinkanh3;
    Button btnsua;
    Dialog dialog;
    ImageView ImageView_OnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sua_chi_tiet_sp);
        AnhXa();
        getData();
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivitySuaChiTietSP.this, ChitietSPActivity.class));
                finish();
            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                if (editTextmsp.getText().toString().isEmpty()||editTextGia.getText().toString().isEmpty()||
                        editTextGiaChinh.getText().toString().isEmpty()|| editTextSize.getText().toString().isEmpty()||editTextLinkanh1.getText().toString().isEmpty()
                || editTextlinkanh2.getText().toString().isEmpty()||editTextlinkanh3.getText().toString().isEmpty()) {
                    dialog.dismiss();
                    Toast.makeText(MainActivitySuaChiTietSP.this, "Vui lòng ko để trống thông tin", Toast.LENGTH_SHORT).show();
                }
                ProductDetails productDetails = new ProductDetails(Id_ProductDetails,Integer.parseInt(editTextmsp.getText().toString()),Integer.parseInt(editTextGia.getText().toString()),
                        Integer.parseInt(editTextGiaChinh.getText().toString()), editTextSize.getText().toString(),editTextLinkanh1.getText().toString(),
                        editTextlinkanh2.getText().toString(),editTextlinkanh3.getText().toString());
                APIInterface.API.PutProductDetail(Id_ProductDetails,productDetails).enqueue(new Callback<ProductDetails>() {
                    @Override
                    public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
                        if (response.isSuccessful()) {
                            dialog.dismiss();
                            Toast.makeText(MainActivitySuaChiTietSP.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductDetails> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void getData() {
        APIInterface.API.GetProductDetail(Id_ProductDetails).enqueue(new Callback<ProductDetails>() {
            @Override
            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
                editTextmsp.setText(response.body().getId_product()+"");
                editTextGia.setText(response.body().getPrice()+"");
                editTextSize.setText(response.body().getSize());
                editTextGiaChinh.setText(response.body().getPromotionalprice()+"");
                editTextLinkanh1.setText(response.body().getPicture_1());
                editTextlinkanh2.setText(response.body().getPicture_2());
                editTextlinkanh3.setText(response.body().getPicture_3());
            }

            @Override
            public void onFailure(Call<ProductDetails> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        editTextmsp = findViewById(R.id.editTextmsp);
        editTextGia = findViewById(R.id.editTextGia);
        editTextGiaChinh = findViewById(R.id.editTextGiaChinh);
        editTextSize = findViewById(R.id.editTextSize);
        editTextLinkanh1 = findViewById(R.id.editTextLinkanh1);
        editTextlinkanh2 = findViewById(R.id.editTextlinkanh2);
        editTextlinkanh3 = findViewById(R.id.editTextlinkanh3);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        btnsua = findViewById(R.id.btnsua);
        dialog = new Dialog(MainActivitySuaChiTietSP.this);
        dia(dialog);
    }
}