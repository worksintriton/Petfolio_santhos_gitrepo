package com.triton.bertsproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.triton.bertsproject.R;
import com.triton.bertsproject.responsepojo.OrderDetailListResponse;

import java.util.List;


public class ProductDetailsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "ProductDetailsAdapter";

    List<OrderDetailListResponse.DataBean.OrdersBean.ProductsBean> productdetailslist;
    private final Context context;
    OrderDetailListResponse.DataBean.OrdersBean.ProductsBean currentItem;
    String orderid;
    String fromactivity;



    public ProductDetailsAdapter(Context context, List<OrderDetailListResponse.DataBean.OrdersBean.ProductsBean> productdetailslist) {
        this.context = context;
        this.productdetailslist = productdetailslist;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product_details, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = productdetailslist.get(position);


        if (currentItem.getProduct_title() != null&&!currentItem.getProduct_title().isEmpty()) {
            holder.txt_producttitle.setText(currentItem.getProduct_title());

        }

        if (currentItem.getProduct_total_price()!= null && !currentItem.getProduct_total_price().isEmpty()) {

                holder.txt_products_price.setText("$ " + currentItem.getProduct_total_price());
        }

//        if (currentItem.) {
//            holder.txt_bookedon.setText("Booked on:" + " " + product_details.get(position).getProduct_booked());
//
//        }

//        if (currentItem.getImages().get(0).getImage_default()!= null && !currentItem.getImages().get(0).getImage_default().isEmpty()) {
//            Glide.with(context)
//                    .load(currentItem.getImages().get(0).getImage_default())
//                    .into(holder.img_products_image);
//
//        }
//
//

        }

    @Override
    public int getItemCount() {
        return productdetailslist.size();

    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_orderid, txt_producttitle, txt_products_price, txt_bookedon, txt_track_order, txt_cancell_order,txt_product_status;
        public ImageView img_products_image;


        public ViewHolderOne(View itemView) {
            super(itemView);
            img_products_image = itemView.findViewById(R.id.img_products_image);

            txt_producttitle = itemView.findViewById(R.id.txt_producttitle);
            txt_products_price = itemView.findViewById(R.id.txt_products_price);
            txt_bookedon = itemView.findViewById(R.id.txt_bookedon);
            txt_product_status = itemView.findViewById(R.id.txt_product_status);
            txt_product_status.setVisibility(View.GONE);


        }


    }

}