package com.triton.bertsproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.triton.bertsproject.R;
import com.triton.bertsproject.api.APIClient;
import com.triton.bertsproject.responsepojo.FetchAllBrandsResponse;
import com.triton.bertsproject.retailer.RetailerProductListActivity;
import com.triton.bertsproject.sessionmanager.Connectivity;

import java.util.List;

public class BrandListAdapter extends RecyclerView.Adapter<BrandListAdapter.ShoplistHolder> {
    Context context;
    List<FetchAllBrandsResponse.DataBean.BrandBean> brandsBeanList ;
    View view;
    int size;
    private static final String TAG = "BrandListAdapter";
    String fromactivity;
    Connectivity connectivity;

    public BrandListAdapter(Context context, List<FetchAllBrandsResponse.DataBean.BrandBean> brandsBeanLists, int size, String fromactivity) {
        this.context = context;
        this.brandsBeanList = brandsBeanLists;
        this.size=size;
        this.fromactivity=fromactivity;

    }

    @NonNull
    @Override
    public ShoplistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_shoplist, parent, false);
        return new ShoplistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoplistHolder holder, final int position) {

        final FetchAllBrandsResponse.DataBean.BrandBean brandsBean = brandsBeanList.get(position);

        if (brandsBean.getName()!= null && !brandsBean.getName().isEmpty()) {

            holder.txt_shoplistname.setText(brandsBean.getName());

        }

        if (brandsBean.getLogo()!= null && !brandsBean.getLogo().isEmpty()&& URLUtil.isValidUrl(brandsBean.getLogo())) {

            Log.w(TAG,"Brand_Img "+brandsBean.getLogo().replaceAll("[\\n\\r\\t]+", ""));

            Glide.with(context)
                    .load(brandsBean.getLogo().replaceAll("[\\n\\r\\t]+", ""))
                    .into(holder.img_shplst);


        }

        else {

            Glide.with(context)
                    .load(APIClient.BASE_IMAGE_URL)
                    .into(holder.img_shplst);
        }


        holder.cardView.setOnClickListener(v -> {

            Intent intent = new Intent(context, RetailerProductListActivity.class);

            intent.putExtra("brand_id",brandsBean.getId());

            intent.putExtra("brand_name",brandsBean.getName());

            intent.putExtra("fromactivity","ShowAllBrandsActivity");

            connectivity = new Connectivity();

            connectivity.storeData(context,"Brand",fromactivity);

            Log.w(TAG,"brand_id : "+brandsBean.getId());

            context.startActivity(intent);

            Animatoo.animateSwipeLeft(context);
        });

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public static class ShoplistHolder extends RecyclerView.ViewHolder {
        ImageView img_shplst;
        TextView txt_shoplistname;
        CardView cardView;
        public ShoplistHolder(View itemView) {
            super(itemView);

            img_shplst = itemView.findViewById(R.id.img_shplst);

            txt_shoplistname = itemView.findViewById(R.id.txt_shoplistname);

            cardView = itemView.findViewById(R.id.cv_shoplist);

        }
    }
}