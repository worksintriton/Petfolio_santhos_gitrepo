package com.carpeinfinitus.petfolio.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.carpeinfinitus.petfolio.R;
import com.carpeinfinitus.petfolio.api.APIClient;
import com.carpeinfinitus.petfolio.petlover.PetVendorOrderDetailsActivity;
import com.carpeinfinitus.petfolio.petlover.PetVendorTrackOrderActivity;
import com.carpeinfinitus.petfolio.responsepojo.PetVendorOrderResponse;

import java.util.List;


public class PetVendorCancelledOrdersAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  String TAG = "PetVendorCancelledOrdersAdapter";
    private List<PetVendorOrderResponse.DataBean> newOrderResponseList;


    private Context context;

    PetVendorOrderResponse.DataBean currentItem;


    private int size;


    public PetVendorCancelledOrdersAdapter(Context context, List<PetVendorOrderResponse.DataBean> newOrderResponseList, int size) {
        this.newOrderResponseList = newOrderResponseList;
        this.context = context;
        this.size = size;




    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pet_cancelled_orders, parent, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initLayoutOne((ViewHolderOne) holder, position);


    }

    @SuppressLint("SetTextI18n")
    private void initLayoutOne(ViewHolderOne holder, final int position) {
        currentItem = newOrderResponseList.get(position);
        if(newOrderResponseList.get(position).getOrder_id() != null){
            holder.txt_orderid.setText(newOrderResponseList.get(position).getOrder_id());
        }

        if(newOrderResponseList.get(position).getProduct_name() != null) {
            holder.txt_producttitle.setText(newOrderResponseList.get(position).getProduct_name());
        }
        if(newOrderResponseList.get(position).getProduct_price() != 0 && newOrderResponseList.get(position).getProduct_quantity() != 0) {
            if(newOrderResponseList.get(position).getProduct_quantity() == 1){
                holder.txt_products_price.setText("INR " + newOrderResponseList.get(position).getProduct_price() + " (" + newOrderResponseList.get(position).getProduct_quantity() + " item )");
            }else{
                holder.txt_products_price.setText("INR " + newOrderResponseList.get(position).getProduct_price() + " (" + newOrderResponseList.get(position).getProduct_quantity() + " items )");

            }
        }
        else{
            if(newOrderResponseList.get(position).getProduct_quantity() == 1){
                holder.txt_products_price.setText("INR " + 0 + " (" + newOrderResponseList.get(position).getProduct_quantity() + " item )");
            }else{
                holder.txt_products_price.setText("INR " + 0 + " (" + newOrderResponseList.get(position).getProduct_quantity() + " items )");

            }

        }



        if(newOrderResponseList.get(position).getDate_of_booking() != null){
            holder.txt_bookedon.setText("Cancelled on:"+" "+newOrderResponseList.get(position).getDate_of_booking());

        }

        if (newOrderResponseList.get(position).getProdcut_image() != null && !newOrderResponseList.get(position).getProdcut_image().isEmpty()) {
            Glide.with(context)
                    .load(newOrderResponseList.get(position).getProdcut_image())
                    .into(holder.img_products_image);

        }
        else{
            Glide.with(context)
                    .load(APIClient.PROFILE_IMAGE_URL)
                    .into(holder.img_products_image);

        }
        holder.txt_order_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PetVendorOrderDetailsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("_id",newOrderResponseList.get(position).get_id());
                i.putExtra("fromactivity",TAG);
                context.startActivity(i);

            }
        });

        holder.txt_track_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PetVendorTrackOrderActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("_id",newOrderResponseList.get(position).get_id());
                i.putExtra("fromactivity",TAG);
                context.startActivity(i);


            }
        });








    }


    @Override
    public int getItemCount() {
        return Math.min(newOrderResponseList.size(), size);

    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView txt_orderid,txt_producttitle,txt_products_price,txt_bookedon,txt_order_details,txt_track_order;
        public ImageView img_products_image;



        public ViewHolderOne(View itemView) {
            super(itemView);
            img_products_image = itemView.findViewById(R.id.img_products_image);
            txt_orderid = itemView.findViewById(R.id.txt_orderid);
            txt_producttitle = itemView.findViewById(R.id.txt_producttitle);
            txt_products_price = itemView.findViewById(R.id.txt_products_price);
            txt_bookedon = itemView.findViewById(R.id.txt_bookedon);
            txt_order_details = itemView.findViewById(R.id.txt_order_details);
            txt_track_order = itemView.findViewById(R.id.txt_track_order);






        }




    }






}
