package com.petfolio.infinitus.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;
import com.petfolio.infinitus.R;
import com.petfolio.infinitus.activity.NotificationActivity;
import com.petfolio.infinitus.adapter.PetLoverSOSAdapter;
import com.petfolio.infinitus.adapter.SelectedServiceProviderAdapter;
import com.petfolio.infinitus.api.APIClient;
import com.petfolio.infinitus.api.RestApiInterface;
import com.petfolio.infinitus.interfaces.SoSCallListener;
import com.petfolio.infinitus.requestpojo.SPSpecificServiceDetailsRequest;
import com.petfolio.infinitus.responsepojo.PetLoverDashboardResponse;
import com.petfolio.infinitus.responsepojo.SPSpecificServiceDetailsResponse;
import com.petfolio.infinitus.serviceprovider.SPFiltersActivity;
import com.petfolio.infinitus.sessionmanager.SessionManager;
import com.petfolio.infinitus.utils.ConnectionDetector;
import com.petfolio.infinitus.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedServiceActivity extends AppCompatActivity implements View.OnClickListener, SoSCallListener, BottomNavigationView.OnNavigationItemSelectedListener {


    private String TAG = "SelectedServiceActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_selectedserviceimage)
    ImageView img_selectedserviceimage;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_selected_service)
    TextView txt_selected_service;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_totalproviders)
    TextView txt_totalproviders;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_nearbyservices)
    RecyclerView rv_nearbyservices;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_footer)
    View include_petlover_footer;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.view6)
    View view;

    BottomNavigationView bottom_navigation_view;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_root)
    LinearLayout ll_root;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_filters)
    RelativeLayout rl_filters;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_sort)
    RelativeLayout rl_sort;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.scrollview)
    NestedScrollView scrollablContent;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.bottomSheetLayouts)
    CoordinatorLayout bottomSheetLayouts;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.textView20)
    TextView textView20;

    private ShimmerFrameLayout mShimmerViewContainer;
    private View includelayout;

    private String active_tag;

    private List<SPSpecificServiceDetailsResponse.DataBean.ServiceProviderBean> serviceProviderList;
    private String userid;
    private String catid = "";
    private String from;
    private Dialog dialog;
    private static final int REQUEST_PHONE_CALL =1 ;
    private String sosPhonenumber;

    private Dialog alertDialog;

    private String selectedprice;
    private int distance = 0;
    private int reviewcount = 0;
    private int Count_value_start = 0;
    private int Count_value_end = 0;
    private String fromactivity;

    // BottomSheetBehavior variable
    @SuppressWarnings("rawtypes")
    public BottomSheetBehavior bottomSheetBehavior;


    @SuppressLint("LogNotTimber")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_service);

        ButterKnife.bind(this);
        Log.w(TAG,"onCreate");


        avi_indicator.setVisibility(View.GONE);

        img_selectedserviceimage.setVisibility(View.GONE);
        txt_selected_service.setVisibility(View.GONE);
        view.setVisibility(View.GONE);
        ll_root.setVisibility(View.GONE);
        bottomSheetLayouts.setVisibility(View.GONE);
        textView20.setVisibility(View.GONE);
        rl_filters.setVisibility(View.GONE);
        rl_sort.setVisibility(View.GONE);

        includelayout = findViewById(R.id.includelayout);
        mShimmerViewContainer = includelayout.findViewById(R.id.shimmer_layout);



        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        img_sos.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);
        toolbar_title.setText(getResources().getString(R.string.service_details));






        bottom_navigation_view = include_petlover_footer.findViewById(R.id.bottom_navigation_view);
        bottom_navigation_view.setItemIconTintList(null);
        bottom_navigation_view.setOnNavigationItemSelectedListener(this);
        bottom_navigation_view.getMenu().findItem(R.id.services).setChecked(true);



        SessionManager session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            catid = extras.getString("catid");
            from = extras.getString("from");
            fromactivity = extras.getString("fromactivity");
            reviewcount = extras.getInt("reviewcount");
            Count_value_start = extras.getInt("Count_value_start");
            Count_value_end = extras.getInt("Count_value_end");
            distance = extras.getInt("distance");
            selectedprice = extras.getString("selectedprice");


           }
        Log.w(TAG,"selectedprice : "+selectedprice+" distance  : "+distance);



        Log.w(TAG," userid : "+userid+ " catid : "+catid+" from : "+from);

        if(fromactivity != null && fromactivity.equalsIgnoreCase("SPFiltersActivity")) {
            if (new ConnectionDetector(SelectedServiceActivity.this).isNetworkAvailable(SelectedServiceActivity.this)) {
                SPSpecificServiceDetailsResponseCall(distance,reviewcount,Count_value_start,Count_value_end);
            }
        }else{
            if(catid != null && userid != null) {
                if (new ConnectionDetector(SelectedServiceActivity.this).isNetworkAvailable(SelectedServiceActivity.this)) {
                    SPSpecificServiceDetailsResponseCall(distance,reviewcount,Count_value_start,Count_value_end);
                }
            }
        }


        bottom_navigation_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                      
                        active_tag = "1";
                        callDirections(active_tag);
                        break;
                    case R.id.shop:
                        active_tag = "2";
                        callDirections(active_tag);
                        break;


                    case R.id.services:
                        active_tag = "3";
                        callDirections(active_tag);
                        break;
                    case R.id.care:
                        active_tag = "4";
                        callDirections(active_tag);
                        break;
                    case R.id.community:
                        active_tag = "5";
                        callDirections(active_tag);
                        break;

                }
                return true;
            }

        });

        img_back.setOnClickListener(this);
        img_sos.setOnClickListener(this);
        img_notification.setOnClickListener(this);
        img_cart.setOnClickListener(this);
        img_profile.setOnClickListener(this);
        rl_filters.setOnClickListener(this);
    }

    /**
     * method to setup the bottomsheet
     */
    private void setBottomSheet() {

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottomSheetLayouts));

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);

        bottomSheetBehavior.setHideable(false);

        bottomSheetBehavior.setFitToContents(false);

        bottomSheetBehavior.setHalfExpandedRatio(0.6f);


        // Capturing the callbacks for bottom sheet
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.w("Bottom Sheet Behaviour", "STATE_COLLAPSED");
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.w("Bottom Sheet Behaviour", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.w("Bottom Sheet Behaviour", "STATE_EXPANDED");
                        //  bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.w("Bottom Sheet Behaviour", "STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.w("Bottom Sheet Behaviour", "STATE_SETTLING");
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        Log.w("Bottom Sheet Behaviour", "STATE_HALF_EXPANDED");
                        break;
                }


            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {


            }


        });
    }



    @SuppressLint("LogNotTimber")
    private void SPSpecificServiceDetailsResponseCall(int distance, int reviewcount, int count_value_start, int count_value_end) {
       /* avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();*/
        img_selectedserviceimage.setVisibility(View.GONE);
        txt_selected_service.setVisibility(View.GONE);
        view.setVisibility(View.GONE);
        ll_root.setVisibility(View.GONE);
        bottomSheetLayouts.setVisibility(View.GONE);
        textView20.setVisibility(View.GONE);
        rl_filters.setVisibility(View.GONE);
        rl_sort.setVisibility(View.GONE);

        includelayout.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmerAnimation();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SPSpecificServiceDetailsResponse> call = apiInterface.SPSpecificServiceDetailsResponseCall(RestUtils.getContentType(), spSpecificServiceDetailsRequest(distance,reviewcount,count_value_start,count_value_end));
        Log.w(TAG,"SPSpecificServiceDetailsResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SPSpecificServiceDetailsResponse>() {
            @SuppressLint({"SetTextI18n", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<SPSpecificServiceDetailsResponse> call, @NonNull Response<SPSpecificServiceDetailsResponse> response) {
                //avi_indicator.smoothToHide();


                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);

                Log.w(TAG,"SPSpecificServiceDetailsResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    if (200 == response.body().getCode()) {
                        txt_no_records.setVisibility(View.GONE);
                        txt_totalproviders.setVisibility(View.GONE);
                        if (response.body().getData() != null) {

                            if(response.body().getData().getService_Details().get_id() != null) {
                                catid = response.body().getData().getService_Details().get_id();
                            }
                            Log.w(TAG,"catid : "+catid);
                            if(response.body().getData().getService_provider() != null) {
                                serviceProviderList = response.body().getData().getService_provider();
                            }

                            if(serviceProviderList != null && serviceProviderList.size()>0){
                                txt_totalproviders.setText(serviceProviderList.size()+" Providers");
                                view.setVisibility(View.VISIBLE);
                                bottomSheetLayouts.setVisibility(View.VISIBLE);
                                img_selectedserviceimage.setVisibility(View.VISIBLE);
                                txt_selected_service.setVisibility(View.VISIBLE);
                                ll_root.setVisibility(View.VISIBLE);
                                textView20.setVisibility(View.VISIBLE);
                                rl_filters.setVisibility(View.VISIBLE);
                                rl_sort.setVisibility(View.VISIBLE);
                                setBottomSheet();
                                if (response.body().getData().getService_Details().getImage_path() != null && !response.body().getData().getService_Details().getImage_path().isEmpty()) {

                                    Glide.with(SelectedServiceActivity.this)
                                            .load(response.body().getData().getService_Details().getImage_path())
                                            .into(img_selectedserviceimage);
                                } else {
                                    Glide.with(SelectedServiceActivity.this)
                                            .load(APIClient.PROFILE_IMAGE_URL)
                                            .into(img_selectedserviceimage);

                                }
                                if(response.body().getData().getService_Details().getTitle() != null){
                                    txt_selected_service.setText(response.body().getData().getService_Details().getTitle());
                                }
                                setViewListedSP(serviceProviderList);
                            }else{
                                showAlertSPNotAvlLoading(response.body().getAlert_msg());

                            }




                        }
                    }
                    else{
//                        txt_totalproviders.setText(serviceProviderList.size()+" Providers");
                        view.setVisibility(View.VISIBLE);
                        bottomSheetLayouts.setVisibility(View.VISIBLE);
                        img_selectedserviceimage.setVisibility(View.VISIBLE);
                        txt_selected_service.setVisibility(View.VISIBLE);
                        ll_root.setVisibility(View.VISIBLE);
                        textView20.setVisibility(View.VISIBLE);
                        rl_filters.setVisibility(View.VISIBLE);
                        rl_sort.setVisibility(View.VISIBLE);
                        txt_totalproviders.setVisibility(View.GONE);
                        txt_no_records.setVisibility(View.VISIBLE);
                        txt_no_records.setText("No service found");
                        setBottomSheet();
                        bottomSheetBehavior.setDraggable(false);
                        if (response.body().getData().getService_Details().getImage_path() != null && !response.body().getData().getService_Details().getImage_path().isEmpty()) {
                            Glide.with(SelectedServiceActivity.this)
                                    .load(response.body().getData().getService_Details().getImage_path())
                                    .into(img_selectedserviceimage);
                        }
                        else {
                            Glide.with(SelectedServiceActivity.this)
                                    .load(APIClient.PROFILE_IMAGE_URL)
                                    .into(img_selectedserviceimage);

                        }
                        if(response.body().getData().getService_Details().getTitle() != null){
                            txt_selected_service.setText(response.body().getData().getService_Details().getTitle());
                        }
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<SPSpecificServiceDetailsResponse> call,@NonNull Throwable t) {
                //avi_indicator.smoothToHide();
                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);
                Log.w(TAG,"SPSpecificServiceDetailsResponse flr"+ t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private SPSpecificServiceDetailsRequest spSpecificServiceDetailsRequest(int distance, int reviewcount, int count_value_start, int count_value_end) {
        /*
         * cata_id : 5fe185d61996f651f5133693
         * distance : 0
         * user_id : 5ffe70d5b699b42563933d90
         * Count_value_start : 0
         * Count_value_end : 500
         * review_count : 3
         */
        SPSpecificServiceDetailsRequest spSpecificServiceDetailsRequest = new SPSpecificServiceDetailsRequest();
        spSpecificServiceDetailsRequest.setCata_id(catid);
        spSpecificServiceDetailsRequest.setDistance(distance);
        spSpecificServiceDetailsRequest.setUser_id(userid);
        spSpecificServiceDetailsRequest.setCount_value_start(count_value_start);
        spSpecificServiceDetailsRequest.setCount_value_end(count_value_end);
        spSpecificServiceDetailsRequest.setReview_count(reviewcount);
        Log.w(TAG,"spSpecificServiceDetailsRequest "+ new Gson().toJson(spSpecificServiceDetailsRequest));
        return spSpecificServiceDetailsRequest;
    }

    private void setViewListedSP(List<SPSpecificServiceDetailsResponse.DataBean.ServiceProviderBean> serviceProviderList) {
        rv_nearbyservices.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_nearbyservices.setItemAnimator(new DefaultItemAnimator());
        SelectedServiceProviderAdapter doctorNewAppointmentAdapter = new SelectedServiceProviderAdapter(getApplicationContext(), serviceProviderList,catid,from,distance,reviewcount,Count_value_start,Count_value_end);
        rv_nearbyservices.setAdapter(doctorNewAppointmentAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!this.isDestroyed()) {
            Glide.with(SelectedServiceActivity.this).pauseRequests();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.img_sos:
                goto_SOS();
                break;
            case R.id.img_notification:
                startActivity(new Intent(SelectedServiceActivity.this, NotificationActivity.class));
                break;
            case R.id.img_profile:
                goto_Profile();
                break;
                case R.id.rl_filters:
                goto_SPFilter();
                break;
        }
    }

    private void goto_SPFilter() {
        Intent intent = new Intent(getApplicationContext(), SPFiltersActivity.class);
        intent.putExtra("distance",distance);
        intent.putExtra("catid",catid);
        intent.putExtra("selectedprice",selectedprice);
        intent.putExtra("reviewcount",reviewcount);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(from != null && from.equalsIgnoreCase("PetServices")){
            callDirections("3");
        }else{
            Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();
    }

    private void goto_Profile() {
        Intent intent = new Intent(getApplicationContext(),PetLoverProfileScreenActivity.class);
        intent.putExtra("fromactivity",TAG);
        intent.putExtra("catid",catid);
        intent.putExtra("from",from);
        intent.putExtra("distance",distance);
        intent.putExtra("reviewcount",reviewcount);
        intent.putExtra("Count_value_start",Count_value_start);
        intent.putExtra("Count_value_end",Count_value_end);
        startActivity(intent);
    }

    private void goto_SOS() {
        showSOSAlert(APIClient.sosList);
    }
    private void showSOSAlert(List<PetLoverDashboardResponse.DataBean.SOSBean> sosList) {

        try {

            dialog = new Dialog(SelectedServiceActivity.this);
            dialog.setContentView(R.layout.sos_popup_layout);
            RecyclerView rv_sosnumbers = (RecyclerView)dialog.findViewById(R.id.rv_sosnumbers);
            Button btn_call = (Button)dialog.findViewById(R.id.btn_call);
            TextView txt_no_records = (TextView)dialog.findViewById(R.id.txt_no_records);
            ImageView img_close = (ImageView)dialog.findViewById(R.id.img_close);
            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            if(sosList != null && sosList.size()>0){
                rv_sosnumbers.setVisibility(View.VISIBLE);
                btn_call.setVisibility(View.VISIBLE);
                txt_no_records.setVisibility(View.GONE);
                rv_sosnumbers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rv_sosnumbers.setItemAnimator(new DefaultItemAnimator());
                PetLoverSOSAdapter petLoverSOSAdapter = new PetLoverSOSAdapter(getApplicationContext(), sosList,this);
                rv_sosnumbers.setAdapter(petLoverSOSAdapter);
            }else{
                rv_sosnumbers.setVisibility(View.GONE);
                btn_call.setVisibility(View.GONE);
                txt_no_records.setVisibility(View.VISIBLE);
                txt_no_records.setText("No phone numbers");

            }

            btn_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(SelectedServiceActivity.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                    }
                    else
                    {
                        gotoPhone();
                    }

                }
            });



            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();


        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }
    private void gotoPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + sosPhonenumber));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }
    @Override
    public void soSCallListener(long phonenumber) {
        if(phonenumber != 0){
            sosPhonenumber = String.valueOf(phonenumber);
        }

    }

    public void showAlertSPNotAvlLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SelectedServiceActivity.this);
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoadingSPnotavl());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void hideLoadingSPnotavl() {
        try {
                distance = 1;

                if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
                    SPSpecificServiceDetailsResponseCall(distance, reviewcount, Count_value_start, Count_value_end);
                }


            alertDialog.dismiss();

        } catch (Exception ignored) {

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}