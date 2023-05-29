package com.example.btl_api.Activity.ThongKe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.R;
import com.skydoves.progressview.ProgressView;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QDThongKeActivity extends AppCompatActivity {
    private Button btnShow,thongketongdondaban,thongketongthunhap,theothang;
    Integer tongdonhang;
    Integer soluong;
    Integer okk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qdthong_ke);
        thongketongdondaban = findViewById(R.id.okkkkkk);
        btnShow = findViewById(R.id.nnnnn);
        theothang = findViewById(R.id.theothang);
        theothang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThongKeTheoThang();
            }
        });
        getData();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        APIInterface.API.GetThongKeTheoNgay(dayOfMonth,month+1,year).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    okk = response.body();

                } else {
                    // Xử lý lỗi nếu có
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(QDThongKeActivity.this, "ERRO", Toast.LENGTH_SHORT).show();
            }
        });
        thongketongthunhap = findViewById(R.id.thongketongthunhap);
        thongketongdondaban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QDThongKeActivity.this);
                builder.setMessage("Thống kê số lượng sản phẩm đã bán :" + soluong +"sản phẩm")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.create();
                builder.show();
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(QDThongKeActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int yearr, int monthn, int dayOfMonthn) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(QDThongKeActivity.this);
                                builder.setTitle("Thống kê sản phẩm : " +"  " +String.format("%d/%d/%d", dayOfMonthn, monthn + 1, yearr )+ "  "+okk +"VNĐ");   // Đặt tiêu đề cho dialog
                                builder.setCancelable(false);   // Đặt dialog không thể huỷ bỏ bằng phím Back
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Xử lý sự kiện khi người dùng nhấn vào nút OK
                                    }
                                });
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
        thongketongthunhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QDThongKeActivity.this);
                builder.setMessage("Thống kê tổng thu nhập  :"+ tongdonhang + "VNĐ")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.create();
                builder.show();
            }

        });
    }

    private void ThongKeTheoThang() {
        final Dialog dialog=new Dialog(QDThongKeActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_dialog_thongke);
        Window window=dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
        WindowManager.LayoutParams windowAttributes=window.getAttributes();
        windowAttributes.gravity= Gravity.CENTER;
        window.setAttributes(windowAttributes);
        dialog.setCancelable(true);
        //
        TextView tv1=dialog.findViewById(R.id.tv1);
        TextView tv2=dialog.findViewById(R.id.tv2);
        TextView tv3=dialog.findViewById(R.id.tv3);
        TextView tv4=dialog.findViewById(R.id.tv4);
        TextView tv5=dialog.findViewById(R.id.tv5);

        ProgressView pro1=dialog.findViewById(R.id.pro1);
        ProgressView pro2=dialog.findViewById(R.id.pro2);
        ProgressView pro3=dialog.findViewById(R.id.pro3);
        ProgressView pro4=dialog.findViewById(R.id.pro4);
        ProgressView pro5=dialog.findViewById(R.id.pro5);

        TextView thang1=dialog.findViewById(R.id.thang1);
        TextView thang2=dialog.findViewById(R.id.thang2);
        TextView thang3=dialog.findViewById(R.id.thang3);
        TextView thang4=dialog.findViewById(R.id.thang4);
        TextView thang5=dialog.findViewById(R.id.thang5);

        pro1.setProgress(100);
        pro2.setProgress(20);
        pro3.setProgress(30);
        pro5.setProgress(20);
        pro4.setProgress(10);

        ProgressDialog progressDialog = new ProgressDialog(QDThongKeActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        APIInterface.API.getThongKe(5).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    String responseData = response.body();
                    String[] kq = responseData.split("/");
                    pro1.setProgress(Float.parseFloat(kq[1]));
                    pro2.setProgress(Float.parseFloat(kq[2]));
                    pro3.setProgress(Float.parseFloat(kq[3]));
                    pro4.setProgress(Float.parseFloat(kq[4]));
                    pro5.setProgress(Float.parseFloat(kq[5]));

                    tv1.setText(kq[1] + " VNĐ");
                    tv2.setText(kq[2] + " VNĐ");
                    tv3.setText(kq[3] + " VNĐ");
                    tv4.setText(kq[4] + " VNĐ");
                    tv5.setText(kq[5] + " VNĐ");

                    thang1.setText("Tháng 5");
                    thang2.setText("Tháng 4");
                    thang3.setText("Tháng 3");
                    thang4.setText("Tháng 2");
                    thang5.setText("Tháng 1");
                } else {
                    // Xử lý lỗi ở đây nếu có
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
        dialog.show();

    }

    private void getData() {
        APIInterface.API.Get_ThongKeTongThuNhap().enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                     tongdonhang = response.body();
                } else {
                    // Xử lý lỗi nếu có
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
        APIInterface.API.Get_ThongKeHangDaBan().enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    soluong = response.body();

                } else {
                    // Xử lý lỗi nếu có
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }
}
