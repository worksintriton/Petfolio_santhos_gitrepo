package com.triton.bertsproject.activities;

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

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import com.triton.bertsproject.customView.CustomEditText;
import com.triton.bertsproject.retailer.RetailerDashboardActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    Context context = LoginActivity.this;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_email)
    CustomEditText edt_email;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_password)
    CustomEditText edt_password;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_sigin)
    Button btn_sigin;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_sigin_with_facebook)
    Button btn_sigin_with_facebook;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_forget_password)
    TextView txt_forget_password;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        txt_toolbar_title.setText(R.string.login);

        spin_kit_loadingView.setVisibility(View.GONE);

        Log.w("Oncreate", TAG);

        btn_sigin.setOnClickListener(v -> {

            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);
        });

        img_back.setOnClickListener(v -> {

            startActivity(new Intent(LoginActivity.this, RetailerDashboardActivity.class));

            Animatoo.animateSwipeRight(context);

        });

        txt_forget_password.setOnClickListener(v -> {

            startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));

            Animatoo.animateSwipeRight(context);

        });

        edt_email.setTitle(getString(R.string.email));

        edt_password.setTitle(getString(R.string.password));

        btn_sigin.setOnClickListener(v -> checkValidation());

    }

    private void checkValidation() {

        if(edt_email.edtContent.getText().toString().equals("") || !isValidEmail(edt_email.edtContent.getText().toString() )){

            edt_email.setError("Please Enter Valid Mail ID");

        }

        else if(edt_password.edtContent.getText().toString().equals("")){

            edt_password.setError("Please Enter Valid Password");

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

        // super.onBackPressed(); commented this line in order to disable back press
        //Write your code here
        startActivity(new Intent(LoginActivity.this, RetailerDashboardActivity.class));

        Animatoo.animateSwipeRight(context);
    }
}