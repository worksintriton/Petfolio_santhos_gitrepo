package com.triton.bertsproject.retailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.ChildCategoriesListAdapter;
import com.triton.bertsproject.adapter.ShippingaddrListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.interfaces.DeleteAddressListener;
import com.triton.bertsproject.interfaces.EditAddressListener;
import com.triton.bertsproject.interfaces.SetDefaultAddressListener;
import com.triton.bertsproject.requestpojo.DeleteAddressListRequest;
import com.triton.bertsproject.requestpojo.SetDefaultAddrRequest;
import com.triton.bertsproject.requestpojo.UserAddressListRequest;
import com.triton.bertsproject.responsepojo.SetDefaultAddrResponse;
import com.triton.bertsproject.responsepojo.UserAddressListResponse;
import com.triton.bertsproject.responsepojo.UserAddressListResponse;
import com.triton.bertsproject.responsepojo.DeleteAddressListResponse;
import com.triton.bertsproject.sessionmanager.Connectivity;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.GridSpacingItemDecoration;
import com.triton.bertsproject.utils.RestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingAddressActivity extends AppCompatActivity implements SetDefaultAddressListener, DeleteAddressListener, EditAddressListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_addrlist)
    RecyclerView rv_addrlist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_addnewaddr)
    Button btn_addnewaddr;

    AlertDialog alertDialog;

    private static final String TAG = "ShippingAddressActivity";

