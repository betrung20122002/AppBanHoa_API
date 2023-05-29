package com.example.btl_api.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.btl_api.R;
import com.google.android.gms.cast.framework.media.ImagePicker;

public class IntroActivity extends AppCompatActivity {
 private ImageView startbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introo);
        startbtn = findViewById(R.id.btn);
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this, WelcomeActivity.class));
            }
        });
    }
}