package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.LoginActivity;
import com.triton.bertsproject.activities.RegisterActivity;
import com.triton.bertsproject.adapter.CartProductListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.interfaces.AddProductListener;
import com.triton.bertsproject.interfaces.CartRemoveProductListener;
import com.triton.bertsproject.interfaces.RemoveProductListener;
import com.triton.bertsproject.model.DeleteCartListRequest;
import com.triton.bertsproject.requestpojo.AddToCartRequest;
import com.triton.bertsproject.requestpojo.HomepageDashboardRequest;
import com.triton.bertsproject.requestpojo.HomepageDashboardResponse;
import com.triton.bertsproject.requestpojo.RemoveOverallProductsRequest;
import com.triton.bertsproject.requestpojo.RemovefromCartRequest;
import com.triton.bertsproject.requestpojo.ShowCartListRequest;
import com.triton.bertsproject.responsepojo.AddToCartResponse;
import com.triton.bertsproject.responsepojo.RemoveOverallProductsResponse;
import com.triton.bertsproject.responsepojo.RemovefromCartResponse;
import com.triton.bertsproject.responsepojo.ShowCartListResponse;
import com.triton.bertsproject.sessionmanager.Connectivity;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.RestUtils;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetailerCartActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, CartRemoveProductListener, AddProductListener, RemoveProductListener {

    private static final String TAG = "RetailerCartActivity";

    public static String active_tag = "1";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;

    CartProductListAdapter cartProductListAdapter;

    Context context = RetailerCartActivity.this;

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
    @BindView(R.id.txt_empty_cart)
    TextView txt_empty_cart;

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

    //    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.btn_proceed)
//    Button btn_proceed;
//
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_proceed)
    LinearLayout ll_proceed;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_deliveryaddrchange)
    TextView txt_deliveryaddrchange;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipaddrchange)
    TextView txt_shipaddrchange;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_shipping)
    CardView cv_shipping;

//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.cv_shipcharge)
//    CardView cv_shipcharge;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_price)
    CardView cv_price;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_total)
    TextView txt_order_total;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_deliveryaddrtype)
    TextView txt_deliveryaddrtype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_deliveryaddr)
    TextView txt_deliveryaddr;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_continue_shop)
    Button btn_continue_shop;

    List<ShowCartListResponse.DataBean.CartBean> cartBeanList ;

    ShowCartListResponse.DataBean.DefaultAddressBean defaultAddressBean;

    String user_id;

