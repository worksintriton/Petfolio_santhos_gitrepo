package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.triton.bertsproject.activities.RegisterActivity;
import com.triton.bertsproject.adapter.RetailerProductListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.interfaces.AddProductListener;
import com.triton.bertsproject.interfaces.ProductListener;
import com.triton.bertsproject.interfaces.WishlistAddProductListener;
import com.triton.bertsproject.model.RetailerProductlistModel;
import com.triton.bertsproject.requestpojo.AddToCartRequest;
import com.triton.bertsproject.requestpojo.AddWishistRequest;
import com.triton.bertsproject.requestpojo.FetchProductBasedOnCatRequest;
import com.triton.bertsproject.requestpojo.HomepageDashboardRequest;
import com.triton.bertsproject.requestpojo.HomepageDashboardResponse;
import com.triton.bertsproject.responsepojo.AddToCartResponse;
import com.triton.bertsproject.responsepojo.ProductListResponse;
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

public class RetailerProductListBasedOnCategActivity extends AppCompatActivity implements WishlistAddProductListener, ProductListener, AddProductListener {

    Context context = RetailerProductListBasedOnCategActivity.this;

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


    private final static String TAG = "RetailerProductListBasedOnCategActivity";

    List<RetailerProductlistModel> retailerProductlistModels;

    List<ProductListResponse.DataBean.ProductsBean> prdouctsBeanList ;

    String brand_id,parent_id,subcategid,categ_name,subcategname,fromactivity;

//    private DD4YouNetReceiver dd4YouNetReceiver;

    private DD4YouConfig dd4YouConfig;

    AlertDialog alertDialog;

    String user_id;

//    private DD4YouNetReceiver dd4YouNetReceiver;

    SessionManager sessionManager;

    String searchString = "",sorting="",previousactivity;

    Connectivity connectivity;

