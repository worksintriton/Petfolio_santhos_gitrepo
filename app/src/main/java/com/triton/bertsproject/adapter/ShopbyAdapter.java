package com.triton.bertsproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.bertsproject.R;
import com.triton.bertsproject.model.ShopByNameModel;
import com.triton.bertsproject.retailer.ShowAllBrandsActivity;
import com.triton.bertsproject.retailer.ShowAllMakeszActivity;
import com.triton.bertsproject.retailer.ShowAllParentCategoriesActivity;

import java.util.List;

public class ShopbyAdapter extends RecyclerView.Adapter<ShopbyAdapter.ShopbyHolder> {
    Context context;
    List<ShopByNameModel> shopByNameModels;
    View view;

    public ShopbyAdapter(Context context, List<ShopByNameModel> shopByNameModels) {
        this.context = context;
        this.shopByNameModels = shopByNameModels;

    }

    @NonNull
    @Override
    public ShopbyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_shopbyname, parent, false);
        return new ShopbyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopbyHolder holder, final int position) {

        final ShopByNameModel shopByNameModel = shopByNameModels.get(position);

        if (shopByNameModel.getShopbyname()!= null) {

                holder.txt_shopbyname.setText(shopByNameModel.getShopbyname());

        }

        holder.cv_shopby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(position==0){

                    context.startActivity(new Intent(context, ShowAllParentCategoriesActivity.class));
                }

                else if(position==1){

                    context.startActivity(new Intent(context, ShowAllBrandsActivity.class));
                }

                else {

                    context.startActivity(new Intent(context, ShowAllMakeszActivity.class));
                }


            }
        });




    }

    @Override
    public int getItemCount() {
        return shopByNameModels.size();
    }

    public static class ShopbyHolder extends RecyclerView.ViewHolder {
        TextView txt_shopbyname;

        CardView cv_shopby;
        public ShopbyHolder(View itemView) {

            super(itemView);

            txt_shopbyname = itemView.findViewById(R.id.txt_shopbyname);

            cv_shopby = itemView.findViewById(R.id.cv_shopby);

        }
    }
}