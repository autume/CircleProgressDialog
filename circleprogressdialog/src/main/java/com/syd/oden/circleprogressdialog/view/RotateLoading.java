package com.syd.oden.circleprogressdialog.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.syd.oden.circleprogressdialog.R;

/**
 * Created by Oden on 2016/7/23.
 */
public class RotateLoading extends View {

    private final int DEFAULT_COLOR = Color.WHITE; //默认颜色
    private final int DEFAULT_WIDTH = 6;
    private final int DEFAULT_SHADOW_POSITION = 2;
    private final boolean DEFAULT_VISIBLE = true;

    private int color = DEFAULT_COLOR;
    private int width = dpToPx(DEFAULT_WIDTH);
    private int shadowOffset = dpToPx(DEFAULT_SHADOW_POSITION);
    private boolean isStart = DEFAULT_VISIBLE;

    private Paint mPaint = new Paint();
    private RectF loadingRectF;
    private RectF shadowRectF;

    //对称的两个弧形起始角度
    private int topDegree = 10;
    private int bottomDegree = 190;

    private float arc;    //弧形的另一边逐渐减小
    private boolean changeBigger = true;

    public RotateLoading(Context context) {
        this(context, null);
    }

    public RotateLoading(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotateLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyleAttrs(attrs);
        initView();
    }

    /**
     * 获取自定义属性
     * @param attrs
     */
    private void obtainStyleAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.RotateLoading);
        color = typedArray.getColor(R.styleable.RotateLoading_loading_color, color);
        width = (int) typedArray.getDimension(R.styleable.RotateLoading_loading_width, width);
        shadowOffset = (int) typedArray.getDimension(R.styleable.RotateLoading_shadow_offset, shadowOffset);
        isStart = typedArray.getBoolean(R.styleable.RotateLoading_loading_visible, isStart);
        typedArray.recycle();
    }


    private void initView() {
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(width);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isStart) {
            return;
        }

        mPaint.setColor(Color.parseColor("#1a000000"));
        canvas.drawArc(shadowRectF, topDegree, arc, false, mPaint);
        canvas.drawArc(shadowRectF, bottomDegree, arc, false, mPaint);

        mPaint.setColor(color);
        canvas.drawArc(loadingRectF, topDegree, arc, false, mPaint);
        canvas.drawArc(loadingRectF, bottomDegree, arc, false, mPaint);

        topDegree += 10;
        bottomDegree += 10;
        if (topDegree > 360) {
            topDegree = topDegree - 360;
        }
        if (bottomDegree > 360) {
            bottomDegree = bottomDegree - 360;
        }

        if (changeBigger) {
            if (arc < 160) {
                arc += 2.5;
                invalidate();
            }
        } else {
            if (arc > 10) {
                arc -= 5;
                invalidate();
            }
        }
        if (arc == 160 || arc == 10) {
            changeBigger = !changeBigger;
            invalidate();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        arc = 10;
        //占满界面 边距为2*width
        loadingRectF = new RectF(2 * width, 2 * width, w - 2 * width, h - 2 * width);
        shadowRectF = new RectF(2 * width + shadowOffset, 2 * width + shadowOffset, w - 2 * width + shadowOffset, h - 2 * width + shadowOffset);
    }


    public void start() {
        startAnimator();
        isStart = true;
        invalidate();
    }

    public void stop() {
        stopAnimator();
        invalidate();
    }

    public boolean isStart() {
        return isStart;
    }

    private void startAnimator() {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", 0.0f, 1);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", 0.0f, 1);
        scaleXAnimator.setDuration(500);
        scaleYAnimator.setDuration(500);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
        animatorSet.start();
    }

    private void stopAnimator() {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", 1, 0);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", 1, 0);
        scaleXAnimator.setDuration(500);
        scaleYAnimator.setDuration(500);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isStart = false;
            }
        });
        animatorSet.start();
    }

    public void setWidth(int width) {
        this.width = dpToPx(width);
        mPaint.setStrokeWidth(width);
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setShadowOffset(int shadowOffset) {
        this.shadowOffset = dpToPx(shadowOffset);
    }

    private int dpToPx(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
    }

}

