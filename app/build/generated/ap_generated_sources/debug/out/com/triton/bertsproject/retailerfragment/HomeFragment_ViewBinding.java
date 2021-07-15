// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailerfragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.cardview.widget.CardView;
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
    target.rlcart = Utils.findRequiredViewAsType(source, R.id.rlcart, "field 'rlcart'", RelativeLayout.class);
    target.rv_shopby = Utils.findRequiredViewAsType(source, R.id.rv_shopby, "field 'rv_shopby'", RecyclerView.class);
    target.btn_addVeh = Utils.findRequiredViewAsType(source, R.id.btn_addVeh, "field 'btn_addVeh'", Button.class);
    target.txt_user_type = Utils.findRequiredViewAsType(source, R.id.txt_user_type, "field 'txt_user_type'", TextView.class);
    target.cl_loginbefore = Utils.findRequiredViewAsType(source, R.id.cl_loginbefore, "field 'cl_loginbefore'", CardView.class);
    target.cv_vehicle = Utils.findRequiredViewAsType(source, R.id.cv_vehicle, "field 'cv_vehicle'", CardView.class);
    target.cv_default_veh = Utils.findRequiredViewAsType(source, R.id.cv_default_veh, "field 'cv_default_veh'", CardView.class);
    target.txt_selected_vehc = Utils.findRequiredViewAsType(source, R.id.txt_selected_vehc, "field 'txt_selected_vehc'", TextView.class);
    target.rl_manageveh = Utils.findRequiredViewAsType(source, R.id.rl_manageveh, "field 'rl_manageveh'", RelativeLayout.class);
    target.rl_addVehfromdefault = Utils.findRequiredViewAsType(source, R.id.rl_addVehfromdefault, "field 'rl_addVehfromdefault'", RelativeLayout.class);
    target.rl_root = Utils.findRequiredViewAsType(source, R.id.rl_root, "field 'rl_root'", RelativeLayout.class);
    target.rl_search = Utils.findRequiredViewAsType(source, R.id.rl_search, "field 'rl_search'", RelativeLayout.class);
    target.txt_shop_txt = Utils.findRequiredViewAsType(source, R.id.txt_shop_txt, "field 'txt_shop_txt'", TextView.class);
    target.fl_image = Utils.findRequiredViewAsType(source, R.id.fl_image, "field 'fl_image'", FrameLayout.class);
    target.cl_shipping = Utils.findRequiredViewAsType(source, R.id.cl_shipping, "field 'cl_shipping'", CardView.class);
    target.txt_cart_count = Utils.findRequiredViewAsType(source, R.id.txt_cart_count, "field 'txt_cart_count'", TextView.class);
    target.edt_search = Utils.findRequiredViewAsType(source, R.id.edt_search, "field 'edt_search'", EditText.class);
    target.img_search = Utils.findRequiredViewAsType(source, R.id.img_search, "field 'img_search'", ImageView.class);
    target.txt_keyword_info = Utils.findRequiredViewAsType(source, R.id.txt_keyword_info, "field 'txt_keyword_info'", TextView.class);
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
    target.rlcart = null;
    target.rv_shopby = null;
    target.btn_addVeh = null;
    target.txt_user_type = null;
    target.cl_loginbefore = null;
    target.cv_vehicle = null;
    target.cv_default_veh = null;
    target.txt_selected_vehc = null;
    target.rl_manageveh = null;
    target.rl_addVehfromdefault = null;
    target.rl_root = null;
    target.rl_search = null;
    target.txt_shop_txt = null;
    target.fl_image = null;
    target.cl_shipping = null;
    target.txt_cart_count = null;
    target.edt_search = null;
    target.img_search = null;
    target.txt_keyword_info = null;
  }
}
