// Generated code from Butter Knife. Do not modify!
package com.dci.berts.retailerfragment;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.dci.berts.R;
import com.github.ybq.android.spinkit.SpinKitView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyGarageFragment_ViewBinding implements Unbinder {
  private MyGarageFragment target;

  @UiThread
  public MyGarageFragment_ViewBinding(MyGarageFragment target, View source) {
    this.target = target;

    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.btn_addVeh = Utils.findRequiredViewAsType(source, R.id.btn_addVeh, "field 'btn_addVeh'", Button.class);
    target.rv_vehiclelist = Utils.findRequiredViewAsType(source, R.id.rv_vehiclelist, "field 'rv_vehiclelist'", RecyclerView.class);
    target.txt_no_records = Utils.findRequiredViewAsType(source, R.id.txt_no_records, "field 'txt_no_records'", TextView.class);
    target.cl_loginbefore = Utils.findRequiredViewAsType(source, R.id.cl_loginbefore, "field 'cl_loginbefore'", CardView.class);
    target.cv_addvehicle = Utils.findRequiredViewAsType(source, R.id.cv_addvehicle, "field 'cv_addvehicle'", CardView.class);
    target.rl_vehicle = Utils.findRequiredViewAsType(source, R.id.rl_vehicle, "field 'rl_vehicle'", RelativeLayout.class);
    target.btn_sigin = Utils.findRequiredViewAsType(source, R.id.btn_signin, "field 'btn_sigin'", Button.class);
    target.txt_user_login = Utils.findRequiredViewAsType(source, R.id.txt_user_login, "field 'txt_user_login'", TextView.class);
    target.txt_cart_count = Utils.findRequiredViewAsType(source, R.id.txt_cart_count, "field 'txt_cart_count'", TextView.class);
    target.rlcart = Utils.findRequiredViewAsType(source, R.id.rlcart, "field 'rlcart'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyGarageFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spin_kit_loadingView = null;
    target.btn_addVeh = null;
    target.rv_vehiclelist = null;
    target.txt_no_records = null;
    target.cl_loginbefore = null;
    target.cv_addvehicle = null;
    target.rl_vehicle = null;
    target.btn_sigin = null;
    target.txt_user_login = null;
    target.txt_cart_count = null;
    target.rlcart = null;
  }
}
