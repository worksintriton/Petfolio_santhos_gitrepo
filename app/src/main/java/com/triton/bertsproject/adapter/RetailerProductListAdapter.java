package com.triton.bertsproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.bertsproject.R;
import com.triton.bertsproject.model.RetailerProductlistModel;

import java.util.List;

public class RetailerProductListAdapter extends RecyclerView.Adapter<RetailerProductListAdapter.ShoplistHolder> {
    Context context;
    List<RetailerProductlistModel> retailerProductlistModels;
    View view;
    boolean check;

    public RetailerProductListAdapter(Context context, List<RetailerProductlistModel> retailerProductlistModels, boolean check) {
        this.context = context;
        this.retailerProductlistModels = retailerProductlistModels ;
        this.check = check;

    }

    @NonNull
    @Override
    public ShoplistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (check) {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_prodlist_listview, parent, false);

        }

        else {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_prodlist_gridview, parent, false);
        }

        return new ShoplistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoplistHolder holder, final int position) {

        final RetailerProductlistModel retailerProductlistModel = retailerProductlistModels.get(position);

        if (retailerProductlistModel.getProduct_name()!= null) {

                holder.txt_product_name.setText(retailerProductlistModel.getProduct_name());

        }

        if (retailerProductlistModel.getProdut_image()!= 0) {

                holder.img_product_image .setImageResource(retailerProductlistModel.getProdut_image());

        }

        if (retailerProductlistModel.getParts_no()!= null) {

            holder.txt_parts_name.setText(retailerProductlistModel.getParts_no());

        }

//        if (retailerProductlistModel.getRating()!= null) {
//
//            holder.ratingBar.setNumStars(Integer.parseInt(retailerProductlistModel.getRating()));
//
//        }

        if (retailerProductlistModel.getReview()!= null) {

            String review = "(" + retailerProductlistModel.getReview()+ ")";

            holder.txt_total_reviews.setText(review);

        }

        if (retailerProductlistModel.getPrice()!= null) {

            String price = ""+retailerProductlistModel.getPrice();

            holder.txt_price.setText(price);

        }

        if(!retailerProductlistModel.isOut_of_stock()){

            holder.txt_stock_status.setVisibility(View.GONE);
        }

        if(!retailerProductlistModel.isProduct_status()){

            holder.ll_product_status.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return retailerProductlistModels.size();
    }

    public static class ShoplistHolder extends RecyclerView.ViewHolder {
        ImageView img_product_image;
        TextView txt_product_name, txt_parts_name,txt_total_reviews,txt_price,txt_stock_status;
  //      RatingBar ratingBar;
        LinearLayout ll_product_status;

        public ShoplistHolder(View itemView) {
            super(itemView);

            img_product_image = itemView.findViewById(R.id.img_product_image);

            txt_product_name = itemView.findViewById(R.id.txt_product_name);

            txt_parts_name = itemView.findViewById(R.id.txt_parts_name);

//            ratingBar = itemView.findViewById(R.id.ratingBar);

            txt_total_reviews = itemView.findViewById(R.id.txt_total_reviews);

            txt_price = itemView.findViewById(R.id.txt_price);

            ll_product_status = itemView.findViewById(R.id.ll_product_status);

            txt_stock_status = itemView.findViewById(R.id.txt_stock_status);
        }
    }
}