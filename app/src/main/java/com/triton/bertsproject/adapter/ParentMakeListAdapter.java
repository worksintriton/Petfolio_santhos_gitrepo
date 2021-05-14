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
import com.triton.bertsproject.model.ShoplistModel;
import com.triton.bertsproject.retailer.RetailerProductListActivity;

import java.util.List;

public class ParentMakeListAdapter extends RecyclerView.Adapter<ParentMakeListAdapter.ShoplistHolder> {
    Context context;
    List<ShoplistModel> shoplistModels;
    View view;

    public ParentMakeListAdapter(Context context, List<ShoplistModel> shoplistModels) {
        this.context = context;
        this.shoplistModels = shoplistModels;

    }

    @NonNull
    @Override
    public ShoplistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_shoplist, parent, false);
        return new ShoplistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoplistHolder holder, final int position) {

        final ShoplistModel shoplistModel = shoplistModels.get(position);

        if (shoplistModel.getShoplistname()!= null) {

                holder.txt_shoplistname.setText(shoplistModel.getShoplistname());

        }

        if (shoplistModel.getImage()!= 0) {

                holder.img_shplst.setImageResource(shoplistModel.getImage());

        }

        holder.cardView.setOnClickListener(v -> {

            context.startActivity(new Intent(context, RetailerProductListActivity.class));

            Animatoo.animateSwipeLeft(context);
        });

    }

    @Override
    public int getItemCount() {
        return shoplistModels.size();
    }

    public static class ShoplistHolder extends RecyclerView.ViewHolder {
        ImageView img_shplst;
        TextView txt_shoplistname;
        CardView cardView;
        public ShoplistHolder(View itemView) {
            super(itemView);

            img_shplst = itemView.findViewById(R.id.img_shplst);

            txt_shoplistname = itemView.findViewById(R.id.txt_shoplistname);

            cardView = itemView.findViewById(R.id.cv_shoplist);

        }
    }
}