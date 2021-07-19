package com.triton.bertsproject.retailerfragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.BrandListAdapter;
import com.triton.bertsproject.adapter.ParentCategoriesListAdapter;
import com.triton.bertsproject.adapter.ParentMakesListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.responsepojo.FetchAllBrandsResponse;
import com.triton.bertsproject.responsepojo.FetchAllParentCategoriesResponse;
import com.triton.bertsproject.responsepojo.FetchAllParentMakesResponse;
import com.triton.bertsproject.retailer.RetailerCartActivity;
import com.triton.bertsproject.retailer.RetailerDashboardActivity;
import com.triton.bertsproject.retailer.ShowAllBrandsActivity;
import com.triton.bertsproject.retailer.ShowAllParentCategoriesActivity;
import com.triton.bertsproject.retailer.ShowAllParentMakesActivity;
import com.triton.bertsproject.sessionmanager.Connectivity;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.GridSpacingItemDecoration;
import com.triton.bertsproject.utils.RestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopFragment extends Fragment implements View.OnClickListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_top_categories)
    RecyclerView rv_top_categories;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_top_brands)
    RecyclerView rv_top_brands;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_top_makes)
    RecyclerView rv_top_makes;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_viewall_catg)
    TextView txt_viewall_catg;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_viewall_brands)
    TextView txt_viewall_brands;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_viewall_makes)
    TextView txt_viewall_makes;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_recordscateg)
    TextView txt_no_recordscateg;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_recordsbrands)
    TextView txt_no_recordsbrands;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_recordsmakes)
    TextView txt_no_recordsmakes;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tb_topcat)
    TableLayout tb_topcat;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tb_brand)
    TableLayout tb_brand;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tb_makes)
    TableLayout tb_makes;

    List<FetchAllParentCategoriesResponse.DataBean.CategoriesBean> categoriesBeanList ;

    List<FetchAllBrandsResponse.DataBean.BrandBean> brandsBeanList ;

    AlertDialog alertDialog;

    List<FetchAllParentMakesResponse.DataBean.MakeBean> makesBeanList ;

    private static final String TAG = "ShopFragment";

    View view;

    Context context;

    DD4YouConfig dd4YouConfig;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cart_count)
    TextView txt_cart_count;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rlcart)
    RelativeLayout rlcart;

    String cart_count ="0";

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // clone the inflater using the ContextThemeWrapper
        assert container != null;

        context = container.getContext();

        // create ContextThemeWrapper from the original Activity Context with the custom theme
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Fragment);

        // clone the inflater using the ContextThemeWrapper
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

        // Inflate the layout for this fragment
        view = localInflater.inflate(R.layout.fragment_shop, container, false);

        ButterKnife.bind(this, view);

        Log.w("Oncreate ", TAG);

        dd4YouConfig = new DD4YouConfig(getContext());

        txt_toolbar_title.setText(R.string.shop);

        tb_topcat.setVisibility(View.GONE);

        rv_top_categories.setVisibility(View.GONE);

        tb_brand.setVisibility(View.GONE);

        rv_top_brands.setVisibility(View.GONE);

        tb_makes.setVisibility(View.GONE);

        rv_top_makes.setVisibility(View.GONE);

        spin_kit_loadingView.setVisibility(View.GONE);

        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallcategoriesListResponseCall();

            fetchallbrandsListResponseCall();

            fetchallmakesListResponseCall();

        }

        else
        {
            callnointernet();

        }

        txt_viewall_catg.setOnClickListener(v -> {

            Intent intent = new Intent(context, ShowAllParentCategoriesActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);


        });

        txt_viewall_brands.setOnClickListener(v -> {

            Intent intent = new Intent(context, ShowAllBrandsActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);
        });

        txt_viewall_makes.setOnClickListener(v -> {

            Intent intent = new Intent(context, ShowAllParentMakesActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);
        });

        SessionManager sessionManager = new SessionManager(getContext());

        HashMap<String, String> user = sessionManager.getProfileDetails();

        if(sessionManager.isLoggedIn())
        {
            Connectivity connectivity = new Connectivity();

            cart_count = connectivity.getData(getContext(),"Cart_Count");

            Log.w(TAG,"cart_count "+cart_count);

            if(cart_count!=null&&!cart_count.equals("0")){

                txt_cart_count.setText(""+cart_count);
            }

            else {

                txt_cart_count.setVisibility(View.GONE);
            }

        }

        else{


            txt_cart_count.setVisibility(View.GONE);
        }

        rlcart.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), RetailerCartActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeRight(Objects.requireNonNull(getContext()));

        });


        return view;
    }

    @SuppressLint("LongLogTag")
    private void fetchallcategoriesListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchAllParentCategoriesResponse> call = apiInterface.fetchallcategoriesListResponseCall(RestUtils.getContentType());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<FetchAllParentCategoriesResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<FetchAllParentCategoriesResponse> call, @NonNull Response<FetchAllParentCategoriesResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"FetchAllParentCategoriesResponse" + new Gson().toJson(response.body()));

                        categoriesBeanList = response.body().getData().getCategories();

                        if(categoriesBeanList != null && categoriesBeanList.size()>0){

                            rv_top_categories.setVisibility(View.VISIBLE);

                            tb_topcat.setVisibility(View.VISIBLE);

                            setViewCategList(categoriesBeanList);
                        }

                        else {


                            rv_top_categories.setVisibility(View.GONE);

                            tb_topcat.setVisibility(View.VISIBLE);

                            txt_no_recordscateg.setVisibility(View.VISIBLE);

                            txt_no_recordscateg.setText(R.string.cat_dis_msg);
                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());
                    }



                }

            }


            @Override
            public void onFailure(@NonNull Call<FetchAllParentCategoriesResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"FetchAllParentCategoriesResponse flr"+t.getMessage());
            }
        });



    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(getContext(), RetailerDashboardActivity.class));
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setViewCategList(List<FetchAllParentCategoriesResponse.DataBean.CategoriesBean> categoriesBeanList) {


        rv_top_categories.setLayoutManager(new GridLayoutManager(getContext(), 2));

        rv_top_categories.setMotionEventSplittingEnabled(false);

        rv_top_categories.setNestedScrollingEnabled(true);

        int size =4;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        rv_top_categories.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));

        rv_top_categories.setItemAnimator(new DefaultItemAnimator());

        ParentCategoriesListAdapter parentCategoriesListAdapter = new ParentCategoriesListAdapter(getContext(), categoriesBeanList, size, "ShopFragment");

        rv_top_categories.setAdapter(parentCategoriesListAdapter);



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

                            rv_top_brands.setVisibility(View.VISIBLE);

                            tb_brand.setVisibility(View.VISIBLE);

                            txt_no_recordsbrands.setVisibility(View.GONE);

                            setViewBrandList(brandsBeanList);
                        }

                        else {

                            rv_top_brands.setVisibility(View.GONE);

                            tb_brand.setVisibility(View.VISIBLE);

                            txt_no_recordsbrands.setVisibility(View.VISIBLE);

                            txt_viewall_brands.setText(R.string.brnd_dis_msg);
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


    private void setViewBrandList(List<FetchAllBrandsResponse.DataBean.BrandBean> brandsBeanList) {

        rv_top_brands.setLayoutManager(new GridLayoutManager(getContext(), 2));

        rv_top_brands.setMotionEventSplittingEnabled(false);

        rv_top_brands.setNestedScrollingEnabled(true);

        int size =4;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        rv_top_brands.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));

        rv_top_brands.setItemAnimator(new DefaultItemAnimator());

        BrandListAdapter brandListAdapter = new BrandListAdapter(getContext(), brandsBeanList, size, "shopFragment");

        rv_top_brands.setAdapter(brandListAdapter);



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

                            rv_top_makes.setVisibility(View.VISIBLE);

                            tb_makes.setVisibility(View.VISIBLE);

                            txt_no_recordsmakes.setVisibility(View.GONE);

                            setViewMakesList(makesBeanList);
                        }

                        else {

                            tb_makes.setVisibility(View.VISIBLE);

                            rv_top_makes.setVisibility(View.GONE);

                            txt_no_recordsmakes.setVisibility(View.VISIBLE);

                            txt_no_recordsmakes.setText(R.string.brnd_dis_msg);
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

        rv_top_makes.setLayoutManager(new GridLayoutManager(getContext(), 2));

        rv_top_makes.setMotionEventSplittingEnabled(false);

        rv_top_makes.setNestedScrollingEnabled(true);

        int size =4;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        rv_top_makes.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));

        rv_top_makes.setItemAnimator(new DefaultItemAnimator());

        ParentMakesListAdapter parentMakesListAdapter = new ParentMakesListAdapter(getContext(), makesBeanList,size, "ShopFragment");

        rv_top_makes.setAdapter(parentMakesListAdapter);



    }

    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
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
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onClick(View v) {

    }
}