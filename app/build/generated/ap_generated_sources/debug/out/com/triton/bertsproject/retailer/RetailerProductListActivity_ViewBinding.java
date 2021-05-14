// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
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

public class RetailerProductListActivity_ViewBinding implements Unbinder {
  private RetailerProductListActivity target;

  @UiThread
  public RetailerProductListActivity_ViewBinding(RetailerProductListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RetailerProductListActivity_ViewBinding(RetailerProductListActivity target, View source) {
    this.target = target;

    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
    target.rlList = Utils.findRequiredViewAsType(source, R.id.rlList, "field 'rlList'", LinearLayout.class);
    target.rlGrid = Utils.findRequiredViewAsType(source, R.id.rlGrid, "field 'rlGrid'", LinearLayout.class);
    target.rv_prodlist = Utils.findRequiredViewAsType(source, R.id.rv_productlist, "field 'rv_prodlist'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RetailerProductListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spin_kit_loadingView = null;
    target.img_back = null;
    target.txt_toolbar_title = null;
    target.rlList = null;
    target.rlGrid = null;
    target.rv_prodlist = null;
  }
}
