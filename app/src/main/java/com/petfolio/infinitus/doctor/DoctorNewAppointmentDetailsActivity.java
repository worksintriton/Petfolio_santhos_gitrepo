package com.petfolio.infinitus.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.petfolio.infinitus.R;
import com.petfolio.infinitus.api.APIClient;
import com.petfolio.infinitus.api.RestApiInterface;
import com.petfolio.infinitus.petlover.PetMyappointmentsActivity;
import com.petfolio.infinitus.petlover.PetNewAppointmentDetailsActivity;
import com.petfolio.infinitus.petlover.VideoCallPetLoverActivity;
import com.petfolio.infinitus.requestpojo.AppoinmentCancelledRequest;
import com.petfolio.infinitus.requestpojo.DoctorStartAppointmentRequest;
import com.petfolio.infinitus.requestpojo.PetNewAppointmentDetailsRequest;
import com.petfolio.infinitus.responsepojo.AppoinmentCancelledResponse;
import com.petfolio.infinitus.responsepojo.AppointmentsUpdateResponse;
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

public class DoctorNewAppointmentDetailsActivity extends AppCompatActivity {

    private static final String TAG = "DrNewAppDetailsAct";


    AVLoadingIndicatorView avi_indicator;


    ImageView img_back;


    ImageView img_user;


    TextView txt_usrname;


    TextView txt_serv_name;


    TextView txt_serv_cost;


    Button btn_cancel;

    Button btn_accept;

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

    String userid, allergies,problem_info,pet_name,pet_type,doctorid;

    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_new_appointment_details);


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

        btn_accept = findViewById(R.id.btn_accept);

        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        appointment_id = bundle.getString("appointment_id");

        if (new ConnectionDetector(DoctorNewAppointmentDetailsActivity.this).isNetworkAvailable(DoctorNewAppointmentDetailsActivity.this)) {
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

                        String usr_image = response.body().getData().getUser_id().getProfile_img();

                        String servname = response.body().getData().getService_name();

                        String servcost= response.body().getData().getService_amount();

                        pet_name = response.body().getData().getPet_id().getPet_name();

                        String pet_image  = response.body().getData().getPet_id().getPet_img();

                        pet_type = response.body().getData().getPet_id().getPet_type();

                        String breed = response.body().getData().getPet_id().getPet_breed();

                        String gender= response.body().getData().getPet_id().getPet_gender();

                        String colour= response.body().getData().getPet_id().getPet_color();

                        String weight= String.valueOf(response.body().getData().getPet_id().getPet_weight());

                        String age= String.valueOf(response.body().getData().getPet_id().getPet_age());

                        userid = response.body().getData().getUser_id().get_id();

                        allergies = response.body().getData().getAllergies();

                        problem_info =  response.body().getData().getProblem_info();

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

                        usrname = response.body().getData().getUser_id().getFirst_name() ;

                        appoinment_status = response.body().getData().getAppoinment_status();

                        start_appointment_status = response.body().getData().getStart_appointment_status();

                        doctorid = response.body().getData().getDoctor_id().get_id();

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

            Glide.with(DoctorNewAppointmentDetailsActivity.this)
                    .load(usr_image)
                    .into(img_user);

        }


        if(!usrname.equals("")){

            txt_usrname.setText(usrname);
        }

//        if(!servname.equals("")){
//
//            txt_serv_name.setText(servname);
//        }

//        if(!servcost.equals("")){
//
//            txt_serv_cost.setText(servcost);
//        }


        if(!pet_image.equals("")){

            Glide.with(DoctorNewAppointmentDetailsActivity.this)
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

//        if(!addr.equals("")){
//
//            txt_address.setText(addr);
//        }

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DoctorNewAppointmentDetailsActivity.this, PrescriptionActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("id", appointment_id);
                i.putExtra("petname", pet_name);
                i.putExtra("pettype", pet_type);
                i.putExtra("userid", userid );
                i.putExtra("allergies", allergies);
                i.putExtra("probleminfo", problem_info );
                i.putExtra("doctorid",doctorid);
                Log.w(TAG, "ID-->" + appointment_id);
                startActivity(i);

            }
        });

    img_videocall.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             Log.w(TAG, "Start_appointment_status : " + appoinment_status);
             if (appoinment_status != null && appoinment_status.equalsIgnoreCase("Not Started")) {
                 doctorStartAppointmentResponseCall(appointment_id);
             } else {
                 Intent i = new Intent(DoctorNewAppointmentDetailsActivity.this, VideoCallDoctorActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 i.putExtra("id", appointment_id);
                 i.putExtra("petname", pet_name);
                 i.putExtra("pettype", pet_type);
                 i.putExtra("userid", userid );
                 i.putExtra("allergies", allergies);
                 i.putExtra("probleminfo", problem_info );
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
            dialog = new Dialog(DoctorNewAppointmentDetailsActivity.this);
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

    private void doctorStartAppointmentResponseCall(String id) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AppointmentsUpdateResponse> call = apiInterface.doctorStartAppointmentResponseCall(RestUtils.getContentType(), doctorStartAppointmentRequest(id));
        Log.w(TAG,"startAppointmentResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AppointmentsUpdateResponse>() {
            @Override
            public void onResponse(@NonNull Call<AppointmentsUpdateResponse> call, @NonNull Response<AppointmentsUpdateResponse> response) {

                Log.w(TAG,"startAppointmentResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        Intent i = new Intent(DoctorNewAppointmentDetailsActivity.this, VideoCallDoctorActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("id", appointment_id);
                        i.putExtra("petname", pet_name);
                        i.putExtra("pettype", pet_type);
                        i.putExtra("userid", userid );
                        i.putExtra("allergies", allergies);
                        i.putExtra("probleminfo", problem_info );
                        Log.w(TAG, "ID-->" + appointment_id);
                        startActivity(i);

                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<AppointmentsUpdateResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"startAppointmentResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }

    private DoctorStartAppointmentRequest doctorStartAppointmentRequest(String id) {
        /*
         * _id : 5fc639ea72fc42044bfa1683
         * appoinment_status : In-Progress
         */
        DoctorStartAppointmentRequest doctorStartAppointmentRequest = new DoctorStartAppointmentRequest();
        doctorStartAppointmentRequest.set_id(id);
        doctorStartAppointmentRequest.setStart_appointment_status("In-Progress");
        Log.w(TAG,"doctorStartAppointmentRequest"+ "--->" + new Gson().toJson(doctorStartAppointmentRequest));
        return doctorStartAppointmentRequest;
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
                        startActivity(new Intent(DoctorNewAppointmentDetailsActivity.this, PetMyappointmentsActivity.class));





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
        appoinmentCancelledRequest.setAppoint_patient_st("Doctor Cancelled appointment");
        appoinmentCancelledRequest.setAppoinment_status("Missed");
        Log.w(TAG,"appoinmentCancelledRequest"+ "--->" + new Gson().toJson(appoinmentCancelledRequest));
        return appoinmentCancelledRequest;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}