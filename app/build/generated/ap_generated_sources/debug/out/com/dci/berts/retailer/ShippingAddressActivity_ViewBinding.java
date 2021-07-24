// Generated code from Butter Knife. Do not modify!
package com.dci.berts.retailer;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.dci.berts.R;
import com.github.ybq.android.spinkit.SpinKitView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShippingAddressActivity_ViewBinding implements Unbinder {
  private ShippingAddressActivity target;

  @UiThread
  public ShippingAddressActivity_ViewBinding(ShippingAddressActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShippingAddressActivity_ViewBinding(ShippingAddressActivity target, View source) {
    this.target = target;

    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
    target.rv_addrlist = Utils.findRequiredViewAsType(source, R.id.rv_addrlist, "field 'rv_addrlist'", RecyclerView.class);
    target.txt_no_records = Utils.findRequiredViewAsType(source, R.id.txt_no_records, "field 'txt_no_records'", TextView.class);
    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
    target.btn_addnewaddr = Utils.findRequiredViewAsType(source, R.id.btn_addnewaddr, "field 'btn_addnewaddr'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShippingAddressActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spin_kit_loadingView = null;
    target.txt_toolbar_title = null;
    target.rv_addrlist = null;
    target.txt_no_records = null;
    target.img_back = null;
    target.btn_addnewaddr = null;
  }
}
