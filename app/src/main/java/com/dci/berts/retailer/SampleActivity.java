package com.dci.berts.retailer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dci.berts.R;
import com.dci.berts.adapter.FilterColorlistAdapter;
import com.dci.berts.adapter.FilterlistAdapter;
import com.dci.berts.model.FilterColorlistModel;
import com.dci.berts.model.FilterlistModel;
import com.dci.berts.utils.GridSpacingItemDecoration;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SampleActivity extends AppCompatActivity implements View.OnClickListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_yr)
    ExpandableLayout expandableLayout_yr;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_make)
    ExpandableLayout expandable_layout_make;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_brand)
    ExpandableLayout expandable_layout_brand;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_categ)
    ExpandableLayout expandable_layout_categ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_price_range)
    ExpandableLayout expandable_layout_price_range;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_rating)
    ExpandableLayout expandable_layout_rating;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.expandable_layout_color)
    ExpandableLayout expandable_layout_color;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_year)
    LinearLayout ll_year;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_makes)
    LinearLayout ll_makes;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_brand)
    LinearLayout ll_brand;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_categ)
    LinearLayout ll_categ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_price_range)
    LinearLayout ll_price_range;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_rating)
    LinearLayout ll_rating;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_color)
    LinearLayout ll_color;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_year)
    ImageView img_arrow_year;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_make)
    ImageView img_arrow_make;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_brand)
    ImageView img_arrow_brand;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_categ)
    ImageView img_arrow_categ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_pr_range)
    ImageView img_arrow_pr_range;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_rate)
    ImageView img_arrow_rate;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_arrow_color)
    ImageView img_arrow_color;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_year)
    RecyclerView rv_year;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_makes)
    RecyclerView rv_makes;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_brand)
    RecyclerView rv_brand;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_categ)
    RecyclerView rv_categ;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_color)
    RecyclerView rv_color;

    List<FilterlistModel> filterlistModel;

    List<FilterColorlistModel> filterColorlistModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        ButterKnife.bind(this);

        ll_year.setOnClickListener(v -> yearlist());

        ll_makes.setOnClickListener(v -> makelist());

        ll_brand.setOnClickListener(v -> brandlist());

        ll_categ.setOnClickListener(v -> categlist());

        ll_price_range.setOnClickListener(v -> pricelist());

        ll_rating.setOnClickListener(v -> ratinglist());

        ll_color.setOnClickListener(v -> colorlist());


        setYear();

        setMake();

        setBrand();

        setCateg();

        setColor();


    }

    private void setYear() {

        filterlistModel = new ArrayList<>();

        filterlistModel.add(new FilterlistModel("1960",false));

        filterlistModel.add(new FilterlistModel("1961",false));

        filterlistModel.add(new FilterlistModel("1962",false));

        filterlistModel.add(new FilterlistModel("1963",false));

        rv_year.setLayoutManager(new GridLayoutManager(SampleActivity.this, 2));

        rv_year.setMotionEventSplittingEnabled(false);

        rv_year.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_year.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_year.setItemAnimator(new DefaultItemAnimator());

        FilterlistAdapter filterlistAdapter = new FilterlistAdapter(SampleActivity.this, filterlistModel);

        rv_year.setAdapter(filterlistAdapter);

    }

    private void yearlist() {

        if (expandableLayout_yr.isExpanded()) {
            expandableLayout_yr.collapse();
            rv_year.setVisibility(View.GONE);
            img_arrow_year.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandableLayout_yr.expand();
            rv_year.setVisibility(View.VISIBLE);
            img_arrow_year.setImageResource(R.drawable.ic_up_arrow);
        }

    }



    private void setMake() {

        filterlistModel = new ArrayList<>();

        filterlistModel.add(new FilterlistModel("BMW",false));

        filterlistModel.add(new FilterlistModel("Honda",false));

        filterlistModel.add(new FilterlistModel("Genuiene SLB",false));

        filterlistModel.add(new FilterlistModel("Jeep",false));

        rv_makes.setLayoutManager(new GridLayoutManager(SampleActivity.this, 2));

        rv_makes.setMotionEventSplittingEnabled(false);

        rv_makes.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_makes.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_makes.setItemAnimator(new DefaultItemAnimator());

        FilterlistAdapter filterlistAdapter = new FilterlistAdapter(SampleActivity.this, filterlistModel);

        rv_makes.setAdapter(filterlistAdapter);

    }

    private void makelist() {

        if (expandable_layout_make.isExpanded()) {
            expandable_layout_make.collapse();
            rv_makes.setVisibility(View.GONE);
            img_arrow_make.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_make.expand();
            rv_makes.setVisibility(View.VISIBLE);
            img_arrow_make.setImageResource(R.drawable.ic_up_arrow);
        }

    }



    private void setBrand() {

        filterlistModel = new ArrayList<>();

        filterlistModel.add(new FilterlistModel("BMW",false));

        filterlistModel.add(new FilterlistModel("Honda",false));

        filterlistModel.add(new FilterlistModel("Genuiene SLB",false));

        filterlistModel.add(new FilterlistModel("Jeep",false));

        rv_brand.setLayoutManager(new GridLayoutManager(SampleActivity.this, 2));

        rv_brand.setMotionEventSplittingEnabled(false);

        rv_brand.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_brand.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_brand.setItemAnimator(new DefaultItemAnimator());

        FilterlistAdapter filterlistAdapter = new FilterlistAdapter(SampleActivity.this, filterlistModel);

        rv_brand.setAdapter(filterlistAdapter);

    }

    private void brandlist() {

        if (expandable_layout_brand.isExpanded()) {
            expandable_layout_brand.collapse();
            rv_brand.setVisibility(View.GONE);
            img_arrow_brand.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_brand.expand();
            rv_brand.setVisibility(View.VISIBLE);
            img_arrow_brand.setImageResource(R.drawable.ic_up_arrow);
        }

    }



    private void setCateg() {

        filterlistModel = new ArrayList<>();

        filterlistModel.add(new FilterlistModel("Interior",false));

        filterlistModel.add(new FilterlistModel("Honda",false));

        filterlistModel.add(new FilterlistModel("Extorior",false));

        filterlistModel.add(new FilterlistModel("Jeep",false));

        rv_categ.setLayoutManager(new GridLayoutManager(SampleActivity.this, 2));

        rv_categ.setMotionEventSplittingEnabled(false);

        rv_categ.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_categ.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_categ.setItemAnimator(new DefaultItemAnimator());

        FilterlistAdapter filterlistAdapter = new FilterlistAdapter(SampleActivity.this, filterlistModel);

        rv_categ.setAdapter(filterlistAdapter);

    }

    private void categlist() {

        if (expandable_layout_categ.isExpanded()) {
            expandable_layout_categ.collapse();
            rv_categ.setVisibility(View.GONE);
            img_arrow_categ.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_categ.expand();
            rv_categ.setVisibility(View.VISIBLE);
            img_arrow_categ.setImageResource(R.drawable.ic_up_arrow);
        }

    }


    private void pricelist() {

        if (expandable_layout_price_range.isExpanded()) {
            expandable_layout_price_range.collapse();
//            rv.setVisibility(View.GONE);
            img_arrow_pr_range.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_price_range.expand();
//            rv_categ.setVisibility(View.VISIBLE);
            img_arrow_pr_range.setImageResource(R.drawable.ic_up_arrow);
        }

    }


    private void ratinglist() {

        if (expandable_layout_rating.isExpanded()) {
            expandable_layout_rating.collapse();
//            rv.setVisibility(View.GONE);
            img_arrow_rate.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_rating.expand();
//            rv_categ.setVisibility(View.VISIBLE);
            img_arrow_rate.setImageResource(R.drawable.ic_up_arrow);
        }

    }


    private void setColor() {

        filterColorlistModels = new ArrayList<>();

        filterColorlistModels.add(new FilterColorlistModel("Red",false,1));

        filterColorlistModels.add(new FilterColorlistModel("Black",false,2));

        filterColorlistModels.add(new FilterColorlistModel("Green",false,3));

        filterColorlistModels.add(new FilterColorlistModel("Blue",false,4));

        rv_color.setLayoutManager(new GridLayoutManager(SampleActivity.this, 2));

        rv_color.setMotionEventSplittingEnabled(false);

        rv_color.setNestedScrollingEnabled(false);

        //int size =3;

        int spanCount = 2; // 3 columns

        int spacing = 0; // 50px

        boolean includeEdge = true;

        rv_color.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rv_color.setItemAnimator(new DefaultItemAnimator());

        FilterColorlistAdapter filterColorlistAdapter = new FilterColorlistAdapter(SampleActivity.this, filterColorlistModels);

        rv_color.setAdapter(filterColorlistAdapter);

    }

    private void colorlist() {

        if (expandable_layout_color.isExpanded()) {
            expandable_layout_color.collapse();
            rv_color.setVisibility(View.GONE);
            img_arrow_color.setImageResource(R.drawable.ic_down_arrow);
        } else {
            expandable_layout_color.expand();
            rv_color.setVisibility(View.VISIBLE);
            img_arrow_color.setImageResource(R.drawable.ic_up_arrow);
        }

    }

    @Override
    public void onClick(View view) {

    }
}