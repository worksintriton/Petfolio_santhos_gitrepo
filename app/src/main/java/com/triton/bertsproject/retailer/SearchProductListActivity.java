package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.SearchFilterlistAdapter;
import com.triton.bertsproject.adapter.SearchProductListAdapter;
import com.triton.bertsproject.model.SearchFilterListModel;
import com.triton.bertsproject.model.SearchProductlistModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchProductListActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    Context context = SearchProductListActivity.this;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.img_back)
//    ImageView img_back;
//
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
      TextView txt_toolbar_title;
//
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.edt_search)
//    EditText edt_search;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_filterlist)
    RecyclerView rv_filterlist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_searchprodlist)
    RecyclerView rv_searchprodlist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_sort_filter)
    RelativeLayout rl_sort_filter;

    private final static String TAG = "SearchProdListActivity";

    public static String active_tag = "1";

    String tag;

    String fromactivity;

    List<SearchFilterListModel> searchFilterListModels;

    List<SearchProductlistModel> searchProductlistModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product_list);

        ButterKnife.bind(this);

        Log.w("Oncreate", TAG);

        floatingActionButton.setImageResource(R.drawable.berts_logo_fb);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromactivity = extras.getString("fromactivity");

        }

        spin_kit_loadingView.setVisibility(View.GONE);

        txt_toolbar_title.setText(R.string.search_products);

        tag = getIntent().getStringExtra("tag");

        Log.w(TAG," tag : "+tag);

        bottomNavigation.setSelectedItemId(R.id.shop);

        bottomNavigation.setOnNavigationItemSelectedListener(this);

        rl_sort_filter.setOnClickListener(v -> {

            Intent intent = new Intent(SearchProductListActivity.this, FilterlistActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

        });

        setView();

    }

    private void setView() {

        searchFilterListModels = new ArrayList<>();

        searchFilterListModels.add(new SearchFilterListModel("Most Popular"));

        searchFilterListModels.add(new SearchFilterListModel("2018 A3 Audi Premium"));

        searchFilterListModels.add(new SearchFilterListModel("Brakes & Suspension"));

        rv_filterlist.setLayoutManager(new LinearLayoutManager(SearchProductListActivity.this, LinearLayoutManager.HORIZONTAL, false));

        rv_filterlist.setMotionEventSplittingEnabled(false);

        //int size =3;

        rv_filterlist.setItemAnimator(new DefaultItemAnimator());

        SearchFilterlistAdapter searchFilterlistAdapter = new SearchFilterlistAdapter(SearchProductListActivity.this, searchFilterListModels);

        rv_filterlist.setAdapter(searchFilterlistAdapter);

        /* **************************************************/

        searchProductlistModels = new ArrayList<>();

        searchProductlistModels.add(new SearchProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Part No: K5975","5","120","139.20",R.drawable.splist1));

        searchProductlistModels.add(new SearchProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Part No: K5975","5","120","139.20",R.drawable.splist1));

        searchProductlistModels.add(new SearchProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Part No: K5975","5","120","139.20",R.drawable.splist1));

        searchProductlistModels.add(new SearchProductlistModel("Power Stop K5975 Front and Rear Z23 Evolution...","Part No: K5975","5","120","139.20",R.drawable.splist1));

        rv_searchprodlist.setLayoutManager(new LinearLayoutManager(SearchProductListActivity.this, LinearLayoutManager.VERTICAL, false));

        rv_searchprodlist.setMotionEventSplittingEnabled(false);

        //int size =3;

        rv_searchprodlist.setItemAnimator(new DefaultItemAnimator());

        SearchProductListAdapter searchProductListAdapter = new SearchProductListAdapter(SearchProductListActivity.this, searchProductlistModels);

        rv_searchprodlist.setAdapter(searchProductListAdapter);

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
        startActivity(new Intent(SearchProductListActivity.this, SearchProductsActivity.class));

        Animatoo.animateSwipeRight(context);
    }
}