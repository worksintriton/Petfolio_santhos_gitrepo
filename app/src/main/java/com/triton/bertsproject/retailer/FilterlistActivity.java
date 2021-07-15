package com.triton.bertsproject.retailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.SearchBrandFilterlistAdapter;
import com.triton.bertsproject.adapter.SearchCategFilterlistAdapter;
import com.triton.bertsproject.adapter.SearchColorFilterlistAdapter;
import com.triton.bertsproject.adapter.SearchMakeFilterlistAdapter;
import com.triton.bertsproject.adapter.SearchModelFilterlistAdapter;
import com.triton.bertsproject.adapter.SearchYearFilterlistAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.interfaces.GetBrandIDListener;
import com.triton.bertsproject.interfaces.GetCategIDListener;
import com.triton.bertsproject.interfaces.GetColorIDListener;
import com.triton.bertsproject.interfaces.GetMakeIDListener;
import com.triton.bertsproject.interfaces.GetModelIDListener;
import com.triton.bertsproject.interfaces.GetYearNameListener;
import com.triton.bertsproject.requestpojo.FetchAllColorsRequest;
import com.triton.bertsproject.requestpojo.FetchAllYearRequest;
import com.triton.bertsproject.requestpojo.FetchChildMakeslistRequest;
import com.triton.bertsproject.responsepojo.FetchAllBrandsResponse;
import com.triton.bertsproject.responsepojo.FetchAllColorsResponse;
import com.triton.bertsproject.responsepojo.FetchAllParentCategoriesResponse;
import com.triton.bertsproject.responsepojo.FetchAllParentMakesResponse;
import com.triton.bertsproject.responsepojo.FetchAllYearResponse;
import com.triton.bertsproject.responsepojo.FetchChildMakeslistRequestResponse;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.GridSpacingItemDecoration;
import com.triton.bertsproject.utils.RestUtils;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterlistActivity extends AppCompatActivity implements GetYearNameListener, GetMakeIDListener, GetBrandIDListener, GetCategIDListener, GetColorIDListener, GetModelIDListener, View.OnClickListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_yr)
    ExpandableLayout expandableLayout_yr;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_make)
    ExpandableLayout expandable_layout_make;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_model)
    ExpandableLayout expandable_layout_model;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_brand)
    ExpandableLayout expandable_layout_brand;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_categ)
    ExpandableLayout expandable_layout_categ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_price_range)
    ExpandableLayout expandable_layout_price_range;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_rating)
    ExpandableLayout expandable_layout_rating;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_color)
    ExpandableLayout expandable_layout_color;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_year)
    LinearLayout ll_year;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_makes)
    LinearLayout ll_makes;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_models)
    LinearLayout ll_models;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_brand)
    LinearLayout ll_brand;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_categ)
    LinearLayout ll_categ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_price_range)
    LinearLayout ll_price_range;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_rating)
    LinearLayout ll_rating;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_color)
    LinearLayout ll_color;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_year)
    ImageView img_arrow_year;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_make)
    ImageView img_arrow_make;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_model)
    ImageView img_arrow_model;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_brand)
    ImageView img_arrow_brand;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_categ)
    ImageView img_arrow_categ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_pr_range)
    ImageView img_arrow_pr_range;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_rate)
    ImageView img_arrow_rate;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_color)
    ImageView img_arrow_color;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_year)
    RecyclerView rv_year;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_makes)
    RecyclerView rv_makes;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_models)
    RecyclerView rv_models;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_brand)
    RecyclerView rv_brand;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_categ)
    RecyclerView rv_categ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_color)
    RecyclerView rv_color;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_model_selected_value)
    TextView txt_model_selected_value;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_reset)
    Button btn_reset;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_apply)
    Button btn_apply;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view4)
    View view4;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view6)
    View view6;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view16)
    View view16;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view7)
    View view7;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view8)
    View view8;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view9)
    View view9;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view10)
    View view10;

    List<FetchAllBrandsResponse.DataBean.BrandBean> brandsBeanList;

    private static final String TAG = "FilterlistActivity";

    AlertDialog alertDialog;

    DD4YouConfig dd4YouConfig;

    SessionManager sessionManager;

    String userid, ywar = "", maked = "", modelid = "", make_name, model_name;

    List<FetchAllParentMakesResponse.DataBean.MakeBean> makesBeanList;

    List<FetchChildMakeslistRequestResponse.DataBean.ModelBean> modelBeanList;

    List<FetchAllYearResponse.DataBean.YearBean> yearBeanList;

    List<FetchAllColorsResponse.DataBean.ColorsBean> colorsBeanList;

    List<FetchAllParentCategoriesResponse.DataBean.CategoriesBean> categoriesBeanList;

    ArrayList<String> year = new ArrayList();

    ArrayList<String> make = new ArrayList();

    ArrayList<String> model = new ArrayList();

    ArrayList<String> brand = new ArrayList();

    ArrayList<String> category = new ArrayList();

    ArrayList<String> colors = new ArrayList();

    SearchBrandFilterlistAdapter searchBrandFilterlistAdapter;

    String search_text,fromactivity,makesid,modelsid,categid,brandid,color,min_pri,max_pri,rating;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filterlist);

        ButterKnife.bind(this);

        dd4YouConfig = new DD4YouConfig(this);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromactivity = extras.getString("fromactivity");

            search_text = extras.getString("search_text");

        }

        ll_year.setOnClickListener(this);

        ll_makes.setOnClickListener(this);

        ll_models.setOnClickListener(this);

        ll_brand.setOnClickListener(this);

        ll_categ.setOnClickListener(this);

        ll_price_range.setOnClickListener(this);

        ll_rating.setOnClickListener(this);

        ll_color.setOnClickListener(this);





        ll_year.setVisibility(View.GONE);

        ll_makes.setVisibility(View.GONE);

        ll_models.setVisibility(View.GONE);

        ll_brand.setVisibility(View.GONE);

        ll_categ.setVisibility(View.GONE);

        ll_price_range.setVisibility(View.GONE);

        ll_rating.setVisibility(View.GONE);

        ll_color.setVisibility(View.GONE);

        view4.setVisibility(View.GONE);

        view6.setVisibility(View.GONE);

        view16.setVisibility(View.GONE);

        view7.setVisibility(View.GONE);

        view8.setVisibility(View.GONE);

        view9.setVisibility(View.GONE);

        view10.setVisibility(View.GONE);

        btn_reset.setVisibility(View.GONE);

        btn_apply.setVisibility(View.GONE);

        spin_kit_loadingView.setVisibility(View.VISIBLE);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });

        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallyearListResponseCall();

        }

        else {

            callnointernet();
        }

        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallmakesListResponseCall();

        }

        else {

            callnointernet();
        }


        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallbrandsListResponseCall();

        }

        else {

            callnointernet();
        }

        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallcategoriesListResponseCall();
        }

        else {

            callnointernet();
        }

        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallcolorListResponseCall();

        }

        else {

            callnointernet();
        }


    }

    /* Get Year */

    private void yearlist() {

        if (expandableLayout_yr.isExpanded()) {
            expandableLayout_yr.collapse();
            rv_year.setVisibility(View.GONE);
            img_arrow_year.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandableLayout_yr.expand();
            rv_year.setVisibility(View.VISIBLE);
            img_arrow_year.setImageResource(R.drawable.ic_up_arrow);
        }

    }

    @SuppressLint("LongLogTag")
    private void fetchallyearListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchAllYearResponse> call = apiInterface.fetchallyearListResponseCall(RestUtils.getContentType(), fetchAllYearRequest());
        Log.w(TAG, "FetchAllYearResponse url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<FetchAllYearResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchAllYearResponse> call, @NonNull Response<FetchAllYearResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if (200 == response.body().getCode()) {

                        Log.w(TAG, "FetchAllYearResponse" + new Gson().toJson(response.body()));

                        yearBeanList = response.body().getData().getYear();

                        if (yearBeanList != null && yearBeanList.size() > 0) {

                            if (dd4YouConfig.isInternetConnectivity()) {

                                fetchallmakesListResponseCall();
                            } else {

                                callnointernet();
                            }

                            setViewYearList(yearBeanList);
                        } else {


                        }
                    } else {

                        showErrorLoading(response.body().getMessage());
                    }


                }

            }


            @Override
            public void onFailure(@NonNull Call<FetchAllYearResponse> call, @NonNull Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG, "FetchAllYearResponse flr" + t.getMessage());
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


        Log.w(TAG, "FetchAllYearRequest " + new Gson().toJson(fetchAllYearRequest));
        return fetchAllYearRequest;
    }

    private void setViewYearList(List<FetchAllYearResponse.DataBean.YearBean> yearBeanList) {

        rv_year.setLayoutManager(new GridLayoutManager(FilterlistActivity.this, 2));

        rv_year.setMotionEventSplittingEnabled(false);

        rv_year.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_year.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_year.setItemAnimator(new DefaultItemAnimator());

        SearchYearFilterlistAdapter searchYearFilterlistAdapter = new SearchYearFilterlistAdapter(FilterlistActivity.this, yearBeanList, this);

        rv_year.setAdapter(searchYearFilterlistAdapter);


    }


    /* Get Makes */

    private void makelist() {

        if (expandable_layout_make.isExpanded()) {
            expandable_layout_make.collapse();
            rv_makes.setVisibility(View.GONE);
            img_arrow_make.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_make.expand();
            rv_makes.setVisibility(View.VISIBLE);
            img_arrow_make.setImageResource(R.drawable.ic_up_arrow);
        }

    }

    @SuppressLint("LongLogTag")
    private void fetchallmakesListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchAllParentMakesResponse> call = apiInterface.fetchallmakesListResponseCall(RestUtils.getContentType());
        Log.w(TAG, "FetchAllParentMakesResponse url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<FetchAllParentMakesResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchAllParentMakesResponse> call, @NonNull Response<FetchAllParentMakesResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if (200 == response.body().getCode()) {

                        Log.w(TAG, "FetchAllParentMakesResponse" + new Gson().toJson(response.body()));

                        makesBeanList = response.body().getData().getMake();

                        if (makesBeanList != null && makesBeanList.size() > 0) {


                            setViewMakesList(makesBeanList);
                        } else {


                        }
                    } else {

                        showErrorLoading(response.body().getMessage());
                    }


                }

            }


            @Override
            public void onFailure(@NonNull Call<FetchAllParentMakesResponse> call, @NonNull Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG, "FetchAllParentMakesResponse flr" + t.getMessage());
            }
        });


    }

    private void setViewMakesList(List<FetchAllParentMakesResponse.DataBean.MakeBean> makesBeanList) {

        rv_makes.setLayoutManager(new GridLayoutManager(FilterlistActivity.this, 2));

        rv_makes.setMotionEventSplittingEnabled(false);

        rv_makes.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_makes.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_makes.setItemAnimator(new DefaultItemAnimator());

        SearchMakeFilterlistAdapter searchMakeFilterlistAdapter = new SearchMakeFilterlistAdapter(FilterlistActivity.this, makesBeanList, this);

        rv_makes.setAdapter(searchMakeFilterlistAdapter);

    }


    /* Get Model */

    private void modellist() {

        if (expandable_layout_model.isExpanded()) {
            expandable_layout_model.collapse();
            rv_models.setVisibility(View.GONE);
            img_arrow_model.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_model.expand();
            rv_models.setVisibility(View.VISIBLE);
            img_arrow_model.setImageResource(R.drawable.ic_up_arrow);
        }

    }

    @SuppressLint("LongLogTag")
    private void fetchallmodelListResponseCall(String makesid) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchChildMakeslistRequestResponse> call = apiInterface.fetchallchildmakelistResponseCall(RestUtils.getContentType(),fetchChildMakeslistRequest(makesid));
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

                            setModelView(modelBeanList);
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
    private FetchChildMakeslistRequest fetchChildMakeslistRequest(String makesid) {

        /*
         * MAKE_ID : 6
         * SORT_BY : DESC
         */
        FetchChildMakeslistRequest fetchChildMakeslistRequest = new FetchChildMakeslistRequest();
        fetchChildMakeslistRequest.setMAKE_ID(makesid);
        fetchChildMakeslistRequest.setSORT_BY("DESC");

        Log.w(TAG,"FetchChildMakeslistRequest "+ new Gson().toJson(fetchChildMakeslistRequest));
        return fetchChildMakeslistRequest;
    }

    private void setModelView(List<FetchChildMakeslistRequestResponse.DataBean.ModelBean> makesBeanList) {

        rv_models.setLayoutManager(new GridLayoutManager(FilterlistActivity.this,2));

        rv_models.setMotionEventSplittingEnabled(false);

        rv_models.setNestedScrollingEnabled(true);

        int size =makesBeanList.size();

        rv_models.setItemAnimator(new DefaultItemAnimator());

        SearchModelFilterlistAdapter searchModelFilterlistAdapter = new SearchModelFilterlistAdapter(FilterlistActivity.this, makesBeanList,this);

        rv_models.setAdapter(searchModelFilterlistAdapter);



    }


    /* Get Brand */

    private void brandlist() {

        if (expandable_layout_brand.isExpanded()) {
            expandable_layout_brand.collapse();
            rv_brand.setVisibility(View.GONE);
            img_arrow_brand.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_brand.expand();
            rv_brand.setVisibility(View.VISIBLE);
            img_arrow_brand.setImageResource(R.drawable.ic_up_arrow);
        }

    }

    @SuppressLint("LongLogTag")
    private void fetchallbrandsListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchAllBrandsResponse> call = apiInterface.fetchallbrandsListResponseCall(RestUtils.getContentType());
        Log.w(TAG, "FetchAllBrandsResponse url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<FetchAllBrandsResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchAllBrandsResponse> call, @NonNull Response<FetchAllBrandsResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if (200 == response.body().getCode()) {

                        Log.w(TAG, "FetchAllBrandsResponse" + new Gson().toJson(response.body()));

                        brandsBeanList = response.body().getData().getBrand();

                        if (brandsBeanList != null && brandsBeanList.size() > 0) {

                            setBrandView(brandsBeanList);
                        } else {

                        }
                    } else {

                        showErrorLoading(response.body().getMessage());
                    }


                }

            }


            @Override
            public void onFailure(@NonNull Call<FetchAllBrandsResponse> call, @NonNull Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG, "FetchAllParentCategoriesResponse flr" + t.getMessage());
            }
        });


    }

    private void setBrandView(List<FetchAllBrandsResponse.DataBean.BrandBean> brandsBeanList) {

        rv_brand.setLayoutManager(new GridLayoutManager(FilterlistActivity.this, 2));

        rv_brand.setMotionEventSplittingEnabled(false);

        rv_brand.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_brand.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_brand.setItemAnimator(new DefaultItemAnimator());

        searchBrandFilterlistAdapter = new SearchBrandFilterlistAdapter(FilterlistActivity.this, brandsBeanList, this);

        rv_brand.setAdapter(searchBrandFilterlistAdapter);


    }


    /* Get Category */

    private void categlist() {

        if (expandable_layout_categ.isExpanded()) {
            expandable_layout_categ.collapse();
            rv_categ.setVisibility(View.GONE);
            img_arrow_categ.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_categ.expand();
            rv_categ.setVisibility(View.VISIBLE);
            img_arrow_categ.setImageResource(R.drawable.ic_up_arrow);
        }

    }

    @SuppressLint("LongLogTag")
    private void fetchallcategoriesListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchAllParentCategoriesResponse> call = apiInterface.fetchallcategoriesListResponseCall(RestUtils.getContentType());
        Log.w(TAG, "url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<FetchAllParentCategoriesResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchAllParentCategoriesResponse> call, @NonNull Response<FetchAllParentCategoriesResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if (200 == response.body().getCode()) {

                        Log.w(TAG, "FetchAllParentCategoriesResponse" + new Gson().toJson(response.body()));

                        categoriesBeanList = response.body().getData().getCategories();

                        if (categoriesBeanList != null && categoriesBeanList.size() > 0) {

                            setView(categoriesBeanList);
                        } else {

                        }
                    } else {

                        showErrorLoading(response.body().getMessage());

                    }


                }

            }


            @Override
            public void onFailure(@NonNull Call<FetchAllParentCategoriesResponse> call, @NonNull Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG, "FetchAllParentCategoriesResponse flr" + t.getMessage());
            }
        });


    }

    private void setView(List<FetchAllParentCategoriesResponse.DataBean.CategoriesBean> categoriesBeanList) {


        rv_categ.setLayoutManager(new GridLayoutManager(FilterlistActivity.this, 2));

        rv_categ.setMotionEventSplittingEnabled(false);

        rv_categ.setNestedScrollingEnabled(true);

        int size = categoriesBeanList.size();

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        rv_categ.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));

        rv_categ.setItemAnimator(new DefaultItemAnimator());

        SearchCategFilterlistAdapter searchCategFilterlistAdapter = new SearchCategFilterlistAdapter(FilterlistActivity.this, categoriesBeanList, this);

        rv_categ.setAdapter(searchCategFilterlistAdapter);


    }


    /* Price Range */

    private void pricelist() {

        if (expandable_layout_price_range.isExpanded()) {
            expandable_layout_price_range.collapse();
//            rv.setVisibility(View.GONE);
            img_arrow_pr_range.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_price_range.expand();
//            rv_categ.setVisibility(View.VISIBLE);
            img_arrow_pr_range.setImageResource(R.drawable.ic_up_arrow);
        }

    }

    /* Rating list */

    private void ratinglist() {

        if (expandable_layout_rating.isExpanded()) {
            expandable_layout_rating.collapse();
//            rv.setVisibility(View.GONE);
            img_arrow_rate.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_rating.expand();
//            rv_categ.setVisibility(View.VISIBLE);
            img_arrow_rate.setImageResource(R.drawable.ic_up_arrow);
        }

    }

    /* Color list */

    private void colorlist() {

        if (expandable_layout_color.isExpanded()) {
            expandable_layout_color.collapse();
            rv_color.setVisibility(View.GONE);
            img_arrow_color.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_color.expand();
            rv_color.setVisibility(View.VISIBLE);
            img_arrow_color.setImageResource(R.drawable.ic_up_arrow);
        }

    }

    /* Get Colors */

    @SuppressLint("LongLogTag")
    private void fetchallcolorListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchAllColorsResponse> call = apiInterface.fetchallcolorListResponseCall(RestUtils.getContentType(), fetchAllColorsRequest());
        Log.w(TAG, "FetchAllColorsResponse url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<FetchAllColorsResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchAllColorsResponse> call, @NonNull Response<FetchAllColorsResponse> response) {
//                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if (200 == response.body().getCode()) {

                        Log.w(TAG, "FetchAllColorsResponse" + new Gson().toJson(response.body()));

                        colorsBeanList = response.body().getData().getColors();

                        if (colorsBeanList != null && colorsBeanList.size() > 0) {

                            ll_year.setVisibility(View.VISIBLE);

                            ll_makes.setVisibility(View.VISIBLE);

                            ll_models.setVisibility(View.VISIBLE);

                            ll_brand.setVisibility(View.VISIBLE);

                            ll_categ.setVisibility(View.VISIBLE);

                            ll_price_range.setVisibility(View.VISIBLE);

                            ll_rating.setVisibility(View.VISIBLE);

                            ll_color.setVisibility(View.VISIBLE);

                            view4.setVisibility(View.VISIBLE);

                            view6.setVisibility(View.VISIBLE);

                            view16.setVisibility(View.VISIBLE);

                            view7.setVisibility(View.VISIBLE);

                            view8.setVisibility(View.VISIBLE);

                            view9.setVisibility(View.VISIBLE);

                            view10.setVisibility(View.VISIBLE);

                            btn_reset.setVisibility(View.VISIBLE);

                            btn_apply.setVisibility(View.VISIBLE);

                            spin_kit_loadingView.setVisibility(View.GONE);

                            setViewColorList(colorsBeanList);
                        } else {


                        }
                    } else {

                        showErrorLoading(response.body().getMessage());
                    }


                }

            }


            @Override
            public void onFailure(@NonNull Call<FetchAllColorsResponse> call, @NonNull Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG, "FetchAllColorsResponse flr" + t.getMessage());
            }
        });


    }

    @SuppressLint("LongLogTag")
    private FetchAllColorsRequest fetchAllColorsRequest() {


        /**
         * ATTRIBUTE : COLORS
         */


        FetchAllColorsRequest fetchAllColorsRequest = new FetchAllColorsRequest();
        fetchAllColorsRequest.setATTRIBUTE("COLORS");


        Log.w(TAG, "FetchAllYearRequest " + new Gson().toJson(fetchAllColorsRequest));
        return fetchAllColorsRequest;
    }

    private void setViewColorList(List<FetchAllColorsResponse.DataBean.ColorsBean> colorsBeanList) {

        rv_color.setLayoutManager(new GridLayoutManager(FilterlistActivity.this, 2));

        rv_color.setMotionEventSplittingEnabled(false);

        rv_color.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_color.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_color.setItemAnimator(new DefaultItemAnimator());

        SearchColorFilterlistAdapter searchColorFilterlistAdapter = new SearchColorFilterlistAdapter( FilterlistActivity.this, colorsBeanList, this);

        rv_color.setAdapter(searchColorFilterlistAdapter);


    }

    @Override
    public void getYearNameListener(String id, String year_name, CheckBox cb_flist, boolean isChecked) {

        Log.w(TAG, "Selected  Year ID" + id);

        Log.w(TAG, "Selected  Year Name" + year_name);

        if(isChecked){

            cb_flist.setChecked(true);

            year.add(year_name);

            Log.w(TAG, "Year_Values" + new Gson().toJson(year));
        }

        else {

            cb_flist.setChecked(false);

            year.remove(year_name);

            Log.w(TAG, "Year_Values" + new Gson().toJson(year));

        }

    }

    @Override
    public void getMakeIDListener(String id, String make_name, CheckBox cb_flist, boolean isChecked) {

        Log.w(TAG, "Selected  Make ID" + id);

        Log.w(TAG, "Selected  Make Name" + make_name);

        if(isChecked){

            cb_flist.setChecked(true);

            make.add(id);

            Log.w(TAG, "Make_ID" + new Gson().toJson(make));

               /* toString method returns the output as [Data
           Structure,Algorithms,...] In order to replace
           '[', ']' and spaces with empty strings to get
           comma separated values.*/

            String commaseparatedlist = make.toString();

            makesid = commaseparatedlist.replace("[", "")
                    .replace("]", "")
                    .replace(" ", "");

            Log.w(TAG, "commaseparatedlist_Make_ID" + makesid);

            if (dd4YouConfig.isInternetConnectivity()) {

                fetchallmodelListResponseCall(makesid);

            }

            else {

                callnointernet();
            }

        }

        else {

            cb_flist.setChecked(false);

            make.remove(id);

            Log.w(TAG, "Make_ID" + new Gson().toJson(make));

                           /* toString method returns the output as [Data
           Structure,Algorithms,...] In order to replace
           '[', ']' and spaces with empty strings to get
           comma separated values.*/

            String commaseparatedlist = make.toString();

            makesid = commaseparatedlist.replace("[", "")
                    .replace("]", "")
                    .replace(" ", "");

            if (dd4YouConfig.isInternetConnectivity()) {

                fetchallmodelListResponseCall(makesid);

            }

            else {

                callnointernet();
            }

        }
    }


    @Override
    public void getModelIDListener(String id, String model_name, CheckBox cb_flist, boolean isChecked) {

        Log.w(TAG, "Selected  Model ID" + id);

        Log.w(TAG, "Selected  Model Name" + model_name);

        if(isChecked){

            cb_flist.setChecked(true);

            model.add(id);

            Log.w(TAG, "Model_ID" + new Gson().toJson(model));

               /* toString method returns the output as [Data
           Structure,Algorithms,...] In order to replace
           '[', ']' and spaces with empty strings to get
           comma separated values.*/

            String commaseparatedlist = model.toString();

            modelid = commaseparatedlist.replace("[", "")
                    .replace("]", "")
                    .replace(" ", "");

            Log.w(TAG, "commaseparatedlist_Model_ID" + modelid);


        }

        else {

            cb_flist.setChecked(false);

            model.remove(id);

            Log.w(TAG, "Model_ID" + new Gson().toJson(model));

               /* toString method returns the output as [Data
           Structure,Algorithms,...] In order to replace
           '[', ']' and spaces with empty strings to get
           comma separated values.*/

            String commaseparatedlist = model.toString();

            modelid = commaseparatedlist.replace("[", "")
                    .replace("]", "")
                    .replace(" ", "");

            Log.w(TAG, "commaseparatedlist_Model_ID" + modelid);
        }

    }


    @Override
    public void getBrandIDListener(String idd, String brand_name, boolean isChecked) {

        Log.w(TAG, "Selected  Brand ID" + idd);

        Log.w(TAG, "Selected  Brand Name" + brand_name);

        if(isChecked){

            brandid = idd;
        }

        else {

            brandid="";
        }
    }

    @Override
    public void getCategIDListener(String id, String categ_name) {

        Log.w(TAG,"Selected  Categ ID"+id);

        Log.w(TAG,"Selected  Categ Name"+categ_name);
    }

    @Override
    public void getColorIDListener(String id, String color_name) {

        Log.w(TAG,"Selected  Color ID"+id);

        Log.w(TAG,"Selected Color Name"+color_name);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(FilterlistActivity.this,SearchProductListActivity.class);
        intent.putExtra("search_text",search_text);
        intent.putExtra("fromactivity",fromactivity);
        startActivity(intent);
        finish();

    }

    public void showErrorLoading(String errormesage) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(FilterlistActivity.this);
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoading());


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void hideLoading() {
        try {
            alertDialog.dismiss();
        } catch (Exception ignored) {

        }
    }


    private void callnointernet() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FilterlistActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(FilterlistActivity.this, FilterlistActivity.class));
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.ll_year:

            yearlist();

            break;

            case R.id.ll_makes:

            makelist();

            break;

            case R.id.ll_models:

            modellist();

            break;

            case R.id.ll_brand:

            brandlist();

            break;

            case R.id.ll_categ:

            categlist();

            break;

            case R.id.ll_price_range:

            pricelist();

            break;

            case R.id.ll_rating:

            ratinglist();

            break;

            case R.id.ll_color:

            colorlist();

            break;

        }

    }
}