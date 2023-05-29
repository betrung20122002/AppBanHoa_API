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
import com.bumptech.glide.request.target.Target;
import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Activity.QLLoaiSP.LoaiActivity;
import com.example.btl_api.Activity.QLLoaiSP.MainActivitySuaLoaiSP;
import com.example.btl_api.Model.Category;
import com.example.btl_api.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoaiSP extends RecyclerView.Adapter<LoaiSP.theloai_viewholder>{
    List<Category> theLoaiList;
    Context context;
    public static int Id_Category = 0;
    public LoaiSP(ArrayList<Category> list, Context context) {
        this.theLoaiList = list;
        this.context = context;
    }
    @NonNull
    @Override
    public LoaiSP.theloai_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai,parent, false);
        return new LoaiSP.theloai_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSP.theloai_viewholder holder, int position) {
        Category theLoai = theLoaiList.get(position);
        Glide.with(context.getApplicationContext()).load(theLoai.getPictureCategory()) .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).error(R.drawable.hoaooo).into(holder.imageView);
        holder.txt_name.setText(theLoai.getNameCategory());
        Id_Category = theLoai.getId_Category();
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
                        APIInterface.API.DeleteCategory(theLoai.getId_Category()).enqueue(new Callback<Category>() {
                            @Override
                            public void onResponse(Call<Category> call, Response<Category> response) {

                            }

                            @Override
                            public void onFailure(Call<Category> call, Throwable t) {

                            }
                        });
                                lick_theloai(theLoai);
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
        holder.imageViewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    context.startActivity(new Intent(context, MainActivitySuaLoaiSP.class));
            }
        });
    }
    private void lick_theloai(Category theloai) {
        Intent intent = new Intent(context, LoaiActivity.class);
        context.startActivity(intent);
        Toast.makeText(context, "Đã xóa  khỏi thư viện thành công", Toast.LENGTH_SHORT).show();
    }
    @Override
    public int getItemCount() {
        if (theLoaiList != null){
            return theLoaiList.size();
        }
        return 0;
    }

    public class theloai_viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txt_name;
        ImageView imageViewedit, imageViewDelete;
        public theloai_viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.ImageView_Category);
            txt_name=itemView.findViewById(R.id.TextView_NameCategory);
            imageViewedit = itemView.findViewById(R.id.imageViewedit);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);

        }
    }


}
