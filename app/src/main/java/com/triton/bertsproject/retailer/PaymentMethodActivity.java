package com.triton.bertsproject.retailer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.triton.bertsproject.R;
import com.triton.bertsproject.sessionmanager.Connectivity;
import com.triton.bertsproject.sessionmanager.SessionManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import in.dd4you.appsconfig.DD4YouConfig;

public class PaymentMethodActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rdGroupPayment)
    RadioGroup rdGroupPayment;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rdpaymgtwy)
    RadioButton rdpaymgtwy;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rdcreditlimit)
    RadioButton rdcreditlimit;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rdcod)
    RadioButton rdcod;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_payment_gateway)
    Button btn_payment_gateway;

    SessionManager sessionManager;

    String user_id,user_type,radioValue;

    RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getProfileDetails();

        user_id = user.get(SessionManager.KEY_ID);

        user_type = user.get(SessionManager.KEY_TYPE);

        if(user_type!=null){

            if(user_type.equals("retail")){

                rdcreditlimit.setVisibility(View.GONE);

            }

        }


        RadioGroup rdGroupPayment = (RadioGroup) findViewById(R.id.rdGroupPayment);

        btn_payment_gateway.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = rdGroupPayment.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                radioValue = radioButton.getText().toString();

                if(!radioValue.equals("")){

                    Intent intent=new Intent(PaymentMethodActivity.this,CheckoutScreenActivity.class);
                    intent.putExtra("MESSAGE",radioValue);
                    setResult(2,intent);
                    finish();//finishing activity
                }

                else {

                    Toasty.error(PaymentMethodActivity.this, "Please Select Payment Method", Toast.LENGTH_SHORT).show();
                }

            }

        });



    }
}