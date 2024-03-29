package com.carpeinfinitus.petfolio.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.carpeinfinitus.petfolio.R;
import com.carpeinfinitus.petfolio.api.APIClient;
import com.carpeinfinitus.petfolio.responsepojo.VendorGetsOrderIDResponse;

import java.util.List;


public class ViewPagerVendorDetailsAdapter extends PagerAdapter {
    private VendorGetsOrderIDResponse.DataBean.BussinessGalleryBean currentItem;
    List<VendorGetsOrderIDResponse.DataBean.BussinessGalleryBean> bussiness_gallery;
    private Context context;
    private LayoutInflater inflater;
    private View itemView;

    private String TAG = "ViewPagerVendorDetailsAdapter";

    public ViewPagerVendorDetailsAdapter(Context context,  List<VendorGetsOrderIDResponse.DataBean.BussinessGalleryBean> bussiness_gallery){
        this.context = context;
        this.bussiness_gallery = bussiness_gallery;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return bussiness_gallery.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }


    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View itemView = inflater.inflate(R.layout.sliding_image, view, false);
        ImageView imageView = itemView.findViewById(R.id.itemImage);




        try {
            String imageURL = bussiness_gallery.get(position).getBussiness_gallery();
            if(imageURL != null && !imageURL.isEmpty()){
                Glide.with(context)
                        .load(imageURL)
                        .into(imageView);
            }else{
                Glide.with(context)
                        .load(APIClient.BANNER_IMAGE_URL)
                        .into(imageView);

            }


        } catch (Exception e) {
            // Handle the condition when str is not a number.
        }


        view.addView(itemView);

        return itemView;

    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
