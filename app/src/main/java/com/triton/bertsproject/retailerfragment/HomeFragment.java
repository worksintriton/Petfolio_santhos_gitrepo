package com.triton.bertsproject.retailerfragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.Utlity.PopUpClass;
import com.triton.bertsproject.activities.LoginActivity;
import com.triton.bertsproject.activities.RegisterActivity;
import com.triton.bertsproject.adapter.ShopbyAdapter;
import com.triton.bertsproject.adapter.VehicletListAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.model.ShopByNameModel;
import com.triton.bertsproject.requestpojo.HomepageDashboardResponse;
import com.triton.bertsproject.requestpojo.HomepageDashboardRequest;
import com.triton.bertsproject.requestpojo.HomepageDashboardResponse;
import com.triton.bertsproject.retailer.AddVehicleActivity;
import com.triton.bertsproject.retailer.MyWishlistActivity;
import com.triton.bertsproject.retailer.RetailerCartActivity;
import com.triton.bertsproject.retailer.SearchProductListActivity;
import com.triton.bertsproject.retailer.SearchProductsActivity;
import com.triton.bertsproject.sessionmanager.Connectivity;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.RestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_signin)
    Button btn_sigin;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_user_login)
    TextView txt_user_login;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rlcart)
    RelativeLayout rlcart;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_shopby)
    RecyclerView rv_shopby;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_addVeh)
    Button btn_addVeh;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_user_type)
    TextView txt_user_type;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cl_loginbefore)
    CardView cl_loginbefore;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_vehicle)
    CardView cv_vehicle;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_default_veh)
    CardView cv_default_veh;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_selected_vehc)
    TextView txt_selected_vehc;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_manageveh)
    RelativeLayout rl_manageveh;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_addVehfromdefault)
    RelativeLayout rl_addVehfromdefault;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_root)
    RelativeLayout rl_root;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_search)
    RelativeLayout rl_search;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shop_txt)
    TextView txt_shop_txt;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fl_image)
    FrameLayout fl_image;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cl_shipping)
    CardView cl_shipping;

    List<ShopByNameModel> shopByNameModels;

    AlertDialog alertDialog;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cart_count)
    TextView txt_cart_count;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_search)
    EditText edt_search;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_search)
    ImageView img_search;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_keyword_info)
    TextView txt_keyword_info;

    HomepageDashboardResponse.DataBean.DefaultVehicleBean defaultVehicleBeanList ;

    HashMap<String, String> user;

    private static final String TAG = "HomeFragment";

    View view;

    String username;

    SessionManager sessionManager;

    DD4YouConfig dd4YouConfig;

    String make_name,model_name,year,user_id;

    int cart_count =0;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        Log.w("Oncreate ", TAG);

        rl_root.setVisibility(View.GONE);

        cv_vehicle.setVisibility(View.GONE);

        cv_default_veh.setVisibility(View.GONE);

        rl_search.setVisibility(View.GONE);

        cl_loginbefore.setVisibility(View.GONE);

        txt_shop_txt.setVisibility(View.GONE);

        rv_shopby.setVisibility(View.GONE);

        fl_image.setVisibility(View.GONE);

        cl_shipping.setVisibility(View.GONE);

        spin_kit_loadingView.setVisibility(View.VISIBLE);

        sessionManager = new SessionManager(getContext());

        dd4YouConfig = new DD4YouConfig(getContext());

        user = sessionManager.getProfileDetails();

        initialSetUp();

        return view;
    }

    private void initialSetUp() {

        if(sessionManager.isLoggedIn())
        {

            user_id = user.get(SessionManager.KEY_ID);

            username = user.get(SessionManager.KEY_FIRST_NAME) + " " + user.get(SessionManager.KEY_LAST_NAME);

            if(dd4YouConfig.isInternetConnectivity()){

                usercommonResponseCall();
            }

            else {

                callnointernet();
            }

        }

        else {

            rl_root.setVisibility(View.VISIBLE);

            txt_cart_count.setVisibility(View.GONE);

            cv_vehicle.setVisibility(View.GONE);

            cv_default_veh.setVisibility(View.GONE);

            rl_search.setVisibility(View.VISIBLE);

            cl_loginbefore.setVisibility(View.VISIBLE);

            txt_shop_txt.setVisibility(View.VISIBLE);

            rv_shopby.setVisibility(View.VISIBLE);

            setView();

            txt_user_login.setOnClickListener(v -> {

                Intent intent = new Intent(getContext(), LoginActivity.class);

                intent.putExtra("fromactivity",TAG);

                startActivity(intent);

                Animatoo.animateSwipeLeft(Objects.requireNonNull(getContext()));
            });

            btn_sigin.setOnClickListener(v -> {

                Intent intent = new Intent(getContext(), RegisterActivity.class);

                intent.putExtra("fromactivity",TAG);

                startActivity(intent);

                Animatoo.animateSwipeRight(Objects.requireNonNull(getContext()));

            });

            fl_image.setVisibility(View.VISIBLE);

            cl_shipping.setVisibility(View.VISIBLE);

            spin_kit_loadingView.setVisibility(View.GONE);

        }

        img_search.setOnClickListener(v -> {

            checkValidation();


        });

        txt_keyword_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showKeywordInfo(v);
            }
        });


        rlcart.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), RetailerCartActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeLeft(Objects.requireNonNull(getContext()));
        });



    }

    private void showKeywordInfo(View v) {

        // Create an alert builder
        AlertDialog.Builder builder
                = new AlertDialog.Builder(getContext());

        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.popup_layout, null);
        builder.setView(customLayout);

        // add a button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // send data from the
                // AlertDialog to the Activity

            }
        });

        // create and show
        // the alert dialog
        AlertDialog dialog
                = builder.create();
        dialog.show();
    }

    private void checkValidation() {

        boolean isvalid = true;

        String search_text = edt_search.getText().toString();

        if(edt_search.getText().toString().equals("")){

            isvalid = false;

            edt_search.setError("Please Enter Valid Text");

        }

        if(isvalid){

            if (dd4YouConfig.isInternetConnectivity()) {

                Intent intent = new Intent(getContext(), SearchProductListActivity.class);

                intent.putExtra("fromactivity",TAG);

                intent.putExtra("search_text",search_text);

                startActivity(intent);

                Animatoo.animateSwipeLeft(Objects.requireNonNull(getContext()));

            }

            else
            {
                callnointernet();

            }


        }
    }



    private void setView() {

        shopByNameModels = new ArrayList<>();

        shopByNameModels.add(new ShopByNameModel("Categories"));

        shopByNameModels.add(new ShopByNameModel("Brands"));

        shopByNameModels.add(new ShopByNameModel("Makes"));

        rv_shopby.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        rv_shopby.setMotionEventSplittingEnabled(false);

        //int size =3;

        rv_shopby.setItemAnimator(new DefaultItemAnimator());

        ShopbyAdapter shopbyAdapter = new ShopbyAdapter(getContext(), shopByNameModels);

        rv_shopby.setAdapter(shopbyAdapter);

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

                            cart_count = response.body().getData().getCart_count();

                            Log.w(TAG, "Cart_Count" + cart_count);

                            Connectivity connectivity = new Connectivity();

                            if(getContext()!=null){

                                connectivity.storeData(getContext(),"Cart_Count",String.valueOf(cart_count));

                            }


                            defaultVehicleBeanList =response.body().getData().getDefault_vehicle();

                            Log.w(TAG, "defaultVehicleBeanList" + new Gson().toJson(defaultVehicleBeanList));

                            Log.w(TAG, "defaultVehicleBeanList" + defaultVehicleBeanList.toString());

                            if(defaultVehicleBeanList!=null&&defaultVehicleBeanList.getAv_default()!=null){

                                Log.w(TAG, "defaultVehicleBeanList true-->");

                                setDefaultView(defaultVehicleBeanList);

                            }

                            else {

                                Log.w(TAG, "defaultVehicleBeanList false-->");

                                rl_root.setVisibility(View.VISIBLE);

                                if (cart_count!=0)  {

                                    txt_cart_count.setText(""+cart_count);
                                }

                                else {

                                    txt_cart_count.setVisibility(View.GONE);
                                }

                                cv_vehicle.setVisibility(View.VISIBLE);

                                cv_default_veh.setVisibility(View.GONE);

                                rl_search.setVisibility(View.VISIBLE);

                                cl_loginbefore.setVisibility(View.GONE);

                                txt_shop_txt.setVisibility(View.VISIBLE);

                                rv_shopby.setVisibility(View.VISIBLE);

                                setView();

                                fl_image.setVisibility(View.VISIBLE);

                                cl_shipping.setVisibility(View.VISIBLE);

                                user_id = user.get(SessionManager.KEY_ID);

                                username = user.get(SessionManager.KEY_FIRST_NAME) + " " + user.get(SessionManager.KEY_LAST_NAME);

                                txt_user_type.setText(""+username);

                                btn_addVeh.setOnClickListener(v -> {

                                    Intent intent = new Intent(getContext(), AddVehicleActivity.class);

                                    intent.putExtra("fromactivity",TAG);

                                    startActivity(intent);

                                    Animatoo.animateSwipeLeft(Objects.requireNonNull(getContext()));
                                });

                            }
                        }

                        else {

                            showErrorLoading(response.body().getMessage());

                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

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

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setDefaultView(HomepageDashboardResponse.DataBean.DefaultVehicleBean defaultVehicleBeanList) {

        Log.w(TAG, "defaultVehicleBeanList defaultVehicleBeanList.getAv_default() ");

        if (defaultVehicleBeanList.getAv_default() != null&&!defaultVehicleBeanList.getAv_default().isEmpty()&&defaultVehicleBeanList.getAv_default().equals("1"))  {

            if (defaultVehicleBeanList.getAv_year() != null&&!defaultVehicleBeanList.getAv_year().isEmpty())  {

                year = defaultVehicleBeanList.getAv_year();

            }

            else {

                year="";
            }

            if (defaultVehicleBeanList.getMake_name() != null&&!defaultVehicleBeanList.getMake_name().isEmpty())  {

                make_name = defaultVehicleBeanList.getMake_name();
            }

            else {

                make_name="";
            }

            if (defaultVehicleBeanList.getModel_name() != null&&!defaultVehicleBeanList.getModel_name().isEmpty())  {

                model_name = defaultVehicleBeanList.getModel_name();
            }

            else {

                model_name = "";
            }

        }

        rl_root.setVisibility(View.VISIBLE);

        if (cart_count!=0)  {

            txt_cart_count.setText(""+cart_count);
        }

        else {

            txt_cart_count.setVisibility(View.GONE);
        }

        cv_vehicle.setVisibility(View.GONE);

        cv_default_veh.setVisibility(View.VISIBLE);

        rl_search.setVisibility(View.VISIBLE);

        cl_loginbefore.setVisibility(View.GONE);

        txt_shop_txt.setVisibility(View.VISIBLE);

        rv_shopby.setVisibility(View.VISIBLE);

        setView();

        fl_image.setVisibility(View.VISIBLE);

        cl_shipping.setVisibility(View.VISIBLE);

        txt_user_type.setText(""+username);

        txt_selected_vehc.setText(year + " " + " " +make_name+ " "+ model_name);

        rl_addVehfromdefault.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), AddVehicleActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeLeft(Objects.requireNonNull(getContext()));
        });

        rl_manageveh.setOnClickListener(v -> {


            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, new MyGarageFragment(), "NewFragmentTag");
            ft.commit();

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

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(getContext(), MyWishlistActivity.class));
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
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
}