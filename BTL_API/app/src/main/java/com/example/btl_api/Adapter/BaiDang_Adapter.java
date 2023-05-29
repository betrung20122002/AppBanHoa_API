package com.example.btl_api.Adapter;

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
import com.example.btl_api.Activity.QLBaiDang.BaiDangActivity;
import com.example.btl_api.Activity.QLBaiDang.MainActivitySuaBaiDang;
import com.example.btl_api.Activity.QLSanPham.MainActivitySuaSP;
import com.example.btl_api.Activity.QLSanPham.SanPhamActivity;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.Model.Product;
import com.example.btl_api.Model.Tintuc;
import com.example.btl_api.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiDang_Adapter extends RecyclerView.Adapter<BaiDang_Adapter.baidang_holder> {
    List<Tintuc> list;

    Context context;
    public BaiDang_Adapter(ArrayList<Tintuc> list, Context context) {
        this.list = list;
        this.context = context;

    }
    @NonNull
    @Override
    public BaiDang_Adapter.baidang_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_baidang,parent,false);
        return new BaiDang_Adapter.baidang_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaiDang_Adapter.baidang_holder holder, int position) {
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
        holder.imageViewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MainActivitySuaBaiDang.class));
            }
        });
        holder.noidung.setText(tintuc.getNoiDung());
        Glide.with(context.getApplicationContext()).load(tintuc.getImage()).error(R.drawable.hoaooo).into(holder.post_image);
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
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
                        APIInterface.API.DeletePOST(tintuc.getMaTT()).enqueue(new Callback<Tintuc>() {
                            @Override
                            public void onResponse(Call<Tintuc> call, Response<Tintuc> response) {

                            }

                            @Override
                            public void onFailure(Call<Tintuc> call, Throwable t) {

                            }
                        });
                        lick_theloai(tintuc);
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
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }
    private void lick_theloai(Tintuc tintuc) {
        Intent intent = new Intent(context, BaiDangActivity.class);
        context.startActivity(intent);
        Toast.makeText(context, "Đã xóa  khỏi bài  thành công", Toast.LENGTH_SHORT).show();
    }
    public class baidang_holder extends RecyclerView.ViewHolder {
        ImageView image_profile,post_image,imageViewedit,imageViewDelete;
        TextView username,noidung;
        public baidang_holder(@NonNull View itemView) {
            super(itemView);
            image_profile = itemView.findViewById(R.id.image_profile);
            post_image = itemView.findViewById(R.id.post_image);
            username = itemView.findViewById(R.id.username);
            noidung = itemView.findViewById(R.id.noidung);
            imageViewedit = itemView.findViewById(R.id.imageViewedit);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}
