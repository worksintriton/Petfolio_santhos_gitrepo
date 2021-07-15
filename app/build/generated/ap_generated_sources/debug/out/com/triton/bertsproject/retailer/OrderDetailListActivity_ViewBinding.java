// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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

public class OrderDetailListActivity_ViewBinding implements Unbinder {
  private OrderDetailListActivity target;

  @UiThread
  public OrderDetailListActivity_ViewBinding(OrderDetailListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderDetailListActivity_ViewBinding(OrderDetailListActivity target, View source) {
    this.target = target;

    target.txt_no_records = Utils.findRequiredViewAsType(source, R.id.txt_no_records, "field 'txt_no_records'", TextView.class);
    target.txt_order_date = Utils.findRequiredViewAsType(source, R.id.txt_order_date, "field 'txt_order_date'", TextView.class);
    target.txt_booking_id = Utils.findRequiredViewAsType(source, R.id.txt_booking_id, "field 'txt_booking_id'", TextView.class);
    target.txt_payment_method = Utils.findRequiredViewAsType(source, R.id.txt_payment_method, "field 'txt_payment_method'", TextView.class);
    target.txt_total_order_cost = Utils.findRequiredViewAsType(source, R.id.txt_total_order_cost, "field 'txt_total_order_cost'", TextView.class);
    target.txt_quantity = Utils.findRequiredViewAsType(source, R.id.txt_quantity, "field 'txt_quantity'", TextView.class);
    target.txt_shipping_address_name = Utils.findRequiredViewAsType(source, R.id.txt_shipping_address_name, "field 'txt_shipping_address_name'", TextView.class);
    target.txt_shipping_address_street = Utils.findRequiredViewAsType(source, R.id.txt_shipping_address_street, "field 'txt_shipping_address_street'", TextView.class);
    target.txt_shipping_address_city = Utils.findRequiredViewAsType(source, R.id.txt_shipping_address_city, "field 'txt_shipping_address_city'", TextView.class);
    target.txt_shipping_address_state_pincode = Utils.findRequiredViewAsType(source, R.id.txt_shipping_address_state_pincode, "field 'txt_shipping_address_state_pincode'", TextView.class);
    target.txt_shipping_address_phone = Utils.findRequiredViewAsType(source, R.id.txt_shipping_address_phone, "field 'txt_shipping_address_phone'", TextView.class);
    target.txt_shipping_address_landmark = Utils.findRequiredViewAsType(source, R.id.txt_shipping_address_landmark, "field 'txt_shipping_address_landmark'", TextView.class);
    target.img_expand_arrow = Utils.findRequiredViewAsType(source, R.id.img_expand_arrow, "field 'img_expand_arrow'", ImageView.class);
    target.ll_orderdetails = Utils.findRequiredViewAsType(source, R.id.ll_orderdetails, "field 'll_orderdetails'", LinearLayout.class);
    target.img_expand_arrow_shipping = Utils.findRequiredViewAsType(source, R.id.img_expand_arrow_shipping, "field 'img_expand_arrow_shipping'", ImageView.class);
    target.ll_shippingaddress = Utils.findRequiredViewAsType(source, R.id.ll_shippingaddress, "field 'll_shippingaddress'", LinearLayout.class);
    target.rv_productdetails = Utils.findRequiredViewAsType(source, R.id.rv_productdetails, "field 'rv_productdetails'", RecyclerView.class);
    target.img_expand_arrow_productdetails = Utils.findRequiredViewAsType(source, R.id.img_expand_arrow_productdetails, "field 'img_expand_arrow_productdetails'", ImageView.class);
    target.ll_productdetails = Utils.findRequiredViewAsType(source, R.id.ll_productdetails, "field 'll_productdetails'", LinearLayout.class);
    target.txt_no_products = Utils.findRequiredViewAsType(source, R.id.txt_no_products, "field 'txt_no_products'", TextView.class);
    target.scrollablContent = Utils.findRequiredViewAsType(source, R.id.scrollablContent, "field 'scrollablContent'", ScrollView.class);
    target.include_header = Utils.findRequiredView(source, R.id.include_header, "field 'include_header'");
    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
    target.spinKitView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spinKitView'", SpinKitView.class);
    target.view1 = Utils.findRequiredView(source, R.id.view1, "field 'view1'");
    target.view2 = Utils.findRequiredView(source, R.id.view2, "field 'view2'");
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
    target.cancel_overall_order = Utils.findRequiredViewAsType(source, R.id.cancel_overall_order, "field 'cancel_overall_order'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderDetailListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txt_no_records = null;
    target.txt_order_date = null;
    target.txt_booking_id = null;
    target.txt_payment_method = null;
    target.txt_total_order_cost = null;
    target.txt_quantity = null;
    target.txt_shipping_address_name = null;
    target.txt_shipping_address_street = null;
    target.txt_shipping_address_city = null;
    target.txt_shipping_address_state_pincode = null;
    target.txt_shipping_address_phone = null;
    target.txt_shipping_address_landmark = null;
    target.img_expand_arrow = null;
    target.ll_orderdetails = null;
    target.img_expand_arrow_shipping = null;
    target.ll_shippingaddress = null;
    target.rv_productdetails = null;
    target.img_expand_arrow_productdetails = null;
    target.ll_productdetails = null;
    target.txt_no_products = null;
    target.scrollablContent = null;
    target.include_header = null;
    target.img_back = null;
    target.spinKitView = null;
    target.view1 = null;
    target.view2 = null;
    target.txt_toolbar_title = null;
    target.cancel_overall_order = null;
  }
}
