package com.carpeinfinitus.petfolio.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.carpeinfinitus.petfolio.R;
import com.carpeinfinitus.petfolio.doctor.Doctor_Holiday_Activity;
import com.carpeinfinitus.petfolio.interfaces.OnItemDeleteHoliday;
import com.carpeinfinitus.petfolio.responsepojo.HolidayListResponse;

import java.util.List;


public class DoctorHolidayListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "DoctorHolidayListAdapter";
    private List<HolidayListResponse.DataBean> holidayListResponseList = null;
    private Context context;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    HolidayListResponse.DataBean currentItem;
    private Dialog dialog;
    private OnItemDeleteHoliday onItemDeleteHoliday;


    public DoctorHolidayListAdapter(Context context, List<HolidayListResponse.DataBean> holidayListResponseList, RecyclerView inbox_list, Doctor_Holiday_Activity doctor_holiday_activity) {
        this.holidayListResponseList = holidayListResponseList;
        this.context = context;
        this.onItemDeleteHoliday = (OnItemDeleteHoliday)doctor_holiday_activity;




    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_doctor_holidaylist_cardview, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {

        currentItem = holidayListResponseList.get(position);
        if(currentItem.getDate() != null) {
            holder.txt_holidaydate.setText(currentItem.getDate());
        }

        holder.txt_holidaydelete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onClick(View v) {
                onItemDeleteHoliday.onItemdeletedate(holidayListResponseList.get(position).get_id());
                Log.w(TAG,"ID--->"+holidayListResponseList.get(position).get_id());

            }
        });



    }









    @Override
    public int getItemCount() {
        return holidayListResponseList  .size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_holidaydate,txt_holidaydelete;




        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_holidaydate = itemView.findViewById(R.id.txt_holidaydate);
            txt_holidaydelete = itemView.findViewById(R.id.txt_holidaydelete);

        }




    }







}
