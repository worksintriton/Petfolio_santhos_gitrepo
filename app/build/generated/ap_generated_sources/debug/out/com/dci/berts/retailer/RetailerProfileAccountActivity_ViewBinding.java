// Generated code from Butter Knife. Do not modify!
package com.dci.berts.retailer;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.dci.berts.R;
import com.dci.berts.customView.CustomEditText;
import com.github.ybq.android.spinkit.SpinKitView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RetailerProfileAccountActivity_ViewBinding implements Unbinder {
  private RetailerProfileAccountActivity target;

  @UiThread
  public RetailerProfileAccountActivity_ViewBinding(RetailerProfileAccountActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RetailerProfileAccountActivity_ViewBinding(RetailerProfileAccountActivity target,
      View source) {
    this.target = target;

    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.edt_firstname = Utils.findRequiredViewAsType(source, R.id.edt_firstname, "field 'edt_firstname'", CustomEditText.class);
    target.edt_lastname = Utils.findRequiredViewAsType(source, R.id.edt_lastname, "field 'edt_lastname'", CustomEditText.class);
    target.edt_email = Utils.findRequiredViewAsType(source, R.id.edt_email, "field 'edt_email'", CustomEditText.class);
    target.edt_password = Utils.findRequiredViewAsType(source, R.id.edt_password, "field 'edt_password'", CustomEditText.class);
    target.edt_cnfmpassword = Utils.findRequiredViewAsType(source, R.id.edt_cnfmpassword, "field 'edt_cnfmpassword'", CustomEditText.class);
    target.edt_zipcode = Utils.findRequiredViewAsType(source, R.id.edt_zipcode, "field 'edt_zipcode'", CustomEditText.class);
    target.sp_country = Utils.findRequiredViewAsType(source, R.id.sp_country, "field 'sp_country'", Spinner.class);
    target.sp_state = Utils.findRequiredViewAsType(source, R.id.sp_state, "field 'sp_state'", Spinner.class);
    target.edt_revenue = Utils.findRequiredViewAsType(source, R.id.edt_revenue, "field 'edt_revenue'", CustomEditText.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
    target.btn_update = Utils.findRequiredViewAsType(source, R.id.btn_update, "field 'btn_update'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RetailerProfileAccountActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.img_back = null;
    target.spin_kit_loadingView = null;
    target.edt_firstname = null;
    target.edt_lastname = null;
    target.edt_email = null;
    target.edt_password = null;
    target.edt_cnfmpassword = null;
    target.edt_zipcode = null;
    target.sp_country = null;
    target.sp_state = null;
    target.edt_revenue = null;
    target.txt_toolbar_title = null;
    target.btn_update = null;
  }
}
