package com.triton.bertsproject.retailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.triton.bertsproject.adapter.ChildCategoriesListAdapter;
import com.triton.bertsproject.adapter.ParentCategoriesListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.requestpojo.FetchChildCateglistRequest;
import com.triton.bertsproject.requestpojo.LoginRequest;
import com.triton.bertsproject.responsepojo.FetchAllParentCategoriesResponse;
import com.triton.bertsproject.responsepojo.FetchChildCateglistResponse;
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

public class ShowAllChildCategActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_top_categories)
    RecyclerView rv_top_categories;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_root)
    RelativeLayout rl_root;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    AlertDialog alertDialog;

    private static final String TAG = "ShowAllChildCategActivity";

//    private DD4YouNetReceiver dd4YouNetReceiver;

    private DD4YouConfig dd4YouConfig;

    List<FetchChildCateglistResponse.DataBean.CategoriesBean> categoriesBeanList ;

    String parent_id, categ_name,fromactivity,previousactivity;

    Connectivity connectivity;

    String value;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_child_categ);

        Log.w("Oncreate ", TAG);

        ButterKnife.bind(this);

        dd4YouConfig = new DD4YouConfig(this);

        //registerBroadcastReceiver();

        connectivity = new Connectivity();

        value = connectivity.getData(ShowAllChildCategActivity.this,"ChildCategories");

        if(value!=null&&!value.isEmpty()){

            fromactivity = value;

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                parent_id = extras.getString("cate_id");

                categ_name = extras.getString("cate_name");

            }

            Log.w(TAG, "Connectivity " + " fromactivity " + fromactivity + " parent_id : " + parent_id + " categ_name : " + categ_name);

        }

        else {

            Log.w(TAG, "false");

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                fromactivity = extras.getString("fromactivity");

                parent_id = extras.getString("cate_id");

                categ_name = extras.getString("cate_name");

                Log.w(TAG,"Connectivity "+" fromactivity "+fromactivity+ " parent_id : " + parent_id + " categ_name : " + categ_name);
            }
        }




        if(categ_name!=null&&!categ_name.isEmpty()){

            txt_toolbar_title.setText(categ_name);
        }

        else {

            txt_toolbar_title.setText(R.string.sub_categories);
        }


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

                        fetchallchildcategoriesListResponseCall();

                    }

                    else
                    {
                        callnointernet();

                    }

                }
        );

        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallchildcategoriesListResponseCall();

        }

        else
        {
            callnointernet();

        }

    }

    @SuppressLint("LongLogTag")
    private void fetchallchildcategoriesListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchChildCateglistResponse> call = apiInterface.fetchallchildcateglistResponseCall(RestUtils.getContentType(),fetchChildCateglistRequest());
        Log.w(TAG,"FetchChildCateglistResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<FetchChildCateglistResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchChildCateglistResponse> call, @NonNull Response<FetchChildCateglistResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"FetchChildCateglistResponse" + new Gson().toJson(response.body()));

                        categoriesBeanList = response.body().getData().getCategories();

                        if(categoriesBeanList != null && categoriesBeanList.size()>0){

                            refresh_layout.setVisibility(View.VISIBLE);

                            rv_top_categories.setVisibility(View.VISIBLE);

                            txt_no_records.setVisibility(View.GONE);

                            setView(categoriesBeanList);
                        }

                        else {

                            refresh_layout.setVisibility(View.GONE);

                            rv_top_categories.setVisibility(View.GONE);

                            txt_no_records.setVisibility(View.VISIBLE);

                            txt_no_records.setText(R.string.cat_dis_msg);
                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }



                }

            }


            @Override
            public void onFailure(@NonNull Call<FetchChildCateglistResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"FetchChildCateglistResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private FetchChildCateglistRequest fetchChildCateglistRequest() {

        /*
         * PARENT_ID : 1
         */

        FetchChildCateglistRequest fetchChildCateglistRequest = new FetchChildCateglistRequest();
        fetchChildCateglistRequest.setPARENT_ID(parent_id);

        Log.w(TAG,"FetchChildCateglistRequest "+ new Gson().toJson(fetchChildCateglistRequest));
        return fetchChildCateglistRequest;
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(ShowAllChildCategActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(ShowAllChildCategActivity.this,ShowAllChildCategActivity.class));
            finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setView(List<FetchChildCateglistResponse.DataBean.CategoriesBean> categoriesBeanList) {


        rv_top_categories.setLayoutManager(new GridLayoutManager(ShowAllChildCategActivity.this, 2));

        rv_top_categories.setMotionEventSplittingEnabled(false);

        rv_top_categories.setNestedScrollingEnabled(true);

        int size =categoriesBeanList.size();

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        rv_top_categories.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));

        rv_top_categories.setItemAnimator(new DefaultItemAnimator());

        ChildCategoriesListAdapter childCategoriesListAdapter = new ChildCategoriesListAdapter(ShowAllChildCategActivity.this, categoriesBeanList,size,parent_id,categ_name,fromactivity);

        rv_top_categories.setAdapter(childCategoriesListAdapter);



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

            fetchallchildcategoriesListResponseCall();

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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ShowAllChildCategActivity.this);
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

            if(fromactivity.equals("ShowAllParentCategoriesActivity")){

                Intent intent = new Intent(ShowAllChildCategActivity.this,ShowAllParentCategoriesActivity.class);

                intent.putExtra("fromactivity",TAG);

                connectivity.clearData(ShowAllChildCategActivity.this,"ChildCategories");

                startActivity(intent);
            }

            else if(fromactivity.equals("ShopFragment")){

                callDirections("3");
            }

            else {

                Intent intent = new Intent(ShowAllChildCategActivity.this,RetailerDashboardActivity.class);

                intent.putExtra("fromactivity",TAG);

                connectivity.clearData(ShowAllChildCategActivity.this,"ChildCategories");

                startActivity(intent);

                finish();
            }


        }
        else {

            Intent intent = new Intent(ShowAllChildCategActivity.this,RetailerDashboardActivity.class);

            intent.putExtra("fromactivity",TAG);

            connectivity.clearData(ShowAllChildCategActivity.this,"ChildCategories");

            startActivity(intent);

            finish();
        }
    }

    public void callDirections(String tag){
        Intent intent = new Intent(ShowAllChildCategActivity.this,RetailerDashboardActivity.class);
        intent.putExtra("fromactivity",TAG);
        intent.putExtra("tag",tag);
        connectivity.clearData(ShowAllChildCategActivity.this,"ChildCategories");
        startActivity(intent);
        finish();

    }
}