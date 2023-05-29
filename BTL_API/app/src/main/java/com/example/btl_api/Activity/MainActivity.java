package com.example.btl_api.Activity;

import static com.example.btl_api.Activity.LoginActivity.Id_Account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Adapter.Category_Adapter;
import com.example.btl_api.Adapter.Product_Adapter;
import com.example.btl_api.Activity.TinNhan.MessegeActivity;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.Model.Category;
import com.example.btl_api.Model.Product;
import com.example.btl_api.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {
    ViewFlipper viewFlipper;
    FloatingActionButton shopping;
    LinearLayout homeBtn1,homeBtn3,homeBtn4;
    TextView TextView_Search,textView8;
    ImageView ImageView_Picture;
    Category_Adapter category_adapter;
    Product_Adapter product_adapter;
    TextView messger;
    public static NumberFormat currencyVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    RecyclerView rcv_displayhome_LoaiSP,rcv_displayhome_SP;
    ArrayList<Category>Category_arr=new ArrayList<>();
    ArrayList<Product> productsArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        ActionViewFlipper();
        getData();
        TextView_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));

            }
        });
        homeBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            }
        });
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShoppingActivity.class));
            }
        });
        homeBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
            }
        });
        messger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MessegeActivity.class));
            }
        });
        homeBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });
    }

    private void getData() {
        APIInterface.API.Get_Category().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Category_arr= (ArrayList<Category>) response.body();
                rcv_displayhome_LoaiSP.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                category_adapter=new Category_Adapter(Category_arr,MainActivity.this);
                rcv_displayhome_LoaiSP.setAdapter(category_adapter);
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
        APIInterface.API.Get_Product().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    productsArrayList = (ArrayList<Product>) response.body();
                    product_adapter =new Product_Adapter(productsArrayList,MainActivity.this);
                    rcv_displayhome_SP.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));
                    rcv_displayhome_SP.setAdapter(product_adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
        APIInterface.API.gettt(Id_Account).enqueue(new Callback<Accounts>() {
            @Override
            public void onResponse(Call<Accounts> call, Response<Accounts> response) {
                if(response.isSuccessful()){
                    Glide.with(getApplicationContext())
                            .load(response.body().getPicture())
                            .error(R.drawable.hoaooo)
                            .into(ImageView_Picture);
                    textView8.setText(response.body().getName());
                }
            }

            @Override
            public void onFailure(Call<Accounts> call, Throwable t) {

            }
        });
    }

    private void ActionViewFlipper() {
        // mảng chứa ảnh cho quảng cáo
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://cdn.thodianhatrang.vn/Media/Posts/hoa-tuoi-nha-trang-5.jpg");
        mangquangcao.add("https://trangvanginan.com/wp-content/uploads/2018/01/cach-ban-hoa-ngay-tet-hieu-qua.jpg");
        mangquangcao.add("https://img5.thuthuatphanmem.vn/uploads/2021/12/02/hinh-anh-sale-50_083653109.jpg");
        mangquangcao.add("https://gotrangtri.vn/wp-content/uploads/2018/08/le-hoi-hoa-ban-5.jpg");

        // lặp gắn ảnh vào imageView
        for (int i = 0; i < mangquangcao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());

            Picasso.get().load(mangquangcao.get(i)).into(imageView);

            // chỉnh ảnh vừa khung quảng cáo
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            // thêm ảnh từ imageview vào flipper
            viewFlipper.addView(imageView);
        }
        // tự động chạy 4s
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        // animation vào ra
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);

        // gọi animation vào flipper
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);
    }

    private void AnhXa() {
        TextView_Search = findViewById(R.id.TextView_Search);
        homeBtn1 = findViewById(R.id.homeBtn1);
        homeBtn3 = findViewById(R.id.homeBtn3);
        homeBtn4 = findViewById(R.id.homeBtn4);
        shopping = findViewById(R.id.shopping);
        textView8 = findViewById(R.id.textView8);
        ImageView_Picture = findViewById(R.id.ImageView_Picture);
        viewFlipper = findViewById(R.id.viewfliper);
        rcv_displayhome_LoaiSP=findViewById(R.id.rcv_displayhome_LoaiSP);
        rcv_displayhome_SP=findViewById(R.id.rcv_displayhome_SP);
        messger = findViewById(R.id.messger);
    }
}