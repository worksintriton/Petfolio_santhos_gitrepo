package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.LoginActivity;
import com.triton.bertsproject.activities.RegisterActivity;
import com.triton.bertsproject.adapter.BrandListAdapter;
import com.triton.bertsproject.adapter.ReviewCommentsistAdapter;
import com.triton.bertsproject.adapter.ViewPagerProductDetailsAdapter;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.model.RetailerProductlistModel;
import com.triton.bertsproject.requestpojo.AddToCartRequest;
import com.triton.bertsproject.requestpojo.HomepageDashboardRequest;
import com.triton.bertsproject.requestpojo.HomepageDashboardResponse;
import com.triton.bertsproject.requestpojo.ProductDetailRequest;
import com.triton.bertsproject.responsepojo.AddToCartResponse;
import com.triton.bertsproject.responsepojo.ProductDetailRespone;
import com.triton.bertsproject.sessionmanager.Connectivity;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.GridSpacingItemDecoration;
import com.triton.bertsproject.utils.RestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.VISIBLE;

public class ProductDetailDescriptionActivity extends AppCompatActivity {

    Context context = ProductDetailDescriptionActivity.this;

    private final static String TAG = "ProductDetailDescriptionActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_addcart)
    Button btn_addcart;

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
    @BindView(R.id.txt_product_name)
    TextView txt_product_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_parts_no)
    TextView txt_parts_no;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_star)
    RelativeLayout rl_star;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_total_reviews)
    TextView txt_total_reviews;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_price)
    TextView txt_price;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_add_minus)
    LinearLayout ll_add_minus;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_minus)
    ImageView img_minus;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_count)
    TextView txt_count;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_plus)
    ImageView img_plus;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_text_fit)
    LinearLayout ll_text_fit;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_prod_desc)
    CardView cv_prod_desc;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_prod_desc)
    TextView txt_prod_desc;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_manufacturer)
    TextView txt_manufacturer;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_brand)
    TextView txt_brand;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_model)
    TextView txt_model;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_weight)
    TextView txt_weight;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_dimen)
    TextView txt_dimen;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_vehicle_fit)
    CardView cv_vehicle_fit;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_quest_ans)
    CardView cv_quest_ans;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_star_review)
    CardView cv_star_review;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_star_review)
    LinearLayout ll_star_review;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_total_rating_calc)
    TextView txt_total_rating_calc;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_review_countings)
    TextView txt_review_countings;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_review_ratings)
    RecyclerView rv_review_ratings;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pager)
    ViewPager viewPager;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tabDots)
    TabLayout tabLayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_desc)
    ImageView img_desc;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_prod_info)
    ImageView img_prod_info;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_cust_revw)
    ImageView img_cust_revw;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_product_info)
    LinearLayout ll_product_info;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_cust_revw)
    LinearLayout ll_cust_revw;



    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img1)
    ImageView hand_img1;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img2)
    ImageView hand_img2;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img3)
    ImageView hand_img3;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img4)
    ImageView hand_img4;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img5)
    ImageView hand_img5;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img11)
    ImageView hand_img11;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img21)
    ImageView hand_img21;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img31)
    ImageView hand_img31;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img41)
    ImageView hand_img41;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.hand_img51)
    ImageView hand_img51;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progress_bar_5star)
    RoundedHorizontalProgressBar progress_bar_5star;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_5star_perc)
    TextView txt_5star_perc;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progress_bar_4star)
    RoundedHorizontalProgressBar progress_bar_4star;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_4star_perc)
    TextView txt_4star_perc;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progress_bar_3star)
    RoundedHorizontalProgressBar progress_bar_3star;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_3star_perc)
    TextView txt_3star_perc;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progress_bar_2star)
    RoundedHorizontalProgressBar progress_bar_2star;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_2star_perc)
    TextView txt_2star_perc;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progress_bar_1star)
    RoundedHorizontalProgressBar progress_bar_1star;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_1star_perc)
    TextView txt_1star_perc;



    String fromactivity;

    ProductDetailRespone.DataBean dataBeanList;

    ProductDetailRespone.DataBean.ProductsBean prdouctsBean ;

    ProductDetailRespone.DataBean.ProductsBean.ReviewsRatingsPercentageBean reviewsRatingsPercentageBean;

    List<ProductDetailRespone.DataBean.ProductsBean.ReviewsDetailsBean> reviewsDetailsBeanList ;

    List<String> imageList = new ArrayList();

    String prod_id,prod_name;

