package com.dci.berts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dci.berts.R;
import com.dci.berts.responsepojo.ShowCartListResponse;

import java.util.List;

public class ChekOutProductListAdapter extends RecyclerView.Adapter<ChekOutProductListAdapter.ShoplistHolder> {
    Context context;
    List<ShowCartListResponse.DataBean.CartBean> cartBeanList;
    View view;


    public ChekOutProductListAdapter(Context context2, List<ShowCartListResponse.DataBean.CartBean> cartBeanList) {
        this.context = context2;
        this.cartBeanList = cartBeanList;
    }

    @NonNull
    public ShoplistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_checkout_prodlist, parent, false);
        this.view = inflate;
        return new ShoplistHolder(inflate);
    }

    public void onBindViewHolder(@NonNull ShoplistHolder holder, int position) {
        ShowCartListResponse.DataBean.CartBean cartBean = this.cartBeanList.get(position);

        if (cartBean.getTitle() != null&&!cartBean.getTitle().isEmpty())  {
            holder.txt_product_name.setText(cartBean.getTitle());
        }

//        if (cartBean.getImages().get(0).getImage_default()!= null&&!cartBean.getImages().get(0).getImage_default().isEmpty()) {
//
//            String imgUrl = cartBean.getImages().get(0).getImage_default();
//
//            Glide.with(context)
//                    .load(imgUrl)
//                    .into(holder.img_product_image);
//
//        }
        if (cartBean.getPart_number() != null&&!cartBean.getPart_number().isEmpty()) {

            holder.txt_parts_no.setText(cartBean.getPart_number());

        }

        if (cartBean.getPrice() != null&&!cartBean.getPrice().isEmpty()) {

            String price = "\u0024" + cartBean.getPrice();

            holder.txt_price.setText(price);
        }
        if (cartBean.getBasket_quantity() != null&&!cartBean.getBasket_quantity().isEmpty()) {

            holder.txt_prod_count.setText("X "+cartBean.getBasket_quantity());

        }
    }


    public int getItemCount() {
        return cartBeanList.size();
    }

    public static class ShoplistHolder extends RecyclerView.ViewHolder {
        ImageView img_product_image;
        //   RatingBar ratingBar;
        TextView txt_parts_no;
        TextView txt_price;
        TextView txt_product_name,txt_prod_count;

        public ShoplistHolder(View itemView) {
            super(itemView);
            img_product_image = itemView.findViewById(R.id.img_product_image);
            txt_product_name = itemView.findViewById(R.id.txt_product_name);
            txt_parts_no = itemView.findViewById(R.id.txt_parts_name);
            // this.ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            txt_price = itemView.findViewById(R.id.txt_price);
            txt_prod_count = itemView.findViewById(R.id.txt_prod_count);

        }
    }
}
