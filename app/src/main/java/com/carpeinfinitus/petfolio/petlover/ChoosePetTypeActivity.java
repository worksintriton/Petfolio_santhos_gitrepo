package com.carpeinfinitus.petfolio.petlover;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.carpeinfinitus.petfolio.R;
import com.carpeinfinitus.petfolio.adapter.PetTypesListAdapter;
import com.carpeinfinitus.petfolio.api.APIClient;
import com.carpeinfinitus.petfolio.api.RestApiInterface;
import com.carpeinfinitus.petfolio.interfaces.PetTypeSelectListener;
import com.carpeinfinitus.petfolio.responsepojo.PetTypeListResponse;
import com.carpeinfinitus.petfolio.utils.ConnectionDetector;
import com.carpeinfinitus.petfolio.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChoosePetTypeActivity extends AppCompatActivity implements PetTypeSelectListener, View.OnClickListener {

    private static final String TAG = "ChoosePetTypeActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_usertype)
    RecyclerView rv_usertype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_norecords)
    TextView tv_norecords;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_change)
    Button btn_change;

    private String petType;
    private String petId;
    private List<PetTypeListResponse.DataBean.UsertypedataBean> usertypedataBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pet_type);
        ButterKnife.bind(this);
        avi_indicator.setVisibility(View.GONE);
        img_back.setOnClickListener(this);
        btn_change.setOnClickListener(this);



        if (new ConnectionDetector(ChoosePetTypeActivity.this).isNetworkAvailable(ChoosePetTypeActivity.this)) {
            petTypeListResponseCall();
        }


    }







    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.btn_change:
                gotoBreedType();
                break;
        }
    }

    @SuppressLint("LogNotTimber")
    private void gotoBreedType() {
        Intent intent = new Intent(ChoosePetTypeActivity.this, ChooseBreedTypeActivity.class);
        intent.putExtra("petType",petType);
        intent.putExtra("petId",petId);
        Log.w(TAG,"ChooseBreedTypeActivity petType : "+petType +"petId : "+petId);

        startActivity(intent);
        finish();
    }

    @SuppressLint("LogNotTimber")
    public void petTypeListResponseCall(){
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<PetTypeListResponse> call = apiInterface.petTypeListResponseCall(RestUtils.getContentType());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<PetTypeListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<PetTypeListResponse> call, @NonNull Response<PetTypeListResponse> response) {
                avi_indicator.smoothToHide();


                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        Log.w(TAG,"PetTypeListResponse" + new Gson().toJson(response.body()));

                        if(response.body().getData().getUsertypedata() != null) {
                            usertypedataBeanList = response.body().getData().getUsertypedata();
                        }
                        if(usertypedataBeanList != null && usertypedataBeanList.size()>0){
                            setView(usertypedataBeanList);
                        }
                    }



                }








            }


            @Override
            public void onFailure(@NonNull Call<PetTypeListResponse> call,@NonNull  Throwable t) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"PetTypeListResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LogNotTimber")
    private void setView(List<PetTypeListResponse.DataBean.UsertypedataBean> usertypedataBeanList) {
        rv_usertype.setLayoutManager(new GridLayoutManager(this, 2));
        rv_usertype.setItemAnimator(new DefaultItemAnimator());
        PetTypesListAdapter petTypesListAdapter = new PetTypesListAdapter(getApplicationContext(), usertypedataBeanList,this);
        rv_usertype.setAdapter(petTypesListAdapter);
    }

    @SuppressLint("LogNotTimber")
    @Override
    public void userTypeSelectListener(String pettitle, String petid) {
        Log.w(TAG,"userTypeSelectListener : "+" pettitle : "+pettitle+" petid : "+petid);
        petType = pettitle;
        petId = petid;


    }
}