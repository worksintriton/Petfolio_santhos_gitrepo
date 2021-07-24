package com.dci.berts.adapter;

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
import com.dci.berts.R;
import com.dci.berts.api.APIClient;
import com.dci.berts.responsepojo.FetchAllParentMakesResponse;
import com.dci.berts.retailer.ShowAllChildMakesActivity;
import com.dci.berts.sessionmanager.Connectivity;

import java.util.List;

public class ParentMakesListAdapter extends RecyclerView.Adapter<ParentMakesListAdapter.ShoplistHolder> {
    Context context;
    List<FetchAllParentMakesResponse.DataBean.MakeBean> makesBeanList;
    View view;
    int size;
    private static final String TAG = "ParentMakesListAdapter";
    String fromactivity;
    Connectivity connectivity;

    public ParentMakesListAdapter(Context context, List<FetchAllParentMakesResponse.DataBean.MakeBean> makesBeanLists, int size, String fromactivity) {
        this.context = context;
        this.makesBeanList = makesBeanLists;
        this.size = size;
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

        final FetchAllParentMakesResponse.DataBean.MakeBean makesBean = makesBeanList.get(position);

        if (makesBean.getName()!= null && !makesBean.getName().isEmpty()) {

            holder.txt_shoplistname.setText(makesBean.getName());

        }

        if (makesBean.getLogo()!= null && !makesBean.getLogo().isEmpty()&& URLUtil.isValidUrl(makesBean.getLogo())) {

            Log.w(TAG,"Makes_Img "+makesBean.getLogo().replaceAll("[\\n\\r\\t]+", ""));

            Glide.with(context)
                    .load(makesBean.getLogo().replaceAll("[\\n\\r\\t]+", ""))
                    .into(holder.img_shplst);


        }

        else {

            Glide.with(context)
                    .load(APIClient.BASE_IMAGE_URL)
                    .into(holder.img_shplst);
        }



        holder.cardView.setOnClickListener(v -> {

            Intent intent = new Intent(context, ShowAllChildMakesActivity.class);

            intent.putExtra("fromactivity","ShowAllParentMakesActivity");

            intent.putExtra("make_id",makesBean.getId());

            intent.putExtra("make_name",makesBean.getName());

            connectivity=new Connectivity();

            connectivity.storeData(context,"ParentMakes",fromactivity);

            Log.w(TAG,"make_id " +makesBean.getId() + "make_name "+makesBean.getName());

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