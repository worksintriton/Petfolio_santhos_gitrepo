package com.petfolio.infinitus.Carousel;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.petfolio.infinitus.R;

public class CustomFragment extends Fragment {

    public static Fragment newInstance(Activity context, int position, float scale, String s) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putFloat("scale", scale);
        bundle.putString("image",s);
        Log.w("image",s);
        return Fragment.instantiate(context,CustomFragment.class.getName(), bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        LinearLayout linearLayout = (LinearLayout)
                inflater.inflate(R.layout.item, container, false);

        int position = this.getArguments().getInt("position");
        ImageView imageView =linearLayout.findViewById(R.id.img_pet_imge);

        CustomLinearLayout root = (CustomLinearLayout) linearLayout.findViewById(R.id.item_root);
        float scale = this.getArguments().getFloat("scale");
        root.setScaleBoth(scale);

        String s = this.getArguments().getString("image");

        Log.w("image",s);

        if(s!= null&&!s.isEmpty()){

            Glide.with(getContext())
                    .load(s)
                    .into(imageView);

        }

        else
        {
            Glide.with(getContext())
                    .load(R.drawable.image_thumbnail)
                    .into(imageView);

        }


        return linearLayout;
    }
}
