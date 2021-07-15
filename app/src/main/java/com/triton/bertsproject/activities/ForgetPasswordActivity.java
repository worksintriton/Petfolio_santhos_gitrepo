package com.triton.bertsproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.customView.CustomEditText;
import com.triton.bertsproject.requestpojo.ForgotPasswordRequest;
import com.triton.bertsproject.requestpojo.LoginRequest;
import com.triton.bertsproject.responsepojo.ForgotPasswordResponse;
import com.triton.bertsproject.responsepojo.LoginResponse;
import com.triton.bertsproject.retailer.RetailerDashboardActivity;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.RestUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity {

    private static final String TAG = "ForgetPasswordActivity";

    Context context = ForgetPasswordActivity.this;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_email)
    CustomEditText edt_email;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_reset_link)
    Button btn_reset_link;

    AlertDialog alertDialog;

    DD4YouConfig dd4YouConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        ButterKnife.bind(this);

        Log.w("Oncreate", TAG);

        dd4YouConfig = new DD4YouConfig(this);

        txt_toolbar_title.setText(R.string.forget_password_withouI_simple);

        edt_email.setTitle(getString(R.string.email));

        spin_kit_loadingView.setVisibility(View.GONE);

        img_back.setOnClickListener(v -> {

            startActivity(new Intent(ForgetPasswordActivity.this,LoginActivity.class));

            Animatoo.animateSwipeLeft(context);
        });

        btn_reset_link.setOnClickListener(v -> checkValidation());

    }

    private void checkValidation() {

        boolean isvalid = true;

        String email = edt_email.edtContent.getText().toString();

        if(edt_email.edtContent.getText().toString().equals("") || !isValidEmail(edt_email.edtContent.getText().toString() )){

            edt_email.setError("Please Enter Valid Mail ID");

            isvalid = false;

        }

        if (isvalid){

            if(dd4YouConfig.isInternetConnectivity()){

                forgotPasswordResponseCall(email);
            }
        }

    }

    @SuppressLint("LongLogTag")
    private void forgotPasswordResponseCall(String email) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ForgotPasswordResponse> call = apiInterface.forgotpasswordResponseCall(RestUtils.getContentType(),forgotPasswordRequest(email));
        Log.w(TAG,"ForgotPasswordResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<ForgotPasswordResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<ForgotPasswordResponse> call, @NonNull Response<ForgotPasswordResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "ForgotPasswordResponse" + new Gson().toJson(response.body()));

                        showErrorLoading(response.body().getMessage());

                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }

                }



            }


            @Override
            public void onFailure(@NonNull Call<ForgotPasswordResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"ForgotPasswordResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private ForgotPasswordRequest forgotPasswordRequest(String email) {

        /*
         * email : prabhu.imsc@gmail.com
         */

        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
        forgotPasswordRequest.setEmail(email);

        Log.w(TAG,"ForgotPasswordRequest "+ new Gson().toJson(forgotPasswordRequest));
        return forgotPasswordRequest;
    }

    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ForgetPasswordActivity.this);
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


    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        startActivity(new Intent(ForgetPasswordActivity.this,ForgetPasswordActivity.class));

        Animatoo.animateSwipeLeft(context);

    }
}