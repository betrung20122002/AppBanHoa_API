package com.example.btl_api.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.btl_api.Order_ViewPager2BUS;
import com.example.btl_api.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class OrderActivity extends AppCompatActivity {
    private ImageView ImageView_OnBack;
    private TabLayout TabLayout_Order;
    private ViewPager2 ViewPager_Order;
    private Order_ViewPager2BUS order_viewPager2BUS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        onclick();
    }

    private void onclick() {
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        TabLayout_Order = findViewById(R.id.TabLayout_Order);
        ViewPager_Order = findViewById(R.id.ViewPager_Order);
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderActivity.this,SettingActivity.class));
            }
        });
        order_viewPager2BUS = new Order_ViewPager2BUS(this);
        ViewPager_Order.setAdapter(order_viewPager2BUS);
        TabLayout_Order.setTabTextColors(this.getResources().getColor(R.color.black),this.getResources().getColor(R.color.night_mode));
        new TabLayoutMediator(TabLayout_Order, ViewPager_Order, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Chờ xác nhận ");
                        break;
                    case 1:
                        tab.setText("Đang giao");
                        break;
                    case 2:
                        tab.setText("Đã giao");
                        break;
                    case 3:
                        tab.setText("Đã hủy");
                        break;
                    default:
                        break;

                }
            }
        }).attach();
    }
}