//    private DD4YouNetReceiver dd4YouNetReceiver;

    private DD4YouConfig dd4YouConfig;

    AlertDialog alertDialog;

    String user_id,product_id,price;

//    private DD4YouNetReceiver dd4YouNetReceiver;

    SessionManager sessionManager;

    String brand_id,brand_name,parent_id,categ_name,subcategid,subcategname,make_id,make_name,model_id,model_name;

    int minteger = 1;

    boolean isdesc = true,isprodInfo = true,iscustrvw = true;

    Connectivity connectivity;

    String value,search_text;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cart_count)
    TextView txt_cart_count;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rlcart)
    RelativeLayout rlcart;

    String cart_count ="0";

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_description);

        ButterKnife.bind(this);

        Log.w("Oncreate", TAG);

        connectivity = new Connectivity();

        value = connectivity.getData(ProductDetailDescriptionActivity.this,"ProductDetailList");

        Log.w(TAG,"value "+value);

        if(value!=null&&!value.isEmpty()) {

            fromactivity = value;

            Log.w(TAG,"condition:  true");

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                prod_id = extras.getString("prod_id");

                //prod_id = "2";
                Log.w(TAG,"condition :  true");

                prod_name = extras.getString("prod_name");

                categ_name = extras.getString("categ_name");

                brand_id = extras.getString("brand_id");

                brand_name = extras.getString("brand_name");

                parent_id = extras.getString("parent_id");

                subcategid = extras.getString("subcategid");

                subcategname = extras.getString("subcategname");

                make_id = extras.getString("make_id");

                make_name = extras.getString("make_name");

                model_id = extras.getString("model_id");

                model_name = extras.getString("model_name");

                search_text = extras.getString("search_text");

            }
            Log.w(TAG,"Connectivity fromactivity : "+fromactivity + "brand_id : "+brand_id + "brand_name : "+brand_name+"parent_id : "+parent_id+"categ_name : "+categ_name+ "subcategid :" +subcategid

                    + "subcategname : "+subcategname + "make_id : "+make_id + "model_id :" +model_id

                    + "model_name : "+model_name);
        }

        else {

            Log.w(TAG,"condition :  false");

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                fromactivity = extras.getString("fromactivity");

                prod_id = extras.getString("prod_id");

                //prod_id = "2";

                prod_name = extras.getString("prod_name");

                categ_name = extras.getString("categ_name");

                brand_id = extras.getString("brand_id");

                brand_name = extras.getString("brand_name");

                parent_id = extras.getString("parent_id");

                subcategid = extras.getString("subcategid");

                subcategname = extras.getString("subcategname");

                make_id = extras.getString("make_id");

                model_id = extras.getString("model_id");

                model_name = extras.getString("model_name");

                search_text = extras.getString("search_text");

                Log.w(TAG,"Connectivity fromactivity : "+fromactivity +"brand_id : "+brand_id + "brand_name : "+brand_name+"parent_id : "+parent_id+ "subcategid :" +subcategid

                        + "subcategname : "+subcategname + "make_id : "+make_id + "model_id :" +model_id

                        + "model_name : "+model_name);

            }
        }



