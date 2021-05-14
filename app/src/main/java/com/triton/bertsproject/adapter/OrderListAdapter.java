package com.triton.bertsproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.bertsproject.R;
import com.triton.bertsproject.model.OrderlistModel;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListHolder> {
    Context context;
    List<OrderlistModel> orderlistModels;
    View view;

    public OrderListAdapter(Context context, List<OrderlistModel> orderlistModels) {
        this.context = context;
        this.orderlistModels = orderlistModels ;

    }

    @NonNull
    @Override
    public OrderListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_orderlist, parent, false);

        return new OrderListHolder(view);
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull OrderListHolder holder, final int position) {

        final OrderlistModel orderlistModel = orderlistModels.get(position);

        if (orderlistModel.getProduct_name()!= null) {

                holder.txt_product_name.setText(orderlistModel.getProduct_name());

        }

        if (orderlistModel.getProdut_image()!= 0) {

                holder.img_product_image .setImageResource(orderlistModel.getProdut_image());

        }

        if (orderlistModel.getParts_no()!= null) {

            holder.txt_parts_name.setText(orderlistModel.getParts_no());

        }

        if (orderlistModel.getOrder_ID()!= null) {

            holder.txt_order_ID.setText(orderlistModel.getOrder_ID());

        }

        if (orderlistModel.getOrder_date()!= null) {

            holder.txt_orders.setText(orderlistModel.getOrder_date());

        }

        if (orderlistModel.getTotal_price()!= null) {

            String price = "\u0024"+orderlistModel.getTotal_price();

            holder.txt_price.setText(price);

        }

        if(orderlistModel.getOrder_status().equals("Completed")){

            holder.rl_order_status.setBackground(context.getResources().getDrawable(R.drawable.rectangle_corner_med_green_background));

            holder.txt_order_status.setText("Completed");

            holder.txt_order_status.setTextColor(context.getResources().getColor(R.color.white));
        }

        else if(orderlistModel.getOrder_status().equals("Cancelled")){

            holder.rl_order_status.setBackground(context.getResources().getDrawable(R.drawable.rectangle_corner_thick_red_background));

            holder.txt_order_status.setText("Cancelled");

            holder.txt_order_status.setTextColor(context.getResources().getColor(R.color.white));
        }

        else{

            holder.rl_order_status.setBackground(context.getResources().getDrawable(R.drawable.rectangle_corner_thickyellow_background));

            holder.txt_order_status.setText("On Going. . ");

            holder.txt_order_status.setTextColor(context.getResources().getColor(R.color.dark_grey));
        }




    }

    @Override
    public int getItemCount() {
        return orderlistModels.size();
    }

    public static class OrderListHolder extends RecyclerView.ViewHolder {
        ImageView img_product_image;
        TextView txt_product_name, txt_parts_name,txt_price,txt_orders,txt_order_ID,txt_order_status;
        RelativeLayout rl_order_status;

        public OrderListHolder(View itemView) {
            super(itemView);

            img_product_image = itemView.findViewById(R.id.img_product_image);

            txt_product_name = itemView.findViewById(R.id.txt_product_name);

            txt_parts_name = itemView.findViewById(R.id.txt_parts_no);

            txt_order_status = itemView.findViewById(R.id.txt_order_status);

            txt_order_ID = itemView.findViewById(R.id.txt_order_ID);

            txt_orders = itemView.findViewById(R.id.txt_orders);

            txt_price = itemView.findViewById(R.id.txt_total_price);

            rl_order_status = itemView.findViewById(R.id.rl_orderstatus);

        }
    }
}