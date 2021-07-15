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

import com.bumptech.glide.Glide;
import com.triton.bertsproject.R;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.interfaces.WishlistAddProductListener;
import com.triton.bertsproject.model.RetailerProductlistModel;
import com.triton.bertsproject.responsepojo.WishlistSuccessResponse;

import java.util.List;

public class MywishListAdapter extends RecyclerView.Adapter<MywishListAdapter.ShoplistHolder> {
    Context context;
    List<WishlistSuccessResponse.DataBean.WishlistBean> wishlistBeanList;
    View view;
    WishlistAddProductListener wishlistAddProductListener;

    public MywishListAdapter(Context context2, List<WishlistSuccessResponse.DataBean.WishlistBean> wishlistBeanList, WishlistAddProductListener wishlistAddProductListener) {
        this.context = context2;
        this.wishlistBeanList = wishlistBeanList;
        this.wishlistAddProductListener = wishlistAddProductListener;
    }

    @NonNull
    public ShoplistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cart_prodlist, parent, false);
        this.view = inflate;
        return new ShoplistHolder(inflate);
    }

    public void onBindViewHolder(@NonNull ShoplistHolder holder, int position) {
        WishlistSuccessResponse.DataBean.WishlistBean wishlistBean = this.wishlistBeanList.get(position);

        if (wishlistBean.getTitle() != null&&!wishlistBean.getTitle().isEmpty())  {
            holder.txt_product_name.setText(wishlistBean.getTitle());
        }

        if(wishlistBean.getImages()!=null&&wishlistBean.getImages().size()>0){

            if (wishlistBean.getImages().get(0).getImage_default()!= null&&!wishlistBean.getImages().get(0).getImage_default().isEmpty()) {

                String imgUrl = wishlistBean.getImages().get(0).getImage_default();

                Glide.with(context)
                        .load(imgUrl)
                        .into(holder.img_product_image);

            }

            else {

                Glide.with(context)
                        .load(APIClient.BASE_IMAGE_URL)
                        .into(holder.img_product_image);
            }
        }

//        if (retailerProductlistModel.getParts_no() != null) {
//            holder.txt_parts_no.setText(retailerProductlistModel.getParts_no());
//        }
////        if (retailerProductlistModel.getRating() != null) {
////            holder.ratingBar.setNumStars(Integer.parseInt(retailerProductlistModel.getRating()));
////        }
//        if (retailerProductlistModel.getReview() != null) {
//
//            String review = retailerProductlistModel.getReview() + " Reviews";
//
//            holder.txt_total_reviews.setText(review);
//        }
        if (wishlistBean.getPrice() != null) {

            String price = "\u0024" + wishlistBean.getPrice();

            holder.txt_price.setText(price);
        }

        //holder.cardView.setRadius(20);

        holder.linearLayout.setVisibility(View.GONE);

        holder.ll_multipleadd.setVisibility(View.GONE);

        holder.img_delete.setImageResource(R.drawable.heart_outline);

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wishlistAddProductListener.addproductListener(wishlistBean.getWishlist_product_id());
            }
        });

    }

    public int getItemCount() {
        return this.wishlistBeanList.size();
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
