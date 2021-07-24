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
import com.dci.berts.interfaces.GetCategIDListener;
import com.dci.berts.responsepojo.FetchAllParentCategoriesResponse;

import java.util.List;

public class SearchCategFilterlistAdapter extends RecyclerView.Adapter<SearchCategFilterlistAdapter.ShopFilterlistHolder> {
    Context context;
    List<FetchAllParentCategoriesResponse.DataBean.CategoriesBean> categoriesBeanList;
    View view;
    GetCategIDListener getCategIDListener;
    private static final String TAG = "SearchCategFilterlistAdapter";
    private int selectedPosition = -1;// no selection by default

    public SearchCategFilterlistAdapter(Context context, List<FetchAllParentCategoriesResponse.DataBean.CategoriesBean> categoriesBeanList, GetCategIDListener getCategIDListener) {
        this.context = context;
        this.categoriesBeanList = categoriesBeanList;
        this.getCategIDListener= getCategIDListener;

    }

    @NonNull
    @Override
    public ShopFilterlistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filter_yearlist, parent, false);
        return new ShopFilterlistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopFilterlistHolder holder, final int position) {

        final FetchAllParentCategoriesResponse.DataBean.CategoriesBean categoriesBean = categoriesBeanList.get(position);

        if (categoriesBean.getName()!= null&&!categoriesBean.getName().isEmpty()) {

            holder.txt_flistname.setText(categoriesBean.getName());

        }

        holder.cb_flist.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if(isChecked){

                getCategIDListener.getCategIDListener(categoriesBean.getId(), categoriesBean.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriesBeanList.size();
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