package com.carpeinfinitus.petfolio.fragmentpetlover.walkinappointments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.carpeinfinitus.petfolio.R;
import com.carpeinfinitus.petfolio.adapter.PetWalkinCompletedAppointmentAdapter;
import com.carpeinfinitus.petfolio.api.APIClient;
import com.carpeinfinitus.petfolio.api.RestApiInterface;
import com.carpeinfinitus.petfolio.interfaces.AddReviewListener;
import com.carpeinfinitus.petfolio.requestpojo.AddReviewRequest;
import com.carpeinfinitus.petfolio.requestpojo.PetLoverAppointmentRequest;
import com.carpeinfinitus.petfolio.responsepojo.AddReviewResponse;
import com.carpeinfinitus.petfolio.responsepojo.PetAppointmentResponse;
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


public class FragmentPetWalkinCompletedAppointment extends Fragment implements View.OnClickListener, AddReviewListener {
    private final String TAG = "FragmentPetWalkinCompletedAppointment";

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

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;




    SessionManager session;
    String doctorid = "";
    private Context mContext;
    private List<PetAppointmentResponse.DataBean> completedAppointmentResponseList;
    private String userid;
    private String userrate;
    private Dialog alertDialog;

    private ShimmerFrameLayout mShimmerViewContainer;
    private View includelayout;


    public FragmentPetWalkinCompletedAppointment() {

    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w(TAG,"onCreateView");

        View view = inflater.inflate(R.layout.fragment_pet_completed_appointment, container, false);

        ButterKnife.bind(this, view);
        mContext = getActivity();
        includelayout = view.findViewById(R.id.includelayout);
        mShimmerViewContainer = includelayout.findViewById(R.id.shimmer_layout);

        avi_indicator.setVisibility(View.GONE);
        btn_load_more.setVisibility(View.GONE);
        btn_filter.setVisibility(View.GONE);

        btn_load_more.setOnClickListener(this);


        session = new SessionManager(getContext());
        HashMap<String, String> user = session.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);
        Log.w(TAG," userid : "+userid);

        String patientname = user.get(SessionManager.KEY_FIRST_NAME);

        Log.w(TAG,"Doctorid"+doctorid +"patientname :"+patientname);

      

