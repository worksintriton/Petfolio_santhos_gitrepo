package com.petfolio.infinitus.petlover.myaddresses;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.petfolio.infinitus.R;
import com.petfolio.infinitus.activity.location.ManageAddressActivity;
import com.petfolio.infinitus.adapter.ShippingAddressListAdapter;
import com.petfolio.infinitus.api.APIClient;
import com.petfolio.infinitus.api.RestApiInterface;
import com.petfolio.infinitus.interfaces.OnDeleteShipAddrListener;
import com.petfolio.infinitus.interfaces.OnEditShipAddrListener;
import com.petfolio.infinitus.interfaces.OnSelectingShipIdListener;
import com.petfolio.infinitus.petlover.ListOfProductsSeeMoreActivity;
import com.petfolio.infinitus.petlover.PetLoverDashboardActivity;
import com.petfolio.infinitus.petlover.PetLoverProfileScreenActivity;
import com.petfolio.infinitus.petlover.ShippingAddressEditActivity;
import com.petfolio.infinitus.requestpojo.ShippingAddrMarkAsLastUsedRequest;
import com.petfolio.infinitus.requestpojo.ShippingAddrMarkAsLastUsedResponse;
import com.petfolio.infinitus.requestpojo.ShippingAddressDeleteRequest;
import com.petfolio.infinitus.requestpojo.ShippingAddressListingByUserIDRequest;
import com.petfolio.infinitus.responsepojo.ShippingAddressDeleteResponse;
import com.petfolio.infinitus.responsepojo.ShippingAddressListingByUserIDResponse;
import com.petfolio.infinitus.sessionmanager.SessionManager;
import com.petfolio.infinitus.utils.ConnectionDetector;
import com.petfolio.infinitus.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAddressesListActivity extends AppCompatActivity implements View.OnClickListener, OnSelectingShipIdListener, OnEditShipAddrListener, OnDeleteShipAddrListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private String TAG = "MyAddressesListActivity";

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
    @BindView(R.id.ll_add_newaddress)
    LinearLayout ll_add_newaddress;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_savedaddress)
    TextView txt_savedaddress;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_footer)
    View include_petlover_footer;

    BottomNavigationView bottom_navigation_view;


    String userid,fromactivity;

    List<ShippingAddressListingByUserIDResponse.DataBean> dataBeanList;

    Dialog dialog;
    private String shippid;


    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address_list);

        Log.w(TAG,"onCreate ");

        ButterKnife.bind(this);

        SessionManager session = new SessionManager(getApplicationContext());

        HashMap<String, String> user = session.getProfileDetails();

        userid = user.get(SessionManager.KEY_ID);

        Log.w(TAG,"User ID:  "+userid);

        ll_add_newaddress.setOnClickListener(this);

        img_back.setOnClickListener(this);



        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromactivity = extras.getString("fromactivity");

            Log.w(TAG,"From "+ fromactivity +" : true-->");


        }

        bottom_navigation_view = include_petlover_footer.findViewById(R.id.bottom_navigation_view);
        bottom_navigation_view.setItemIconTintList(null);
        bottom_navigation_view.setOnNavigationItemSelectedListener(this);
        bottom_navigation_view.getMenu().findItem(R.id.shop).setChecked(true);

        if (new ConnectionDetector(MyAddressesListActivity.this).isNetworkAvailable(MyAddressesListActivity.this)) {

            shippingAddressresponseCall(userid);

        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.ll_add_newaddress:
                gotoShippingaddressCreate();
                break;


        }

    }

    @SuppressLint("LogNotTimber")
    private void shippingAddressresponseCall(String userid) {

        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ShippingAddressListingByUserIDResponse> call = apiInterface.list_shipp_addr_ResponseCall(RestUtils.getContentType(), shippingAddressListingByUserIDRequest(userid));

        Log.w(TAG,"ShippingAddressListingByUserIDResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<ShippingAddressListingByUserIDResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<ShippingAddressListingByUserIDResponse> call, @NonNull Response<ShippingAddressListingByUserIDResponse> response) {

                Log.w(TAG,"ShippingAddressListingByUserIDResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {

                    if(response.body().getCode() == 200){

                        if(response.body().getData()!=null) {
                            dataBeanList = response.body().getData();

                            if(dataBeanList != null && dataBeanList.size()>0) {
                                txt_savedaddress.setVisibility(View.VISIBLE);
                                txt_savedaddress.setText(dataBeanList.size()+" Saved Address");
                                setView();
                                txt_no_records.setVisibility(View.GONE);
                            }else{
                                txt_savedaddress.setVisibility(View.GONE);
                                txt_no_records.setVisibility(View.VISIBLE);
                                txt_no_records.setText("No address found");
                            }

                        }

                            else {

                                showNoAddressAlert();

                                rv_shipping_address.setVisibility(View.GONE);

                                txt_no_records.setVisibility(View.VISIBLE);

                                avi_indicator.smoothToHide();

                            }

                        }

                    }
                    else{
                        //showErrorLoading(response.body().getMessage());
                        avi_indicator.smoothToHide();

                    }
                }

            @Override
            public void onFailure(@NonNull Call<ShippingAddressListingByUserIDResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"ShippingAddressFetchByUserIDResponse flr"+"--->" + t.getMessage());
            }
        });


    }

    @SuppressLint("LogNotTimber")
    private ShippingAddressListingByUserIDRequest shippingAddressListingByUserIDRequest(String userid) {


        /**
         * user_id : 6048589d0b3a487571a1c567
         */


        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        ShippingAddressListingByUserIDRequest shippingAddressListingByUserIDRequest = new ShippingAddressListingByUserIDRequest();
        shippingAddressListingByUserIDRequest.setUser_id(userid);


        Log.w(TAG,"shippingAddressFetchByUserIDRequest"+ "--->" + new Gson().toJson(shippingAddressListingByUserIDRequest));
        return shippingAddressListingByUserIDRequest;
    }

    private void markAsLastUsedResponseCall(String shipsid){

        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ShippingAddrMarkAsLastUsedResponse> call = apiInterface.mark_shipp_addr_ResponseCall(RestUtils.getContentType(), shippingAddrMarkAsLastUsedRequest(shipsid));

        Log.w(TAG,"ShippingAddrMarkAsLastUsedResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<ShippingAddrMarkAsLastUsedResponse>() {
            @Override
            public void onResponse(@NonNull Call<ShippingAddrMarkAsLastUsedResponse> call, @NonNull Response<ShippingAddrMarkAsLastUsedResponse> response) {

                Log.w(TAG,"ShippingAddrMarkAsLastUsedResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        finish();
                        startActivity(getIntent());

                    }
                    else{
                        //showErrorLoading(response.body().getMessage());
                        avi_indicator.smoothToHide();

                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<ShippingAddrMarkAsLastUsedResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"ShippingAddrMarkAsLastUsedResponse flr"+"--->" + t.getMessage());
            }
        });


    }

    @SuppressLint("LogNotTimber")
    private ShippingAddrMarkAsLastUsedRequest shippingAddrMarkAsLastUsedRequest(String shipid) {

        /**
         * _id : 6058f4ebe748565ddb1fc515
         * user_id : 604081d12c2b43125f8cb840
         * user_address_stauts : Last Used
         */

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        ShippingAddrMarkAsLastUsedRequest shippingAddrMarkAsLastUsedRequest = new ShippingAddrMarkAsLastUsedRequest();
        shippingAddrMarkAsLastUsedRequest.set_id(shipid);
        shippingAddrMarkAsLastUsedRequest.setUser_id(userid);
        shippingAddrMarkAsLastUsedRequest.setUser_address_stauts("Last Used");


        Log.w(TAG,"shippingAddrMarkAsLastUsedRequest"+ "--->" + new Gson().toJson(shippingAddrMarkAsLastUsedRequest));
        return shippingAddrMarkAsLastUsedRequest;
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


    @SuppressLint("SetTextI18n")
    private void showNoAddressAlert() {

        try{

            dialog = new Dialog(MyAddressesListActivity.this);
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

    @SuppressLint("SetTextI18n")
    private void showWaring(String shippingid) {

        try{

            dialog = new Dialog(MyAddressesListActivity.this);
            dialog.setContentView(R.layout.alert_cancel_layout);
            dialog.setCanceledOnTouchOutside(false);
            Button btn_ok = dialog.findViewById(R.id.btn_ok);
            Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
            TextView txt_msg = dialog.findViewById(R.id.txt_text_message);
            txt_msg.setText("Are you sure want to delete");
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (new ConnectionDetector(MyAddressesListActivity.this).isNetworkAvailable(MyAddressesListActivity.this)) {

                        deleteshipAddrresponseCall(shippingid);

                    }

                    dialog.dismiss();

                }
            });
            btn_cancel.setOnClickListener(view -> dialog.dismiss());

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            dialog.show();


        }
        catch(
                WindowManager.BadTokenException e){
            e.printStackTrace();
        }
    }


    private void setView() {
        rv_shipping_address.setLayoutManager(new LinearLayoutManager(MyAddressesListActivity.this));
        rv_shipping_address.setItemAnimator(new DefaultItemAnimator());
        //ShippingAddressListAdapter shippingAddressListAdapter = new ShippingAddressListAdapter(MyAddressesListActivity.this,dataBeanList,this,this,this);
      //  rv_shipping_address.setAdapter(shippingAddressListAdapter);

    }

    private void gotoShippingaddressCreate() {
        Intent intent = new Intent(MyAddressesListActivity.this, AddNewShippingAddressActivity.class);
        startActivity(intent);

    }


    @Override
    public void OnEditShipAddr(int position){

      /*  Intent intent = new Intent(getApplicationContext(), MyAddressEditActivity.class);

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

        intent.putExtra("city",city);

        startActivity(intent);

        finish();*/

    }

    @Override
    public void onSelectShipID(String shipid) {
        shippid = shipid;

        if(shippid!=null&&!shippid.isEmpty()){
            showLocationStatusChangeAlert();
        }

    }

    @Override
    public void OnDeleteShipAddr(String shipid) {
        showWaring(shipid);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), PetLoverProfileScreenActivity.class));
        finish();
    }

    private void showLocationStatusChangeAlert() {

        try {

            dialog = new Dialog(MyAddressesListActivity.this);
            dialog.setContentView(R.layout.alert_approve_reject_layout);
            TextView tvheader = dialog.findViewById(R.id.tvInternetNotConnected);
            tvheader.setText(R.string.locationstatuschange);
            Button dialogButtonApprove = dialog.findViewById(R.id.btnApprove);
            dialogButtonApprove.setText("Yes");
            Button dialogButtonRejected = dialog.findViewById(R.id.btnReject);
            dialogButtonRejected.setText("No");

            dialogButtonApprove.setOnClickListener(view -> {
                dialog.dismiss();

                markAsLastUsedResponseCall(shippid);




            });
            dialogButtonRejected.setOnClickListener(view -> {
                // Toasty.info(context, "Rejected Successfully", Toast.LENGTH_SHORT, true).show();
                dialog.dismiss();




            });
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                callDirections("1");
                break;
            case R.id.shop:
                callDirections("2");
                break;
            case R.id.services:
                callDirections("3");
                break;
            case R.id.care:
                callDirections("4");
                break;
            case R.id.community:
                callDirections("5");
                break;

            default:
                return  false;
        }
        return true;
    }

    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();

    }

}