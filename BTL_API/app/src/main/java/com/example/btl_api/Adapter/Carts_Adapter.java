package com.example.btl_api.Adapter;

import static com.example.btl_api.RetrofitClient.dia;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Activity.ShoppingActivity;
import com.example.btl_api.Model.Carts;
import com.example.btl_api.Model.Product;
import com.example.btl_api.Model.ProductDetails;
import com.example.btl_api.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Carts_Adapter extends RecyclerView.Adapter<Carts_Adapter.theloai_viewholder> {
    List<Carts> list;
    Dialog dialogg;
    Context context;
    int Amount;
    private int tong4 ;


    public Carts_Adapter(List<Carts> list, Context context ){

        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public theloai_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carts, parent, false);
        return new theloai_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull theloai_viewholder holder, int position) {
        Carts carts = list.get(position);
        holder.TextView_Amount.setText(carts.getQuantity() + "");
        final int[] count = {carts.getQuantity()};
        final int[] count1 = {carts.getQuantity()};
        holder.ImageView_AddAmount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        count[0] = count[0] + 1;
                        holder.TextView_Amount.setText(count[0] + "");
                        PutCart_( carts, Amount);
                    }
                });
        holder.ImageView_MinusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count1[0] = count1[0] - 1;
                if (count[0] != 1) {
                    count[0] = count[0] - 1;
                    count1[0] = count[0];
                    holder.TextView_Amount.setText(count[0] + "");
                    PutCart_( carts, Amount);
                }
                if (count1[0] <= 0) {
                    Dialog dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.dialog_stop);
                    TextView tvAnswer = (TextView) dialog.findViewById(R.id.tv_dialog_stop);
                    tvAnswer.setText("Bạn có muốn xóa không ?");
                    Button btnOKStop = (Button) dialog.findViewById(R.id.btn_ok_stop_game);
                    Button btnCancelStop = (Button) dialog.findViewById(R.id.btn_cancel_stop_game);
                    btnOKStop.setText("OK");
                    btnCancelStop.setText("Không");
                    btnOKStop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            APIInterface.API.DeletCarts(carts.getId_Cart()).enqueue(new Callback<Carts>() {
                                @Override
                                public void onResponse(Call<Carts> call, Response<Carts> response) {
                                }

                                @Override
                                public void onFailure(Call<Carts> call, Throwable t) {

                                }
                            });
                            lick_theloai(carts);
                        }

                    });
                    btnCancelStop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    dialog.create();
                }
                lick_theloai(carts);
            }
        });
        holder.tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_stop);
                TextView tvAnswer = (TextView) dialog.findViewById(R.id.tv_dialog_stop);
                tvAnswer.setText("Bạn có muốn xóa không ?");
                Button btnOKStop = (Button) dialog.findViewById(R.id.btn_ok_stop_game);
                Button btnCancelStop = (Button) dialog.findViewById(R.id.btn_cancel_stop_game);
                btnOKStop.setText("OK");
                btnCancelStop.setText("Không");
                btnOKStop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        APIInterface.API.DeletCarts(carts.getId_Cart()).enqueue(new Callback<Carts>() {
                            @Override
                            public void onResponse(Call<Carts> call, Response<Carts> response) {
                            }

                            @Override
                            public void onFailure(Call<Carts> call, Throwable t) {

                            }
                        });
                        lick_theloai(carts);
                    }

                });
                btnCancelStop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.create();
            }
        });

        APIInterface.API.GetProductDetail(carts.getId_productdetails()).enqueue(new Callback<ProductDetails>() {
            @Override
            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
                ProductDetails productDetails = (ProductDetails) response.body();
                holder.TextView_Size.setText("Phân loại :"+productDetails.getSize());
                tong4= productDetails.getPromotionalprice();
                holder.TextView_Promotionalprice.setText(response.body().getPromotionalprice()+"");
                Glide.with(context.getApplicationContext()).load(productDetails.getPicture_3()).error(R.drawable.hoaooo).into(holder.ImageView_Product);
                APIInterface.API.getchitiet(productDetails.getId_product()).enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        Product product = response.body();
                        holder.TextView_NameProduct.setText(product.getNameProduct());

                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                    }
                });
            }

            @Override
            public void onFailure(Call<ProductDetails> call, Throwable t) {

            }
        });
    }

    private void lick_theloai(Carts carts) {
        Intent intent = new Intent(context, ShoppingActivity.class);
        context.startActivity(intent);
        Toast.makeText(context, "Đã xóa sản phẩm khỏi giỏ hàng thành công", Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }
    public void PutCart_( Carts carts,int Amount) {
        Carts carts1 = new Carts(carts.getId_Cart(),carts.getId_Account(),carts.getId_productdetails(),Amount,tong4*Amount,carts.getNotes());
        APIInterface.API.PutCart(carts.getId_Cart(),carts1).enqueue(new Callback<Carts>() {
            @Override
            public void onResponse(Call<Carts> call, Response<Carts> response) {

            }

            @Override
            public void onFailure(Call<Carts> call, Throwable t) {

            }
        });
    }
    public class theloai_viewholder extends RecyclerView.ViewHolder{
        TextView TextView_NameProduct,TextView_Size,TextView_Amount,TextView_Promotionalprice;
        ImageView ImageView_Product,ImageView_MinusQuantity,ImageView_AddAmount,tru;
        public theloai_viewholder(@NonNull View itemView) {
            super(itemView);
            TextView_NameProduct = itemView.findViewById(R.id.TextView_NameProduct);
            TextView_Size = itemView.findViewById(R.id.TextView_Size);
            TextView_Amount = itemView.findViewById(R.id.TextView_Amount);
            TextView_Promotionalprice = itemView.findViewById(R.id.TextView_Promotionalprice);
            ImageView_Product = itemView.findViewById(R.id.ImageView_Product);
            ImageView_MinusQuantity = itemView.findViewById(R.id.ImageView_MinusQuantity);
            ImageView_AddAmount =itemView.findViewById(R.id.ImageView_AddAmount);
            tru = itemView.findViewById(R.id.delete);
            dialogg=new Dialog(context.getApplicationContext());
            dia(dialogg);
        }
    }
}
