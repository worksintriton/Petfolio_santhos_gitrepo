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
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.RegisterFragmentPagerAdapter;
import com.triton.bertsproject.retailer.ProductDetailDescriptionActivity;
import com.triton.bertsproject.retailer.RetailerCartActivity;
import com.triton.bertsproject.retailer.RetailerDashboardActivity;
import com.triton.bertsproject.retailer.RetailerProductListActivity;
import com.triton.bertsproject.retailer.RetailerProductListBasedOnCategActivity;
import com.triton.bertsproject.retailer.RetailerProductListBasedOnMakeActivity;
import com.triton.bertsproject.retailer.SearchProductListActivity;

import org.json.JSONException;
import org.json.JSONObject;

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

    String brand_id,brand_name,parent_id,subcategid,categ_name,subcategname,make_id,make_name,model_id,model_name, prod_id,prod_name;;

    String search_text , quantity, unit_price;

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

            fromactivity = extras.getString("fromactivity");

            prod_id = extras.getString("prod_id");

            //prod_id = "2";

            prod_name = extras.getString("prod_name");

            brand_id = extras.getString("brand_id");

            brand_name = extras.getString("brand_name");

            parent_id = extras.getString("parent_id");

            categ_name = extras.getString("categ_name");

            subcategid = extras.getString("subcategid");

            subcategname = extras.getString("subcategname");

            make_id = extras.getString("make_id");

            make_name = extras.getString("make_name");

            model_id = extras.getString("model_id");

            model_name = extras.getString("model_name");

            quantity = extras.getString("quantity");

            unit_price = extras.getString("unit_price");

            search_text = extras.getString("search_text");

            Log.w(TAG,"brand_id "+brand_id+"brand_name "+ brand_name+"parent_id : "+parent_id+ "categ_name : "+categ_name+"subcategid :" +subcategid

                    + "subcategname : "+subcategname +

                    "make_id : "+make_id +"make_name :" +make_name +"model_id :" +model_id

                    + "model_name : "+model_name+ "quantity : "+quantity+ "unit_price : "+unit_price+

                    "search_text : "+search_text+ "fromactivity :" +fromactivity);

        }
        Log.w(TAG, "fromactivity "+fromactivity);

        img_back.setOnClickListener(v -> {

            onBackPressed();


        });

        JSONObject data = new JSONObject();
        try {
            data.put("prod_id", prod_id);
            data.put("prod_name", prod_name);
            data.put("brand_id", brand_id);
            data.put("brand_name", brand_name);
            data.put("parent_id", parent_id);
            data.put("categ_name", categ_name);
            data.put("subcategid", subcategid);
            data.put("LastName", subcategid);
            data.put("make_id", make_id);
            data.put("make_name", make_name);
            data.put("model_id", model_id);
            data.put("model_name", model_name);
            data.put("search_text", search_text);
            data.put("quantity", quantity);
            data.put("unit_price", unit_price);

            Log.w(TAG, "Data" + new Gson().toJson(data));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Create an adapter that knows which fragment should be shown on each page
        RegisterFragmentPagerAdapter adapter = new RegisterFragmentPagerAdapter(this, getSupportFragmentManager(),fromactivity,data);

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

    public void callDirections(String tag){
        Intent intent = new Intent(RegisterActivity.this,RetailerDashboardActivity.class);
        intent.putExtra("tag",tag);
        intent.putExtra("fromactivity",fromactivity);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {

        if(fromactivity!=null) {

            if (fromactivity.equals("HomeFragment")) {

                callDirections("1");

                Animatoo.animateSwipeRight(context);

            }

            else if(fromactivity.equals("MyGarageFragment")){

                callDirections("2");

            }

            else if(fromactivity.equals("ProfileFragment")){

                callDirections("5");
            }

            else if(fromactivity.equals("RetailerCartActivity")){

                gotoCartActivity();
            }

            else if(fromactivity.equals("SearchProdListActivity")){

                gotoSearchProductListActivity();
            }

            else if(fromactivity.equals("ProductDetailDescriptionActivity")){

                gotoProductDetailDescriptionActivity();
            }

            else if(fromactivity.equals("RetailerProductListActivity")){

                gotoBrandProductListActivity();

            }

            else if(fromactivity.equals("RetailerProductListBasedOnCategActivity")){

                gotoCategoryProductListActivity();
            }

            else if(fromactivity.equals("RetailerProductListBasedOnMakeActivity")){

                gotoMakeProductListActivity();
            }

            else  {

                Intent intent = new Intent(RegisterActivity.this,RetailerDashboardActivity.class);
                intent.putExtra("fromactivity",TAG);
                startActivity(intent);
                finish();
                Animatoo.animateSwipeRight(context);

            }
        }
        else  {

            Intent intent = new Intent(RegisterActivity.this,RetailerDashboardActivity.class);
            intent.putExtra("fromactivity",TAG);
            startActivity(intent);
            finish();
            Animatoo.animateSwipeRight(context);

        }
        }

    private void gotoMakeProductListActivity() {

        Intent intent = new Intent(RegisterActivity.this, RetailerProductListBasedOnMakeActivity.class);

        intent.putExtra("make_id",make_id);

        intent.putExtra("make_name",make_name);

        intent.putExtra("model_id", model_id);

        intent.putExtra("model_name",model_name);

        intent.putExtra("fromactivity",TAG);

        startActivity(intent);

        finish();

    }
    private void gotoCategoryProductListActivity() {

        Intent intent = new Intent(RegisterActivity.this, RetailerProductListBasedOnCategActivity.class);

        intent.putExtra("parent_id",parent_id);

        intent.putExtra("categ_name",categ_name);

        intent.putExtra("subcategid",subcategid);

        intent.putExtra("subcategname",subcategname);

        intent.putExtra("fromactivity",TAG);

        startActivity(intent);

        finish();

    }
    private void gotoBrandProductListActivity() {

        Intent intent = new Intent(RegisterActivity.this, RetailerProductListActivity.class);

        intent.putExtra("brand_id",brand_id);

        intent.putExtra("brand_name",brand_name);

        intent.putExtra("fromactivity",TAG);

        startActivity(intent);

        finish();

    }

    private void gotoCartActivity() {

        Intent intent = new Intent(RegisterActivity.this, RetailerCartActivity.class);

        intent.putExtra("fromactivity",TAG);

        intent.putExtra("prod_id",prod_id);

        intent.putExtra("prod_name",prod_name);

        intent.putExtra("brand_id",brand_id);

        intent.putExtra("brand_name",brand_name);

        intent.putExtra("parent_id",parent_id);

        intent.putExtra("categ_name",categ_name);

        intent.putExtra("subcategid",subcategid);

        intent.putExtra("subcategname",subcategname);

        intent.putExtra("make_id",make_id);

        intent.putExtra("make_name",make_name);

        intent.putExtra("model_id", model_id);

        intent.putExtra("model_name",model_name);

        intent.putExtra("search_text",search_text);

        startActivity(intent);

        finish();

    }

    private void gotoSearchProductListActivity() {

        Intent intent = new Intent(RegisterActivity.this, SearchProductListActivity.class);

        intent.putExtra("fromactivity",TAG);

        intent.putExtra("search_text",search_text);

        intent.putExtra("prod_id",prod_id);

        intent.putExtra("quantity",quantity);

        intent.putExtra("unit_price",unit_price);

        startActivity(intent);

        finish();
    }

    private void gotoProductDetailDescriptionActivity() {

        Intent intent = new Intent(RegisterActivity.this, ProductDetailDescriptionActivity.class);

        intent.putExtra("prod_id",prod_id);

        intent.putExtra("prod_name",prod_name);

        intent.putExtra("brand_id",brand_id);

        intent.putExtra("brand_name",brand_name);

        intent.putExtra("parent_id",parent_id);

        intent.putExtra("categ_name",categ_name);

        intent.putExtra("subcategid",subcategid);

        intent.putExtra("subcategname",subcategname);

        intent.putExtra("make_id",make_id);

        intent.putExtra("model_id", model_id);

        intent.putExtra("model_name",model_name);

        intent.putExtra("fromactivity",TAG);

        intent.putExtra("search_text",search_text);

        startActivity(intent);

        finish();
    }
}