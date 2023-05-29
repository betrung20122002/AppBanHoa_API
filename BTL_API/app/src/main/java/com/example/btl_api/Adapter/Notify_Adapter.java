package com.example.btl_api.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Activity.MainActivity;
import com.example.btl_api.Model.Notify;
import com.example.btl_api.Model.Product;
import com.example.btl_api.Model.ProductDetails;
import com.example.btl_api.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notify_Adapter extends RecyclerView.Adapter<Notify_Adapter.Notify_ViewHolder> {
    List<Notify> notifies;
    Context  context;

    public Notify_Adapter(List<Notify> notifies, Context  context) {
        this.notifies = notifies;
        this.context = context;
    }




    @NonNull
    @Override
    public Notify_Adapter.Notify_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notify,parent,false);
        return new Notify_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Notify_Adapter.Notify_ViewHolder holder, int position) {
        Notify notify=notifies.get(position);
        switch (notify.getStatus()){
            case 1:
                APIInterface.API.GetProductDetail(notify.getId_productdetails()).enqueue(new Callback<ProductDetails>() {
                    @Override
                    public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
                        if (response.isSuccessful()) {
                            APIInterface.API.getchitiet(response.body().getId_product()).enqueue(new Callback<Product>() {
                                @Override
                                public void onResponse(Call<Product> call, Response<Product> response) {
                                    Glide.with(context.getApplicationContext()).load(response.body().getImagelinks()).apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(15))).error(R.drawable.hoaooo).into(holder.ImageView_Product);
                                    holder.TextView_Title.setText("Sản phẩm " + response.body().getNameProduct());
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
                holder.TextView_Content.setText("Đồ của đại ca đang được shipper giao một cách nhanh nhất");
            case 2:
                APIInterface.API.GetProductDetail(notify.getId_productdetails()).enqueue(new Callback<ProductDetails>() {
                    @Override
                    public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
                        if (response.isSuccessful()) {
                            APIInterface.API.getchitiet(response.body().getId_product()).enqueue(new Callback<Product>() {
                                @Override
                                public void onResponse(Call<Product> call, Response<Product> response) {
                                    Glide.with(context.getApplicationContext()).load(response.body().getImagelinks()).apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(15))).error(R.drawable.hoaooo).into(holder.ImageView_Product);
                                    holder.TextView_Title.setText("Sản phẩm " + response.body().getNameProduct());
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
                holder.TextView_Content.setText("Cảm ơn đại ca đã đặt hàng của chúng tôi");
                break;
            case 3:
                APIInterface.API.GetProductDetail(notify.getId_productdetails()).enqueue(new Callback<ProductDetails>() {
                    @Override
                    public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
                        if (response.isSuccessful()) {
                            APIInterface.API.getchitiet(response.body().getId_product()).enqueue(new Callback<Product>() {
                                @Override
                                public void onResponse(Call<Product> call, Response<Product> response) {
                                    Glide.with(context.getApplicationContext()).load(response.body().getImagelinks()).apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(15))).error(R.drawable.hoaooo).into(holder.ImageView_Product);
                                    holder.TextView_Title.setText("Sản phẩm " + response.body().getNameProduct());
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
                holder.TextView_Content.setText("Có vẻ sản phẩm này không phù hợp với đại ca, đại ca hãy chọn món ăn mà bạn thích nhất.");
                holder.TextView_Content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        click(notify);
                    }
                });
                break;
        }
    }

    private void click(Notify notify) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (notifies!=null){
            return notifies.size();
        }
        return 0;
    }

    public class Notify_ViewHolder extends RecyclerView.ViewHolder {
        ImageView ImageView_Product;
        TextView TextView_Title,TextView_Content,TextView_Datetime;
        public Notify_ViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageView_Product=itemView.findViewById(R.id.ImageView_Product);
            TextView_Title=itemView.findViewById(R.id.TextView_Title);
            TextView_Content=itemView.findViewById(R.id.TextView_Content);
            TextView_Datetime=itemView.findViewById(R.id.TextView_Datetime);
        }
    }
}
