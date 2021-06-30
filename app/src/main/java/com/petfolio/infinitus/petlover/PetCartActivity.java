package com.petfolio.infinitus.petlover;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.petfolio.infinitus.R;
import com.petfolio.infinitus.adapter.Cart_Adapter;
import com.petfolio.infinitus.api.APIClient;
import com.petfolio.infinitus.api.RestApiInterface;

import com.petfolio.infinitus.fragmentpetlover.bottommenu.PetCareFragment;
import com.petfolio.infinitus.fragmentpetlover.bottommenu.PetHomeNewFragment;
import com.petfolio.infinitus.fragmentpetlover.bottommenu.PetServicesFragment;
import com.petfolio.infinitus.fragmentpetlover.bottommenu.VendorShopFragment;
import com.petfolio.infinitus.interfaces.AddandRemoveProductListener;
import com.petfolio.infinitus.requestpojo.FetchByIdRequest;
import com.petfolio.infinitus.requestpojo.VendorOrderBookingCreateRequest;
import com.petfolio.infinitus.responsepojo.CartDetailsResponse;
import com.petfolio.infinitus.responsepojo.CartSuccessResponse;
import com.petfolio.infinitus.responsepojo.SuccessResponse;
import com.petfolio.infinitus.sessionmanager.SessionManager;
import com.petfolio.infinitus.utils.ConnectionDetector;
import com.petfolio.infinitus.utils.RestUtils;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.wang.avi.AVLoadingIndicatorView;


