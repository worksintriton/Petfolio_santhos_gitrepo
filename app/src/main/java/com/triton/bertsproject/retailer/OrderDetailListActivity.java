package com.triton.bertsproject.retailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.ProductDetailsAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.requestpojo.OrderDetailListRequest;
import com.triton.bertsproject.requestpojo.OrderCancelOverallRequest;
import com.triton.bertsproject.responsepojo.OrderDetailListResponse;
import com.triton.bertsproject.responsepojo.OrderCancelOverallResponse;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.RestUtils;

import java.util.ArrayList;
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

public class OrderDetailListActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SPOrderDetailsActivity" ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_date)
    TextView txt_order_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_booking_id)
    TextView txt_booking_id;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_payment_method)
    TextView txt_payment_method;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_total_order_cost)
    TextView txt_total_order_cost;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_quantity)
    TextView txt_quantity;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipping_address_name)
    TextView txt_shipping_address_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipping_address_street)
    TextView txt_shipping_address_street;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipping_address_city)
    TextView txt_shipping_address_city;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipping_address_state_pincode)
    TextView txt_shipping_address_state_pincode;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipping_address_phone)
    TextView txt_shipping_address_phone;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipping_address_landmark)
    TextView txt_shipping_address_landmark;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_expand_arrow)
    ImageView img_expand_arrow;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_orderdetails)
    LinearLayout ll_orderdetails;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_expand_arrow_shipping)
    ImageView img_expand_arrow_shipping;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_shippingaddress)
    LinearLayout ll_shippingaddress;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_productdetails)
    RecyclerView rv_productdetails;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_expand_arrow_productdetails)
    ImageView img_expand_arrow_productdetails;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_productdetails)
    LinearLayout ll_productdetails;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_products)
    TextView txt_no_products;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.scrollablContent)
    ScrollView scrollablContent;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_header)
    View include_header;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spinKitView;

    BottomNavigationView bottom_navigation_view;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view1)
    View view1;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view2)
    View view2;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cancel_overall_order)
    TextView cancel_overall_order;


    AlertDialog alertDialog;

    private String _id;
    private String fromactivity;

    private  boolean button1IsVisible = true;
    private  boolean ShippingIsVisible = true;
    private  boolean productsIsVisible = true;
    private String orderid;

    DD4YouConfig dd4YouConfig;

    private List<Integer> product_id = new ArrayList<>();

    List<OrderDetailListResponse.DataBean.OrdersBean> ordersBeanList;

    List<OrderDetailListResponse.DataBean> dataBeanList;

    List<OrderDetailListResponse.DataBean.OrdersBean.ProductsBean> productdetailslist;

    SessionManager sessionManager;

    String user_id,pincode, city, state, first_name,last_name,phonenum,ship_addr1,ship_addr2,country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_list);

        ButterKnife.bind(this);


        img_back.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            orderid = extras.getString("order_id");
            fromactivity = extras.getString("fromactivity");
            Log.w(TAG,"_id : "+_id+" fromactivity : "+ fromactivity);



        }

        dd4YouConfig = new DD4YouConfig(this);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getProfileDetails();

        user_id = user.get(SessionManager.KEY_ID);

        txt_toolbar_title.setText("Order Details");

        ll_orderdetails.setVisibility(View.GONE);

        view1.setVisibility(View.GONE);

        view2.setVisibility(View.GONE);

        ll_shippingaddress.setVisibility(View.GONE);

        ll_productdetails.setVisibility(View.GONE);

        if (dd4YouConfig.isInternetConnectivity()) {

            orderDetailListResponseCall();

        }

        else {
            callnointernet();



        }

        img_expand_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.w(TAG, "button1IsVisible : "+button1IsVisible);

                if(button1IsVisible) {
                    ll_orderdetails.setVisibility(View.VISIBLE);
                    img_expand_arrow.setImageResource(R.drawable.ic_up_arrow);
                    button1IsVisible = false;
                }
                else {
                    ll_orderdetails.setVisibility(View.GONE);
                    img_expand_arrow.setImageResource(R.drawable.ic_right_down);
                    button1IsVisible = true;

                }


            }
        });
        img_expand_arrow_shipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.w(TAG, "ShippingIsVisible : "+ShippingIsVisible);

                if(ShippingIsVisible) {
                    ll_shippingaddress.setVisibility(View.VISIBLE);
                    img_expand_arrow_shipping.setImageResource(R.drawable.ic_up_arrow);
                    ShippingIsVisible = false;
                } else {
                    ll_shippingaddress.setVisibility(View.GONE);
                    img_expand_arrow_shipping.setImageResource(R.drawable.ic_right_down);
                    ShippingIsVisible = true;

                }

            }
        });
        img_expand_arrow_productdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.w(TAG, "productsIsVisible : "+productsIsVisible);

                if(productsIsVisible) {
                    ll_productdetails.setVisibility(View.GONE);
                    img_expand_arrow_productdetails.setImageResource(R.drawable.ic_right_down);
                    productsIsVisible = false;
                } else {
                    ll_productdetails.setVisibility(View.VISIBLE);
                    img_expand_arrow_productdetails.setImageResource(R.drawable.ic_up_arrow);
                    productsIsVisible = true;

                }


            }
        });

        cancel_overall_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dd4YouConfig.isInternetConnectivity()){

                    String msg = "Are you sure want to Cancel Order?";

                    showAlert(msg);
                }
            }
        });
    }

    private void showAlert(String msg) {


        try {

            new SweetAlertDialog(OrderDetailListActivity.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Alert")
                    .setContentText(msg)
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



    @SuppressLint("LongLogTag")
    private void emptycartlistResponseCall() {

        spinKitView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<OrderCancelOverallResponse> call = apiInterface.canceloverallOrderResponseCall(RestUtils.getContentType(), OrderCancelOverallRequest());
        Log.w(TAG,"ShowCartListResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<OrderCancelOverallResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<OrderCancelOverallResponse> call, @NonNull Response<OrderCancelOverallResponse> response) {
                spinKitView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "OrderCancelOverallResponse" + new Gson().toJson(response.body()));

                        Toasty.success(getApplicationContext(),""+response.body().getMessage(), Toast.LENGTH_LONG).show();

                        startActivity(new Intent(OrderDetailListActivity.this,OrderListActivity.class));
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }

                }



            }


            @Override
            public void onFailure(@NonNull Call<OrderCancelOverallResponse> call,@NonNull  Throwable t) {
                spinKitView.setVisibility(View.GONE);
                Log.w(TAG,"OrderCancelOverallResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private OrderCancelOverallRequest OrderCancelOverallRequest() {

        /*
         * MODE : CANCEL
         * USER_ID : 541
         * ORDER_ID : 303
         */
        OrderCancelOverallRequest OrderCancelOverallRequest = new OrderCancelOverallRequest();
        OrderCancelOverallRequest.setUSER_ID(user_id);
        OrderCancelOverallRequest.setMODE("CANCEL");
        OrderCancelOverallRequest.setORDER_ID(orderid);

        Log.w(TAG,"OrderCancelOverallRequest "+ new Gson().toJson(OrderCancelOverallRequest));
        return OrderCancelOverallRequest;
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(OrderDetailListActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(OrderDetailListActivity.this, OrderDetailListActivity.class));
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrderDetailListActivity.this,OrderListActivity.class);
        startActivity(intent);
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_back) {
            onBackPressed();
        }

    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void orderDetailListResponseCall() {
        spinKitView.setVisibility(View.VISIBLE);
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<OrderDetailListResponse> call = apiInterface.orderDetailListResponseCall(RestUtils.getContentType(), orderDetailListRequest());
        Log.w(TAG,"OrderDetailListResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<OrderDetailListResponse>() {
            @SuppressLint({"LongLogTag", "LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<OrderDetailListResponse> call, @NonNull Response<OrderDetailListResponse> response) {

                Log.w(TAG,"OrderDetailListResponse"+ "--->" + new Gson().toJson(response.body()));

                spinKitView.setVisibility(View.GONE);
                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        scrollablContent.setVisibility(View.VISIBLE);

                        if(response.body().getData()!=null){

                            ll_orderdetails.setVisibility(View.VISIBLE);

                            ll_shippingaddress.setVisibility(View.VISIBLE);

                            view1.setVisibility(View.VISIBLE);

                            view2.setVisibility(View.VISIBLE);

                            ll_productdetails.setVisibility(View.VISIBLE);

                            ordersBeanList = response.body().getData().getOrders();

                            if(ordersBeanList.get(0).getCreated_at()!=null&&!ordersBeanList.get(0).getCreated_at().isEmpty()){
                                txt_order_date.setText(ordersBeanList.get(0).getCreated_at());
                            }
                            if(ordersBeanList.get(0).getOrder_number()!=null&&!ordersBeanList.get(0).getOrder_number().isEmpty()){
                                txt_booking_id.setText("# "+ordersBeanList.get(0).getOrder_number());

                            }
                            if(ordersBeanList.get(0).getPrice_total()!=null&&!ordersBeanList.get(0).getPrice_total().isEmpty()){
                                txt_total_order_cost.setText("$ "+ordersBeanList.get(0).getPrice_total());
                            }
                            if(ordersBeanList.get(0).getProducts_count() !=0){
                                txt_quantity.setText(""+ordersBeanList.get(0).getProducts_count());
                            }

                            if(ordersBeanList.get(0).getShipping_first_name()!=null&&!ordersBeanList.get(0).getShipping_first_name().isEmpty()){

                                first_name =ordersBeanList.get(0).getShipping_first_name();
                            }

                            else {

                                first_name="";
                            }


                            if(ordersBeanList.get(0).getShipping_last_name()!=null){

                                last_name =ordersBeanList.get(0).getShipping_last_name().toString();
                            }

                            else {

                                last_name="";
                            }

                            txt_shipping_address_name.setText(first_name+" "+last_name);

                             if(ordersBeanList.get(0).getShipping_address_1()!=null&&!ordersBeanList.get(0).getShipping_address_1().isEmpty()){

                                ship_addr1 = ordersBeanList.get(0).getShipping_address_1();
                            }

                            else {

                                ship_addr1="";
                            }

                            if(ordersBeanList.get(0).getShipping_address_2()!=null&&!ordersBeanList.get(0).getShipping_address_2().isEmpty()){

                                ship_addr2 = ordersBeanList.get(0).getShipping_address_2();
                            }

                            else {

                                ship_addr2="";
                            }

                            txt_shipping_address_street.setText(ship_addr1+" ,"+ship_addr2+", ");

                            if(ordersBeanList.get(0).getShipping_city()!=null&&!ordersBeanList.get(0).getShipping_city().isEmpty()){

                                city=ordersBeanList.get(0).getShipping_city();
                            }

                            else {

                                city="";
                            }

                          txt_shipping_address_city.setText(city);

                            if(ordersBeanList.get(0).getShipping_state_name()!=null&&!ordersBeanList.get(0).getShipping_state_name().isEmpty()){

                                state=ordersBeanList.get(0).getShipping_state_name();
                            }

                            else {

                                state="";
                            }



                            if(ordersBeanList.get(0).getShipping_zip_code()!=null&&!ordersBeanList.get(0).getShipping_zip_code().isEmpty()){

                                pincode=ordersBeanList.get(0).getShipping_zip_code();
                            }

                            else {

                                pincode="";
                            }

                            txt_shipping_address_state_pincode.setText(state+" - "+pincode);

                          if(ordersBeanList.get(0).getShipping_phone_number() != null && !ordersBeanList.get(0).getShipping_phone_number().isEmpty()) {

                                txt_shipping_address_phone.setText("Phone : " + ordersBeanList.get(0).getShipping_phone_number());

                            }

                            if(ordersBeanList.get(0).getShipping_country_name()!=null&&!ordersBeanList.get(0).getShipping_country_name().isEmpty()){

                                country=ordersBeanList.get(0).getShipping_country_name();
                            }

                            else {

                                country="";
                            }


                                txt_shipping_address_landmark.setText("Country : " + country);

//
//                            if(fromactivity != null && fromactivity.equalsIgnoreCase("FragmentSPNewOrders")){
//                                txt_order_status.setText("Booked on");
//                                img_order_status.setImageResource(R.drawable.completed);
//                                if(response.body().getData().getOrder_details().getOrder_booked() != null){
//                                    txt_delivered_date.setText(response.body().getData().getOrder_details().getOrder_booked());
//                                }
//                            }
//                            else if(fromactivity != null && fromactivity.equalsIgnoreCase("FragmentSPCompletedOrders")){
//                                txt_order_status.setText("Delivered on");
//                                img_order_status.setImageResource(R.drawable.completed);
//                                if(response.body().getData().getOrder_details().getOrder_completed() != null){
//                                    txt_delivered_date.setText(response.body().getData().getOrder_details().getOrder_completed());
//                                }
//                            }
//                            else if(fromactivity != null && fromactivity.equalsIgnoreCase("FragmentSPCancelledOrders")) {
//                                txt_order_status.setText("Cancelled on");
//                                img_order_status.setImageResource(R.drawable.ic_baseline_cancel_24);
//                                if (response.body().getData().getOrder_details().getOrder_cancelled() != null && !response.body().getData().getOrder_details().getOrder_cancelled().isEmpty()) {
//                                    txt_delivered_date.setText(response.body().getData().getOrder_details().getOrder_cancelled());
//                                }
//
//
//                            }
//
                            if(response.body().getData().getOrders().get(0).getProducts() != null && response.body().getData().getOrders().get(0).getProducts().size()>0){

                                productdetailslist = response.body().getData().getOrders().get(0).getProducts();
                                Log.w(TAG,"ProductsDetails size : "+productdetailslist.size()+" productdetails "+new Gson().toJson(productdetailslist));



                                rv_productdetails.setVisibility(View.VISIBLE);
                                txt_no_products.setVisibility(View.GONE);
                                setView(productdetailslist);
                            }else{
                                rv_productdetails.setVisibility(View.GONE);
                                txt_no_products.setVisibility(View.VISIBLE);
                                txt_no_products.setText("No products found");
                            }






                        }


                    }

                }


            }

            @SuppressLint({"LongLogTag", "LogNotTimber"})
            @Override
            public void onFailure(@NonNull Call<OrderDetailListResponse> call, @NonNull Throwable t) {

                spinKitView.setVisibility(View.GONE);
                Log.w(TAG,"OrderDetailListResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private OrderDetailListRequest orderDetailListRequest() {

        /*
         * MODE : LIST
         * USER_ID : 541
         * ORDER_ID : 306
         */


        OrderDetailListRequest orderDetailListRequest = new OrderDetailListRequest();
        orderDetailListRequest.setORDER_ID(orderid);
        orderDetailListRequest.setUSER_ID(user_id);
        orderDetailListRequest.setMODE("LIST");

        Log.w(TAG,"vendorOrderDetailsRequest"+ "--->" + new Gson().toJson(orderDetailListRequest));
        return orderDetailListRequest;
    }
//
    private void setView(List<OrderDetailListResponse.DataBean.OrdersBean.ProductsBean> productdetailslist) {
        rv_productdetails.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_productdetails.setItemAnimator(new DefaultItemAnimator());
        ProductDetailsAdapter productDetailsAdapter = new ProductDetailsAdapter(getApplicationContext(),productdetailslist);
        rv_productdetails.setAdapter(productDetailsAdapter);

    }

    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(OrderDetailListActivity.this);
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