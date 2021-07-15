package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.triton.bertsproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RetailerSetttingsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "RetailerSetngsActivity";

    public static String active_tag = "1";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;

    Context context = RetailerSetttingsActivity.this;

//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.coordinator)
//    CoordinatorLayout coordinatorLayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    String fromactivity;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    String tag;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_lang)
    RelativeLayout rl_lang;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_currncy)
    RelativeLayout rl_currncy;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_date)
    RelativeLayout rl_date;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_time)
    RelativeLayout rl_time;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_measurment_unit)
    RelativeLayout rl_measurment_unit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_settings);

        ButterKnife.bind( this);
        Log.w("Oncreate", TAG);
        txt_toolbar_title.setText(R.string.setting);
        floatingActionButton.setImageResource(R.drawable.berts_logo_fb);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fromactivity = extras.getString("fromactivity");
        }
        spin_kit_loadingView.setVisibility(View.GONE);
        tag = getIntent().getStringExtra("tag");
        Log.w(TAG, " tag : " + tag);
        bottomNavigation.setSelectedItemId(R.id.shop);
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        img_back.setOnClickListener(v -> {

            onBackPressed();

        });

        rl_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showLangBottomSheetDialog();
            }
        });

        rl_currncy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showCurrBottomSheetDialog();
            }
        });

        rl_measurment_unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showMeasurementUnitSheetDialog();
            }
        });

        rl_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDateSheetDialog();
            }
        });

        rl_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTimeSheetDialog();
            }
        });
    }

    public void showLangBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);

        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_lang);

        RecyclerView rv_language_list = bottomSheetDialog.findViewById(R.id.rv_language_list);

        bottomSheetDialog.show();
    }

    public void showCurrBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);

        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_currency);

        RecyclerView rv_currency_list = bottomSheetDialog.findViewById(R.id.rv_currency_list);

        bottomSheetDialog.show();
    }

    public void showMeasurementUnitSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);

        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_measurement_unit);

        bottomSheetDialog.show();
    }

    public void showDateSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);

        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_date);

        bottomSheetDialog.show();
    }

    public void showTimeSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);

        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_time);

        bottomSheetDialog.show();

    }

    public void callDirections(String tag){
        Intent intent = new Intent(RetailerSetttingsActivity.this,RetailerDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();

    }

    @SuppressLint("NonConstantResourceId")
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chat:
                active_tag = "4";
                callDirections(active_tag);
                return true;
            case R.id.garage:
                active_tag = "2";
                callDirections(active_tag);
                return true;
            case R.id.home:
                active_tag = "1";
                callDirections(active_tag);
                return true;
            case R.id.profile:
                active_tag = "5";
                callDirections(active_tag);
                return true;
            case R.id.shop:
                active_tag = "3";
                callDirections(active_tag);
                return true;
            default:
                return false;
        }
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onBackPressed() {

        callDirections("5");
    }


}