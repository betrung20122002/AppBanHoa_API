package com.example.btl_api.Activity.QLSanPham;

import static com.example.btl_api.RetrofitClient.dia;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
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
import com.example.btl_api.Adapter.Category_Adapter;
import com.example.btl_api.Model.Category;
import com.example.btl_api.Model.Product;
import com.example.btl_api.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityThemSP extends AppCompatActivity {
    Dialog dialog;
    Spinner masptk;
    String ten="";
    ArrayList<String> dsmasp = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    List<Category> list=new ArrayList<Category>();
    int ma;
    EditText editTextmsp, editTextttsp, editTextluotban, editTextluotxem, editTextmotasanpham, editTextlinkanh;
    ImageView ImageView_OnBack;
    Button btnthem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_sp);
        AnhXa();
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityThemSP.this, SanPhamActivity.class));
                finish();
            }
        });
        APIInterface.API.Get_Category().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> categories = response.body();
                list= categories;
                if (categories != null) {
                    for (Category category : categories) {
                        String nameCategory = category.getNameCategory();
                        dsmasp.add(nameCategory);
                    }
                    if (arrayAdapter == null) {
                        arrayAdapter = new ArrayAdapter<>(MainActivityThemSP.this, android.R.layout.simple_spinner_item, dsmasp);
                        masptk.setAdapter(arrayAdapter);
                    } else {
                        arrayAdapter.notifyDataSetChanged(); // Thông báo cho adapter rằng dữ liệu đã thay đổi
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                // Xử lý khi gặp lỗi
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
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                dialog.show();
                for(int i=0; i<list.size();i++){
                    if(list.get(i).getNameCategory().contains(ten)){
                        ma=list.get(i).getId_Category();
                    }
                }
                String ten = editTextttsp.getText().toString().trim();
                String luotban = editTextluotban.getText().toString().trim();
                String luotxem = editTextluotxem.getText().toString().trim();
                String mota = editTextmotasanpham.getText().toString().trim();
                String linkanh = editTextlinkanh.getText().toString().trim();
                if (ten.isEmpty() || luotban.isEmpty() || luotxem.isEmpty() || mota.isEmpty() || linkanh.isEmpty()) {
                    dialog.dismiss();
                    Toast.makeText(MainActivityThemSP.this, "Vui lòng không để trống thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    Product product = new Product(0, ma, Integer.parseInt(luotban), Integer.parseInt(luotxem), ten, mota, linkanh, java.time.LocalDateTime.now() + "");
                    APIInterface.API.PostProduct(product).enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
                            if (response.isSuccessful()) {
                                dialog.dismiss();
                                Toast.makeText(MainActivityThemSP.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                dialog.dismiss();
                                Toast.makeText(MainActivityThemSP.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {
                            // Xử lý khi gặp lỗi
                        }
                    });
                }
            }

        });
    }

    private void AnhXa() {
        editTextmsp = findViewById(R.id.editTextmsp);
        editTextttsp = findViewById(R.id.editTextttsp);
        editTextluotban = findViewById(R.id.editTextluotban);
        editTextluotxem = findViewById(R.id.editTextluotxem);
        editTextmotasanpham = findViewById(R.id.editTextmotasanpham);
        editTextlinkanh = findViewById(R.id.editTextlinkanh);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        btnthem = findViewById(R.id.btnthem);
        masptk = findViewById(R.id.masptk);
        dialog = new Dialog(MainActivityThemSP.this);
        dia(dialog);
    }
}