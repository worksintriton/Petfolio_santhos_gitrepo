package com.petfolio.infinitus.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.petfolio.infinitus.R;
import com.petfolio.infinitus.api.APIClient;
import com.petfolio.infinitus.api.RestApiInterface;
import com.petfolio.infinitus.requestpojo.ShippingAddressDeleteRequest;
import com.petfolio.infinitus.requestpojo.ShippingAddressFetchByUserIDRequest;
import com.petfolio.infinitus.requestpojo.VendorOrderBookingCreateRequest;
import com.petfolio.infinitus.responsepojo.CartDetailsResponse;
import com.petfolio.infinitus.responsepojo.CartSuccessResponse;
import com.petfolio.infinitus.responsepojo.ShippingAddressDeleteResponse;
import com.petfolio.infinitus.responsepojo.ShippingAddressFetchByUserIDResponse;
import com.petfolio.infinitus.sessionmanager.SessionManager;
import com.petfolio.infinitus.utils.ConnectionDetector;
import com.petfolio.infinitus.utils.RestUtils;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingAddressActivity extends AppCompatActivity implements View.OnClickListener, PaymentResultListener {

    private String TAG = "ShippingAddressActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_addresslist)
    LinearLayout ll_addresslist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_address_list_show)
    LinearLayout ll_address_list_show;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_username)
    TextView txt_username;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_phnum)
    TextView txt_phnum;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_user_city)
    TextView txt_user_city;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_street)
    TextView txt_street;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_dist_pincode_state)
    TextView txt_dist_pincode_state;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_date)
    TextView txt_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_addrs_type)
    TextView txt_addrs_type;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_edit)
    LinearLayout ll_edit;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_delete)
    LinearLayout ll_delete;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_rupee)
    Button btn_rupee;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_continue)
    Button btn_continue;

    String userid, name, phonum, state, street, landmark_pincode, address_type, date, shipid, fromactivity;

    String first_name,last_name,flat_no,landmark,pincode,alt_phonum,address_status;

    ShippingAddressFetchByUserIDResponse.DataBean dataBeanList;

    private Dialog dialog;

    private String Payment_id = "";

    List<VendorOrderBookingCreateRequest.DataBean> Data = new ArrayList<>();

    private int prodouct_total;

    private int shipping_charge;

    private int discount_price;

    private int grand_total;

    private int prodcut_count;

    private int prodcut_item_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shipping_address);

        Log.w(TAG,"onCreate ");

        ButterKnife.bind(this);

        SessionManager session = new SessionManager(getApplicationContext());

        HashMap<String, String> user = session.getProfileDetails();

        userid = user.get(SessionManager.KEY_ID);

        Log.w(TAG,"User ID:  "+userid);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromactivity = extras.getString("fromactivity");

            if(!fromactivity.equals("ShippingAddressAddActivity"))
            {
                Log.w(TAG,"From "+ fromactivity +" : true-->");

             //   Data = (List<VendorOrderBookingCreateRequest.DataBean>) extras.getSerializable("data");

                prodouct_total = extras.getInt("product_total");

                shipping_charge = extras.getInt("shipping_charge");

                discount_price = extras.getInt("discount_price");

                grand_total = extras.getInt("grand_total");

                prodcut_count = extras.getInt("prodcut_count");

                prodcut_item_count = extras.getInt("prodcut_item_count");

                if (new ConnectionDetector(ShippingAddressActivity.this).isNetworkAvailable(ShippingAddressActivity.this)) {

                    shippingAddressresponseCall(userid);

                }

            }

            else
            {
                Log.w(TAG,"false-->");

                shipid = extras.getString("shipid");

                first_name = extras.getString("first_name");

                last_name = extras.getString("last_name");

                name = first_name + " " + last_name;

                phonum = extras.getString("phonum");

                alt_phonum = extras.getString("alt_phonum");

                flat_no = extras.getString("flat_no");

                state = extras.getString("state");

                street = extras.getString("street");

                landmark = extras.getString("landmark");

                pincode  = extras.getString("pincode");

                landmark_pincode = landmark +" , "+ pincode;

                address_type = extras.getString("address_type");

                date = extras.getString("date");

                address_status = extras.getString("address_status");

                setView();

            }

        }

        img_back.setOnClickListener(this);

        ll_addresslist.setOnClickListener(this);

        ll_edit.setOnClickListener(this);

        ll_delete.setOnClickListener(this);

        btn_continue.setOnClickListener(this);


    }

    private void setView() {

        if(name!=null&&!name.isEmpty()){

            txt_username.setText(name);
        }

        if(phonum!=null&&!phonum.isEmpty()){

            txt_phnum.setText(phonum);
        }

        if(state!=null&&!state.isEmpty()){

            txt_user_city.setText(state);
        }

        if(landmark_pincode!=null&&!landmark_pincode.isEmpty()){

            txt_dist_pincode_state.setText(landmark_pincode);
        }

        if(street!=null&&!street.isEmpty()){

            txt_street.setText(street);

        }

        if(date!=null&&!date.isEmpty()){

            txt_date.setText(date);

        }

        if(address_type!=null&&!address_type.isEmpty()){

            txt_addrs_type.setText(address_type);

        }
    }


    @SuppressLint("LogNotTimber")
    private void shippingAddressresponseCall(String userid) {

        avi_indicator.setVisibility(View.VISIBLE);

        avi_indicator.smoothToShow();

        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);

        Call<ShippingAddressFetchByUserIDResponse> call = apiInterface.fetch_shipp_addr_ResponseCall(RestUtils.getContentType(), shippingAddressFetchByUserIDRequest(userid));

        Log.w(TAG,"ShippingAddressFetchByUserIDResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<ShippingAddressFetchByUserIDResponse>() {
            @Override
            public void onResponse(@NonNull Call<ShippingAddressFetchByUserIDResponse> call, @NonNull Response<ShippingAddressFetchByUserIDResponse> response) {

                Log.w(TAG,"ShippingAddressFetchByUserIDResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null)
                {
                    if(response.body().getCode() == 200)
                    {

                        if(response.body().getData()!=null)
                        {
                            dataBeanList = response.body().getData();

                            if(dataBeanList.getUser_address_stauts()!=null&&!dataBeanList.getUser_address_stauts().isEmpty())
                            {

                                Log.w(TAG, "Status" + dataBeanList.getUser_address_stauts());

                                if(dataBeanList.getUser_address_stauts().equals("Last Used")){

                                    Log.w(TAG,"true-->");

                                    shipid = dataBeanList.get_id();

                                    first_name = dataBeanList.getUser_first_name();

                                    last_name = dataBeanList.getUser_last_name();

                                    name = first_name + " " + last_name;

                                    phonum = dataBeanList.getUser_mobile();

                                    alt_phonum = dataBeanList.getUser_alter_mobile();

                                    flat_no = dataBeanList.getUser_flat_no();

                                    state = dataBeanList.getUser_state();

                                    street = dataBeanList.getUser_stree();

                                    landmark = dataBeanList.getUser_landmark();

                                    pincode  = dataBeanList.getUser_picocode();

                                    landmark_pincode = landmark +" , "+ pincode;

                                    address_type = dataBeanList.getUser_address_type();

                                    Log.w(TAG, "address_type"+address_type);

                                    date = dataBeanList.getUser_display_date();

                                    address_status = dataBeanList.getUser_address_stauts();

                                    setView();

                                    ll_address_list_show.setVisibility(View.VISIBLE);

                                    txt_no_records.setVisibility(View.GONE);

                                    avi_indicator.smoothToHide();


                                }


                                else {

                                    showNoAddressAlert();

                                    ll_address_list_show.setVisibility(View.GONE);

                                    txt_no_records.setVisibility(View.VISIBLE);

                                    avi_indicator.smoothToHide();

                                }

                           }

                            else {

                                showNoAddressAlert();

                                ll_address_list_show.setVisibility(View.GONE);

                                txt_no_records.setVisibility(View.VISIBLE);

                                avi_indicator.smoothToHide();

                            }




                        }

                        else{
                            //showErrorLoading(response.body().getMessage());
                            avi_indicator.smoothToHide();

                            Toasty.warning(ShippingAddressActivity.this,"Server Issue",Toasty.LENGTH_LONG).show();
                        }


                    }

                    else{
                        //showErrorLoading(response.body().getMessage());
                        avi_indicator.smoothToHide();

                        Toasty.warning(ShippingAddressActivity.this,"Server Issue",Toasty.LENGTH_LONG).show();
                    }


                }

                else{
                    //showErrorLoading(response.body().getMessage());
                    avi_indicator.smoothToHide();

                    Toasty.warning(ShippingAddressActivity.this,"Server Issue",Toasty.LENGTH_LONG).show();
                }



            }

            @Override
            public void onFailure(@NonNull Call<ShippingAddressFetchByUserIDResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"ShippingAddressFetchByUserIDResponse flr"+"--->" + t.getMessage());
            }
        });


    }

    @SuppressLint("LogNotTimber")
    private ShippingAddressFetchByUserIDRequest shippingAddressFetchByUserIDRequest(String userid) {


        /**
         * user_id : 6048589d0b3a487571a1c567
         */


        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        ShippingAddressFetchByUserIDRequest shippingAddressFetchByUserIDRequest = new ShippingAddressFetchByUserIDRequest();
        shippingAddressFetchByUserIDRequest.setUser_id(userid);


        Log.w(TAG,"shippingAddressFetchByUserIDRequest"+ "--->" + new Gson().toJson(shippingAddressFetchByUserIDRequest));
        return shippingAddressFetchByUserIDRequest;
    }


    private void deleteshipAddrresponseCall(String shippingid) {

        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ShippingAddressDeleteResponse> call = apiInterface.delete_shipp_addr_ResponseCall(RestUtils.getContentType(), shippingAddressDeleteRequest(shippingid));

        Log.w(TAG,"ShippingAddressDeleteResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<ShippingAddressDeleteResponse>() {
            @Override
            public void onResponse(@NonNull Call<ShippingAddressDeleteResponse> call, @NonNull Response<ShippingAddressDeleteResponse> response) {

                Log.w(TAG,"ShippingAddressDeleteResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        startActivity(getIntent());

                        finish();

                    }
                    else{
                        //showErrorLoading(response.body().getMessage());
                        avi_indicator.smoothToHide();

                        Toasty.warning(ShippingAddressActivity.this,"Server Issue",Toasty.LENGTH_LONG).show();
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<ShippingAddressDeleteResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"ShippingAddressDeleteResponse flr"+"--->" + t.getMessage());
            }
        });




    }

    @SuppressLint("LogNotTimber")
    private ShippingAddressDeleteRequest shippingAddressDeleteRequest(String shipid) {


        /**
         * _id : 6057129e2e3b894a69767d40
         */

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        ShippingAddressDeleteRequest shippingAddressDeleteRequest = new ShippingAddressDeleteRequest();
        shippingAddressDeleteRequest.set_id(shipid);


        Log.w(TAG,"shippingAddressDeleteRequest"+ "--->" + new Gson().toJson(shippingAddressDeleteRequest));
        return shippingAddressDeleteRequest;
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
    private VendorOrderBookingCreateRequest vendorOrderBookingCreateRequest() {
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

        VendorOrderBookingCreateRequest vendorOrderBookingCreateRequest = new VendorOrderBookingCreateRequest();
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
        vendorOrderBookingCreateRequest.setShipping_details_id(shipid);
        vendorOrderBookingCreateRequest.setPayment_id(Payment_id);
        Log.w(TAG,"vendorOrderBookingCreateRequest"+ "--->" + new Gson().toJson(vendorOrderBookingCreateRequest));
        return vendorOrderBookingCreateRequest;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.ll_addresslist:
                gotoShippingaddresslist();
                break;

            case R.id.ll_edit:
                gotoShippingaddressEdit();
                break;

            case R.id.ll_delete:
                showWaring();
                break;

            case R.id.btn_continue:
                if(grand_total!=0){
                    startPayment();
                }
                break;
        }

    }


    private void gotoShippingaddresslist() {

        Intent intent = new Intent(ShippingAddressActivity.this, ShippingAddressAddActivity.class);

        startActivity(intent);
    }

    private void gotoShippingaddressEdit() {

        Intent intent = new Intent(getApplicationContext(), ShippingAddressEditActivity.class);

        intent.putExtra("fromactivity", TAG);

        intent.putExtra("shipid",shipid);

        intent.putExtra("first_name",first_name);

        intent.putExtra("last_name",last_name);

        intent.putExtra("phonum",phonum);

        intent.putExtra("alt_phonum",alt_phonum);

        intent.putExtra("flat_no",flat_no);

        intent.putExtra("state",state);

        intent.putExtra("street",street);

        intent.putExtra("landmark",landmark);

        intent.putExtra("pincode",pincode);

        intent.putExtra("address_type",address_type);

        intent.putExtra("date",date);

        intent.putExtra("address_status",address_status);

        startActivity(intent);

        finish();

    }

    private void gotoShippingaddressCreate() {

        Intent intent = new Intent(ShippingAddressActivity.this, ShippingAddressCreateActivity.class);

        startActivity(intent);

    }

    private void showWaring() {

        try{

            dialog = new Dialog(ShippingAddressActivity.this);
            dialog.setContentView(R.layout.alert_cancel_layout);
            dialog.setCanceledOnTouchOutside(false);
            Button btn_ok = dialog.findViewById(R.id.btn_ok);
            Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
            TextView txt_msg = dialog.findViewById(R.id.txt_text_message);
            txt_msg.setText("Are you sure want to delete");
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (new ConnectionDetector(ShippingAddressActivity.this).isNetworkAvailable(ShippingAddressActivity.this)) {

                        deleteshipAddrresponseCall(shipid);

                    }

                    dialog.dismiss();

                }
            });
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();

                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            dialog.show();


        }
        catch(
                WindowManager.BadTokenException e){
            e.printStackTrace();
        }
    }

    private void showNoAddressAlert() {

        try{

            dialog = new Dialog(ShippingAddressActivity.this);
            dialog.setContentView(R.layout.alert_cancel_layout);
            dialog.setCanceledOnTouchOutside(false);
            Button btn_ok = dialog.findViewById(R.id.btn_ok);
            Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
            TextView txt_msg = dialog.findViewById(R.id.txt_text_message);
            txt_msg.setText("No Address Found ! Please Add Some Address");
            txt_msg.setTextSize(12);
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    gotoShippingaddressCreate();

                    dialog.dismiss();

                }
            });
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();

                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            dialog.show();


        }
        catch(
                WindowManager.BadTokenException e){
            e.printStackTrace();
        }

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();
    }
}