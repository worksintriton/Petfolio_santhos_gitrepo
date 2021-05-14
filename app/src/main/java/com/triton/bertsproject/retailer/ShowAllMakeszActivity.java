package com.triton.bertsproject.retailer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.ShoplistAdapter;
import com.triton.bertsproject.model.ShoplistModel;
import com.triton.bertsproject.utils.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowAllMakeszActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_top_makes)
    RecyclerView rv_top_makes;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    List<ShoplistModel> shoplistModels;

    private static final String TAG = "ShowAllMakeszActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_makesz);

        ButterKnife.bind(this);

        Log.w("Oncreate ", TAG);

        txt_toolbar_title.setText(R.string.makes);

        spin_kit_loadingView.setVisibility(View.GONE);

        setView();
    }

    private void setView() {

        shoplistModels = new ArrayList<>();

        shoplistModels.add(new ShoplistModel("Nissan",R.drawable.tc_img1));

        shoplistModels.add(new ShoplistModel("Mazda",R.drawable.tc_img2));

        shoplistModels.add(new ShoplistModel("Chevrolet",R.drawable.tc_img3));

        shoplistModels.add(new ShoplistModel("BMW",R.drawable.tc_img4));

        rv_top_makes.setLayoutManager(new GridLayoutManager(ShowAllMakeszActivity.this, 2));

        rv_top_makes.setMotionEventSplittingEnabled(false);

        rv_top_makes.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_top_makes.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_top_makes.setItemAnimator(new DefaultItemAnimator());

        ShoplistAdapter shoplistAdapter = new ShoplistAdapter(ShowAllMakeszActivity.this, shoplistModels);

        rv_top_makes.setAdapter(shoplistAdapter);



    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


}