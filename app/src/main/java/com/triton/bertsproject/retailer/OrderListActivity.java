package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.CartProductListAdapter;
import com.triton.bertsproject.adapter.OrderListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.model.OrderlistModel;
import com.triton.bertsproject.requestpojo.OrderCreateRequest;
import com.triton.bertsproject.requestpojo.ShowOrderlistRequest;
import com.triton.bertsproject.responsepojo.OrderCreateResponse;
import com.triton.bertsproject.responsepojo.ShowOrderlistResponse;
import com.triton.bertsproject.responsepojo.ShowOrderlistResponse;
import com.triton.bertsproject.sessionmanager.Connectivity;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.RestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderListActivity extends AppCompatActivity {

    private static final String TAG = "OrderListActivity";

//    public static String active_tag = "1";
//
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.bottomNavigation)
//    BottomNavigationView bottomNavigation;

    OrderListAdapter orderListAdapter;

    Context context = OrderListActivity.this;

//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.coordinator)
//    CoordinatorLayout coordinatorLayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    String fromactivity;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_orderlist)
    RecyclerView rv_orderlist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_continue_shop)
    Button btn_continue_shop;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cart_count)
    TextView txt_cart_count;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rlcart)
    RelativeLayout rlcart;

    String cart_count ="0";



    String tag;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    AlertDialog alertDialog;

    List<ShowOrderlistResponse.DataBean.OrdersBean> ordersBeanList ;

    SessionManager sessionManager;

    DD4YouConfig dd4YouConfig;

    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        ButterKnife.bind( this);
        Log.w("Oncreate", TAG);
        txt_toolbar_title.setText(R.string.order_history);
        floatingActionButton.setImageResource(R.drawable.berts_logo_fb);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fromactivity = extras.getString("fromactivity");
        }
        tag = getIntent().getStringExtra("tag");
        Log.w(TAG, " tag : " + this.tag);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getProfileDetails();

      user_id = user.get(SessionManager.KEY_ID);

       // user_id  = "541";

        img_back.setOnClickListener(v -> {

            onBackPressed();

        });

        dd4YouConfig = new DD4YouConfig(this);

        if (dd4YouConfig.isInternetConnectivity()) {

            ShowOrderlistResponseCall();

        }

        else
        {
            callnointernet();

        }

        Connectivity connectivity = new Connectivity();

        cart_count = connectivity.getData(context,"Cart_Count");

        Log.w(TAG,"cart_count "+cart_count);

        if(cart_count!=null&&!cart_count.equals("0")){

            txt_cart_count.setText(""+cart_count);
        }

        else {

            txt_cart_count.setVisibility(View.GONE);
        }

        rlcart.setOnClickListener(v -> {

            Intent intent = new Intent(context, RetailerCartActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeRight(context);

        });

    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(OrderListActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(OrderListActivity.this, OrderListActivity.class));
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @SuppressLint("LongLogTag")
    private void ShowOrderlistResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ShowOrderlistResponse> call = apiInterface.orderListResponseCall(RestUtils.getContentType(), ShowOrderlistRequest());
        Log.w(TAG,"ShowOrderlistResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<ShowOrderlistResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<ShowOrderlistResponse> call, @NonNull Response<ShowOrderlistResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "ShowOrderlistResponse" + new Gson().toJson(response.body()));

                        ordersBeanList=response.body().getData().getOrders();

                        if(ordersBeanList!=null&&ordersBeanList.size()>0){

                            rv_orderlist.setVisibility(View.VISIBLE);

                            txt_no_records.setVisibility(View.GONE);

                            setView(ordersBeanList);

                        }

                        else {

                            rv_orderlist.setVisibility(View.GONE);

                            txt_no_records.setVisibility(View.VISIBLE);

                            btn_continue_shop.setVisibility(View.VISIBLE);

                            txt_no_records.setText("Hurray ! It's Time to Grab Offers");

                            btn_continue_shop.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    callDirections("2");

                                }
                            });


                        }
                    }

                    else {

                        rv_orderlist.setVisibility(View.GONE);

                        txt_no_records.setVisibility(View.VISIBLE);

                        btn_continue_shop.setVisibility(View.VISIBLE);

                        txt_no_records.setText("Hurray ! It's Time to Grab Offers");

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
            public void onFailure(@NonNull Call<ShowOrderlistResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"ShowOrderlistResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private ShowOrderlistRequest ShowOrderlistRequest() {


        /*
         * MODE : LIST
         * USER_ID : 541
         */

        ShowOrderlistRequest ShowOrderlistRequest = new ShowOrderlistRequest();
        ShowOrderlistRequest.setUSER_ID(user_id);
        ShowOrderlistRequest.setMODE("LIST");

        Log.w(TAG,"ShowOrderlistRequest "+ new Gson().toJson(ShowOrderlistRequest));
        return ShowOrderlistRequest;
    }

    private void setView(List<ShowOrderlistResponse.DataBean.OrdersBean> ordersBeanList) {

        rv_orderlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        rv_orderlist.setMotionEventSplittingEnabled(false);

        rv_orderlist.setItemAnimator(new DefaultItemAnimator());

        orderListAdapter = new OrderListAdapter(this, ordersBeanList);

        rv_orderlist.setAdapter(orderListAdapter);

    }

    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(OrderListActivity.this);
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

    @Override
    public void onBackPressed() {

        callDirections("5");
    }

    public void callDirections(String tag){
        Intent intent = new Intent(OrderListActivity.this,RetailerDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();

    }
}