package com.carpeinfinitus.petfolio.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.carpeinfinitus.petfolio.R;
import com.carpeinfinitus.petfolio.requestpojo.ServiceProviderRegisterFormCreateRequest;

import java.util.List;

public class AddSPCertPdfAdapter extends RecyclerView.Adapter<AddSPCertPdfAdapter.AddImageListHolder> {
    Context context;
    List<ServiceProviderRegisterFormCreateRequest.BusCertifBean> bus_certif_list;
    View view;
    String extension;
    public AddSPCertPdfAdapter(Context context, List<ServiceProviderRegisterFormCreateRequest.BusCertifBean> bus_certif_list) {
        this.context = context;
        this.bus_certif_list = bus_certif_list;

    }

    @NonNull
    @Override
    public AddImageListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pdf_upload, parent, false);
        return new AddImageListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddImageListHolder holder, final int position) {
        final ServiceProviderRegisterFormCreateRequest.BusCertifBean certificatePicBean = bus_certif_list.get(position);

        if (certificatePicBean.getBus_certif()!= null) {

            String uri = certificatePicBean.getBus_certif();
            if(uri.contains(".")) {
                extension = uri.substring(uri.lastIndexOf("."));

                Log.w("extension",extension);
            }

        }
        if(extension != null && !extension.isEmpty()) {
            if (extension.equals(".png") || extension.equals(".jpg") || (extension.equals(".jpeg"))) {
                Glide.with(context)
                        .load(certificatePicBean.getBus_certif())
                        .into(holder.certificate_pics_1);

            }
        } else {

            holder.certificate_pics_1.setImageResource(R.drawable.pdf_icon);
        }



        holder.removeImg.setOnClickListener(view -> {
            bus_certif_list.remove(position);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return bus_certif_list.size();
    }

    public static class AddImageListHolder extends RecyclerView.ViewHolder {
        ImageView removeImg,certificate_pics_1;
        public AddImageListHolder(View itemView) {
            super(itemView);
            certificate_pics_1 = itemView.findViewById(R.id.pdf_file);
            removeImg = itemView.findViewById(R.id.close);
        }
    }


}