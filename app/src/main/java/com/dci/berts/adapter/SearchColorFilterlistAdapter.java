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
import com.dci.berts.interfaces.GetColorIDListener;
import com.dci.berts.responsepojo.FetchAllColorsResponse;

import java.util.List;

public class SearchColorFilterlistAdapter extends RecyclerView.Adapter<SearchColorFilterlistAdapter.ShopFilterlistHolder> {
    Context context;
    List<FetchAllColorsResponse.DataBean.ColorsBean> colorsBeanList;
    View view;
    GetColorIDListener getColorIDListener;
    private static final String TAG = "SearchColorFilterlistAdapter";

    public SearchColorFilterlistAdapter(Context context, List<FetchAllColorsResponse.DataBean.ColorsBean> colorsBeanList,   GetColorIDListener getColorIDListener) {
        this.context = context;
        this.colorsBeanList =  colorsBeanList;
        this.getColorIDListener= getColorIDListener;

    }

    @NonNull
    @Override
    public ShopFilterlistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filter_yearlist, parent, false);
        return new ShopFilterlistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopFilterlistHolder holder, final int position) {

        final FetchAllColorsResponse.DataBean.ColorsBean colorsBean = colorsBeanList.get(position);

        if (colorsBean.getName()!= null&&!colorsBean.getName().isEmpty()) {

            holder.txt_flistname.setText(colorsBean.getName());

        }

        holder.cb_flist.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if(isChecked){

                getColorIDListener.getColorIDListener(colorsBean.getId(), colorsBean.getName());

            }
        });
    }

    @Override
    public int getItemCount() {
        return colorsBeanList.size();
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