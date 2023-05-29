package com.example.btl_api.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.Model.Product;
import com.example.btl_api.Model.ProductDetails;
import com.example.btl_api.Model.Tintuc;
import com.example.btl_api.R;
import com.google.android.gms.cast.framework.media.ImagePicker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Post_Adapter extends RecyclerView.Adapter<Post_Adapter.post_viewholder>{
    List<Tintuc> list;

    Context context;
    public Post_Adapter(ArrayList<Tintuc> list, Context context) {
        this.list = list;
        this.context = context;

    }
    @NonNull
    @Override
    public Post_Adapter.post_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post,parent,false);
        return new Post_Adapter.post_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Post_Adapter.post_viewholder holder, int position) {
        Tintuc tintuc = list.get(position);
        APIInterface.API.GetAccount(tintuc.getId_Account()).enqueue(new Callback<Accounts>() {
            @Override
            public void onResponse(Call<Accounts> call, Response<Accounts> response) {
                if (response.isSuccessful()) {
                    holder.username.setText(response.body().getName());
                    Glide.with(context.getApplicationContext()).load(response.body().getPicture()).error(R.drawable.hoaooo).into(holder.image_profile);
                }

            }

            @Override
            public void onFailure(Call<Accounts> call, Throwable t) {

            }
        });
        holder.noidung.setText(tintuc.getNoiDung());
        Glide.with(context.getApplicationContext()).load(tintuc.getImage()).error(R.drawable.hoaooo).into(holder.post_image);
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    public class post_viewholder extends RecyclerView.ViewHolder {
        ImageView image_profile,post_image;
        TextView username,noidung;
        public post_viewholder(@NonNull View itemView) {
            super(itemView);
            image_profile = itemView.findViewById(R.id.image_profile);
            post_image = itemView.findViewById(R.id.post_image);
            username = itemView.findViewById(R.id.username);
            noidung = itemView.findViewById(R.id.noidung);
        }
    }
}
