package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.OrderListAdapter;
import com.triton.bertsproject.model.OrderlistModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderListActivity extends AppCompatActivity {

    private static final String TAG = "OrderListActivity";

//    public static String active_tag = "1";
//
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.bottomNavigation)
//    BottomNavigationView bottomNavigation;

    OrderListAdapter orderListAdapter;

    Context context = OrderListActivity.this;

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
    @BindView(R.id.rv_productlist)
    RecyclerView rv_productlist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    String tag;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        ButterKnife.bind( this);
        Log.w("Oncreate", TAG);
        txt_toolbar_title.setText(R.string.order_history);
        floatingActionButton.setImageResource(R.drawable.berts_logo_fb);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fromactivity = extras.getString("fromactivity");
        }
        spin_kit_loadingView.setVisibility(View.GONE);
        tag = getIntent().getStringExtra("tag");
        Log.w(TAG, " tag : " + this.tag);

        setView();
        img_back.setOnClickListener(v -> {

            startActivity(new Intent(OrderListActivity.this, RetailerCartActivity.class));

            Animatoo.animateSwipeRight(context);

        });

    }

    private void setView() {

        List<OrderlistModel> orderlistModels = new ArrayList<>();

        orderlistModels.add(new OrderlistModel("K-Metal", "Jeep CJ-Style Replacement Mirrors", "Completed", "Feb 28,2021 - 10.19", "139.20","FT1236784678", R.drawable.splist1));

        orderlistModels.add(new OrderlistModel("K-Metal", "Jeep CJ-Style Replacement Mirrors", "Cancelled", "Feb 28,2021 - 10.19", "139.20","FT1236784678", R.drawable.splist1));

        orderlistModels.add(new OrderlistModel("K-Metal", "Jeep CJ-Style Replacement Mirrors", "On Going", "Feb 28,2021 - 10.19", "139.20", "FT1236784678", R.drawable.splist1));

        rv_productlist.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));

        rv_productlist.setMotionEventSplittingEnabled(false);

        rv_productlist.setItemAnimator(new DefaultItemAnimator());

        orderListAdapter = new OrderListAdapter(this, orderlistModels);

        rv_productlist.setAdapter(orderListAdapter);
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
        startActivity(new Intent(this, RetailerCartActivity.class));
        Animatoo.animateSwipeRight(this.context);
    }
}