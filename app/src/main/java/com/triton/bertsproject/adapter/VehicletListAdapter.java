package com.triton.bertsproject.adapter;

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

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.triton.bertsproject.R;
import com.triton.bertsproject.interfaces.CartRemoveProductListener;
import com.triton.bertsproject.interfaces.SetDefaultVehicleProductListener;
import com.triton.bertsproject.responsepojo.ShowCartListResponse;
import com.triton.bertsproject.responsepojo.ShowVehiclelistResponse;
import com.triton.bertsproject.retailer.EditVehicleActivity;
import com.triton.bertsproject.retailer.ShowAllChildCategActivity;

import java.util.List;

import butterknife.ButterKnife;

public class VehicletListAdapter extends RecyclerView.Adapter<VehicletListAdapter.ShoplistHolder> {
    Context context;
    List<ShowVehiclelistResponse.DataBean.AddvehicleBean> addvehicleBeanList;
    View view;
    SetDefaultVehicleProductListener setDefaultVehicleProductListener;

    private static final String TAG = "VehicletListAdapter";

    String make_name,model_name,year;

    public VehicletListAdapter(Context context2, List<ShowVehiclelistResponse.DataBean.AddvehicleBean> addvehicleBeanList,  SetDefaultVehicleProductListener setDefaultVehicleProductListener) {
        this.context = context2;
        this.addvehicleBeanList = addvehicleBeanList;
        this.setDefaultVehicleProductListener=setDefaultVehicleProductListener;
    }

    @NonNull
    public ShoplistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_vehlist, parent, false);
        this.view = inflate;
        return new ShoplistHolder(inflate);
    }

    public void onBindViewHolder(@NonNull ShoplistHolder holder, int position) {
        ShowVehiclelistResponse.DataBean.AddvehicleBean addvehicleBean= addvehicleBeanList.get(position);

        if (addvehicleBean.getAv_year() != null&&!addvehicleBean.getAv_year().isEmpty())  {

            year = addvehicleBean.getAv_year();

        }

        else {

            year="";
        }

        if (addvehicleBean.getMake_name() != null&&!addvehicleBean.getMake_name().isEmpty())  {

            make_name = addvehicleBean.getMake_name();
        }

        else {

            make_name="";
        }

        if (addvehicleBean.getModel_name() != null&&!addvehicleBean.getModel_name().isEmpty())  {

            model_name = addvehicleBean.getModel_name();
        }

        else {

            model_name="";
        }

        holder.txt_select_vehc2.setText(year + " " + make_name +" "+ model_name);

        holder.txt_select_vehc3.setVisibility(View.INVISIBLE);

        if(addvehicleBean.getAv_default().equals("1")){

            holder.txt_select_vehc.setText("Selected Vehicle");

            holder.txt_select_vehc.setTextColor(context.getResources().getColor(R.color.white));

            holder.txt_select_vehc2.setTextColor(context.getResources().getColor(R.color.white));

            holder.txt_select_vehc3.setVisibility(View.INVISIBLE);

            //holder.txt_select_vehc3.setTextColor(context.getResources().getColor(R.color.white));

            holder.img_vehc_icon.setImageResource(R.drawable.tick);

            holder.cv_root.setCardBackgroundColor(context.getResources().getColor(R.color.dark_blue));

            holder.btn_selveh.setVisibility(View.GONE);

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)holder.ll_root.getLayoutParams();

            params.setMargins(0, 0, 0, 20);

            holder.ll_root.setLayoutParams(params);
        }

        holder.btn_selveh.setOnClickListener(v -> setDefaultVehicleProductListener.setVehicleidListener(addvehicleBean.getAv_id()));


        holder.img_edit.setOnClickListener(v -> {

            Intent intent = new Intent(context, EditVehicleActivity.class);

            intent.putExtra("av_id",addvehicleBean.getAv_id());

            intent.putExtra("make_id",addvehicleBean.getAv_make_id());

            intent.putExtra("model_id",addvehicleBean.getAv_model_id());

            intent.putExtra("Year",addvehicleBean.getAv_year());

            intent.putExtra("make_name",addvehicleBean.getMake_name());

            intent.putExtra("model_name",addvehicleBean.getModel_name());

            intent.putExtra("default_av_id",addvehicleBean.getAv_default());

            Log.w(TAG,"av_id : "+addvehicleBean.getAv_id() +"make_id : "+addvehicleBean.getAv_make_id()

            + "model_id "+addvehicleBean.getAv_model_id() + "Year" +addvehicleBean.getAv_year()

            + "make_name "+addvehicleBean.getMake_name() + "model_name "+addvehicleBean.getModel_name()+

                     "default_av_id" +addvehicleBean.getAv_default());

            context.startActivity(intent);

            Animatoo.animateSwipeLeft(context);

        });

    }


    public int getItemCount() {
        return addvehicleBeanList.size();
    }

    public static class ShoplistHolder extends RecyclerView.ViewHolder {
        ImageView img_vehc_icon,img_edit,img_delete;
        Button btn_selveh;
        TextView txt_select_vehc;
        TextView txt_select_vehc2;
        TextView txt_select_vehc3;
        CardView cv_root;
        LinearLayout ll_root;

        public ShoplistHolder(View itemView) {

            super(itemView);

            img_vehc_icon = itemView.findViewById(R.id.img_vehc_icon);

            txt_select_vehc = itemView.findViewById(R.id.txt_select_vehc);

            txt_select_vehc2 = itemView.findViewById(R.id.txt_select_vehc2);

            btn_selveh = itemView.findViewById(R.id.btn_selveh);

//            img_delete = itemView.findViewById(R.id.img_delete);

            img_edit = itemView.findViewById(R.id.img_edit);

            txt_select_vehc3 = itemView.findViewById(R.id.txt_select_vehc3);

            cv_root = itemView.findViewById(R.id.cv_root);

            ll_root = itemView.findViewById(R.id.ll_root);

        }
    }
}
