// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShowAllParentCategoriesActivity_ViewBinding implements Unbinder {
  private ShowAllParentCategoriesActivity target;

  @UiThread
  public ShowAllParentCategoriesActivity_ViewBinding(ShowAllParentCategoriesActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShowAllParentCategoriesActivity_ViewBinding(ShowAllParentCategoriesActivity target,
      View source) {
    this.target = target;

    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.rv_top_categories = Utils.findRequiredViewAsType(source, R.id.rv_top_categories, "field 'rv_top_categories'", RecyclerView.class);
    target.rl_root = Utils.findRequiredViewAsType(source, R.id.rl_root, "field 'rl_root'", RelativeLayout.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
    target.txt_no_records = Utils.findRequiredViewAsType(source, R.id.txt_no_records, "field 'txt_no_records'", TextView.class);
    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
    target.refresh_layout = Utils.findRequiredViewAsType(source, R.id.refresh_layout, "field 'refresh_layout'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShowAllParentCategoriesActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.spin_kit_loadingView = null;
    target.rv_top_categories = null;
    target.rl_root = null;
    target.txt_toolbar_title = null;
    target.txt_no_records = null;
    target.img_back = null;
    target.refresh_layout = null;
  }
}