        if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
            petCompletedAppointmentResponseCall();
        }

        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    try {
                        //your method here
                        if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
                            petCompletedAppointmentResponseCall();
                        }

                    } catch (Exception ignored) {
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 30000);//you can put 30000(30 secs)

        refresh_layout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
                            petCompletedAppointmentResponseCall();

                        }

                    }
                }
        );

        return view;
    }



    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private void petCompletedAppointmentResponseCall() {
        /*avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();*/
        mShimmerViewContainer.startShimmerAnimation();

        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<PetAppointmentResponse> call = ApiService.petWalkinCompletedAppointmentResponseCall(RestUtils.getContentType(),petLoverAppointmentRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<PetAppointmentResponse>() {
            @SuppressLint({"LongLogTag", "SetTextI18n"})
            @Override
            public void onResponse(@NonNull Call<PetAppointmentResponse> call, @NonNull Response<PetAppointmentResponse> response) {
               /* avi_indicator.smoothToHide();*/
                refresh_layout.setRefreshing(false);
                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);
                Log.w(TAG,"PetAppointmentResponse"+ "--->" + new Gson().toJson(response.body()));


                if (response.body() != null) {
                    if(200 == response.body().getCode()){
                        if(response.body().getData() != null && response.body().getData().size()>0){
                            completedAppointmentResponseList = response.body().getData();
                            Log.w(TAG,"Size"+completedAppointmentResponseList.size());
                            Log.w(TAG,"completedAppointmentResponseList : "+new Gson().toJson(completedAppointmentResponseList));

                            txt_no_records.setVisibility(View.GONE);
                            rv_completedappointment.setVisibility(View.VISIBLE);
                            if(completedAppointmentResponseList.size() > 3){
                                btn_load_more.setVisibility(View.VISIBLE);

                            }else{
                                btn_load_more.setVisibility(View.GONE);

                            }
                            setView();

                        }else{
                            rv_completedappointment.setVisibility(View.GONE);
                            btn_load_more.setVisibility(View.GONE);
                            btn_filter.setVisibility(View.GONE);
                            txt_no_records.setVisibility(View.VISIBLE);
                            txt_no_records.setText("No completed appointments");

                        }

                    }



                }
            }

            @Override
            public void onFailure(@NonNull Call<PetAppointmentResponse> call, @NonNull Throwable t) {
             /*   avi_indicator.smoothToHide();*/
                mShimmerViewContainer.stopShimmerAnimation();
                includelayout.setVisibility(View.GONE);

                Log.w(TAG,"PetNewAppointmentResponse"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private PetLoverAppointmentRequest petLoverAppointmentRequest() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = simpleDateFormat.format(new Date());

        PetLoverAppointmentRequest petLoverAppointmentRequest = new PetLoverAppointmentRequest();
        petLoverAppointmentRequest.setUser_id(userid);
        petLoverAppointmentRequest.setCurrent_time(currentDateandTime);
        Log.w(TAG,"petLoverAppointmentRequest"+ "--->" + new Gson().toJson(petLoverAppointmentRequest));
        return petLoverAppointmentRequest;
    }
    private void setView() {
        rv_completedappointment.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_completedappointment.setItemAnimator(new DefaultItemAnimator());
        int size = 3;
        PetWalkinCompletedAppointmentAdapter petWalkinCompletedAppointmentAdapter = new PetWalkinCompletedAppointmentAdapter(getContext(), completedAppointmentResponseList, size,this);
        rv_completedappointment.setAdapter(petWalkinCompletedAppointmentAdapter);

    }
    private void setViewLoadMore() {
        rv_completedappointment.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_completedappointment.setItemAnimator(new DefaultItemAnimator());
        int size = completedAppointmentResponseList.size();
        PetWalkinCompletedAppointmentAdapter petWalkinCompletedAppointmentAdapter = new PetWalkinCompletedAppointmentAdapter(getContext(), completedAppointmentResponseList, size,this);
        rv_completedappointment.setAdapter(petWalkinCompletedAppointmentAdapter);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_load_more) {
            setViewLoadMore();
        }
    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    @Override
    public void addReviewListener(String id, int userrate, String userfeedback,String appointment_for) {
        Log.w(TAG,"addReviewListener : "+"id : "+id+" userrate : "+userrate+" userfeedback : "+userfeedback+" appointment_for : "+appointment_for);
        showAddReview(id,appointment_for);
    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private void showAddReview(String id,String appointment_for) {
        try {

            Dialog dialog = new Dialog(mContext);
            dialog.setContentView(R.layout.addreview_popup_layout);
            dialog.setCancelable(true);
            RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
            EditText edt_addreview = dialog.findViewById(R.id.edt_addreview);
            Button btn_addreview = dialog.findViewById(R.id.btn_addreview);

            ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
                 userrate = String.valueOf(rating);
                Log.w(TAG,"onRatingChanged userrate : "+userrate);
            });

            btn_addreview.setOnClickListener(view -> {
                if(userrate != null){
                    dialog.dismiss();
                if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
                    if(appointment_for != null && appointment_for.equalsIgnoreCase("Doctor")){
                        addReviewResponseCall(id, edt_addreview.getText().toString(), userrate);

                    }else if(appointment_for != null && appointment_for.equalsIgnoreCase("SP")) {
                        spaddReviewResponseCall(id, edt_addreview.getText().toString(), userrate);
                    }
                }
                }else{
                    showErrorLoading("Please choose a star.");
                }


            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }
    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private void addReviewResponseCall(String id, String userfeedback, String userrate) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AddReviewResponse> call = apiInterface.addReviewResponseCall(RestUtils.getContentType(), addReviewRequest(id,userfeedback,userrate));
        Log.w(TAG,"addReviewResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AddReviewResponse>() {
            @Override
            public void onResponse(@NonNull Call<AddReviewResponse> call, @NonNull Response<AddReviewResponse> response) {

                Log.w(TAG,"AddReviewResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        showAddReviewSuccess();



                    }
                    else{
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<AddReviewResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"AddReviewResponse flr"+"--->" + t.getMessage());
            }
        });

    } @SuppressLint({"LogNotTimber", "LongLogTag"})

    private void spaddReviewResponseCall(String id, String userfeedback, String userrate) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AddReviewResponse> call = apiInterface.spaddReviewResponseCall(RestUtils.getContentType(), addReviewRequest(id,userfeedback,userrate));
        Log.w(TAG,"spaddReviewResponseCall url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<AddReviewResponse>() {
            @Override
            public void onResponse(@NonNull Call<AddReviewResponse> call, @NonNull Response<AddReviewResponse> response) {

                Log.w(TAG,"spaddReviewResponseCall"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        showAddReviewSuccess();



                    }
                    else{
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(@NonNull Call<AddReviewResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"spaddReviewResponseCall flr"+"--->" + t.getMessage());
            }
        });

    }

    @SuppressLint({"LogNotTimber", "LongLogTag"})
    private AddReviewRequest addReviewRequest(String id, String userfeedback, String userrate) {

        /*
         * _id : 5fd30a701978e618628c966c
         * user_feedback :
         * user_rate : 0
         */
        AddReviewRequest addReviewRequest = new AddReviewRequest();
        addReviewRequest.set_id(id);
        if(userfeedback != null){
            addReviewRequest.setUser_feedback(userfeedback);

        }else{
            addReviewRequest.setUser_feedback("");

        }if(userrate != null){
            int c = 0;
            try {

                c = Integer.parseInt(userrate);

            }
            catch(NumberFormatException e) {

                double d = Double.parseDouble(userrate);

                c = (int) d;
            }
            addReviewRequest.setUser_rate(c);

        }else{
            addReviewRequest.setUser_rate(0);

        }
        Log.w(TAG,"addReviewRequest"+ "--->" + new Gson().toJson(addReviewRequest));
        return addReviewRequest;
    }
    private void showAddReviewSuccess() {
        try {

            Dialog dialog = new Dialog(mContext);
            dialog.setContentView(R.layout.addreview_review_success_layout);
            dialog.setCancelable(false);

            Button btn_back = dialog.findViewById(R.id.btn_back);


            btn_back.setOnClickListener(view -> {
               dialog.dismiss();
                if (new ConnectionDetector(getActivity()).isNetworkAvailable(getActivity())) {
                    petCompletedAppointmentResponseCall();
                }


            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }
    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
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