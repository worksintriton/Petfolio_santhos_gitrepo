// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

public class RetailerProductListBasedOnCategActivity_ViewBinding implements Unbinder {
  private RetailerProductListBasedOnCategActivity target;

  @UiThread
  public RetailerProductListBasedOnCategActivity_ViewBinding(
      RetailerProductListBasedOnCategActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RetailerProductListBasedOnCategActivity_ViewBinding(
      RetailerProductListBasedOnCategActivity target, View source) {
    this.target = target;

    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
    target.rlList = Utils.findRequiredViewAsType(source, R.id.rlList, "field 'rlList'", LinearLayout.class);
    target.rlGrid = Utils.findRequiredViewAsType(source, R.id.rlGrid, "field 'rlGrid'", LinearLayout.class);
    target.edt_search = Utils.findRequiredViewAsType(source, R.id.edt_search, "field 'edt_search'", EditText.class);
    target.txt_no_records = Utils.findRequiredViewAsType(source, R.id.txt_no_records, "field 'txt_no_records'", TextView.class);
    target.rv_prodlist = Utils.findRequiredViewAsType(source, R.id.rv_productlist, "field 'rv_prodlist'", RecyclerView.class);
    target.rl_search = Utils.findRequiredViewAsType(source, R.id.rl_search, "field 'rl_search'", RelativeLayout.class);
    target.rl_sort = Utils.findRequiredViewAsType(source, R.id.rl_sort, "field 'rl_sort'", LinearLayout.class);
    target.rl_spinner = Utils.findRequiredViewAsType(source, R.id.rl_spinner, "field 'rl_spinner'", LinearLayout.class);
    target.txt_spinnertext = Utils.findRequiredViewAsType(source, R.id.txt_spinnertext, "field 'txt_spinnertext'", TextView.class);
    target.txt_cart_count = Utils.findRequiredViewAsType(source, R.id.txt_cart_count, "field 'txt_cart_count'", TextView.class);
    target.rlcart = Utils.findRequiredViewAsType(source, R.id.rlcart, "field 'rlcart'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RetailerProductListBasedOnCategActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spin_kit_loadingView = null;
    target.img_back = null;
    target.txt_toolbar_title = null;
    target.rlList = null;
    target.rlGrid = null;
    target.edt_search = null;
    target.txt_no_records = null;
    target.rv_prodlist = null;
    target.rl_search = null;
    target.rl_sort = null;
    target.rl_spinner = null;
    target.txt_spinnertext = null;
    target.txt_cart_count = null;
    target.rlcart = null;
  }
}
