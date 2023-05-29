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
import com.example.btl_api.Activity.QLChitietSP.ChitietSPActivity;
import com.example.btl_api.Activity.QLChitietSP.MainActivitySuaChiTietSP;
import com.example.btl_api.Activity.QLLoaiSP.MainActivitySuaLoaiSP;
import com.example.btl_api.Activity.QLSanPham.SanPhamActivity;
import com.example.btl_api.Model.Product;
import com.example.btl_api.Model.ProductDetails;
import com.example.btl_api.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietSP_Adapter extends RecyclerView.Adapter<ChiTietSP_Adapter.chitetsp_viewholder> {
    List<ProductDetails> list;

    Context context;
    public static int Id_ProductDetails = 0;
    public ChiTietSP_Adapter(ArrayList<ProductDetails> list, Context context) {
        this.list = list;
        this.context = context;

    }
    @NonNull
    @Override
    public ChiTietSP_Adapter.chitetsp_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitietsanpham,parent,false);
        return new ChiTietSP_Adapter.chitetsp_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietSP_Adapter.chitetsp_viewholder holder, int position) {
        ProductDetails productDetails =list.get(position);
        holder.IdProduct.setText(productDetails.getId_product()+"");
        Glide.with(context.getApplicationContext()).load(productDetails.getPicture_1()) .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).error(R.drawable.hoaooo).into(holder.img1);
        holder.Size.setText(productDetails.getSize());
        holder.TextView_Price.setText(productDetails.getPrice()+"");
        Id_ProductDetails = productDetails.getId_productdetails();
        holder.TextView_Promotionalprice.setText(productDetails.getPromotionalprice()+"");
        holder.imageViewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MainActivitySuaChiTietSP.class));
            }
        });
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
                        APIInterface.API.DeleteProductDetail(productDetails.getId_productdetails()).enqueue(new Callback<ProductDetails>() {
                            @Override
                            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {

                            }

                            @Override
                            public void onFailure(Call<ProductDetails> call, Throwable t) {

                            }
                        });
                        lick_theloai(productDetails);
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
    private void lick_theloai(ProductDetails productDetails) {
        Intent intent = new Intent(context, ChitietSPActivity.class);
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

    public class chitetsp_viewholder extends RecyclerView.ViewHolder {
        TextView IdProduct,Size,TextView_Price,TextView_Promotionalprice;
        ImageView img1,imageViewDelete,imageViewedit;
        public chitetsp_viewholder(@NonNull View itemView) {
            super(itemView);
            IdProduct = itemView.findViewById(R.id.IdProduct);
            Size = itemView.findViewById(R.id.Size);
            TextView_Price = itemView.findViewById(R.id.TextView_Price);
            TextView_Promotionalprice = itemView.findViewById(R.id.TextView_Promotionalprice);
            img1= itemView.findViewById(R.id.img1);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            imageViewedit = itemView.findViewById(R.id.imageViewedit);
        }
    }
}
