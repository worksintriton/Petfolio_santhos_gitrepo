package com.petfolio.infinitus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.petfolio.infinitus.R;
import com.petfolio.infinitus.responsepojo.DoctorDetailsResponse;

import java.util.List;


public class DoctorClinicSpecTypesListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "DoctorClinicSpecTypesListAdapter";
    Context mcontext;
    List<DoctorDetailsResponse.DataBean.SpecializationBean> specializationBeanList;
    DoctorDetailsResponse.DataBean.SpecializationBean currentItem;


    public DoctorClinicSpecTypesListAdapter(Context context, List<DoctorDetailsResponse.DataBean.SpecializationBean> specializationBeanList) {
        this.specializationBeanList = specializationBeanList;
        this.mcontext = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sp_speclist, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    private void initLayoutOne(ViewHolderOne holder, final int position) {

        currentItem = specializationBeanList.get(position);

        if(currentItem.getSpecialization()!=null&&!currentItem.getSpecialization().isEmpty()){

            holder.txt_spectypes.setText(currentItem.getSpecialization());
        }


    }
    @Override
    public int getItemCount() {
        return specializationBeanList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {

        public TextView txt_spectypes;



        public ViewHolderOne(View itemView) {
            super(itemView);

            txt_spectypes = itemView.findViewById(R.id.txt_specname);


        }

    }

}