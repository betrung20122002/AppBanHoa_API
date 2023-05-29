package com.example.btl_api.Activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.bumptech.glide.Glide;
import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.btl_api.Activity.LoginActivity.Id_Account;
import static com.example.btl_api.RetrofitClient.dia;

public class EditAccountActivity extends AppCompatActivity {
ImageView ImageView_OnBack,ImageView_Camera,ImageView_Picture;
TextView EditText_Name,TextView_Email,EditText_JoinDate,EditText_NumberPhone;
EditText EditText_Address;
Button Button_Save;
    Dialog dialog;
    private StorageReference reference ;
    private String AccountName,AccountPhone ,AccountAddress;
    private Uri img_uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        AnhXa();
        getdata();
        ImageView_Camera.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                        Camera(EditAccountActivity.this, view);
                return true;
            }
        });
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditAccountActivity.this, SettingActivity.class));
            }
        });
        Button_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!EditText_Address.getText().toString().isEmpty()) {
                    AccountName = EditText_Name.getText().toString().trim();
                    AccountAddress = EditText_Address.getText().toString().trim();
                    AccountPhone = EditText_NumberPhone.getText().toString().trim();
                }
                    dialog.show();
                    PutAccount_edit(AccountName, AccountPhone,AccountAddress, EditAccountActivity.this,dialog, img_uri,reference);
                }

        });

    }

    private void getdata() {
        APIInterface.API.gettt(Id_Account).enqueue(new Callback<Accounts>() {
            @Override
            public void onResponse(Call<Accounts> call, Response<Accounts> response) {
                if (response.isSuccessful()) {
                    Glide.with(getApplicationContext()).load(response.body().getPicture()).error(R.drawable.hoaooo).into(ImageView_Picture);
                    EditText_Name.setText(response.body().getName());
                    TextView_Email.setText(response.body().getEmail());
                    EditText_NumberPhone.setText(response.body().getNumberphone());
                    EditText_Address.setText(response.body().getAddress());
                    EditText_JoinDate.setText(response.body().getJoinDate());
                }
            }

            @Override
            public void onFailure(Call<Accounts> call, Throwable t) {
            }
        });
        if (EditText_NumberPhone.getText().toString().isEmpty()) {
            EditText_NumberPhone.setHint("Vui lòng thêm số điện thoại!");
        }
        if (EditText_Address.getText().toString().isEmpty()) {
            EditText_Address.setHint("Vui lòng thêm địa chỉ!");
        }
    }


    private void AnhXa() {
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        ImageView_Camera = findViewById(R.id.ImageView_Camera);
        ImageView_Picture = findViewById(R.id.ImageView_Picture);
        EditText_Name = findViewById(R.id.EditText_Name);
        TextView_Email = findViewById(R.id.TextView_Email);
        EditText_JoinDate = findViewById(R.id.EditText_JoinDate);
        EditText_NumberPhone = findViewById(R.id.EditText_NumberPhone);
        EditText_Address = findViewById(R.id.TextView_Address);
        Button_Save = findViewById(R.id.Button_Save);
        dialog=new Dialog(EditAccountActivity.this);
        dia(dialog);
    }
    public static void Camera(Activity activity, View view){
        PopupMenu popupMenu = new PopupMenu(activity, view);
        popupMenu.inflate(R.menu.menu_camera);
        popupMenu.setGravity(Gravity.CENTER);
        popupMenu.setForceShowIcon(true);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_camera:
                        ImagePicker.with(activity)
                                .crop(16f, 16f)
                                .compress(1024)
                                .maxResultSize(1080, 1080)
                                .cameraOnly()
                                .start();
                        return true;
                    case R.id.menu_chon_anh:
                        ImagePicker.with(activity)
                                .crop(16f, 16f)
                                .compress(1024)
                                .maxResultSize(1080, 1080)
                                .galleryOnly()
                                .start();
                        return true;

                }
                return false;
            }
        });
        popupMenu.show();
    }
    public static void PutAccount_edit(String AccountName, String AccountNumberPhone, String AccountAddress, Activity activity, Dialog loadDialog, Uri imageuri, StorageReference reference) {
        APIInterface.API.GetAccount(Id_Account).enqueue(new Callback<Accounts>() {
            @Override
            public void onResponse(Call<Accounts> call, Response<Accounts> response) {
                if (response.isSuccessful()) {

                    if (imageuri != null) {
                        StorageReference storageReference = reference.child(String.valueOf(Id_Account));
                        storageReference.putFile(imageuri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        loadDialog.dismiss();
                                        PutAccount(AddAccount(response.body().getId_Account(), AccountName, response.body().getEmail(),
                                                response.body().getPasswordd(), AccountNumberPhone,
                                                AccountAddress, response.body().getJoinDate(), uri.toString()), activity);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        loadDialog.dismiss();
                                    }
                                });
                            }
                        });

                    } else {
                        loadDialog.dismiss();
                        PutAccount(AddAccount(response.body().getId_Account(), AccountName, response.body().getEmail(),
                                response.body().getPasswordd(), AccountNumberPhone,
                                AccountAddress, response.body().getJoinDate(), response.body().getPicture()), activity);
                    }

                }
            }

            @Override
            public void onFailure(Call<Accounts> call, Throwable t) {
            }
        });

    }
    public static void PutAccount(Accounts account, Activity activity) {
        APIInterface.API.PutAccount(Id_Account, account).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(activity, "Lưu thành công", Toast.LENGTH_SHORT).show();
                    activity.onBackPressed();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }
    public static Accounts AddAccount(int id,String Name,String Email,String Password,String NumberPhone,String Address,String JoinDate,String Picture){
        Accounts account=new Accounts(id,Name,Email,Password,NumberPhone,Address,JoinDate,Picture);
        return account;
    }

}