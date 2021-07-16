package com.triton.bertsproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.triton.bertsproject.R;
import com.triton.bertsproject.interfaces.AddProductListener;
import com.triton.bertsproject.interfaces.ProductListener;
import com.triton.bertsproject.interfaces.WishlistAddProductListener;
import com.triton.bertsproject.model.RetailerProductlistModel;
import com.triton.bertsproject.responsepojo.ProductListResponse;
import com.triton.bertsproject.retailer.ProductDetailDescriptionActivity;

import java.util.List;

public class RetailerProductListAdapter extends RecyclerView.Adapter<RetailerProductListAdapter.ShoplistHolder> {
    Context context;
    List<ProductListResponse.DataBean.ProductsBean> prdouctsBeanList ;
    View view;
    boolean check;
    WishlistAddProductListener wishlistAddProductListener;
    ProductListener productListener;

    private final static String TAG = "RetailerProductListAdapter";
    AddProductListener addProductListener;

    public RetailerProductListAdapter(Context context,List<ProductListResponse.DataBean.ProductsBean> prdouctsBeanList , boolean check, WishlistAddProductListener wishlistAddProductListener, ProductListener productListener,AddProductListener addProductListener) {
        this.context = context;
        this.prdouctsBeanList = prdouctsBeanList ;
        this.check = check;
        this.wishlistAddProductListener=wishlistAddProductListener;
        this.addProductListener = addProductListener;
        this.productListener = productListener;

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

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull ShoplistHolder holder, final int position) {

        final ProductListResponse.DataBean.ProductsBean prdouctsBean = prdouctsBeanList.get(position);

        if (prdouctsBean.getTitle()!= null&&!prdouctsBean.getTitle().isEmpty()) {

                holder.txt_product_name.setText(prdouctsBean.getTitle());

        }
/*

        if (prdouctsBean.getImages().get(0).getImage_default()!= null&&!prdouctsBean.getImages().get(0).getImage_default().isEmpty()) {

                String imgurl = prdouctsBean.getImages().get(0).getImage_default();

                    Glide.with(context)
                    .load(imgurl)
                    .into(holder.img_product_image);

        }
*/

        if (prdouctsBean.getPart_number()!= null&&!prdouctsBean.getPart_number().isEmpty()) {

            holder.txt_parts_name.setText(prdouctsBean.getPart_number());

        }

//        if (retailerProductlistModel.getRating()!= null) {
//
//            holder.ratingBar.setNumStars(Integer.parseInt(retailerProductlistModel.getRating()));
//
//        }

        if (prdouctsBean.getReviews_comments()!= 0) {

            String review = "(" + prdouctsBean.getReviews_comments() + ")";

            holder.txt_total_reviews.setText(review);

        }

        else {

            holder.txt_total_reviews.setText("( 0 )");
        }

        if (prdouctsBean.getPrice()!= null) {

            String price = "$ "+prdouctsBean.getPrice();

            holder.txt_price.setText(price);

        }

        else {

            holder.txt_price.setText("$ 0");
        }

        if(prdouctsBean.getQuantity()!=null&&!prdouctsBean.getQuantity().isEmpty()){

            String qty = prdouctsBean.getQuantity();

            if(qty.equals("0")){

                holder.txt_stock_status.setVisibility(View.VISIBLE);

            }

            else {

                holder.txt_stock_status.setVisibility(View.GONE);
            }


        }

        if(prdouctsBean.getTag_new()!=null&&!prdouctsBean.getTag_new().isEmpty()){

            String tag_new = prdouctsBean.getTag_new();

            if(tag_new.equals("1")){

                holder.ll_product_status.setVisibility(View.VISIBLE);

            }

            else {

                holder.ll_product_status.setVisibility(View.GONE);
            }
        }

        if(prdouctsBean.getWishlist()==1){

            holder.img_heart.setImageResource(R.drawable.like);
        }

        else {

            holder.img_heart.setImageResource(R.drawable.ic_heart_outline);
        }

        holder.img_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wishlistAddProductListener.addproductListener(prdouctsBean.getId());

            }
        });

        holder.btn_addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addProductListener.addproductListener(prdouctsBean.getId(),"1",prdouctsBean.getPrice(),holder.btn_addcart);

            }
        });

        holder.cv_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                productListener.productListener(prdouctsBean.getId(),prdouctsBean.getTitle());
            }
        });

        Log.w(TAG,"rating : "+ prdouctsBean.getRating());
        if(prdouctsBean.getRating()!=null&&!prdouctsBean.getRating().isEmpty()&&prdouctsBean.getRating().equals("1")){
            holder.hand_img1.setBackgroundResource(R.drawable.ic_star_filled);
            holder.hand_img2.setBackgroundResource(R.drawable.ic_star_empty);
            holder.hand_img3.setBackgroundResource(R.drawable.ic_star_empty);
            holder.hand_img4.setBackgroundResource(R.drawable.ic_star_empty);
            holder.hand_img5.setBackgroundResource(R.drawable.ic_star_empty);
        } else if(prdouctsBean.getRating()!=null&&!prdouctsBean.getRating().isEmpty()&&prdouctsBean.getRating().equals("2")){
            holder.hand_img1.setBackgroundResource(R.drawable.ic_star_filled);
            holder.hand_img2.setBackgroundResource(R.drawable.ic_star_filled);
            holder.hand_img3.setBackgroundResource(R.drawable.ic_star_empty);
            holder.hand_img4.setBackgroundResource(R.drawable.ic_star_empty);
            holder.hand_img5.setBackgroundResource(R.drawable.ic_star_empty);
        }else if(prdouctsBean.getRating()!=null&&!prdouctsBean.getRating().isEmpty()&&prdouctsBean.getRating().equals("3")){
            holder.hand_img1.setBackgroundResource(R.drawable.ic_star_filled);
            holder.hand_img2.setBackgroundResource(R.drawable.ic_star_filled);
            holder.hand_img3.setBackgroundResource(R.drawable.ic_star_filled);
            holder.hand_img4.setBackgroundResource(R.drawable.ic_star_empty);
            holder.hand_img5.setBackgroundResource(R.drawable.ic_star_empty);
        }else if(prdouctsBean.getRating()!=null&&!prdouctsBean.getRating().isEmpty()&&prdouctsBean.getRating().equals("4")){
            holder.hand_img1.setBackgroundResource(R.drawable.ic_star_filled);
            holder.hand_img2.setBackgroundResource(R.drawable.ic_star_filled);
            holder.hand_img3.setBackgroundResource(R.drawable.ic_star_filled);
            holder.hand_img4.setBackgroundResource(R.drawable.ic_star_filled);
            holder.hand_img5.setBackgroundResource(R.drawable.ic_star_empty);
        } else if(prdouctsBean.getRating()!=null&&!prdouctsBean.getRating().isEmpty()&&prdouctsBean.getRating().equals("5")){
            holder.hand_img1.setBackgroundResource(R.drawable.ic_star_filled);
            holder.hand_img2.setBackgroundResource(R.drawable.ic_star_filled);
            holder.hand_img3.setBackgroundResource(R.drawable.ic_star_filled);
            holder.hand_img4.setBackgroundResource(R.drawable.ic_star_filled);
            holder.hand_img5.setBackgroundResource(R.drawable.ic_star_filled);
        }
        else {

            holder.hand_img1.setBackgroundResource(R.drawable.ic_star_empty);
            holder.hand_img2.setBackgroundResource(R.drawable.ic_star_empty);
            holder.hand_img3.setBackgroundResource(R.drawable.ic_star_empty);
            holder.hand_img4.setBackgroundResource(R.drawable.ic_star_empty);
            holder.hand_img5.setBackgroundResource(R.drawable.ic_star_empty);
        }

    }

    @Override
    public int getItemCount() {
        return prdouctsBeanList.size();
    }

    public static class ShoplistHolder extends RecyclerView.ViewHolder {
        ImageView img_product_image,img_heart;
        TextView txt_product_name, txt_parts_name,txt_total_reviews,txt_price,txt_stock_status;
  //      RatingBar ratingBar;
        LinearLayout ll_product_status;
        public ImageView hand_img1,hand_img2,hand_img3,hand_img4,hand_img5;
        CardView cv_root;
        Button btn_addcart;

        public ShoplistHolder(View itemView) {
            super(itemView);

            img_product_image = itemView.findViewById(R.id.img_product_image);

            txt_product_name = itemView.findViewById(R.id.txt_product_name);

            txt_parts_name = itemView.findViewById(R.id.txt_parts_name);

            cv_root = itemView.findViewById(R.id.cv_root);

            btn_addcart = itemView.findViewById(R.id.btn_addcart);

//            ratingBar = itemView.findViewById(R.id.ratingBar);

            hand_img1 = itemView.findViewById(R.id.hand_img1);
            hand_img2 = itemView.findViewById(R.id.hand_img2);
            hand_img3 = itemView.findViewById(R.id.hand_img3);
            hand_img4 = itemView.findViewById(R.id.hand_img4);
            hand_img5 = itemView.findViewById(R.id.hand_img5);


            txt_total_reviews = itemView.findViewById(R.id.txt_total_reviews);

            txt_price = itemView.findViewById(R.id.txt_price);

            ll_product_status = itemView.findViewById(R.id.ll_product_status);

            txt_stock_status = itemView.findViewById(R.id.txt_stock_status);

            img_heart = itemView.findViewById(R.id.img_heart);
        }
    }
}