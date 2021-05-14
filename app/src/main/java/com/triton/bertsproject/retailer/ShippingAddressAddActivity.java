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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShippingAddressAddActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address_add);

        ButterKnife.bind(this);

        txt_toolbar_title.setText(R.string.new_addr);
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

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(ShippingAddressAddActivity.this, R.layout.spinner_item, arrayList);

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

        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<>(ShippingAddressAddActivity.this, R.layout.spinner_item, arrayList1);

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


        spin_kit_loadingView.setVisibility(View.GONE);

    }
}