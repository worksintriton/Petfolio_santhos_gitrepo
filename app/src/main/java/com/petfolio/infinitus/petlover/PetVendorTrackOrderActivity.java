package com.petfolio.infinitus.petlover;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.petfolio.infinitus.R;
import com.petfolio.infinitus.api.APIClient;
import com.petfolio.infinitus.api.RestApiInterface;
import com.petfolio.infinitus.requestpojo.VendorOrderDetailsRequest;
import com.petfolio.infinitus.responsepojo.DropDownListResponse;
import com.petfolio.infinitus.responsepojo.VendorOrderDetailsResponse;
import com.petfolio.infinitus.responsepojo.VendorReasonListResponse;
import com.petfolio.infinitus.utils.ConnectionDetector;
import com.petfolio.infinitus.utils.RestUtils;
import com.petfolio.infinitus.vendor.VendorUpdateOrderStatusActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetVendorTrackOrderActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PetVendorTrackOrderActivity" ;

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


    private List<DropDownListResponse.DataBean.SpecialzationBean> petSpecilaziationList;
    private String _id;
    private List<VendorOrderDetailsResponse.DataBean.ProdcutTrackDetailsBean> prodcutTrackDetailsBeanList;


    @SuppressLint({"LogNotTimber", "LongLogTag"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_vendor_track_order_status);
        ButterKnife.bind(this);

        img_back.setOnClickListener(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            _id = extras.getString("_id");
            Log.w(TAG,"_id : "+_id);


        }

        if (new ConnectionDetector(PetVendorTrackOrderActivity.this).isNetworkAvailable(PetVendorTrackOrderActivity.this)) {
            vendorOrderDetailsResponseCall();

        }



    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                onBackPressed();
                break;




        }

    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private void vendorOrderDetailsResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<VendorOrderDetailsResponse> call = apiInterface.vendorOrderDetailsResponseCall(RestUtils.getContentType(), vendorOrderDetailsRequest());
        Log.w(TAG,"vendorOrderDetailsResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<VendorOrderDetailsResponse>() {
            @SuppressLint({"LongLogTag", "LogNotTimber", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<VendorOrderDetailsResponse> call, @NonNull Response<VendorOrderDetailsResponse> response) {

                Log.w(TAG,"vendorOrderDetailsResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        if(response.body().getData()!=null){

                            if(response.body().getData().getProduct_name()!=null&&!(response.body().getData().getProduct_name().isEmpty())){
                                txt_product_title.setText(response.body().getData().getProduct_name());
                            }
                            if(response.body().getData().getProduct_price()!=0){
                                txt_products_price.setText("\u20B9 "+response.body().getData().getProduct_price());
                            }
                            if(response.body().getData().getDate_of_booking_display()!=null){
                                txt_order_date.setText(response.body().getData().getDate_of_booking_display());
                            }
                            if(response.body().getData().getOrder_id()!=null){
                                txt_booking_id.setText(response.body().getData().getOrder_id());
                            }
                            if(response.body().getData().getGrand_total() !=0){
                                txt_total_order_cost.setText("\u20B9 "+response.body().getData().getGrand_total());
                            }
                            if(response.body().getData().getProduct_quantity() !=0){
                                txt_quantity.setText(""+response.body().getData().getProduct_quantity());
                            }
                            if(response.body().getData().getProduct_quantity() !=0){
                                txt_quantity.setText(""+response.body().getData().getProduct_quantity());
                            }
                            if (response.body().getData().getProdcut_image() != null && !response.body().getData().getProdcut_image().isEmpty()) {
                                Glide.with(getApplicationContext())
                                        .load(response.body().getData().getProdcut_image())
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
                                        Log.w(TAG, "Title " + i + prodcutTrackDetailsBeanList.get(i).getTitle());
                                        if(prodcutTrackDetailsBeanList.get(i).getTitle().equals("Order Booked")){
                                            if(prodcutTrackDetailsBeanList.get(i).isStatus()){
                                                txt_booked_date.setText(" " + prodcutTrackDetailsBeanList.get(i).getDate());
                                                txt_booked_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                                                img_vendor_booked.setImageResource(R.drawable.completed);
                                            } else {
                                                txt_booked_date.setText(" " );
                                                txt_booked_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.coolGrey));
                                                img_vendor_booked.setImageResource(R.drawable.button_grey_circle);

                                            }

                                        }
                                        else if(prodcutTrackDetailsBeanList.get(i).getTitle().equals("Order Accept")) {
                                            if (prodcutTrackDetailsBeanList.get(i).isStatus()) {
                                                txt_order_confirm_date.setText(" " + prodcutTrackDetailsBeanList.get(i).getDate());
                                                txt_order_confirm_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                                                img_vendor_confirmed.setImageResource(R.drawable.completed);

                                            } else {
                                                txt_booked_date.setText(" ");
                                                txt_order_confirm_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.coolGrey));
                                                img_vendor_confirmed.setImageResource(R.drawable.button_grey_circle);

                                            }

                                        }
                                        else if(prodcutTrackDetailsBeanList.get(i).getTitle().equals("Order Dispatch")) {
                                            if (prodcutTrackDetailsBeanList.get(i).isStatus()) {
                                                txt_order_dispatch_date.setText(" " + prodcutTrackDetailsBeanList.get(i).getDate());
                                                txt_order_dispatch_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                                                txt_order_transit_date.setText(" " + prodcutTrackDetailsBeanList.get(i).getDate());
                                                txt_order_transit_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                                                img_vendor_order_dispatched.setImageResource(R.drawable.completed);
                                                img_vendor_order_transit.setImageResource(R.drawable.completed);
                                            } else {
                                                txt_order_dispatch_date.setText(" ");
                                                txt_order_transit_date.setText(" ");
                                                img_vendor_order_dispatched.setImageResource(R.drawable.button_grey_circle);
                                                img_vendor_order_transit.setImageResource(R.drawable.button_grey_circle);
                                                txt_order_transit_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.coolGrey));
                                                txt_order_dispatch_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.coolGrey));
                                                txt_order_dispatch_packdetails.setVisibility(View.GONE);

                                            }

                                        }
                                        else if(prodcutTrackDetailsBeanList.get(i).getTitle().equals("Order Cancelled")) {
                                            if (prodcutTrackDetailsBeanList.get(i).isStatus()) {
                                                ll_order_reject_bypetlover.setVisibility(View.VISIBLE);
                                                txt_order_reject_date_petlover.setText(" " + prodcutTrackDetailsBeanList.get(i).getDate());
                                                txt_order_reject_date_petlover.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                                                img_vendor_order_rejected_bypetlover.setImageResource(R.drawable.ic_baseline_check_circle_24);

                                            } else {
                                                ll_order_reject_bypetlover.setVisibility(View.GONE);

                                            }

                                        }
                                        else if(prodcutTrackDetailsBeanList.get(i).getTitle().equals("Vendor cancelled")) {
                                            if (prodcutTrackDetailsBeanList.get(i).isStatus()) {
                                                ll_order_reject.setVisibility(View.VISIBLE);
                                                txt_order_reject_date.setText(" " + prodcutTrackDetailsBeanList.get(i).getDate());
                                                txt_order_reject_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                                                img_vendor_order_rejected.setImageResource(R.drawable.ic_baseline_check_circle_24);


                                            } else {
                                                ll_order_reject.setVisibility(View.GONE);


                                            }

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
            public void onFailure(@NonNull Call<VendorOrderDetailsResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"VendorOrderDetailsResponse flr"+"--->" + t.getMessage());
            }
        });

    }

    @SuppressLint({"LongLogTag", "LogNotTimber"})
    private VendorOrderDetailsRequest vendorOrderDetailsRequest() {

        VendorOrderDetailsRequest vendorOrderDetailsRequest = new VendorOrderDetailsRequest();

        vendorOrderDetailsRequest.set_id(_id);

        Log.w(TAG,"vendorOrderDetailsRequest"+ "--->" + new Gson().toJson(vendorOrderDetailsRequest));

        return vendorOrderDetailsRequest;
    }




}