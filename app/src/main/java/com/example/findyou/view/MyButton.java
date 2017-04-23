package com.example.findyou.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

import com.example.findyou.R;

public class MyButton extends Button {

    protected Drawable drawable;
    private float drawableHeight;
    private float drawableWidth;
    private float buttonHeight;

    public MyButton(Context context,AttributeSet attrs) {
        super(context,attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.my);
        drawable = a.getDrawable(R.styleable.my_drawable);

        drawableWidth = drawable.getIntrinsicWidth();
        drawableHeight = drawable.getIntrinsicHeight();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);

        buttonHeight = MeasureSpec.getSize(heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        drawable.setBounds(10, 10, (int)((buttonHeight - 20)/drawableHeight * drawableWidth + 10), (int)(buttonHeight - 10 ));

        drawable.draw(canvas);
    }

}
