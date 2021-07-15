package com.triton.bertsproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.triton.bertsproject.R;
import com.triton.bertsproject.interfaces.GetBrandIDListener;
import com.triton.bertsproject.interfaces.GetModelIDListener;
import com.triton.bertsproject.responsepojo.FetchAllBrandsResponse;
import com.triton.bertsproject.responsepojo.FetchChildMakeslistRequestResponse;

import java.util.List;

public class SearchModelFilterlistAdapter extends RecyclerView.Adapter<SearchModelFilterlistAdapter.ShopFilterlistHolder> {
    Context context;
    List<FetchChildMakeslistRequestResponse.DataBean.ModelBean> modelBeanList;
    View view;
    GetModelIDListener getModelIDListener;
    private static final String TAG = "SearchModelFilterlistAdapter";

    public SearchModelFilterlistAdapter(Context context,  List<FetchChildMakeslistRequestResponse.DataBean.ModelBean> modelBeanList, GetModelIDListener getModelIDListener) {
        this.context = context;
        this.modelBeanList = modelBeanList;
        this.getModelIDListener=getModelIDListener;

    }

    @NonNull
    @Override
    public ShopFilterlistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filter_yearlist, parent, false);
        return new ShopFilterlistHolder(view);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull ShopFilterlistHolder holder, final int position) {

        final FetchChildMakeslistRequestResponse.DataBean.ModelBean makeBean= modelBeanList.get(position);

        Log.w(TAG,"GetName "+ makeBean.getName());

        if (makeBean.getName()!= null&&!makeBean.getName().isEmpty()) {

            holder.txt_flistname.setText(makeBean.getName());

        }

        holder.cb_flist.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if(isChecked){

                getModelIDListener.getModelIDListener(makeBean.getId(), makeBean.getName(),holder.cb_flist,isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelBeanList.size();
    }

    public static class ShopFilterlistHolder extends RecyclerView.ViewHolder {

        TextView txt_flistname;

        CheckBox cb_flist;

        ImageView img_colorlist;

        public ShopFilterlistHolder(View itemView) {
            super(itemView);
            cb_flist = itemView.findViewById(R.id.cb_flist);
            txt_flistname = itemView.findViewById(R.id.txt_fllistname);
            img_colorlist = itemView.findViewById(R.id.img_colorlist);
        }
    }
}