package com.example.btl_api.Adapter;

import static com.example.btl_api.Activity.LoginActivity.Id_Account;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.Model.Messege;
import com.example.btl_api.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Messeger_Adapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<Messege> messagesAdpterArrayList;
    int ITEM_SEND=1;
    int ITEM_RECIVE=2;

    public Messeger_Adapter(Context context, ArrayList<Messege> messagesAdpterArrayList) {
        this.context = context;
        this.messagesAdpterArrayList = messagesAdpterArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SEND){
            View view = LayoutInflater.from(context).inflate(R.layout.item_messege, parent, false);
            return new senderVierwHolder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.reciver_layout, parent, false);
            return new reciverViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Messege messages = messagesAdpterArrayList.get(position);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context).setTitle("Delete")
                        .setMessage("Are you sure you want to delete this message?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();

                return false;
            }
        });
        if (holder.getClass()==senderVierwHolder.class){
            senderVierwHolder viewHolder = (senderVierwHolder) holder;
            viewHolder.msgtxt.setText(messages.getNoidung());
           APIInterface.API.GetAccount(messages.getId_MaGui()).enqueue(new Callback<Accounts>() {
               @Override
               public void onResponse(Call<Accounts> call, Response<Accounts> response) {
                   Picasso.get().load(response.body().getPicture()).into(viewHolder.circleImageView);
               }

               @Override
               public void onFailure(Call<Accounts> call, Throwable t) {

               }
           });
        }else { reciverViewHolder viewHolder = (reciverViewHolder) holder;
            viewHolder.msgtxt.setText(messages.getNoidung());
            APIInterface.API.GetAccount(messages.getId_MaNhan()).enqueue(new Callback<Accounts>() {
                @Override
                public void onResponse(Call<Accounts> call, Response<Accounts> response) {
                    Picasso.get().load(response.body().getPicture()).into(viewHolder.circleImageView);
                }

                @Override
                public void onFailure(Call<Accounts> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (messagesAdpterArrayList!=null){
            return messagesAdpterArrayList.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        Messege messages = messagesAdpterArrayList.get(position);
        if (messages.getId_MaGui() ==Id_Account) {
            return ITEM_SEND;
        } else {
            return ITEM_RECIVE;
        }
    }

    class  senderVierwHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView msgtxt;
        public senderVierwHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.profilerggg);
            msgtxt = itemView.findViewById(R.id.textView_Messeger);

        }
    }
    class reciverViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView msgtxt;
        public reciverViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.pro);
            msgtxt = itemView.findViewById(R.id.recivertextset);
        }
    }
}
