package com.triton.bertsproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.bertsproject.R;
import com.triton.bertsproject.interfaces.GetMakeIDListener;
import com.triton.bertsproject.interfaces.GetYearNameListener;
import com.triton.bertsproject.responsepojo.FetchAllParentMakesResponse;
import com.triton.bertsproject.responsepojo.FetchAllParentMakesResponse;
import com.triton.bertsproject.responsepojo.FetchAllYearResponse;

import java.util.List;

public class SearchMakeFilterlistAdapter extends RecyclerView.Adapter<SearchMakeFilterlistAdapter.ShopFilterlistHolder> {
    Context context;
    List<FetchAllParentMakesResponse.DataBean.MakeBean> makesBeanList;
    View view;
    GetMakeIDListener getMakeIDListener;
    private static final String TAG = "SearchYearFilterlistAdapter";

    public SearchMakeFilterlistAdapter(Context context, List<FetchAllParentMakesResponse.DataBean.MakeBean> makesBeanList, GetMakeIDListener getMakeIDListener) {
        this.context = context;
        this.makesBeanList = makesBeanList;
        this.getMakeIDListener=getMakeIDListener;

    }

    @NonNull
    @Override
    public ShopFilterlistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filter_yearlist, parent, false);
        return new ShopFilterlistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopFilterlistHolder holder, final int position) {

        final FetchAllParentMakesResponse.DataBean.MakeBean makeBean = makesBeanList.get(position);

        if (makeBean.getName()!= null&&!makeBean.getName().isEmpty()) {

            holder.txt_flistname.setText(makeBean.getName());

        }

        holder.cb_flist.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if(isChecked){

                getMakeIDListener.getMakeIDListener(makeBean.getId(),makeBean.getName(),holder.cb_flist,isChecked);
            }

            else {

                getMakeIDListener.getMakeIDListener(makeBean.getId(),makeBean.getName(),holder.cb_flist,isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return makesBeanList.size();
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