package com.example.btl_api.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.btl_api.Activity.TinNhan.MessegeActivity;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdaper extends RecyclerView.Adapter<UserAdaper.viewholder>{
    Context mainActivity;

    ArrayList<Accounts> usersArrayList;
    public static int Id_Accountt = 0;
    public UserAdaper(Context mainActivity, ArrayList<Accounts> usersArrayList) {
        this.mainActivity=mainActivity;
        this.usersArrayList=usersArrayList;
    }
    @NonNull
    @Override
    public UserAdaper.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.user_item,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdaper.viewholder holder, int position) {
        Accounts users = usersArrayList.get(position);
        holder.username.setText(users.getName());
        holder.userstatus.setText(users.getEmail());
        Glide.with(mainActivity.getApplicationContext()).load(users.getPicture()) .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).error(R.drawable.hoaooo).into(holder.userimg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, MessegeActivity.class);
                mainActivity.startActivity(intent);
                Id_Accountt = users.getId_Account();

            }
        });
    }

    @Override
    public int getItemCount() {
        if (usersArrayList != null){
            return usersArrayList.size();
        }
        return 0;
    }

    public class viewholder extends RecyclerView.ViewHolder {
        CircleImageView userimg;
        TextView username;
        TextView userstatus;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            userimg = itemView.findViewById(R.id.userimg);
            username = itemView.findViewById(R.id.username);
            userstatus = itemView.findViewById(R.id.userstatus);
        }
    }
}
