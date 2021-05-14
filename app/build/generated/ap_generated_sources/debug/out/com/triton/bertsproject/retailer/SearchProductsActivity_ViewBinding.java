// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.EditText;
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

public class SearchProductsActivity_ViewBinding implements Unbinder {
  private SearchProductsActivity target;

  @UiThread
  public SearchProductsActivity_ViewBinding(SearchProductsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchProductsActivity_ViewBinding(SearchProductsActivity target, View source) {
    this.target = target;

    target.floatingActionButton = Utils.findRequiredViewAsType(source, R.id.fab, "field 'floatingActionButton'", FloatingActionButton.class);
    target.bottomNavigation = Utils.findRequiredViewAsType(source, R.id.bottomNavigation, "field 'bottomNavigation'", BottomNavigationView.class);
    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
    target.edt_search = Utils.findRequiredViewAsType(source, R.id.edt_search, "field 'edt_search'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchProductsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.floatingActionButton = null;
    target.bottomNavigation = null;
    target.spin_kit_loadingView = null;
    target.img_back = null;
    target.txt_toolbar_title = null;
    target.edt_search = null;
  }
}
