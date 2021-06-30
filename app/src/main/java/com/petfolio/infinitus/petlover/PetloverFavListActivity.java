package com.petfolio.infinitus.petlover;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.petfolio.infinitus.R;
import com.petfolio.infinitus.activity.NotificationActivity;
import com.petfolio.infinitus.adapter.PetLoverSOSAdapter;
import com.petfolio.infinitus.api.APIClient;
import com.petfolio.infinitus.fragmentpetlover.favourites.DoctorFavFragment;
import com.petfolio.infinitus.fragmentpetlover.favourites.SPFavFragment;
import com.petfolio.infinitus.fragmentpetlover.favourites.ShopFavFragment;

import com.petfolio.infinitus.interfaces.SoSCallListener;
import com.petfolio.infinitus.responsepojo.PetLoverDashboardResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetloverFavListActivity extends AppCompatActivity  implements View.OnClickListener, SoSCallListener {

    private String TAG = "PetloverFavListActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_footer)
    View include_petlover_footer;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tablayout)
    TabLayout tabLayout;




    private String active_tag = "1";


    String tag;

    String fromactivity;
    private Dialog dialog;

    private static final int REQUEST_PHONE_CALL =1 ;
    private String sosPhonenumber;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.include_petlover_header)
    View include_petlover_header;


    /* Petlover Bottom Navigation */
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_home)
    RelativeLayout rl_home;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_home)
    TextView title_home;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_home)
    ImageView img_home;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_care)
    RelativeLayout rl_care;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_care)
    TextView title_care;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_care)
    ImageView img_care;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_service)
    RelativeLayout rl_service;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_serv)
    TextView title_serv;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_serv)
    ImageView img_serv;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_shop)
    RelativeLayout rl_shop;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_shop)
    TextView title_shop;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_shop)
    ImageView img_shop;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_comn)
    RelativeLayout rl_comn;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.title_community)
    TextView title_community;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_community)
    ImageView img_community;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_homes)
    RelativeLayout rl_homes;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_services)
    RelativeLayout rl_services;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_shops)
    RelativeLayout rl_shops;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_cares)
    RelativeLayout rl_cares;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_comns)
    RelativeLayout rl_comns;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.root_nav)
    LinearLayout root_nav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petlover_fav_list);

        ButterKnife.bind(this);
        Log.w(TAG,"onCreate");

        ImageView img_back = include_petlover_header.findViewById(R.id.img_back);
        ImageView img_sos = include_petlover_header.findViewById(R.id.img_sos);
        ImageView img_notification = include_petlover_header.findViewById(R.id.img_notification);
        ImageView img_cart = include_petlover_header.findViewById(R.id.img_cart);
        ImageView img_profile = include_petlover_header.findViewById(R.id.img_profile);
        TextView toolbar_title = include_petlover_header.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.my_favourites));

        img_sos.setVisibility(View.GONE);
        img_cart.setVisibility(View.GONE);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        img_back.setOnClickListener(v -> onBackPressed());

        img_sos.setOnClickListener(this);
        img_notification.setOnClickListener(this);
        img_cart.setOnClickListener(this);
        img_profile.setOnClickListener(this);


        root_nav.setBackgroundResource(R.drawable.nav_home);
        rl_homes.setVisibility(View.VISIBLE);
        rl_cares.setVisibility(View.INVISIBLE);
        rl_services.setVisibility(View.INVISIBLE);
        rl_shops.setVisibility(View.INVISIBLE);
        rl_comns.setVisibility(View.INVISIBLE);
        setMargins(rl_homes,0,0,0,0);
        setMargins(rl_cares,0,0,0,0);
        setMargins(rl_shops,0,0,0,0);
        setMargins(rl_services,0,0,0,0);
        setMargins(rl_comns,0,0,0,0);
        rl_home.setVisibility(View.INVISIBLE);
        rl_shop.setVisibility(View.VISIBLE);
        rl_service.setVisibility(View.VISIBLE);
        rl_care.setVisibility(View.VISIBLE);
        rl_comn.setVisibility(View.VISIBLE);
        title_home.setVisibility(View.INVISIBLE);
        img_home.setVisibility(View.INVISIBLE);
        title_care.setVisibility(View.VISIBLE);
        img_care.setVisibility(View.VISIBLE);
        title_serv.setVisibility(View.VISIBLE);
        img_serv.setVisibility(View.VISIBLE);
        title_shop.setVisibility(View.VISIBLE);
        img_shop.setVisibility(View.VISIBLE);
        title_community.setVisibility(View.VISIBLE);
        img_community.setVisibility(View.VISIBLE);
        title_care.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_care.setImageResource(R.drawable.grey_care);
        title_serv.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_serv.setImageResource(R.drawable.grey_servc);
        title_shop.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_shop.setImageResource(R.drawable.grey_shop);
        title_community.setTextColor(getResources().getColor(R.color.darker_grey_new,getTheme()));
        img_community.setImageResource(R.drawable.grey_community);
        rl_home.setOnClickListener(this);

        rl_care.setOnClickListener(this);

        rl_service.setOnClickListener(this);

        rl_shop.setOnClickListener(this);

        rl_comn.setOnClickListener(this);


        rl_homes.setOnClickListener(this);

        rl_cares.setOnClickListener(this);

        rl_services.setOnClickListener(this);

        rl_shops.setOnClickListener(this);

        rl_comns.setOnClickListener(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DoctorFavFragment(), "Doctor");
        adapter.addFragment(new SPFavFragment(), "Service");
        adapter.addFragment(new ShopFavFragment(), "Shop");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(PetloverFavListActivity.this, PetLoverDashboardActivity.class);
        startActivity(i);
        finish();
    }

 
    public void callDirections(String tag){
        Intent intent = new Intent(getApplicationContext(), PetLoverDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_sos:
                showSOSAlert(APIClient.sosList);
                break;
            case R.id.img_notification:
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                break;
            case R.id.img_cart:
                break;
            case R.id.img_profile:
                Intent intent = new Intent(getApplicationContext(),PetLoverProfileScreenActivity.class);
                intent.putExtra("fromactivity",TAG);
                startActivity(intent);
                break;

            case R.id.rl_homes:
                callDirections("1");
                break;

            case R.id.rl_home:
                callDirections("1");
                break;

            case R.id.rl_shops:
                callDirections("2");
                break;

            case R.id.rl_shop:
                callDirections("2");
                break;

            case R.id.rl_services:
                callDirections("3");
                break;

            case R.id.rl_service:
                callDirections("3");
                break;

            case R.id.rl_cares:
                callDirections("4");
                break;

            case R.id.rl_care:
                callDirections("4");
                break;

            case R.id.rl_comns:
                callDirections("5");
                break;

            case R.id.rl_comn:
                callDirections("5");
                break;

        }

    }

    @Override
    public void soSCallListener(long phonenumber) {
        if(phonenumber != 0){
            sosPhonenumber = String.valueOf(phonenumber);
        }

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    private void showSOSAlert(List<PetLoverDashboardResponse.DataBean.SOSBean> sosList) {

        try {

            dialog = new Dialog(PetloverFavListActivity.this);
            dialog.setContentView(R.layout.sos_popup_layout);
            RecyclerView rv_sosnumbers = (RecyclerView)dialog.findViewById(R.id.rv_sosnumbers);
            Button btn_call = (Button)dialog.findViewById(R.id.btn_call);
            TextView txt_no_records = (TextView)dialog.findViewById(R.id.txt_no_records);
            ImageView img_close = (ImageView)dialog.findViewById(R.id.img_close);
            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            if(sosList != null && sosList.size()>0){
                rv_sosnumbers.setVisibility(View.VISIBLE);
                btn_call.setVisibility(View.VISIBLE);
                txt_no_records.setVisibility(View.GONE);
                rv_sosnumbers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rv_sosnumbers.setItemAnimator(new DefaultItemAnimator());
                PetLoverSOSAdapter petLoverSOSAdapter = new PetLoverSOSAdapter(getApplicationContext(), sosList,this);
                rv_sosnumbers.setAdapter(petLoverSOSAdapter);
            }else{
                rv_sosnumbers.setVisibility(View.GONE);
                btn_call.setVisibility(View.GONE);
                txt_no_records.setVisibility(View.VISIBLE);
                txt_no_records.setText("No phone numbers");

            }

            btn_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(PetloverFavListActivity.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                    }
                    else
                    {
                        gotoPhone();
                    }

                }
            });



            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();


        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }




    }
    private void gotoPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + sosPhonenumber));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

    private void setMargins(RelativeLayout rl_layout, int i, int i1, int i2, int i3) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)rl_layout.getLayoutParams();
        params.setMargins(i, i1, i2, i3);
        rl_layout.setLayoutParams(params);
    }

}