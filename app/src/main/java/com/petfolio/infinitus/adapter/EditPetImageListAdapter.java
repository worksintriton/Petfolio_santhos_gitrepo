package com.petfolio.infinitus.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.petfolio.infinitus.R;
import com.petfolio.infinitus.requestpojo.PetAddImageRequest;
import com.petfolio.infinitus.responsepojo.PetListResponse;

import java.util.List;

public class EditPetImageListAdapter extends RecyclerView.Adapter<EditPetImageListAdapter.AddImageListHolder> {
    private String TAG = "EditPetImageListAdapter";
    Context context;
    List<PetListResponse.DataBean.PetImgBean> pet_img;
    View view;

    public EditPetImageListAdapter(Context context, List<PetListResponse.DataBean.PetImgBean> pet_img) {
        this.context = context;
        this.pet_img = pet_img;


    }

    @NonNull
    @Override
    public AddImageListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_images_upload, parent, false);

        return new AddImageListHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AddImageListHolder holder, final int position) {
        final PetListResponse.DataBean.PetImgBean petImgBean = pet_img.get(position);

        Log.w(TAG,"ImagePic : "+petImgBean.getPet_img());

        if (petImgBean.getPet_img()!= null) {
            Glide.with(context)
                    .load(petImgBean.getPet_img())
                    .into(holder.certificate_pics_1);

        }

        holder.removeImg.setOnClickListener(view -> {
            pet_img.remove(position);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return pet_img.size();

    }

    public static class AddImageListHolder extends RecyclerView.ViewHolder {
        ImageView removeImg,certificate_pics_1;
        public AddImageListHolder(View itemView) {
            super(itemView);
            certificate_pics_1 = itemView.findViewById(R.id.certificate_pics_1);
            removeImg = itemView.findViewById(R.id.close);
        }
    }


}