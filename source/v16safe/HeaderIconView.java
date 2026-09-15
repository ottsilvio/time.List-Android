package com.timelist.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

/** Flat header action button. Liquid-glass styling is intentionally reserved for course cards. */
public final class HeaderIconView extends View {
    public static final int PLUS = 0;
    public static final int SCAN = 1;

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final float density;
    private int type;

    public HeaderIconView(Context context, int type) {
        super(context);
        this.type = type;
        density = getResources().getDisplayMetrics().density;
        init();
    }

    public HeaderIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        density = getResources().getDisplayMetrics().density;
        init();
    }

    private void init() {
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.rgb(244, 241, 248));
        bg.setCornerRadius(13f * density);
        bg.setStroke(Math.max(1, Math.round(0.7f * density)), Color.rgb(232, 227, 238));
        setBackground(bg);
        paint.setColor(Color.rgb(104, 82, 156));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2.0f * density);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        setClickable(true);
        setFocusable(true);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float cx = getWidth() / 2f;
        float cy = getHeight() / 2f;
        float s = Math.min(getWidth(), getHeight()) * 0.34f;
        if (type == PLUS) {
            canvas.drawLine(cx - s, cy, cx + s, cy, paint);
            canvas.drawLine(cx, cy - s, cx, cy + s, paint);
        } else {
            float x0 = cx - s, x1 = cx + s, y0 = cy - s, y1 = cy + s;
            float k = s * 0.48f;
            canvas.drawLine(x0, y0 + k, x0, y0, paint);
            canvas.drawLine(x0, y0, x0 + k, y0, paint);
            canvas.drawLine(x1 - k, y0, x1, y0, paint);
            canvas.drawLine(x1, y0, x1, y0 + k, paint);
            canvas.drawLine(x0, y1 - k, x0, y1, paint);
            canvas.drawLine(x0, y1, x0 + k, y1, paint);
            canvas.drawLine(x1 - k, y1, x1, y1, paint);
            canvas.drawLine(x1, y1 - k, x1, y1, paint);
            canvas.drawCircle(cx, cy, 1.2f * density, paint);
        }
    }
}
