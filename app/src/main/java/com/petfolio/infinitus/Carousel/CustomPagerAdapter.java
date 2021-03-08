    package com.petfolio.infinitus.Carousel;

    import android.app.Activity;
    import android.util.Log;
    import android.view.View;

    import androidx.fragment.app.Fragment;
    import androidx.fragment.app.FragmentManager;
    import androidx.fragment.app.FragmentPagerAdapter;
    import androidx.viewpager.widget.ViewPager;

    import com.petfolio.infinitus.R;

    import java.util.List;

    import static com.petfolio.infinitus.petlover.BookAppointmentActivity.FIRST_PAGE;
    import static com.petfolio.infinitus.petlover.BookAppointmentActivity.PAGES;

    public class CustomPagerAdapter extends FragmentPagerAdapter implements ViewPager.PageTransformer {
        public final static float BIG_SCALE = 1.0f;
        public final static float SMALL_SCALE = 0.7f;
        public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;

        private Activity mContext;
        private FragmentManager mFragmentManager;
        private float mScale;
        private List<String> list;

        public CustomPagerAdapter(Activity context, List<String> list, FragmentManager fragmentManager) {
            super(fragmentManager,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            this.mFragmentManager = fragmentManager;
            this.mContext = context;
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            // make the first mViewPager bigger than others
            if (position == FIRST_PAGE)
                mScale = BIG_SCALE;
            else
                mScale = SMALL_SCALE;

            Log.w("image",list.get(position));

            return CustomFragment.newInstance(mContext, position, mScale,list.get(position));
        }

        @Override
        public int getCount() {
            return PAGES;
        }

        @Override
        public void transformPage(View page, float position) {
            CustomLinearLayout myLinearLayout = (CustomLinearLayout) page.findViewById(R.id.item_root);
            float scale = BIG_SCALE;
            if (position > 0) {
                scale = scale - position * DIFF_SCALE;
            } else {
                scale = scale + position * DIFF_SCALE;
            }
            if (scale < 0) scale = 0;
            myLinearLayout.setScaleBoth(scale);
        }
    }
