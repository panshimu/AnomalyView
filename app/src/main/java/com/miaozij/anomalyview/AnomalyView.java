package com.miaozij.anomalyview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class AnomalyView extends View {
    private Paint mSquarePaint;//正方形画笔
    private Paint mTrianglePaint;//三角形
    private Paint mCirclePaint;//圆形
    private SHOWTYPE mCurrentShowType = SHOWTYPE.CIRCLE;
    public enum SHOWTYPE{
        CIRCLE,SQUARE,TRIANGLE
    }
    public AnomalyView(Context context) {
        this(context,null);
    }

    public AnomalyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AnomalyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mSquarePaint = getPaintByColor(Color.RED);
        mCirclePaint = getPaintByColor(Color.BLUE);
        mTrianglePaint = getPaintByColor(Color.GREEN);
    }

    private Paint getPaintByColor(int color){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width>height?height:width,width>height?height:width);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if(mCurrentShowType == SHOWTYPE.CIRCLE) {
            //画一个圆
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mCirclePaint);
        }else if(mCurrentShowType == SHOWTYPE.SQUARE){
            //画正方形
            canvas.drawRect(0,0,getWidth(),getHeight(),mSquarePaint);
        }else if(mCurrentShowType == SHOWTYPE.TRIANGLE){

            //画三角形
            Path path = new Path();

            float x1 = getWidth()/2;
            float x2 = (float) ((getWidth()/2)*( 1- Math.sqrt(3) /2 ));
            float y2 = 3 * getWidth() / 4;
            float x3 = (float) ( x2 + Math.sqrt(3) * getWidth() / 2);

            //先确定一个点
            path.moveTo(x1,0);
            path.lineTo(x2,y2);
            path.lineTo(x3,y2);

            path.close();
            canvas.drawPath(path,mTrianglePaint);

        }else {
            super.onDraw(canvas);
        }
    }

    public void updateView(SHOWTYPE showType){
        this.mCurrentShowType = showType;
        invalidate();
    }

    public SHOWTYPE getCurrentShowType(){
        return this.mCurrentShowType;
    }
}
