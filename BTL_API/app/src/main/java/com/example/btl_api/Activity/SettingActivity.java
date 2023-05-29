package com.example.btl_api.Activity;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Activity.ThongKe.QDThongKeActivity;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.Model.order_;
import com.example.btl_api.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.btl_api.Activity.LoginActivity.Id_Account;
import static com.example.btl_api.Activity.MainActivity.currencyVN;
import static com.example.btl_api.RetrofitClient.dia;
public class SettingActivity extends AppCompatActivity {
    FloatingActionButton shopping;
    LinearLayout homeBtn1,homeBtn3,homeBtnn;
    Dialog dialog;
    ImageView ImageView_Picture;
    Button ImageView_EditAccount;
    Integer tien;
    Integer soluong;
    TextView TextView_Name,TextView_Email,thongketheoAccount;
    RelativeLayout TextView_Order,TextView_Spport,TextView_SendFeedback,TextView_Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        AnhXa();
        getData();
        homeBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, HistoryActivity.class));
            }
        });
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, ShoppingActivity.class));
            }
        });
        homeBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, NotificationActivity.class));
            }
        });
        homeBtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, MainActivity.class));
            }
        });
        TextView_Spport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingActivity.this, "Đại vương ơi đang bảo trì !", Toast.LENGTH_SHORT).show();
            }
        });
        TextView_SendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingActivity.this, "Đại vương ơi đang bảo trì !", Toast.LENGTH_SHORT).show();
            }
        });
        TextView_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDialogOutGame();
            }
        });
        ImageView_EditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, EditAccountActivity.class));
            }
        });
        TextView_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this,OrderActivity.class));
            }
        });
        thongketheoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setTitle("Khách hàng thân thiết");   // Đặt tiêu đề cho dialog
                builder.setMessage("Cảm ơn bạn đã chi tiêu " +tien + "VNĐ " + "và mua "+ soluong + "đơn hàng");  // Đặt nội dung cho dialog
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
        });
    }

    private void getData() {
        APIInterface.API.gettt(Id_Account).enqueue(new Callback<Accounts>() {
            @Override
            public void onResponse(Call<Accounts> call, Response<Accounts> response) {
                if(response.isSuccessful()){
                    Glide.with(getApplicationContext())
                            .load(response.body().getPicture())
                            .error(R.drawable.hoaooo)
                            .into(ImageView_Picture);
                    TextView_Name.setText(response.body().getName());
                    TextView_Email.setText(response.body().getEmail());
                }
            }

            @Override
            public void onFailure(Call<Accounts> call, Throwable t) {

            }
        });
        APIInterface.API.Get_ThongKe_TaiKhoan_SoLuong(Id_Account).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    soluong  = Integer.valueOf(response.body());

                } else {
                    // Xử lý lỗi nếu có
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        APIInterface.API.Get_ThongKe_TaiKhoan_Tien(Id_Account).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    tien  = Integer.valueOf(response.body());

                } else {
                    // Xử lý lỗi nếu có
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setDialogOutGame();
    }

    private void setDialogOutGame() {
        Dialog dialog = new Dialog(SettingActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_stop);
        TextView tvAnswer = (TextView) dialog.findViewById(R.id.tv_dialog_stop);
        tvAnswer.setText("Bạn có muốn thoát không ?");
        Button btnOKStop = (Button) dialog.findViewById(R.id.btn_ok_stop_game);
        Button btnCancelStop = (Button) dialog.findViewById(R.id.btn_cancel_stop_game);
        btnOKStop.setText("OK");
        btnCancelStop.setText("Không");

        btnOKStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, IntroActivity.class));
            }
        });

        btnCancelStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void AnhXa() {
        homeBtn1 = findViewById(R.id.homeBtn1);
        homeBtn3 = findViewById(R.id.homeBtn3);
        homeBtnn = findViewById(R.id.homeBtnn);
        shopping = findViewById(R.id.shopping);
        ImageView_Picture = findViewById(R.id.ImageView_Picture);
        ImageView_EditAccount = findViewById(R.id.ImageView_EditAccount);
        TextView_Order = findViewById(R.id.TextView_Order);
        TextView_Spport = findViewById(R.id.TextView_Spport);
        TextView_SendFeedback = findViewById(R.id.TextView_SendFeedback);
        TextView_Logout = findViewById(R.id.TextView_Logout);
        TextView_Name = findViewById(R.id.TextView_Name);
        TextView_Email = findViewById(R.id.TextView_Email);
        dialog = new Dialog(getApplicationContext());
        dia(dialog);
        thongketheoAccount = findViewById(R.id.thongketheoAccount);
    }
}