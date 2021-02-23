package com.petfolio.infinitus.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.petfolio.infinitus.R;
import com.petfolio.infinitus.api.APIClient;
import com.petfolio.infinitus.responsepojo.PetLoverDashboardResponse;

import java.util.List;


public class ViewPagerDashboardAdapter extends PagerAdapter {
    private PetLoverDashboardResponse.DataBean.DashboarddataBean.BannerDetailsBean currentItem;
    private List<PetLoverDashboardResponse.DataBean.DashboarddataBean.BannerDetailsBean> listHomeBannerResponse;
    private Context context;
    private LayoutInflater inflater;
    private View itemView;

    private String TAG = "ViewPagerDashboardAdapter";

    public ViewPagerDashboardAdapter(Context context,  List<PetLoverDashboardResponse.DataBean.DashboarddataBean.BannerDetailsBean>listHomeBannerResponse){

        this.context = context;
        this.listHomeBannerResponse = listHomeBannerResponse;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return listHomeBannerResponse.size();
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
            String imageURL = listHomeBannerResponse.get(position).getImg_path();
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
