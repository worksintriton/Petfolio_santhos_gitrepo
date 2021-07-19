// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.tabs.TabLayout;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProductDetailDescriptionActivity_ViewBinding implements Unbinder {
  private ProductDetailDescriptionActivity target;

  @UiThread
  public ProductDetailDescriptionActivity_ViewBinding(ProductDetailDescriptionActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProductDetailDescriptionActivity_ViewBinding(ProductDetailDescriptionActivity target,
      View source) {
    this.target = target;

    target.btn_addcart = Utils.findRequiredViewAsType(source, R.id.btn_addcart, "field 'btn_addcart'", Button.class);
    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
    target.txt_product_name = Utils.findRequiredViewAsType(source, R.id.txt_product_name, "field 'txt_product_name'", TextView.class);
    target.txt_parts_no = Utils.findRequiredViewAsType(source, R.id.txt_parts_no, "field 'txt_parts_no'", TextView.class);
    target.rl_star = Utils.findRequiredViewAsType(source, R.id.rl_star, "field 'rl_star'", RelativeLayout.class);
    target.txt_total_reviews = Utils.findRequiredViewAsType(source, R.id.txt_total_reviews, "field 'txt_total_reviews'", TextView.class);
    target.txt_price = Utils.findRequiredViewAsType(source, R.id.txt_price, "field 'txt_price'", TextView.class);
    target.ll_add_minus = Utils.findRequiredViewAsType(source, R.id.ll_add_minus, "field 'll_add_minus'", LinearLayout.class);
    target.img_minus = Utils.findRequiredViewAsType(source, R.id.img_minus, "field 'img_minus'", ImageView.class);
    target.txt_count = Utils.findRequiredViewAsType(source, R.id.txt_count, "field 'txt_count'", TextView.class);
    target.img_plus = Utils.findRequiredViewAsType(source, R.id.img_plus, "field 'img_plus'", ImageView.class);
    target.ll_text_fit = Utils.findRequiredViewAsType(source, R.id.ll_text_fit, "field 'll_text_fit'", LinearLayout.class);
    target.cv_prod_desc = Utils.findRequiredViewAsType(source, R.id.cv_prod_desc, "field 'cv_prod_desc'", CardView.class);
    target.txt_prod_desc = Utils.findRequiredViewAsType(source, R.id.txt_prod_desc, "field 'txt_prod_desc'", TextView.class);
    target.txt_manufacturer = Utils.findRequiredViewAsType(source, R.id.txt_manufacturer, "field 'txt_manufacturer'", TextView.class);
    target.txt_brand = Utils.findRequiredViewAsType(source, R.id.txt_brand, "field 'txt_brand'", TextView.class);
    target.txt_model = Utils.findRequiredViewAsType(source, R.id.txt_model, "field 'txt_model'", TextView.class);
    target.txt_weight = Utils.findRequiredViewAsType(source, R.id.txt_weight, "field 'txt_weight'", TextView.class);
    target.txt_dimen = Utils.findRequiredViewAsType(source, R.id.txt_dimen, "field 'txt_dimen'", TextView.class);
    target.cv_vehicle_fit = Utils.findRequiredViewAsType(source, R.id.cv_vehicle_fit, "field 'cv_vehicle_fit'", CardView.class);
    target.cv_quest_ans = Utils.findRequiredViewAsType(source, R.id.cv_quest_ans, "field 'cv_quest_ans'", CardView.class);
    target.cv_star_review = Utils.findRequiredViewAsType(source, R.id.cv_star_review, "field 'cv_star_review'", CardView.class);
    target.ll_star_review = Utils.findRequiredViewAsType(source, R.id.ll_star_review, "field 'll_star_review'", LinearLayout.class);
    target.txt_total_rating_calc = Utils.findRequiredViewAsType(source, R.id.txt_total_rating_calc, "field 'txt_total_rating_calc'", TextView.class);
    target.txt_review_countings = Utils.findRequiredViewAsType(source, R.id.txt_review_countings, "field 'txt_review_countings'", TextView.class);
    target.rv_review_ratings = Utils.findRequiredViewAsType(source, R.id.rv_review_ratings, "field 'rv_review_ratings'", RecyclerView.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.pager, "field 'viewPager'", ViewPager.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tabDots, "field 'tabLayout'", TabLayout.class);
    target.img_desc = Utils.findRequiredViewAsType(source, R.id.img_desc, "field 'img_desc'", ImageView.class);
    target.img_prod_info = Utils.findRequiredViewAsType(source, R.id.img_prod_info, "field 'img_prod_info'", ImageView.class);
    target.img_cust_revw = Utils.findRequiredViewAsType(source, R.id.img_cust_revw, "field 'img_cust_revw'", ImageView.class);
    target.ll_product_info = Utils.findRequiredViewAsType(source, R.id.ll_product_info, "field 'll_product_info'", LinearLayout.class);
    target.ll_cust_revw = Utils.findRequiredViewAsType(source, R.id.ll_cust_revw, "field 'll_cust_revw'", LinearLayout.class);
    target.hand_img1 = Utils.findRequiredViewAsType(source, R.id.hand_img1, "field 'hand_img1'", ImageView.class);
    target.hand_img2 = Utils.findRequiredViewAsType(source, R.id.hand_img2, "field 'hand_img2'", ImageView.class);
    target.hand_img3 = Utils.findRequiredViewAsType(source, R.id.hand_img3, "field 'hand_img3'", ImageView.class);
    target.hand_img4 = Utils.findRequiredViewAsType(source, R.id.hand_img4, "field 'hand_img4'", ImageView.class);
    target.hand_img5 = Utils.findRequiredViewAsType(source, R.id.hand_img5, "field 'hand_img5'", ImageView.class);
    target.hand_img11 = Utils.findRequiredViewAsType(source, R.id.hand_img11, "field 'hand_img11'", ImageView.class);
    target.hand_img21 = Utils.findRequiredViewAsType(source, R.id.hand_img21, "field 'hand_img21'", ImageView.class);
    target.hand_img31 = Utils.findRequiredViewAsType(source, R.id.hand_img31, "field 'hand_img31'", ImageView.class);
    target.hand_img41 = Utils.findRequiredViewAsType(source, R.id.hand_img41, "field 'hand_img41'", ImageView.class);
    target.hand_img51 = Utils.findRequiredViewAsType(source, R.id.hand_img51, "field 'hand_img51'", ImageView.class);
    target.progress_bar_5star = Utils.findRequiredViewAsType(source, R.id.progress_bar_5star, "field 'progress_bar_5star'", RoundedHorizontalProgressBar.class);
    target.txt_5star_perc = Utils.findRequiredViewAsType(source, R.id.txt_5star_perc, "field 'txt_5star_perc'", TextView.class);
    target.progress_bar_4star = Utils.findRequiredViewAsType(source, R.id.progress_bar_4star, "field 'progress_bar_4star'", RoundedHorizontalProgressBar.class);
    target.txt_4star_perc = Utils.findRequiredViewAsType(source, R.id.txt_4star_perc, "field 'txt_4star_perc'", TextView.class);
    target.progress_bar_3star = Utils.findRequiredViewAsType(source, R.id.progress_bar_3star, "field 'progress_bar_3star'", RoundedHorizontalProgressBar.class);
    target.txt_3star_perc = Utils.findRequiredViewAsType(source, R.id.txt_3star_perc, "field 'txt_3star_perc'", TextView.class);
    target.progress_bar_2star = Utils.findRequiredViewAsType(source, R.id.progress_bar_2star, "field 'progress_bar_2star'", RoundedHorizontalProgressBar.class);
    target.txt_2star_perc = Utils.findRequiredViewAsType(source, R.id.txt_2star_perc, "field 'txt_2star_perc'", TextView.class);
    target.progress_bar_1star = Utils.findRequiredViewAsType(source, R.id.progress_bar_1star, "field 'progress_bar_1star'", RoundedHorizontalProgressBar.class);
    target.txt_1star_perc = Utils.findRequiredViewAsType(source, R.id.txt_1star_perc, "field 'txt_1star_perc'", TextView.class);
    target.txt_cart_count = Utils.findRequiredViewAsType(source, R.id.txt_cart_count, "field 'txt_cart_count'", TextView.class);
    target.rlcart = Utils.findRequiredViewAsType(source, R.id.rlcart, "field 'rlcart'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProductDetailDescriptionActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btn_addcart = null;
    target.spin_kit_loadingView = null;
    target.img_back = null;
    target.txt_toolbar_title = null;
    target.txt_product_name = null;
    target.txt_parts_no = null;
    target.rl_star = null;
    target.txt_total_reviews = null;
    target.txt_price = null;
    target.ll_add_minus = null;
    target.img_minus = null;
    target.txt_count = null;
    target.img_plus = null;
    target.ll_text_fit = null;
    target.cv_prod_desc = null;
    target.txt_prod_desc = null;
    target.txt_manufacturer = null;
    target.txt_brand = null;
    target.txt_model = null;
    target.txt_weight = null;
    target.txt_dimen = null;
    target.cv_vehicle_fit = null;
    target.cv_quest_ans = null;
    target.cv_star_review = null;
    target.ll_star_review = null;
    target.txt_total_rating_calc = null;
    target.txt_review_countings = null;
    target.rv_review_ratings = null;
    target.viewPager = null;
    target.tabLayout = null;
    target.img_desc = null;
    target.img_prod_info = null;
    target.img_cust_revw = null;
    target.ll_product_info = null;
    target.ll_cust_revw = null;
    target.hand_img1 = null;
    target.hand_img2 = null;
    target.hand_img3 = null;
    target.hand_img4 = null;
    target.hand_img5 = null;
    target.hand_img11 = null;
    target.hand_img21 = null;
    target.hand_img31 = null;
    target.hand_img41 = null;
    target.hand_img51 = null;
    target.progress_bar_5star = null;
    target.txt_5star_perc = null;
    target.progress_bar_4star = null;
    target.txt_4star_perc = null;
    target.progress_bar_3star = null;
    target.txt_3star_perc = null;
    target.progress_bar_2star = null;
    target.txt_2star_perc = null;
    target.progress_bar_1star = null;
    target.txt_1star_perc = null;
    target.txt_cart_count = null;
    target.rlcart = null;
  }
}
