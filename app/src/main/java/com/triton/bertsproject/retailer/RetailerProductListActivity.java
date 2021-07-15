package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.LoginActivity;
import com.triton.bertsproject.adapter.RetailerProductListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.interfaces.ProductListener;
import com.triton.bertsproject.interfaces.WishlistAddProductListener;
import com.triton.bertsproject.model.RetailerProductlistModel;
import com.triton.bertsproject.requestpojo.AddWishistRequest;
import com.triton.bertsproject.requestpojo.FetchProductBasedOnBrandRequest;
import com.triton.bertsproject.requestpojo.SearchProductsRequest;
import com.triton.bertsproject.responsepojo.ProductListResponse;
import com.triton.bertsproject.responsepojo.SearchProductsResponse;
import com.triton.bertsproject.responsepojo.WishlistSuccessResponse;
import com.triton.bertsproject.sessionmanager.Connectivity;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.GridSpacingItemDecoration;
import com.triton.bertsproject.utils.RestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetailerProductListActivity extends AppCompatActivity implements WishlistAddProductListener, ProductListener {

    Context context = RetailerProductListActivity.this;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rlList)
    LinearLayout rlList;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rlGrid)
    LinearLayout rlGrid;

//    @SuppressLint({"NonConstantResourceId", "UseSwitchCompatOrMaterialCode"})
//    @BindView(R.id.switch1)
//    Switch Switch;
//
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_search)
    EditText edt_search;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_productlist)
    RecyclerView rv_prodlist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_search)
    RelativeLayout rl_search;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_sort)
    LinearLayout rl_sort;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_spinner)
    LinearLayout rl_spinner;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_spinnertext)
    TextView txt_spinnertext;

    String searchString = "",sorting="";

    private final static String TAG = "RetailerProductListActivity";

    String fromactivity;

    List<RetailerProductlistModel> retailerProductlistModels;

    List<ProductListResponse.DataBean.ProductsBean> prdouctsBeanList ;

    List<SearchProductsResponse.DataBean.ProductsBean> searchprdouctsBeanList ;

    String brand_id,brand_name;

//    private DD4YouNetReceiver dd4YouNetReceiver;

    private DD4YouConfig dd4YouConfig;

    AlertDialog alertDialog;

    String user_id;

