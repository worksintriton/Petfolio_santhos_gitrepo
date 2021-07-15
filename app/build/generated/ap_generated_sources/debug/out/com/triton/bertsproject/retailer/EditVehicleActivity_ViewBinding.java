// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
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

public class EditVehicleActivity_ViewBinding implements Unbinder {
  private EditVehicleActivity target;

  @UiThread
  public EditVehicleActivity_ViewBinding(EditVehicleActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditVehicleActivity_ViewBinding(EditVehicleActivity target, View source) {
    this.target = target;

    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
    target.sp_year = Utils.findRequiredViewAsType(source, R.id.sp_year, "field 'sp_year'", Spinner.class);
    target.sp_make = Utils.findRequiredViewAsType(source, R.id.sp_make, "field 'sp_make'", Spinner.class);
    target.sp_model = Utils.findRequiredViewAsType(source, R.id.sp_model, "field 'sp_model'", Spinner.class);
    target.rl_year = Utils.findRequiredViewAsType(source, R.id.rl_year, "field 'rl_year'", RelativeLayout.class);
    target.rl_make = Utils.findRequiredViewAsType(source, R.id.rl_amke, "field 'rl_make'", RelativeLayout.class);
    target.rl_model = Utils.findRequiredViewAsType(source, R.id.rl_model, "field 'rl_model'", RelativeLayout.class);
    target.btn_addVeh = Utils.findRequiredViewAsType(source, R.id.btn_addVeh, "field 'btn_addVeh'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EditVehicleActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spin_kit_loadingView = null;
    target.txt_toolbar_title = null;
    target.sp_year = null;
    target.sp_make = null;
    target.sp_model = null;
    target.rl_year = null;
    target.rl_make = null;
    target.rl_model = null;
    target.btn_addVeh = null;
  }
}
