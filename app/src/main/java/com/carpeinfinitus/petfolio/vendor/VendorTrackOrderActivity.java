package com.carpeinfinitus.petfolio.vendor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.carpeinfinitus.petfolio.R;
import com.carpeinfinitus.petfolio.api.APIClient;
import com.carpeinfinitus.petfolio.api.RestApiInterface;
import com.carpeinfinitus.petfolio.requestpojo.VendorTrackOrderDetailsRequest;
import com.carpeinfinitus.petfolio.responsepojo.TrackOrderDetailsResponse;
import com.carpeinfinitus.petfolio.utils.ConnectionDetector;
import com.carpeinfinitus.petfolio.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VendorTrackOrderActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "VendorTrackOrderActivity" ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

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
    @BindView(R.id.txt_order_vendor_reject_date_reason)
    TextView txt_order_vendor_reject_date_reason;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_vendor_order_rejected)
    ImageView img_vendor_order_rejected;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_edit_order_reject)
    TextView txt_edit_order_reject;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_order_reject_bypetlover)
    LinearLayout ll_order_reject_bypetlover;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_reject_date_petlover)
    TextView txt_order_reject_date_petlover;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_reject_date_reason)
    TextView txt_order_reject_date_reason;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_vendor_order_rejected_bypetlover)
    ImageView img_vendor_order_rejected_bypetlover;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_edit_order_reject_petlover)
    TextView txt_edit_order_reject_petlover;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_vendor_order_dispatched)
    ImageView img_vendor_order_dispatched;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_dispatch_date)
    TextView txt_order_dispatch_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_dispatch_packdetails)
    TextView txt_order_dispatch_packdetails;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_vendor_order_transit)
    ImageView img_vendor_order_transit;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_order_transit_date)
    TextView txt_order_transit_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_order_reject)
    LinearLayout ll_order_reject;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_vendor_footer)
    View include_vendor_footer;

    /* Bottom Navigation */

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_home)
    RelativeLayout rl_home;

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


    private int _id;
    private String orderid;
    private String fromactivity;
    private List<TrackOrderDetailsResponse.DataBean.ProdcutTrackDetailsBean> prodcutTrackDetailsBeanList;


    @SuppressLint({"LogNotTimber", "LongLogTag"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_track_order_status);
        ButterKnife.bind(this);
        img_back.setOnClickListener(this);

        txt_booked_date.setText(" ");
        txt_order_confirm_date.setText("");
        txt_order_dispatch_packdetails.setText("");
        txt_order_reject_date_reason.setText("");
        txt_order_dispatch_date.setText(" ");
        txt_order_transit_date.setText(" ");



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            _id = extras.getInt("_id");
            orderid = extras.getString("orderid");
            fromactivity = extras.getString("fromactivity");
            Log.w(TAG,"_id : "+_id);
        }
        if (new ConnectionDetector(VendorTrackOrderActivity.this).isNetworkAvailable(VendorTrackOrderActivity.this)) {
            vendor_fetch_single_product_detail_ResponseCall();
        }