//    private DD4YouNetReceiver dd4YouNetReceiver;

    SessionManager sessionManager;

    Connectivity connectivity;

    String value;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_product_list);

        ButterKnife.bind(this);

        Log.w("Oncreate", TAG);

        connectivity = new Connectivity();

        retailerProductlistModels = new ArrayList<>();

        value = connectivity.getData(RetailerProductListActivity.this,"BrandProduct");

        if(value!=null&&!value.isEmpty()){

            fromactivity = value;

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                brand_id = extras.getString("brand_id");

                brand_name = extras.getString("brand_name");

            }
            Log.w(TAG,"Connectivity fromactivity : "+fromactivity + "brand_id : "+brand_id + "brand_name : "+brand_name);

        }

        else {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                fromactivity = extras.getString("fromactivity");

                brand_id = extras.getString("brand_id");

                brand_name = extras.getString("brand_name");

                Log.w(TAG,"Connectivity fromactivity : "+fromactivity + "brand_id : "+brand_id + "brand_name : "+brand_name);
            }

        }

        if(brand_name!=null&&!brand_name.isEmpty()){

            txt_toolbar_title.setText(brand_name);

        }

        dd4YouConfig = new DD4YouConfig(this);

        //registerBroadcastReceiver();

        sessionManager=new SessionManager(this);

        if(sessionManager.isLoggedIn()){

            HashMap<String, String> user = sessionManager.getProfileDetails();

            user_id = user.get(SessionManager.KEY_ID);
        }

        else {

            user_id  = "";

        }

        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallproductsListResponseCall(searchString);

        }

        else
        {
            callnointernet();

        }

        rl_search.setVisibility(View.GONE);

        rl_sort.setVisibility(View.GONE);

        img_back.setOnClickListener(v -> {

            onBackPressed();
        });



    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RetailerProductListActivity.this, ShowAllBrandsActivity.class);

        intent.putExtra("brand_id",brand_id);

        intent.putExtra("brand_name",brand_name);

        intent.putExtra("fromactivity",TAG);

        connectivity.clearData(RetailerProductListActivity.this,"BrandProduct");

        context.startActivity(intent);

        Animatoo.animateSwipeLeft(context);

        finish();
    }

    @SuppressLint("LongLogTag")
    private void fetchallproductsListResponseCall(String searchString) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ProductListResponse> call = apiInterface.fetchallprodbasedonbrandlistResponseCall(RestUtils.getContentType(),fetchProductBasedOnBrandRequest(searchString));
        Log.w(TAG,"ProductListResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<ProductListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<ProductListResponse> call, @NonNull Response<ProductListResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"ProductListResponse" + new Gson().toJson(response.body()));

                        prdouctsBeanList = response.body().getData().getProducts();

                        if(prdouctsBeanList != null && prdouctsBeanList.size()>0){


                            setGridView(prdouctsBeanList);
                        }

                        else {


                            rv_prodlist.setVisibility(View.GONE);

                            txt_no_records.setVisibility(View.VISIBLE);

                            txt_no_records.setText(R.string.no_prod_found);
                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }



                }

            }


            @Override
            public void onFailure(@NonNull Call<ProductListResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"ProductListResponse flr"+t.getMessage());

                txt_no_records.setText(R.string.no_prod_found);
            }
        });



    }

    private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);

        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);

        bottomSheetDialog.setCancelable(false);

        RadioGroup radioGroup = bottomSheetDialog.findViewById(R.id.radioGroup);


        RadioButton radioButtonhot = bottomSheetDialog.findViewById(R.id.radioButtonhot);

        RadioButton radioButtonlowtohigh = bottomSheetDialog.findViewById(R.id.radioButtonlowtohigh);

        RadioButton radioButtonhightolow = bottomSheetDialog.findViewById(R.id.radioButtonhightolow);

       RadioButton radioButtontopreleated = bottomSheetDialog.findViewById(R.id.radioButtonnTopRat);

        RadioButton radioButtonnewarr = bottomSheetDialog.findViewById(R.id.radioButtonnewarr);

        if(sorting.equals("PRICELOWTOHIGH")){

            radioButtonlowtohigh.setChecked(true);

        }

        else  if(sorting.equals("PRICEHIGHTOLOW")) {

            radioButtonhightolow.setChecked(true);
        }


        else  if(sorting.equals("Hot")){

            radioButtonhot.setChecked(true);

        }


        else  if(sorting.equals("NEW")){

            radioButtonnewarr.setChecked(true);

        }

        else  if(sorting.equals("TOPRATING")){

            radioButtontopreleated.setChecked(true);

        }

        assert radioGroup != null;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                RadioButton rb=(RadioButton)bottomSheetDialog.findViewById(checkedId);
                assert rb != null;
                rb.setChecked(true);
                txt_spinnertext.setText(""+rb.getText());

                if(rb.getText().toString().equals("Price Low to High")){

                    radioButtonlowtohigh.setChecked(true);

                    sorting = "PRICELOWTOHIGH";
                }

                else  if(rb.getText().toString().equals("Price High to low")){

                    radioButtonhightolow.setChecked(true);

                    sorting="PRICEHIGHTOLOW";
                }
                else  if(rb.getText().toString().equals("Hot")){

                    radioButtonhot.setChecked(true);

                    sorting="HOT";
                }
                else  if(rb.getText().toString().equals("New Arrival")){

                    radioButtonnewarr.setChecked(true);

                    sorting="NEW";
                }

                else  if(rb.getText().toString().equals("Top Rating")){

                    radioButtontopreleated.setChecked(true);

                    sorting="TOPRATING";
                }

                if(dd4YouConfig.isInternetConnectivity()){

                    fetchallproductsListResponseCall(searchString);
                }
                else {

                    callnointernet();
                }
                //Toast.makeText(getApplicationContext(), rb.getText(), Toast.LENGTH_SHORT).show();\
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.show();
    }

    private void searchText() {

        edt_search.addTextChangedListener(new TextWatcher() {
            @SuppressLint({"LogNotTimber", "LongLogTag"})
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.w(TAG,"beforeTextChanged-->"+s.toString());
            }

            @SuppressLint({"LogNotTimber", "LongLogTag"})
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.w(TAG,"onTextChanged-->"+s.toString());
                searchString = s.toString();


            }

            @SuppressLint({"LogNotTimber", "LongLogTag"})
            @Override
            public void afterTextChanged(Editable s) {
                Log.w(TAG,"afterTextChanged-->"+s.toString());
                searchString = s.toString();
                if(!searchString.isEmpty()){

                    if(dd4YouConfig.isInternetConnectivity()){

                        fetchallproductsListResponseCall(searchString);
                    }

                    else {

                        callnointernet();
                    }


                }else{
                    searchString ="";
                    if(dd4YouConfig.isInternetConnectivity()){

                        fetchallproductsListResponseCall(searchString);
                    }

                    else {

                        callnointernet();
                    }

                }

            }
        });


    }

    @SuppressLint("LongLogTag")
    private FetchProductBasedOnBrandRequest fetchProductBasedOnBrandRequest(String searchString) {

        /*
         * BRAND_ID : 2
         * MODE : LIST
         * USER_ID :
         */

        FetchProductBasedOnBrandRequest fetchProductBasedOnBrandRequest = new FetchProductBasedOnBrandRequest();
        fetchProductBasedOnBrandRequest.setBRAND_ID(brand_id);
        fetchProductBasedOnBrandRequest.setSEARCH_STRING(searchString);
        fetchProductBasedOnBrandRequest.setSORTING(sorting);
        fetchProductBasedOnBrandRequest.setMODE("LIST");
        fetchProductBasedOnBrandRequest.setUSER_ID(user_id);

        Log.w(TAG,"FetchProductBasedOnBrandRequest "+ new Gson().toJson(fetchProductBasedOnBrandRequest));
        return fetchProductBasedOnBrandRequest;
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(RetailerProductListActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(RetailerProductListActivity.this,RetailerProductListActivity.class));
            finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setGridView(List<ProductListResponse.DataBean.ProductsBean> prdouctsBeanList) {


        rv_prodlist.setVisibility(View.VISIBLE);

        txt_no_records.setVisibility(View.GONE);

        rl_search.setVisibility(View.VISIBLE);

        searchText();

        rl_sort.setVisibility(View.VISIBLE);

        rl_spinner.setVisibility(View.VISIBLE);

        txt_spinnertext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showBottomSheetDialog();
            }
        });

        rlList.setOnClickListener(v -> {

            rlList.setBackgroundResource(R.drawable.bg_cycler_blue);

            rlGrid.setBackgroundResource(R.color.transparent);

            setlistView(prdouctsBeanList);
        });


        rlGrid.setOnClickListener(v -> {

            rlGrid.setBackgroundResource(R.drawable.bg_cycler_blue);

            rlList.setBackgroundResource(R.color.transparent);

            setGridView(prdouctsBeanList);
        });

        rv_prodlist.setLayoutManager(new GridLayoutManager(RetailerProductListActivity.this, 2));

        rv_prodlist.setMotionEventSplittingEnabled(false);

        rv_prodlist.setNestedScrollingEnabled(true);

        int size =prdouctsBeanList.size();

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        rv_prodlist.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));

        rv_prodlist.setItemAnimator(new DefaultItemAnimator());

        RetailerProductListAdapter retailerProductListAdapter = new RetailerProductListAdapter(RetailerProductListActivity.this, prdouctsBeanList, false,this,this);

        rv_prodlist.setAdapter(retailerProductListAdapter);



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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerProductListActivity.this);
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

    private void setlistView(List<ProductListResponse.DataBean.ProductsBean> prdouctsBeanList) {

        rv_prodlist.setLayoutManager(new LinearLayoutManager(RetailerProductListActivity.this,LinearLayoutManager.VERTICAL,false));

        rv_prodlist.setMotionEventSplittingEnabled(false);

        //int size =3;

        rv_prodlist.setItemAnimator(new DefaultItemAnimator());

        RetailerProductListAdapter retailerProductListAdapter = new RetailerProductListAdapter(RetailerProductListActivity.this, prdouctsBeanList,true,this,this);

        rv_prodlist.setAdapter(retailerProductListAdapter);


    }


    @SuppressLint("LongLogTag")
    private void wishlistaddResponseCall(String productId) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<WishlistSuccessResponse> call = apiInterface.wishlistaddResponseCall(RestUtils.getContentType(),addWishistRequest(productId));
        Log.w(TAG,"WishlistSuccessResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<WishlistSuccessResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<WishlistSuccessResponse> call, @NonNull Response<WishlistSuccessResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(response.body().getStatus().equals("Success")) {

                        Log.w(TAG, "WishlistSuccessResponse" + new Gson().toJson(response.body()));

                        Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                        fetchallproductsListResponseCall(searchString);

                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }

                }



            }


            @Override
            public void onFailure(@NonNull Call<WishlistSuccessResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"WishlistSuccessResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private AddWishistRequest addWishistRequest(String productID) {

        /*
         * USER_ID : 541
         * PRODUCT_ID : 4
         * MODE : ADD_DELETE
         */

        AddWishistRequest addWishistRequest = new AddWishistRequest();
        addWishistRequest.setPRODUCT_ID(productID);
        addWishistRequest.setUSER_ID(user_id);
        addWishistRequest.setMODE("ADD_DELETE");

        Log.w(TAG,"AddWishistRequest "+ new Gson().toJson(addWishistRequest));
        return addWishistRequest;
    }


    @Override
    public void addproductListener(String id) {


        if(sessionManager.isLoggedIn()){

            if (dd4YouConfig.isInternetConnectivity()) {

                wishlistaddResponseCall(id);

            }

            else
            {
                callnointernet();

            }

        }

        else {

            showAlert();
        }


    }

    private void showAlert() {

        AlertDialog.Builder builder=new AlertDialog.Builder(RetailerProductListActivity.this);
        builder.setTitle("Alert");
        builder.setMessage("Please Login to add Products in wishlist");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", (dialogInterface, i) -> {

            gotoLogin();

        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void gotoLogin() {

        Intent intent = new Intent(RetailerProductListActivity.this,LoginActivity.class);

        intent.putExtra("brand_id",brand_id);

        intent.putExtra("brand_name",brand_name);

        intent.putExtra("fromActivity",TAG);

        startActivity(intent);

        finish();

    }

    @Override
    public void productListener(String prod_id, String prod_name) {

        Intent intent = new Intent(RetailerProductListActivity.this,ProductDetailDescriptionActivity.class);

        intent.putExtra("brand_id",brand_id);

        intent.putExtra("brand_name",brand_name);

        intent.putExtra("prod_id",prod_id);

        intent.putExtra("prod_name",prod_name);

        intent.putExtra("fromactivity",TAG);

        connectivity.storeData(context,"BrandProduct",fromactivity);

        startActivity(intent);

    }
}