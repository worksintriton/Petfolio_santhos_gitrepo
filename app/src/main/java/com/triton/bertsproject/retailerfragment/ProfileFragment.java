package com.triton.bertsproject.retailerfragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.LoginActivity;
import com.triton.bertsproject.activities.RegisterActivity;
import com.triton.bertsproject.retailer.MyWishlistActivity;
import com.triton.bertsproject.retailer.OrderListActivity;
import com.triton.bertsproject.retailer.RetailerCartActivity;
import com.triton.bertsproject.retailer.RetailerDashboardActivity;
import com.triton.bertsproject.retailer.RetailerOrderTrackActivity;
import com.triton.bertsproject.retailer.RetailerProfileAccountActivity;
import com.triton.bertsproject.retailer.RetailerSetttingsActivity;
import com.triton.bertsproject.retailer.ShippingAddressActivity;
import com.triton.bertsproject.sessionmanager.Connectivity;
import com.triton.bertsproject.sessionmanager.SessionManager;

import java.util.HashMap;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_wishlist)
    RelativeLayout rl_wishlist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_ordrhist)
    RelativeLayout rl_ordrhist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_ordrtrack)
    RelativeLayout rl_ordrtrack;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rlEdit)
    RelativeLayout rlEdit;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_settings)
    RelativeLayout rl_settings;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_login_after)
    RelativeLayout rl_login_after;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_manageaddress)
    RelativeLayout rl_manageaddress;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_login_after)
    LinearLayout ll_login_after;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_logout)
    CardView cv_logout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_before_login)
    CardView cv_before_login;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_username)
    TextView txt_username;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_profile)
    ImageView img_profile;

    private static final String TAG = "ProfileFragment";

    View view;

    String userid,username,imgUrl;

    SessionManager sessionManager;

    Dialog dialog;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_signin)
    Button btn_sigin;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_user_login)
    TextView txt_user_login;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cart_count)
    TextView txt_cart_count;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rlcart)
    RelativeLayout rlcart;

    String cart_count ="0";

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        // create ContextThemeWrapper from the original Activity Context with the custom theme
//        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Fragment);
//
//        // clone the inflater using the ContextThemeWrapper
//        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

        Objects.requireNonNull(getContext()).getTheme().applyStyle(R.style.Fragment, true);

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, view);

        Log.w("Oncreate ", TAG);

        sessionManager = new SessionManager(getContext());

        HashMap<String, String> user = sessionManager.getProfileDetails();

        if(sessionManager.isLoggedIn())
        {

            rl_login_after.setVisibility(View.VISIBLE);

            ll_login_after.setVisibility(View.VISIBLE);

            cv_before_login.setVisibility(View.GONE);

            userid = user.get(SessionManager.KEY_ID);

            imgUrl = user.get(SessionManager.KEY_PROFILE_IMAGE);

            username = user.get(SessionManager.KEY_FIRST_NAME) + " " + user.get(SessionManager.KEY_LAST_NAME);

            txt_username.setText(" "+username);

            Glide.with(getContext())
                    .load(imgUrl)
                    .into(img_profile);

            Log.w(TAG, "Session Details : userid " +userid + " Username "+ username +" Image Url "+ imgUrl );

            cv_logout.setOnClickListener(this);

            rl_wishlist.setOnClickListener(v -> {

                Intent intent = new Intent(getContext(), MyWishlistActivity.class);

                intent.putExtra("fromactivity",TAG);

                startActivity(intent);

                Animatoo.animateSwipeLeft(Objects.requireNonNull(getContext()));
            });

            rl_ordrhist.setOnClickListener(v -> {

                Intent intent = new Intent(getContext(), OrderListActivity.class);

                intent.putExtra("fromactivity",TAG);

                startActivity(intent);

                Animatoo.animateSwipeRight(Objects.requireNonNull(getContext()));

            });

            rl_ordrtrack.setOnClickListener(v -> {

                Intent intent = new Intent(getContext(), RetailerOrderTrackActivity.class);

                intent.putExtra("fromactivity",TAG);

                startActivity(intent);

                Animatoo.animateSwipeRight(Objects.requireNonNull(getContext()));

            });

            rl_settings.setOnClickListener(v -> {

                Intent intent = new Intent(getContext(), RetailerSetttingsActivity.class);

                intent.putExtra("fromactivity",TAG);

                startActivity(intent);

                Animatoo.animateSwipeRight(Objects.requireNonNull(getContext()));

            });

            txt_username.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), RetailerProfileAccountActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeRight(Objects.requireNonNull(getContext()));

        });

            rl_manageaddress.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), ShippingAddressActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeRight(Objects.requireNonNull(getContext()));

        });

            Connectivity connectivity = new Connectivity();

            cart_count = connectivity.getData(getContext(),"Cart_Count");

            Log.w(TAG,"cart_count "+cart_count);

            if(cart_count!=null&&!cart_count.equals("0")){

                txt_cart_count.setText(""+cart_count);
            }

            else {

                txt_cart_count.setVisibility(View.GONE);
            }

        }

        else
        {
            cv_before_login.setVisibility(View.VISIBLE);

            rl_login_after.setVisibility(View.GONE);

            ll_login_after.setVisibility(View.GONE);

            txt_cart_count.setVisibility(View.GONE);

            txt_user_login.setOnClickListener(v -> {

                Intent intent = new Intent(getContext(), LoginActivity.class);

                intent.putExtra("fromactivity",TAG);

                startActivity(intent);

                Animatoo.animateSwipeLeft(Objects.requireNonNull(getContext()));
            });

            btn_sigin.setOnClickListener(v -> {

                Intent intent = new Intent(getContext(), RegisterActivity.class);

                intent.putExtra("fromactivity",TAG);

                startActivity(intent);

                Animatoo.animateSwipeRight(Objects.requireNonNull(getContext()));

            });



        }
        rlcart.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), RetailerCartActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeRight(Objects.requireNonNull(getContext()));

        });

        spin_kit_loadingView.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.cv_logout) {
            gotologout();
        }

    }

    private void gotologout() {

        try {

            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Alert")
                    .setContentText("Are you sure want to logout?")
                    .setCancelText("No")
                    .setConfirmText("Yes")
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {

                            sessionManager.logoutUser();

                            sessionManager.setIsLogin(false);

                            startActivity(new Intent(getContext(), RetailerDashboardActivity.class));

                            sDialog.dismiss();

                        }
                    })
                    .show();

//            dialog = new Dialog(getContext());
//
//            dialog.setContentView(R.layout.alert_logout_layout);
//
//            Button btn_no = dialog.findViewById(R.id.btn_no);
//
//            Button btn_yes = dialog.findViewById(R.id.btn_yes);
//
//            btn_yes.setOnClickListener(view -> {
//
//                dialog.dismiss();
//
//                sessionManager.logoutUser();
//
//                sessionManager.setIsLogin(false);
//
//                startActivity(new Intent(getContext(), RetailerDashboardActivity.class));
//
//            });
//
//            btn_no.setOnClickListener(view -> dialog.dismiss());
//
//            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//            dialog.show();
//
        }
//
        catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }
}