package com.carpeinfinitus.petfolio.fragmentdoctor.walkinappointments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.carpeinfinitus.petfolio.R;
import com.carpeinfinitus.petfolio.adapter.DoctorWalkinCompletedAppointmentAdapter;
import com.carpeinfinitus.petfolio.api.APIClient;
import com.carpeinfinitus.petfolio.api.RestApiInterface;
import com.carpeinfinitus.petfolio.requestpojo.DoctorNewAppointmentRequest;
import com.carpeinfinitus.petfolio.responsepojo.DoctorAppointmentsResponse;
import com.carpeinfinitus.petfolio.sessionmanager.SessionManager;
import com.carpeinfinitus.petfolio.utils.ConnectionDetector;
import com.carpeinfinitus.petfolio.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentDoctorWalkinCompletedAppointment extends Fragment implements View.OnClickListener {
    private String TAG = "FragmentDoctorWalkinCompletedAppointment";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_completedappointment)
    RecyclerView rv_completedappointment;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_load_more)
    Button btn_load_more;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_filter)
    Button btn_filter;




    SessionManager session;
    String type = "",name = "",doctorid = "";
    private SharedPreferences preferences;
    private Context mContext;
    private List<DoctorAppointmentsResponse.DataBean> completedAppointmentResponseList;


    public FragmentDoctorWalkinCompletedAppointment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w(TAG,"onCreateView");

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        View view = inflater.inflate(R.layout.fragment_doctor_completed_appointment, container, false);

        ButterKnife.bind(this, view);
        mContext = getActivity();

        avi_indicator.setVisibility(View.GONE);
        btn_load_more.setVisibility(View.GONE);
        btn_filter.setVisibility(View.GONE);

        btn_load_more.setOnClickListener(this);

        session = new SessionManager(getContext());
        HashMap<String, String> user = session.getProfileDetails();

        doctorid = user.get(SessionManager.KEY_ID);

        String doctorname = user.get(SessionManager.KEY_FIRST_NAME);

        Log.w(TAG,"Doctorid"+doctorid +"doctorname :"+doctorname);

      

        if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
            doctorCompletedAppointmentResponseCall();
        }


        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            //your method here
                            if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
                                doctorCompletedAppointmentResponseCall();
                            }

                        } catch (Exception e) {
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 30000);//you can put 30000(30 secs)

        return view;
    }



    @SuppressLint("LogNotTimber")
    private void doctorCompletedAppointmentResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<DoctorAppointmentsResponse> call = ApiService.doctorWalkinCompletedAppointmentResponseCall(RestUtils.getContentType(),doctorNewAppointmentRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<DoctorAppointmentsResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<DoctorAppointmentsResponse> call, @NonNull Response<DoctorAppointmentsResponse> response) {
               avi_indicator.smoothToHide();
                Log.w(TAG,"DoctorCompletedAppointmentResponse"+ "--->" + new Gson().toJson(response.body()));


               if (response.body() != null) {

                   if(200 == response.body().getCode()){
                       if(response.body().getData() != null) {
                           completedAppointmentResponseList = response.body().getData();
                           Log.w(TAG, "Size" + completedAppointmentResponseList.size());
                           Log.w(TAG, "completedAppointmentResponseList : " + new Gson().toJson(completedAppointmentResponseList));
                       }
                       if(response.body().getData() != null && response.body().getData().isEmpty()){
                           txt_no_records.setVisibility(View.VISIBLE);
                           txt_no_records.setText("No completed appointnments");
                           rv_completedappointment.setVisibility(View.GONE);
                           btn_load_more.setVisibility(View.GONE);
                           btn_filter.setVisibility(View.GONE);
                       }else{
                           txt_no_records.setVisibility(View.GONE);
                           rv_completedappointment.setVisibility(View.VISIBLE);
                           Log.w(TAG,"Size : "+completedAppointmentResponseList.size());
                           if(completedAppointmentResponseList.size() > 3){
                               btn_load_more.setVisibility(View.VISIBLE);
                           }else{
                               btn_load_more.setVisibility(View.GONE);

                           }
                           setView();
                       }

                   }



                }
            }

            @Override
            public void onFailure(@NonNull Call<DoctorAppointmentsResponse> call, @NonNull Throwable t) {
                avi_indicator.smoothToHide();

                Log.w(TAG,"DoctorCompletedAppointmentResponseflr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private DoctorNewAppointmentRequest doctorNewAppointmentRequest() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = simpleDateFormat.format(new Date());
        DoctorNewAppointmentRequest doctorNewAppointmentRequest = new DoctorNewAppointmentRequest();
        doctorNewAppointmentRequest.setDoctor_id(doctorid);
        doctorNewAppointmentRequest.setCurrent_time(currentDateandTime);
        Log.w(TAG,"doctorNewAppointmentRequest"+ "--->" + new Gson().toJson(doctorNewAppointmentRequest));
        return doctorNewAppointmentRequest;
    }
    private void setView() {
        rv_completedappointment.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_completedappointment.setItemAnimator(new DefaultItemAnimator());
        int size = 3;
        DoctorWalkinCompletedAppointmentAdapter doctorWalkinCompletedAppointmentAdapter = new DoctorWalkinCompletedAppointmentAdapter(getContext(), completedAppointmentResponseList, rv_completedappointment,size);
        rv_completedappointment.setAdapter(doctorWalkinCompletedAppointmentAdapter);

    }
    private void setViewLoadMore() {
        rv_completedappointment.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_completedappointment.setItemAnimator(new DefaultItemAnimator());
        int size = completedAppointmentResponseList.size();
        DoctorWalkinCompletedAppointmentAdapter doctorWalkinCompletedAppointmentAdapter = new DoctorWalkinCompletedAppointmentAdapter(getContext(), completedAppointmentResponseList, rv_completedappointment,size);
        rv_completedappointment.setAdapter(doctorWalkinCompletedAppointmentAdapter);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_load_more:
                setViewLoadMore();
                break;
        }
    }
}