package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.LoginActivity;
import com.triton.bertsproject.adapter.MywishListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.interfaces.WishlistAddProductListener;
import com.triton.bertsproject.model.RetailerProductlistModel;
import com.triton.bertsproject.requestpojo.AddWishistRequest;
import com.triton.bertsproject.requestpojo.RemoveWishistRequest;
import com.triton.bertsproject.requestpojo.ShowWishistRequest;
import com.triton.bertsproject.responsepojo.ProductListResponse;
import com.triton.bertsproject.responsepojo.WishlistSuccessResponse;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.RestUtils;
import com.triton.bertsproject.utils.SwipeToDeleteCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyWishlistActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, WishlistAddProductListener {

    private static final String TAG = "MyWishlistActivity";

    public static String active_tag = "1";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;

    MywishListAdapter mywishListAdapter;

    Context context = MyWishlistActivity.this;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinatorLayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    String fromactivity;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_productlist)
    RecyclerView rv_productlist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    String tag;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_continue_shop)
    Button btn_continue_shop;

   List<WishlistSuccessResponse.DataBean.WishlistBean> wishlistBeanList ;

    String user_id;

//    private DD4YouNetReceiver dd4YouNetReceiver;

    private DD4YouConfig dd4YouConfig;

    AlertDialog alertDialog;

    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wishlist);

        ButterKnife.bind( this);
        Log.w("Oncreate", TAG);
        txt_toolbar_title.setText(R.string.my_wishlist);
        floatingActionButton.setImageResource(R.drawable.berts_logo_fb);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fromactivity = extras.getString("fromactivity");
        }
        spin_kit_loadingView.setVisibility(View.GONE);
        tag = getIntent().getStringExtra("tag");
        Log.w(TAG, " tag : " + tag);

        sessionManager=new SessionManager(this);

        HashMap<String, String> user = sessionManager.getProfileDetails();

        user_id = user.get(SessionManager.KEY_ID);

        //user_id  = "541";

        bottomNavigation.setSelectedItemId(R.id.shop);

        bottomNavigation.setOnNavigationItemSelectedListener(this);

        img_back.setOnClickListener(v -> {

            startActivity(new Intent(MyWishlistActivity.this, RetailerDashboardActivity.class));

            Animatoo.animateSwipeRight(context);

        });


        dd4YouConfig = new DD4YouConfig(this);

        //enableSwipeToDeleteAndUndo();

        if (dd4YouConfig.isInternetConnectivity()) {

            showwishlistResponseCall();

        }

        else
        {
            callnointernet();

        }

    }


    private void setView(List<WishlistSuccessResponse.DataBean.WishlistBean> wishlistBeanList) {

        rv_productlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        rv_productlist.setMotionEventSplittingEnabled(false);

        rv_productlist.setItemAnimator(new DefaultItemAnimator());

        mywishListAdapter = new MywishListAdapter(this, wishlistBeanList,this);

        rv_productlist.setAdapter(mywishListAdapter);

    }

    @SuppressLint("NonConstantResourceId")
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chat:
                active_tag = "4";
                return true;
            case R.id.garage:
                active_tag = "2";
                return true;
            case R.id.home:
                active_tag = "1";
                return true;
            case R.id.profile:
                active_tag = "5";
                return true;
            case R.id.shop:
                active_tag = "3";
                return true;
            default:
                return false;
        }
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onBackPressed() {
        startActivity(new Intent(this, RetailerDashboardActivity.class));
        Animatoo.animateSwipeRight(this.context);
    }


    @SuppressLint("LongLogTag")
    private void showwishlistResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<WishlistSuccessResponse> call = apiInterface.showwishlistResponseCall(RestUtils.getContentType(),showWishistRequest());
        Log.w(TAG,"WishlistSuccessResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<WishlistSuccessResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<WishlistSuccessResponse> call, @NonNull Response<WishlistSuccessResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "WishlistSuccessResponse" + new Gson().toJson(response.body()));

                        wishlistBeanList =response.body().getData().getWishlist();

                        if(wishlistBeanList!=null&&wishlistBeanList.size()>0){

                            rv_productlist.setVisibility(View.VISIBLE);

                            txt_no_records.setVisibility(View.GONE);

                            btn_continue_shop.setVisibility(View.GONE);

                            setView(wishlistBeanList);

                        }

                        else {

                            rv_productlist.setVisibility(View.GONE);

                            txt_no_records.setVisibility(View.VISIBLE);

                            btn_continue_shop.setVisibility(View.VISIBLE);

                            txt_no_records.setText("Your wishlist seems empty!");

                            btn_continue_shop.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    callDirections("2");

                                }
                            });
                        }
                    }

                    else {

                        rv_productlist.setVisibility(View.GONE);

                        txt_no_records.setVisibility(View.VISIBLE);

                        btn_continue_shop.setVisibility(View.VISIBLE);

                        txt_no_records.setText("Your wishlist seems empty!");

                        btn_continue_shop.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                callDirections("2");

                            }
                        });
                    }

                }



            }


            @Override
            public void onFailure(@NonNull Call<WishlistSuccessResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"WishlistSuccessResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private ShowWishistRequest showWishistRequest() {

        /*
         * USER_ID : 541
         * MODE : LIST
         */


        ShowWishistRequest showWishistRequest = new ShowWishistRequest();
        showWishistRequest.setUSER_ID(user_id);
        showWishistRequest.setMODE("LIST");

        Log.w(TAG,"ShowWishistRequest "+ new Gson().toJson(showWishistRequest));
        return showWishistRequest;
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(MyWishlistActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(MyWishlistActivity.this,MyWishlistActivity.class));
            finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyWishlistActivity.this);
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoading());


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void hideLoading(){
        try {
            alertDialog.dismiss();
        }catch (Exception ignored){

        }
    }


    @Override
    public void addproductListener(String id) {



        if(sessionManager.isLoggedIn()){

            if (dd4YouConfig.isInternetConnectivity()) {

                wishlistaddResponseCall(id);

            }

            else
            {
                callnointernet();

            }

        }

        else {

            showAlert();
        }


    }

    private void showAlert() {

        AlertDialog.Builder builder=new AlertDialog.Builder(MyWishlistActivity.this);
        builder.setTitle("Alert");
        builder.setMessage("Please Login to add Products in wishlist");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", (dialogInterface, i) -> {

            gotoLogin();

        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void gotoLogin() {

        Intent intent = new Intent(MyWishlistActivity.this, LoginActivity.class);

        intent.putExtra("fromActivity",TAG);

        startActivity(intent);

        finish();

    }



    @SuppressLint("LongLogTag")
    private void wishlistaddResponseCall(String productId) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<WishlistSuccessResponse> call = apiInterface.wishlistaddResponseCall(RestUtils.getContentType(),addWishistRequest(productId));
        Log.w(TAG,"WishlistSuccessResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<WishlistSuccessResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<WishlistSuccessResponse> call, @NonNull Response<WishlistSuccessResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "WishlistSuccessResponse" + new Gson().toJson(response.body()));

                        Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                        showwishlistResponseCall();

                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }

                }



            }


            @Override
            public void onFailure(@NonNull Call<WishlistSuccessResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"WishlistSuccessResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private AddWishistRequest addWishistRequest(String productID) {

        /*
         * USER_ID : 541
         * PRODUCT_ID : 4
         * MODE : ADD_DELETE
         */

        AddWishistRequest addWishistRequest = new AddWishistRequest();
        addWishistRequest.setPRODUCT_ID(productID);
        addWishistRequest.setUSER_ID(user_id);
        addWishistRequest.setMODE("ADD_DELETE");

        Log.w(TAG,"AddWishistRequest "+ new Gson().toJson(addWishistRequest));
        return addWishistRequest;
    }

    public void callDirections(String tag){
        Intent intent = new Intent(MyWishlistActivity.this,RetailerDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();

    }

}