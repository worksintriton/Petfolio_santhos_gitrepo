package com.triton.bertsproject.activities;

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

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import com.triton.bertsproject.customView.CustomEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        ButterKnife.bind(this);

        Log.w("Oncreate", TAG);

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

        if(edt_email.edtContent.getText().toString().equals("") || !isValidEmail(edt_email.edtContent.getText().toString() )){

            edt_email.setError("Please Enter Valid Mail ID");

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

        startActivity(new Intent(ForgetPasswordActivity.this,LoginActivity.class));

        Animatoo.animateSwipeLeft(context);

    }
}