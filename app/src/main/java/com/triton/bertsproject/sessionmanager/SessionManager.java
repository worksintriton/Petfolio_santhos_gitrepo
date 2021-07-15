package com.triton.bertsproject.sessionmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

public class SessionManager {

    public static final String KEY_FIRST_NAME = "firstname" ;
    public static final String KEY_LAST_NAME = "lastname";
    public static final String KEY_PROFILE_IMAGE = "profileimage" ;
    String TAG = "SessionManager";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "Session Manager";
    public static final String IS_LOGIN = "IsLoggedIn";




    public static final String KEY_EMAIL_ID = "emailid";
    public static final String KEY_TYPE = "type";
    public static final String KEY_ID = "id";

    public static final String KEEPLOGIN = "keeplogin";
    public static final String KEEPPROFILEUPDATE = "keepprofileupdate";

    public static final String KEY_COUNTRY_ID = "country_id";
    public static final String KEY_STATE_ID = "state_id";
    public static final String KEY_ZIPCODE = "zip_code";
    public static final String KEY_REVENUE = "revenue";

    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


//    public void createLoginSession(String id, String firstname, String lastname, String useremail,String usertype,String profileimage) {
//
//
//        editor.putBoolean(IS_LOGIN, true);
//        editor.putString(KEY_ID, id);
//        editor.putString(KEY_FIRST_NAME, firstname);
//        editor.putString(KEY_LAST_NAME, lastname);
//        editor.putString(KEY_EMAIL_ID, useremail);
//        editor.putString(KEY_TYPE, usertype);
//        editor.putString(KEY_PROFILE_IMAGE, profileimage);
//        Log.e(TAG, "................................>> session Login Details " + "KEY_ID" + id);
//
//        editor.commit();
//
//    }

    public void createLoginSession(String id, String firstname, String lastname, String useremail,String usertype,String profileimage

    ,String countryid,String stateid,String zipcode,String revenue) {


        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, id);
        editor.putString(KEY_FIRST_NAME, firstname);
        editor.putString(KEY_LAST_NAME, lastname);
        editor.putString(KEY_EMAIL_ID, useremail);
        editor.putString(KEY_TYPE, usertype);
        editor.putString(KEY_PROFILE_IMAGE, profileimage);
        editor.putString(KEY_COUNTRY_ID, countryid);
        editor.putString(KEY_STATE_ID, stateid);
        editor.putString(KEY_ZIPCODE, zipcode);
        editor.putString(KEY_REVENUE, revenue);
        Log.e(TAG, "................................>> session Login Details " + "KEY_ID" + id);

        editor.commit();

    }



    public HashMap<String, String> getProfileDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_ID, pref.getString(KEY_ID, ""));
        user.put(KEY_FIRST_NAME, pref.getString(KEY_FIRST_NAME, ""));
        user.put(KEY_LAST_NAME, pref.getString(KEY_LAST_NAME, ""));
        user.put(KEY_EMAIL_ID, pref.getString(KEY_EMAIL_ID, ""));
        user.put(KEY_TYPE, pref.getString(KEY_TYPE, ""));
        user.put(KEY_PROFILE_IMAGE, pref.getString(KEY_PROFILE_IMAGE, ""));
        user.put(KEY_COUNTRY_ID, pref.getString(KEY_COUNTRY_ID, ""));
        user.put(KEY_STATE_ID, pref.getString(KEY_STATE_ID, ""));
        user.put(KEY_ZIPCODE, pref.getString(KEY_REVENUE, ""));
        user.put(KEY_REVENUE, pref.getString(KEY_ZIPCODE, ""));
        return user;
    }


    public void logoutUser() {
        editor.clear();
        editor.commit();


    }




    public void setIsLogin(boolean isLoogedIn){
        editor.putBoolean(KEEPLOGIN,isLoogedIn);
        editor.apply();
    }

    public boolean isLoggedIn() {

        return pref.getBoolean(KEEPLOGIN, false);
    }

    public boolean isProfileUpdate() {
        return pref.getBoolean(KEEPPROFILEUPDATE, false);
    }

    public void setIsProfileUpdate(boolean isProfileUpdate){
        editor.putBoolean(KEEPPROFILEUPDATE,isProfileUpdate);
        editor.apply();
    }




}
