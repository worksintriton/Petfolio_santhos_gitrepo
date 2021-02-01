package com.petfolio.infinitus.petlover;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.petfolio.infinitus.R;
import com.petfolio.infinitus.api.APIClient;
import com.petfolio.infinitus.api.RestApiInterface;
import com.petfolio.infinitus.requestpojo.PetNewAppointmentDetailsRequest;
import com.petfolio.infinitus.responsepojo.PetNewAppointmentDetailsResponse;
import com.petfolio.infinitus.utils.ConnectionDetector;
import com.petfolio.infinitus.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetMissedAppointmentDetailsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "PetCmpAppDetailsAct";


    AVLoadingIndicatorView avi_indicator;


    ImageView img_back;


    ImageView img_user;


    TextView txt_usrname;


    TextView txt_serv_name;


    TextView txt_serv_cost;


    Button btn_cancel;


    ImageView img_petimg;


    TextView txt_pet_name;


    TextView txt_pet_type;


    TextView txt_breed;


    TextView txt_gender;


    TextView txt_color;

    TextView txt_weight;

    TextView txt_age;

    TextView txt_vaccinated;

    TextView txt_order_date;

    TextView txt_order_id;

    TextView txt_payment_method;

    TextView txt_order_cost;

    TextView txt_address;

    String appointment_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_missed_appointment_details);


        avi_indicator=findViewById(R.id.avi_indicator);


        img_back=findViewById(R.id.img_back);


        img_user =findViewById(R.id.img_user);


        txt_usrname =findViewById(R.id.txt_usrname);


        txt_serv_name=findViewById(R.id.txt_serv_name);


        txt_serv_cost=findViewById(R.id.txt_serv_cost);


        btn_cancel=findViewById(R.id.btn_cancel);


        img_petimg=findViewById(R.id.img_petimg);


        txt_pet_name=findViewById(R.id.txt_pet_name);


        txt_pet_type=findViewById(R.id.txt_pet_type);


        txt_breed=findViewById(R.id.txt_breed);


        txt_gender=findViewById(R.id.txt_gender);


        txt_color=findViewById(R.id.txt_color);


        txt_weight=findViewById(R.id.txt_weight);


        txt_age=findViewById(R.id.txt_age);


        txt_vaccinated =findViewById(R.id.txt_vaccinated);


        txt_order_date=findViewById(R.id.txt_order_date);


        txt_order_id =findViewById(R.id.txt_order);


        txt_payment_method =findViewById(R.id.txt_payment_method);


        txt_order_cost=findViewById(R.id.txt_order_cost);


        txt_address=findViewById(R.id.txt_address);

        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        appointment_id = bundle.getString("appointment_id");

        if (new ConnectionDetector(PetMissedAppointmentDetailsActivity.this).isNetworkAvailable(PetMissedAppointmentDetailsActivity.this)) {
            petNewAppointmentResponseCall();
        }
    }

    private void petNewAppointmentResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<PetNewAppointmentDetailsResponse> call = ApiService.petNewAppointDetailResponseCall(RestUtils.getContentType(), petNewAppointmentDetailsRequest());
        Log.w(TAG, "url  :%s" + call.request().url().toString());

        call.enqueue(new Callback<PetNewAppointmentDetailsResponse>() {
            @Override
            public void onResponse(@NonNull Call<PetNewAppointmentDetailsResponse> call, @NonNull Response<PetNewAppointmentDetailsResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG, "PetNewAppointmentDetailsResponse" + "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {

                    if (200 == response.body().getCode()) {

                        String vaccinated , addr = null, usrname = null;

                        String usr_image = response.body().getData().getDoctor_id().getProfile_img();

                        String servname = response.body().getData().getService_name();

                        String servcost= response.body().getData().getService_amount();

                        String pet_name = response.body().getData().getPet_id().getPet_name();

                        String pet_image  = response.body().getData().getPet_id().getPet_img();

                        String pet_type = response.body().getData().getPet_id().getPet_type();

                        String breed = response.body().getData().getPet_id().getPet_breed();

                        String gender= response.body().getData().getPet_id().getPet_gender();

                        String colour= response.body().getData().getPet_id().getPet_color();

                        String weight= String.valueOf(response.body().getData().getPet_id().getPet_weight());

                        String age= String.valueOf(response.body().getData().getPet_id().getPet_age());

                        if(response.body().getData().getPet_id().isVaccinated()){
                            vaccinated= "Yes";
                        }

                        else {

                            vaccinated="No" ;
                        }

                        String order_date= response.body().getData().getBooking_date();

                        String orderid= response.body().getData().getAppointment_UID();

                        String payment_method= response.body().getData().getPayment_method();

                        String order_cost= response.body().getData().getAmount();

                        List<PetNewAppointmentDetailsResponse.DataBean.DocBusinessInfoBean> Address= response.body().getData().getDoc_business_info();

                        for(int i =0; i<Address.size(); i++){

                            addr = Address.get(i).getClinic_loc();

                            usrname = Address.get(i).getDr_name();
                        }

                        setView(usrname , usr_image , servname , servcost , pet_name , pet_image , pet_type , breed

                                , gender, colour , weight , age , order_date , orderid , payment_method , order_cost, vaccinated , addr);
                    }


                }
            }

            @Override
            public void onFailure(@NonNull Call<PetNewAppointmentDetailsResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG, "PetNewAppointmentDetailsResponse" + "--->" + t.getMessage());
            }
        });

    }


    private PetNewAppointmentDetailsRequest petNewAppointmentDetailsRequest() {

        PetNewAppointmentDetailsRequest petNewAppointmentDetailsRequest = new PetNewAppointmentDetailsRequest();
        petNewAppointmentDetailsRequest.setApppointment_id(appointment_id);
        Log.w(TAG, "petNewAppointmentDetailsRequest" + "--->" + new Gson().toJson(petNewAppointmentDetailsRequest));
        return petNewAppointmentDetailsRequest;
    }

    private void setView(String usrname, String usr_image, String servname, String servcost, String pet_name, String pet_image, String pet_type, String breed, String gender, String colour, String weight, String age, String order_date, String orderid, String payment_method, String order_cost, String vaccinated, String addr) {


        if(!usr_image.equals("")){

            Glide.with(PetMissedAppointmentDetailsActivity.this)
                    .load(usr_image)
                    .into(img_user);

        }


        if(!usrname.equals("")){

            txt_usrname.setText(usrname);
        }

        if(!servname.equals("")){

            txt_serv_name.setText(servname);
        }

        if(!servcost.equals("")){

            txt_serv_cost.setText(servcost);
        }


        if(!pet_image.equals("")){

            Glide.with(PetMissedAppointmentDetailsActivity.this)
                    .load(pet_image)
                    .into(img_petimg);


        }

        if(!pet_name.equals("")){

            txt_pet_name.setText(pet_name);
        }

        if(!pet_type.equals("")){

            txt_pet_type.setText(pet_type);
        }

        if(!breed.equals("")){

            txt_breed.setText(breed);
        }

        if(!gender.equals("")){

            txt_gender.setText(gender);
        }

        if(!colour.equals("")){

            txt_color.setText(colour);
        }

        if(!weight.equals("")){

            txt_weight.setText(weight);
        }

        if(!age.equals("")){

            txt_age.setText(age);
        }

        txt_vaccinated.setText(vaccinated);

        if(!order_date.equals("")){

            txt_order_date.setText(order_date);
        }

        if(!orderid.equals("")){

            txt_order_id.setText(orderid);
        }

        if(!payment_method.equals("")) {

            txt_payment_method.setText(payment_method);

        }

        if(!order_cost.equals("")){

            txt_order_cost.setText(order_cost);
        }

        if(!addr.equals("")){

            txt_address.setText(addr);
        }

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                callDirections("1");
                break;
            case R.id.shop:
                callDirections("2");
                break;
            case R.id.services:
                callDirections("3");
                break;
            case R.id.care:
                callDirections("4");
                break;
            case R.id.community:
                callDirections("5");
                break;

            default:
                return  false;
        }
        return true;
    }
    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();
    }
}