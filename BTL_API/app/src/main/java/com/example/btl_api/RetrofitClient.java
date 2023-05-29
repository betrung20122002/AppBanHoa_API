package com.example.btl_api;


import android.accounts.Account;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.btl_api.Activity.LoginActivity.Id_Account;
import static com.example.btl_api.Activity.MainActivity.currencyVN;
import static com.example.btl_api.Activity.EditAccountActivity.AddAccount;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.btl_api.API.APIInterface;
import com.example.btl_api.Model.Accounts;
import com.example.btl_api.Model.Product;
import com.example.btl_api.Model.ProductDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitClient {
    public static Animation ANIMATIONUP;
    public static Animation ANIMATIONDOWN;
    public static void ANIMATION(Context context) {
        ANIMATIONUP = AnimationUtils.loadAnimation(context, R.anim.up);
        ANIMATIONDOWN = AnimationUtils.loadAnimation(context, R.anim.down);
    }
    public static void dia(Dialog dialog) {
        dialog.setContentView(R.layout.load);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
    }

}
