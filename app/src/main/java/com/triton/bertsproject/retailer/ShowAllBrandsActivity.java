package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.BrandListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.responsepojo.FetchAllBrandsResponse;
import com.triton.bertsproject.utils.GridSpacingItemDecoration;
import com.triton.bertsproject.utils.RestUtils;

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

    List<FetchAllBrandsResponse.DataBean.BrandsBean> brandsBeanList ;

    private static final String TAG = "ShowAllBrandsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_brands);

        Log.w("Oncreate ", TAG);

        ButterKnife.bind(this);

        dd4YouConfig = new DD4YouConfig(this);

        //registerBroadcastReceiver();

        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallbrandsListResponseCall();

        }

        else
        {
            callnointernet();

        }

        txt_toolbar_title.setText(R.string.brandss);

        spin_kit_loadingView.setVisibility(View.GONE);

        img_back.setOnClickListener(v -> startActivity(new Intent(ShowAllBrandsActivity.this,RetailerDashboardActivity.class)));

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

                    if(response.body().isStatus()){

                        Log.w(TAG,"FetchAllBrandsResponse" + new Gson().toJson(response.body()));

                        brandsBeanList = response.body().getData().getBrands();

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

                        Toast.makeText(getApplicationContext(),""+response.body().getError_message(),Toast.LENGTH_SHORT).show();

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

    private void setView(List<FetchAllBrandsResponse.DataBean.BrandsBean> brandsBeanList) {

        rv_top_brands.setLayoutManager(new GridLayoutManager(ShowAllBrandsActivity.this, 2));

        rv_top_brands.setMotionEventSplittingEnabled(false);

        rv_top_brands.setNestedScrollingEnabled(true);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        rv_top_brands.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));

        rv_top_brands.setItemAnimator(new DefaultItemAnimator());

        BrandListAdapter brandListAdapter = new BrandListAdapter(ShowAllBrandsActivity.this, brandsBeanList);

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



}