// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RetailerCartActivity_ViewBinding implements Unbinder {
  private RetailerCartActivity target;

  @UiThread
  public RetailerCartActivity_ViewBinding(RetailerCartActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RetailerCartActivity_ViewBinding(RetailerCartActivity target, View source) {
    this.target = target;

    target.bottomNavigation = Utils.findRequiredViewAsType(source, R.id.bottomNavigation, "field 'bottomNavigation'", BottomNavigationView.class);
    target.coordinatorLayout = Utils.findRequiredViewAsType(source, R.id.coordinator, "field 'coordinatorLayout'", CoordinatorLayout.class);
    target.floatingActionButton = Utils.findRequiredViewAsType(source, R.id.fab, "field 'floatingActionButton'", FloatingActionButton.class);
    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
    target.txt_empty_cart = Utils.findRequiredViewAsType(source, R.id.txt_empty_cart, "field 'txt_empty_cart'", TextView.class);
    target.rv_productlist = Utils.findRequiredViewAsType(source, R.id.rv_productlist, "field 'rv_productlist'", RecyclerView.class);
    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
    target.ll_proceed = Utils.findRequiredViewAsType(source, R.id.ll_proceed, "field 'll_proceed'", LinearLayout.class);
    target.txt_deliveryaddrchange = Utils.findRequiredViewAsType(source, R.id.txt_deliveryaddrchange, "field 'txt_deliveryaddrchange'", TextView.class);
    target.txt_shipaddrchange = Utils.findRequiredViewAsType(source, R.id.txt_shipaddrchange, "field 'txt_shipaddrchange'", TextView.class);
    target.txt_no_records = Utils.findRequiredViewAsType(source, R.id.txt_no_records, "field 'txt_no_records'", TextView.class);
    target.cv_shipping = Utils.findRequiredViewAsType(source, R.id.cv_shipping, "field 'cv_shipping'", CardView.class);
    target.cv_price = Utils.findRequiredViewAsType(source, R.id.cv_price, "field 'cv_price'", CardView.class);
    target.txt_order_total = Utils.findRequiredViewAsType(source, R.id.txt_order_total, "field 'txt_order_total'", TextView.class);
    target.txt_deliveryaddrtype = Utils.findRequiredViewAsType(source, R.id.txt_deliveryaddrtype, "field 'txt_deliveryaddrtype'", TextView.class);
    target.txt_deliveryaddr = Utils.findRequiredViewAsType(source, R.id.txt_deliveryaddr, "field 'txt_deliveryaddr'", TextView.class);
    target.btn_continue_shop = Utils.findRequiredViewAsType(source, R.id.btn_continue_shop, "field 'btn_continue_shop'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RetailerCartActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bottomNavigation = null;
    target.coordinatorLayout = null;
    target.floatingActionButton = null;
    target.img_back = null;
    target.txt_empty_cart = null;
    target.rv_productlist = null;
    target.spin_kit_loadingView = null;
    target.txt_toolbar_title = null;
    target.ll_proceed = null;
    target.txt_deliveryaddrchange = null;
    target.txt_shipaddrchange = null;
    target.txt_no_records = null;
    target.cv_shipping = null;
    target.cv_price = null;
    target.txt_order_total = null;
    target.txt_deliveryaddrtype = null;
    target.txt_deliveryaddr = null;
    target.btn_continue_shop = null;
  }
}
