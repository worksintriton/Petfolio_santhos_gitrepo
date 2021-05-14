package com.triton.bertsproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.bertsproject.R;
import com.triton.bertsproject.model.FilterColorlistModel;

import java.util.List;

public class FilterColorlistAdapter extends RecyclerView.Adapter<FilterColorlistAdapter.FilterlistHolder> {
    Context context;
    List<FilterColorlistModel> filtercolorlistModels;
    View view;

    public FilterColorlistAdapter(Context context, List<FilterColorlistModel> filtercolorlistModels) {
        this.context = context;
        this.filtercolorlistModels = filtercolorlistModels;

    }

    @NonNull
    @Override
    public FilterlistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filter_colorlist, parent, false);
        return new FilterlistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterlistHolder holder, final int position) {

        final FilterColorlistModel filtercolorlistModel = filtercolorlistModels.get(position);

        if (filtercolorlistModel.getFlistname()!= null) {

            holder.txt_fllistname.setText(filtercolorlistModel.getFlistname());

        }

        if(filtercolorlistModel.getColorlist()==1){

            holder.img_colorlist.setImageResource(R.drawable.ic_red_checkbox);

        }

        else if(filtercolorlistModel.getColorlist()==2){

            holder.img_colorlist.setImageResource(R.drawable.ic_black_checkbox);
        }

        else if(filtercolorlistModel.getColorlist()==3){

            holder.img_colorlist.setImageResource(R.drawable.ic_green_checkbox);
        }

        else{
            holder.img_colorlist.setImageResource(R.drawable.ic_blue_checkbox);

        }


    }

    @Override
    public int getItemCount() {
        return filtercolorlistModels.size();
    }

    public static class FilterlistHolder extends RecyclerView.ViewHolder {

        TextView txt_fllistname;

        ImageView img_colorlist;

        public FilterlistHolder(View itemView) {
            super(itemView);

            txt_fllistname = itemView.findViewById(R.id.txt_fllistname);

            img_colorlist = itemView.findViewById(R.id.img_colorlist);

        }
    }
}