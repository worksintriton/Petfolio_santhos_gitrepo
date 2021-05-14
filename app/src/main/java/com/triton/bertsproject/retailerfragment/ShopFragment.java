package com.triton.bertsproject.retailerfragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.ShoplistAdapter;
import com.triton.bertsproject.model.ShoplistModel;
import com.triton.bertsproject.utils.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopFragment extends Fragment implements View.OnClickListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_top_categories)
    RecyclerView rv_top_categories;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_top_brands)
    RecyclerView rv_top_brands;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_top_makes)
    RecyclerView rv_top_makes;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    List<ShoplistModel> shoplistModels;

    List<ShoplistModel> shoplistModels1;

    List<ShoplistModel> shoplistModels2;

    private static final String TAG = "ShopFragment";

    View view;

    Context context;

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // clone the inflater using the ContextThemeWrapper
        assert container != null;

        context = container.getContext();

        // create ContextThemeWrapper from the original Activity Context with the custom theme
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Fragment);

        // clone the inflater using the ContextThemeWrapper
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

        // Inflate the layout for this fragment
        view = localInflater.inflate(R.layout.fragment_shop, container, false);

        ButterKnife.bind(this, view);

        Log.w("Oncreate ", TAG);

        txt_toolbar_title.setText(R.string.shop);

        spin_kit_loadingView.setVisibility(View.GONE);

        setView();


        return view;
    }

    private void setView() {

        shoplistModels = new ArrayList<>();

        shoplistModels1 = new ArrayList<>();

        shoplistModels2 = new ArrayList<>();

        shoplistModels.add(new ShoplistModel("Auto Body Parts",R.drawable.tc_img1));

        shoplistModels.add(new ShoplistModel("Headlights & Lighitings",R.drawable.tc_img2));

        shoplistModels.add(new ShoplistModel("Engine",R.drawable.tc_img3));

        shoplistModels.add(new ShoplistModel("Brakes & Suspension",R.drawable.tc_img4));

        rv_top_categories.setLayoutManager(new GridLayoutManager(getContext(), 2));

        rv_top_categories.setMotionEventSplittingEnabled(false);

        rv_top_categories.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_top_categories.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_top_categories.setItemAnimator(new DefaultItemAnimator());

        ShoplistAdapter shoplistAdapter = new ShoplistAdapter(getContext(), shoplistModels);

        rv_top_categories.setAdapter(shoplistAdapter);

        /* ************************************************************************/

        shoplistModels1.add(new ShoplistModel("Garrett",R.drawable.tc_img1));

        shoplistModels1.add(new ShoplistModel("Boregson",R.drawable.tc_img2));

        shoplistModels1.add(new ShoplistModel("Bosch",R.drawable.tc_img3));

        shoplistModels1.add(new ShoplistModel("K&N",R.drawable.tc_img4));

        rv_top_brands.setLayoutManager(new GridLayoutManager(getContext(), 2));

        rv_top_brands.setMotionEventSplittingEnabled(false);

        rv_top_brands.setNestedScrollingEnabled(false);

        //int size =3;

        rv_top_brands.setItemAnimator(new DefaultItemAnimator());

        ShoplistAdapter shoplistAdapter1 = new ShoplistAdapter(getContext(), shoplistModels1);

        rv_top_brands.setAdapter(shoplistAdapter1);


        /* ************************************************************************/

        shoplistModels2.add(new ShoplistModel("Nissan",R.drawable.tc_img1));

        shoplistModels2.add(new ShoplistModel("Mazda",R.drawable.tc_img2));

        shoplistModels2.add(new ShoplistModel("Chevrolet",R.drawable.tc_img3));

        shoplistModels2.add(new ShoplistModel("BMW",R.drawable.tc_img4));

        rv_top_makes.setLayoutManager(new GridLayoutManager(getContext(), 2));

        rv_top_makes.setMotionEventSplittingEnabled(false);

        rv_top_makes.setNestedScrollingEnabled(false);

        //int size =3;

        rv_top_makes.setItemAnimator(new DefaultItemAnimator());

        ShoplistAdapter shoplistAdapter2 = new ShoplistAdapter(getContext(), shoplistModels2);

        rv_top_makes.setAdapter(shoplistAdapter2);


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