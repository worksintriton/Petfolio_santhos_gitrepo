package com.dci.berts.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.dci.berts.R;
import com.dci.berts.responsepojo.FetchChildMakeslistRequestResponse;
import com.dci.berts.retailer.RetailerProductListBasedOnMakeActivity;
import com.dci.berts.sessionmanager.Connectivity;

import java.util.List;

public class ChildMakesListAdapter extends RecyclerView.Adapter<ChildMakesListAdapter.ShoplistHolder> {
    Context context;
    List<FetchChildMakeslistRequestResponse.DataBean.ModelBean> makesBeanList;
    View view;
    int size;
    private static final String TAG = "ChildMakesListAdapter";
    String fromactivity;

    public ChildMakesListAdapter(Context context, List<FetchChildMakeslistRequestResponse.DataBean.ModelBean> makesBeanLists, int size, String fromactivity) {
        this.context = context;
        this.makesBeanList = makesBeanLists;
        this.size = size;
        this.fromactivity = fromactivity;
    }

    @NonNull
    @Override
    public ShoplistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_modellist, parent, false);
        return new ShoplistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoplistHolder holder, final int position) {

        final FetchChildMakeslistRequestResponse.DataBean.ModelBean makesBean = makesBeanList.get(position);

        if (makesBean.getName()!= null && !makesBean.getName().isEmpty()) {

            holder.txt_shoplistname.setText(makesBean.getName());

        }

//        if (makesBean.getLogo()!= null && !makesBean.getLogo().isEmpty()) {
//
//            Glide.with(context)
//                    .load(makesBean.getLogo())
//                    .into(holder.img_shplst);
//
//
//        }

        holder.ll_shoplist.setOnClickListener(v -> {

            Intent intent = new Intent(context, RetailerProductListBasedOnMakeActivity.class);

            intent.putExtra("make_id",makesBean.getMake_id());

            intent.putExtra("make_name",makesBean.getMake_name());

            intent.putExtra("model_id",makesBean.getId());

            intent.putExtra("model_name",makesBean.getName());

            intent.putExtra("fromactivity", "ShowAllChildMakesActivity");

            Connectivity connectivity = new Connectivity();

            connectivity.storeData(context,"ChildMakes",fromactivity);

//            Log.w(TAG,"make_id : "+makesBean.getMake_id() + "model_id :" +makesBean.getId()
//
//                            + "model_name : "+makesBean.getName() +
//
//                            "fromactivity :" +TAG);

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
        LinearLayout ll_shoplist;
        public ShoplistHolder(View itemView) {
            super(itemView);

            img_shplst = itemView.findViewById(R.id.img_shplst);

            txt_shoplistname = itemView.findViewById(R.id.txt_shoplistname);

            ll_shoplist = itemView.findViewById(R.id.ll_shoplist);

        }
    }
}