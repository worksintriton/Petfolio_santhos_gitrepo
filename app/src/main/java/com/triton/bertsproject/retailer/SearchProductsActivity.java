package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.triton.bertsproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchProductsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    Context context = SearchProductsActivity.this;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;

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
    @BindView(R.id.edt_search)
    EditText edt_search;

    private final static String TAG = "SearchProductsActivity";

    public static String active_tag = "1";

    String tag;

    String fromactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_products);

        ButterKnife.bind(this);

        Log.w("Oncreate", TAG);

        floatingActionButton.setImageResource(R.drawable.berts_logo_fb);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromactivity = extras.getString("fromactivity");

        }

        spin_kit_loadingView.setVisibility(View.GONE);

        txt_toolbar_title.setVisibility(View.GONE);

        img_back.setOnClickListener(v -> {

            startActivity(new Intent(SearchProductsActivity.this, RetailerDashboardActivity.class));

            Animatoo.animateSwipeLeft(context);
        });

        edt_search.setOnClickListener(v -> {

            Intent intent = new Intent(SearchProductsActivity.this, SearchProductListActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeLeft(context);

        });


        tag = getIntent().getStringExtra("tag");

        Log.w(TAG," tag : "+tag);

        bottomNavigation.setSelectedItemId(R.id.shop);

        bottomNavigation.setOnNavigationItemSelectedListener(this);

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                active_tag = "1";
                //replaceFragment(new HomeFragment());
                break;
            case R.id.garage:
                active_tag = "2";
                // replaceFragment(new MyGarageFragment());
                break;
            case R.id.shop:
                active_tag = "3";
                //replaceFragment(new ShopFragment());
                break;
            case R.id.chat:
                active_tag = "4";
                // replaceFragment(new LiveChatFragment());
                break;
            case R.id.profile:
                active_tag = "5";
                //replaceFragment(new ProfileFragment());
                break;

            default:
                return  false;
        }
        return true;
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
        startActivity(new Intent(SearchProductsActivity.this, RetailerDashboardActivity.class));

        Animatoo.animateSwipeRight(context);
    }
}