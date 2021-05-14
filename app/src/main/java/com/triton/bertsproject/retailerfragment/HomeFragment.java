package com.triton.bertsproject.retailerfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.LoginActivity;
import com.triton.bertsproject.activities.RegisterActivity;
import com.triton.bertsproject.adapter.ShopbyAdapter;
import com.triton.bertsproject.model.ShopByNameModel;
import com.triton.bertsproject.retailer.AddVehicleActivity;
import com.triton.bertsproject.retailer.RetailerCartActivity;
import com.triton.bertsproject.retailer.SearchProductsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements View.OnClickListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_signin)
    Button btn_sigin;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_user_login)
    TextView txt_user_login;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_search)
    EditText edt_search;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fl_cart)
    FrameLayout fl_cart;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_shopby)
    RecyclerView rv_shopby;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_addVeh)
    Button btn_addVeh;

    List<ShopByNameModel> shopByNameModels;

    private static final String TAG = "HomeFragment";

    View view;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        Log.w("Oncreate ", TAG);

        txt_user_login.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), LoginActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeLeft(getContext());
        });

        btn_sigin.setOnClickListener(v -> {

           Intent intent = new Intent(getContext(), RegisterActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeRight(getContext());

        });

        spin_kit_loadingView.setVisibility(View.GONE);

        edt_search.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), SearchProductsActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeLeft(getContext());

        });

        btn_addVeh.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), AddVehicleActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeLeft(getContext());
        });

        fl_cart.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), RetailerCartActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeLeft(getContext());
        });


        setView();

        return view;
    }

    private void setView() {

        shopByNameModels = new ArrayList<>();

        shopByNameModels.add(new ShopByNameModel("Categories"));

        shopByNameModels.add(new ShopByNameModel("Brands"));

        shopByNameModels.add(new ShopByNameModel("Makes"));

        rv_shopby.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        rv_shopby.setMotionEventSplittingEnabled(false);

        //int size =3;

        rv_shopby.setItemAnimator(new DefaultItemAnimator());

        ShopbyAdapter shopbyAdapter = new ShopbyAdapter(getContext(), shopByNameModels);

        rv_shopby.setAdapter(shopbyAdapter);

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


    @Override
    public void onClick(View v) {

    }
}