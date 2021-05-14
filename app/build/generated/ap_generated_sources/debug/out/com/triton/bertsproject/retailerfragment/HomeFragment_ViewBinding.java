// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailerfragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  @UiThread
  public HomeFragment_ViewBinding(HomeFragment target, View source) {
    this.target = target;

    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.btn_sigin = Utils.findRequiredViewAsType(source, R.id.btn_signin, "field 'btn_sigin'", Button.class);
    target.txt_user_login = Utils.findRequiredViewAsType(source, R.id.txt_user_login, "field 'txt_user_login'", TextView.class);
    target.edt_search = Utils.findRequiredViewAsType(source, R.id.edt_search, "field 'edt_search'", EditText.class);
    target.fl_cart = Utils.findRequiredViewAsType(source, R.id.fl_cart, "field 'fl_cart'", FrameLayout.class);
    target.rv_shopby = Utils.findRequiredViewAsType(source, R.id.rv_shopby, "field 'rv_shopby'", RecyclerView.class);
    target.btn_addVeh = Utils.findRequiredViewAsType(source, R.id.btn_addVeh, "field 'btn_addVeh'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spin_kit_loadingView = null;
    target.btn_sigin = null;
    target.txt_user_login = null;
    target.edt_search = null;
    target.fl_cart = null;
    target.rv_shopby = null;
    target.btn_addVeh = null;
  }
}
