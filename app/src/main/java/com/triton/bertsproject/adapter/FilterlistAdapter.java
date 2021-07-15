package com.triton.bertsproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.triton.bertsproject.R;
import com.triton.bertsproject.model.FilterlistModel;
import com.triton.bertsproject.retailer.RetailerProductListActivity;

import java.util.List;

public class FilterlistAdapter extends RecyclerView.Adapter<FilterlistAdapter.FilterlistHolder> {
    Context context;
    List<FilterlistModel> filterlistModels;
    View view;

    public FilterlistAdapter(Context context, List<FilterlistModel> filterlistModels) {
        this.context = context;
        this.filterlistModels = filterlistModels;

    }

    @NonNull
    @Override
    public FilterlistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filter_yearlist, parent, false);
        return new FilterlistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterlistHolder holder, final int position) {

        final FilterlistModel filterlistModel = filterlistModels.get(position);

        if (filterlistModel.getFlistname()!= null) {

            holder.txt_fllistname.setText(filterlistModel.getFlistname());

        }


    }

    @Override
    public int getItemCount() {
        return filterlistModels.size();
    }

    public static class FilterlistHolder extends RecyclerView.ViewHolder {

        TextView txt_fllistname;

        public FilterlistHolder(View itemView) {
            super(itemView);

            txt_fllistname = itemView.findViewById(R.id.txt_fllistname);

        }
    }
}