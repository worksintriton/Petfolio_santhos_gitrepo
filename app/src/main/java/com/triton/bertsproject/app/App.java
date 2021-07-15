package com.triton.bertsproject.app;

import android.app.Application;

import com.triton.bertsproject.Utlity.BasicUtils;
import com.triton.bertsproject.sessionmanager.SessionManager;


public class App extends Application {

    private static final String TAG = "App";

    private static App sApp;

    public static SessionManager appPreference;
    public static BasicUtils appUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        appPreference = new SessionManager(this);
        appUtils = new BasicUtils(this);
        sApp = this;
    }

    public static App getInstance() {
        return sApp;
    }


}
