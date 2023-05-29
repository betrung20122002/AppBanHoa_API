package com.example.btl_api.Adapter;

import static com.example.btl_api.Activity.LoginActivity.Id_Account;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.Model.Messege;
import com.example.btl_api.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Messege2_Adapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<Messege> messagesAdpterArrayList;
    int ITEM_SEND = 1;
    int ITEM_RECIVE = 2;

    public Messege2_Adapter(Context context, ArrayList<Messege> messagesAdpterArrayList) {
        this.context = context;
        this.messagesAdpterArrayList = messagesAdpterArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SEND) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_messege, parent, false);
            return new Messege2_Adapter.senderVierwHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.reciver_layout, parent, false);
            return new Messege2_Adapter.reciverViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Messege messages = messagesAdpterArrayList.get(position);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context).setTitle("Delete")
                        .setMessage("Bạn có muốn xóa tin nhắn này không ?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                APIInterface.API.DeleteMessge(messages.getId_MaGui()).enqueue(new Callback<Messege>() {
                                    @Override
                                    public void onResponse(Call<Messege> call, Response<Messege> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<Messege> call, Throwable t) {

                                    }
                                });
                            }
                        }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();

                return false;
            }
        });
        if (holder.getClass() == Messeger_Adapter.senderVierwHolder.class) {
            Messeger_Adapter.senderVierwHolder viewHolder = (Messeger_Adapter.senderVierwHolder) holder;
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
        } else {
            Messeger_Adapter.reciverViewHolder viewHolder = (Messeger_Adapter.reciverViewHolder) holder;
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
        if (messagesAdpterArrayList != null) {
            return messagesAdpterArrayList.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        Messege messages = messagesAdpterArrayList.get(position);
        if (messages.getId_MaGui() == Id_Account) {
            return ITEM_SEND;
        } else {
            return ITEM_RECIVE;
        }
    }

    class senderVierwHolder extends RecyclerView.ViewHolder {
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
