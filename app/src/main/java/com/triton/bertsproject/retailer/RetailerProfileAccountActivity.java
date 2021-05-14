package com.triton.bertsproject.retailer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import com.triton.bertsproject.customView.CustomEditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RetailerProfileAccountActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_firstname)
    CustomEditText edt_firstname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_lastname)
    CustomEditText edt_lastname;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_email)
    CustomEditText edt_email;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_password)
    CustomEditText edt_password;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_cnfmpassword)
    CustomEditText edt_cnfmpassword;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_zipcode)
    CustomEditText edt_zipcode;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sp_country)
    Spinner sp_country;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sp_state)
    Spinner sp_state;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_revenue)
    CustomEditText edt_revenue;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_profile_account);

        ButterKnife.bind(this);

        txt_toolbar_title.setText(R.string.edit_profile);

        edt_firstname.setTitle(getString(R.string.firstname));

        edt_lastname.setTitle(getString(R.string.lastname));

        edt_email.setTitle(getString(R.string.email));

        edt_password.setTitle(getString(R.string.password));

        edt_cnfmpassword.setTitle(getString(R.string.confirm_password));

        edt_zipcode.setTitle(getString(R.string.zipcode));

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Country");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        arrayList.add("India");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(RetailerProfileAccountActivity.this, R.layout.spinner_item, arrayList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item); // The drop down view

        sp_country.setAdapter(spinnerArrayAdapter);

        sp_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.hint_color));

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        ArrayList<String> arrayList1 = new ArrayList<>();

        arrayList1.add("State");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        arrayList1.add("Tamilnadu");

        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<>(RetailerProfileAccountActivity.this, R.layout.spinner_item, arrayList1);

        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinner_item); // The drop down view

        sp_state.setAdapter(spinnerArrayAdapter1);

        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int arg2, long arg3) {
                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.hint_color));

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        edt_revenue.setTitle(getString(R.string.revenue));

        spin_kit_loadingView.setVisibility(View.GONE);
    }
}