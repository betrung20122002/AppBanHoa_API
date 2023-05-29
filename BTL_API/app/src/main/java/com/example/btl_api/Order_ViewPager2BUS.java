package com.example.btl_api;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.btl_api.Fragment.CancelledFragment;
import com.example.btl_api.Fragment.DeliveredFragment;
import com.example.btl_api.Fragment.DeliveryFragment;
import com.example.btl_api.Fragment.WaitForConfirmationFragment;

public class Order_ViewPager2BUS extends FragmentStateAdapter {
    public Order_ViewPager2BUS(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new WaitForConfirmationFragment();
            case 1:
                return new DeliveryFragment();
            case 2:
                return new DeliveredFragment();
            case 3:
                return new CancelledFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}