// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyWishlistActivity_ViewBinding implements Unbinder {
  private MyWishlistActivity target;

  @UiThread
  public MyWishlistActivity_ViewBinding(MyWishlistActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyWishlistActivity_ViewBinding(MyWishlistActivity target, View source) {
    this.target = target;

    target.bottomNavigation = Utils.findRequiredViewAsType(source, R.id.bottomNavigation, "field 'bottomNavigation'", BottomNavigationView.class);
    target.coordinatorLayout = Utils.findRequiredViewAsType(source, R.id.coordinator, "field 'coordinatorLayout'", CoordinatorLayout.class);
    target.floatingActionButton = Utils.findRequiredViewAsType(source, R.id.fab, "field 'floatingActionButton'", FloatingActionButton.class);
    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
    target.rv_productlist = Utils.findRequiredViewAsType(source, R.id.rv_productlist, "field 'rv_productlist'", RecyclerView.class);
    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyWishlistActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bottomNavigation = null;
    target.coordinatorLayout = null;
    target.floatingActionButton = null;
    target.img_back = null;
    target.rv_productlist = null;
    target.spin_kit_loadingView = null;
    target.txt_toolbar_title = null;
  }
}
