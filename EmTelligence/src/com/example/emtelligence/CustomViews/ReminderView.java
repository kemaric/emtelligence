package com.example.emtelligence.CustomViews;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import java.util.concurrent.CancellationException;

/**
 * Created by Kemari Legg on 5/21/2015.
 */
public class ReminderView extends View {
    public ReminderView(Context context){
        super(context);
    }

    @Override
    protected void onDraw(Canvas convas){

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        setMeasuredDimension(100,100);
    }
}