//    private DD4YouNetReceiver dd4YouNetReceiver;

    private DD4YouConfig dd4YouConfig;

    List<UserAddressListResponse.DataBean.AddressBean> addressBeanList ;

    SessionManager sessionManager;

    String userid;

    String isdefault = "0",fromActivity;

    String brand_id,brand_name,parent_id,subcategid,subcategname,make_id,model_id,model_name;

    String prod_id,prod_name,shipid;

    String addr_name,address1,state_name,country_name;

    Connectivity connectivity;

    String value,categ_name,make_name,search_text,cart_count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address);

        ButterKnife.bind(this);

        Log.w("oncreate",TAG);

        dd4YouConfig = new DD4YouConfig(this);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getProfileDetails();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromActivity = extras.getString("fromactivity");

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

            Log.w(TAG,"Connectivity fromactivity : "+fromActivity+ "brand_id : "+brand_id + "brand_name : "+brand_name+"parent_id : "+parent_id+ "categ_name : "+categ_name+ "subcategid :" +subcategid

                    + "subcategname : "+subcategname + "make_id : "+make_id + "model_id :" +model_id

                    + "model_name : "+model_name + "search_text : "+search_text);

        }
        if(sessionManager.isLoggedIn()) {

            userid = user.get(SessionManager.KEY_ID);

            Log.w(TAG,"user_id "+userid);
        }

        txt_toolbar_title.setText(R.string.ship_addr);

        btn_addnewaddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ShippingAddressActivity.this,ShippingAddressAddActivity.class);

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

                startActivity(intent);

                finish();

            }
        });


        img_back.setOnClickListener(v -> {

            onBackPressed();

        });

        spin_kit_loadingView.setVisibility(View.GONE);

    }


    @Override
    public void onBackPressed() {

        if(fromActivity!=null){

            if(fromActivity.equals("ProfileFragment")){

                callDirections("5");
            }

            else if(fromActivity.equals("RetailerCartActivity")){

                gotoRetailerCartActivity();
            }
            else {

                Intent intent = new Intent(ShippingAddressActivity.this,RetailerDashboardActivity.class);
                intent.putExtra("fromActivity",fromActivity);
                startActivity(intent);
                finish();
            }
        }
        else {

            Intent intent = new Intent(ShippingAddressActivity.this,RetailerDashboardActivity.class);
            startActivity(intent);
            finish();
        }



    }

    private void gotoRetailerCartActivity() {

        Intent intent = new Intent(ShippingAddressActivity.this, RetailerCartActivity.class);

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

        startActivity(intent);

        finish();
    }

    public void callDirections(String tag){
        Intent intent = new Intent(ShippingAddressActivity.this,RetailerDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();

    }

    @SuppressLint("LongLogTag")
    private void fetchalladdressListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<UserAddressListResponse> call = apiInterface.useraddressListResponseCall(RestUtils.getContentType(),UserAddressListRequest());
        Log.w(TAG,"UserAddressListResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<UserAddressListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<UserAddressListResponse> call, @NonNull Response<UserAddressListResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"UserAddressListResponse" + new Gson().toJson(response.body()));

                        addressBeanList = response.body().getData().getAddress();

                        if(addressBeanList != null && addressBeanList.size()>0){

                            rv_addrlist.setVisibility(View.VISIBLE);

                            txt_no_records.setVisibility(View.GONE);

                            setView(addressBeanList);
                        }

                        else {

                            isdefault="1";

                            rv_addrlist.setVisibility(View.GONE);

                            txt_no_records.setVisibility(View.VISIBLE);

                            txt_no_records.setText(R.string.shipaddr_dzis_msg);
                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }



                }

            }


            @Override
            public void onFailure(@NonNull Call<UserAddressListResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"UserAddressListResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private UserAddressListRequest UserAddressListRequest() {


        /**
         * USER_ID : 541
         * MODE : LIST
         */

        UserAddressListRequest UserAddressListRequest = new UserAddressListRequest();
        UserAddressListRequest.setUSER_ID(userid);
        UserAddressListRequest.setMODE("LIST");

        Log.w(TAG,"UserAddressListRequest "+ new Gson().toJson(UserAddressListRequest));
        return UserAddressListRequest;
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(ShippingAddressActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(ShippingAddressActivity.this,ShippingAddressActivity.class));
            finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setView(List<UserAddressListResponse.DataBean.AddressBean> addressBeanList) {


        rv_addrlist.setLayoutManager(new LinearLayoutManager(ShippingAddressActivity.this));

        rv_addrlist.setMotionEventSplittingEnabled(false);

        rv_addrlist.setNestedScrollingEnabled(true);

        int size =addressBeanList.size();

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        rv_addrlist.setItemAnimator(new DefaultItemAnimator());

        ShippingaddrListAdapter shippingaddrListAdapter = new ShippingaddrListAdapter(ShippingAddressActivity.this, addressBeanList,this,this,this);

        rv_addrlist.setAdapter(shippingaddrListAdapter);


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //    private void registerBroadcastReceiver() {
//        if (dd4YouNetReceiver == null)
//            dd4YouNetReceiver = new DD4YouNetReceiver(rl_root,1000);
//        dd4YouNetReceiver.register(this.getApplicationContext());
//    }
//    private void unregisterBroadcastReceiver() {
//        if (dd4YouNetReceiver != null)        {
//            dd4YouNetReceiver.unregister(this.getApplicationContext());
//        }
//    }
    @Override
    protected void onPause() {
        super.onPause();
//        unregisterBroadcastReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dd4YouConfig.isInternetConnectivity()) {

            fetchalladdressListResponseCall();

        }else {
            callnointernet();

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // unregisterBroadcastReceiver();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ShippingAddressActivity.this);
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
    private void deleteaddrListResponseCall(String shipid) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<DeleteAddressListResponse> call = apiInterface.deleteaddressResponseCall(RestUtils.getContentType(), DeleteAddressListRequest(shipid));
        Log.w(TAG,"DeleteAddressListResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<DeleteAddressListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<DeleteAddressListResponse> call, @NonNull Response<DeleteAddressListResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "DeleteAddressListResponse" + new Gson().toJson(response.body()));

                        fetchalladdressListResponseCall();

                        }

                        else {


                        showErrorLoading(response.body().getMessage());
                        }


                }



            }


            @Override
            public void onFailure(@NonNull Call<DeleteAddressListResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"DeleteAddressListResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private DeleteAddressListRequest DeleteAddressListRequest(String shipid) {

        /*
         * ADDRESS_ID : 352
         * MODE : DELETE
         */



        DeleteAddressListRequest DeleteAddressListRequest = new DeleteAddressListRequest();
        DeleteAddressListRequest.setADDRESS_ID(shipid);
        DeleteAddressListRequest.setMODE("DELETE");

        Log.w(TAG,"DeleteAddressListRequest "+ new Gson().toJson(DeleteAddressListRequest));
        return DeleteAddressListRequest;
    }


    @Override
    public void setshipListener(String id) {

        showAlert(id);
    }


    private void showAlert(String id) {

        new SweetAlertDialog(ShippingAddressActivity.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Alert!!")
                .setContentText("Are You Sure to set this vehicle as Default vehicle")
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
                        if(dd4YouConfig.isInternetConnectivity()) {

                            setdafultaddrResponseCall(id);
                        }

                        else {

                            callnointernet();
                        }

                        sDialog.dismiss();

                    }
                })
                .show();

    }

    @SuppressLint("LongLogTag")
    private void setdafultaddrResponseCall(String id) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SetDefaultAddrResponse> call = apiInterface.setdefaultaddrResponseCall(RestUtils.getContentType(),SetDefaultAddrRequest(id));
        Log.w(TAG,"SetDefaultAddrResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SetDefaultAddrResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<SetDefaultAddrResponse> call, @NonNull Response<SetDefaultAddrResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "SetDefaultAddrResponse" + new Gson().toJson(response.body()));

                        Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                        gotoRetailerCartActivity();
                    }

                    else {

                        showErrorLoading(response.body().getMessage());
                    }
                }


            }




            @Override
            public void onFailure(@NonNull Call<SetDefaultAddrResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"SetDefaultAddrResponse flr"+t.getMessage());
            }
        });

    }
    @SuppressLint("LongLogTag")
    private SetDefaultAddrRequest SetDefaultAddrRequest(String av_id) {

        /**
         * ADDRESS_ID : 348
         * MODE : SETDEFAULT
         */

        SetDefaultAddrRequest SetDefaultAddrRequest = new SetDefaultAddrRequest();
        SetDefaultAddrRequest.setADDRESS_ID(av_id);
        SetDefaultAddrRequest.setMODE("SETDEFAULT");

        Log.w(TAG,"SETDEFAULT "+ new Gson().toJson(SetDefaultAddrRequest));
        return SetDefaultAddrRequest;
    }

    @Override
    public void setshipidListener(String shipid,String isdefault) {

        if(isdefault!=null&&!isdefault.isEmpty()){

            if(isdefault.equals("0")){

                DefaultDeleteWar(shipid);

            }
            else {

                showWarning();
            }
        }




    }

    private void DefaultDeleteWar(String shipingd) {

        new SweetAlertDialog(ShippingAddressActivity.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Quick Alert!!")
                .setContentText("Are you sure want to delete")
                .setConfirmText("Yes")
                .setCancelText("No")
                .showCancelButton(true)
                .setConfirmClickListener(sDialog -> {

                    if(dd4YouConfig.isInternetConnectivity()) {

                        deleteaddrListResponseCall(shipingd);
                    }

                    else {

                        callnointernet();
                    }

                    sDialog.dismiss();

                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {

                        sDialog.dismiss();

                    }
                })
                .show();
    }

    private void showWarning() {


        new SweetAlertDialog(ShippingAddressActivity.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Sorry!!")
                .setContentText("You Cannnot delete default address")
                .setConfirmText("OK")
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {

                        sDialog.dismiss();

                    }
                })
                .show();

    }


    @Override
    public void editshipinfoListener(String ADDRESS_ID, String NAME, String PHONE, String ADDRESS1, String ADDRESS2, String CITY, String COUNTRY_ID, String COUNTRY_NAME, String STATE_ID, String STATE_NAME, String ZP_CODE, String isdefault) {

        Intent intent = new Intent(ShippingAddressActivity.this,ShippingAddressEditActivity.class);

        intent.putExtra("ADDRESS_ID",ADDRESS_ID);

        intent.putExtra("NAME",NAME);

        intent.putExtra("PHONE",PHONE);

        intent.putExtra("ADDRESS1",ADDRESS1);

        intent.putExtra("ADDRESS2",ADDRESS2);

        intent.putExtra("CITY",CITY);

        intent.putExtra("COUNTRY_ID",COUNTRY_ID);

        intent.putExtra("COUNTRY_NAME",COUNTRY_NAME);

        intent.putExtra("STATE_ID",STATE_ID);

        intent.putExtra("STATE_NAME",STATE_NAME);

        intent.putExtra("ZIP_CODE",ZP_CODE);

        intent.putExtra("isdefault",isdefault);

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

        startActivity(intent);

        finish();

    }
}