//    private DD4YouNetReceiver dd4YouNetReceiver;

    private DD4YouConfig dd4YouConfig;

    AlertDialog alertDialog;

    SessionManager sessionManager;

    String brand_id,brand_name,parent_id,subcategid,subcategname,make_id,model_id,model_name;

    String prod_id,prod_name,shipid;

    String addr_name,address1,state_name,country_name;

    Connectivity connectivity;

    String value,categ_name,make_name,search_text,cart_count;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_cart);
        ButterKnife.bind( this);
        Log.w("Oncreate", TAG);
        txt_toolbar_title.setText(R.string.cart);
      //  floatingActionButton.setImageResource(R.drawable.berts_logo_fb);

        connectivity = new Connectivity();

        value = connectivity.getData(RetailerCartActivity.this,"RetailerCart");

        if(value!=null&&!value.isEmpty()) {

            fromactivity = value;

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                prod_id = extras.getString("prod_id");

                //prod_id = "2";

                prod_name = extras.getString("prod_name");

                brand_id = extras.getString("brand_id");

                brand_name = extras.getString("brand_name");

                parent_id = extras.getString("parent_id");

                categ_name = extras.getString("categ_name");

                subcategid = extras.getString("subcategid");

                subcategname = extras.getString("subcategname");

                make_id = extras.getString("make_id");

                make_name = extras.getString("make_name");

                model_id = extras.getString("model_id");

                model_name = extras.getString("model_name");

            }

            Log.w(TAG,"Connectivity fromactivity : "+fromactivity+ " brand_id : "+brand_id + "brand_name : "+brand_name+"parent_id : "+parent_id+ "categ_name : "+categ_name+ "subcategid :" +subcategid

                    + "subcategname : "+subcategname + "make_id : "+make_id + "model_id :" +model_id

                    + "model_name : "+model_name);
        }

        else {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                fromactivity = extras.getString("fromactivity");

                prod_id = extras.getString("prod_id");

                //prod_id = "2";

                prod_name = extras.getString("prod_name");

                brand_id = extras.getString("brand_id");

                brand_name = extras.getString("brand_name");

                parent_id = extras.getString("parent_id");

                categ_name = extras.getString("categ_name");

                subcategid = extras.getString("subcategid");

                subcategname = extras.getString("subcategname");

                make_id = extras.getString("make_id");

                make_name = extras.getString("make_name");

                model_id = extras.getString("model_id");

                model_name = extras.getString("model_name");

                Log.w(TAG,"Connectivity fromactivity : "+fromactivity+ "brand_id : "+brand_id + "brand_name : "+brand_name+"parent_id : "+parent_id+ "categ_name : "+categ_name+ "subcategid :" +subcategid

                        + "subcategname : "+subcategname + "make_id : "+make_id + "model_id :" +model_id

                        + "model_name : "+model_name);

            }
        }




        spin_kit_loadingView.setVisibility(View.GONE);
        tag = getIntent().getStringExtra("tag");
        Log.w(TAG, " tag : " + this.tag);
        bottomNavigation.setSelectedItemId(R.id.shop);
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        img_back.setOnClickListener(v -> {

            onBackPressed();

        });

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getProfileDetails();

        user_id = user.get(SessionManager.KEY_ID);

        dd4YouConfig = new DD4YouConfig(this);

        //enableSwipeToDeleteAndUndo();

        txt_empty_cart.setVisibility(View.GONE);

        cv_shipping.setVisibility(View.GONE);

       // cv_shipcharge.setVisibility(View.GONE);

        ll_proceed.setVisibility(View.GONE);

        cv_price.setVisibility(View.GONE);

        if(sessionManager.isLoggedIn()){

            if (dd4YouConfig.isInternetConnectivity()) {

                showcartlistResponseCall();

            }

            else
            {
                callnointernet();

            }
        }


        else
        {
            showAlert();

        }


    }

    private void setView(List<ShowCartListResponse.DataBean.CartBean> cartBeanList) {

        rv_productlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        rv_productlist.setMotionEventSplittingEnabled(false);

        rv_productlist.setItemAnimator(new DefaultItemAnimator());

        cartProductListAdapter = new CartProductListAdapter(this, cartBeanList,this,this,this);

        rv_productlist.setAdapter(cartProductListAdapter);

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

        if(fromactivity!=null){

            if(fromactivity.equals("HomeFragment")){

                callDirections("1");
            }
            else if(fromactivity.equals("MyGarageFragment")){

                callDirections("2");
            }
            else if(fromactivity.equals("ShopFragment")){

                callDirections("3");
            }
            else if(fromactivity.equals("ProfileFragment")){

             callDirections("5");
            }
            else if(fromactivity.equals("ProductDetailDescriptionActivity")){

                connectivity.clearData(RetailerCartActivity.this,"RetailerCart");

                gotoProductDescrActivity();

            }

            else if(fromactivity.equals("RetailerProductListActivity")){

                connectivity.clearData(RetailerCartActivity.this,"RetailerCart");

                gotoRetailerProductListActivity();

            }

            else if(fromactivity.equals("RetailerProductListBasedOnCategActivity")){

                connectivity.clearData(RetailerCartActivity.this,"RetailerCart");

                gotoRetailerProductListBasedOnCategActivity();

            }

            else if(fromactivity.equals("RetailerProductListBasedOnMakeActivity")){

                connectivity.clearData(RetailerCartActivity.this,"RetailerCart");

                gotoRetailerProductListBasedOnMakeActivity();

            }
            else {
                Intent intent = new Intent(RetailerCartActivity.this, RetailerDashboardActivity.class);
                intent.putExtra("fromactivity",TAG);
                connectivity.clearData(RetailerCartActivity.this,"RetailerCart");
                startActivity(intent);
                finish();

            }
        }
        else {
            Intent intent = new Intent(RetailerCartActivity.this, RetailerDashboardActivity.class);
            intent.putExtra("fromactivity",TAG);
            connectivity.clearData(RetailerCartActivity.this,"RetailerCart");
            startActivity(intent);
            finish();

        }
    }

    private void gotoRetailerProductListBasedOnMakeActivity() {

        Intent intent = new Intent(RetailerCartActivity.this, RetailerProductListBasedOnMakeActivity.class);

        intent.putExtra("fromactivity",TAG);

        intent.putExtra("brand_id",brand_id);

        intent.putExtra("make_id",make_id);

        intent.putExtra("make_name",make_name);

        intent.putExtra("model_id", model_id);

        intent.putExtra("model_name",model_name);

        startActivity(intent);

        finish();
    }

    private void gotoRetailerProductListBasedOnCategActivity() {

        Intent intent = new Intent(RetailerCartActivity.this, RetailerProductListBasedOnCategActivity.class);

        intent.putExtra("fromactivity",TAG);

        intent.putExtra("brand_id",brand_id);

        intent.putExtra("parent_id",parent_id);

        intent.putExtra("categ_name",categ_name);

        intent.putExtra("subcategid",subcategid);

        intent.putExtra("subcategname",subcategname);

        startActivity(intent);

        finish();

    }

    private void gotoRetailerProductListActivity() {

        Intent intent = new Intent(RetailerCartActivity.this, RetailerProductListActivity.class);

        intent.putExtra("fromactivity",TAG);

        intent.putExtra("brand_id",brand_id);

        intent.putExtra("brand_name",brand_name);

        startActivity(intent);

        finish();
    }

    private void gotoProductDescrActivity() {

            Intent intent = new Intent(RetailerCartActivity.this, ProductDetailDescriptionActivity.class);

            intent.putExtra("fromactivity",TAG);

            intent.putExtra("prod_id",prod_id);

            intent.putExtra("prod_name",prod_name);

            intent.putExtra("brand_id",brand_id);

            intent.putExtra("brand_name",brand_name);

            intent.putExtra("parent_id",parent_id);

            intent.putExtra("categ_name",categ_name);

            intent.putExtra("subcategid",subcategid);

            intent.putExtra("subcategname",subcategname);

            intent.putExtra("make_id",make_id);

            intent.putExtra("make_name",make_name);

            intent.putExtra("model_id", model_id);

            intent.putExtra("model_name",model_name);

            startActivity(intent);

            finish();

    }


    @SuppressLint("LongLogTag")
    private void showcartlistResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ShowCartListResponse> call = apiInterface.showcartlistResponseCall(RestUtils.getContentType(),showCartListRequest());
        Log.w(TAG,"ShowCartListResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<ShowCartListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<ShowCartListResponse> call, @NonNull Response<ShowCartListResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "ShowCartListResponse" + new Gson().toJson(response.body()));

                        usercommonResponseCall();

                        cartBeanList=response.body().getData().getCart();

                        if(cartBeanList!=null&&cartBeanList.size()>0){

                            txt_empty_cart.setVisibility(View.VISIBLE);

                            txt_empty_cart.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    if (dd4YouConfig.isInternetConnectivity()) {

                                        showOverallDeleteAlert();

                                    }

                                    else
                                    {
                                        callnointernet();

                                    }

                                }
                            });

                            cv_shipping.setVisibility(View.VISIBLE);

                     //       cv_shipcharge.setVisibility(View.VISIBLE);

                            ll_proceed.setVisibility(View.VISIBLE);

                            rv_productlist.setVisibility(View.VISIBLE);

                            cv_price.setVisibility(View.VISIBLE);

                            if(response.body().getData().getCart_total()!=0){

                                txt_order_total.setText("$ "+response.body().getData().getCart_total());
                            }


                            txt_no_records.setVisibility(View.GONE);

                            btn_continue_shop.setVisibility(View.GONE);

                            defaultAddressBean = response.body().getData().getDefault_address();

                            if(defaultAddressBean!=null&&defaultAddressBean.getId()!=null){

                                if(defaultAddressBean.getId()!=null&&!defaultAddressBean.getId().isEmpty()){

                                    shipid = defaultAddressBean.getId();
                                }
                                else {

                                    shipid="";
                                }


                                if(defaultAddressBean.getAddress1()!=null&&!defaultAddressBean.getAddress1().isEmpty()){

                                    address1 = defaultAddressBean.getAddress1();
                                }
                                else {

                                    address1="";
                                }

                                if(defaultAddressBean.getAddress1()!=null&&!defaultAddressBean.getAddress1().isEmpty()){

                                    state_name = defaultAddressBean.getState_name();
                                }
                                else {

                                    state_name="";
                                }

                                if(defaultAddressBean.getAddress1()!=null&&!defaultAddressBean.getAddress1().isEmpty()){

                                    country_name = defaultAddressBean.getCountry_name();
                                }

                                else {

                                    country_name="";
                                }

                                txt_deliveryaddr.setText(address1 + " "+state_name+ " "+country_name);

                                if(defaultAddressBean.getName()!=null&&!defaultAddressBean.getName().isEmpty()){

                                    addr_name = defaultAddressBean.getName();
                                }

                                else {

                                    addr_name="";
                                }


                                txt_deliveryaddrtype.setText(addr_name);

                                txt_deliveryaddrchange.setText("Choose Address");



                            }

                            else
                            {

                                txt_deliveryaddrtype.setText("No Address Found");

                                txt_deliveryaddr.setText("Please add some Address");

                                txt_deliveryaddrchange.setText("Add Address");

                            }

                            txt_deliveryaddrchange.setOnClickListener(v -> {

                                Intent intent = new Intent(RetailerCartActivity.this,ShippingAddressActivity.class);

                                intent.putExtra("fromactivity",fromactivity);

                                startActivity(intent);

                                Animatoo.animateSwipeRight(context);
                            });

                            txt_shipaddrchange.setOnClickListener(v -> {

                                startActivity(new Intent(RetailerCartActivity.this, ShippingMethodActivity.class));

                                Animatoo.animateSwipeRight(context);
                            });

                            ll_proceed.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    if(shipid!=null&&!shipid.isEmpty()){

                                        gotoCheckout();
                                    }

                                    else {

                                        Toasty.warning(getApplicationContext(),"Please Add Shipping Address",Toasty.LENGTH_LONG).show();
                                    }


                                }
                            });


                            setView(cartBeanList);

                        }

                        else {

                            txt_empty_cart.setVisibility(View.GONE);

                            cv_shipping.setVisibility(View.GONE);

                          //  cv_shipcharge.setVisibility(View.GONE);

                            ll_proceed.setVisibility(View.GONE);

                            rv_productlist.setVisibility(View.GONE);

                            txt_no_records.setVisibility(View.VISIBLE);

                            cv_price.setVisibility(View.GONE);

                            txt_no_records.setText("Please Add Some Products to Cart");

                            btn_continue_shop.setVisibility(View.VISIBLE);

                            btn_continue_shop.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    callDirections("2");

                                }
                            });
                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }

                }



            }


            @Override
            public void onFailure(@NonNull Call<ShowCartListResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"ShowCartListResponse flr"+t.getMessage());
            }
        });

    }

    private void showOverallDeleteAlert() {


        try {

            new SweetAlertDialog(RetailerCartActivity.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Alert")
                    .setContentText("Are you sure want to Empty Cart?")
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

                            if(dd4YouConfig.isInternetConnectivity()){

                                emptycartlistResponseCall();
                            }
                            else {

                                callnointernet();
                            }
                            sDialog.dismiss();

                        }
                    })
                    .show();

        }

        catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }

    }

    private void gotoCheckout() {

        Intent intent = new Intent(RetailerCartActivity.this, CheckoutScreenActivity.class);

        intent.putExtra("fromactivity",TAG);

        intent.putExtra("prod_id",prod_id);

        intent.putExtra("prod_name",prod_name);

        intent.putExtra("brand_id",brand_id);

        intent.putExtra("brand_name",brand_name);

        intent.putExtra("parent_id",parent_id);

        intent.putExtra("categ_name",categ_name);

        intent.putExtra("subcategid",subcategid);

        intent.putExtra("subcategname",subcategname);

        intent.putExtra("make_id",make_id);

        intent.putExtra("make_name",make_name);

        intent.putExtra("model_id", model_id);

        intent.putExtra("model_id",model_name);

        connectivity.storeData(RetailerCartActivity.this,"RetailerCart",fromactivity);

        startActivity(intent);

        finish();
    }

    @SuppressLint("LongLogTag")
    private ShowCartListRequest showCartListRequest() {


        /*
         * USER_ID : 1
         * MODE : LIST
         */

        ShowCartListRequest showCartListRequest = new ShowCartListRequest();
        showCartListRequest.setUSER_ID(user_id);
        showCartListRequest.setMODE("LIST");

        Log.w(TAG,"ShowCartListRequest "+ new Gson().toJson(showCartListRequest));
        return showCartListRequest;
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(RetailerCartActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(RetailerCartActivity.this,RetailerCartActivity.class));
            finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerCartActivity.this);
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

    @SuppressLint("LongLogTag")
    private void deletecartlistResponseCall(String id) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ShowCartListResponse> call = apiInterface.deletecartlistResponseCall(RestUtils.getContentType(), deleteCartListRequest(id));
        Log.w(TAG,"ShowCartListResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<ShowCartListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<ShowCartListResponse> call, @NonNull Response<ShowCartListResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "ShowCartListResponse" + new Gson().toJson(response.body()));

                        Toast.makeText(getApplicationContext(),""+response.body().getMessage(),Toast.LENGTH_LONG).show();

                        if (dd4YouConfig.isInternetConnectivity()) {

                            showcartlistResponseCall();

                            usercommonResponseCall();

                        }

                        else
                        {
                            callnointernet();

                        }

                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }

                }



            }


            @Override
            public void onFailure(@NonNull Call<ShowCartListResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"ShowCartListResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private DeleteCartListRequest deleteCartListRequest(String id) {

        /*
         * USER_ID : 1
         * BASKET_ID : 8
         * MODE : DELETE
         */

        DeleteCartListRequest deleteCartListRequest = new DeleteCartListRequest();
        deleteCartListRequest.setUSER_ID(user_id);
        deleteCartListRequest.setBASKET_ID(id);
        deleteCartListRequest.setMODE("DELETE");

        Log.w(TAG,"RemoveWishistRequest "+ new Gson().toJson(deleteCartListRequest));
        return deleteCartListRequest;
    }

    @Override
    public void removeproductListener(String id) {

        if (dd4YouConfig.isInternetConnectivity()) {

            deletecartlistResponseCall(id);

        }

        else
        {
            callnointernet();

        }


    }

    @Override
    public void addproductListener(String prod_id, String quantity, String unit_price, Button btn_addcart) {

        addcartlistResponseCall(prod_id,quantity,unit_price);

    }

    @Override
    public void removeproductListener(String prod_id, String quantity, String unit_price) {

        removecartlistResponseCall(prod_id,quantity,unit_price);

    }

    @SuppressLint("LongLogTag")
    private void addcartlistResponseCall(String prod_id, String quantity, String unit_price) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AddToCartResponse> call = apiInterface.addcartlistResponseCall(RestUtils.getContentType(),AddToCartRequest(prod_id,quantity,unit_price));
        Log.w(TAG,"AddToCartResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<AddToCartResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<AddToCartResponse> call, @NonNull Response<AddToCartResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "AddToCartResponse" + new Gson().toJson(response.body()));

                        Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                        if (dd4YouConfig.isInternetConnectivity()) {

                            showcartlistResponseCall();

                            usercommonResponseCall();

                        }

                        else
                        {
                            callnointernet();

                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }

                }

            }


            @Override
            public void onFailure(@NonNull Call<AddToCartResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"AddToCartResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private AddToCartRequest AddToCartRequest(String prod_id, String quantity, String unit_price) {


        /*
         * USER_ID : 541
         * PRODUCT_ID : 2
         * QUANTITY : 1
         * UNIT_PRICE : 50000
         * MODE : ADDTOCART
         */

        AddToCartRequest AddToCartRequest = new AddToCartRequest();
        AddToCartRequest.setUNIT_PRICE(unit_price);
        AddToCartRequest.setQUANTITY(quantity);
        AddToCartRequest.setPRODUCT_ID(prod_id);
        AddToCartRequest.setUSER_ID(user_id);
        AddToCartRequest.setMODE("ADDTOCART");

        Log.w(TAG,"AddToCartRequest "+ new Gson().toJson(AddToCartRequest));
        return AddToCartRequest;
    }

    @SuppressLint("LongLogTag")
    private void removecartlistResponseCall(String prod_id, String quantity, String unit_price) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<RemovefromCartResponse> call = apiInterface.removefromcartlistResponseCall(RestUtils.getContentType(),RemovefromCartRequest(prod_id,quantity,unit_price));
        Log.w(TAG,"RemovefromCartResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<RemovefromCartResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<RemovefromCartResponse> call, @NonNull Response<RemovefromCartResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "RemovefromCartResponse" + new Gson().toJson(response.body()));

                        Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                        if (dd4YouConfig.isInternetConnectivity()) {

                            showcartlistResponseCall();

                            usercommonResponseCall();

                        }

                        else
                        {
                            callnointernet();

                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }

                }

            }


            @Override
            public void onFailure(@NonNull Call<RemovefromCartResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"RemovefromCartResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private RemovefromCartRequest RemovefromCartRequest(String prod_id, String quantity, String unit_price) {


        /*
         * USER_ID : 541
         * PRODUCT_ID : 2
         * QUANTITY : 1
         * MODE : REMOVE
         */


        RemovefromCartRequest RemovefromCartRequest = new RemovefromCartRequest();
        RemovefromCartRequest.setQUANTITY(quantity);
        RemovefromCartRequest.setPRODUCT_ID(prod_id);
        RemovefromCartRequest.setUSER_ID(user_id);
        RemovefromCartRequest.setMODE("REMOVE");

        Log.w(TAG,"RemovefromCartRequest "+ new Gson().toJson(RemovefromCartRequest));
        return RemovefromCartRequest;
    }

    private void showAlert() {

        AlertDialog.Builder builder=new AlertDialog.Builder(RetailerCartActivity.this);
        builder.setTitle("Alert");
        builder.setMessage("Please Login to View Products in Cart");
        builder.setCancelable(false);
        builder.setPositiveButton("Login", (dialogInterface, i) -> {
            Intent intent = new Intent(RetailerCartActivity.this,LoginActivity.class);

            intent.putExtra("fromactivity",TAG);

            intent.putExtra("prod_id",prod_id);

            intent.putExtra("prod_name",prod_name);

            intent.putExtra("brand_id",brand_id);

            intent.putExtra("brand_name",brand_name);

            intent.putExtra("parent_id",parent_id);

            intent.putExtra("categ_name",categ_name);

            intent.putExtra("subcategid",subcategid);

            intent.putExtra("subcategname",subcategname);

            intent.putExtra("make_id",make_id);

            intent.putExtra("make_name", make_name);

            intent.putExtra("model_id", model_id);

            intent.putExtra("model_id",model_name);

            connectivity.storeData(RetailerCartActivity.this,"RetailerCart",fromactivity);

            startActivity(intent);

            finish();

        });
        builder.setNegativeButton("SignUp", (dialogInterface, i) -> {
            Intent intent = new Intent(RetailerCartActivity.this, RegisterActivity.class);

            intent.putExtra("prod_id",prod_id);

            intent.putExtra("prod_name",prod_name);

            intent.putExtra("brand_id",brand_id);

            intent.putExtra("brand_name",brand_name);

            intent.putExtra("parent_id",parent_id);

            intent.putExtra("categ_name",categ_name);

            intent.putExtra("subcategid",subcategid);

            intent.putExtra("subcategname",subcategname);

            intent.putExtra("make_id",make_id);

            intent.putExtra("make_name", make_name);

            intent.putExtra("model_id", model_id);

            intent.putExtra("model_id",model_name);

            connectivity.storeData(RetailerCartActivity.this,"RetailerCart",fromactivity);

            startActivity(intent);

            finish();
        });
        builder.setNeutralButton("Cancel", (dialogInterface, i) -> {
            AlertDialog alertDialog = builder.create();
            alertDialog.dismiss();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @SuppressLint("LongLogTag")
    private void emptycartlistResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<RemoveOverallProductsResponse> call = apiInterface.deleteoverallcartlistResponseCall(RestUtils.getContentType(), removeOverallProductsRequest());
        Log.w(TAG,"ShowCartListResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<RemoveOverallProductsResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<RemoveOverallProductsResponse> call, @NonNull Response<RemoveOverallProductsResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "RemoveOverallProductsResponse" + new Gson().toJson(response.body()));

                        Toasty.success(getApplicationContext(),""+response.body().getMessage(),Toast.LENGTH_LONG).show();

                        usercommonResponseCall();

                        startActivity(new Intent(RetailerCartActivity.this,RetailerDashboardActivity.class));
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }

                }



            }


            @Override
            public void onFailure(@NonNull Call<RemoveOverallProductsResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"RemoveOverallProductsResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private RemoveOverallProductsRequest removeOverallProductsRequest() {

        /*
         * USER_ID : 541
         * MODE : EMPTYCART
         */

        RemoveOverallProductsRequest removeOverallProductsRequest = new RemoveOverallProductsRequest();
        removeOverallProductsRequest.setUSER_ID(user_id);
        removeOverallProductsRequest.setMODE("EMPTYCART");

        Log.w(TAG,"RemoveOverallProductsRequest "+ new Gson().toJson(removeOverallProductsRequest));
        return removeOverallProductsRequest;
    }

    public void callDirections(String tag){
        Intent intent = new Intent(RetailerCartActivity.this,RetailerDashboardActivity.class);
        intent.putExtra("tag",tag);
        connectivity.clearData(RetailerCartActivity.this,"RetailerCart");
        startActivity(intent);
        finish();

    }


    @SuppressLint("LongLogTag")
    private void usercommonResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<HomepageDashboardResponse> call = apiInterface.usercommonResponseCall(RestUtils.getContentType(),HomepageDashboardRequest());
        Log.w(TAG,"HomepageDashboardResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<HomepageDashboardResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<HomepageDashboardResponse> call, @NonNull Response<HomepageDashboardResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(response.body().getData()!=null){

                        if(200==response.body().getCode()) {

                            Log.w(TAG, "HomepageDashboardResponse" + new Gson().toJson(response.body()));

                            cart_count = String.valueOf(response.body().getData().getCart_count());

                            Log.w(TAG, "Cart_Count" + cart_count);

                            if (cart_count!=null&&!cart_count.equals("0"))  {

                                Connectivity connectivity = new Connectivity();

                                connectivity.clearData(context,"Cart_Count");

                                connectivity.storeData(context,"Cart_Count",String.valueOf(cart_count));


                            }

                            else {

                                Connectivity connectivity = new Connectivity();

                                connectivity.clearData(context,"Cart_Count");

                                connectivity.storeData(context,"Cart_Count","0");

                            }


                        }

                        else {

//                            showErrorLoading(response.body().getMessage());
                            Connectivity connectivity = new Connectivity();

                            connectivity.clearData(context,"Cart_Count");

                            connectivity.storeData(context,"Cart_Count","0");

                        }
                    }

                    else {


                        Connectivity connectivity = new Connectivity();

                        connectivity.clearData(context,"Cart_Count");

                        connectivity.storeData(context,"Cart_Count","0");

                    }

                }

            }


            @Override
            public void onFailure(@NonNull Call<HomepageDashboardResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"HomepageDashboardResponse flr"+t.getMessage());
            }
        });

    }


    @SuppressLint("LongLogTag")
    private HomepageDashboardRequest HomepageDashboardRequest() {

        /*
         * USER_ID : 541
         */


        HomepageDashboardRequest HomepageDashboardRequest = new HomepageDashboardRequest();
        HomepageDashboardRequest.setUSER_ID(user_id);

        Log.w(TAG,"HomepageDashboardRequest "+ new Gson().toJson(HomepageDashboardRequest));
        return HomepageDashboardRequest;
    }


}
