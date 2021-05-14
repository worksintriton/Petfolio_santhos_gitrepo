// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RetailerSetttingsActivity_ViewBinding implements Unbinder {
  private RetailerSetttingsActivity target;

  @UiThread
  public RetailerSetttingsActivity_ViewBinding(RetailerSetttingsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RetailerSetttingsActivity_ViewBinding(RetailerSetttingsActivity target, View source) {
    this.target = target;

    target.bottomNavigation = Utils.findRequiredViewAsType(source, R.id.bottomNavigation, "field 'bottomNavigation'", BottomNavigationView.class);
    target.floatingActionButton = Utils.findRequiredViewAsType(source, R.id.fab, "field 'floatingActionButton'", FloatingActionButton.class);
    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RetailerSetttingsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bottomNavigation = null;
    target.floatingActionButton = null;
    target.img_back = null;
    target.spin_kit_loadingView = null;
    target.txt_toolbar_title = null;
  }
}
