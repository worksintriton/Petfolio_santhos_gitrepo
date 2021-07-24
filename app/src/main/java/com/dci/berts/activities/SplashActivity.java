package com.dci.berts.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.dci.berts.R;
import com.dci.berts.retailer.RetailerDashboardActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";

    Context context = SplashActivity.this;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.w("Oncreate", TAG);



        try{
            // Initialize Firebase
            FirebaseApp.initializeApp(getApplicationContext());
            FirebaseMessaging.getInstance().setAutoInitEnabled(true);
            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(task -> {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        token = task.getResult();
                        Log.w(TAG,"token--->"+ token);



                    });



        }
        catch (Exception e){
            Log.w(TAG,"FCM : "+e.getLocalizedMessage());
            Log.w(TAG,"FCM Message : "+e.getMessage());
            e.printStackTrace();
        }


        /* ***** Create Thread that will sleep for 5 seconds****/
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(5*1000);

                    // After 5 seconds redirect to another intent
                    Intent i=new Intent(context, RetailerDashboardActivity.class);
                    startActivity(i);
                    Animatoo.animateSlideRight(SplashActivity.this);

                    //Remove activity
                    finish();
                } catch (Exception ignored) {
                }
            }
        };
        // start thread
        background.start();
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
        Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
    }
}