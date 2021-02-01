package com.petfolio.infinitus.petlover;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.petfolio.infinitus.R;
import com.petfolio.infinitus.api.APIClient;
import com.petfolio.infinitus.api.RestApiInterface;
import com.petfolio.infinitus.requestpojo.AppoinmentCancelledRequest;
import com.petfolio.infinitus.requestpojo.PetNewAppointmentDetailsRequest;
import com.petfolio.infinitus.responsepojo.AppoinmentCancelledResponse;
import com.petfolio.infinitus.responsepojo.PetNewAppointmentDetailsResponse;
import com.petfolio.infinitus.utils.ConnectionDetector;
import com.petfolio.infinitus.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetNewAppointmentDetailsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "PetNewAppDetailsAct";


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

    ImageView img_videocall;

    String appoinment_status;

    String start_appointment_status;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_new_appointment_details);


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


        img_videocall=findViewById(R.id.img_videocall);

        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        appointment_id = bundle.getString("appointment_id");

        BottomNavigationView bottom_navigation_view = findViewById(R.id.bottom_navigation_view);

        bottom_navigation_view.setOnNavigationItemSelectedListener(this);


        if (new ConnectionDetector(PetNewAppointmentDetailsActivity.this).isNetworkAvailable(PetNewAppointmentDetailsActivity.this)) {
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

                        appoinment_status = response.body().getData().getAppoinment_status();

                        start_appointment_status = response.body().getData().getStart_appointment_status();

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

            Glide.with(PetNewAppointmentDetailsActivity.this)
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

            Glide.with(PetNewAppointmentDetailsActivity.this)
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

        img_videocall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(TAG,"Start_appointment_status : "+start_appointment_status);
                if(start_appointment_status != null && start_appointment_status.equalsIgnoreCase("Not Started")){
                    Toasty.warning(PetNewAppointmentDetailsActivity.this,"Doctor is yet to start the Appointment. Please wait for the doctor to initiate the Appointment", Toast.LENGTH_SHORT, true).show();
                }else {
                    Intent i = new Intent(PetNewAppointmentDetailsActivity.this, VideoCallPetLoverActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("id", appointment_id);
                    Log.w(TAG, "ID-->" + appointment_id);
                    startActivity(i);
                }


            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showStatusAlert(appointment_id,"Doctor");
            }
        });

    }

    private void showStatusAlert(String id,String appointmenttype) {
        try {
            dialog = new Dialog(PetNewAppointmentDetailsActivity.this);
            dialog.setContentView(R.layout.alert_approve_reject_layout);
            TextView tvheader = (TextView)dialog.findViewById(R.id.tvInternetNotConnected);
            tvheader.setText(R.string.cancelappointment);
            Button dialogButtonApprove = (Button) dialog.findViewById(R.id.btnApprove);
            dialogButtonApprove.setText("Yes");
            Button dialogButtonRejected = (Button) dialog.findViewById(R.id.btnReject);
            dialogButtonRejected.setText("No");

            dialogButtonApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    //if(appointmenttype != null && appointmenttype.equalsIgnoreCase("Doctor")){
                        appoinmentCancelledResponseCall(id);
                   // } else if(appointmenttype != null && appointmenttype.equalsIgnoreCase("SP")){
                        //spappoinmentCancelledResponseCall(id);
                    //}



                }
            });
            dialogButtonRejected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Toasty.info(context, "Rejected Successfully", Toast.LENGTH_SHORT, true).show();
                    dialog.dismiss();




                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }


    }

    private void appoinmentCancelledResponseCall(String id) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AppoinmentCancelledResponse> call = apiInterface.appoinmentCancelledResponseCall(RestUtils.getContentType(), appoinmentCancelledRequest(id));
        Log.w(TAG,"appoinmentCancelledResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AppoinmentCancelledResponse>() {
            @Override
            public void onResponse(@NonNull Call<AppoinmentCancelledResponse> call, @NonNull Response<AppoinmentCancelledResponse> response) {

                Log.w(TAG,"appoinmentCancelledResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        startActivity(new Intent(PetNewAppointmentDetailsActivity.this, PetMyappointmentsActivity.class));





                    }
                    else{
                        //showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<AppoinmentCancelledResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"appoinmentCancelledResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }

    private AppoinmentCancelledRequest appoinmentCancelledRequest(String id) {

        /*
         * _id : 5fc639ea72fc42044bfa1683
         * missed_at : 23-10-2000 10 : 00 AM
         * doc_feedback : One Emergenecy work i am cancelling this appointment
         * appoinment_status : Missed
         */


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        AppoinmentCancelledRequest appoinmentCancelledRequest = new AppoinmentCancelledRequest();
        appoinmentCancelledRequest.set_id(id);
        appoinmentCancelledRequest.setMissed_at(currentDateandTime);
        appoinmentCancelledRequest.setDoc_feedback("");
        appoinmentCancelledRequest.setAppoint_patient_st("Petowner Cancelled appointment");
        appoinmentCancelledRequest.setAppoinment_status("Missed");
        Log.w(TAG,"appoinmentCancelledRequest"+ "--->" + new Gson().toJson(appoinmentCancelledRequest));
        return appoinmentCancelledRequest;
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