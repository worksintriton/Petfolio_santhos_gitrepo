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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.bertsproject.R;
import com.triton.bertsproject.interfaces.AddProductListener;
import com.triton.bertsproject.responsepojo.SearchProductsResponse;
import com.triton.bertsproject.retailer.ProductDetailDescriptionActivity;

import java.util.List;

public class SearchProductListAdapter extends RecyclerView.Adapter<SearchProductListAdapter.ShoplistHolder> {
    Context context;
    List<SearchProductsResponse.DataBean.ProductsBean> prdouctsBeanList ;
    View view;
    AddProductListener addProductListener;

    private final static String TAG = "SearchProductListAdapter";
    String search_text;

    public SearchProductListAdapter(Context context, List<SearchProductsResponse.DataBean.ProductsBean> prdouctsBeanList, AddProductListener addProductListener, String search_text) {
        this.context = context;
        this.prdouctsBeanList = prdouctsBeanList ;
        this.addProductListener = addProductListener;
        this.search_text = search_text;
    }

    @NonNull
    @Override
    public ShoplistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_searchprodlist, parent, false);

        return new ShoplistHolder(view);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull ShoplistHolder holder, final int position) {

        final SearchProductsResponse.DataBean.ProductsBean prdouctsBean = prdouctsBeanList.get(position);

        if (prdouctsBean.getTitle()!= null&&!prdouctsBean.getTitle().isEmpty()) {

            holder.txt_product_name.setText(prdouctsBean.getTitle());

        }

//        if (prdouctsBean.g().get(0).getImage_default()!= null&&!prdouctsBean.getImages().get(0).getImage_default().isEmpty()) {
//
//            String imgurl = prdouctsBean.getImages().get(0).getImage_default();
//
//            Glide.with(context)
//                    .load(imgurl)
//                    .into(holder.img_product_image);
//
//        }

        if (prdouctsBean.getPart_number()!= null&&!prdouctsBean.getPart_number().isEmpty()) {

            holder.txt_parts_name.setText(prdouctsBean.getPart_number());

        }

//        if (retailerProductlistModel.getRating()!= null) {
//
//            holder.ratingBar.setNumStars(Integer.parseInt(retailerProductlistModel.getRating()));
//
//        }

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

        if (prdouctsBean.getReviews_comments()!= 0) {

            String review = "(" + prdouctsBean.getReviews_comments() + ")";

            holder.txt_total_reviews.setText(review);

        }

        else {

            String review = "(" +" 0 "+ ")";

            holder.txt_total_reviews.setText(review);
        }

        if (prdouctsBean.getPrice()!= null) {

            String price = "$ "+prdouctsBean.getPrice();

            holder.txt_price.setText(price);

        }

        holder.btn_addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addProductListener.addproductListener(prdouctsBean.getId(),"1",prdouctsBean.getPrice(), holder.btn_addcart);

            }
        });


        holder.cv_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,ProductDetailDescriptionActivity.class);

                intent.putExtra("prod_id",prdouctsBean.getId());

                intent.putExtra("prod_name",prdouctsBean.getTitle());

                intent.putExtra("search_text",search_text);

                intent.putExtra("fromactivity","SearchProductListActivity");

                context.startActivity(intent);

            }
        });

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

            txt_parts_name = itemView.findViewById(R.id.txt_parts_no);

            cv_root = itemView.findViewById(R.id.cv_root);

//            ratingBar = itemView.findViewById(R.id.ratingBar);

            txt_total_reviews = itemView.findViewById(R.id.txt_total_reviews);

            txt_price = itemView.findViewById(R.id.txt_price);

            btn_addcart = itemView.findViewById(R.id.btn_addcart);

            hand_img1 = itemView.findViewById(R.id.hand_img1);
            hand_img2 = itemView.findViewById(R.id.hand_img2);
            hand_img3 = itemView.findViewById(R.id.hand_img3);
            hand_img4 = itemView.findViewById(R.id.hand_img4);
            hand_img5 = itemView.findViewById(R.id.hand_img5);


        }
    }
}