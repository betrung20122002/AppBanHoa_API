package com.example.btl_api.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static com.example.btl_api.Activity.LoginActivity.Id_Account;
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

public class WaitForConfirmationFragment extends Fragment {
    public static RecyclerView RecyclerView_WFconfirmation;
    public static LinearLayout LinearLayout_WFconfirmation;
    public static OrderActivity orderActivity;
    public WaitForConfirmationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wait_for_confirmation, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iutuid(view);
        getdata_WCF();
    }

    public static void getdata_WCF() {
        APIInterface.API.Getorder(Id_Account, 0).enqueue(new Callback<List<order_>>() {
            @Override
            public void onResponse(Call<List<order_>> call, Response<List<order_>> response) {
                if (response.isSuccessful()) {
                    RecyclerView_WFconfirmation.setVisibility(View.VISIBLE);
                    LinearLayout_WFconfirmation.setVisibility(View.GONE);
                    Orderr_Adapter order_adapterBUS = new Orderr_Adapter(response.body(), orderActivity);
                    RecyclerView_WFconfirmation.setLayoutManager(new LinearLayoutManager(orderActivity));
                    RecyclerView_WFconfirmation.setAdapter(order_adapterBUS);
                } else {
                    RecyclerView_WFconfirmation.setVisibility(View.GONE);
                    LinearLayout_WFconfirmation.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<order_>> call, Throwable t) {

            }
        });
    }

    private void iutuid(View view) {
        orderActivity= (OrderActivity) getActivity();
        RecyclerView_WFconfirmation=view.findViewById(R.id.RecyclerView_WFconfirmation);
        LinearLayout_WFconfirmation=view.findViewById(R.id.LinearLayout_WFconfirmation);
    }
}