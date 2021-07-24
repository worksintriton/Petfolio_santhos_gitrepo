package com.dci.berts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dci.berts.R;
import com.dci.berts.model.SearchFilterListModel;

import java.util.List;

public class SearchFilterlistAdapter extends RecyclerView.Adapter<SearchFilterlistAdapter.ShopFilterlistHolder> {
    Context context;
    List<SearchFilterListModel> searchFilterListModels;
    View view;

    public SearchFilterlistAdapter(Context context, List<SearchFilterListModel> searchFilterListModels) {
        this.context = context;
        this.searchFilterListModels = searchFilterListModels;

    }

    @NonNull
    @Override
    public ShopFilterlistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filterlist, parent, false);
        return new ShopFilterlistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopFilterlistHolder holder, final int position) {

        final SearchFilterListModel searchFilterListModel = searchFilterListModels.get(position);

        if (searchFilterListModel.getFlistname()!= null) {

                holder.txt_flistname.setText(searchFilterListModel.getFlistname());

        }


    }

    @Override
    public int getItemCount() {
        return searchFilterListModels.size();
    }

    public static class ShopFilterlistHolder extends RecyclerView.ViewHolder {

        TextView txt_flistname;
        public ShopFilterlistHolder(View itemView) {
            super(itemView);
            txt_flistname = itemView.findViewById(R.id.txt_flistname);

        }
    }
}