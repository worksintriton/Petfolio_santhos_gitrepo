// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailerfragment;

import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProfileFragment_ViewBinding implements Unbinder {
  private ProfileFragment target;

  @UiThread
  public ProfileFragment_ViewBinding(ProfileFragment target, View source) {
    this.target = target;

    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.rl_wishlist = Utils.findRequiredViewAsType(source, R.id.rl_wishlist, "field 'rl_wishlist'", RelativeLayout.class);
    target.rl_ordrhist = Utils.findRequiredViewAsType(source, R.id.rl_ordrhist, "field 'rl_ordrhist'", RelativeLayout.class);
    target.rl_ordrtrack = Utils.findRequiredViewAsType(source, R.id.rl_ordrtrack, "field 'rl_ordrtrack'", RelativeLayout.class);
    target.rlEdit = Utils.findRequiredViewAsType(source, R.id.rlEdit, "field 'rlEdit'", RelativeLayout.class);
    target.rl_settings = Utils.findRequiredViewAsType(source, R.id.rl_settings, "field 'rl_settings'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spin_kit_loadingView = null;
    target.rl_wishlist = null;
    target.rl_ordrhist = null;
    target.rl_ordrtrack = null;
    target.rlEdit = null;
    target.rl_settings = null;
  }
}
