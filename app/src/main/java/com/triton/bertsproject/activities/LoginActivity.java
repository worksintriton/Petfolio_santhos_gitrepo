package com.triton.bertsproject.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.api.RestApiInterface;
import com.triton.bertsproject.app.App;
import com.triton.bertsproject.customView.CustomEditText;
import com.triton.bertsproject.requestpojo.FacebookLoginRequest;
import com.triton.bertsproject.requestpojo.LoginRequest;
import com.triton.bertsproject.requestpojo.SignupRequest;
import com.triton.bertsproject.responsepojo.LoginResponse;
import com.triton.bertsproject.responsepojo.SignupResponse;
import com.triton.bertsproject.retailer.ProductDetailDescriptionActivity;
import com.triton.bertsproject.retailer.RetailerCartActivity;
import com.triton.bertsproject.retailer.RetailerDashboardActivity;
import com.triton.bertsproject.retailer.RetailerProductListActivity;
import com.triton.bertsproject.retailer.RetailerProductListBasedOnCategActivity;
import com.triton.bertsproject.retailer.RetailerProductListBasedOnMakeActivity;
import com.triton.bertsproject.retailer.RetailerProfileAccountActivity;
import com.triton.bertsproject.retailer.SearchProductListActivity;
import com.triton.bertsproject.sessionmanager.Connectivity;
import com.triton.bertsproject.sessionmanager.SessionManager;
import com.triton.bertsproject.utils.RestUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    Context context = LoginActivity.this;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_email)
    CustomEditText edt_email;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_password)
    CustomEditText edt_password;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_sigin)
    Button btn_sigin;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_forget_password)
    TextView txt_forget_password;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.login_button)
    LoginButton btnLogin;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_or)
    TextView txt_or;

    CallbackManager callbackManager;

    private String SMedia;

    String strName,strCurrentemail,strSMedia;

    String register_mode = "Manual";

    String firstname,lastname,email,password,cnfm_password,facebook_id;


    DD4YouConfig dd4YouConfig;

    Dialog alertDialog;

    String fromactivty;

    String brand_id,brand_name,parent_id,subcategid,categ_name,subcategname,make_id,make_name,model_id,model_name, prod_id,prod_name;;

    String search_text , quantity, unit_price;

    Connectivity connectivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        txt_toolbar_title.setText(R.string.login);

        spin_kit_loadingView.setVisibility(View.GONE);

        FacebookSdk.sdkInitialize(this);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            fromactivty = extras.getString("fromactivity");

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

                    "search_text : "+search_text+ "fromactivity :" +fromactivty);


        }

        Log.w("Oncreate ", TAG + "fromActivity " +fromactivty);

        dd4YouConfig = new DD4YouConfig(this);

        connectivity = new Connectivity();

        img_back.setOnClickListener(v -> {

            onBackPressed();

        });

        txt_forget_password.setOnClickListener(v -> {

           gotoForgetPasswordActivity();

        });

        edt_email.setTitle(getString(R.string.email));

        edt_password.setTitle(getString(R.string.password));

        btn_sigin.setOnClickListener(v -> checkValidation());

        getKeyHash();

        callbackManager = CallbackManager.Factory.create();

        btnLogin.setPermissions(Arrays.asList("user_photos", "email", "public_profile", "user_posts"));

        // Callback registration
        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @SuppressLint("LongLogTag")
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                Log.w(TAG, "Facebook" + object);

                                Log.v("Main", response.toString());
                                setProfileToView(object);
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link,email,gender,last_name,first_name,locale,timezone,updated_time,verified");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(LoginActivity.this, "error to Login Facebook", Toast.LENGTH_SHORT).show();
            }
        });

        if (!App.appUtils.isNetAvailable()) {
            alertUserP(LoginActivity.this, "Connection Error", "No Internet connection available", "OK");
        } else {
            disconnectFromFacebook();
        }


    }

    private void gotoForgetPasswordActivity() {

        Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);

        intent.putExtra("fromactivity",fromactivty);

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

        intent.putExtra("prod_id",prod_id);

        intent.putExtra("quantity",quantity);

        intent.putExtra("unit_price",unit_price);


        startActivity(intent);

        finish();

    }

    private void checkValidation() {

        boolean isvalid = true;

        String email = edt_email.edtContent.getText().toString();

        String password = edt_password.edtContent.getText().toString();

        if(edt_email.edtContent.getText().toString().equals("") || !isValidEmail(edt_email.edtContent.getText().toString() )){

            isvalid = false;

            edt_email.setError("Please Enter Valid Mail ID");

        }

        else if(edt_password.edtContent.getText().toString().equals("")){

            isvalid = false;

            edt_password.setError("Please Enter Valid Password");

        }

        if(isvalid){

            if (dd4YouConfig.isInternetConnectivity()) {

                loginResponseCall(email,password);

            }

            else
            {
                callnointernet();

            }



        }

    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            startActivity(new Intent(LoginActivity.this, LoginActivity.class));
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @SuppressLint("LongLogTag")
    private void loginResponseCall(String email, String password) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<LoginResponse> call = apiInterface.loginResponseCall(RestUtils.getContentType(),loginRequest(email,password));
        Log.w(TAG,"LoginResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<LoginResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "LoginResponse" + new Gson().toJson(response.body()));

                        SessionManager sessionManager = new SessionManager(LoginActivity.this);
                        sessionManager.setIsLogin(true);
                        sessionManager.createLoginSession(
                                response.body().getData().getProfile().getId(),
                                response.body().getData().getProfile().getFirst_name(),
                                response.body().getData().getProfile().getLast_name(),
                                response.body().getData().getProfile().getEmail(),
                                response.body().getData().getProfile().getRole(),
                                response.body().getData().getProfile().getAvatar(),
                                response.body().getData().getProfile().getCountry_id(),
                                response.body().getData().getProfile().getState_id(),
                                response.body().getData().getProfile().getZip_code(),
                                response.body().getData().getProfile().getRevenue()

                        );


                        Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                        onBackPressed();

                    }

                    else {

                        Toast.makeText(LoginActivity.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();

                    }

                }



            }


            @Override
            public void onFailure(@NonNull Call<LoginResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"LoginResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private LoginRequest loginRequest(String email, String password) {

        /*
         * email : prabhu.imsc@gmail.com
         * password : testc1234
         */

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        Log.w(TAG,"LoginRequest "+ new Gson().toJson(loginRequest));
        return loginRequest;
    }

    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
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


    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
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
        Intent intent = new Intent(LoginActivity.this,RetailerDashboardActivity.class);
        intent.putExtra("tag",tag);
        intent.putExtra("fromactivity",fromactivty);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {

        // super.onBackPressed(); commented this line in order to disable back press
        //Write your code here
       if(fromactivty!=null){
           if(fromactivty.equals("HomeFragment")){

               callDirections("1");

           }
           else if(fromactivty.equals("MyGarageFragment")){

               callDirections("2");

           }

           else if(fromactivty.equals("ProfileFragment")){

               callDirections("5");
           }

           else if(fromactivty.equals("RetailerCartActivity")){

               gotoCartActivity();
           }

           else if(fromactivty.equals("SearchProdListActivity")){

               gotoSearchProductListActivity();
           }

           else if(fromactivty.equals("ProductDetailDescriptionActivity")){

               gotoProductDetailDescriptionActivity();
           }

           else if(fromactivty.equals("RetailerProductListActivity")){

              gotoBrandProductListActivity();

           }

           else if(fromactivty.equals("RetailerProductListBasedOnCategActivity")){

              gotoCategoryProductListActivity();
           }

           else if(fromactivty.equals("RetailerProductListBasedOnMakeActivity")){

               gotoMakeProductListActivity();
           }

           else {

               Intent intent = new Intent(LoginActivity.this, RetailerDashboardActivity.class);

               startActivity(intent);

               finish();
           }
       }

       else {

           Intent intent = new Intent(LoginActivity.this, RetailerDashboardActivity.class);

           startActivity(intent);

           finish();
       }
    }

    private void gotoMakeProductListActivity() {

        Intent intent = new Intent(LoginActivity.this, RetailerProductListBasedOnMakeActivity.class);

        intent.putExtra("make_id",make_id);

        intent.putExtra("make_name",make_name);

        intent.putExtra("model_id", model_id);

        intent.putExtra("model_name",model_name);

        intent.putExtra("fromactivity",TAG);

        startActivity(intent);

        finish();

    }
    private void gotoCategoryProductListActivity() {

        Intent intent = new Intent(LoginActivity.this, RetailerProductListBasedOnCategActivity.class);

        intent.putExtra("parent_id",parent_id);

        intent.putExtra("categ_name",categ_name);

        intent.putExtra("subcategid",subcategid);

        intent.putExtra("subcategname",subcategname);

        intent.putExtra("fromactivity",TAG);

        startActivity(intent);

        finish();

    }
    private void gotoBrandProductListActivity() {

        Intent intent = new Intent(LoginActivity.this, RetailerProductListActivity.class);

        intent.putExtra("brand_id",brand_id);

        intent.putExtra("brand_name",brand_name);

        intent.putExtra("fromactivity",TAG);

        startActivity(intent);

        finish();

    }

    private void gotoProductDetailDescriptionActivity() {

        Intent intent = new Intent(LoginActivity.this, ProductDetailDescriptionActivity.class);

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

    private void gotoSearchProductListActivity() {

        Intent intent = new Intent(LoginActivity.this, SearchProductListActivity.class);

        intent.putExtra("fromactivity",TAG);

        intent.putExtra("search_text",search_text);

        intent.putExtra("prod_id",prod_id);

        intent.putExtra("quantity",quantity);

        intent.putExtra("unit_price",unit_price);


    }

    private void gotoCartActivity() {

        Intent intent = new Intent(LoginActivity.this, RetailerCartActivity.class);

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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("LongLogTag")
    private void setProfileToView(JSONObject jsonObject) {

        register_mode="Facebook";


        try {

            if(jsonObject.has("id")){

                facebook_id = jsonObject.getString("id");

                Log.w(TAG,"fb_id "+jsonObject.getString("id"));

            }

            else {

                facebook_id="";

            }

            if(dd4YouConfig.isInternetConnectivity()){

                fbloginResponseCall(facebook_id);
            }

            else {

                callnointernet();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void facebookClickAction() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("user_photos", "email", "public_profile", "user_posts"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        String f_name = "";
                        String m_name = "";
                        String l_name = "";
                        String full_name = "";
                        String profile_image = "";
                        String facebook_id = "";
                        String f_email = "";
                        if (AccessToken.getCurrentAccessToken() != null) {
                            RequestData();
                            Profile profile = Profile.getCurrentProfile();
                            if (profile != null) {
                                facebook_id = profile.getId();

                                Log.e("facebook_id", facebook_id);
                                Log.e("f_email", f_email);
                                f_name = profile.getFirstName();
                                Log.e("f_name", f_name);
                                m_name = profile.getMiddleName();
                                Log.e("m_name", m_name);
                                l_name = profile.getLastName();
                                Log.e("l_name", l_name);
                                full_name = profile.getName();
                                Log.e("full_name", full_name);
                                profile_image = profile.getProfilePictureUri(400, 400).toString();
                                Log.e("profile_image", profile_image);
                            }

                        } else {
                            showDialogMethod("Warning", "Facebook is not available at the moment, Please try other method to signup");
                        }
                    }

                    @Override
                    public void onCancel() {
                        showDialogMethod("Warning", "Facebook is not available at the moment, Please try other method to signup");
                        // App code
                        disconnectFromFacebook();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }


    private void RequestData() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                JSONObject json = response.getJSONObject();
                System.out.println("Json data :" + json);

                try {
                    if (json != null) {
                        String currentemail = "";
                        String name = "";
                        SMedia = "Facebook";
                        currentemail = json.getString("email");
                        name = json.getString("name");
                        Log.d("email", currentemail);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }



    private void KeyboardHide() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        // imm.hideSoftInputFromWindow(etRenterPassword.getWindowToken(), 0);
    }

    private void showDialogMethod(String title, String message) {
        KeyboardHide();
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();

    }



    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }
    public void alertUserP(Context context, String title, String msg, String btn) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setMessage(msg).setTitle(title).setCancelable(false)
                .setPositiveButton(btn, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        android.app.AlertDialog alert = builder.create();
        alert.show();
    }


    @SuppressLint("PackageManagerGetSignatures")
    private void getKeyHash() {

        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.triton.bertsproject", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.w("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.w("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.w("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.w("exception", e.toString());
        }
    }


    @SuppressLint("LongLogTag")
    private void fbloginResponseCall(String facebook_id) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<LoginResponse> call = apiInterface.fbloginResponseCall(RestUtils.getContentType(),facebookLoginRequest(facebook_id));
        Log.w(TAG,"LoginResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<LoginResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "LoginResponse" + new Gson().toJson(response.body()));

                        SessionManager sessionManager = new SessionManager(LoginActivity.this);
                        sessionManager.setIsLogin(true);
                        sessionManager.createLoginSession(
                                response.body().getData().getProfile().getId(),
                                response.body().getData().getProfile().getFirst_name(),
                                response.body().getData().getProfile().getLast_name(),
                                response.body().getData().getProfile().getEmail(),
                                response.body().getData().getProfile().getRole(),
                                response.body().getData().getProfile().getAvatar(),
                                response.body().getData().getProfile().getCountry_id(),
                                response.body().getData().getProfile().getState_id(),
                                response.body().getData().getProfile().getZip_code(),
                                response.body().getData().getProfile().getRevenue()

                        );


                        Toasty.success(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                        onBackPressed();

                    }

                    else {

                        Toast.makeText(LoginActivity.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();

                    }

                }



            }


            @Override
            public void onFailure(@NonNull Call<LoginResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"LoginResponse flr"+t.getMessage());
            }
        });

    }

    @SuppressLint("LongLogTag")
    private FacebookLoginRequest facebookLoginRequest(String facebook_id) {

        /*
         * fb_id : FBID_1234
         */

        FacebookLoginRequest facebookLoginRequest = new FacebookLoginRequest();
        facebookLoginRequest.setFb_id(facebook_id);

        Log.w(TAG,"FacebookLoginRequest "+ new Gson().toJson(facebookLoginRequest));
        return facebookLoginRequest;
    }


}