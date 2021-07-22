package com.triton.bertsproject.retailer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.RegisterActivity;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.customView.CustomEditText;
import com.triton.bertsproject.requestpojo.CreateAddressListRequest;
import com.triton.bertsproject.requestpojo.GetStateRequest;
import com.triton.bertsproject.responsepojo.CreateAddressListResponse;
import com.triton.bertsproject.responsepojo.GetCountryResponse;
import com.triton.bertsproject.responsepojo.GetStateResponse;
import com.triton.bertsproject.sessionmanager.Connectivity;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.RestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingAddressAddActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_name)
    CustomEditText edt_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_phoneno)
    CustomEditText edt_phoneno;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_city)
    CustomEditText edt_city;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtAddress1)
    CustomEditText edtAddress1;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edtAddress2)
    CustomEditText edtAddress2;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_zipcode)
    CustomEditText edt_zipcode;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sw_saveasdefault)
    SwitchCompat sw_saveasdefault;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_saveaddr)
    Button btn_saveaddr;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sp_country)
    Spinner sp_country;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sp_state)
    Spinner sp_state;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_default)
    LinearLayout ll_default;

    private static final String TAG = "ShippingAddressAddActivity";

    DD4YouConfig dd4YouConfig;

    SessionManager sessionManager;

    String userid, name, phoneno, countryid, stateid,city,

    statename = "",countryname = "",zipcode,addrln1,addrln2,zip,set_default="1";

    List<GetCountryResponse.DataBean.CountriesBean> countriesBeanList ;

    List<GetStateResponse.DataBean.StatesBean> statesBeanList ;

    HashMap<String, String> hashMap_Countryid = new HashMap<>();

    HashMap<String, String> hashMap_Stateid = new HashMap<>();

    AlertDialog alertDialog;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    String fromActivity,isdefault;

    String brand_id,brand_name,parent_id,subcategid,subcategname,make_id,model_id,model_name;

    String prod_id,prod_name,shipid;

    String addr_name,address1,state_name,country_name;

    Connectivity connectivity;

    String value,categ_name,make_name,search_text,cart_count;


    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address_add);

        ButterKnife.bind(this);

        Log.w(TAG,"onCreate");

        txt_toolbar_title.setText(R.string.new_addr);

        dd4YouConfig = new DD4YouConfig(ShippingAddressAddActivity.this);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getProfileDetails();

        userid = user.get(SessionManager.KEY_ID);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromActivity = extras.getString("fromactivity");

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

            search_text = extras.getString("search_text");

            Log.w(TAG,"Connectivity fromactivity : "+fromActivity+ "brand_id : "+brand_id + "brand_name : "+brand_name+"parent_id : "+parent_id+ "categ_name : "+categ_name+ "subcategid :" +subcategid

                    + "subcategname : "+subcategname + "make_id : "+make_id + "model_id :" +model_id

                    + "model_name : "+model_name + "search_text : "+search_text);

        }

        img_back.setOnClickListener(v -> {

            onBackPressed();

        });

        if (dd4YouConfig.isInternetConnectivity()) {

            fetchallcountryListResponseCall();

        }

        else
        {
            callnointernet();

        }

        btn_saveaddr.setOnClickListener(v -> {

            if (dd4YouConfig.isInternetConnectivity()) {

                checkValidation();

            }

            else
            {
                callnointernet();

            }

        });

        ll_default.setVisibility(View.GONE);

