// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailerfragment;

import android.view.View;
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
  }
}
