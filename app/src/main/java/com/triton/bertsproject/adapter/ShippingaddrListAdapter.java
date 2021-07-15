package com.triton.bertsproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.triton.bertsproject.R;
import com.triton.bertsproject.interfaces.DeleteAddressListener;
import com.triton.bertsproject.interfaces.EditAddressListener;
import com.triton.bertsproject.interfaces.SetDefaultAddressListener;
import com.triton.bertsproject.interfaces.WishlistAddProductListener;
import com.triton.bertsproject.responsepojo.ProductListResponse;
import com.triton.bertsproject.responsepojo.UserAddressListResponse;

import java.util.List;

public class ShippingaddrListAdapter extends RecyclerView.Adapter<ShippingaddrListAdapter.ShoplistHolder> {
    Context context;
    List<UserAddressListResponse.DataBean.AddressBean> addressBeanList;
    View view;
    boolean check;
    String pincode, district, state;
    SetDefaultAddressListener setDefaultAddressListener;
    DeleteAddressListener deleteAddressListener;
    EditAddressListener editAddressListener;

    public ShippingaddrListAdapter(Context context, List<UserAddressListResponse.DataBean.AddressBean> addressBeanList, SetDefaultAddressListener setDefaultAddressListener,DeleteAddressListener deleteAddressListener,EditAddressListener editAddressListener)
    {
        this.context = context;
        this.addressBeanList = addressBeanList ;
        this.setDefaultAddressListener=setDefaultAddressListener;
        this.deleteAddressListener=deleteAddressListener;
        this.editAddressListener=editAddressListener;
    }

    @NonNull
    @Override
    public ShoplistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_shipaddrlist, parent, false);

        return new ShoplistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoplistHolder holder, final int position) {

        final UserAddressListResponse.DataBean.AddressBean addressBean = addressBeanList.get(position);

        if(addressBean.getName()!=null&&!addressBean.getName().isEmpty()){

            holder.txt_username.setText(addressBean.getName());
        }

        if(addressBean.getPhone()!=null&&!addressBean.getPhone().isEmpty()){

            holder.txt_phnum.setText(addressBean.getPhone());
        }

        if(addressBean.getCity()!=null&&!addressBean.getCity().isEmpty()){

            holder.txt_user_city.setText(addressBean.getCity());
        }

        if(addressBean.getAddress1()!=null&&!addressBean.getAddress1().isEmpty()){

            holder.txt_street.setText(addressBean.getAddress1());
        }

        if(addressBean.getState_name()!=null&&!addressBean.getState_name().isEmpty()){

           state = addressBean.getState_name();
        }

        else{

            state = "";

        }

        if(addressBean.getCountry_name()!=null&&!addressBean.getCountry_name().isEmpty()){

            district = addressBean.getCountry_name();
        }

        else{
            district = "";
        }

        if(addressBean.getZipcode()!=null&&!addressBean.getZipcode().isEmpty()){

            pincode = addressBean.getZipcode();
        }

        else{
            pincode = "";
        }

        holder.txt_dist_pincode_state.setText(state+" "+district+" "+pincode);

        if(addressBean.getIs_default()!=null&&!addressBean.getIs_default().isEmpty()&&addressBean.getIs_default().equals("1")){

            holder.rv_default.setVisibility(View.VISIBLE);
        }

        else {

            holder.rv_default.setVisibility(View.GONE);
        }

        holder.cv_root.setOnClickListener(v -> setDefaultAddressListener.setshipListener(addressBean.getId()));

        holder.ll_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editAddressListener.editshipinfoListener(addressBean.getId(),addressBean.getName(),addressBean.getPhone(),addressBean.getAddress1(),addressBean.getAddress2(),addressBean.getCity(),addressBean.getCountry_id(),addressBean.getCountry_name(),addressBean.getState(),addressBean.getState_name(),addressBean.getZipcode(),addressBean.getIs_default());
            }
        });

        holder.ll_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteAddressListener.setshipidListener(addressBean.getId(),addressBean.getIs_default());
            }
        });

    }

    @Override
    public int getItemCount() {
        return addressBeanList.size();
    }

    public static class ShoplistHolder extends RecyclerView.ViewHolder {

        TextView txt_username, txt_phnum,txt_user_city,txt_street,txt_dist_pincode_state;
  //      RatingBar ratingBar;
        LinearLayout ll_edit,ll_delete;

        RelativeLayout rv_default;

        CardView cv_root;

        public ShoplistHolder(View itemView) {
            super(itemView);

            txt_username = itemView.findViewById(R.id.txt_username);

            txt_phnum = itemView.findViewById(R.id.txt_phnum);

            txt_user_city = itemView.findViewById(R.id.txt_user_city);

//            ratingBar = itemView.findViewById(R.id.ratingBar);

            txt_street = itemView.findViewById(R.id.txt_street);

            txt_dist_pincode_state = itemView.findViewById(R.id.txt_dist_pincode_state);

            ll_edit = itemView.findViewById(R.id.ll_edit);

            ll_delete = itemView.findViewById(R.id.ll_delete);

            rv_default = itemView.findViewById(R.id.rv_default);

            cv_root = itemView.findViewById(R.id.cv_root);
        }
    }
}