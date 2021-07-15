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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.ChildMakesListAdapter;
import com.triton.bertsproject.adapter.ParentMakesListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.requestpojo.FetchChildMakeslistRequest;
import com.triton.bertsproject.responsepojo.FetchAllParentMakesResponse;
import com.triton.bertsproject.responsepojo.FetchChildMakeslistRequestResponse;
import com.triton.bertsproject.sessionmanager.Connectivity;
import com.triton.bertsproject.utils.GridSpacingItemDecoration;
import com.triton.bertsproject.utils.RestUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAllChildMakesActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_top_makes)
    RecyclerView rv_top_makes;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_root)
    RelativeLayout rl_root;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

//    private DD4YouNetReceiver dd4YouNetReceiver;

    private DD4YouConfig dd4YouConfig;

    List<FetchChildMakeslistRequestResponse.DataBean.ModelBean> makesBeanList ;

    private static final String TAG = "ShowAllChildMakesActivity";

    String make_id, make_name,fromactivity;

    AlertDialog alertDialog;

    Connectivity connectivity;

    String value;


    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_child_makes);

        ButterKnife.bind(this);

        Log.w("Oncreate ", TAG);

        dd4YouConfig = new DD4YouConfig(this);

        //registerBroadcastReceiver();

        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallmakesListResponseCall();

        }

        else
        {
            callnointernet();

        }

        connectivity = new Connectivity();

        value = connectivity.getData(ShowAllChildMakesActivity.this,"ChildMakes");

        if(value!=null&&!value.isEmpty()){

            Log.w(TAG, "condition --> true");

            fromactivity = value;

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                make_id = extras.getString("make_id");

                make_name = extras.getString("make_name");


            }

            Log.w(TAG,"Connectivity : "+ "fromactivity "+fromactivity+" make_id : "+make_id +"make_name : "+make_name);
        }

        else {

            Log.w(TAG, "condition --> false");

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                fromactivity = extras.getString("fromactivity");

                make_id = extras.getString("make_id");

                make_name = extras.getString("make_name");

                Log.w(TAG,"Connectivity : "+ "fromactivity "+fromactivity+" make_id : "+make_id +"make_name : "+make_name);
            }



        }


        if(make_name!=null&&!make_name.isEmpty()){

            txt_toolbar_title.setText(make_name);
        }

        else {

            txt_toolbar_title.setText(R.string.model);

        }


        spin_kit_loadingView.setVisibility(View.GONE);

        txt_no_records.setVisibility(View.GONE);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        refresh_layout.setOnRefreshListener(
                () -> {
                    refresh_layout.setEnabled(false);
                    if (dd4YouConfig.isInternetConnectivity()) {

                        fetchallmakesListResponseCall();

                    }

                    else
                    {
                        callnointernet();

                    }

                }
        );
    }


    @SuppressLint("LongLogTag")
    private void fetchallmakesListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchChildMakeslistRequestResponse> call = apiInterface.fetchallchildmakelistResponseCall(RestUtils.getContentType(),fetchChildMakeslistRequest());
        Log.w(TAG,"FetchChildMakeslistRequestResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<FetchChildMakeslistRequestResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchChildMakeslistRequestResponse> call, @NonNull Response<FetchChildMakeslistRequestResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"FetchChildMakeslistRequestResponse" + new Gson().toJson(response.body()));

                        makesBeanList = response.body().getData().getModel();

                        if(makesBeanList != null && makesBeanList.size()>0){

                            refresh_layout.setVisibility(View.VISIBLE);

                            rv_top_makes.setVisibility(View.VISIBLE);

                            txt_no_records.setVisibility(View.GONE);

                            setView(makesBeanList);
                        }

                        else {

                            refresh_layout.setVisibility(View.GONE);

                            rv_top_makes.setVisibility(View.GONE);

                            txt_no_records.setVisibility(View.VISIBLE);

                            txt_no_records.setText(R.string.model_dis_msg);
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
    private FetchChildMakeslistRequest fetchChildMakeslistRequest() {

        /*
         * MAKE_ID : 6
         * SORT_BY : DESC
         */
        FetchChildMakeslistRequest fetchChildMakeslistRequest = new FetchChildMakeslistRequest();
        fetchChildMakeslistRequest.setMAKE_ID(make_id);
        fetchChildMakeslistRequest.setSORT_BY("DESC");

        Log.w(TAG,"FetchChildMakeslistRequest "+ new Gson().toJson(fetchChildMakeslistRequest));
        return fetchChildMakeslistRequest;
    }



    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(ShowAllChildMakesActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(ShowAllChildMakesActivity.this, ShowAllChildMakesActivity.class));
            finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setView(List<FetchChildMakeslistRequestResponse.DataBean.ModelBean> makesBeanList) {

        rv_top_makes.setLayoutManager(new LinearLayoutManager(ShowAllChildMakesActivity.this));

        rv_top_makes.setMotionEventSplittingEnabled(false);

        rv_top_makes.setNestedScrollingEnabled(true);

        int size =makesBeanList.size();

        rv_top_makes.setItemAnimator(new DefaultItemAnimator());

        ChildMakesListAdapter childMakesListAdapter = new ChildMakesListAdapter(ShowAllChildMakesActivity.this, makesBeanList, size,fromactivity);

        rv_top_makes.setAdapter(childMakesListAdapter);



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
        //unregisterBroadcastReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallmakesListResponseCall();

        }else {
            callnointernet();

        }
        //registerBroadcastReceiver();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterBroadcastReceiver();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ShowAllChildMakesActivity.this);
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

    @Override
    public void onBackPressed() {

        if(fromactivity!=null&&!fromactivity.isEmpty()){

            if(fromactivity.equals("ShowAllParentMakesActivity")){


                Intent intent = new Intent(ShowAllChildMakesActivity.this,ShowAllParentMakesActivity.class);

                intent.putExtra("fromactivity",TAG);

                connectivity.clearData(ShowAllChildMakesActivity.this,"ChildMakes");

                startActivity(intent);

                finish();
            }

            else {

                Intent intent = new Intent(ShowAllChildMakesActivity.this,RetailerDashboardActivity.class);

                intent.putExtra("fromactivity",TAG);

                connectivity.clearData(ShowAllChildMakesActivity.this,"ChildMakes");

                startActivity(intent);

                finish();
            }


        }

        else {

            Intent intent = new Intent(ShowAllChildMakesActivity.this,RetailerDashboardActivity.class);

            intent.putExtra("fromactivity",TAG);

            connectivity.clearData(ShowAllChildMakesActivity.this,"ChildMakes");

            startActivity(intent);

            finish();
        }
    }

}