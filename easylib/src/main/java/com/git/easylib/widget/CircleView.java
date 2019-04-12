package com.git.easylib.widget;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {

    private Paint paint;//画笔
    private RectF oval; // RectF对象（圆环）
    private int currentDegree = 0;//当前度数（除360可求百分比）
    @SuppressLint("NewApi")
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();//颜色渐变插值器
    private int height;//控件高
    private int width;//控件宽
    private int circleWidth;//圆环宽

    private final static int strokeWidth = 40;//画笔大小

    private boolean isGradual = true;//是否显示渐变色

    public CircleView(Context context) {
        this(context, null, 0);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
        //初始化画笔
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        oval = new RectF();
        paint.setStrokeWidth(strokeWidth); // 线宽
        paint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        //计算最小宽度
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        width = View.MeasureSpec.getSize(widthMeasureSpec);

        if(width >= height) {
            circleWidth = height;
        } else {
            circleWidth = width;
        }

        setMeasuredDimension(circleWidth, circleWidth);
        oval.left = strokeWidth / 2; // 左边
        oval.top = strokeWidth / 2; // 上边
        oval.right = circleWidth - strokeWidth / 2; // 右边
        oval.bottom = circleWidth - strokeWidth / 2; // 下边
//        //自动旋转
        handler.postDelayed(runnable, 500);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        canvas.drawArc(oval,-90 + currentDegree, 1.3f , false, paint); //绘制圆弧
        for (int i = 0; i < currentDegree; i++) {
            if (!isGradual) {
                if (i < 180) {
                    paint.setColor(Color.BLUE);//右半边颜色
                } else {
                    paint.setColor(Color.GREEN);//所半边颜色
                }
            } else {
                Integer color;
                if (i < 180) {
                     color = (Integer) argbEvaluator.evaluate(i / 360f, Color.BLUE, Color.GREEN);//颜色插值器（level 11以上才可以用）
                } else {
                     color = (Integer) argbEvaluator.evaluate(i / 360f, Color.GREEN, Color.BLUE);//颜色插值器（level 11以上才可以用）
                }

//                Integer color = (Integer) argbEvaluator.evaluate(i / 360f, Color.BLUE, Color.GREEN);//颜色插值器（level 11以上才可以用）
                paint.setColor(color);
            }
            if (i % 2 == 0) {
                canvas.drawArc(oval, -90 + i, 1.35f, false, paint); // 绘制圆弧 1.35f是每个色块宽度
            }
        }
        // 绘制白色的底部背景
        if(currentDegree < 360) {
            paint.setColor(Color.WHITE);//右半边颜色
            for(int j = currentDegree; j < 360; j++) {
                if (j % 2 == 0) {
                    canvas.drawArc(oval, -90 + j, 1.35f, false, paint); // 绘制圆弧 1.35f是每个色块宽度
                }
            }
        }
    }

    /**
     * 根据百分比设置颜色范围
     * @param pDegree
     */
    public void setCurrentDegree(float pDegree) {
        this.currentDegree = (int)(360f * pDegree);
    }

    /**
     * 颜色是否渐变
     * @param gradual
     */
    public void setGradual(boolean gradual)
    {
        this.isGradual = gradual;
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            if (currentDegree > 360)
                currentDegree = 0;
            invalidate();
            handler.postDelayed(runnable, 6);//6毫秒绘制一次（可以根据需要更改）
            currentDegree++;
        }
    };

}
