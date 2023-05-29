package com.example.btl_api.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import static com.example.btl_api.Activity.LoginActivity.Id_Account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import static com.example.btl_api.Activity.MainActivity.currencyVN;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Adapter.Carts_Adapter;
import com.example.btl_api.Adapter.Product_Adapter;
import com.example.btl_api.Model.Carts;
import com.example.btl_api.Model.Product;
import com.example.btl_api.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingActivity extends AppCompatActivity {
    LinearLayout homeBtnn,homeBtn1,homeBtn3,homeBtn4;
    RecyclerView RecyclerView_Cart,RecyclerView_ProductLike;
    TextView sl, tongtien,demso,TextView_TotalMoney;
    public static LinearLayout LinearLayout_BuyProduct, LinearLayout_Cart;
    private Button Button_BuyProduct, Button_Order;
    private SwipeRefreshLayout SwipeRefreshLayoutCart;
    public ShoppingActivity() {
        // Required empty public constructor
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        AnhXa();
        getdata();
        getcart_GetTotalMoney();
        onClik();

        homeBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShoppingActivity.this, HistoryActivity.class));
            }
        });
        homeBtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShoppingActivity.this, MainActivity.class));
            }
        });
        homeBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShoppingActivity.this, NotificationActivity.class));
            }
        });
        homeBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShoppingActivity.this, SettingActivity.class));
            }
        });
        Button_BuyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShoppingActivity.this,MainActivity.class));
            }
        });
        Button_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShoppingActivity.this,CheckCartActivity.class));
            }
        });
    }

    private void onClik() {
        SwipeRefreshLayoutCart.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getdata();
                getcart_GetTotalMoney();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SwipeRefreshLayoutCart.setRefreshing(false);
                    }
                },1000);
            }
        });
    }

    private void getcart_GetTotalMoney() {

        APIInterface.API.GetTotalMoney(Id_Account).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    TextView_TotalMoney.setText(currencyVN.format(Integer.parseInt(response.body())));
                } else {
                    TextView_TotalMoney.setText(currencyVN.format(0));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void getdata() {
        APIInterface.API.GetCartAccpunt(Id_Account).enqueue(new Callback<List<Carts>>() {
            @Override
            public void onResponse(Call<List<Carts>> call, Response<List<Carts>> response) {
                if (response.isSuccessful()) {
                    LinearLayout_Cart.setVisibility(View.VISIBLE);
                    LinearLayout_BuyProduct.setVisibility(View.GONE);
                    demso.setText(response.body().size() + "");

                    RecyclerView_Cart.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    Carts_Adapter cart_adapterBUS = new Carts_Adapter(response.body(),ShoppingActivity.this );
                    RecyclerView_Cart.setAdapter(cart_adapterBUS);
                } else {
                    LinearLayout_BuyProduct.setVisibility(View.VISIBLE);
                    LinearLayout_Cart.setVisibility(View.GONE);
                    demso.setText("0");
                }
            }
            @Override
            public void onFailure(Call<List<Carts>> call, Throwable t) {
            }
        });
        APIInterface.API.Get_Product().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){

                    Product_Adapter product_adapter =new Product_Adapter((ArrayList<Product>) response.body(), (Context) ShoppingActivity.this);
                    RecyclerView_ProductLike.setLayoutManager(new LinearLayoutManager(ShoppingActivity.this,RecyclerView.VERTICAL,false));
                    RecyclerView_ProductLike.setAdapter(product_adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
    private void AnhXa() {
        homeBtn1 = findViewById(R.id.homeBtn1);
        homeBtn3 = findViewById(R.id.homeBtn3);
        homeBtn4 = findViewById(R.id.homeBtn4);
        homeBtnn = findViewById(R.id.homeBtnn);
        RecyclerView_Cart = findViewById(R.id.RecyclerView_Cart);
        sl = findViewById(R.id.sl);
        tongtien = findViewById(R.id.tongtien);
        demso = findViewById(R.id.TextView_Amount);
        TextView_TotalMoney = findViewById(R.id.TextView_TotalMoney);
        SwipeRefreshLayoutCart = findViewById(R.id.SwipeRefreshLayoutCart);
        LinearLayout_BuyProduct = findViewById(R.id.LinearLayout_BuyProduct);
        LinearLayout_Cart = findViewById(R.id.LinearLayout_Cart);
        Button_BuyProduct = findViewById(R.id.Button_BuyProduct);
        Button_Order = findViewById(R.id.Button_Order);
        RecyclerView_ProductLike = findViewById(R.id.RecyclerView_ProductLike);
    }
}