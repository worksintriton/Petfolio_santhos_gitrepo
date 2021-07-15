// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchProductListActivity_ViewBinding implements Unbinder {
  private SearchProductListActivity target;

  @UiThread
  public SearchProductListActivity_ViewBinding(SearchProductListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchProductListActivity_ViewBinding(SearchProductListActivity target, View source) {
    this.target = target;

    target.floatingActionButton = Utils.findRequiredViewAsType(source, R.id.fab, "field 'floatingActionButton'", FloatingActionButton.class);
    target.bottomNavigation = Utils.findRequiredViewAsType(source, R.id.bottomNavigation, "field 'bottomNavigation'", BottomNavigationView.class);
    target.spin_kit_loadingView = Utils.findRequiredViewAsType(source, R.id.spin_kit_loadingView, "field 'spin_kit_loadingView'", SpinKitView.class);
    target.img_back = Utils.findRequiredViewAsType(source, R.id.img_back, "field 'img_back'", ImageView.class);
    target.txt_toolbar_title = Utils.findRequiredViewAsType(source, R.id.txt_toolbar_title, "field 'txt_toolbar_title'", TextView.class);
    target.rv_filterlist = Utils.findRequiredViewAsType(source, R.id.rv_filterlist, "field 'rv_filterlist'", RecyclerView.class);
    target.rv_searchprodlist = Utils.findRequiredViewAsType(source, R.id.rv_searchprodlist, "field 'rv_searchprodlist'", RecyclerView.class);
    target.txt_no_records = Utils.findRequiredViewAsType(source, R.id.txt_no_records, "field 'txt_no_records'", TextView.class);
    target.txt_total_results = Utils.findRequiredViewAsType(source, R.id.txt_total_results, "field 'txt_total_results'", TextView.class);
    target.ll_sort = Utils.findRequiredViewAsType(source, R.id.ll_sort, "field 'll_sort'", LinearLayout.class);
    target.rl_sort_filter = Utils.findRequiredViewAsType(source, R.id.rl_sort_filter, "field 'rl_sort_filter'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchProductListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.floatingActionButton = null;
    target.bottomNavigation = null;
    target.spin_kit_loadingView = null;
    target.img_back = null;
    target.txt_toolbar_title = null;
    target.rv_filterlist = null;
    target.rv_searchprodlist = null;
    target.txt_no_records = null;
    target.txt_total_results = null;
    target.ll_sort = null;
    target.rl_sort_filter = null;
  }
}
