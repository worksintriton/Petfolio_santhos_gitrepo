package com.triton.bertsproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.triton.bertsproject.R;
import com.triton.bertsproject.model.SearchProductlistModel;
import com.triton.bertsproject.retailer.RetailerCartActivity;

import java.util.List;

public class SearchProductListAdapter extends RecyclerView.Adapter<SearchProductListAdapter.ShoplistHolder> {
    Context context;
    List<SearchProductlistModel> searchProductlistModels;
    View view;

    public SearchProductListAdapter(Context context, List<SearchProductlistModel> searchProductlistModels) {
        this.context = context;
        this.searchProductlistModels = searchProductlistModels;

    }

    @NonNull
    @Override
    public ShoplistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_searchprodlist, parent, false);
        return new ShoplistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoplistHolder holder, final int position) {

        final SearchProductlistModel searchProductlistModel = searchProductlistModels.get(position);

        if (searchProductlistModel.getProduct_name()!= null) {

                holder.txt_product_name.setText(searchProductlistModel.getProduct_name());

        }

        if (searchProductlistModel.getProdut_image()!= 0) {

                holder.img_product_image .setImageResource(searchProductlistModel.getProdut_image());

        }

        if (searchProductlistModel.getParts_no()!= null) {

            holder.txt_parts_no.setText(searchProductlistModel.getParts_no());

        }

//        if (searchProductlistModel.getRating()!= null) {
//
//            holder.ratingBar.setNumStars(Integer.parseInt(searchProductlistModel.getRating()));
//
//        }

        if (searchProductlistModel.getReview()!= null) {

            String review = "(" + searchProductlistModel.getReview()+ " Reviews )";

            holder.txt_total_reviews.setText(review);

        }

        if (searchProductlistModel.getPrice()!= null) {

            String price = "\u0024"+searchProductlistModel.getPrice();

            holder.txt_price.setText(price);

        }

        holder.btn_addcart.setOnClickListener(v -> {

            context.startActivity(new Intent(context, RetailerCartActivity.class));

            Animatoo.animateSwipeRight(context);

        });
    }

    @Override
    public int getItemCount() {
        return searchProductlistModels.size();
    }

    public static class ShoplistHolder extends RecyclerView.ViewHolder {
        ImageView img_product_image;
        TextView txt_product_name, txt_parts_no,txt_total_reviews,txt_price;
       // RatingBar ratingBar;
        Button btn_addcart;

        public ShoplistHolder(View itemView) {
            super(itemView);

            img_product_image = itemView.findViewById(R.id.img_product_image);

            txt_product_name = itemView.findViewById(R.id.txt_product_name);

            txt_parts_no = itemView.findViewById(R.id.txt_parts_no);

     //       ratingBar = itemView.findViewById(R.id.ratingBar);

            txt_total_reviews = itemView.findViewById(R.id.txt_total_reviews);

            txt_price = itemView.findViewById(R.id.txt_price);

            btn_addcart = itemView.findViewById(R.id.btn_addcart);
        }
    }
}