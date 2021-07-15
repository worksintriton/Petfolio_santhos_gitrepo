// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;
import net.cachapa.expandablelayout.ExpandableLayout;

public class FilterlistActivity_ViewBinding implements Unbinder {
  private FilterlistActivity target;

  @UiThread
  public FilterlistActivity_ViewBinding(FilterlistActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FilterlistActivity_ViewBinding(FilterlistActivity target, View source) {
    this.target = target;

    target.expandableLayout_yr = Utils.findRequiredViewAsType(source, R.id.expandable_layout_yr, "field 'expandableLayout_yr'", ExpandableLayout.class);
    target.expandable_layout_make = Utils.findRequiredViewAsType(source, R.id.expandable_layout_make, "field 'expandable_layout_make'", ExpandableLayout.class);
    target.expandable_layout_model = Utils.findRequiredViewAsType(source, R.id.expandable_layout_model, "field 'expandable_layout_model'", ExpandableLayout.class);
    target.expandable_layout_brand = Utils.findRequiredViewAsType(source, R.id.expandable_layout_brand, "field 'expandable_layout_brand'", ExpandableLayout.class);
    target.expandable_layout_categ = Utils.findRequiredViewAsType(source, R.id.expandable_layout_categ, "field 'expandable_layout_categ'", ExpandableLayout.class);
    target.expandable_layout_price_range = Utils.findRequiredViewAsType(source, R.id.expandable_layout_price_range, "field 'expandable_layout_price_range'", ExpandableLayout.class);
    target.expandable_layout_rating = Utils.findRequiredViewAsType(source, R.id.expandable_layout_rating, "field 'expandable_layout_rating'", ExpandableLayout.class);
    target.expandable_layout_color = Utils.findRequiredViewAsType(source, R.id.expandable_layout_color, "field 'expandable_layout_color'", ExpandableLayout.class);
    target.ll_year = Utils.findRequiredViewAsType(source, R.id.ll_year, "field 'll_year'", LinearLayout.class);
    target.ll_makes = Utils.findRequiredViewAsType(source, R.id.ll_makes, "field 'll_makes'", LinearLayout.class);
    target.ll_models = Utils.findRequiredViewAsType(source, R.id.ll_models, "field 'll_models'", LinearLayout.class);
    target.ll_brand = Utils.findRequiredViewAsType(source, R.id.ll_brand, "field 'll_brand'", LinearLayout.class);
    target.ll_categ = Utils.findRequiredViewAsType(source, R.id.ll_categ, "field 'll_categ'", LinearLayout.class);
    target.ll_price_range = Utils.findRequiredViewAsType(source, R.id.ll_price_range, "field 'll_price_range'", LinearLayout.class);
    target.ll_rating = Utils.findRequiredViewAsType(source, R.id.ll_rating, "field 'll_rating'", LinearLayout.class);
    target.ll_color = Utils.findRequiredViewAsType(source, R.id.ll_color, "field 'll_color'", LinearLayout.class);
    target.img_arrow_year = Utils.findRequiredViewAsType(source, R.id.img_arrow_year, "field 'img_arrow_year'", ImageView.class);
    target.img_arrow_make = Utils.findRequiredViewAsType(source, R.id.img_arrow_make, "field 'img_arrow_make'", ImageView.class);
    target.img_arrow_model = Utils.findRequiredViewAsType(source, R.id.img_arrow_model, "field 'img_arrow_model'", ImageView.class);
    target.img_arrow_brand = Utils.findRequiredViewAsType(source, R.id.img_arrow_brand, "field 'img_arrow_brand'", ImageView.class);
    target.img_arrow_categ = Utils.findRequiredViewAsType(source, R.id.img_arrow_categ, "field 'img_arrow_categ'", ImageView.class);
    target.img_arrow_pr_range = Utils.findRequiredViewAsType(source, R.id.img_arrow_pr_range, "field 'img_arrow_pr_range'", ImageView.class);
    target.img_arrow_rate = Utils.findRequiredViewAsType(source, R.id.img_arrow_rate, "field 'img_arrow_rate'", ImageView.class);
    target.img_arrow_color = Utils.findRequiredViewAsType(source, R.id.img_arrow_color, "field 'img_arrow_color'", ImageView.class);
    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
    target.rv_year = Utils.findRequiredViewAsType(source, R.id.rv_year, "field 'rv_year'", RecyclerView.class);
    target.rv_makes = Utils.findRequiredViewAsType(source, R.id.rv_makes, "field 'rv_makes'", RecyclerView.class);
    target.rv_models = Utils.findRequiredViewAsType(source, R.id.rv_models, "field 'rv_models'", RecyclerView.class);
    target.rv_brand = Utils.findRequiredViewAsType(source, R.id.rv_brand, "field 'rv_brand'", RecyclerView.class);
    target.rv_categ = Utils.findRequiredViewAsType(source, R.id.rv_categ, "field 'rv_categ'", RecyclerView.class);
    target.rv_color = Utils.findRequiredViewAsType(source, R.id.rv_color, "field 'rv_color'", RecyclerView.class);
    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.txt_model_selected_value = Utils.findRequiredViewAsType(source, R.id.txt_model_selected_value, "field 'txt_model_selected_value'", TextView.class);
    target.btn_reset = Utils.findRequiredViewAsType(source, R.id.btn_reset, "field 'btn_reset'", Button.class);
    target.btn_apply = Utils.findRequiredViewAsType(source, R.id.btn_apply, "field 'btn_apply'", Button.class);
    target.view4 = Utils.findRequiredView(source, R.id.view4, "field 'view4'");
    target.view6 = Utils.findRequiredView(source, R.id.view6, "field 'view6'");
    target.view16 = Utils.findRequiredView(source, R.id.view16, "field 'view16'");
    target.view7 = Utils.findRequiredView(source, R.id.view7, "field 'view7'");
    target.view8 = Utils.findRequiredView(source, R.id.view8, "field 'view8'");
    target.view9 = Utils.findRequiredView(source, R.id.view9, "field 'view9'");
    target.view10 = Utils.findRequiredView(source, R.id.view10, "field 'view10'");
  }

  @Override
  @CallSuper
  public void unbind() {
    FilterlistActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.expandableLayout_yr = null;
    target.expandable_layout_make = null;
    target.expandable_layout_model = null;
    target.expandable_layout_brand = null;
    target.expandable_layout_categ = null;
    target.expandable_layout_price_range = null;
    target.expandable_layout_rating = null;
    target.expandable_layout_color = null;
    target.ll_year = null;
    target.ll_makes = null;
    target.ll_models = null;
    target.ll_brand = null;
    target.ll_categ = null;
    target.ll_price_range = null;
    target.ll_rating = null;
    target.ll_color = null;
    target.img_arrow_year = null;
    target.img_arrow_make = null;
    target.img_arrow_model = null;
    target.img_arrow_brand = null;
    target.img_arrow_categ = null;
    target.img_arrow_pr_range = null;
    target.img_arrow_rate = null;
    target.img_arrow_color = null;
    target.img_back = null;
    target.rv_year = null;
    target.rv_makes = null;
    target.rv_models = null;
    target.rv_brand = null;
    target.rv_categ = null;
    target.rv_color = null;
    target.spin_kit_loadingView = null;
    target.txt_model_selected_value = null;
    target.btn_reset = null;
    target.btn_apply = null;
    target.view4 = null;
    target.view6 = null;
    target.view16 = null;
    target.view7 = null;
    target.view8 = null;
    target.view9 = null;
    target.view10 = null;
  }
}
