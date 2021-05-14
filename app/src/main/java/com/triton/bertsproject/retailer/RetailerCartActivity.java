package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.CartProductListAdapter;
import com.triton.bertsproject.model.RetailerProductlistModel;
import com.triton.bertsproject.utils.SwipeToDeleteCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RetailerCartActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "RetailerCartActivity";

    public static String active_tag = "1";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;

    CartProductListAdapter cartProductListAdapter;

    Context context = RetailerCartActivity.this;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinatorLayout;

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

//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.btn_proceed)
//    Button btn_proceed;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_deliveryaddrchange)
    TextView txt_deliveryaddrchange;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_shipaddrchange)
    TextView txt_shipaddrchange;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_cart);
        ButterKnife.bind( this);
        Log.w("Oncreate", TAG);
        txt_toolbar_title.setText(R.string.cart);
        floatingActionButton.setImageResource(R.drawable.berts_logo_fb);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           fromactivity = extras.getString("fromactivity");
        }
        spin_kit_loadingView.setVisibility(View.GONE);
        tag = getIntent().getStringExtra("tag");
        Log.w(TAG, " tag : " + this.tag);
        bottomNavigation.setSelectedItemId(R.id.shop);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        setView();
        img_back.setOnClickListener(v -> {

            startActivity(new Intent(RetailerCartActivity.this, SearchProductListActivity.class));

            Animatoo.animateSwipeRight(context);

        });

        txt_deliveryaddrchange.setOnClickListener(v -> {

            startActivity(new Intent(RetailerCartActivity.this, ShippingAddressActivity.class));

            Animatoo.animateSwipeRight(context);
        });

        txt_shipaddrchange.setOnClickListener(v -> {

            startActivity(new Intent(RetailerCartActivity.this, ShippingMethodActivity.class));

            Animatoo.animateSwipeRight(context);
        });

        //btn_proceed.setOnClickListener(v -> startActivity(new Intent(RetailerCartActivity.this,OrderListActivity.class)));
        enableSwipeToDeleteAndUndo();
    }


    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final RetailerProductlistModel retailerProductlistModel= cartProductListAdapter.getData().get(position);

                cartProductListAdapter.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", view -> {

                    cartProductListAdapter.restoreItem(retailerProductlistModel, position);
                    rv_productlist.scrollToPosition(position);
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(rv_productlist);
    }



    private void setView() {

        List<RetailerProductlistModel> retailerProductlistModels = new ArrayList<>();

        retailerProductlistModels.add(new RetailerProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...", "Part No: K5975", "5", "120", "139.20", R.drawable.splist1, false, true));

        rv_productlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        rv_productlist.setMotionEventSplittingEnabled(false);

        rv_productlist.setItemAnimator(new DefaultItemAnimator());

        cartProductListAdapter = new CartProductListAdapter(this, retailerProductlistModels);

        rv_productlist.setAdapter(cartProductListAdapter);
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chat:
                active_tag = "4";
                return true;
            case R.id.garage:
                active_tag = "2";
                return true;
            case R.id.home:
                active_tag = "1";
                return true;
            case R.id.profile:
                active_tag = "5";
                return true;
            case R.id.shop:
                active_tag = "3";
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
        startActivity(new Intent(this, SearchProductListActivity.class));
        Animatoo.animateSwipeRight(this.context);
    }
}