//        if(isdefault.equals("1")){
//
//            ll_default.setVisibility(View.GONE);
//
//            set_default="1";
//        }
//        else {
//
//            ll_default.setVisibility(View.VISIBLE);
//
//            set_default="0";
//
//
//            sw_saveasdefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
//            {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
//                {
//
//                    if (isChecked) {
//                        //if 'isChecked' is true do whatever you need...
//
//                        set_default ="1";
//                    }
//                    else {
//
//                        set_default ="0";
//                    }
//                }
//            });
//
//        }

        spin_kit_loadingView.setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {

        gotoShippingAddressActivity();
    }

    private void gotoShippingAddressActivity() {

        Intent intent = new Intent(ShippingAddressAddActivity.this,ShippingAddressActivity.class);

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

    /* Get Country */

    @SuppressLint("LongLogTag")
    private void fetchallcountryListResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<GetCountryResponse> call = apiInterface.fetchallcountryListResponseCall(RestUtils.getContentType());
        Log.w(TAG,"GetCountryResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<GetCountryResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<GetCountryResponse> call, @NonNull Response<GetCountryResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"GetCountryResponse" + new Gson().toJson(response.body()));

                        countriesBeanList = response.body().getData().getCountries();

                        if(countriesBeanList != null && countriesBeanList.size()>0){


                            setCountry(countriesBeanList);


                        }

                        else {

                            ArrayList<String> arrayList = new ArrayList<>();

                            arrayList.add("Select Country");

                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(ShippingAddressAddActivity.this, R.layout.spinner_item, arrayList);

                            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view

                            sp_country.setAdapter(spinnerArrayAdapter);

                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }



                }

            }


            @Override
            public void onFailure(@NonNull Call<GetCountryResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"GetCountryResponse flr"+t.getMessage());
            }
        });



    }


    private void setCountry(List<GetCountryResponse.DataBean.CountriesBean> countriesBeanList) {

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Select Country");

        for(int i=0;i<countriesBeanList.size();i++){

            arrayList.add(countriesBeanList.get(i).getName());

            hashMap_Countryid.put(countriesBeanList.get(i).getName(),countriesBeanList.get(i).getId());

        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(ShippingAddressAddActivity.this, R.layout.spinner_item, arrayList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view

        sp_country.setAdapter(spinnerArrayAdapter);

        sp_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.hint_color));

                String country_name = sp_country.getSelectedItem().toString();

                countryid =  hashMap_Countryid.get(country_name) ;

                Log.w(TAG,"country_id "+countryid);

                if (dd4YouConfig.isInternetConnectivity()) {

                    fetchallstateListResponseCall(countryid);

                }

                else
                {
                    callnointernet();

                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });



    }

    /* Get State */

    @SuppressLint("LongLogTag")
    private void fetchallstateListResponseCall(String countryid) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<GetStateResponse> call = apiInterface.fetchallstateListResponseCall(RestUtils.getContentType(),getStateRequest(countryid));
        Log.w(TAG,"GetStateResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<GetStateResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<GetStateResponse> call, @NonNull Response<GetStateResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()){

                        Log.w(TAG,"GetStateResponse" + new Gson().toJson(response.body()));

                        statesBeanList = response.body().getData().getStates();

                        if(statesBeanList != null && statesBeanList.size()>0){


                            setState(statesBeanList);


                        }

                        else {

                            ArrayList<String> arrayList1 = new ArrayList<>();

                            arrayList1.add("Select State");

                            ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<>(ShippingAddressAddActivity.this, R.layout.spinner_item, arrayList1);

                            spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinner_item); // The drop down view

                            sp_state.setAdapter(spinnerArrayAdapter1);

                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }



                }

            }


            @Override
            public void onFailure(@NonNull Call<GetStateResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"GetStateResponse flr"+t.getMessage());
            }
        });



    }


    private void setState(List<GetStateResponse.DataBean.StatesBean> statesBeanList) {

        ArrayList<String> arrayList1 = new ArrayList<>();

        arrayList1.add("Select State");

        for(int i=0;i<statesBeanList.size();i++){

            arrayList1.add(statesBeanList.get(i).getName());

            hashMap_Stateid.put(statesBeanList.get(i).getName(),statesBeanList.get(i).getId());
        }


        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<>(ShippingAddressAddActivity.this, R.layout.spinner_item, arrayList1);

        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinner_item); // The drop down view

        sp_state.setAdapter(spinnerArrayAdapter1);

        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.hint_color));

                String state_name = sp_state.getSelectedItem().toString();

                stateid =  hashMap_Stateid.get(state_name) ;

                Log.w(TAG,"stateid "+stateid);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });



    }

    @SuppressLint("LongLogTag")
    private GetStateRequest getStateRequest(String countryid) {

        /*
         * COUNTRY_ID : 101
         */

        GetStateRequest getStateRequest = new GetStateRequest();
        getStateRequest.setCOUNTRY_ID(countryid);

        Log.w(TAG,"GetStateRequest "+ new Gson().toJson(getStateRequest));
        return getStateRequest;
    }


    private void checkValidation() {

        boolean isvalid = true;

        name = edt_name.edtContent.getText().toString();

        phoneno = edt_phoneno.edtContent.getText().toString();

        city = edt_city.edtContent.getText().toString();

        statename = sp_state.getSelectedItem().toString();

        countryname = sp_country.getSelectedItem().toString();

        zipcode = edt_zipcode.edtContent.getText().toString();

        addrln1 = edtAddress1.edtContent.getText().toString();

        addrln2 = edtAddress2.edtContent.getText().toString();


        if(name.equals("")){

            isvalid =false;

            edt_name.setError("Please Fill the Name");

            edt_name.requestFocus();
        }

        else if(phoneno.equals("")){

            isvalid =false;

            edt_phoneno.setError("Please Fill Phone Number");

            edt_phoneno.requestFocus();
        }

        else if(city.equals("")){

            isvalid =false;

            edt_city.setError("Please Fill City Name");

            edt_city.requestFocus();
        }

        else if(addrln1.equals("")){

            isvalid =false;

            edtAddress1.setError("Please Fill Address Line 1");

            edtAddress1.requestFocus();
        }

        else if(addrln2.equals("")){

            isvalid =false;

            edtAddress2.setError("Please Fill Address Line 2");

            edtAddress2.requestFocus();
        }

        else if(zipcode.equals("")){

            isvalid =false;

            edt_zipcode.setError("Please Fill Zipcode");

            edt_zipcode.requestFocus();
        }

        else if(countryname.equals("Select Country")){

            isvalid =false;

            TextView errorText = (TextView)sp_country.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Please Select Country");//changes the selected item text to this

            //Toast.makeText(ShippingAddressAddActivity.this,"Please Select Country",Toast.LENGTH_SHORT).show();
        }

        else if(statename.equals("Select State")){

            isvalid =false;

            TextView errorText = (TextView)sp_state.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Please Select State");//changes the selected item text to this

           // Toast.makeText(ShippingAddressAddActivity.this,"Please Select State",Toast.LENGTH_SHORT).show();
        }


        if(isvalid){

            if (dd4YouConfig.isInternetConnectivity()) {

                createaddrResponseCall(name,phoneno,city,statename,countryname,addrln1,addrln2,zipcode,set_default);

            }

            else
            {
                callnointernet();

            }


        }
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(ShippingAddressAddActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(ShippingAddressAddActivity.this, RegisterActivity.class));
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @SuppressLint("LongLogTag")
    private void createaddrResponseCall(String name, String phoneno, String city, String statename, String countryname, String addrln1, String addrln2, String zip, String is_default) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<CreateAddressListResponse> call = apiInterface.createaddressResponseCall(RestUtils.getContentType(), CreateAddressListRequest(name,phoneno,city,statename,countryname,addrln1,addrln2,zip,is_default));
        Log.w(TAG,"CreateAddressListResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<CreateAddressListResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<CreateAddressListResponse> call, @NonNull Response<CreateAddressListResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "CreateAddressListResponse" + new Gson().toJson(response.body()));

                        Toasty.success(getApplicationContext(),""+response.body().getMessage(),Toasty.LENGTH_LONG).show();

                        gotoCartActivity();

                    }

                    else {

                        Toast.makeText(ShippingAddressAddActivity.this, "" + response.body().getMessage(), Toast.LENGTH_LONG).show();

                    }

                }

            }


            @Override
            public void onFailure(@NonNull Call<CreateAddressListResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"CreateAddressListResponse flr"+t.getMessage());
            }
        });

    }

    private void gotoCartActivity() {

        Intent intent = new Intent(ShippingAddressAddActivity.this,RetailerCartActivity.class);

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

    @SuppressLint("LongLogTag")
    private CreateAddressListRequest CreateAddressListRequest(String name, String phoneno, String city, String statename, String countryname, String addrln1, String addrln2, String zip, String is_default) {

        /*
         * USER_ID : 541
         * NAME : tstaddr1a
         * PHONE : 1234567890
         * ADDRESS1 : tstaddr1
         * ADDRESS2 : tstaddr2
         * CITY : tstcity1
         * COUNTRY_ID : 101
         * STATE : 19
         * ZIP_CODE : 123456
         * DEFAULT : 1
         * MODE : INSERT
         */
        CreateAddressListRequest CreateAddressListRequest = new CreateAddressListRequest();
        CreateAddressListRequest.setUSER_ID(userid);
        CreateAddressListRequest.setNAME(name);
        CreateAddressListRequest.setPHONE(phoneno);
        CreateAddressListRequest.setCITY(city);
        CreateAddressListRequest.setCOUNTRY_ID(countryid);
        CreateAddressListRequest.setSTATE(stateid);
        CreateAddressListRequest.setADDRESS1(addrln1);
        CreateAddressListRequest.setADDRESS2(addrln2);
        CreateAddressListRequest.setZIP_CODE(zip);
        CreateAddressListRequest.setDEFAULT("1");
        CreateAddressListRequest.setMODE("INSERT");

        Log.w(TAG,"CreateAddressListRequest "+ new Gson().toJson(CreateAddressListRequest));
        return CreateAddressListRequest;
    }

    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ShippingAddressAddActivity.this);
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