package com.dci.berts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dci.berts.R;
import com.dci.berts.interfaces.GetYearNameListener;
import com.dci.berts.responsepojo.FetchAllYearResponse;

import java.util.List;

public class SearchYearFilterlistAdapter extends RecyclerView.Adapter<SearchYearFilterlistAdapter.ShopFilterlistHolder> {
    Context context;
    List<FetchAllYearResponse.DataBean.YearBean> yearBeanList;
    View view;
    GetYearNameListener getYearNameListener;
    private static final String TAG = "SearchYearFilterlistAdapter";

    public SearchYearFilterlistAdapter(Context context, List<FetchAllYearResponse.DataBean.YearBean> yearBeanList,GetYearNameListener getYearNameListener) {
        this.context = context;
        this.yearBeanList = yearBeanList;
        this.getYearNameListener=getYearNameListener;

    }

    @NonNull
    @Override
    public ShopFilterlistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filter_yearlist, parent, false);
        return new ShopFilterlistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopFilterlistHolder holder, final int position) {

        final FetchAllYearResponse.DataBean.YearBean yearBean = yearBeanList.get(position);

        if (yearBean.getP_year()!= null&&!yearBean.getP_year().isEmpty()) {

            holder.txt_flistname.setText(yearBean.getP_year());

        }

        holder.cb_flist.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if(isChecked){

                getYearNameListener.getYearNameListener(yearBean.getId(),yearBean.getP_year(),holder.cb_flist,isChecked);
            }

            else {

                getYearNameListener.getYearNameListener(yearBean.getId(),yearBean.getP_year(),holder.cb_flist,isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return yearBeanList.size();
    }

    public static class ShopFilterlistHolder extends RecyclerView.ViewHolder {

        TextView txt_flistname;

        CheckBox cb_flist;
        public ShopFilterlistHolder(View itemView) {
            super(itemView);
            cb_flist = itemView.findViewById(R.id.cb_flist);
            txt_flistname = itemView.findViewById(R.id.txt_fllistname);

        }
    }
}