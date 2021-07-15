package com.triton.bertsproject.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.tabs.TabLayout;
import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.RegisterFragmentPagerAdapter;
import com.triton.bertsproject.retailer.RetailerDashboardActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    Context context = RegisterActivity.this;

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
    @BindView(R.id.tablayout)
    TabLayout tablayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    String fromactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        Log.w("Oncreate", TAG);

        txt_toolbar_title.setText(R.string.register);

        spin_kit_loadingView.setVisibility(View.GONE);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromactivity = extras.getString("fromActivity");

        }

        img_back.setOnClickListener(v -> {

            if(fromactivity!=null&&!fromactivity.isEmpty()){

                if(fromactivity.equals("HomeFragment")){

                    startActivity(new Intent(RegisterActivity.this, RetailerDashboardActivity.class));

                    Animatoo.animateSwipeRight(context);

                }

                else if(fromactivity.equals("LoginActivity")){

                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

                    Animatoo.animateSwipeRight(context);

                }


            }



        });

        // Create an adapter that knows which fragment should be shown on each page
        RegisterFragmentPagerAdapter adapter = new RegisterFragmentPagerAdapter(this, getSupportFragmentManager(),fromactivity);

        // Set the adapter onto the view pager
        viewpager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        tablayout.setupWithViewPager(viewpager);
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

        if(fromactivity!=null&&!fromactivity.isEmpty()) {

            if (fromactivity.equals("HomeFragment")) {

                startActivity(new Intent(RegisterActivity.this, RetailerDashboardActivity.class));

                Animatoo.animateSwipeRight(context);

            } else if (fromactivity.equals("LoginActivity")) {

                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                Animatoo.animateSwipeRight(context);

            }

        }

        }
}