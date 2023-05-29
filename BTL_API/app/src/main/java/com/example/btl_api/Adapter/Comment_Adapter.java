package com.example.btl_api.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.Model.Comment;
import com.example.btl_api.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Comment_Adapter extends RecyclerView.Adapter<Comment_Adapter.commecnt_viewholder> {
    List<Comment> theLoaiList;
    Context context;;
    public Comment_Adapter(List<Comment> list, Context context) {
        this.theLoaiList = list;
        this.context = context;
    }
    @NonNull
    @Override
    public Comment_Adapter.commecnt_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,parent, false);
        return new Comment_Adapter.commecnt_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Comment_Adapter.commecnt_viewholder holder, int position) {
        Comment comment = theLoaiList.get(position);
        holder.comment.setText(comment.getBinhluan());
        APIInterface.API.GetAccount(comment.getId_Account()).enqueue(new Callback<Accounts>() {
            @Override
            public void onResponse(Call<Accounts> call, Response<Accounts> response) {
                Glide.with(context.getApplicationContext())
                        .load(response.body().getPicture())
                        .error(R.drawable.hoaooo)
                        .into(holder.image_profile);
                holder.username.setText(response.body().getName());
            }

            @Override
            public void onFailure(Call<Accounts> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if (theLoaiList != null){
            return theLoaiList.size();
        }
        return 0;
    }

    public class commecnt_viewholder extends RecyclerView.ViewHolder {
        CircleImageView image_profile;
        TextView username,comment;
        public commecnt_viewholder(@NonNull View itemView) {
            super(itemView);
            image_profile = itemView.findViewById(R.id.image_profile);
            username = itemView.findViewById(R.id.username);
            comment = itemView.findViewById(R.id.comment);
        }
    }
}
