package com.dci.berts.sessionmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Connectivity {

//    Context context;
//    String key,value;
//
//    public Connectivity(Context context,String key,String value) {
//        this.context = context;
//        this.key = key;
//        this.value = value;
//    }

    @SuppressLint("LongLogTag")
    public void storeData(Context context, String key, String value){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,value);
//        Log.w("Connectivity Class Store"," Key : "+ key + " value : "+ value);
        editor.apply();
    }

    public String getData(Context context, String key){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String value = preferences.getString(key, "");
//        Log.w("Connectivity Class Get"," Key : "+ key + " value : "+ value);
        if(value!=null&&!value.isEmpty())
        {
          return value;
        }
        else {

            return "";
        }
    }

    @SuppressLint("LongLogTag")
    public void clearData(Context context, String key){

      //  Log.w("Connectivity Class key"," Key : "+ key);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

        String value = settings.getString(key, "");

    //   Log.w("Connectivity Class before"," value : "+ value);

        settings.edit().remove(key).apply();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String value1 = preferences.getString(key, "");

       // Log.w("Connectivity Class after"," value : "+ value1);
    }


}
