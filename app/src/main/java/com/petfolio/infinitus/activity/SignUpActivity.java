package com.petfolio.infinitus.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.petfolio.infinitus.R;
import com.petfolio.infinitus.api.APIClient;
import com.petfolio.infinitus.api.RestApiInterface;
import com.petfolio.infinitus.appUtils.NumericKeyBoardTransformationMethod;
import com.petfolio.infinitus.requestpojo.SignupRequest;
import com.petfolio.infinitus.requestpojo.UserStatusUpdateRequest;
import com.petfolio.infinitus.responsepojo.SignupResponse;
import com.petfolio.infinitus.responsepojo.UserStatusUpdateResponse;
import com.petfolio.infinitus.utils.ConnectionDetector;
import com.petfolio.infinitus.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_usertypes)
    TextView txt_usertypes;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_changeusertype)
    Button btn_changeusertype;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_firstname)
    EditText edt_firstname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_lastname)
    EditText edt_lastname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_email)
    EditText edt_email;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_phone)
    EditText edt_phone;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_continue)
    Button btn_continue;

    private final String TAG = "SignUpActivity";

    private String UserType;
    private int UserTypeValue;
     Dialog alertDialog;

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    String[] permissionString = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.RECEIVE_SMS,
            "check"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        avi_indicator.setVisibility(View.GONE);

        edt_phone.setTransformationMethod(new NumericKeyBoardTransformationMethod());

        img_back.setOnClickListener(this);
        btn_changeusertype.setOnClickListener(this);
        btn_continue.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            UserType = extras.getString("UserType");
            UserTypeValue = extras.getInt("UserTypeValue");
        }else{
            UserType = "Pet lover";
            UserTypeValue = 1;
        }
        txt_usertypes.setText(UserType);


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_continue:
                signUpValidator();
                break;
            case R.id.img_back:
                 onBackPressed();
                break;

                case R.id.btn_changeusertype:
                    Intent intent = new Intent(SignUpActivity.this,ChooseUserTypeActivity.class);
                    intent.putExtra("UserType",UserType);
                    intent.putExtra("UserTypeValue",UserTypeValue);
                    startActivity(intent);
                    break;
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
        finish();
    }

    private void signupResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SignupResponse> call = apiInterface.signupResponseCall(RestUtils.getContentType(), signupRequest());
        Log.w(TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(@NonNull Call<SignupResponse> call, @NonNull Response<SignupResponse> response) {
                  avi_indicator.smoothToHide();
                Log.w(TAG,"SignupResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    if (200 == response.body().getCode()) {
                        if(response.body().getData().getUser_details().get_id() != null){
                            userStatusUpdateResponseCall(response.body().getData().getUser_details().get_id() );
                        }
                       /* Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                        Intent intent = new Intent(SignUpActivity.this,VerifyOtpActivity.class);
                        intent.putExtra("phonemumber",response.body().getData().getUser_details().getUser_phone());
                        intent.putExtra("otp",response.body().getData().getUser_details().getOtp());
                        intent.putExtra("usertype",response.body().getData().getUser_details().getUser_type());
                        intent.putExtra("userstatus","Incomplete");
                        Log.w(TAG,"signupResponseCall "+" userphone : "+response.body().getData().getUser_details().getUser_phone()+" usertype : "+response.body().getData().getUser_details().getUser_type());
                        startActivity(intent);*/


                    } else {
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<SignupResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private SignupRequest signupRequest() {
        /*
         * first_name : mohammed
         * last_name : imthiyas
         * user_email : m@gmail.com
         * user_phone : 987987989
         * user_type : 1
         * date_of_reg : 23/10/2019 12:12:00
         */
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setFirst_name(edt_firstname.getText().toString().trim());
        signupRequest.setLast_name(edt_lastname.getText().toString().trim());
        signupRequest.setUser_email(edt_email.getText().toString());
        signupRequest.setUser_phone(edt_phone.getText().toString());
        signupRequest.setUser_type(UserTypeValue);
        signupRequest.setDate_of_reg(currentDateandTime);
        signupRequest.setMobile_type("Android");
        Log.w(TAG,"signupRequest "+ new Gson().toJson(signupRequest));
        return signupRequest;
    }


    private void userStatusUpdateResponseCall(String id) {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<UserStatusUpdateResponse> call = apiInterface.userStatusUpdateResponse(RestUtils.getContentType(), userStatusUpdateRequest(id));
        Log.w(TAG,"SignupResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<UserStatusUpdateResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserStatusUpdateResponse> call, @NonNull Response<UserStatusUpdateResponse> response) {
                avi_indicator.smoothToHide();
                Log.w(TAG,"UserStatusUpdateResponse" + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    if (200 == response.body().getCode()) {

                      /*  SessionManager sessionManager = new SessionManager(SignUpActivity.this);
                        //sessionManager.logoutUser();
                        //sessionManager.setIsLogin(true);
                        sessionManager.createLoginSession(
                                response.body().getData().get_id(),
                                response.body().getData().getFirst_name(),
                                response.body().getData().getLast_name(),
                                response.body().getData().getUser_email(),
                                response.body().getData().getUser_phone(),
                                String.valueOf(response.body().getData().getUser_type()),
                                response.body().getData().getUser_status()
                        );*/

                        Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();
                        Intent intent = new Intent(SignUpActivity.this,VerifyOtpActivity.class);
                        intent.putExtra("phonemumber",response.body().getData().getUser_phone());
                        intent.putExtra("otp",response.body().getData().getOtp());
                        intent.putExtra("usertype",response.body().getData().getUser_type());
                        intent.putExtra("userid",response.body().getData().get_id());
                        intent.putExtra("userstatus","Incomplete");
                        intent.putExtra("firstname", response.body().getData().getFirst_name());
                        intent.putExtra("lastname",response.body().getData().getLast_name());
                        intent.putExtra("useremail", response.body().getData().getUser_email());
                        startActivity(intent);


                    } else {
                        showErrorLoading(response.body().getMessage());
                    }
                }


            }

            @Override
            public void onFailure(@NonNull Call<UserStatusUpdateResponse> call,@NonNull Throwable t) {
                avi_indicator.smoothToHide();
                Log.e("OTP", "--->" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private UserStatusUpdateRequest userStatusUpdateRequest(String id) {

        /*
         * user_id : 5fb6162a211fce241eaf53a9
         * user_status : complete
         */
        UserStatusUpdateRequest userStatusUpdateRequest = new UserStatusUpdateRequest();
        userStatusUpdateRequest.setUser_id(id);
        userStatusUpdateRequest.setUser_status("complete");
        Log.w(TAG,"userStatusUpdateRequest "+ new Gson().toJson(userStatusUpdateRequest));
        return userStatusUpdateRequest;
    }

    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
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

    public void signUpValidator() {
        boolean can_proceed = true;
        int moblength = edt_phone.getText().toString().trim().length();
        int firstnamelength = edt_firstname.getText().toString().trim().length();
        int lastnamelength = edt_firstname.getText().toString().trim().length();
        String emailAddress = edt_email.getText().toString().trim();
        String emailPattern = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$";


        if (Objects.requireNonNull(edt_firstname.getText()).toString().trim().equals("") && Objects.requireNonNull(edt_lastname.getText()).toString().trim().equals("") &&
                Objects.requireNonNull(edt_phone.getText()).toString().trim().equals("")) {
            Toasty.warning(getApplicationContext(), "Please enter the fields", Toast.LENGTH_SHORT, true).show();
            can_proceed = false;
        } else if (edt_firstname.getText().toString().trim().equals("")) {
            edt_firstname.setError("Please enter first name");
            edt_firstname.requestFocus();
            can_proceed = false;
        }else if (firstnamelength > 25) {
            edt_firstname.setError("The maximum length for an first name is 25 characters.");
            edt_firstname.requestFocus();
            can_proceed = false;
        }
        else if (edt_lastname.getText().toString().trim().equals("")) {
            edt_lastname.setError("Please enter last name");
            edt_lastname.requestFocus();
            can_proceed = false;
        }
        else if (lastnamelength > 25) {
            edt_lastname.setError("The maximum length for an last name is 25 characters.");
            edt_lastname.requestFocus();
            can_proceed = false;
        }
        else if (Objects.requireNonNull(edt_phone.getText()).toString().trim().equals("")) {
            edt_phone.setError("Please enter phone number");
            edt_phone.requestFocus();
            can_proceed = false;
        } else if (moblength <= 9) {
            edt_phone.setError("Please enter valid mobile number");
            edt_phone.requestFocus();
            can_proceed = false;
        }else if(!emailAddress.matches(emailPattern)){
            edt_email.setError("Please enter correct E_mail address");
            edt_email.requestFocus();
            can_proceed = false;
        }




        if (can_proceed) {
            insertmappermission();


        }

    }

    private void insertmappermission() {

        int haslocationpermission;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            haslocationpermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) | checkSelfPermission(Manifest.permission.RECEIVE_SMS);

            if (haslocationpermission != PackageManager.PERMISSION_GRANTED) {

                if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) | !shouldShowRequestPermissionRationale(Manifest.permission.RECEIVE_SMS)) {

                    requestPermissions(permissionString,
                            REQUEST_CODE_ASK_PERMISSIONS);

                    return;
                }
                requestPermissions(permissionString,
                        REQUEST_CODE_ASK_PERMISSIONS);
            }else{
                if (new ConnectionDetector(SignUpActivity.this).isNetworkAvailable(SignUpActivity.this)) {
                    signupResponseCall();


                }

            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               /* startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();*/
                // Permission Granted
                if (new ConnectionDetector(SignUpActivity.this).isNetworkAvailable(SignUpActivity.this)) {
                    signupResponseCall();


                }



            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}