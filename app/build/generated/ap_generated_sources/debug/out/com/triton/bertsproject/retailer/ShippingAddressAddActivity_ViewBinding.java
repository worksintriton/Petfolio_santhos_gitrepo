// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShippingAddressAddActivity_ViewBinding implements Unbinder {
  private ShippingAddressAddActivity target;

  @UiThread
  public ShippingAddressAddActivity_ViewBinding(ShippingAddressAddActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShippingAddressAddActivity_ViewBinding(ShippingAddressAddActivity target, View source) {
    this.target = target;

    target.sp_country = Utils.findRequiredViewAsType(source, R.id.sp_country, "field 'sp_country'", Spinner.class);
    target.sp_state = Utils.findRequiredViewAsType(source, R.id.sp_state, "field 'sp_state'", Spinner.class);
    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShippingAddressAddActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.sp_country = null;
    target.sp_state = null;
    target.spin_kit_loadingView = null;
    target.txt_toolbar_title = null;
  }
}
