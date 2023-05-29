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
import com.example.btl_api.Activity.QLLoaiSP.LoaiActivity;
import com.example.btl_api.Activity.QLLoaiSP.MainActivitySuaLoaiSP;
import com.example.btl_api.Activity.QLSanPham.MainActivitySuaSP;
import com.example.btl_api.Activity.QLSanPham.SanPhamActivity;
import com.example.btl_api.Model.Category;
import com.example.btl_api.Model.Product;
import com.example.btl_api.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SanPham_Adapter extends RecyclerView.Adapter<SanPham_Adapter.sanpham_viewholder> {
    List<Product> list;

    Context context;
    public static int Id_product = 0;

    public SanPham_Adapter(ArrayList<Product> list, Context context) {
        this.list = list;
        this.context = context;

    }
    @NonNull
    @Override
    public SanPham_Adapter.sanpham_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham,parent,false);
        return new SanPham_Adapter.sanpham_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPham_Adapter.sanpham_viewholder holder, int position) {
        Product product =list.get(position);
        Id_product = product.getId_product();
        Glide.with(context.getApplicationContext()).load(product.getImagelinks()).error(R.drawable.hoaooo).into(holder.ImageView_Product);
        holder.TextView_NameProduct.setText(product.getNameProduct());
        holder.TextView_Content.setText(product.getContent());
        holder.TextView_Scale.setText(product.getSales() + "");
        holder.TextView_View.setText(product.getViews() + "");
        holder.TextView_IdCategory.setText(product.getId_danhmuc()+"");
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
                        APIInterface.API.DeleteProduct(product.getId_product()).enqueue(new Callback<Product>() {
                            @Override
                            public void onResponse(Call<Product> call, Response<Product> response) {

                            }

                            @Override
                            public void onFailure(Call<Product> call, Throwable t) {

                            }
                        });
                        lick_theloai(product);
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
                context.startActivity(new Intent(context, MainActivitySuaSP.class));
            }
        });
    }
    private void lick_theloai(Product product) {
        Intent intent = new Intent(context, SanPham_Adapter.class);
        context.startActivity(intent);
        Toast.makeText(context, "Đã xóa  khỏi thư viện thành công", Toast.LENGTH_SHORT).show();
    }
    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    public class sanpham_viewholder extends RecyclerView.ViewHolder {
        ImageView ImageView_Product,imageViewDelete,imageViewedit;
        TextView TextView_NameProduct,TextView_Price,TextView_IdCategory,TextView_Content,TextView_Scale,TextView_View;
        public sanpham_viewholder(@NonNull View itemView) {
            super(itemView);

            TextView_IdCategory = itemView.findViewById(R.id.TextView_IdCategory);
            TextView_Price = itemView.findViewById(R.id.TextView_Price);
            TextView_Scale =itemView.findViewById(R.id.TextView_Scale);
            TextView_NameProduct = itemView.findViewById(R.id.TextView_NameProduct);
            TextView_View =itemView.findViewById(R.id.TextView_View);
            ImageView_Product =itemView.findViewById(R.id.ImageView_Product);
            TextView_Content = itemView.findViewById((R.id.TextView_Content));
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            imageViewedit = itemView.findViewById(R.id.imageViewedit);

        }
    }
}
