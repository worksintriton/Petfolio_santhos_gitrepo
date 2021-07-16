// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CheckoutScreenActivity_ViewBinding implements Unbinder {
  private CheckoutScreenActivity target;

  @UiThread
  public CheckoutScreenActivity_ViewBinding(CheckoutScreenActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CheckoutScreenActivity_ViewBinding(CheckoutScreenActivity target, View source) {
    this.target = target;

    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
    target.rv_productlist = Utils.findRequiredViewAsType(source, R.id.rv_productlist, "field 'rv_productlist'", RecyclerView.class);
    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
    target.txt_selectpymthd = Utils.findRequiredViewAsType(source, R.id.txt_selectpymthd, "field 'txt_selectpymthd'", TextView.class);
    target.ll_proceed = Utils.findRequiredViewAsType(source, R.id.ll_proceed, "field 'll_proceed'", LinearLayout.class);
    target.txt_no_records = Utils.findRequiredViewAsType(source, R.id.txt_no_records, "field 'txt_no_records'", TextView.class);
    target.cv_orders = Utils.findRequiredViewAsType(source, R.id.cv_orders, "field 'cv_orders'", CardView.class);
    target.cv_order_total = Utils.findRequiredViewAsType(source, R.id.cv_order_total, "field 'cv_order_total'", CardView.class);
    target.cv_payment = Utils.findRequiredViewAsType(source, R.id.cv_payment, "field 'cv_payment'", CardView.class);
    target.txt_total_order = Utils.findRequiredViewAsType(source, R.id.txt_total_order, "field 'txt_total_order'", TextView.class);
    target.rl_default_shipping = Utils.findRequiredViewAsType(source, R.id.rl_default_shipping, "field 'rl_default_shipping'", RelativeLayout.class);
    target.txt_deliveryaddrtype = Utils.findRequiredViewAsType(source, R.id.txt_deliveryaddrtype, "field 'txt_deliveryaddrtype'", TextView.class);
    target.txt_deliveryaddr = Utils.findRequiredViewAsType(source, R.id.txt_deliveryaddr, "field 'txt_deliveryaddr'", TextView.class);
    target.txt_order_total = Utils.findRequiredViewAsType(source, R.id.txt_order_total, "field 'txt_order_total'", TextView.class);
    target.txt_payment_method = Utils.findRequiredViewAsType(source, R.id.txt_payment_method, "field 'txt_payment_method'", TextView.class);
    target.txt_shipping_subtotal_value = Utils.findRequiredViewAsType(source, R.id.txt_shipping_subtotal_value, "field 'txt_shipping_subtotal_value'", TextView.class);
    target.txt_dis_total_price = Utils.findRequiredViewAsType(source, R.id.txt_dis_total_price, "field 'txt_dis_total_price'", TextView.class);
    target.txt_total_price = Utils.findRequiredViewAsType(source, R.id.txt_total_price, "field 'txt_total_price'", TextView.class);
    target.rl_payment_method = Utils.findRequiredViewAsType(source, R.id.rl_payment_method, "field 'rl_payment_method'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CheckoutScreenActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.img_back = null;
    target.rv_productlist = null;
    target.spin_kit_loadingView = null;
    target.txt_toolbar_title = null;
    target.txt_selectpymthd = null;
    target.ll_proceed = null;
    target.txt_no_records = null;
    target.cv_orders = null;
    target.cv_order_total = null;
    target.cv_payment = null;
    target.txt_total_order = null;
    target.rl_default_shipping = null;
    target.txt_deliveryaddrtype = null;
    target.txt_deliveryaddr = null;
    target.txt_order_total = null;
    target.txt_payment_method = null;
    target.txt_shipping_subtotal_value = null;
    target.txt_dis_total_price = null;
    target.txt_total_price = null;
    target.rl_payment_method = null;
  }
}
