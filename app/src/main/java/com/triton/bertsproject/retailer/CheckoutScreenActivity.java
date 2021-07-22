package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.LoginActivity;
import com.triton.bertsproject.activities.RegisterActivity;
import com.triton.bertsproject.adapter.CartProductListAdapter;
import com.triton.bertsproject.adapter.ChekOutProductListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.requestpojo.OrderCreateRequest;
import com.triton.bertsproject.requestpojo.ShowCartListRequest;
import com.triton.bertsproject.responsepojo.OrderCreateResponse;
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

public class CheckoutScreenActivity extends AppCompatActivity {

    private static final String TAG = "CheckoutScreenActivity";

    Context context = CheckoutScreenActivity.this;

    ChekOutProductListAdapter chekOutProductListAdapter;

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
    @BindView(R.id.txt_selectpymthd)
    TextView txt_selectpymthd;


    //    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.btn_proceed)
//    Button btn_proceed;
//
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_proceed)
    LinearLayout ll_proceed;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_orders)
    CardView cv_orders;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_order_total)
    CardView cv_order_total;
//
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.cv_coupon)
//    CardView cv_coupon;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_payment)
    CardView cv_payment;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_total_order)
    TextView txt_total_order;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_default_shipping)
    RelativeLayout rl_default_shipping;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_deliveryaddrtype)
    TextView txt_deliveryaddrtype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_deliveryaddr)
    TextView txt_deliveryaddr;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_total)
    TextView txt_order_total;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_payment_method)
    TextView txt_payment_method;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipping_subtotal_value)
    TextView txt_shipping_subtotal_value;
