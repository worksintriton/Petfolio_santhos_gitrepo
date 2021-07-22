package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.requestpojo.AddVehicleRequest;
import com.triton.bertsproject.requestpojo.FetchAllYearRequest;
import com.triton.bertsproject.requestpojo.FetchChildMakeslistRequest;
import com.triton.bertsproject.responsepojo.AddVehicleResponse;
import com.triton.bertsproject.responsepojo.FetchAllParentMakesResponse;
import com.triton.bertsproject.responsepojo.FetchAllYearResponse;
import com.triton.bertsproject.responsepojo.FetchChildMakeslistRequestResponse;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.RestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddVehicleActivity extends AppCompatActivity {

    private static final String TAG = "AddVehicleActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sp_year)
    Spinner sp_year;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sp_make)
    Spinner sp_make;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sp_model)
    Spinner sp_model;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_year)
    RelativeLayout rl_year;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_amke)
    RelativeLayout rl_make;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_model)
    RelativeLayout rl_model;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_addVeh)
    Button btn_addVeh;

    Dialog alertDialog;

    DD4YouConfig dd4YouConfig;

    SessionManager sessionManager;

    String userid,ywar="", maked="", modelid="",make_name,model_name;

    List<FetchAllParentMakesResponse.DataBean.MakeBean> makesBeanList ;

    List<FetchChildMakeslistRequestResponse.DataBean.ModelBean> modelBeanList ;

    List<FetchAllYearResponse.DataBean.YearBean> yearBeanList ;

    HashMap<String, String> hashMap_makeid = new HashMap<>();

    HashMap<String, String> hashMap_modelid = new HashMap<>();

    String fromactivty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        ButterKnife.bind(this);

        Log.w("oncreate",TAG);

        dd4YouConfig = new DD4YouConfig(this);

        sessionManager=new SessionManager(this);

        HashMap<String, String> user = sessionManager.getProfileDetails();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromactivty = extras.getString("fromactivity");
        }


            userid = user.get(SessionManager.KEY_ID);

        rl_year.setVisibility(View.GONE);

        rl_make.setVisibility(View.GONE);

        rl_model.setVisibility(View.GONE);

        btn_addVeh.setVisibility(View.GONE);

        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallyearListResponseCall();

        }

        else
        {
            callnointernet();

        }

        btn_addVeh.setOnClickListener(v -> {

            if (dd4YouConfig.isInternetConnectivity()) {

                checkValidation();

            }

            else
            {
                callnointernet();

            }


        });

        img_back.setOnClickListener(v -> {

            onBackPressed();

        });


        txt_toolbar_title.setText(R.string.add_vehicle);

    }

    @Override
    public void onBackPressed() {

        if(fromactivty!=null){

            if(fromactivty.equals("HomeFragment")){

                callDirections("1");
            }

            else {

                callDirections("2");
            }
        }

        else {

            callDirections("1");
        }
    }



    private void checkValidation() {

        boolean isvalid = true;

       if(ywar.equals("Select Year")){

            isvalid =false;

            Toast.makeText(AddVehicleActivity.this,"Please Select Year",Toast.LENGTH_SHORT).show();


       }

        else if(make_name.equals("Select Make")){

            isvalid =false;

            Toast.makeText(AddVehicleActivity.this,"Please Select Make",Toast.LENGTH_SHORT).show();
        }

        else if(model_name.equals("Select Model")){

            isvalid =false;

            Toast.makeText(AddVehicleActivity.this,"Please Select Model",Toast.LENGTH_SHORT).show();
        }


        if(isvalid){

            if (dd4YouConfig.isInternetConnectivity()) {

                registerResponseCall(ywar,maked,modelid);

            }

            else
            {
                callnointernet();

            }


        }
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(AddVehicleActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(AddVehicleActivity.this, AddVehicleActivity.class));
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    @SuppressLint("LongLogTag")
    private void registerResponseCall(String ywar, String maked, String modelid) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AddVehicleResponse> call = apiInterface.addvehiclelistResponseCall(RestUtils.getContentType(),addVehicleRequest(ywar, maked, modelid));
        Log.w(TAG,"AddVehicleResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<AddVehicleResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<AddVehicleResponse> call, @NonNull Response<AddVehicleResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "AddVehicleResponse" + new Gson().toJson(response.body()));

                        Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                        onBackPressed();

                       // startActivity(new Intent(AddVehicleActivity.this, RetailerDashboardActivity.class));

                    }

                    else {

                      showErrorLoading(response.body().getMessage());

                    }

                }

            }


            @Override
            public void onFailure(@NonNull Call<AddVehicleResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"AddVehicleResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private AddVehicleRequest addVehicleRequest(String ywar, String maked, String modelid) {


        /*
         * USER_ID : 541
         * MAKE_ID : 32
         * MODEL_ID : 788
         * YEAR : 201
         * MODE : ADD
         */



        AddVehicleRequest addVehicleRequest = new AddVehicleRequest();
        addVehicleRequest.setYEAR(Integer.parseInt(ywar));
        addVehicleRequest.setMAKE_ID(maked);
        addVehicleRequest.setMODEL_ID(modelid);
        addVehicleRequest.setUSER_ID(userid);
        addVehicleRequest.setMODE("ADD");

        Log.w(TAG,"AddVehicleRequest "+ new Gson().toJson(addVehicleRequest));
        return addVehicleRequest;
    }


    public void callDirections(String tag){
        Intent intent = new Intent(AddVehicleActivity.this,RetailerDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();

    }


    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddVehicleActivity.this);
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

    /* Get Year */

    @SuppressLint("LongLogTag")
    private void fetchallyearListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchAllYearResponse> call = apiInterface.fetchallyearListResponseCall(RestUtils.getContentType(),fetchAllYearRequest());
        Log.w(TAG,"FetchAllYearResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<FetchAllYearResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchAllYearResponse> call, @NonNull Response<FetchAllYearResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"FetchAllYearResponse" + new Gson().toJson(response.body()));

                        yearBeanList = response.body().getData().getYear();

                        rl_year.setVisibility(View.VISIBLE);

                        if(dd4YouConfig.isInternetConnectivity()){

                            fetchallmakesListResponseCall();
                        }

                        else {

                            callnointernet();
                        }

                        if(yearBeanList != null && yearBeanList.size()>0){


                            setViewYearList(yearBeanList);
                        }

                        else {


                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());
                    }



                }

            }


            @Override
            public void onFailure(@NonNull Call<FetchAllYearResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"FetchAllYearResponse flr"+t.getMessage());
            }
        });



    }



    @SuppressLint("LongLogTag")
    private FetchAllYearRequest fetchAllYearRequest() {


        /*
         * ATTRIBUTE : YEAR
         */

        FetchAllYearRequest fetchAllYearRequest = new FetchAllYearRequest();
        fetchAllYearRequest.setATTRIBUTE("YEAR");


        Log.w(TAG,"FetchAllYearRequest "+ new Gson().toJson(fetchAllYearRequest));
        return fetchAllYearRequest;
    }



    private void setViewYearList(List<FetchAllYearResponse.DataBean.YearBean> makesBeanList) {


        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Select Year");

        for(int i=0;i<makesBeanList.size();i++){

            arrayList.add(makesBeanList.get(i).getP_year());

        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(AddVehicleActivity.this, R.layout.spinner_item, arrayList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view

        sp_year.setAdapter(spinnerArrayAdapter);

        sp_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.hint_color));

                ywar= sp_year.getSelectedItem().toString();

                Log.w(TAG,"ywar "+ywar);



            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    /* Get Makes */

    @SuppressLint("LongLogTag")
    private void fetchallmakesListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchAllParentMakesResponse> call = apiInterface.fetchallmakesListResponseCall(RestUtils.getContentType());
        Log.w(TAG,"FetchAllParentMakesResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<FetchAllParentMakesResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchAllParentMakesResponse> call, @NonNull Response<FetchAllParentMakesResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"FetchAllParentMakesResponse" + new Gson().toJson(response.body()));

                        makesBeanList = response.body().getData().getMake();

                        if(makesBeanList != null && makesBeanList.size()>0){

                            rl_make.setVisibility(View.VISIBLE);

                            setViewMakesList(makesBeanList);
                        }

                        else {


                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());
                    }



                }

            }


            @Override
            public void onFailure(@NonNull Call<FetchAllParentMakesResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"FetchAllParentMakesResponse flr"+t.getMessage());
            }
        });



    }


    private void setViewMakesList(List<FetchAllParentMakesResponse.DataBean.MakeBean> makesBeanList) {


        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Select Make");

        for(int i=0;i<makesBeanList.size();i++){

            arrayList.add(makesBeanList.get(i).getName());

            hashMap_makeid.put(makesBeanList.get(i).getName(),makesBeanList.get(i).getId());

        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(AddVehicleActivity.this, R.layout.spinner_item, arrayList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view

        sp_make.setAdapter(spinnerArrayAdapter);


        sp_make.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.hint_color));

                make_name= sp_make.getSelectedItem().toString();

                maked =  hashMap_makeid.get(make_name) ;

                Log.w(TAG,"make_id "+maked + "make_name " +make_name);

                if (maked!=null) {

                    if(dd4YouConfig.isInternetConnectivity()){

                        fetchallmodelListResponseCall(maked);
                    }

                    else
                    {
                        callnointernet();

                    }

                }

                else {

                    rl_model.setVisibility(View.GONE);

                    btn_addVeh.setVisibility(View.GONE);
                }





            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }


    @SuppressLint("LongLogTag")
    private void fetchallmodelListResponseCall(String maked) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchChildMakeslistRequestResponse> call = apiInterface.fetchallchildmakelistResponseCall(RestUtils.getContentType(),fetchChildMakeslistRequest(maked));
        Log.w(TAG,"FetchChildMakeslistRequestResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<FetchChildMakeslistRequestResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchChildMakeslistRequestResponse> call, @NonNull Response<FetchChildMakeslistRequestResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"FetchChildMakeslistRequestResponse" + new Gson().toJson(response.body()));

                        modelBeanList = response.body().getData().getModel();

                        if(modelBeanList != null && modelBeanList.size()>0){

                            rl_model.setVisibility(View.VISIBLE);

                            btn_addVeh.setVisibility(View.VISIBLE);

                            setViewModel(modelBeanList);
                        }

                        else {


                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }



                }

            }


            @Override
            public void onFailure(@NonNull Call<FetchChildMakeslistRequestResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"FetchChildMakeslistRequestResponse flr"+t.getMessage());
            }
        });



    }

    @SuppressLint("LongLogTag")
    private FetchChildMakeslistRequest fetchChildMakeslistRequest(String maked) {

        /*
         * MAKE_ID : 6
         * SORT_BY : DESC
         */
        FetchChildMakeslistRequest fetchChildMakeslistRequest = new FetchChildMakeslistRequest();
        fetchChildMakeslistRequest.setMAKE_ID(maked);
        fetchChildMakeslistRequest.setSORT_BY("DESC");

        Log.w(TAG,"FetchChildMakeslistRequest "+ new Gson().toJson(fetchChildMakeslistRequest));
        return fetchChildMakeslistRequest;
    }

    private void setViewModel(List<FetchChildMakeslistRequestResponse.DataBean.ModelBean> makesBeanList) {


        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Select Model");

        for(int i=0;i<makesBeanList.size();i++){

            arrayList.add(makesBeanList.get(i).getName());

            hashMap_modelid.put(makesBeanList.get(i).getName(),makesBeanList.get(i).getId());

        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(AddVehicleActivity.this, R.layout.spinner_item, arrayList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view

        sp_model.setAdapter(spinnerArrayAdapter);


        sp_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.hint_color));

                model_name = sp_model.getSelectedItem().toString();

                modelid =  hashMap_modelid.get(model_name) ;

                Log.w(TAG,"modelid "+modelid + "model_name "+model_name);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }
}