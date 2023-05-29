package com.example.btl_api.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import static com.example.btl_api.RetrofitClient.dia;
import static com.example.btl_api.RetrofitClient.ANIMATION;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.btl_api.Activity.LoginActivity.Id_Account;
import com.bumptech.glide.Glide;
import com.example.btl_api.API.APIInterface;
import com.example.btl_api.API.Get_Money;
import com.example.btl_api.Adapter.Category_Adapter;
import com.example.btl_api.Adapter.Comment_Adapter;
import com.example.btl_api.Adapter.Notify_Adapter;
import com.example.btl_api.Adapter.Product_Adapter;
import com.example.btl_api.Adapter.Size_Adapter;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.Model.Carts;
import com.example.btl_api.Model.Category;
import com.example.btl_api.Model.Comment;
import com.example.btl_api.Model.Product;
import com.example.btl_api.Model.ProductDetails;
import com.example.btl_api.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import static com.example.btl_api.Adapter.Product_Adapter.Id_product;
import static com.example.btl_api.Activity.MainActivity.currencyVN;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    TextView tensp,giatiensp,luotban,luotxem,TextView_Content,giachinh,add_comment,post;
    ImageView img_back;
    LinearLayout themvaogiohang;
    Get_Money get_money;
    private int tong2 ;
    Context context;
    private int tong3 = 1;
    private int tong4 ;
    RadioButton textView_Size;
    RecyclerView recycleview_item;
    CircleImageView image_profile;
    private int Id_productdetails_;
    ImageView imageView1,imageView2,imageView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        AnhXa();
        getdata();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductDetailActivity.this,MainActivity.class));
            }
        });
        themvaogiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomThem();
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String add_commentt = add_comment.getText().toString().trim();
                Comment comment = new Comment(0,Id_Account,Id_product,add_commentt);
                APIInterface.API.PostbinhLuan(comment).enqueue(new Callback<Comment>() {
                    @Override
                    public void onResponse(Call<Comment> call, Response<Comment> response) {
                        if (response.isSuccessful()){
                        }
                    }

                    @Override
                    public void onFailure(Call<Comment> call, Throwable t) {
                        Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void getBinhLuan() {

    }


    private void bottomThem() {

        View view = getLayoutInflater().inflate(R.layout.dialog_themgiohang,null);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
        Dialog dialog;
        dialog=new Dialog(ProductDetailActivity.this);
        dia(dialog);
        ANIMATION(this);
        RecyclerView phanloai = view.findViewById(R.id.phanloai);
        Button button = view.findViewById(R.id.backkkkk);
        ImageView hinhanhloai = view.findViewById(R.id.hinhanhloai);
        TextView tien = view.findViewById(R.id.tien);
        ImageView tru=  view.findViewById(R.id.tru);
        ImageView cong = view.findViewById(R.id.cong);

        TextView sl = view.findViewById(R.id.sl);
        Button Button_addCart = view.findViewById(R.id.Button_addCart);
        TextView tongtien = view.findViewById(R.id.tongtien);
        Button_addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                Carts cart=new Carts(0,Id_Account,Id_productdetails_,tong3,tong3*tong2,"");
                APIInterface.API.PostCart(cart).enqueue(new Callback<List<Carts>>() {
                    @Override
                    public void onResponse(Call<List<Carts>> call, Response<List<Carts>> response) {
                        if (response.isSuccessful()) {
                            Carts cart1 = response.body().get(0);
                            Carts cart_ = new Carts(cart1.getId_Cart(),
                                    cart1.getId_Account(), cart1.getId_productdetails(),
                                    cart1.getQuantity() + cart.getQuantity(),
                                    cart1.getTotalMoney() + cart.getTotalMoney(), cart1.getNotes());
                            APIInterface.API.PutCart(response.body().get(0).getId_Cart(), cart_).enqueue(new Callback<Carts>() {
                                @Override
                                public void onResponse(Call<Carts> call, Response<Carts> response) {

                                }

                                @Override
                                public void onFailure(Call<Carts> call, Throwable t) {
                                }
                            });
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Carts>> call, Throwable t) {

                    }
                });
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        getdata();
                        Toast.makeText(ProductDetailActivity.this, "Đã thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();

                    }
                },2000);
            }
        });
        cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tong3 = tong3 + 1;
                sl.setText(tong3 + "");
            }
        });

        tong4 = tong2 * tong3;
        tongtien.setText(currencyVN.format(tong4));
        tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tong3 != 1) {
                    tong3 = tong3 - 1;
                    sl.setText(tong3 + "");
                    tongtien.setText(""+tong3*tong2);
                    tongtien.setText(currencyVN.format(tong4));
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        APIInterface.API.GetProductDetails(Id_product).enqueue(new Callback<List<ProductDetails>>() {
            @Override
            public void onResponse(Call<List<ProductDetails>> call, Response<List<ProductDetails>> response) {
                if (response.isSuccessful()) {
                    int Position = response.body().size() - 1;
                    Glide.with(bottomSheetDialog.getContext())
                            .load(response.body().get(0).getPicture_1())
                            .into(hinhanhloai);
                    tien.setText(currencyVN.format(response.body().get(0).getPromotionalprice()) + "-" + currencyVN.format(response.body().get(Position).getPromotionalprice()));
                        Size_Adapter size_adapterBUS = new Size_Adapter(response.body(), context, get_money);
                        phanloai.setLayoutManager(new GridLayoutManager(ProductDetailActivity.this,5));
                        phanloai.setAdapter(size_adapterBUS);
                }
            }
            @Override
            public void onFailure(Call<List<ProductDetails>> call, Throwable t) {
            }
        });
        get_money = new Get_Money() {
            @Override
            public void Get_Money(int Id_productdetails, int Price, int Promotionalprice) {
                Id_productdetails_ = Id_productdetails;
                 if (Promotionalprice == 0) {
                    tong2 = Price;
                    tong4 = tong2 * tong3;
                    tongtien.setText(currencyVN.format(tong4));
                } else {
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(currencyVN.format(Price));
                    StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
                    spannableStringBuilder.setSpan(strikethroughSpan, 0, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tong2 = Promotionalprice;
                    tong4 = tong2 * tong3;
                    tongtien.setText(currencyVN.format(tong4));
                }
            }
        };
    }


    private void getdata() {
        APIInterface.API.Get_BinhLuan(Id_product).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                Comment_Adapter comment_adapter = new Comment_Adapter( response.body(),ProductDetailActivity.this);
                recycleview_item.setLayoutManager(new LinearLayoutManager(ProductDetailActivity.this,RecyclerView.VERTICAL,false));
                recycleview_item.setAdapter(comment_adapter);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
        APIInterface.API.GetAccount(Id_Account).enqueue(new Callback<Accounts>() {
            @Override
            public void onResponse(Call<Accounts> call, Response<Accounts> response) {
                Glide.with(getApplicationContext())
                        .load(response.body().getPicture())
                        .error(R.drawable.hoaooo)
                        .into(image_profile);
            }

            @Override
            public void onFailure(Call<Accounts> call, Throwable t) {

            }
        });
        APIInterface.API.getchitiet(Id_product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Product product = response.body();
                    tensp.setText(product.getNameProduct());
                    luotban.setText("Đã bán : " + response.body().getSales());
                    luotxem.setText("Đã xem: " + response.body().getViews());
                    TextView_Content.setText(product.getContent());
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });

        APIInterface.API.GetProductDetails(Id_product).enqueue(new Callback<List<ProductDetails>>() {
            @Override
            public void onResponse(Call<List<ProductDetails>> call, Response<List<ProductDetails>> response) {
                if (response.isSuccessful()) {
                    int Position = response.body().size() - 1;
                    Glide.with(getApplicationContext())
                            .load(response.body().get(0).getPicture_1())
                            .error(R.drawable.hoaooo)
                            .into(imageView1);
                    if (response.body().get(0).getPromotionalprice() == 0 || response.body().get(Position).getPromotionalprice() == 0) {
                        giatiensp.setText(currencyVN.format(response.body().get(0).getPrice()) + "-" + currencyVN.format(response.body().get(Position).getPrice()));
                        giachinh.setVisibility(View.INVISIBLE);
                    } else {
                        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(currencyVN.format(response.body().get(0).getPrice()) + "-" + currencyVN.format(response.body().get(Position).getPrice()));
                        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();//Kỹ thuật tạo dấu gạch ngang
                        spannableStringBuilder.setSpan(strikethroughSpan, 0, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        giatiensp.setText(spannableStringBuilder);
                        giachinh.setText(currencyVN.format(response.body().get(0).getPromotionalprice()) + "-" + currencyVN.format(response.body().get(Position).getPromotionalprice()));
                    }
                }

            }

            @Override
            public void onFailure(Call<List<ProductDetails>> call, Throwable t) {
            }
        });

    }
    private int getNotificationId() {
        return  (int) new Date().getTime();
    }

    private void AnhXa() {
        tensp = findViewById(R.id.tensp);
        giatiensp = findViewById(R.id.giatiensp);
        luotban = findViewById(R.id.luotban);
        imageView1 = findViewById(R.id.imageview1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        luotxem = findViewById(R.id.luotxem);
        TextView_Content = findViewById(R.id.TextView_Content);
        giachinh = findViewById(R.id.giachinh);
        themvaogiohang = findViewById(R.id.themvaogiohang);
        img_back = findViewById(R.id.img_back);
        textView_Size = findViewById(R.id.textView_Size);
        image_profile = findViewById(R.id.image_profile);
        add_comment = findViewById(R.id.add_comment);
        post = findViewById(R.id.post);
        recycleview_item = findViewById(R.id.recycleview_item);
    }
}