//        bottom_navigation_view = include_vendor_footer.findViewById(R.id.bottom_navigation_view);
//        bottom_navigation_view.setItemIconTintList(null);
//        bottom_navigation_view.getMenu().findItem(R.id.home).setChecked(true);
//        bottom_navigation_view.setOnNavigationItemSelectedListener(this);


        /*home*/

        title_shop.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_shop.setImageResource(R.drawable.grey_shop_selector);
        title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_community.setImageResource(R.drawable.grey_community);


        rl_home.setOnClickListener(this);

        rl_shop.setOnClickListener(this);

        rl_comn.setOnClickListener(this);


        rl_homes.setOnClickListener(this);




    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void vendor_fetch_single_product_detail_ResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<TrackOrderDetailsResponse> call = apiInterface.vendor_fetch_single_product_detail_ResponseCall(RestUtils.getContentType(), vendorTrackOrderDetailsRequest());
        Log.w(TAG,"TrackOrderDetailsResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<TrackOrderDetailsResponse>() {
            @SuppressLint({"LongLogTag", "LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<TrackOrderDetailsResponse> call, @NonNull Response<TrackOrderDetailsResponse> response) {

                Log.w(TAG,"TrackOrderDetailsResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        if(response.body().getData()!=null){

                            if(response.body().getData().getProduct_name()!=null&&!(response.body().getData().getProduct_name().isEmpty())){
                                txt_product_title.setText(response.body().getData().getProduct_name());
                            }
                            if(response.body().getData().getProduct_price()!=0){
                                txt_products_price.setText("INR "+response.body().getData().getProduct_price());
                            }
                            if(response.body().getData().getProduct_booked()!=null){
                                txt_order_date.setText(response.body().getData().getProduct_booked());
                            }
                            if(orderid !=null){
                                txt_booking_id.setText(orderid);
                            }
                            if(response.body().getData().getProduct_price() !=0){
                                txt_total_order_cost.setText("INR "+response.body().getData().getProduct_price());
                            }
                            if(response.body().getData().getProduct_count() !=0){
                                txt_quantity.setText(""+response.body().getData().getProduct_count());
                            }
                           
                           /* if(response.body().getData().getVendor_complete_info() !=null) {
                                txt_order_dispatch_packdetails.setText(response.body().getData().getVendor_complete_info());
                            }*/
                            if (response.body().getData().getProduct_image() != null && !response.body().getData().getProduct_image().isEmpty()) {
                                Glide.with(getApplicationContext())
                                        .load(response.body().getData().getProduct_image())
                                        .into(img_products_image);
                            }
                            else{
                                Glide.with(getApplicationContext())
                                        .load(APIClient.PROFILE_IMAGE_URL)
                                        .into(img_products_image);

                            }

                            if(response.body().getData().getProdcut_track_details() != null && response.body().getData().getProdcut_track_details().size()>0){
                                prodcutTrackDetailsBeanList = response.body().getData().getProdcut_track_details();


                                for(int i=0; i<prodcutTrackDetailsBeanList.size();i++){
                                    if(prodcutTrackDetailsBeanList.get(i).getTitle()!= null && !prodcutTrackDetailsBeanList.get(i).getTitle().isEmpty()){
                                        Log.w(TAG, " Title " + i+" " + prodcutTrackDetailsBeanList.get(i).getTitle());
                                        switch (prodcutTrackDetailsBeanList.get(i).getTitle()) {
                                            case "Order Booked":
                                                if (prodcutTrackDetailsBeanList.get(i).isStatus()) {
                                                    Log.w(TAG, "Order Booked Date " + prodcutTrackDetailsBeanList.get(i).getDate());
                                                    txt_booked_date.setText(" " + prodcutTrackDetailsBeanList.get(i).getDate());
                                                //    txt_booked_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                                                    img_vendor_booked.setImageResource(R.drawable.completed);
                                                }else {
                                                    //txt_booked_date.setText(" ");
                                                    txt_booked_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.coolGrey));
                                                    img_vendor_booked.setImageResource(R.drawable.button_grey_circle);

                                                }

                                                break;
                                            case "Order Accept":
                                                if (prodcutTrackDetailsBeanList.get(i).isStatus()) {
                                                    txt_order_confirm_date.setText(" " + prodcutTrackDetailsBeanList.get(i).getDate());
                                                  //  txt_order_confirm_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                                                    img_vendor_confirmed.setImageResource(R.drawable.completed);

                                                } else {
                                                    txt_order_confirm_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.coolGrey));
                                                    img_vendor_confirmed.setImageResource(R.drawable.button_grey_circle);

                                                }

                                                break;
                                            case "Order Dispatch":
                                                if (prodcutTrackDetailsBeanList.get(i).isStatus()) {
                                                    txt_order_dispatch_date.setText(" " + prodcutTrackDetailsBeanList.get(i).getDate());
                                                  //  txt_order_dispatch_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                                                    txt_order_transit_date.setText(" " + prodcutTrackDetailsBeanList.get(i).getDate());
                                                   // txt_order_transit_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                                                    img_vendor_order_dispatched.setImageResource(R.drawable.completed);
                                                    img_vendor_order_transit.setImageResource(R.drawable.completed);
                                                    txt_order_dispatch_packdetails.setVisibility(View.VISIBLE);
                                                    txt_order_dispatch_packdetails.setText(prodcutTrackDetailsBeanList.get(i).getText());


                                                } else {
                                                    txt_order_dispatch_date.setText(" ");
                                                    txt_order_transit_date.setText(" ");
                                                    img_vendor_order_dispatched.setImageResource(R.drawable.button_grey_circle);
                                                    img_vendor_order_transit.setImageResource(R.drawable.button_grey_circle);
                                                    txt_order_transit_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.coolGrey));
                                                    txt_order_dispatch_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.coolGrey));
                                                    txt_order_dispatch_packdetails.setVisibility(View.GONE);

                                                }
                                                break;
                                            case "Order Cancelled":
                                                if (prodcutTrackDetailsBeanList.get(i).isStatus()) {
                                                    ll_order_reject_bypetlover.setVisibility(View.VISIBLE);
                                                    txt_order_reject_date_petlover.setText(" " + prodcutTrackDetailsBeanList.get(i).getDate());
                                                    txt_order_vendor_reject_date_reason.setText(prodcutTrackDetailsBeanList.get(i).getText());
                                                    txt_order_vendor_reject_date_reason.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                                                    img_vendor_order_rejected_bypetlover.setImageResource(R.drawable.ic_baseline_check_circle_24);

                                                } else {
                                                    ll_order_reject_bypetlover.setVisibility(View.GONE);

                                                }

                                                break;
                                            case "Vendor cancelled":
                                                if (prodcutTrackDetailsBeanList.get(i).isStatus()) {
                                                    ll_order_reject.setVisibility(View.VISIBLE);
                                                    txt_order_reject_date.setText(" " + prodcutTrackDetailsBeanList.get(i).getDate());
                                                    txt_order_vendor_reject_date_reason.setText(prodcutTrackDetailsBeanList.get(i).getText());
                                                    txt_order_vendor_reject_date_reason.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                                                    img_vendor_order_rejected.setImageResource(R.drawable.ic_baseline_check_circle_24);


                                                } else {
                                                    ll_order_reject.setVisibility(View.GONE);


                                                }

                                                break;
                                        }


                                    }

                                }

                            }



                        }


                    }

                }


            }

            @SuppressLint({"LongLogTag", "LogNotTimber"})
            @Override
            public void onFailure(@NonNull Call<TrackOrderDetailsResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"TrackOrderDetailsResponse flr"+"--->" + t.getMessage());
            }
        });

    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private VendorTrackOrderDetailsRequest vendorTrackOrderDetailsRequest() {
        VendorTrackOrderDetailsRequest vendorTrackOrderDetailsRequest = new VendorTrackOrderDetailsRequest();
        vendorTrackOrderDetailsRequest.setOrder_id(orderid);
        vendorTrackOrderDetailsRequest.setProduct_order_id(_id);
        vendorTrackOrderDetailsRequest.setVendor_id(APIClient.VENDOR_ID);
        Log.w(TAG,"trackOrderDetailsRequest"+ "--->" + new Gson().toJson(vendorTrackOrderDetailsRequest));
        return vendorTrackOrderDetailsRequest;
    }

//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.home:
//                callDirections("1");
//                break;
//            case R.id.feeds:
//                callDirections("2");
//                break;
//
//            case R.id.community:
//                callDirections("3");
//                break;
//
//            default:
//                return  false;
//        }
//
//        return false;
//    }
    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), VendorDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.rl_homes:

                callDirections("1");
                break;

            case R.id.rl_home:

                callDirections("1");
                break;

            case R.id.rl_shop:
                callDirections("2");
                break;


            case R.id.rl_comn:

                callDirections("3");
                break;


        }
    }

}