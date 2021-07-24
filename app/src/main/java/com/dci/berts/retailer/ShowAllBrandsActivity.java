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
import com.dci.berts.adapter.BrandListAdapter;
import com.dci.berts.api.APIClient;
import com.dci.berts.api.RestApiInterface;
import com.dci.berts.responsepojo.FetchAllBrandsResponse;
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

public class ShowAllBrandsActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_top_brands)
    RecyclerView rv_top_brands;

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

    List<FetchAllBrandsResponse.DataBean.BrandBean> brandsBeanList ;

    AlertDialog alertDialog;

    private static final String TAG = "ShowAllBrandsActivity";

    String fromactivity;

    Connectivity connectivity;

    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_brands);

        Log.w("Oncreate ", TAG);

        ButterKnife.bind(this);

        dd4YouConfig = new DD4YouConfig(this);

        //registerBroadcastReceiver();

        connectivity = new Connectivity();

        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallbrandsListResponseCall();

        }

        else
        {
            callnointernet();

        }

        txt_toolbar_title.setText(R.string.brandss);

        spin_kit_loadingView.setVisibility(View.GONE);

        value = connectivity.getData(ShowAllBrandsActivity.this,"Brand");

        if(value!=null&&!value.isEmpty()){

            fromactivity = value;

            Log.w(TAG,"Connectivity "+ " fromactivity : "+ fromactivity);
        }

        else {
            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                fromactivity = extras.getString("fromactivity");

                Log.w(TAG,"Connectivity fromactivity : "+fromactivity );
            }
        }


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

                        fetchallbrandsListResponseCall();

                    }

                    else
                    {
                        callnointernet();

                    }

                }
        );



    }


    @SuppressLint("LongLogTag")
    private void fetchallbrandsListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchAllBrandsResponse> call = apiInterface.fetchallbrandsListResponseCall(RestUtils.getContentType());
        Log.w(TAG,"FetchAllBrandsResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<FetchAllBrandsResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchAllBrandsResponse> call, @NonNull Response<FetchAllBrandsResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"FetchAllBrandsResponse" + new Gson().toJson(response.body()));

                        brandsBeanList = response.body().getData().getBrand();

                        if(brandsBeanList != null && brandsBeanList.size()>0){

                            refresh_layout.setVisibility(View.VISIBLE);

                            rv_top_brands.setVisibility(View.VISIBLE);

                            txt_no_records.setVisibility(View.GONE);

                            setView(brandsBeanList);
                        }

                        else {

                            refresh_layout.setVisibility(View.GONE);

                            rv_top_brands.setVisibility(View.GONE);

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
            public void onFailure(@NonNull Call<FetchAllBrandsResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"FetchAllParentCategoriesResponse flr"+t.getMessage());
            }
        });



    }


    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ShowAllBrandsActivity.this);
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

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(ShowAllBrandsActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(ShowAllBrandsActivity.this,ShowAllBrandsActivity.class));
            finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setView(List<FetchAllBrandsResponse.DataBean.BrandBean> brandsBeanList) {

        rv_top_brands.setLayoutManager(new GridLayoutManager(ShowAllBrandsActivity.this, 2));

        rv_top_brands.setMotionEventSplittingEnabled(false);

        rv_top_brands.setNestedScrollingEnabled(true);

        int size =brandsBeanList.size();

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        rv_top_brands.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));

        rv_top_brands.setItemAnimator(new DefaultItemAnimator());

        BrandListAdapter brandListAdapter = new BrandListAdapter(ShowAllBrandsActivity.this, brandsBeanList,size,fromactivity);

        rv_top_brands.setAdapter(brandListAdapter);



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

            fetchallbrandsListResponseCall();

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

                Intent intent = new Intent(ShowAllBrandsActivity.this,RetailerDashboardActivity.class);

                intent.putExtra("fromactivity",TAG);

                startActivity(intent);
            }


        }

        else {

            Intent intent = new Intent(ShowAllBrandsActivity.this,RetailerDashboardActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);
        }
    }

    public void callDirections(String tag){
        Intent intent = new Intent(ShowAllBrandsActivity.this,RetailerDashboardActivity.class);
        intent.putExtra("fromactivity",TAG);
        intent.putExtra("tag",tag);
        connectivity.clearData(ShowAllBrandsActivity.this,"Brand");
        startActivity(intent);
        finish();

    }

}