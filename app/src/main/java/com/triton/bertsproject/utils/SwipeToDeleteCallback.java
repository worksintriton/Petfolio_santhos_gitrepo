package com.triton.bertsproject.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.triton.bertsproject.R;

public abstract class SwipeToDeleteCallback extends ItemTouchHelper.Callback {
    private final int backgroundColor = Color.parseColor("#FFF41C");
    private final Drawable deleteDrawable;
    private final int intrinsicHeight;
    private final int intrinsicWidth;
    private final ColorDrawable mBackground = new ColorDrawable();
    private final Paint mClearPaint;
    Context mContext;
    Canvas canvas;
    float f;

    protected SwipeToDeleteCallback(Context context) {
        this.mContext = context;
        Paint paint = new Paint();
        this.mClearPaint = paint;
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Drawable drawable = ContextCompat.getDrawable(this.mContext, R.drawable.ic_baseline_delete_forever_24);
        this.deleteDrawable = drawable;
        this.intrinsicWidth = drawable.getIntrinsicWidth();
        this.intrinsicHeight = drawable.getIntrinsicHeight();
    }

    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, 4);
    }

    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        canvas = c;
        f = dX;
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View itemView = viewHolder.itemView;
        int itemHeight = itemView.getHeight();
        if (f == 0.0f && !isCurrentlyActive) {
            clearCanvas(c, ((float) itemView.getRight()) + f, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            return;
        }
        this.mBackground.setColor(this.backgroundColor);
        this.mBackground.setBounds(itemView.getRight() + ((int) f), itemView.getTop(), itemView.getRight(), itemView.getBottom());
        this.mBackground.draw(c);
        int top = itemView.getTop();
        int i = this.intrinsicHeight;
        int deleteIconTop = top + ((itemHeight - i) / 2);
        int deleteIconMargin = (itemHeight - i) / 2;
        this.deleteDrawable.setBounds((itemView.getRight() - deleteIconMargin) - this.intrinsicWidth, deleteIconTop, itemView.getRight() - deleteIconMargin, this.intrinsicHeight + deleteIconTop);
        this.deleteDrawable.draw(c);
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    private void clearCanvas(Canvas c, Float left, Float top, Float right, Float bottom) {
        c.drawRect(left, top, right, bottom, this.mClearPaint);
    }

    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return 0.7f;
    }
}