/*

        if(prod_name!=null&&!prod_name.isEmpty()){

            txt_toolbar_title.setText(prod_name);

        }
*/

        dd4YouConfig = new DD4YouConfig(this);

        //registerBroadcastReceiver();

        sessionManager=new SessionManager(this);

        if(sessionManager.isLoggedIn()){

            HashMap<String, String> user = sessionManager.getProfileDetails();

            user_id = user.get(SessionManager.KEY_ID);

            rlcart.setOnClickListener(v -> {

               gotoCartActivity();

            });


            cart_count = connectivity.getData(context,"Cart_Count");

            Log.w(TAG,"cart_count "+cart_count);

            if(cart_count!=null&&!cart_count.equals("0")){

                txt_cart_count.setText(""+cart_count);
            }

            else {

                txt_cart_count.setVisibility(View.GONE);
            }

        }

        else {

            user_id  = "";
            txt_cart_count.setVisibility(View.GONE);
        }

        txt_product_name.setVisibility(View.GONE);

        txt_parts_no.setVisibility(View.GONE);

        rl_star.setVisibility(View.GONE);

        txt_total_reviews.setVisibility(View.GONE);

        txt_price.setVisibility(View.GONE);

        ll_add_minus.setVisibility(View.GONE);

        img_minus.setVisibility(View.GONE);

        txt_count.setVisibility(View.GONE);

        img_plus.setVisibility(View.GONE);

        ll_text_fit.setVisibility(View.GONE);

        cv_prod_desc.setVisibility(View.GONE);

        txt_prod_desc.setVisibility(View.GONE);

        txt_manufacturer.setVisibility(View.GONE);

        txt_brand.setVisibility(View.GONE);

        txt_model.setVisibility(View.GONE);

        txt_weight.setVisibility(View.GONE);

        txt_dimen.setVisibility(View.GONE);

        cv_vehicle_fit.setVisibility(View.GONE);

        cv_quest_ans.setVisibility(View.GONE);

        cv_star_review.setVisibility(View.GONE);

        ll_star_review.setVisibility(View.GONE);

        txt_total_rating_calc.setVisibility(View.GONE);

        txt_review_countings.setVisibility(View.GONE);

        rv_review_ratings.setVisibility(View.GONE);

        viewPager.setVisibility(View.GONE);

        tabLayout.setVisibility(View.GONE);

        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallproductsListResponseCall();

        }

        else
        {
            callnointernet();

        }

        img_back.setOnClickListener(v -> {

            onBackPressed();
        });



    }

    private void gotoCartActivity() {

        Intent intent = new Intent(ProductDetailDescriptionActivity.this, RetailerCartActivity.class);

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

        connectivity.storeData(ProductDetailDescriptionActivity.this,"ProductDetailList",fromactivity);

        startActivity(intent);

        finish();

    }


    @SuppressLint("LongLogTag")
    @Override
    public void onBackPressed() {

        if(fromactivity!=null){

            if(fromactivity.equals("RetailerProductListActivity")){

                Intent intent = new Intent(ProductDetailDescriptionActivity.this,RetailerProductListActivity.class);

                intent.putExtra("brand_id",brand_id);

                intent.putExtra("brand_name",brand_name);

                intent.putExtra("prod_id",prod_id);

                intent.putExtra("prod_name",prod_name);

                intent.putExtra("fromactivity",TAG);

                connectivity.clearData(ProductDetailDescriptionActivity.this,"ProductDetailList");

                String value = connectivity.getData(ProductDetailDescriptionActivity.this,"ProductDetailList");

                Log.w(TAG,"value "+value);

                startActivity(intent);

                finish();

                Animatoo.animateSwipeLeft(context);
            }

            else if(fromactivity.equals("RetailerProductListBasedOnCategActivity")){

                Intent intent = new Intent(ProductDetailDescriptionActivity.this,RetailerProductListBasedOnCategActivity.class);

                intent.putExtra("parent_id",parent_id);

                intent.putExtra("categ_name",categ_name);

                intent.putExtra("subcategid",subcategid);

                intent.putExtra("subcategname",subcategname);

                intent.putExtra("prod_id",prod_id);

                intent.putExtra("prod_name",prod_name);

                intent.putExtra("fromactivity",TAG);

                connectivity.clearData(ProductDetailDescriptionActivity.this,"ProductDetailList");

                value = connectivity.getData(ProductDetailDescriptionActivity.this,"ProductDetailList");

                Log.w(TAG,"value "+value);

                startActivity(intent);

                finish();

                Animatoo.animateSwipeLeft(context);

            }

            else if(fromactivity.equals("RetailerProductListBasedOnMakeActivity")){

                Intent intent = new Intent(ProductDetailDescriptionActivity.this,RetailerProductListBasedOnMakeActivity.class);

                intent.putExtra("make_id",make_id);

                intent.putExtra("model_id", model_id);

                intent.putExtra("model_id",model_name);

                intent.putExtra("prod_id",prod_id);

                intent.putExtra("prod_name",prod_name);

                intent.putExtra("fromactivity",TAG);

                connectivity.clearData(ProductDetailDescriptionActivity.this,"ProductDetailList");

                value = connectivity.getData(ProductDetailDescriptionActivity.this,"ProductDetailList");

                Log.w(TAG,"value "+value);

                startActivity(intent);

                finish();

                Animatoo.animateSwipeLeft(context);
            }

            else if(fromactivity.equals("SearchProductListActivity")){

                Intent intent = new Intent(ProductDetailDescriptionActivity.this,SearchProductListActivity.class);

                intent.putExtra("search_text",search_text);

                intent.putExtra("fromactivity",TAG);

                connectivity.clearData(ProductDetailDescriptionActivity.this,"ProductDetailList");

                value = connectivity.getData(ProductDetailDescriptionActivity.this,"ProductDetailList");

                Log.w(TAG,"value "+value);

                startActivity(intent);

                finish();

                Animatoo.animateSwipeLeft(context);
            }

            else {

                Intent intent = new Intent(ProductDetailDescriptionActivity.this, RetailerDashboardActivity.class);
                intent.putExtra("fromactivity",TAG);
                connectivity.clearData(ProductDetailDescriptionActivity.this,"ProductDetailList");
                value = connectivity.getData(ProductDetailDescriptionActivity.this,"ProductDetailList");

                Log.w(TAG,"value "+value);
                startActivity(intent);
                finish();
            }
        }

        else {
            Intent intent = new Intent(ProductDetailDescriptionActivity.this, RetailerDashboardActivity.class);
            intent.putExtra("fromactivity",TAG);
            String value = connectivity.getData(ProductDetailDescriptionActivity.this,"ProductDetailList");

            Log.w(TAG,"value "+value);
            connectivity.clearData(ProductDetailDescriptionActivity.this,"ProductDetailList");
            startActivity(intent);
            finish();

        }

    }


    @SuppressLint("LongLogTag")
    private void fetchallproductsListResponseCall() {

        spin_kit_loadingView.setVisibility(VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ProductDetailRespone> call = apiInterface.fetchproductdetailsResponseCall(RestUtils.getContentType(), ProductDetailRequest());
        Log.w(TAG,"ProductDetailRespone url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<ProductDetailRespone>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<ProductDetailRespone> call, @NonNull Response<ProductDetailRespone> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"ProductDetailRespone" + new Gson().toJson(response.body()));

                        prdouctsBean = response.body().getData().getProducts();

                        Log.w(TAG,"prdouctsBeanList" + new Gson().toJson(prdouctsBean));

                        if(prdouctsBean != null){

                            setView(prdouctsBean);
                        }

                        else {

                            showErrorLoading(response.body().getMessage());
                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }



                }

            }


            @Override
            public void onFailure(@NonNull Call<ProductDetailRespone> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"ProductDetailRespone flr"+t.getMessage());

            }
        });



    }

    @SuppressLint("LongLogTag")
    private ProductDetailRequest ProductDetailRequest() {

        /*
         * MODE : DETAIL
         * PRODUCT_ID : 2
         */

        ProductDetailRequest ProductDetailRequest = new ProductDetailRequest();
        ProductDetailRequest.setPRODUCT_ID(prod_id);
        ProductDetailRequest.setMODE("DETAIL");

        Log.w(TAG,"ProductDetailRequest "+ new Gson().toJson(ProductDetailRequest));
        return ProductDetailRequest;
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(ProductDetailDescriptionActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(ProductDetailDescriptionActivity.this,ProductDetailDescriptionActivity.class));
            finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @SuppressLint("LongLogTag")
    private void setView(ProductDetailRespone.DataBean.ProductsBean prdouctsBean) {

        txt_product_name.setVisibility(VISIBLE);

        txt_parts_no.setVisibility(VISIBLE);

        rl_star.setVisibility(VISIBLE);

        txt_total_reviews.setVisibility(VISIBLE);

        txt_price.setVisibility(VISIBLE);

        ll_add_minus.setVisibility(VISIBLE);

        img_minus.setVisibility(VISIBLE);

        txt_count.setVisibility(VISIBLE);

        img_plus.setVisibility(VISIBLE);

        ll_text_fit.setVisibility(VISIBLE);

        cv_prod_desc.setVisibility(VISIBLE);

        txt_prod_desc.setVisibility(VISIBLE);

        txt_manufacturer.setVisibility(VISIBLE);

        txt_brand.setVisibility(VISIBLE);

        txt_model.setVisibility(VISIBLE);

        txt_weight.setVisibility(VISIBLE);

        txt_dimen.setVisibility(VISIBLE);

        cv_vehicle_fit.setVisibility(View.GONE);

        cv_quest_ans.setVisibility(View.GONE);

        cv_star_review.setVisibility(VISIBLE);

        ll_star_review.setVisibility(VISIBLE);

        txt_total_rating_calc.setVisibility(VISIBLE);

        txt_review_countings.setVisibility(VISIBLE);

        rv_review_ratings.setVisibility(VISIBLE);

        viewPager.setVisibility(VISIBLE);

        tabLayout.setVisibility(VISIBLE);

        if(prdouctsBean.getTitle()!=null&&!prdouctsBean.getTitle().isEmpty()) {

            txt_product_name.setText(prdouctsBean.getTitle());
        }

        if(prdouctsBean.getPart_number()!=null&&!prdouctsBean.getPart_number().isEmpty()){

            txt_parts_no.setText("Part No: "+prdouctsBean.getPart_number());
        }

        Log.w(TAG,"prdouctsBeanList getRating" + prdouctsBean.getRating());

        int starcount = Integer.parseInt(prdouctsBean.getRating());

        if(starcount == 1){
            hand_img1.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img2.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img3.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img4.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img5.setBackgroundResource(R.drawable.ic_star_empty);
        } else if(starcount == 2){
            hand_img1.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img2.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img3.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img4.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img5.setBackgroundResource(R.drawable.ic_star_empty);
        }else if(starcount == 3){
            hand_img1.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img2.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img3.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img4.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img5.setBackgroundResource(R.drawable.ic_star_empty);
        }else if(starcount == 4){
            hand_img1.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img2.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img3.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img4.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img5.setBackgroundResource(R.drawable.ic_star_empty);
        } else if(starcount == 5){
            hand_img1.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img2.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img3.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img4.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img5.setBackgroundResource(R.drawable.ic_star_filled);
        }
        else {
            hand_img1.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img2.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img3.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img4.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img5.setBackgroundResource(R.drawable.ic_star_empty);

        }


        Log.w(TAG,"prdouctsBeanList getReviews_ratings" + prdouctsBean.getReviews_ratings());

        if(prdouctsBean.getReviews_ratings()!=0){

            txt_total_reviews.setText("( "+prdouctsBean.getReviews_ratings()+ " Reviews)");
        }

        else {

            txt_total_reviews.setText("( "+0+ " Reviews)");
        }

        Log.w(TAG,"prdouctsBeanList getImages" + new Gson().toJson(prdouctsBean.getImages()));

        if(prdouctsBean.getImages()!=null&&!prdouctsBean.getImages().isEmpty()){

            if(prdouctsBean.getImages().size()>0){

                for(int i = 0; i<prdouctsBean.getImages().size();i++){

                    imageList.add(prdouctsBean.getImages().get(i).getImage_default());
                }
            }
        }

        else {

            imageList.add(APIClient.BASE_IMAGE_URL);
        }

        if(imageList.size()>0){

            viewpageData(imageList);
        }

        if(prdouctsBean.getPrice()!=null&&!prdouctsBean.getPrice().isEmpty()){

            txt_price.setText("$ "+prdouctsBean.getPrice());

            price = prdouctsBean.getPrice();
        }

        if(prdouctsBean.getDescription()!=null&&!prdouctsBean.getDescription().isEmpty()){

            txt_prod_desc.setText(prdouctsBean.getDescription());
        }

//        if(prdouctsBean.getBrand_id()!=null&&!prdouctsBean.getDescription().isEmpty()){
//
//            txt_brand.setText(prdouctsBean.getDescription());
//        }

        txt_manufacturer.setText("");

        txt_brand.setText("");

        txt_model.setText("");

        txt_dimen.setText("");

        Log.w(TAG,"prdouctsBeanList getWeight" + prdouctsBean.getWeight());

        if(prdouctsBean.getWeight()!=null&&!prdouctsBean.getWeight().isEmpty()){

            txt_weight.setText("( "+prdouctsBean.getWeight() + " ) pounds");
        }

        Log.w(TAG,"prdouctsBeanList getReviews ratings" + prdouctsBean.getReviews_ratings());

        if(prdouctsBean.getReviews_ratings()!=0){

            txt_total_rating_calc.setText(" "+prdouctsBean.getReviews_ratings() + " Out of 5");
        }

        else {

            txt_total_rating_calc.setText(" "+0+ " Out of 5");
        }

        int starcountReview = prdouctsBean.getReviews_ratings();

        Log.w(TAG,"starcountReview" + starcountReview);

        if(starcountReview == 1){
            hand_img11.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img21.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img31.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img41.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img51.setBackgroundResource(R.drawable.ic_star_empty);
        } else if(starcountReview == 2){
            hand_img11.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img21.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img31.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img41.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img51.setBackgroundResource(R.drawable.ic_star_empty);
        }else if(starcountReview == 3){
            hand_img11.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img21.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img31.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img41.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img51.setBackgroundResource(R.drawable.ic_star_empty);
        }else if(starcountReview == 4){
            hand_img11.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img21.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img31.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img41.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img51.setBackgroundResource(R.drawable.ic_star_empty);
        } else if(starcountReview == 5){
            hand_img11.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img21.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img31.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img41.setBackgroundResource(R.drawable.ic_star_filled);
            hand_img51.setBackgroundResource(R.drawable.ic_star_filled);
        }
        else if(starcountReview==0){
            hand_img11.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img21.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img31.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img41.setBackgroundResource(R.drawable.ic_star_empty);
            hand_img51.setBackgroundResource(R.drawable.ic_star_empty);

        }

        Log.w(TAG,"prdouctsBeanList getReviews_comments" + prdouctsBean.getReviews_comments());

        if(prdouctsBean.getReviews_comments()!=0){

            txt_review_countings.setText("( "+prdouctsBean.getReviews_comments() + " ) Reviews");
        }
        else {

            txt_review_countings.setText("( "+0+ " ) Reviews");
        }

        Log.w(TAG,"prdouctsBeanList getReviews_details" + new Gson().toJson(prdouctsBean.getReviews_details()));

        reviewsDetailsBeanList = prdouctsBean.getReviews_details();

//        Log.w(TAG,"reviewsDetailsBeanList size" + reviewsDetailsBeanList.size());

        if(reviewsDetailsBeanList!=null&&reviewsDetailsBeanList.size()>0){

            setReviewComments(reviewsDetailsBeanList);
        }

        img_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!txt_count.getText().equals("1")){

                    decreaseInteger();

                }
            }
        });

        img_plus.setOnClickListener(v -> {

            int threshold = Integer.parseInt(prdouctsBean.getQuantity());

            int value = Integer.parseInt(txt_count.getText().toString());

            if(value>threshold) {

               Toasty.warning(getApplicationContext(),"Sorry you cant add beyond quantity",Toasty.LENGTH_LONG).show();

            }
            else {

                increaseInteger();
            }
        });

        btn_addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sessionManager.isLoggedIn()){

                    if(dd4YouConfig.isInternetConnectivity()){

                        addcartlistResponseCall();
                    }

                    else {

                        callnointernet();
                    }
                }

                else {

                    showAlert();
                }
            }
        });

        Log.w(TAG,"prdouctsBeanList getReviews_ratings_percentage" + new Gson().toJson(prdouctsBean.getReviews_ratings_percentage()));

        reviewsRatingsPercentageBean = prdouctsBean.getReviews_ratings_percentage();

        if(reviewsRatingsPercentageBean!=null){

            if(reviewsRatingsPercentageBean.getRating_5()!=0){

                progress_bar_5star.animateProgress(1000, 0, reviewsRatingsPercentageBean.getRating_5());

                txt_5star_perc.setText(""+reviewsRatingsPercentageBean.getRating_5()+" %");
            }

            else {

                progress_bar_5star.animateProgress(1000, 0, 0);

                txt_5star_perc.setText("0"+" %");
            }

            if(reviewsRatingsPercentageBean.getRating_4()!=0){

                progress_bar_4star.animateProgress(1000, 0, reviewsRatingsPercentageBean.getRating_4());

                txt_4star_perc.setText(""+reviewsRatingsPercentageBean.getRating_4()+" %");
            }
            else {

                progress_bar_4star.animateProgress(1000, 0, 0);

                txt_4star_perc.setText("0"+" %");
            }

            if(reviewsRatingsPercentageBean.getRating_3()!=0){

                progress_bar_3star.animateProgress(1000, 0, reviewsRatingsPercentageBean.getRating_3());

                txt_3star_perc.setText(""+reviewsRatingsPercentageBean.getRating_3()+" %");
            }

            else {

                progress_bar_3star.animateProgress(1000, 0, 0);

                txt_3star_perc.setText("0"+" %");
            }

            if(reviewsRatingsPercentageBean.getRating_2()!=0){

                progress_bar_2star.animateProgress(1000, 0, reviewsRatingsPercentageBean.getRating_2());

                txt_2star_perc.setText(""+reviewsRatingsPercentageBean.getRating_2() + " %");
            }
            else {

                progress_bar_2star.animateProgress(1000, 0, 0);

                txt_2star_perc.setText("0"+" %");
            }

            if(reviewsRatingsPercentageBean.getRating_1()!=0){

                progress_bar_1star.animateProgress(1000, 0, reviewsRatingsPercentageBean.getRating_1());

                txt_1star_perc.setText(""+reviewsRatingsPercentageBean.getRating_1() +" %");
            }

            else {

                progress_bar_1star.animateProgress(1000, 0, 0);

                txt_1star_perc.setText("0"+" %");
            }
        }

        img_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isdesc){

                    isdesc = false;

                    txt_prod_desc.setVisibility(View.GONE);

                    img_desc.setImageResource(R.drawable.ic_right_down);
                }

                else {

                    isdesc = true;

                    txt_prod_desc.setVisibility(VISIBLE);

                    img_desc.setImageResource(R.drawable.ic_up_arrow);
                }
            }
        });

        img_prod_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isprodInfo){

                    isprodInfo = false;

                    ll_product_info.setVisibility(View.GONE);

                    img_prod_info.setImageResource(R.drawable.ic_right_down);
                }

                else {

                    isprodInfo = true;

                    ll_product_info.setVisibility(VISIBLE);

                    img_prod_info.setImageResource(R.drawable.ic_up_arrow);
                }
            }
        });

        img_cust_revw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(iscustrvw){

                    iscustrvw = false;

                    ll_cust_revw.setVisibility(View.GONE);

                    img_cust_revw.setImageResource(R.drawable.ic_right_down);
                }

                else {

                    iscustrvw = true;

                    ll_cust_revw.setVisibility(VISIBLE);

                    img_cust_revw.setImageResource(R.drawable.ic_up_arrow);
                }
            }
        });

    }

    @SuppressLint("LongLogTag")
    private void addcartlistResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<AddToCartResponse> call = apiInterface.addcartlistResponseCall(RestUtils.getContentType(),AddToCartRequest());
        Log.w(TAG,"AddToCartResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<AddToCartResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<AddToCartResponse> call, @NonNull Response<AddToCartResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "AddToCartResponse" + new Gson().toJson(response.body()));

                        Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                        usercommonResponseCall();

                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }

                }

            }


            @Override
            public void onFailure(@NonNull Call<AddToCartResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"AddToCartResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private AddToCartRequest AddToCartRequest() {


        /*
         * USER_ID : 541
         * PRODUCT_ID : 2
         * QUANTITY : 1
         * UNIT_PRICE : 50000
         * MODE : ADDTOCART
         */
        String QUANTITY = txt_count.getText().toString();

        AddToCartRequest AddToCartRequest = new AddToCartRequest();
        AddToCartRequest.setUNIT_PRICE(price);
        AddToCartRequest.setQUANTITY(QUANTITY);
        AddToCartRequest.setPRODUCT_ID(prod_id);
        AddToCartRequest.setUSER_ID(user_id);
        AddToCartRequest.setMODE("ADDTOCART");

        Log.w(TAG,"AddToCartRequest "+ new Gson().toJson(AddToCartRequest));
        return AddToCartRequest;
    }


    public void increaseInteger() {
        minteger = minteger + 1;
        display(minteger);

    }public void decreaseInteger() {
        minteger = minteger - 1;
        display(minteger);
    }

    private void display(int number) {

        txt_count.setText("" + number);
    }

    private void setReviewComments(List<ProductDetailRespone.DataBean.ProductsBean.ReviewsDetailsBean> reviewsDetailsBeanList) {

        rv_review_ratings.setLayoutManager(new LinearLayoutManager(ProductDetailDescriptionActivity.this));

        rv_review_ratings.setMotionEventSplittingEnabled(false);

        rv_review_ratings.setNestedScrollingEnabled(true);

        int size =reviewsDetailsBeanList.size();

        rv_review_ratings.setItemAnimator(new DefaultItemAnimator());

        ReviewCommentsistAdapter reviewCommentsistAdapter = new ReviewCommentsistAdapter(ProductDetailDescriptionActivity.this, reviewsDetailsBeanList,size);

        rv_review_ratings.setAdapter(reviewCommentsistAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //    private void registerBroadcastReceiver() {
//        if (dd4YouNetReceiver == null)
//            dd4YouNetReceiver = new DD4YouNetReceiver(rl_root,1000);
//        dd4YouNetReceiver.register(this.getApplicationContext());
//    }
//    private void unregisterBroadcastReceiver() {
//        if (dd4YouNetReceiver != null)        {
//            dd4YouNetReceiver.unregister(this.getApplicationContext());
//        }
//    }
    @Override
    protected void onPause() {
        super.onPause();
//        unregisterBroadcastReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // unregisterBroadcastReceiver();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ProductDetailDescriptionActivity.this);
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoading());


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void hideLoading(){
        try {
            alertDialog.dismiss();
        }catch (Exception ignored){

        }
    }

    private void viewpageData(List<String> product_img) {
        tabLayout.setupWithViewPager(viewPager, true);

        ViewPagerProductDetailsAdapter viewPagerProductDetailsAdapter = new ViewPagerProductDetailsAdapter(getApplicationContext(), product_img);
        viewPager.setAdapter(viewPagerProductDetailsAdapter);
        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == product_img.size()) {
                currentPage = 0;
            }
            viewPager.setCurrentItem(currentPage++, false);
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    private void showAlert() {

        AlertDialog.Builder builder=new AlertDialog.Builder(ProductDetailDescriptionActivity.this);
        builder.setTitle("Alert");
        builder.setMessage("Please Login to add Products");
        builder.setCancelable(false);
        builder.setPositiveButton("Login", (dialogInterface, i) -> {
            Intent intent = new Intent(ProductDetailDescriptionActivity.this, LoginActivity.class);

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

            intent.putExtra("fromactivity",TAG);

            connectivity.storeData(ProductDetailDescriptionActivity.this,"ProductDetailList",fromactivity);

            startActivity(intent);
        });
        builder.setNegativeButton("Sign In", (dialogInterface, i) -> {
            Intent intent = new Intent(ProductDetailDescriptionActivity.this, RegisterActivity.class);

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

            intent.putExtra("fromactivity",TAG);

            connectivity.storeData(ProductDetailDescriptionActivity.this,"ProductDetailList",fromactivity);

            startActivity(intent);


        });
        builder.setNeutralButton("Cancel", (dialogInterface, i) -> {

            dialogInterface.dismiss();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @SuppressLint("LongLogTag")
    private void usercommonResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<HomepageDashboardResponse> call = apiInterface.usercommonResponseCall(RestUtils.getContentType(),HomepageDashboardRequest());
        Log.w(TAG,"HomepageDashboardResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<HomepageDashboardResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<HomepageDashboardResponse> call, @NonNull Response<HomepageDashboardResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(response.body().getData()!=null){

                        if(200==response.body().getCode()) {

                            Log.w(TAG, "HomepageDashboardResponse" + new Gson().toJson(response.body()));

                            cart_count = String.valueOf(response.body().getData().getCart_count());

                            Log.w(TAG, "Cart_Count" + cart_count);

                            if (cart_count!=null&&!cart_count.equals("0"))  {

                                Connectivity connectivity = new Connectivity();

                                connectivity.clearData(context,"Cart_Count");

                                connectivity.storeData(context,"Cart_Count",String.valueOf(cart_count));

                                gotoNextActivity();

                            }

                            else {

                                Connectivity connectivity = new Connectivity();

                                connectivity.clearData(context,"Cart_Count");

                                connectivity.storeData(context,"Cart_Count","0");

                                gotoNextActivity();

                            }


                        }

                        else {

                            cart_count="0";
//                            showErrorLoading(response.body().getMessage());
                            Connectivity connectivity = new Connectivity();

                            connectivity.clearData(context,"Cart_Count");

                            connectivity.storeData(context,"Cart_Count","0");

                            gotoNextActivity();
                        }
                    }

                    else {

                        cart_count="0";

                        txt_cart_count.setVisibility(View.GONE);

                        Connectivity connectivity = new Connectivity();

                        connectivity.clearData(context,"Cart_Count");

                        connectivity.storeData(context,"Cart_Count","0");

                        gotoNextActivity();
                    }

                }

            }


            @Override
            public void onFailure(@NonNull Call<HomepageDashboardResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"HomepageDashboardResponse flr"+t.getMessage());
            }
        });

    }


    @SuppressLint("LongLogTag")
    private HomepageDashboardRequest HomepageDashboardRequest() {

        /*
         * USER_ID : 541
         */


        HomepageDashboardRequest HomepageDashboardRequest = new HomepageDashboardRequest();
        HomepageDashboardRequest.setUSER_ID(user_id);

        Log.w(TAG,"HomepageDashboardRequest "+ new Gson().toJson(HomepageDashboardRequest));
        return HomepageDashboardRequest;
    }


    public void gotoNextActivity(){

        Intent intent = new Intent(ProductDetailDescriptionActivity.this, RetailerCartActivity.class);

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

        intent.putExtra("model_id",model_name);

        intent.putExtra("fromactivity",TAG);

        connectivity.storeData(ProductDetailDescriptionActivity.this,"ProductDetailList",fromactivity);

        startActivity(intent);


    }

}