    String value;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cart_count)
    TextView txt_cart_count;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rlcart)
    RelativeLayout rlcart;

    String cart_count ="0";


    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_product_list_categbased);

        ButterKnife.bind(this);

        Log.w("Oncreate", TAG);

        retailerProductlistModels = new ArrayList<>();

        connectivity = new Connectivity();

        value = connectivity.getData(RetailerProductListBasedOnCategActivity.this,"ProductListCategories");

        if(value!=null&&!value.isEmpty()) {

            fromactivity = value;

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                previousactivity = extras.getString("previousactivity");

                brand_id = extras.getString("brand_id");

                parent_id = extras.getString("parent_id");

                categ_name = extras.getString("categ_name");

                subcategid = extras.getString("subcategid");

                subcategname = extras.getString("subcategname");

            }

            Log.w(TAG,"Connectivity "+"parent_id : "+parent_id+ "categ_name : "+categ_name+ "subcategid :" +subcategid

                    + "subcategname : "+subcategname + "fromactivity :" +fromactivity + "previousactivity :" +previousactivity);

        }

        else {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                fromactivity = extras.getString("fromactivity");

                previousactivity = extras.getString("previousactivity");

                brand_id = extras.getString("brand_id");

                parent_id = extras.getString("parent_id");

                categ_name = extras.getString("categ_name");

                subcategid = extras.getString("subcategid");

                subcategname = extras.getString("subcategname");

                Log.w(TAG,"Connectivity "+"parent_id : "+parent_id+ "categ_name : "+categ_name+ " subcategid :" +subcategid

                        + "subcategname : "+subcategname + "fromactivity :" +fromactivity + "previousactivity :" +previousactivity);

            }

        }

        dd4YouConfig = new DD4YouConfig(this);

        //registerBroadcastReceiver();

        sessionManager=new SessionManager(this);

        rlcart.setOnClickListener(v -> {

            gotoCartActivity();

        });

        if(sessionManager.isLoggedIn()){

            HashMap<String, String> user = sessionManager.getProfileDetails();

            user_id = user.get(SessionManager.KEY_ID);

            Connectivity connectivity = new Connectivity();

            cart_count = connectivity.getData(context,"Cart_Count");

            Log.w(TAG,"cart_count "+cart_count);

            if(cart_count!=null&&!cart_count.equals("0")){

                txt_cart_count.setText(""+cart_count);
            }

            else {

                txt_cart_count.setVisibility(View.GONE);
            }
        }

        else {

            user_id  = "";

            txt_cart_count.setVisibility(View.GONE);
        }


        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallproductsListResponseCall(searchString);

        }

        else
        {
            callnointernet();

        }

        if(subcategname!=null&&!subcategname.isEmpty()){

            txt_toolbar_title.setText(subcategname);

        }

        img_back.setOnClickListener(v -> {

            onBackPressed();
        });

        rl_search.setVisibility(View.GONE);

        rl_sort.setVisibility(View.GONE);


    }


    private void gotoCartActivity() {

        Intent intent = new Intent(RetailerProductListBasedOnCategActivity.this, RetailerCartActivity.class);

        intent.putExtra("fromactivity",TAG);

        intent.putExtra("brand_id",brand_id);

        intent.putExtra("parent_id",parent_id);

        intent.putExtra("categ_name",categ_name);

        intent.putExtra("subcategid",subcategid);

        intent.putExtra("subcategname",subcategname);

        connectivity.storeData(RetailerProductListBasedOnCategActivity.this,"ProductListCategories",fromactivity);

        startActivity(intent);

        finish();

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

    @SuppressLint("LongLogTag")
    private void fetchallproductsListResponseCall(String searchString) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ProductListResponse> call = apiInterface.fetchallprodbasedonCatlistResponseCall(RestUtils.getContentType(),fetchProductBasedOnCatRequest(searchString));
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

                txt_no_records.setVisibility(View.VISIBLE);

                txt_no_records.setText(R.string.no_prod_found);
            }
        });



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

                }

            }
        });


    }

    @SuppressLint("LongLogTag")
    private FetchProductBasedOnCatRequest fetchProductBasedOnCatRequest(String searchString) {



        /**
         * CATEGORY_ID : 2
         * SUBCATEGORY_ID : 30
         * SEARCH_STRING : t
         * MODE : LIST
         * USER_ID : 541
         * SORTING :
         */

        FetchProductBasedOnCatRequest fetchProductBasedOnCatRequest = new FetchProductBasedOnCatRequest();
        fetchProductBasedOnCatRequest.setCATEGORY_ID(parent_id);
        fetchProductBasedOnCatRequest.setSUBCATEGORY_ID(subcategid);
        fetchProductBasedOnCatRequest.setMODE("LIST");
        fetchProductBasedOnCatRequest.setUSER_ID(user_id);
        fetchProductBasedOnCatRequest.setSEARCH_STRING(searchString);
        fetchProductBasedOnCatRequest.setSORTING(sorting);


        Log.w(TAG,"FetchProductBasedOnCatRequest "+ new Gson().toJson(fetchProductBasedOnCatRequest));
        return fetchProductBasedOnCatRequest;
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(RetailerProductListBasedOnCategActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(RetailerProductListBasedOnCategActivity.this, RetailerProductListBasedOnCategActivity.class));
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


        rv_prodlist.setLayoutManager(new GridLayoutManager(RetailerProductListBasedOnCategActivity.this, 2));

        rv_prodlist.setMotionEventSplittingEnabled(false);

        rv_prodlist.setNestedScrollingEnabled(true);

        int size =prdouctsBeanList.size();

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        rv_prodlist.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));

        rv_prodlist.setItemAnimator(new DefaultItemAnimator());

        RetailerProductListAdapter retailerProductListAdapter = new RetailerProductListAdapter(RetailerProductListBasedOnCategActivity.this, prdouctsBeanList, false,this,this,this);

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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RetailerProductListBasedOnCategActivity.this);
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

        rv_prodlist.setLayoutManager(new LinearLayoutManager(RetailerProductListBasedOnCategActivity.this,LinearLayoutManager.VERTICAL,false));

        rv_prodlist.setMotionEventSplittingEnabled(false);

        //int size =3;

        rv_prodlist.setItemAnimator(new DefaultItemAnimator());

        RetailerProductListAdapter retailerProductListAdapter = new RetailerProductListAdapter(RetailerProductListBasedOnCategActivity.this, prdouctsBeanList,true,this,this,this);

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

    private void showLoginAlert() {

        AlertDialog.Builder builder=new AlertDialog.Builder(RetailerProductListBasedOnCategActivity.this);
        builder.setTitle("Alert");
        builder.setMessage("Please Login add Products in wishlist");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", (dialogInterface, i) -> {

            gotoLogin();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void gotoLogin() {

        Intent intent = new Intent(RetailerProductListBasedOnCategActivity.this,LoginActivity.class);

        intent.putExtra("parent_id",parent_id);

        intent.putExtra("subcategid",subcategid);

        intent.putExtra("subcategname",subcategname);

        intent.putExtra("fromActivity",TAG);

        startActivity(intent);

        finish();

    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(RetailerProductListBasedOnCategActivity.this, ShowAllChildCategActivity.class);

        intent.putExtra("cate_id",parent_id);

        intent.putExtra("cate_name",categ_name);

        intent.putExtra("subcategid",subcategid);

        intent.putExtra("subcategname",subcategname);

        intent.putExtra("fromactivity",TAG);

        connectivity.clearData(RetailerProductListBasedOnCategActivity.this,"ProductListCategories");

        startActivity(intent);

        finish();

        Log.w(TAG,"parent_id : "+parent_id+ "subcategid :" +subcategid

                + "subcategname : "+subcategname +

                "fromactivity :" +fromactivity);


        Animatoo.animateSwipeLeft(RetailerProductListBasedOnCategActivity.this);

    }

    @Override
    public void productListener(String prod_id, String prod_name) {

        Intent intent = new Intent(RetailerProductListBasedOnCategActivity.this,ProductDetailDescriptionActivity.class);

        intent.putExtra("parent_id",parent_id);

        intent.putExtra("categ_name",categ_name);

        intent.putExtra("subcategid",subcategid);

        intent.putExtra("subcategname",subcategname);

        intent.putExtra("prod_id",prod_id);

        intent.putExtra("prod_name",prod_name);

        intent.putExtra("fromactivity",TAG);

        connectivity.storeData(context,"ProductListCategories",fromactivity);

        startActivity(intent);
    }

    @Override
    public void addproductListener(String prod_id, String quantity, String unit_price, Button btn_addcart) {

        if(sessionManager.isLoggedIn()){

            if(dd4YouConfig.isInternetConnectivity()){

                btn_addcart.setText("Added to Cart");

                addcartlistResponseCall(prod_id,quantity,unit_price);
            }

            else {

                callnointernet();
            }
        }

        else {

            showAlert();
        }

    }

    private void showAlert() {

        AlertDialog.Builder builder=new AlertDialog.Builder(RetailerProductListBasedOnCategActivity.this);
        builder.setTitle("Alert");
        builder.setMessage("Please Login to add Products");
        builder.setCancelable(false);
        builder.setPositiveButton("Login", (dialogInterface, i) -> {
            Intent intent = new Intent(RetailerProductListBasedOnCategActivity.this, LoginActivity.class);

            intent.putExtra("parent_id",parent_id);

            intent.putExtra("categ_name",categ_name);

            intent.putExtra("subcategid",subcategid);

            intent.putExtra("subcategname",subcategname);

            intent.putExtra("fromactivity",TAG);

            connectivity.storeData(context,"ProductListCategories",fromactivity);

            startActivity(intent);


        });
        builder.setNegativeButton("Sign In", (dialogInterface, i) -> {
            Intent intent = new Intent(RetailerProductListBasedOnCategActivity.this, RegisterActivity.class);

            intent.putExtra("parent_id",parent_id);

            intent.putExtra("categ_name",categ_name);

            intent.putExtra("subcategid",subcategid);

            intent.putExtra("subcategname",subcategname);

            intent.putExtra("fromactivity",TAG);

            connectivity.storeData(context,"ProductListCategories",fromactivity);

            startActivity(intent);

        });
        builder.setNeutralButton("Cancel", (dialogInterface, i) -> {


        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @SuppressLint("LongLogTag")
    private void addcartlistResponseCall(String prod_id, String quantity, String unit_price) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AddToCartResponse> call = apiInterface.addcartlistResponseCall(RestUtils.getContentType(),AddToCartRequest(prod_id,quantity,unit_price));
        Log.w(TAG,"AddToCartResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<AddToCartResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<AddToCartResponse> call, @NonNull Response<AddToCartResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "AddToCartResponse" + new Gson().toJson(response.body()));

                        Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                      // fetchallproductsListResponseCall(searchString);

                        usercommonResponseCall();

                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }

                }

            }


            @Override
            public void onFailure(@NonNull Call<AddToCartResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"AddToCartResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private AddToCartRequest AddToCartRequest(String prod_id, String quantity, String unit_price) {


        /*
         * USER_ID : 541
         * PRODUCT_ID : 2
         * QUANTITY : 1
         * UNIT_PRICE : 50000
         * MODE : ADDTOCART
         */

        AddToCartRequest AddToCartRequest = new AddToCartRequest();
        AddToCartRequest.setUNIT_PRICE(unit_price);
        AddToCartRequest.setQUANTITY(quantity);
        AddToCartRequest.setPRODUCT_ID(prod_id);
        AddToCartRequest.setUSER_ID(user_id);
        AddToCartRequest.setMODE("ADDTOCART");

        Log.w(TAG,"AddToCartRequest "+ new Gson().toJson(AddToCartRequest));
        return AddToCartRequest;
    }

    @SuppressLint("LongLogTag")
    private void usercommonResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<HomepageDashboardResponse> call = apiInterface.usercommonResponseCall(RestUtils.getContentType(),HomepageDashboardRequest());
        Log.w(TAG,"HomepageDashboardResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<HomepageDashboardResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<HomepageDashboardResponse> call, @NonNull Response<HomepageDashboardResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(response.body().getData()!=null){

                        if(200==response.body().getCode()) {

                            Log.w(TAG, "HomepageDashboardResponse" + new Gson().toJson(response.body()));

                            cart_count = String.valueOf(response.body().getData().getCart_count());

                            Log.w(TAG, "Cart_Count" + cart_count);

                            if (cart_count!=null&&!cart_count.equals("0"))  {

                                Connectivity connectivity = new Connectivity();

                                connectivity.storeData(context,"Cart_Count",String.valueOf(cart_count));

                                txt_cart_count.setText(""+cart_count);
                            }

                            else {

                                txt_cart_count.setVisibility(View.GONE);

                                Connectivity connectivity = new Connectivity();

                                connectivity.storeData(context,"Cart_Count","0");

                            }


                        }

                        else {

                            cart_count="0";
//                            showErrorLoading(response.body().getMessage());
                            txt_cart_count.setVisibility(View.GONE);

                            Connectivity connectivity = new Connectivity();

                            connectivity.storeData(context,"Cart_Count","0");
                        }
                    }

                    else {

                        cart_count="0";

                        txt_cart_count.setVisibility(View.GONE);

                        Connectivity connectivity = new Connectivity();

                        connectivity.storeData(context,"Cart_Count","0");
                    }

                }

            }


            @Override
            public void onFailure(@NonNull Call<HomepageDashboardResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"HomepageDashboardResponse flr"+t.getMessage());
            }
        });

    }


    @SuppressLint("LongLogTag")
    private HomepageDashboardRequest HomepageDashboardRequest() {

        /*
         * USER_ID : 541
         */


        HomepageDashboardRequest HomepageDashboardRequest = new HomepageDashboardRequest();
        HomepageDashboardRequest.setUSER_ID(user_id);

        Log.w(TAG,"HomepageDashboardRequest "+ new Gson().toJson(HomepageDashboardRequest));
        return HomepageDashboardRequest;
    }

}