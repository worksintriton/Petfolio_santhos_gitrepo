// Generated code from Butter Knife. Do not modify!
package com.triton.bertsproject.retailer;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.triton.bertsproject.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PaymentMethodActivity_ViewBinding implements Unbinder {
  private PaymentMethodActivity target;

  @UiThread
  public PaymentMethodActivity_ViewBinding(PaymentMethodActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PaymentMethodActivity_ViewBinding(PaymentMethodActivity target, View source) {
    this.target = target;

    target.rdGroupPayment = Utils.findRequiredViewAsType(source, R.id.rdGroupPayment, "field 'rdGroupPayment'", RadioGroup.class);
    target.rdpaymgtwy = Utils.findRequiredViewAsType(source, R.id.rdpaymgtwy, "field 'rdpaymgtwy'", RadioButton.class);
    target.rdcreditlimit = Utils.findRequiredViewAsType(source, R.id.rdcreditlimit, "field 'rdcreditlimit'", RadioButton.class);
    target.rdcod = Utils.findRequiredViewAsType(source, R.id.rdcod, "field 'rdcod'", RadioButton.class);
    target.btn_payment_gateway = Utils.findRequiredViewAsType(source, R.id.btn_payment_gateway, "field 'btn_payment_gateway'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PaymentMethodActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rdGroupPayment = null;
    target.rdpaymgtwy = null;
    target.rdcreditlimit = null;
    target.rdcod = null;
    target.btn_payment_gateway = null;
  }
}
