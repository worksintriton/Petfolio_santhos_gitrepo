package com.carpeinfinitus.petfolio.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carpeinfinitus.petfolio.R;
import com.carpeinfinitus.petfolio.doctor.DoctorMyCalendarNewUserActivity;
import com.carpeinfinitus.petfolio.doctor.DoctorMyCalendarTimeActivity;
import com.carpeinfinitus.petfolio.interfaces.OnItemClickSpecialization;
import com.carpeinfinitus.petfolio.responsepojo.DoctorMyCalendarAvlDaysResponse;

import java.util.ArrayList;
import java.util.List;


public class DoctorMyCalendarAvailableNewUserAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "DoctorMyCalendarAvailableNewUserAdapter";
    private List<DoctorMyCalendarAvlDaysResponse.DataBean> dataBeanList = null;
    private Context context;

    DoctorMyCalendarAvlDaysResponse.DataBean currentItem;

    private OnItemClickSpecialization mCallback;



    public DoctorMyCalendarAvailableNewUserAdapter(Context context, List<DoctorMyCalendarAvlDaysResponse.DataBean> dataBeanList, RecyclerView inbox_list, DoctorMyCalendarNewUserActivity doctorMyCalendarActivity) {
        this.dataBeanList = dataBeanList;
        this.context = context;
        this.mCallback = (OnItemClickSpecialization)doctorMyCalendarActivity;



    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_doctor_mycalendar_available_cardview, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    private void initLayoutOne(ViewHolderOne holder, final int position) {

        currentItem = dataBeanList.get(position);
        if(dataBeanList != null && dataBeanList.size()>0){
            for (int i = 0; i < dataBeanList.size(); i++) {
                if(dataBeanList.get(position).getTitle() != null) {
                    holder.txt_days.setText(dataBeanList.get(position).getTitle());
                }

            }
        }

        if(dataBeanList.get(position).isStatus()){
            holder.ch_days.setClickable(false);
            holder.img_edit.setVisibility(View.VISIBLE);

        }else{
            holder.img_edit.setVisibility(View.GONE);

        }

        holder.ch_days.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String chspecialization = dataBeanList.get(position).getTitle();

                if(isChecked){
                    if (holder.ch_days.isChecked()) {
                        mCallback.onItemCheckSpecialization(chspecialization);
                    }

                }else{

                    mCallback.onItemUncheckSpecialization(chspecialization);

                }

            }
        });


        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DoctorMyCalendarTimeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 ArrayList<String> dateList = new ArrayList<>();
                dateList.add(dataBeanList.get(position).getTitle());
                i.putExtra("dateList",dateList);
                context.startActivity(i);

            }
        });





    }









    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_days;
        public LinearLayout ll_days;
        public CheckBox ch_days;
        public ImageView img_edit;



        public ViewHolderOne(View itemView) {
            super(itemView);

            ch_days = itemView.findViewById(R.id.ch_days);
            txt_days = itemView.findViewById(R.id.txt_days);
            ll_days = itemView.findViewById(R.id.ll_days);
            img_edit = itemView.findViewById(R.id.img_edit);
            img_edit.setVisibility(View.GONE);











        }




    }














}
