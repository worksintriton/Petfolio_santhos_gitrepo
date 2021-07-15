// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.activities;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import com.triton.bertsproject.customView.CustomEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ForgetPasswordActivity_ViewBinding implements Unbinder {
  private ForgetPasswordActivity target;

  @UiThread
  public ForgetPasswordActivity_ViewBinding(ForgetPasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ForgetPasswordActivity_ViewBinding(ForgetPasswordActivity target, View source) {
    this.target = target;

    target.edt_email = Utils.findRequiredViewAsType(source, R.id.edt_email, "field 'edt_email'", CustomEditText.class);
    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
    target.btn_reset_link = Utils.findRequiredViewAsType(source, R.id.btn_reset_link, "field 'btn_reset_link'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ForgetPasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edt_email = null;
    target.spin_kit_loadingView = null;
    target.img_back = null;
    target.txt_toolbar_title = null;
    target.btn_reset_link = null;
  }
}
