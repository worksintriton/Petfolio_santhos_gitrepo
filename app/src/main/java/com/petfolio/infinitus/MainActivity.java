package com.petfolio.infinitus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.petfolio.infinitus.api.APIClient;
import com.petfolio.infinitus.api.RestApiInterface;
import com.petfolio.infinitus.doctor.DoctorBusinessInfoActivity;
import com.petfolio.infinitus.requestpojo.AppoinmentCancelledRequest;
import com.petfolio.infinitus.requestpojo.VendorConfirmsOrderRequest;
import com.petfolio.infinitus.responsepojo.AppoinmentCancelledResponse;
import com.petfolio.infinitus.responsepojo.VendorOrderAcceptResponse;
import com.petfolio.infinitus.utils.ConnectionDetector;
import com.petfolio.infinitus.utils.RestUtils;
import com.petfolio.infinitus.vendor.VendorDashboardActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private  String TAG = "VendorTrackDetailsActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_products_image)
    ImageView img_products_image;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_product_title)
    TextView txt_product_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_products_price)
    TextView txt_products_price;

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
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_vendor_booked)
    ImageView img_vendor_booked;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_booked_date)
    TextView txt_booked_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_vendor_confirmed)
    ImageView img_vendor_confirmed;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_confirm_date)
    TextView txt_order_confirm_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_edit_order_confirm)
    TextView txt_edit_order_confirm;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_reject_date)
    TextView txt_order_reject_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_edit_order_reject)
    TextView txt_edit_order_reject;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_vendor_order_dispatched)
    ImageView img_vendor_order_dispatched;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_dispatch_date)
    TextView txt_order_dispatch_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_vendor_order_transit)
    ImageView img_vendor_order_transit;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_transit_date)
    TextView txt_order_transit_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spr_ordertype)
    Spinner spr_ordertype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_submit)
    Button btn_submit;

    String product_title, product_image, order_date, order_id, payment_mode,updated_order_status;

    int product_pr, order_total, quantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_track_details);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            product_title = extras.getString("product_title");

            order_date = extras.getString("order_date");

            order_id = extras.getString("order_id");

            payment_mode = extras.getString("payment_mode");

            product_image = extras.getString("product_image");

            product_pr = extras.getInt("product_pr");

            order_total = extras.getInt("order_total");

            quantity = extras.getInt("quantity");

        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());

        String currentDateandTime = sdf.format(new Date());

        if(product_image != null&&!product_image.isEmpty()){

            Glide.with(this)
                    .load(product_image)
                    .into(img_products_image);

        }
        else{
            Glide.with(this)
                    .load(R.drawable.image_thumbnail)
                    .into(img_products_image);

        }

        if(product_title != null&&!product_title.isEmpty()){

            txt_product_title.setText(product_title);
        }

        if(product_pr!=0){

            txt_products_price.setText(" \u20B9 "+ product_pr);
        }

        if(order_date != null&&!order_date.isEmpty()){

            txt_order_date.setText(order_date);

            txt_booked_date.setText(order_date);
        }

        if(order_id != null&&!order_id.isEmpty()){

            txt_booking_id.setText(product_title);
        }

        if(payment_mode != null&&!payment_mode.isEmpty()){

            txt_payment_method.setText(payment_mode);
        }

        if(order_total != 0){

            txt_total_order_cost.setText(order_total);
        }

        if(quantity != 0){

            txt_quantity.setText(quantity);
        }


        ArrayList<String> orderTypeArrayList = new ArrayList<>();

        orderTypeArrayList.add("Select Order Status");

        orderTypeArrayList.add("Order Confirmation");

        orderTypeArrayList.add("Order Cancellation");

        orderTypeArrayList.add("Order Dispatched");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.spinner_item, orderTypeArrayList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view

        spr_ordertype.setAdapter(spinnerArrayAdapter);

        spr_ordertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.green));
                updated_order_status = spr_ordertype.getSelectedItem().toString();
                Log.w(TAG,"selected_order_type : "+updated_order_status);


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        btn_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_submit:
                checkValidation();
                break;

        }
    }

    private void checkValidation() {

        if(!(updated_order_status.equals("Select Order Status"))){

            if(updated_order_status.equals("Order Confirmation")){

                if (new ConnectionDetector(MainActivity.this).isNetworkAvailable(MainActivity.this)) {

                    vendorUpdateOrderStatus(2, "Order Confirm");
                }
            }

            else if(updated_order_status.equals("Order Cancellation")){



            }

            else {



            }


        }

        else {

            Toasty.warning(MainActivity.this, "Please Select Order Type", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("LogNotTimber")
    private void vendorUpdateOrderStatus(int activity_id, String activity_title) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<VendorOrderAcceptResponse> call = apiInterface.vendor_order_booking_accept_ResponseCall(RestUtils.getContentType(), vendorConfirmsOrderRequest(activity_id,activity_title));

        Log.w(TAG,"vendorConfirmsOrderRequest url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<VendorOrderAcceptResponse>() {
            @Override
            public void onResponse(@NonNull Call<VendorOrderAcceptResponse> call, @NonNull Response<VendorOrderAcceptResponse> response) {

                Log.w(TAG,"appoinmentCancelledResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        startActivity(new Intent(MainActivity.this, VendorDashboardActivity.class));

                    }
                    else{
                        //showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<VendorOrderAcceptResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"appoinmentCancelledResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }

    @SuppressLint("LogNotTimber")
    private VendorConfirmsOrderRequest vendorConfirmsOrderRequest(int id,String title) {

        /**
         * _id : 6049e4f564a9296f3d7c3327
         * activity_id : 2
         * activity_title : Order Confirm
         * activity_date : 11-03-2021 03:07 PM
         */



        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        VendorConfirmsOrderRequest vendorConfirmsOrderRequest = new VendorConfirmsOrderRequest();
        vendorConfirmsOrderRequest.set_id(order_id);
        vendorConfirmsOrderRequest.setActivity_id(id);
        vendorConfirmsOrderRequest.setActivity_title(title);
        vendorConfirmsOrderRequest.setActivity_date(currentDateandTime);

        Log.w(TAG,"appoinmentCancelledRequest"+ "--->" + new Gson().toJson(vendorConfirmsOrderRequest));
        return vendorConfirmsOrderRequest;
    }

}