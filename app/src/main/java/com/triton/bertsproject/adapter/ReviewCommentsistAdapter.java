package com.triton.bertsproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.triton.bertsproject.R;
import com.triton.bertsproject.responsepojo.FetchAllBrandsResponse;
import com.triton.bertsproject.responsepojo.ProductDetailRespone;
import com.triton.bertsproject.retailer.RetailerProductListActivity;

import java.util.List;

public class ReviewCommentsistAdapter extends RecyclerView.Adapter<ReviewCommentsistAdapter.ShoplistHolder> {
    Context context;
    List<ProductDetailRespone.DataBean.ProductsBean.ReviewsDetailsBean> reviewsDetailsBeanList ;
    View view;
    int size;
    private static final String TAG = "ReviewCommentsistAdapter";

    public ReviewCommentsistAdapter(Context context,   List<ProductDetailRespone.DataBean.ProductsBean.ReviewsDetailsBean> reviewsDetailsBeanList , int size) {
        this.context = context;
        this.reviewsDetailsBeanList = reviewsDetailsBeanList;
        this.size=size;

    }

    @NonNull
    @Override
    public ShoplistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_review_ratings, parent, false);
        return new ShoplistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoplistHolder holder, final int position) {

        final ProductDetailRespone.DataBean.ProductsBean.ReviewsDetailsBean reviewsDetailsBean = reviewsDetailsBeanList.get(position);

        if (reviewsDetailsBean.getReview()!= null && !reviewsDetailsBean.getReview().isEmpty()) {

            holder.txt_user_reviews.setText(reviewsDetailsBean.getReview());

        }

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public static class ShoplistHolder extends RecyclerView.ViewHolder {
        TextView txt_user_reviews;
        public ShoplistHolder(View itemView) {
            super(itemView);

            txt_user_reviews = itemView.findViewById(R.id.txt_user_reviews);


        }
    }
}