package com.petfolio.infinitus.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import com.google.gson.Gson;
import com.petfolio.infinitus.R;
import com.petfolio.infinitus.api.APIClient;
import com.petfolio.infinitus.api.RestApiInterface;
import com.petfolio.infinitus.requestpojo.ShippingAddressDeleteRequest;
import com.petfolio.infinitus.requestpojo.ShippingAddressFetchUserRequest;
import com.petfolio.infinitus.requestpojo.VendorFetchOrderDetailsIdRequest;
import com.petfolio.infinitus.responsepojo.ShippingAddressDeleteResponse;
import com.petfolio.infinitus.responsepojo.ShippingAddressFetchUserResponse;
import com.petfolio.infinitus.responsepojo.VendorFetchOrderDetailsResponse;
import com.petfolio.infinitus.sessionmanager.SessionManager;
import com.petfolio.infinitus.utils.ConnectionDetector;
import com.petfolio.infinitus.utils.RestUtils;
import com.petfolio.infinitus.vendor.VendorOrderDetailsActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingAddressActivity extends AppCompatActivity implements View.OnClickListener{

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

    String userid, name, phonum, state, street, landmark_pincode, address_type, date, shipid;

    List<ShippingAddressFetchUserResponse.DataBean> dataBeanList;

    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shipping_address);

        Log.w(TAG,"onCreate ");

        ButterKnife.bind(this);

        SessionManager session = new SessionManager(getApplicationContext());

        HashMap<String, String> user = session.getProfileDetails();

        userid = user.get(SessionManager.KEY_ID);

        Log.w(TAG,"Vendor ID:  "+userid);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {


        }

        else
        {

            if (new ConnectionDetector(ShippingAddressActivity.this).isNetworkAvailable(ShippingAddressActivity.this)) {

                shippingAddressresponseCall(userid);

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

                        if(response.body().getMessage()!=null&&response.body().getMessage().equals("No Shipping address details")){

                            showNoAddressAlert();

                        }

                        else if(response.body().getData()!=null)

                        {
                            dataBeanList = response.body().getData();

                            if(dataBeanList.size()>0){

                                for(int i=0;i<dataBeanList.size();i++){

                                    if(dataBeanList.get(i).getUser_address_stauts().equals("Last Used")){

                                        shipid = dataBeanList.get(i).get_id();

                                        name = dataBeanList.get(i).getUser_first_name() + " " +dataBeanList.get(i).getUser_last_name();

                                        phonum = dataBeanList.get(i).getUser_mobile();

                                        state = dataBeanList.get(i).getUser_state();

                                        street = dataBeanList.get(i).getUser_stree();

                                        landmark_pincode = dataBeanList.get(i).getUser_landmark() +" , "+ dataBeanList.get(i).getUser_picocode();

                                        address_type = "Home";

                                        date = "14/02/2021";

                                        setView();

                                    }
                                }

                            }

                            else {

                                ll_address_list_show.setVisibility(View.GONE);

                                txt_no_records.setVisibility(View.VISIBLE);

                                avi_indicator.smoothToHide();

                            }

                        }

                    }
                    else{
                        //showErrorLoading(response.body().getMessage());
                        avi_indicator.smoothToHide();

                        Toasty.warning(ShippingAddressActivity.this,"Server Issue",Toasty.LENGTH_LONG).show();
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
                gotoPaymentGateway();
                break;
        }

    }


    private void gotoShippingaddresslist() {

        Intent intent = new Intent(ShippingAddressActivity.this, ShippingAddressAddActivity.class);

        startActivity(intent);
    }

    private void gotoShippingaddressEdit() {

        Intent intent = new Intent(ShippingAddressActivity.this, ShippingAddressEditActivity.class);

        startActivity(intent);

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


    private void gotoPaymentGateway() {


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}