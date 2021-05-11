package com.petfolio.infinitus.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.petfolio.infinitus.R;
import com.petfolio.infinitus.api.APIClient;
import com.petfolio.infinitus.api.RestApiInterface;
import com.petfolio.infinitus.requestpojo.FetchDoctorPaymDetaRequest;
import com.petfolio.infinitus.requestpojo.FetchPetloverPaymDetaRequest;
import com.petfolio.infinitus.requestpojo.SPFavCreateRequest;
import com.petfolio.infinitus.requestpojo.SPFavCreateResponse;
import com.petfolio.infinitus.responsepojo.FetchDoctorPaymDetaResponse;
import com.petfolio.infinitus.responsepojo.FetchPetloverDoctorFavListResponse;
import com.petfolio.infinitus.responsepojo.FetchPetloverPaymDetaResponse;
import com.petfolio.infinitus.sessionmanager.SessionManager;
import com.petfolio.infinitus.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetloverPaymentDetailsActivity extends AppCompatActivity {

    private final String TAG = "PetloverPaymentDetailsActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_docfavlist)
    RecyclerView rv_docfavlist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_load_more)
    Button btn_load_more;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    SessionManager session;
    String userid = "";
    private Context mContext;

    List<FetchPetloverDoctorFavListResponse.DataBean> dataBeanList;
    private Dialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petlover_payment_details);
    }

    @SuppressLint("LongLogTag")
    private void favResponseCall() {

        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<FetchPetloverPaymDetaResponse> call = apiInterface.fetchpetlvrprodetaillistResponseCall(RestUtils.getContentType(), fetchPetloverPaymDetaRequest());
        Log.w(TAG,"FetchPetloverPaymDetaResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<FetchPetloverPaymDetaResponse>() {
            @SuppressLint({"SetTextI18n", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<FetchPetloverPaymDetaResponse> call, @NonNull Response<FetchPetloverPaymDetaResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"SPFavCreateResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    if (200 == response.body().getCode()) {
                        if (response.body().getStatus() != null&&!response.body().getStatus().isEmpty()) {

                            Toasty.success(getApplicationContext(),""+response.body().getMessage(),Toasty.LENGTH_SHORT).show();

                        }
                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<FetchPetloverPaymDetaResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"FetchPetloverPaymDetaResponse flr"+ t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private FetchPetloverPaymDetaRequest fetchPetloverPaymDetaRequest() {

        /**
         * doctor_id : 603e2a7b2c2b43125f8cb805
         */

        FetchPetloverPaymDetaRequest fetchPetloverPaymDetaRequest = new FetchPetloverPaymDetaRequest();
        fetchPetloverPaymDetaRequest.setUser_id(userid);

        Log.w(TAG,"fetchPetloverPaymDetaRequest "+ new Gson().toJson(fetchPetloverPaymDetaRequest));
        return fetchPetloverPaymDetaRequest;
    }

}