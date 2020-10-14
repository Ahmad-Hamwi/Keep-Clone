package com.ahmadhamwi.keepclone.presentation.ui.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;

import androidx.cardview.widget.CardView;

public class CurvedBottomNavigationView extends CardView {
    private Path mPath;
    private Paint mPaint;

    /** the CURVE_CIRCLE_RADIUS represent the radius of the fab button */
    private final int CURVE_CIRCLE_RADIUS = 64;
    private int radius = 28;
    private int margin = 28;
    private int fabMargin = 34;
    private int layoutOffset = 12;
    private int shadowOffset = 4;
    private float density;

    // the coordinates of the first curve
    private Point mFirstCurveStartPoint = new Point();
    private Point mFirstCurveEndPoint = new Point();
    private Point mFirstCurveControlPoint1 = new Point();
    private Point mFirstCurveControlPoint2 = new Point();

    //the coordinates of the second curve
    @SuppressWarnings("FieldCanBeLocal")
    private Point mSecondCurveStartPoint = new Point();
    private Point mSecondCurveEndPoint = new Point();
    private Point mSecondCurveControlPoint1 = new Point();
    private Point mSecondCurveControlPoint2 = new Point();
    private int mNavigationBarWidth;
    private int mNavigationBarHeight;

    public CurvedBottomNavigationView(Context context) {
        super(context);
        init(context);
    }

    public CurvedBottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CurvedBottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xDFFFFFFF);
        mPaint.setShadowLayer(9, 0, 3, 0xFFAFAFAF);
        setBackgroundColor(Color.TRANSPARENT);
        density = context.getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // get width and height of navigation bar
        // Navigation bar bounds (width & height)
        mNavigationBarWidth = getMeasuredWidth();
        mNavigationBarHeight = getMeasuredHeight();
        radius *= density;
        margin *= density;
        fabMargin *= density;
        layoutOffset *= density;
        shadowOffset *= density;

        // the coordinates (x,y) of the start point before curve
        mFirstCurveStartPoint.set((mNavigationBarWidth - fabMargin - radius) - (int)(radius / 1.4) - layoutOffset, 0);
        // the coordinates (x,y) of the end point after curve
        mFirstCurveEndPoint.set((mNavigationBarWidth - fabMargin - radius), (int)(radius / 1.5));
        // same thing for the second curve
        mSecondCurveStartPoint = mFirstCurveEndPoint;
        mSecondCurveEndPoint.set((mNavigationBarWidth - fabMargin - radius) + (int)(radius / 1.4) + layoutOffset, 0);

        // the coordinates (x,y)  of the 1st control point on a cubic curve
        mFirstCurveControlPoint1.set(mFirstCurveStartPoint.x + (layoutOffset / 2), mFirstCurveStartPoint.y);
        // the coordinates (x,y)  of the 2nd control point on a cubic curve
        mFirstCurveControlPoint2.set(mFirstCurveEndPoint.x - (int)(radius * 0.80), mFirstCurveEndPoint.y);

        mSecondCurveControlPoint1.set(mSecondCurveStartPoint.x + (int)(radius * 0.80), mSecondCurveStartPoint.y);
        mSecondCurveControlPoint2.set(mSecondCurveEndPoint.x - (layoutOffset / 2), mSecondCurveEndPoint.y);

        mPath.reset();
        mPath.moveTo(0,  + shadowOffset);
        mPath.lineTo(mFirstCurveStartPoint.x, mFirstCurveStartPoint.y + shadowOffset);

        mPath.cubicTo(mFirstCurveControlPoint1.x, mFirstCurveControlPoint1.y + shadowOffset,
                mFirstCurveControlPoint2.x, mFirstCurveControlPoint2.y + shadowOffset,
                mFirstCurveEndPoint.x, mFirstCurveEndPoint.y + shadowOffset);

        mPath.cubicTo(mSecondCurveControlPoint1.x, mSecondCurveControlPoint1.y + shadowOffset,
                mSecondCurveControlPoint2.x, mSecondCurveControlPoint2.y + shadowOffset,
                mSecondCurveEndPoint.x, mSecondCurveEndPoint.y + shadowOffset);

        mPath.lineTo(mNavigationBarWidth, 0 + shadowOffset);
        mPath.lineTo(mNavigationBarWidth, mNavigationBarHeight);
        mPath.lineTo(0, mNavigationBarHeight);
        mPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }
}