package com.example.btl_api.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import static com.example.btl_api.Activity.MainActivity.currencyVN;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_api.API.Get_Money;
import com.example.btl_api.Model.ProductDetails;
import com.example.btl_api.R;

import java.util.List;

public class Size_Adapter extends RecyclerView.Adapter<Size_Adapter.Productdetailhoder> {
    List<ProductDetails> list;
    Context context;
    int selectedPos = 0;
    Get_Money get_money;
    public Size_Adapter(List<ProductDetails> list, Context context, Get_Money get_money) {
        this.list = list;
        this.context = context;
        this.get_money = get_money;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Productdetailhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_size, parent, false);
        return new Productdetailhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Productdetailhoder holder, int position) {

        ProductDetails ct_san_pham = list.get(position);
        holder.textView_Size.setChecked(position == selectedPos);
        if (holder.textView_Size.isChecked()) {
            get_money.Get_Money(ct_san_pham.getId_productdetails(),ct_san_pham.getPrice(),ct_san_pham.getPromotionalprice());
        }
        holder.textView_Size.setText(ct_san_pham.getSize());
        holder.textView_Size.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedPos = holder.getAdapterPosition();
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class Productdetailhoder extends RecyclerView.ViewHolder {
        CardView card_view;
        RadioButton textView_Size;
        TextView txt_tien1;
        public Productdetailhoder(@NonNull View itemView) {
            super(itemView);
            card_view = itemView.findViewById(R.id.card_view);
            textView_Size = itemView.findViewById(R.id.textView_Size);
            txt_tien1 = itemView.findViewById(R.id.txt_tien1);

        }
    }

}
