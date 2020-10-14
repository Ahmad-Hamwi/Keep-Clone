package com.ahmadhamwi.keepclone.presentation.ui.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

public class HomeNavigationView extends CardView {
    public HomeNavigationView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public HomeNavigationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HomeNavigationView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private Path mPathStroke;
    private Path mPathFilled;
    private Paint mPaintStroke;
    private Paint mPaintFilled;

    private float density;
    private int radius = 28;
    private int margin = 28;
    private int fabMargin = 34;
    private int layoutOffset = 12;

    // the coordinates of the first curve
    private Point start1 = new Point();
    private Point end1 = new Point();
    private Point control11 = new Point();
    private Point control12 = new Point();

    //the coordinates of the second curve
    @SuppressWarnings("FieldCanBeLocal")
    private Point start2 = new Point();
    private Point end2 = new Point();
    private Point control21 = new Point();
    private Point control22 = new Point();

    private int w;
    private int h;

    private void init(Context context) {

        density = context.getResources().getDisplayMetrics().density;

        setBackgroundColor(Color.TRANSPARENT);

        mPathStroke = new Path();
        mPathFilled = new Path();

//        mPaintStroke = new Paint();
//        mPaintStroke.setStyle(Paint.Style.FILL);
//        mPaintStroke.setColor(0xFFE0E0E0);
//        mPaintStroke.setAntiAlias(true);

        mPaintFilled = new Paint();
        mPaintFilled.setStyle(Paint.Style.FILL);
//        mPaintFilled.setColor(0xEFFFFFFF); //this is white transparent
        mPaintFilled.setColor(0xFFAFAFAF);
        mPaintFilled.setAntiAlias(true);


    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        w = getWidth();
        h = getHeight();
        radius *= density;
        margin *= density;
        fabMargin *= density;
        layoutOffset *= density;

        start1.set((w - fabMargin - radius) - (radius + layoutOffset), 0);
        end1.set((w - fabMargin - radius), -(fabMargin + layoutOffset));

        control11.set(start1.x + layoutOffset, start1.y);
        control12.set(control11.x, end1.y);


        start2 = end1;
        end2.set((w - fabMargin - radius) + (radius + layoutOffset), 0);

        control21.set(end2.x, start2.y);
        control22.set(end2.x - layoutOffset, end2.y);


//        mPathStroke.reset();
//        mPathStroke.moveTo(0, 0);
//        mPathStroke.lineTo(start1.x, start1.y);
//
//        mPathStroke.cubicTo(control11.x, control11.y, control12.x, control12.y, end1.x, end1.y);
//        mPathStroke.cubicTo(control21.x, control21.y, control22.x, control22.y, end2.x, end2.y);
//
//        mPathStroke.lineTo(w, 0);
//        mPathStroke.lineTo(w, h);
//        mPathStroke.lineTo(0, h);
//        mPathStroke.close();

        mPathFilled.reset();
        mPathFilled.lineTo(start1.x, start1.y);

        mPathFilled.cubicTo(control11.x, control11.y, control12.x, control12.y, end1.x, end1.y);
        System.out.println(control11.x);
        System.out.println(control11.y);
        System.out.println(control12.x);
        System.out.println(control12.y);
        System.out.println(end1.x);
        System.out.println(end1.y);
//        mPathFilled.cubicTo(control21.x, control21.y, control22.x, control22.y, end2.x, end2.y);
//
//        mPathFilled.lineTo(w, 0);
//        mPathFilled.lineTo(w, h);
//        mPathFilled.lineTo(0, h);
        mPathFilled.close();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            canvas.enableZ();
            setElevation(20f);
        }
//        canvas.drawPath(mPathStroke, mPaintStroke);
        canvas.drawPath(mPathFilled, mPaintFilled);
    }
}
