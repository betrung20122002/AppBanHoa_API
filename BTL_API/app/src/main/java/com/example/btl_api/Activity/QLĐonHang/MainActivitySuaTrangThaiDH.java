package com.example.btl_api.Activity.QLĐonHang;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.btl_api.Adapter.ChinhTrangThai_Adapter.Id_Order;
import static com.example.btl_api.RetrofitClient.dia;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.btl_api.API.APIInterface;

import com.example.btl_api.Activity.QLSanPham.MainActivitySuaSP;
import com.example.btl_api.Model.Product;
import com.example.btl_api.Model.ProductDetails;
import com.example.btl_api.Model.order_;
import com.example.btl_api.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivitySuaTrangThaiDH extends AppCompatActivity {
    EditText editTextttsp,mataikhoan,machitietsp,soluong,tongtien,tinhan,banktien,datetime,note;
    ImageView ImageView_OnBack;
    Button btnsua;
    Dialog dialog;
    LinearLayout okk;
    ArrayAdapter arrayAdapter;
    int stt;
    String tinhtrang="";
    Spinner spinner;
    List<String> list= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sua_trang_thai_dh);
        AnhXa();
        getData();
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivitySuaTrangThaiDH.this, DonHangActivity.class));
                finish();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tinhtrang=spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                if(tinhtrang.contains("Đang xác nhận")){
                    stt=0;
                }else if(tinhtrang.contains("Đang giao hàng")){
                    stt=1;
                }else if(tinhtrang.contains("Đã giao")){
                    stt=1+1;
                }else if(tinhtrang.contains("Hủy đơn")){
                    stt=3;
                }
                order_ order = new order_(Id_Order,Integer.parseInt(mataikhoan.getText().toString()),Integer.parseInt(machitietsp.getText().toString()),
                        Integer.parseInt(soluong.getText().toString()),Integer.parseInt(tongtien.getText().toString()),stt,
                        tinhan.getText().toString(),banktien.getText().toString(),
                        datetime.getText().toString(),note.getText().toString());
                APIInterface.API.PutOder(Id_Order,order).enqueue(new Callback<order_>() {
                    @Override
                    public void onResponse(Call<order_> call, Response<order_> response) {
                        if (response.isSuccessful()) {
                            dialog.dismiss();
                            Toast.makeText(MainActivitySuaTrangThaiDH.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<order_> call, Throwable t) {

                    }
                });
            }
        });

    }

    private void getData() {
        APIInterface.API.GetOrder_id(Id_Order).enqueue(new Callback<order_>() {
            @Override
            public void onResponse(Call<order_> call, Response<order_> response) {
                list.add("Đang xác nhận");
                list.add("Đang giao hàng");
                list.add("Đã giao");
                list.add("Hủy đơn");
                arrayAdapter= new ArrayAdapter(MainActivitySuaTrangThaiDH.this, android.R.layout.simple_spinner_item,list);
                spinner.setAdapter(arrayAdapter);
                mataikhoan.setText(response.body().getId_Account()+"");
                machitietsp.setText(response.body().getId_productdetails()+"");
                soluong.setText(response.body().getQuantity()+"");
                tongtien.setText(response.body().getTotalMoney()+"");
                tinhan.setText(response.body().getMessage());
                note.setText(response.body().getNotes());
                datetime.setText(response.body().getNgayDat());
                banktien.setText(response.body().getPaymentMethod());
            }

            @Override
            public void onFailure(Call<order_> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        editTextttsp = findViewById(R.id.editTextttsp);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        btnsua = findViewById(R.id.btnsua);
        mataikhoan = findViewById(R.id.mataikhoan);
        machitietsp = findViewById(R.id.machitietsp);
        soluong = findViewById(R.id.soluong);
        tongtien = findViewById(R.id.tongtien);
        tinhan = findViewById(R.id.tinhan);
        banktien= findViewById(R.id.banktien);
        spinner = findViewById(R.id.masptk);
        datetime = findViewById(R.id.datetime);
        note = findViewById(R.id.note);
        dialog = new Dialog(MainActivitySuaTrangThaiDH.this);
        dia(dialog);
    }
}