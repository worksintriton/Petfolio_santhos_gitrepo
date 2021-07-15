// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailerfragment;

import android.view.View;
import android.widget.TableLayout;
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

public class ShopFragment_ViewBinding implements Unbinder {
  private ShopFragment target;

  @UiThread
  public ShopFragment_ViewBinding(ShopFragment target, View source) {
    this.target = target;

    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.rv_top_categories = Utils.findRequiredViewAsType(source, R.id.rv_top_categories, "field 'rv_top_categories'", RecyclerView.class);
    target.rv_top_brands = Utils.findRequiredViewAsType(source, R.id.rv_top_brands, "field 'rv_top_brands'", RecyclerView.class);
    target.rv_top_makes = Utils.findRequiredViewAsType(source, R.id.rv_top_makes, "field 'rv_top_makes'", RecyclerView.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
    target.txt_viewall_catg = Utils.findRequiredViewAsType(source, R.id.txt_viewall_catg, "field 'txt_viewall_catg'", TextView.class);
    target.txt_viewall_brands = Utils.findRequiredViewAsType(source, R.id.txt_viewall_brands, "field 'txt_viewall_brands'", TextView.class);
    target.txt_viewall_makes = Utils.findRequiredViewAsType(source, R.id.txt_viewall_makes, "field 'txt_viewall_makes'", TextView.class);
    target.txt_no_recordscateg = Utils.findRequiredViewAsType(source, R.id.txt_no_recordscateg, "field 'txt_no_recordscateg'", TextView.class);
    target.txt_no_recordsbrands = Utils.findRequiredViewAsType(source, R.id.txt_no_recordsbrands, "field 'txt_no_recordsbrands'", TextView.class);
    target.txt_no_recordsmakes = Utils.findRequiredViewAsType(source, R.id.txt_no_recordsmakes, "field 'txt_no_recordsmakes'", TextView.class);
    target.tb_topcat = Utils.findRequiredViewAsType(source, R.id.tb_topcat, "field 'tb_topcat'", TableLayout.class);
    target.tb_brand = Utils.findRequiredViewAsType(source, R.id.tb_brand, "field 'tb_brand'", TableLayout.class);
    target.tb_makes = Utils.findRequiredViewAsType(source, R.id.tb_makes, "field 'tb_makes'", TableLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShopFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spin_kit_loadingView = null;
    target.rv_top_categories = null;
    target.rv_top_brands = null;
    target.rv_top_makes = null;
    target.txt_toolbar_title = null;
    target.txt_viewall_catg = null;
    target.txt_viewall_brands = null;
    target.txt_viewall_makes = null;
    target.txt_no_recordscateg = null;
    target.txt_no_recordsbrands = null;
    target.txt_no_recordsmakes = null;
    target.tb_topcat = null;
    target.tb_brand = null;
    target.tb_makes = null;
  }
}
