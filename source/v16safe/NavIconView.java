package com.timelist.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/** Minimal, consistent line icons for the bottom navigation. */
public final class NavIconView extends View {
    public static final int SCHEDULE = 0;
    public static final int AGENDA = 1;
    public static final int PROFILE = 2;

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final float density;
    private int type;
    private int color = Color.rgb(126, 123, 135);

    public NavIconView(Context context, int type) {
        super(context);
        this.type = type;
        density = getResources().getDisplayMetrics().density;
        init();
    }

    public NavIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        density = getResources().getDisplayMetrics().density;
        init();
    }

    private void init() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1.75f * density);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    public void setIconColor(int color) {
        this.color = color;
        invalidate();
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(color);
        float cx = getWidth() / 2f;
        float cy = getHeight() / 2f;
        float s = Math.min(getWidth(), getHeight()) * 0.56f;

        if (type == SCHEDULE) {
            RectF r = new RectF(cx - s * 0.52f, cy - s * 0.46f, cx + s * 0.52f, cy + s * 0.46f);
            canvas.drawRoundRect(r, 3.2f * density, 3.2f * density, paint);
            canvas.drawLine(r.left, cy - s * 0.12f, r.right, cy - s * 0.12f, paint);
            canvas.drawLine(cx, cy - s * 0.12f, cx, r.bottom, paint);
            canvas.drawLine(cx - s * 0.27f, r.top - s * 0.12f, cx - s * 0.27f, r.top + s * 0.08f, paint);
            canvas.drawLine(cx + s * 0.27f, r.top - s * 0.12f, cx + s * 0.27f, r.top + s * 0.08f, paint);
        } else if (type == AGENDA) {
            float left = cx - s * 0.44f;
            float right = cx + s * 0.48f;
            float[] ys = {cy - s * 0.30f, cy, cy + s * 0.30f};
            for (float y : ys) {
                canvas.drawCircle(left, y, 1.15f * density, paint);
                canvas.drawLine(left + s * 0.18f, y, right, y, paint);
            }
        } else {
            canvas.drawCircle(cx, cy - s * 0.20f, s * 0.20f, paint);
            RectF shoulders = new RectF(cx - s * 0.43f, cy + s * 0.03f, cx + s * 0.43f, cy + s * 0.48f);
            canvas.drawArc(shoulders, 198f, 144f, false, paint);
        }
    }
}