//
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.txt_ship_subtotal_value)
//    TextView txt_ship_subtotal_value;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_dis_total_price)
    TextView txt_dis_total_price;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_total_price)
    TextView txt_total_price;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_payment_method)
    RelativeLayout rl_payment_method;

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

    String value,categ_name,make_name,search_text,radioValue="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_screen);

        ButterKnife.bind(this);

        txt_toolbar_title.setText(R.string.checkout_screen);

        Log.w("Oncreate", TAG);

        connectivity = new Connectivity();

        dd4YouConfig = new DD4YouConfig(this);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getProfileDetails();

        user_id = user.get(SessionManager.KEY_ID);

        value = connectivity.getData(CheckoutScreenActivity.this,"CheckoutScreen");

        if(value!=null&&!value.isEmpty()) {

            fromactivity = value;

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                prod_id = extras.getString("prod_id");

                //prod_id = "2";
                radioValue=extras.getString("radioValue");

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

                search_text = extras.getString("search_text");


            }
            Log.w(TAG,"Connectivity fromactivity "+ fromactivity +" brand_id : "+brand_id + "brand_name : "+brand_name+"parent_id : "+parent_id+ "subcategid :" +subcategid

                    + "subcategname : "+subcategname + "make_id : "+make_id + "model_id :" +model_id

                    + "model_name : "+model_name + "search_text : "+search_text);

        }

        else {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                radioValue=extras.getString("radioValue");

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

                search_text = extras.getString("search_text");

                Log.w(TAG,"Connectivity fromactivity "+ fromactivity + "brand_id : "+brand_id + "brand_name : "+brand_name+"parent_id : "+parent_id+ "subcategid :" +subcategid

                        + "subcategname : "+subcategname + "make_id : "+make_id + "model_id :" +model_id

                        + "model_name : "+model_name + "search_text : "+search_text);

            }
        }



        cv_orders.setVisibility(View.GONE);

        cv_order_total.setVisibility(View.GONE);

        //cv_coupon.setVisibility(View.GONE);

        cv_payment.setVisibility(View.GONE);

        ll_proceed.setVisibility(View.GONE);

        if(sessionManager.isLoggedIn()){

            if (dd4YouConfig.isInternetConnectivity()) {

                showcartlistResponseCall();

            }

            else
            {
                callnointernet();

            }
        }

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        if(radioValue!=null&&!radioValue.equals("")){

            txt_selectpymthd.setText(""+radioValue);
        }

        else {

            txt_selectpymthd.setText("Choose Payment Method");
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

            if(fromactivity.equals("RetailerCartActivity")){

                Intent intent = new Intent(CheckoutScreenActivity.this, RetailerCartActivity.class);

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

                intent.putExtra("search_text",search_text);

                connectivity.clearData(CheckoutScreenActivity.this,"CheckoutScreen");

                startActivity(intent);

                finish();
            }

            else {
                Intent intent = new Intent(CheckoutScreenActivity.this, RetailerDashboardActivity.class);
                intent.putExtra("fromactivity",TAG);
                connectivity.clearData(CheckoutScreenActivity.this,"CheckoutScreen");
                startActivity(intent);
                finish();

            }
        }
        else {
            Intent intent = new Intent(CheckoutScreenActivity.this, RetailerDashboardActivity.class);
            intent.putExtra("fromActivity",TAG);
            connectivity.clearData(CheckoutScreenActivity.this,"CheckoutScreen");
            startActivity(intent);
            finish();

        }
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

                    if(response.body().getData()!=null){

                        if(200==response.body().getCode()) {

                            Log.w(TAG, "ShowCartListResponse" + new Gson().toJson(response.body()));

                            cartBeanList=response.body().getData().getCart();

                            if(cartBeanList!=null&&cartBeanList.size()>0){

                                cv_orders.setVisibility(View.VISIBLE);

                                cv_order_total.setVisibility(View.VISIBLE);

                              //  cv_coupon.setVisibility(View.VISIBLE);

                                cv_payment.setVisibility(View.VISIBLE);

                                ll_proceed.setVisibility(View.VISIBLE);

                                rv_productlist.setVisibility(View.VISIBLE);


                                if(response.body().getData().getCart_total()!=0){

                                    txt_order_total.setText("$ "+response.body().getData().getCart_total());
                                }

                                else {

                                    txt_order_total.setText("$ 0");
                                }

                                setView(cartBeanList);

                                txt_no_records.setVisibility(View.GONE);

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

                                }

                                ll_proceed.setOnClickListener(v -> {

                                    if(txt_selectpymthd.getText().toString().equals("Choose Payment Method")){

                                        Toasty.warning(getApplicationContext(),"Please Choose Payment Method",Toasty.LENGTH_LONG).show();
                                    }
                                    else if(cartBeanList.size()>0&&!shipid.isEmpty()){

                                        if (dd4YouConfig.isInternetConnectivity()) {

                                            OrderCreateResponseCall();

                                        }

                                        else
                                        {
                                            callnointernet();

                                        }

                                    }

                                    else {

                                        Toasty.warning(getApplicationContext(),"Please Add Some Products in Cart",Toasty.LENGTH_LONG).show();
                                    }


                                });

                                if(response.body().getData().getShipping_cost()!=0){

                                   txt_shipping_subtotal_value.setText("$ "+response.body().getData().getShipping_cost());
                                }
                                else {

                                    txt_shipping_subtotal_value.setText("0");
                                }

                                if(response.body().getData().getDiscount()!=0){

                                    txt_dis_total_price.setText("$ "+response.body().getData().getDiscount());
                                }
                                else {

                                    txt_dis_total_price.setText("0");
                                }

                                if(response.body().getData().getCart_total()!=0){

                                    txt_total_price.setText("$ "+response.body().getData().getCart_total());
                                }
                                else {

                                    txt_total_price.setText("0");
                                }



                                rl_payment_method.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent=new Intent(CheckoutScreenActivity.this,PaymentMethodActivity.class);

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

                                        intent.putExtra("search_text",search_text);

                                        connectivity.storeData(CheckoutScreenActivity.this,"CheckoutScreen",fromactivity);

                                        startActivity(intent);

                                        finish();
                                    }
                                });

                            }

                            else {

                                cv_orders.setVisibility(View.GONE);

                                cv_order_total.setVisibility(View.GONE);

                               // cv_coupon.setVisibility(View.GONE);

                                cv_payment.setVisibility(View.GONE);

                                ll_proceed.setVisibility(View.GONE);

                                rv_productlist.setVisibility(View.GONE);

                                txt_no_records.setVisibility(View.VISIBLE);

                                txt_no_records.setText("No Products Found");
                            }
                        }

                        else {

                            showErrorLoading(response.body().getMessage());

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
        AlertDialog.Builder builder=new AlertDialog.Builder(CheckoutScreenActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(CheckoutScreenActivity.this, CheckoutScreenActivity.class));
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @SuppressLint("LongLogTag")
    private void OrderCreateResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<OrderCreateResponse> call = apiInterface.orderCreateListResponseCall(RestUtils.getContentType(),OrderCreateRequest());
        Log.w(TAG,"OrderCreateResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<OrderCreateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<OrderCreateResponse> call, @NonNull Response<OrderCreateResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                  if(response.body().getData()!=null){

                      if(200==response.body().getCode()) {

                          Log.w(TAG, "OrderCreateResponse" + new Gson().toJson(response.body()));

                          Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                          successMessage(response.body().getMessage());


                      }

                      else {

                          showErrorLoading(response.body().getMessage());
                      }
                  }

                  else {

                      showErrorLoading(response.body().getMessage());
                  }

                }

            }


            @Override
            public void onFailure(@NonNull Call<OrderCreateResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"OrderCreateResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private OrderCreateRequest OrderCreateRequest() {


        /*
         * MODE : SAVE
         * USER_ID : 541
         * ADDRESS_ID : 351
         * PAYMENT_METHOD : Offline Payment
         */

        OrderCreateRequest OrderCreateRequest = new OrderCreateRequest();
        OrderCreateRequest.setMODE("SAVE");
        OrderCreateRequest.setUSER_ID(user_id);
        OrderCreateRequest.setADDRESS_ID(shipid);
        OrderCreateRequest.setPAYMENT_METHOD("Offline Payment");

        Log.w(TAG,"OrderCreateRequest "+ new Gson().toJson(OrderCreateRequest));
        return OrderCreateRequest;
    }


    private void setView(List<ShowCartListResponse.DataBean.CartBean> cartBeanList) {

        rv_productlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        rv_productlist.setMotionEventSplittingEnabled(false);

        rv_productlist.setItemAnimator(new DefaultItemAnimator());

        chekOutProductListAdapter = new ChekOutProductListAdapter(this, cartBeanList);

        rv_productlist.setAdapter(chekOutProductListAdapter);

    }

    public void successMessage(String message) {

        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Success")
                .setContentText(message)
                .setConfirmClickListener(sweetAlertDialog -> {

                    Intent intent = new Intent(CheckoutScreenActivity.this, RetailerDashboardActivity.class);

                    intent.putExtra("fromActivity",TAG);

                    connectivity.clearData(CheckoutScreenActivity.this,"CheckoutScreen");

                    startActivity(intent);

                    sweetAlertDialog.dismiss();

                    finish();
                })
                .show();

    }


    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CheckoutScreenActivity.this);
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


}