import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetCartActivity extends AppCompatActivity implements AddandRemoveProductListener, PaymentResultListener, View.OnClickListener {

    private String TAG = "PetCartActivity";


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;
/*

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottom_navigation_view;
*/

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_footer)
    View include_petlover_footer;

    BottomNavigationView bottom_navigation_view;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_cart)
    RecyclerView rv_cart;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_lbl_subtotal)
    TextView txt_lbl_subtotal;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_sub_total)
    TextView txt_sub_total;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_discount_amount)
    TextView txt_discount_amount;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipping_amount)
    TextView txt_shipping_amount;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_total_amount)
    TextView txt_total_amount;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_procced_to_buy)
    Button btn_procced_to_buy;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_cart_is_empty)
    LinearLayout ll_cart_is_empty;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_content)
    LinearLayout ll_content;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.scrollablContent)
    ScrollView scrollablContent;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.footerView)
    LinearLayout footerView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_content_amount)
    LinearLayout ll_content_amount;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_removeall_products)
    TextView txt_removeall_products;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cart_count_badge)
    TextView txt_cart_count_badge;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_shopnow)
    Button btn_shopnow;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_profile)
    ImageView img_profile;




    String tag;
    String fromactivity;

    private String userid;
    private String productid;
    private String productdetails_productid;

    List<CartDetailsResponse.DataBean> Data = new ArrayList<>();
    private int prodouct_total;
    private int shipping_charge;
    private int discount_price;
    private int grand_total;
    private int prodcut_count;
    private int prodcut_item_count;
    private String Payment_id = "";

    private int product_cart_counts = 0;
    private String threshould;

    private String active_tag;
    private String cat_id;

    /* Petlover Bottom Navigation */

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_home)
    RelativeLayout rl_home;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_home)
    TextView title_home;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_home)
    ImageView img_home;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_care)
    RelativeLayout rl_care;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_care)
    TextView title_care;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_care)
    ImageView img_care;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_service)
    RelativeLayout rl_service;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_serv)
    TextView title_serv;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_serv)
    ImageView img_serv;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_shop)
    RelativeLayout rl_shop;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_shop)
    TextView title_shop;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_shop)
    ImageView img_shop;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_comn)
    RelativeLayout rl_comn;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_community)
    TextView title_community;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_community)
    ImageView img_community;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_homes)
    RelativeLayout rl_homes;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_services)
    RelativeLayout rl_services;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_shops)
    RelativeLayout rl_shops;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_cares)
    RelativeLayout rl_cares;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_comns)
    RelativeLayout rl_comns;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.root_nav)
    LinearLayout root_nav;


    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_cart);
        ButterKnife.bind(this);
        Log.w(TAG,"onCreate");

        scrollablContent.setVisibility(View.GONE);
        btn_procced_to_buy.setVisibility(View.GONE);
        ll_content_amount.setVisibility(View.GONE);
        footerView.setVisibility(View.GONE);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            productdetails_productid = extras.getString("productid");
            fromactivity = extras.getString("fromactivity");
            active_tag = extras.getString("active_tag");
            cat_id = extras.getString("cat_id");
        }


        img_back.setOnClickListener(v -> onBackPressed());

            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                fetch_cart_details_by_userid_Call();
            }

        btn_shopnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDirections("2");

            }
        });
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PetLoverProfileScreenActivity.class));
            }
        });


        btn_procced_to_buy.setOnClickListener(v -> {
            if(grand_total!=0){

                Intent i = new Intent(getApplicationContext(), ShippingAddressActivity.class);
                i.putExtra("fromactivity",TAG);
                i.putExtra("data", (Serializable) Data);
                i.putExtra("product_total",prodouct_total);
                i.putExtra("shipping_charge",shipping_charge);
                i.putExtra("discount_price",discount_price);
                i.putExtra("grand_total",grand_total);
                i.putExtra("prodcut_count",prodcut_count);
                i.putExtra("prodcut_item_count",prodcut_item_count);
                startActivity(i);

            }
        });

            txt_removeall_products.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove_overall_products_ResponseCall();
                }
            });



        root_nav.setBackgroundResource(R.drawable.nav__shop);
        rl_homes.setVisibility(View.INVISIBLE);
        rl_cares.setVisibility(View.INVISIBLE);
        rl_services.setVisibility(View.INVISIBLE);
        rl_shops.setVisibility(View.VISIBLE);
        rl_comns.setVisibility(View.INVISIBLE);
        setMargins(rl_homes,0,0,0,0);
        setMargins(rl_cares,0,0,0,0);
        setMargins(rl_shops,0,0,30,0);
        setMargins(rl_services,0,0,0,0);
        setMargins(rl_comns,0,0,0,0);
        rl_home.setVisibility(View.VISIBLE);
        rl_shop.setVisibility(View.INVISIBLE);
        rl_service.setVisibility(View.VISIBLE);
        rl_care.setVisibility(View.VISIBLE);
        rl_comn.setVisibility(View.VISIBLE);
        title_home.setVisibility(View.VISIBLE);
        img_home.setVisibility(View.VISIBLE);
        title_care.setVisibility(View.VISIBLE);
        img_care.setVisibility(View.VISIBLE);
        title_serv.setVisibility(View.VISIBLE);
        img_serv.setVisibility(View.VISIBLE);
        title_shop.setVisibility(View.INVISIBLE);
        img_shop.setVisibility(View.INVISIBLE);
        title_community.setVisibility(View.VISIBLE);
        img_community.setVisibility(View.VISIBLE);
        title_care.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_care.setImageResource(R.drawable.grey_care);
        title_serv.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_serv.setImageResource(R.drawable.grey_servc);
        title_home.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_home.setImageResource(R.drawable.grey_home);
        title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_community.setImageResource(R.drawable.grey_community);

        rl_home.setOnClickListener(this);

        rl_care.setOnClickListener(this);

        rl_service.setOnClickListener(this);

        rl_shop.setOnClickListener(this);

        rl_comn.setOnClickListener(this);


        rl_homes.setOnClickListener(this);

        rl_cares.setOnClickListener(this);

        rl_services.setOnClickListener(this);

        rl_shops.setOnClickListener(this);

        rl_comns.setOnClickListener(this);




    }

    private void fetch_cart_details_by_userid_Call() {
        if(userid != null){
            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                fetch_cart_details_by_userid_ResponseCall();
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(fromactivity != null && fromactivity.equalsIgnoreCase("ProductDetailsActivity")){
            Intent i = new Intent(PetCartActivity.this, ProductDetailsActivity.class);
            i.putExtra("productid",productdetails_productid);
            startActivity(i);
            finish();
        }else if(fromactivity != null && fromactivity.equalsIgnoreCase("PetShopTodayDealsSeeMoreActivity")){
            Intent i = new Intent(PetCartActivity.this, PetShopTodayDealsSeeMoreActivity.class);
            startActivity(i);
            finish();
        }else if(fromactivity != null && fromactivity.equalsIgnoreCase("ListOfProductsSeeMoreActivity")){
            Intent i = new Intent(PetCartActivity.this, ListOfProductsSeeMoreActivity.class);
            i.putExtra("cat_id",cat_id);
            startActivity(i);
            finish();
        }else if(active_tag != null){
            callDirections(active_tag);
        } else{
            Intent i = new Intent(PetCartActivity.this, PetLoverDashboardActivity.class);
            startActivity(i);
            finish();
        }

    }

    @SuppressLint("LogNotTimber")
    public void fetch_cart_details_by_userid_ResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<CartDetailsResponse> call = ApiService.fetch_cart_details_by_userid_ResponseCall(RestUtils.getContentType(),fetchByIdRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());
        call.enqueue(new Callback<CartDetailsResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<CartDetailsResponse> call, @NonNull Response<CartDetailsResponse> response) {
                avi_indicator.smoothToHide();
                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"CartDetailsResponse" + new Gson().toJson(response.body()));
                        footerView.setVisibility(View.VISIBLE);

                        if(response.body().getData() != null && response.body().getData().size()>0){
                            txt_cart_count_badge.setText(response.body().getData().size()+"");
                            scrollablContent.setVisibility(View.VISIBLE);
                            ll_content.setVisibility(View.VISIBLE);
                            ll_cart_is_empty.setVisibility(View.GONE);
                            btn_procced_to_buy.setVisibility(View.VISIBLE);
                            ll_content_amount.setVisibility(View.VISIBLE);

                            Data = response.body().getData();
                            for(int i=0;i<Data.size();i++){
                                threshould = Data.get(i).getProduct_id().getThreshould();

                            }
                            setView(response.body().getData());

                            if(response.body().getProdcut_item_count() != 0){
                                txt_lbl_subtotal.setText("Subtotal ( "+response.body().getProdcut_item_count()+" items)" );
                            }
                            if(response.body().getProdouct_total() != 0){
                                txt_sub_total.setText(" \u20B9 "+response.body().getProdouct_total());
                            }else{
                                txt_sub_total.setText("\u20B9 "+0);

                            }
                            if(response.body().getDiscount_price() != 0){
                                txt_discount_amount.setText(" \u20B9 "+response.body().getDiscount_price());
                            }else{
                                txt_discount_amount.setText(" \u20B9 "+0);
                            }
                            if(response.body().getShipping_charge() != 0){
                                txt_shipping_amount.setText(" \u20B9 "+response.body().getShipping_charge());
                            }else{
                                txt_shipping_amount.setText(" \u20B9 "+0);

                            }
                            if(response.body().getGrand_total() != 0){
                                txt_total_amount.setText(" \u20B9 "+response.body().getGrand_total());
                            }else{
                                txt_total_amount.setText(" \u20B9 "+0);

                            }






                        }
                        else {
                            txt_cart_count_badge.setText("0");
                            scrollablContent.setVisibility(View.VISIBLE);
                            ll_content.setVisibility(View.GONE);
                            ll_cart_is_empty.setVisibility(View.VISIBLE);
                            btn_procced_to_buy.setVisibility(View.GONE);
                            ll_content_amount.setVisibility(View.GONE);

                        }

                        if(response.body() != null) {
                            prodouct_total = response.body().getProdouct_total();
                            shipping_charge = response.body().getShipping_charge();
                            discount_price  = response.body().getDiscount_price();
                            grand_total = response.body().getGrand_total();
                            prodcut_count = response.body().getProdcut_count();
                            prodcut_item_count  = response.body().getProdcut_item_count();
                        }



                    }
                }
            }


            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<CartDetailsResponse> call, @NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"CartDetailsResponse flr"+t.getMessage());
            }
        });

    }

    private void setView(List<CartDetailsResponse.DataBean> data) {
        rv_cart.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv_cart.setItemAnimator(new DefaultItemAnimator());
        Cart_Adapter cart_adapter = new Cart_Adapter(getApplicationContext(), data,this);
        rv_cart.setAdapter(cart_adapter);
    }

    @SuppressLint("LogNotTimber")
    private FetchByIdRequest fetchByIdRequest() {
        /*
         * user_id : 603e27792c2b43125f8cb802
         */
        FetchByIdRequest fetchByIdRequest = new FetchByIdRequest();
        fetchByIdRequest.setUser_id(userid);
        Log.w(TAG,"fetchByIdRequest"+ "--->" + new Gson().toJson(fetchByIdRequest));
        return fetchByIdRequest;
    }

    @SuppressLint("LogNotTimber")
    @Override
    public void addandRemoveProductListener(String id, String name,String threshould,int prodcutcount) {
        if(name != null){
            if(name.equalsIgnoreCase("add")){
                if(threshould != null){
                    productid = id;
                    int productqty = Integer.parseInt(threshould);
                    Log.w(TAG," productqty : "+productqty+" prodcutcount : "+prodcutcount);
                    if(prodcutcount == productqty || prodcutcount >productqty){
                        Toasty.warning(getApplicationContext(), "You can buy only up to "+productqty+" quantity of this product", Toast.LENGTH_SHORT, true).show();
                    }else{
                        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                            add_product_ResponseCall();
                        }
                    }
                }

            }
            else if(name.equalsIgnoreCase("remove")){
                productid = id;
                if(productid != null){
                    if(prodcutcount != 0) {
                        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                            remove_product_ResponseCall();
                        }
                    }
                }
            }
            else if(name.equalsIgnoreCase("singleproductremove")){
                productid = id;
                if(productid != null){
                    if(prodcutcount != 0) {
                        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                            remove_single_products_ResponseCall();
                        }
                    }
                }
            }
        }
    }
    @SuppressLint("LogNotTimber")
    public void remove_product_ResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = ApiService.remove_product_ResponseCall(RestUtils.getContentType(),addandRemoveCartRequest());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {
                avi_indicator.smoothToHide();
                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"Remove SuccessResponse" + new Gson().toJson(response.body()));
                        Toasty.success(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                        fetch_cart_details_by_userid_Call();
                    }
                }
            }


            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<SuccessResponse> call, @NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"Remove SuccessResponse flr"+t.getMessage());
            }
        });

    }
    public void remove_single_products_ResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = ApiService.remove_single_products_ResponseCall(RestUtils.getContentType(),addandRemoveCartRequest());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {
                avi_indicator.smoothToHide();
                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"Remove SuccessResponse" + new Gson().toJson(response.body()));
                        Toasty.success(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                        fetch_cart_details_by_userid_Call();
                    }
                }
            }


            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<SuccessResponse> call, @NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"Remove SuccessResponse flr"+t.getMessage());
            }
        });

    }
    public void remove_overall_products_ResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = ApiService.remove_overall_products_ResponseCall(RestUtils.getContentType(),removeAllProductsRequest());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {
                avi_indicator.smoothToHide();
                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"Remove SuccessResponse" + new Gson().toJson(response.body()));
                        Toasty.success(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                        fetch_cart_details_by_userid_Call();
                    }
                }
            }


            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<SuccessResponse> call, @NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"Remove SuccessResponse flr"+t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    public void add_product_ResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = ApiService.add_product_ResponseCall(RestUtils.getContentType(),addandRemoveCartRequest());

        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {
                avi_indicator.smoothToHide();


                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"Add SuccessResponse" + new Gson().toJson(response.body()));
                        Toasty.success(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                        fetch_cart_details_by_userid_Call();


                    }
                }
            }


            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<SuccessResponse> call, @NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"Add SuccessResponse flr"+t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private FetchByIdRequest addandRemoveCartRequest() {
        /*
         * user_id : 603e27792c2b43125f8cb802
         * product_id : 6034d6a5888af7628e7e17d4
         */
        FetchByIdRequest fetchByIdRequest = new FetchByIdRequest();
        fetchByIdRequest.setUser_id(userid);
        fetchByIdRequest.setProduct_id(productid);
        Log.w(TAG,"fetchByIdRequest"+ "--->" + new Gson().toJson(fetchByIdRequest));
        return fetchByIdRequest;
    }
    private FetchByIdRequest removeAllProductsRequest() {
        /*
         * user_id : 603e27792c2b43125f8cb802
       */
        FetchByIdRequest fetchByIdRequest = new FetchByIdRequest();
        fetchByIdRequest.setUser_id(userid);
        Log.w(TAG,"fetchByIdRequest"+ "--->" + new Gson().toJson(fetchByIdRequest));
        return fetchByIdRequest;
    }
    @SuppressLint("LogNotTimber")
    public void vendor_order_booking_create_ResponseCall(){
       avi_indicator.setVisibility(View.VISIBLE);
       avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<CartSuccessResponse> call = ApiService.vendor_order_booking_create_ResponseCall(RestUtils.getContentType(),vendorOrderBookingCreateRequest());

       Log.w(TAG,"url  :%s"+ call.request().url().toString());

       call.enqueue(new Callback<CartSuccessResponse>() {
            @SuppressLint({"LogNotTimber", "SetTextI18n"})
           @Override
           public void onResponse(@NonNull Call<CartSuccessResponse> call, @NonNull Response<CartSuccessResponse> response) {
                avi_indicator.smoothToHide();
                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"SuccessResponse "+new Gson().toJson(response.body().getData()));

                        Toasty.success(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                        callDirections("2");




                    }
                }
            }


            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<CartSuccessResponse> call, @NonNull  Throwable t) {
            avi_indicator.smoothToHide();
               Log.w(TAG,"SuccessResponse flr"+t.getMessage());
          }
        });

    }

    @SuppressLint("LogNotTimber")
    private CartDetailsResponse vendorOrderBookingCreateRequest() {
        /*
         * user_id : 603e27792c2b43125f8cb802
         * Data : [{"_id":"6046fa59cb48ca0b68cda50c","user_id":"603e27792c2b43125f8cb802","product_id":{"breed_type":["602d1c20562e0916bc9b3218"],"pet_type":["602d1c6b562e0916bc9b321d"],"age":[3],"product_img":["http://54.212.108.156:3000/api/uploads/1614075552394.jpg"],"_id":"6034d6a5888af7628e7e17d4","user_id":"602a2061b3c2dd2c152d77d8","cat_id":"5fec14a5ea832e2e73c1fc79","cost":1000,"threshould":"100","product_name":"Cat Dinner","product_discription":"This cat  food","discount":10,"related":"","count":0,"status":"true","verification_status":"Not Verified","date_and_time":"Tue Feb 23 2021 15:49:15 GMT+0530 (India Standard Time)","mobile_type":"Admin","delete_status":true,"fav_status":false,"today_deal":true,"updatedAt":"2021-03-08T09:15:24.812Z","createdAt":"2021-02-23T10:19:17.691Z","__v":0},"product_count":7,"updatedAt":"2021-03-09T06:10:04.116Z","createdAt":"2021-03-09T04:32:25.151Z","__v":0},{"_id":"60471192760fff2968288bbd","user_id":"603e27792c2b43125f8cb802","product_id":{"breed_type":["602d1c17562e0916bc9b3217"],"pet_type":["602d1c6b562e0916bc9b321d"],"age":[3],"product_img":["http://54.212.108.156:3000/api/uploads/1614075490400.jpg"],"_id":"6034d66598fa826140f6a3a3","user_id":"602a2061b3c2dd2c152d77d8","cat_id":"5fec14a5ea832e2e73c1fc79","cost":40000,"threshould":"100","product_name":"CAT Lunch","product_discription":"This is cat lunch","discount":40,"related":"","count":0,"status":"true","verification_status":"Not Verified","date_and_time":"Tue Feb 23 2021 15:48:14 GMT+0530 (India Standard Time)","mobile_type":"Admin","delete_status":true,"fav_status":false,"today_deal":true,"updatedAt":"2021-03-08T09:15:22.710Z","createdAt":"2021-02-23T10:18:13.989Z","__v":0},"product_count":1,"updatedAt":"2021-03-09T06:11:30.904Z","createdAt":"2021-03-09T06:11:30.904Z","__v":0}]
         * prodouct_total : 47000
         * shipping_charge : 0
         * discount_price : 0
         * grand_total : 0
         * prodcut_count : 0
         * prodcut_item_count : 0
         * "date_of_booking_display" : "23-Jan-2020",
            "date_of_booking" : "23-10-2021  11 : 00 PM",
            "coupon_code" : "",
             "shipping_address_id" : "",
            "billling_address_id" : "",
            "shipping_address" : "",
             "billing_address" : "",
         */

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
        String currentDateandTime = simpleDateFormat.format(new Date());

        CartDetailsResponse vendorOrderBookingCreateRequest = new CartDetailsResponse();
        vendorOrderBookingCreateRequest.setUser_id(userid);
        vendorOrderBookingCreateRequest.setData(Data);
        vendorOrderBookingCreateRequest.setProdouct_total(prodouct_total);
        vendorOrderBookingCreateRequest.setShipping_charge(shipping_charge);
        vendorOrderBookingCreateRequest.setDiscount_price(discount_price);
        vendorOrderBookingCreateRequest.setGrand_total(grand_total);
        vendorOrderBookingCreateRequest.setProdcut_count(prodcut_count);
        vendorOrderBookingCreateRequest.setProdcut_item_count(prodcut_item_count);
        vendorOrderBookingCreateRequest.setDate_of_booking_display(currentDateandTime);
        vendorOrderBookingCreateRequest.setDate_of_booking(currentDateandTime);
        vendorOrderBookingCreateRequest.setCoupon_code("");
        vendorOrderBookingCreateRequest.setShipping_address_id("");
        vendorOrderBookingCreateRequest.setBillling_address_id("");
        vendorOrderBookingCreateRequest.setShipping_address("");
        vendorOrderBookingCreateRequest.setBilling_address("");
        vendorOrderBookingCreateRequest.setPayment_id(Payment_id);
        Log.w(TAG,"vendorOrderBookingCreateRequest"+ "--->" + new Gson().toJson(vendorOrderBookingCreateRequest));
        return vendorOrderBookingCreateRequest;
    }


    @SuppressLint({"LongLogTag", "LogNotTimber"})
    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        //totalamount = amount;

      /*  Double d = new Double(amount);
        int amout = d.intValue();*/


        Integer totalamout = grand_total*100;

        try {
            JSONObject options = new JSONObject();
            options.put("name", "PetFolio");
            options.put("description", userid);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", totalamout);


            co.open(activity, options);
        } catch (Exception e) {
            Log.w(TAG,"Error in payment: " + e.getMessage());

            e.printStackTrace();
        }
    }
    @SuppressLint({"LongLogTag", "LogNotTimber"})
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Payment_id = razorpayPaymentID;

            Log.w(TAG, "Payment Successful: " + razorpayPaymentID);
            Toasty.success(getApplicationContext(), "Payment Successful. View your booking details in upcoming appointments.", Toast.LENGTH_SHORT, true).show();


            if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
              vendor_order_booking_create_ResponseCall();

            }




        } catch (Exception e) {
            Log.w(TAG, "Exception in onPaymentSuccess", e);
        }
    }
    @SuppressLint({"LongLogTag", "LogNotTimber"})
    @Override
    public void onPaymentError(int code, String response) {
        try {
            if(new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {

            }
            Log.w(TAG,  "Payment failed: " + code + " " + response);
            Toasty.error(getApplicationContext(), "Payment failed. Please try again with another payment method..", Toast.LENGTH_SHORT, true).show();

        } catch (Exception e) {
            Log.w(TAG, "Exception in onPaymentError", e);
        }
    }

    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();
    }

//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.home:
//               callDirections("1");
//                break;
//            case R.id.shop:
//                callDirections("2");
//                break;
//            case R.id.services:
//                callDirections("3");
//                break;
//            case R.id.care:
//                callDirections("4");
//                break;
//            case R.id.community:
//                callDirections("5");
//                break;
//
//            default:
//                return  false;
//        }
//        return true;
//    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.rl_homes:
                callDirections("1");
                break;

            case R.id.rl_home:
                callDirections("1");
                break;

            case R.id.rl_shops:
                callDirections("2");
                break;

            case R.id.rl_shop:
                callDirections("2");
                break;

            case R.id.rl_services:
                callDirections("3");
                break;

            case R.id.rl_service:
                callDirections("3");
                break;

            case R.id.rl_cares:
                callDirections("4");
                break;

            case R.id.rl_care:
                callDirections("4");
                break;

            case R.id.rl_comns:
                callDirections("5");
                break;

            case R.id.rl_comn:
                callDirections("5");
                break;
        }
    }


    private void setMargins(RelativeLayout rl_layout, int i, int i1, int i2, int i3) {

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)rl_layout.getLayoutParams();
        params.setMargins(i, i1, i2, i3);
        rl_layout.setLayoutParams(params);
    }
}