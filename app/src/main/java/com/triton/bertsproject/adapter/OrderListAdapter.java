package com.triton.bertsproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.triton.bertsproject.R;
import com.triton.bertsproject.model.OrderlistModel;
import com.triton.bertsproject.responsepojo.ShowOrderlistResponse;
import com.triton.bertsproject.retailer.OrderDetailListActivity;
import com.triton.bertsproject.retailer.OrderListActivity;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListHolder> {
    Context context;
    List<ShowOrderlistResponse.DataBean.OrdersBean> ordersBeanList;
    View view;
    String address1,city,state,country,pincode;

    public OrderListAdapter(Context context, List<ShowOrderlistResponse.DataBean.OrdersBean> ordersBeanList) {
        this.context = context;
        this.ordersBeanList = ordersBeanList ;

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

        final ShowOrderlistResponse.DataBean.OrdersBean orderlistModel = ordersBeanList.get(position);


        if (orderlistModel.getOrder_number()!= null&&!orderlistModel.getOrder_number().isEmpty()) {

            holder.txt_order_ID.setText(orderlistModel.getOrder_number());

        }

        else {

            holder.txt_order_ID.setText("");
        }


        if (orderlistModel.getPrice_total()!= null&&!orderlistModel.getPrice_total().isEmpty()) {

            String price = "$ "+orderlistModel.getPrice_total();

            holder.txt_price.setText(price);

        }

        else {

            holder.txt_price.setText("$ 0");
        }


        if (orderlistModel.getProducts_count()!= 0) {

            holder.txt_orders.setText(""+orderlistModel.getProducts_count()+" Products");

        }

        else {

            holder.txt_orders.setText("$ 0");
        }

        if (orderlistModel.getStatus_name()!= null&&!orderlistModel.getStatus_name().isEmpty()) {

           if(orderlistModel.getStatus_name().equals("Cancelled")){

                holder.rl_order_status.setBackground(context.getResources().getDrawable(R.drawable.rectangle_corner_thick_red_background));

                holder.txt_order_status.setText("Cancelled");

                holder.txt_order_status.setTextColor(context.getResources().getColor(R.color.white));
            }

            else {

                holder.rl_order_status.setBackground(context.getResources().getDrawable(R.drawable.rectangle_corner_thickyellow_background));

                holder.txt_order_status.setText(""+orderlistModel.getStatus_name());

                holder.txt_order_status.setTextColor(context.getResources().getColor(R.color.white));
            }


        }

        else{

           holder.rl_order_status.setVisibility(View.GONE);
        }

        holder.cv_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, OrderDetailListActivity.class);

                intent.putExtra("order_id",orderlistModel.getId());

                context.startActivity(intent);
            }
        });

        if (orderlistModel.getShipping_address_1()!= null&&!orderlistModel.getShipping_address_1().isEmpty()) {

            address1 = orderlistModel.getShipping_address_1();

        }

        else {

            address1 = "";
        }

        if (orderlistModel.getShipping_city()!= null&&!orderlistModel.getShipping_city().isEmpty()) {

            city = orderlistModel.getShipping_city();

        }

        else {

            city = "";
        }
        if (orderlistModel.getShipping_state_name()!= null&&!orderlistModel.getShipping_state_name().isEmpty()) {

            state = orderlistModel.getShipping_state_name();

        }

        else {

            state = "";
        }

        if (orderlistModel.getShipping_country_name()!= null&&!orderlistModel.getShipping_country_name().isEmpty()) {

            country = orderlistModel.getShipping_country_name();

        }

        else {

            country = "";
        }

        if (orderlistModel.getShipping_zip_code()!= null&&!orderlistModel.getShipping_zip_code().isEmpty()) {

            pincode = orderlistModel.getShipping_zip_code();

        }

        else {

            pincode = "";
        }

        if (orderlistModel.getShipping_zip_code()!= null&&!orderlistModel.getShipping_zip_code().isEmpty()) {

            pincode = orderlistModel.getShipping_zip_code();

        }

        else {

            pincode = "";
        }
        holder.txt_orders_address.setText(address1+" "+city+" "+state+ " "+country+" "+pincode);
    }

    @Override
    public int getItemCount() {
        return ordersBeanList.size();
    }

    public static class OrderListHolder extends RecyclerView.ViewHolder {
        ImageView img_product_image;
        TextView txt_product_name, txt_parts_name,txt_price,txt_orders,txt_order_ID,txt_order_status,txt_orders_address;
        RelativeLayout rl_order_status;
        CardView cv_root;

        public OrderListHolder(View itemView) {
            super(itemView);

            txt_order_status = itemView.findViewById(R.id.txt_order_status);

            txt_order_ID = itemView.findViewById(R.id.txt_order_ID);

            txt_orders = itemView.findViewById(R.id.txt_orders);

            txt_price = itemView.findViewById(R.id.txt_total_price);

            rl_order_status = itemView.findViewById(R.id.rl_orderstatus);

            cv_root = itemView.findViewById(R.id.cv_root);

            txt_orders_address = itemView.findViewById(R.id.txt_orders_address);
        }
    }
}