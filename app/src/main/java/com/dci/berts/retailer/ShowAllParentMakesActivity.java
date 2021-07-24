package com.dci.berts.retailer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.dci.berts.R;
import com.dci.berts.adapter.ParentMakesListAdapter;
import com.dci.berts.api.APIClient;
import com.dci.berts.api.RestApiInterface;
import com.dci.berts.responsepojo.FetchAllParentMakesResponse;
import com.dci.berts.sessionmanager.Connectivity;
import com.dci.berts.utils.GridSpacingItemDecoration;
import com.dci.berts.utils.RestUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAllParentMakesActivity extends AppCompatActivity {

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

    List<FetchAllParentMakesResponse.DataBean.MakeBean> makesBeanList ;

    AlertDialog alertDialog;

    private static final String TAG = "ShowAllParentMakesActivity";

    String fromactivity;

    Connectivity connectivity;

    String value;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_makesz);

        ButterKnife.bind(this);

        Log.w("Oncreate ", TAG);

        dd4YouConfig = new DD4YouConfig(this);

        //registerBroadcastReceiver();

        connectivity = new Connectivity();

        value = connectivity.getData(ShowAllParentMakesActivity.this,"ParentMakes");

        if(value!=null&&!value.isEmpty()){

            Log.w(TAG, "condition --> true");

            fromactivity = value;

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                fromactivity = extras.getString("fromactivity");

                Log.w(TAG,"Connectivity fromactivity : "+fromactivity );
            }

            Log.w(TAG,"Connectivity "+ " fromactivity : "+ fromactivity);
        }

        else {

            Log.w(TAG, "condition --> false");

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                fromactivity = extras.getString("fromactivity");

                Log.w(TAG,"Connectivity fromactivity : "+fromactivity );
            }

        }


        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallmakesListResponseCall();

        }

        else
        {
            callnointernet();

        }


        txt_toolbar_title.setText(R.string.make);

        spin_kit_loadingView.setVisibility(View.GONE);

        spin_kit_loadingView.setVisibility(View.GONE);

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

                            refresh_layout.setVisibility(View.VISIBLE);

                            rv_top_makes.setVisibility(View.VISIBLE);

                            txt_no_records.setVisibility(View.GONE);

                            setView(makesBeanList);
                        }

                        else {

                            refresh_layout.setVisibility(View.GONE);

                            rv_top_makes.setVisibility(View.GONE);

                            txt_no_records.setVisibility(View.VISIBLE);

                            txt_no_records.setText(R.string.brnd_dis_msg);
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

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(ShowAllParentMakesActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(ShowAllParentMakesActivity.this, ShowAllParentMakesActivity.class));
            finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setView(List<FetchAllParentMakesResponse.DataBean.MakeBean> makesBeanList) {

        rv_top_makes.setLayoutManager(new GridLayoutManager(ShowAllParentMakesActivity.this, 2));

        rv_top_makes.setMotionEventSplittingEnabled(false);

        rv_top_makes.setNestedScrollingEnabled(true);

        int size =makesBeanList.size();

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        rv_top_makes.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));

        rv_top_makes.setItemAnimator(new DefaultItemAnimator());

        ParentMakesListAdapter parentMakesListAdapter = new ParentMakesListAdapter(ShowAllParentMakesActivity.this, makesBeanList, size,fromactivity);

        rv_top_makes.setAdapter(parentMakesListAdapter);



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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ShowAllParentMakesActivity.this);
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

            if(fromactivity.equals("ShopFragment")){

                callDirections("3");
            }

            else  if(fromactivity.equals("HomeFragment")){

                callDirections("1");
            }

            else {

                Intent intent = new Intent(ShowAllParentMakesActivity.this,RetailerDashboardActivity.class);

                intent.putExtra("fromactivity",TAG);

                connectivity.clearData(ShowAllParentMakesActivity.this,"ParentMakes");

                startActivity(intent);

                finish();
            }


        }

        else {

            Intent intent = new Intent(ShowAllParentMakesActivity.this,RetailerDashboardActivity.class);

            intent.putExtra("fromactivity",TAG);

            connectivity.clearData(ShowAllParentMakesActivity.this,"ParentMakes");

            startActivity(intent);

            finish();
        }
    }

    public void callDirections(String tag){
        Intent intent = new Intent(ShowAllParentMakesActivity.this,RetailerDashboardActivity.class);
        intent.putExtra("tag",tag);
        intent.putExtra("fromactivity",TAG);
        connectivity.clearData(ShowAllParentMakesActivity.this,"ParentMakes");
        startActivity(intent);
        finish();

    }

}