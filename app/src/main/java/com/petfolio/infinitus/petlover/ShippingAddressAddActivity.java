package com.petfolio.infinitus.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.petfolio.infinitus.R;
import com.petfolio.infinitus.api.APIClient;
import com.petfolio.infinitus.api.RestApiInterface;
import com.petfolio.infinitus.requestpojo.ShippingAddressFetchUserRequest;
import com.petfolio.infinitus.responsepojo.ShippingAddressFetchUserResponse;
import com.petfolio.infinitus.sessionmanager.SessionManager;
import com.petfolio.infinitus.utils.ConnectionDetector;
import com.petfolio.infinitus.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingAddressAddActivity extends AppCompatActivity implements View.OnClickListener{

    private String TAG = "ShippingAddressAddActivity";

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
    @BindView(R.id.rv_shipping_address)
    RecyclerView rv_shipping_address;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_add_address)
    LinearLayout ll_add_address;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_cancel)
    Button btn_cancel;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_use_this_addreess)
    Button btn_use_this_addreess;

    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address_add);

        Log.w(TAG,"onCreate ");

        ButterKnife.bind(this);

        SessionManager session = new SessionManager(getApplicationContext());

        HashMap<String, String> user = session.getProfileDetails();

        userid = user.get(SessionManager.KEY_ID);

        Log.w(TAG,"Vendor ID:  "+userid);

        ll_add_address.setOnClickListener(this);

        img_back.setOnClickListener(this);

        btn_cancel.setOnClickListener(this);

        btn_use_this_addreess.setOnClickListener(this);

        if (new ConnectionDetector(ShippingAddressAddActivity.this).isNetworkAvailable(ShippingAddressAddActivity.this)) {

            shippingAddressresponseCall(userid);

        }

    }

    @Override
    public void onClick(View v) {



    }


    @SuppressLint("LogNotTimber")
    private void shippingAddressresponseCall(String userid) {

        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ShippingAddressFetchUserResponse> call = apiInterface.fetch_shipp_addr_ResponseCall(RestUtils.getContentType(), shippingAddressFetchUserRequest(userid));

        Log.w(TAG,"ShippingAddressFetchUserResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<ShippingAddressFetchUserResponse>() {
            @Override
            public void onResponse(@NonNull Call<ShippingAddressFetchUserResponse> call, @NonNull Response<ShippingAddressFetchUserResponse> response) {

                Log.w(TAG,"VendorFetchOrderDetailsResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

//                        if(response.body().getMessage()!=null&&response.body().getMessage().equals("No Shipping address details")){
//
//                            showNoAddressAlert();
//
//                        }
//
//                        else if(response.body().getData()!=null)
//
//                        {
//                            dataBeanList = response.body().getData();
//
//                            if(dataBeanList.size()>0){
//
//
//                            }
//
//                            else {
//
//                                ll_address_list_show.setVisibility(View.GONE);
//
//                                txt_no_records.setVisibility(View.VISIBLE);
//
//                                avi_indicator.smoothToHide();
//
//                            }
//
//                        }

                    }
                    else{
                        //showErrorLoading(response.body().getMessage());
                        avi_indicator.smoothToHide();

                        Toasty.warning(ShippingAddressAddActivity.this,"Server Issue",Toasty.LENGTH_LONG).show();
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<ShippingAddressFetchUserResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"ShippingAddressFetchUserResponse flr"+"--->" + t.getMessage());
            }
        });


    }

    @SuppressLint("LogNotTimber")
    private ShippingAddressFetchUserRequest shippingAddressFetchUserRequest(String userid) {


        /**
         * user_id : 6048589d0b3a487571a1c567
         */


        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        ShippingAddressFetchUserRequest shippingAddressFetchUserRequest = new ShippingAddressFetchUserRequest();
        shippingAddressFetchUserRequest.setUser_id(userid);


        Log.w(TAG,"shippingAddressFetchUserRequest"+ "--->" + new Gson().toJson(shippingAddressFetchUserRequest));
        return shippingAddressFetchUserRequest;
    }

}