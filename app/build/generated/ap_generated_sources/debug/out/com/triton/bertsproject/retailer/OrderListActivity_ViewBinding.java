// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderListActivity_ViewBinding implements Unbinder {
  private OrderListActivity target;

  @UiThread
  public OrderListActivity_ViewBinding(OrderListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderListActivity_ViewBinding(OrderListActivity target, View source) {
    this.target = target;

    target.floatingActionButton = Utils.findRequiredViewAsType(source, R.id.fab, "field 'floatingActionButton'", FloatingActionButton.class);
    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
    target.rv_orderlist = Utils.findRequiredViewAsType(source, R.id.rv_orderlist, "field 'rv_orderlist'", RecyclerView.class);
    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.btn_continue_shop = Utils.findRequiredViewAsType(source, R.id.btn_continue_shop, "field 'btn_continue_shop'", Button.class);
    target.txt_cart_count = Utils.findRequiredViewAsType(source, R.id.txt_cart_count, "field 'txt_cart_count'", TextView.class);
    target.rlcart = Utils.findRequiredViewAsType(source, R.id.rlcart, "field 'rlcart'", RelativeLayout.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
    target.txt_no_records = Utils.findRequiredViewAsType(source, R.id.txt_no_records, "field 'txt_no_records'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.floatingActionButton = null;
    target.img_back = null;
    target.rv_orderlist = null;
    target.spin_kit_loadingView = null;
    target.btn_continue_shop = null;
    target.txt_cart_count = null;
    target.rlcart = null;
    target.txt_toolbar_title = null;
    target.txt_no_records = null;
  }
}
