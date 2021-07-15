// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.login.widget.LoginButton;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import com.triton.bertsproject.customView.CustomEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WholeSalerRegisterFragment_ViewBinding implements Unbinder {
  private WholeSalerRegisterFragment target;

  @UiThread
  public WholeSalerRegisterFragment_ViewBinding(WholeSalerRegisterFragment target, View source) {
    this.target = target;

    target.btnLogin = Utils.findRequiredViewAsType(source, R.id.login_button, "field 'btnLogin'", LoginButton.class);
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
    target.btn_sigin = Utils.findRequiredViewAsType(source, R.id.btn_sigin, "field 'btn_sigin'", Button.class);
    target.txt_or = Utils.findRequiredViewAsType(source, R.id.txt_or, "field 'txt_or'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WholeSalerRegisterFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnLogin = null;
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
    target.btn_sigin = null;
    target.txt_or = null;
  }
}
