// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailerfragment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.cardview.widget.CardView;
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
    target.rl_login_after = Utils.findRequiredViewAsType(source, R.id.rl_login_after, "field 'rl_login_after'", RelativeLayout.class);
    target.rl_manageaddress = Utils.findRequiredViewAsType(source, R.id.rl_manageaddress, "field 'rl_manageaddress'", RelativeLayout.class);
    target.ll_login_after = Utils.findRequiredViewAsType(source, R.id.ll_login_after, "field 'll_login_after'", LinearLayout.class);
    target.cv_logout = Utils.findRequiredViewAsType(source, R.id.cv_logout, "field 'cv_logout'", CardView.class);
    target.cv_before_login = Utils.findRequiredViewAsType(source, R.id.cv_before_login, "field 'cv_before_login'", CardView.class);
    target.txt_username = Utils.findRequiredViewAsType(source, R.id.txt_username, "field 'txt_username'", TextView.class);
    target.img_profile = Utils.findRequiredViewAsType(source, R.id.img_profile, "field 'img_profile'", ImageView.class);
    target.btn_sigin = Utils.findRequiredViewAsType(source, R.id.btn_signin, "field 'btn_sigin'", Button.class);
    target.txt_user_login = Utils.findRequiredViewAsType(source, R.id.txt_user_login, "field 'txt_user_login'", TextView.class);
    target.txt_cart_count = Utils.findRequiredViewAsType(source, R.id.txt_cart_count, "field 'txt_cart_count'", TextView.class);
    target.rlcart = Utils.findRequiredViewAsType(source, R.id.rlcart, "field 'rlcart'", RelativeLayout.class);
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
    target.rl_login_after = null;
    target.rl_manageaddress = null;
    target.ll_login_after = null;
    target.cv_logout = null;
    target.cv_before_login = null;
    target.txt_username = null;
    target.img_profile = null;
    target.btn_sigin = null;
    target.txt_user_login = null;
    target.txt_cart_count = null;
    target.rlcart = null;
  }
}
