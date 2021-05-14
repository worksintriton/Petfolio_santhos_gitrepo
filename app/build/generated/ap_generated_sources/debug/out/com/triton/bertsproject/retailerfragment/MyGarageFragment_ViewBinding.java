// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailerfragment;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyGarageFragment_ViewBinding implements Unbinder {
  private MyGarageFragment target;

  @UiThread
  public MyGarageFragment_ViewBinding(MyGarageFragment target, View source) {
    this.target = target;

    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.btn_addVeh = Utils.findRequiredViewAsType(source, R.id.btn_addVeh, "field 'btn_addVeh'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyGarageFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spin_kit_loadingView = null;
    target.btn_addVeh = null;
  }
}
