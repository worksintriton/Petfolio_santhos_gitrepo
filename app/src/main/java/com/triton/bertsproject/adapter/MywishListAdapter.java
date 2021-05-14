package com.triton.bertsproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.bertsproject.R;
import com.triton.bertsproject.model.RetailerProductlistModel;

import java.util.List;

public class MywishListAdapter extends RecyclerView.Adapter<MywishListAdapter.ShoplistHolder> {
    Context context;
    List<RetailerProductlistModel> retailerProductlistModels;
    View view;

    public MywishListAdapter(Context context2, List<RetailerProductlistModel> retailerProductlistModels2) {
        this.context = context2;
        this.retailerProductlistModels = retailerProductlistModels2;
    }

    @NonNull
    public ShoplistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cart_prodlist, parent, false);
        this.view = inflate;
        return new ShoplistHolder(inflate);
    }

    public void onBindViewHolder(@NonNull ShoplistHolder holder, int position) {
        RetailerProductlistModel retailerProductlistModel = this.retailerProductlistModels.get(position);
        if (retailerProductlistModel.getProduct_name() != null) {
            holder.txt_product_name.setText(retailerProductlistModel.getProduct_name());
        }
        if (retailerProductlistModel.getProdut_image() != 0) {
            holder.img_product_image.setImageResource(retailerProductlistModel.getProdut_image());
        }
        if (retailerProductlistModel.getParts_no() != null) {
            holder.txt_parts_no.setText(retailerProductlistModel.getParts_no());
        }
//        if (retailerProductlistModel.getRating() != null) {
//            holder.ratingBar.setNumStars(Integer.parseInt(retailerProductlistModel.getRating()));
//        }
        if (retailerProductlistModel.getReview() != null) {

            String review = retailerProductlistModel.getReview() + " Reviews";

            holder.txt_total_reviews.setText(review);
        }
        if (retailerProductlistModel.getPrice() != null) {

            String price = "\u0024" + retailerProductlistModel.getPrice();

            holder.txt_price.setText(price);
        }

        //holder.cardView.setRadius(20);

        holder.linearLayout.setVisibility(View.GONE);

        holder.ll_multipleadd.setVisibility(View.GONE);

        holder.img_delete.setVisibility(View.GONE);
    }

    public void removeItem(int position) {
        this.retailerProductlistModels.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(RetailerProductlistModel item, int position) {
        this.retailerProductlistModels.add(position, item);
        notifyItemInserted(position);
    }

    public List<RetailerProductlistModel> getData() {
        return this.retailerProductlistModels;
    }

    public int getItemCount() {
        return this.retailerProductlistModels.size();
    }

    public static class ShoplistHolder extends RecyclerView.ViewHolder {
        ImageView img_product_image,img_delete;
        //RatingBar ratingBar;
        TextView txt_parts_no;
        TextView txt_price;
        TextView txt_product_name;
        TextView txt_total_reviews;
        LinearLayout linearLayout,ll_multipleadd;
        CardView cardView;
        public ShoplistHolder(View itemView) {
            super(itemView);
            img_product_image = itemView.findViewById(R.id.img_product_image);
            img_delete = itemView.findViewById(R.id.img_delete);
            txt_product_name = itemView.findViewById(R.id.txt_product_name);
            txt_parts_no =  itemView.findViewById(R.id.txt_parts_name);
           // ratingBar = itemView.findViewById(R.id.ratingBar);
            txt_total_reviews = itemView.findViewById(R.id.txt_total_reviews);
            txt_price =  itemView.findViewById(R.id.txt_price);
            linearLayout =  itemView.findViewById(R.id.ll_root);
            ll_multipleadd =  itemView.findViewById(R.id.ll_multipleadd);
            cardView =  itemView.findViewById(R.id.cv_root);
        }
    }
}
