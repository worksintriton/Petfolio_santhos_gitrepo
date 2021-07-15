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
import com.facebook.login.widget.LoginButton;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import com.triton.bertsproject.customView.CustomEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target, View source) {
    this.target = target;

    target.edt_email = Utils.findRequiredViewAsType(source, R.id.edt_email, "field 'edt_email'", CustomEditText.class);
    target.edt_password = Utils.findRequiredViewAsType(source, R.id.edt_password, "field 'edt_password'", CustomEditText.class);
    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.btn_sigin = Utils.findRequiredViewAsType(source, R.id.btn_sigin, "field 'btn_sigin'", Button.class);
    target.txt_forget_password = Utils.findRequiredViewAsType(source, R.id.txt_forget_password, "field 'txt_forget_password'", TextView.class);
    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
    target.btnLogin = Utils.findRequiredViewAsType(source, R.id.login_button, "field 'btnLogin'", LoginButton.class);
    target.txt_or = Utils.findRequiredViewAsType(source, R.id.txt_or, "field 'txt_or'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edt_email = null;
    target.edt_password = null;
    target.spin_kit_loadingView = null;
    target.btn_sigin = null;
    target.txt_forget_password = null;
    target.img_back = null;
    target.txt_toolbar_title = null;
    target.btnLogin = null;
    target.txt_or = null;
  }
}
