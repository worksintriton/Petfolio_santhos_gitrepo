package com.dci.berts.adapter;

import android.annotation.SuppressLint;
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
import com.dci.berts.responsepojo.FetchAllParentCategoriesResponse;
import com.dci.berts.retailer.ShowAllChildCategActivity;
import com.dci.berts.sessionmanager.Connectivity;

import java.util.List;

public class ParentCategoriesListAdapter extends RecyclerView.Adapter<ParentCategoriesListAdapter.ViewHolder> {
    Context context;
    List<FetchAllParentCategoriesResponse.DataBean.CategoriesBean> categoriesBeanList;
    View view;
    int size;
    private static final String TAG = "ParentCategoriesListAdapter";
    String fromactivity;

    public ParentCategoriesListAdapter(Context context, List<FetchAllParentCategoriesResponse.DataBean.CategoriesBean> categoriesBeanLists, int size, String fromactivity) {
        this.context = context;
        this.categoriesBeanList = categoriesBeanLists;
        this.size=size;
        this.fromactivity=fromactivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_shoplist, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final FetchAllParentCategoriesResponse.DataBean.CategoriesBean categoriesBean = categoriesBeanList.get(position);

        if (categoriesBean.getName()!= null && !categoriesBean.getName().isEmpty()) {

                holder.txt_shoplistname.setText(categoriesBean.getName());

        }


        if (categoriesBean.getImage_1()!= null && !categoriesBean.getImage_1().isEmpty()&& URLUtil.isValidUrl(categoriesBean.getImage_1())) {

            Log.w(TAG,"Cat_Img "+categoriesBean.getImage_1().replaceAll("[\\n\\r\\t]+", ""));

            Glide.with(context)
                    .load(categoriesBean.getImage_1().replaceAll("[\\n\\r\\t]+", ""))
                    .into(holder.img_shplst);


        }

        else {

            Glide.with(context)
                    .load(APIClient.BASE_IMAGE_URL)
                    .into(holder.img_shplst);
        }



        holder.cardView.setOnClickListener(v -> {

            Intent intent = new Intent(context, ShowAllChildCategActivity.class);

            intent.putExtra("fromactivity","ShowAllParentCategoriesActivity");

            intent.putExtra("cate_id",categoriesBean.getId());

            intent.putExtra("cate_name",categoriesBean.getName());

            Connectivity connectivity = new Connectivity();

            connectivity.storeData(context,"ParentCategories",fromactivity);

          //  Log.w(TAG,"parent_id : "+categoriesBean.getId() +"categ_name : "+categoriesBean.getParent_id());

            context.startActivity(intent);

            Animatoo.animateSwipeLeft(context);
        });

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_shplst;
        TextView txt_shoplistname;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);

            img_shplst = itemView.findViewById(R.id.img_shplst);

            txt_shoplistname = itemView.findViewById(R.id.txt_shoplistname);

            cardView = itemView.findViewById(R.id.cv_shoplist);

        }
    }
}