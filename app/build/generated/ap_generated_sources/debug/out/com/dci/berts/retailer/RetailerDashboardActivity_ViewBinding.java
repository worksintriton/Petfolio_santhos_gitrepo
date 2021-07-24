// Generated code from Butter Knife. Do not modify!
package com.dci.berts.retailer;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.dci.berts.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RetailerDashboardActivity_ViewBinding implements Unbinder {
  private RetailerDashboardActivity target;

  @UiThread
  public RetailerDashboardActivity_ViewBinding(RetailerDashboardActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RetailerDashboardActivity_ViewBinding(RetailerDashboardActivity target, View source) {
    this.target = target;

    target.floatingActionButton = Utils.findRequiredViewAsType(source, R.id.fab, "field 'floatingActionButton'", FloatingActionButton.class);
    target.bottomNavigation = Utils.findRequiredViewAsType(source, R.id.bottomNavigation, "field 'bottomNavigation'", BottomNavigationView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RetailerDashboardActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.floatingActionButton = null;
    target.bottomNavigation = null;
  }
}
