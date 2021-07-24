// Generated code from Butter Knife. Do not modify!
package com.dci.berts.retailer;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.SwitchCompat;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.dci.berts.R;
import com.dci.berts.customView.CustomEditText;
import com.github.ybq.android.spinkit.SpinKitView;
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

    target.edt_name = Utils.findRequiredViewAsType(source, R.id.edt_name, "field 'edt_name'", CustomEditText.class);
    target.edt_phoneno = Utils.findRequiredViewAsType(source, R.id.edt_phoneno, "field 'edt_phoneno'", CustomEditText.class);
    target.edt_city = Utils.findRequiredViewAsType(source, R.id.edt_city, "field 'edt_city'", CustomEditText.class);
    target.edtAddress1 = Utils.findRequiredViewAsType(source, R.id.edtAddress1, "field 'edtAddress1'", CustomEditText.class);
    target.edtAddress2 = Utils.findRequiredViewAsType(source, R.id.edtAddress2, "field 'edtAddress2'", CustomEditText.class);
    target.edt_zipcode = Utils.findRequiredViewAsType(source, R.id.edt_zipcode, "field 'edt_zipcode'", CustomEditText.class);
    target.sw_saveasdefault = Utils.findRequiredViewAsType(source, R.id.sw_saveasdefault, "field 'sw_saveasdefault'", SwitchCompat.class);
    target.btn_saveaddr = Utils.findRequiredViewAsType(source, R.id.btn_saveaddr, "field 'btn_saveaddr'", Button.class);
    target.sp_country = Utils.findRequiredViewAsType(source, R.id.sp_country, "field 'sp_country'", Spinner.class);
    target.sp_state = Utils.findRequiredViewAsType(source, R.id.sp_state, "field 'sp_state'", Spinner.class);
    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
    target.ll_default = Utils.findRequiredViewAsType(source, R.id.ll_default, "field 'll_default'", LinearLayout.class);
    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShippingAddressAddActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edt_name = null;
    target.edt_phoneno = null;
    target.edt_city = null;
    target.edtAddress1 = null;
    target.edtAddress2 = null;
    target.edt_zipcode = null;
    target.sw_saveasdefault = null;
    target.btn_saveaddr = null;
    target.sp_country = null;
    target.sp_state = null;
    target.spin_kit_loadingView = null;
    target.txt_toolbar_title = null;
    target.ll_default = null;
    target.img_back = null;
  }
}
