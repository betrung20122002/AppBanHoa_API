package com.example.btl_api.Fragment;

import static com.example.btl_api.Activity.LoginActivity.Id_Account;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Activity.OrderActivity;
import com.example.btl_api.Adapter.Orderr_Adapter;
import com.example.btl_api.Model.order_;
import com.example.btl_api.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancelledFragment extends Fragment {
    private static RecyclerView RecyclerView_Cancelled;
    private static LinearLayout LinearLayout_Cancelled;
    private static OrderActivity orderActivity;
    public CancelledFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cancelled, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iutuid(view);
        getdata_CF();
    }

    public static void getdata_CF() {
        APIInterface.API.Getorder(Id_Account, 3).enqueue(new Callback<List<order_>>() {
            @Override
            public void onResponse(Call<List<order_>> call, Response<List<order_>> response) {
                if (response.isSuccessful()) {
                    RecyclerView_Cancelled.setVisibility(View.VISIBLE);
                    LinearLayout_Cancelled.setVisibility(View.GONE);
                    Orderr_Adapter order_adapterBUS = new Orderr_Adapter(response.body(),orderActivity);
                    RecyclerView_Cancelled.setLayoutManager(new LinearLayoutManager(orderActivity));
                    RecyclerView_Cancelled.setAdapter(order_adapterBUS);
                } else {
                    RecyclerView_Cancelled.setVisibility(View.GONE);
                    RecyclerView_Cancelled.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<order_>> call, Throwable t) {

            }
        });
    }

    private void iutuid(View view) {
        orderActivity= (OrderActivity) getActivity();
        RecyclerView_Cancelled=view.findViewById(R.id.RecyclerView_Cancelled);
        LinearLayout_Cancelled=view.findViewById(R.id.LinearLayout_Cancelled);
    }
}