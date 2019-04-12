package com.git.easylib.ui.nomal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by yiping on 15/11/25.
 */
public class TagEditText extends EditText {

    public TagEditText(Context context) {
        super(context);
    }

    public TagEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TagEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(20);
        paint.setColor(Color.GRAY);
        canvas.drawText(mTag, 10, getHeight() / 2 + 5, paint);
        super.onDraw(canvas);
    }

    private String mTag = "";

    public void setTag(String tag) {
        mTag = tag + ":";
        postInvalidate();
    }
}
