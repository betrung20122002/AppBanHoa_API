package com.example.btl_api.Activity.QLChitietSP;

import static com.example.btl_api.RetrofitClient.dia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Activity.QLLoaiSP.LoaiActivity;
import com.example.btl_api.Activity.QLLoaiSP.MainActivityThemLoaiSP;
import com.example.btl_api.Activity.QLSanPham.MainActivityThemSP;
import com.example.btl_api.Model.Category;
import com.example.btl_api.Model.Product;
import com.example.btl_api.Model.ProductDetails;
import com.example.btl_api.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityThemChiTietSP extends AppCompatActivity {
    EditText editTextmsp,editTextGia,editTextGiaChinh,editTextSize,editTextLinkanh1,editTextlinkanh2,editTextlinkanh3;
    ImageView ImageView_OnBack;
    Dialog dialog;
    Spinner masptk;
    ArrayList<String> dsmasp=new ArrayList<String>();
    List<Product> list=new ArrayList<Product>();
    String ten="";
    ArrayAdapter<String> arrayAdapter;
    int ma;
    Button btnthem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_chi_tiet_sp);
        AnhXa();
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityThemChiTietSP.this, ChitietSPActivity.class));
                finish();
            }
        });
        APIInterface.API.Get_Product().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> products = response.body();
                list= products;
                if (products != null) {
                    for (Product product : products) {
                        String nameCategory = product.getNameProduct();
                        dsmasp.add(nameCategory);
                    }

                    if (arrayAdapter == null) {
                        arrayAdapter = new ArrayAdapter(MainActivityThemChiTietSP.this, android.R.layout.simple_spinner_item, dsmasp);
                        masptk.setAdapter(arrayAdapter);
                    } else {
                        arrayAdapter.notifyDataSetChanged(); // Thông báo cho adapter rằng dữ liệu đã thay đổi
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
        masptk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ten=masptk.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                for(int i=0; i<list.size();i++){
                    if(list.get(i).getNameProduct().contains(ten)){
                        ma=list.get(i).getId_product();
                    }
                }
                String gia = editTextGia.getText().toString().trim();
                String giachinh = editTextGiaChinh.getText().toString().trim();
                String size = editTextSize.getText().toString().trim();
                String linkanh1 = editTextLinkanh1.getText().toString().trim();
                String linkanh2 = editTextlinkanh2.getText().toString().trim();
                String linkanh3 = editTextlinkanh3.getText().toString().trim();
                if (  gia.isEmpty() && giachinh.isEmpty()||size.isEmpty()||linkanh1.isEmpty()
                ||linkanh2.isEmpty()||linkanh3.isEmpty()) {
                    dialog.dismiss();
                    Toast.makeText(MainActivityThemChiTietSP.this, "Vui lòng ko để trống thông tin", Toast.LENGTH_SHORT).show();
                } else{
                    ProductDetails productDetails = new ProductDetails(0,ma,Integer.parseInt(gia),Integer.parseInt(giachinh),size,linkanh1,linkanh2,linkanh3);
                    APIInterface.API.PostProductDetail(productDetails).enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
                            if (response.isSuccessful()) {
                                dialog.dismiss();
                                Toast.makeText(MainActivityThemChiTietSP.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                dialog.dismiss();
                                Toast.makeText(MainActivityThemChiTietSP.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {

                        }
                    });
                }
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
        masptk = findViewById(R.id.masptk);
        btnthem = findViewById(R.id.btnthem);
        dialog = new Dialog(MainActivityThemChiTietSP.this);
        dia(dialog);
    }
}