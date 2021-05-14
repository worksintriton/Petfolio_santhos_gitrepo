// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddVehicleActivity_ViewBinding implements Unbinder {
  private AddVehicleActivity target;

  @UiThread
  public AddVehicleActivity_ViewBinding(AddVehicleActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddVehicleActivity_ViewBinding(AddVehicleActivity target, View source) {
    this.target = target;

    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddVehicleActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spin_kit_loadingView = null;
    target.txt_toolbar_title = null;
  }
}
