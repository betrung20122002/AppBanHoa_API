package com.example.btl_api.Adapter;

import static com.example.btl_api.Activity.MainActivity.currencyVN;

import android.app.Activity;
import android.content.Intent;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Activity.QLSanPham.MainActivitySuaSP;
import com.example.btl_api.Activity.QLĐonHang.MainActivitySuaTrangThaiDH;
import com.example.btl_api.Model.Product;
import com.example.btl_api.Model.ProductDetails;
import com.example.btl_api.Model.order_;
import com.example.btl_api.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChinhTrangThai_Adapter extends RecyclerView.Adapter<ChinhTrangThai_Adapter.trangthai_viewholder> {
    List<order_> orders;
    public static int Id_Order =0;
    Activity activity;
    public ChinhTrangThai_Adapter(List<order_> orders, Activity activity) {
        this.orders = orders;
        this.activity = activity;
    }
    @NonNull
    @Override
    public ChinhTrangThai_Adapter.trangthai_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chinhdonhang, parent, false);
        return new ChinhTrangThai_Adapter.trangthai_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChinhTrangThai_Adapter.trangthai_viewholder holder, int position) {
        order_ order = orders.get(position);
        Id_Order = order.getId_Order();
        APIInterface.API.GetProductDetail(order.getId_productdetails()).enqueue(new Callback<ProductDetails>() {
            @Override
            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
                if (response.isSuccessful()) {
                    ProductDetails productDetails = response.body();
                    if (productDetails.getPromotionalprice() == 0) {
                        holder.TextView_Price.setText(currencyVN.format(productDetails.getPrice()));
                        holder.TextView_Promotionalprice.setVisibility(View.GONE);
                    } else {
                        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(currencyVN.format(productDetails.getPrice()));
                        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
                        spannableStringBuilder.setSpan(strikethroughSpan, 0, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        holder.TextView_Price.setText(spannableStringBuilder);
                        holder.TextView_Promotionalprice.setText(currencyVN.format(productDetails.getPromotionalprice()));
                    }
                    holder.TextView_Size.setText("Khích cỡ: " + productDetails.getSize());
                    APIInterface.API.getchitiet(productDetails.getId_product()).enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
//                            Glide.with(activity).load(response.body().getImagelinks()).apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(15))).error(R.drawable.hoaooo).into(holder.ImageView_Product);
                            holder.TextView_NameProduct.setText(response.body().getNameProduct());
                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ProductDetails> call, Throwable t) {

            }
        });
        holder.TextView_Amount.setText("Số lượng: " + order.getQuantity());
        if(order.getStatus() == 0){
            holder.TrangThai.setText("Đang xác nhận");
        }if(order.getStatus() ==1){
            holder.TrangThai.setText("Đang giao hàng");
        }if(order.getStatus() ==2){
            holder.TrangThai.setText("Đã giao");
        }if(order.getStatus() ==3){
            holder.TrangThai.setText("Đã hủy");
        }
        holder.TextView_TotalMoney.setText("Tổng tiền: " + currencyVN.format(order.getTotalMoney()));
        holder.TextView_Datetime.setText(order.getNgayDat());
        holder.Button_CancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, MainActivitySuaTrangThaiDH.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (orders != null) {
            return orders.size();
        }
        return 0;
    }

    public class trangthai_viewholder extends RecyclerView.ViewHolder {
        ImageView ImageView_Product;
        TextView TextView_NameProduct, TrangThai,TextView_Size, TextView_Price, TextView_Promotionalprice, TextView_Amount, TextView_TotalMoney, TextView_Datetime;
        Button Button_CancelOrder;
        public trangthai_viewholder(@NonNull View itemView) {
            super(itemView);
            ImageView_Product = itemView.findViewById(R.id.ImageView_Product);
            TextView_NameProduct = itemView.findViewById(R.id.TextView_NameProduct);
            TextView_Size = itemView.findViewById(R.id.TextView_Size);
            TrangThai = itemView.findViewById(R.id.Trangthai);
            TextView_Price = itemView.findViewById(R.id.TextView_Price);
            TextView_Promotionalprice = itemView.findViewById(R.id.TextView_Promotionalprice);
            TextView_Amount = itemView.findViewById(R.id.TextView_Amount);
            TextView_TotalMoney = itemView.findViewById(R.id.TextView_TotalMoney);
            TextView_Datetime = itemView.findViewById(R.id.TextView_Datetime);
            Button_CancelOrder = itemView.findViewById(R.id.Button_CancelOrder);

        }
    }
}
