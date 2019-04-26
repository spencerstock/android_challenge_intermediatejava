package com.example.intermediatejavachallenge;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.atomic.AtomicBoolean;

public class DoubleClickView extends android.support.v7.widget.AppCompatButton implements DoubleClickInterface{

    private DoubleClickHandler doubleClickHandler;
    private int clicks;

    public DoubleClickView(Context context) { //Constructors
        super(context);
        doubleClickHandler = new DoubleClickHandler(this);
        clicks = 0;
    }
    public DoubleClickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        doubleClickHandler = new DoubleClickHandler(this);
        clicks = 0;
    }
    public DoubleClickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        doubleClickHandler = new DoubleClickHandler(this);
        clicks = 0;
    }




    @Override
    public void SetOnClickListener(DoubleClickListener doubleClickListener) {
        doubleClickHandler.SetOnClickListener(doubleClickListener);
    }

    @Override
    public boolean performClick() {
        if (doubleClickHandler != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    clicks++;
                    try {
                        if (clicks < 2) {
                            Thread.sleep(1000);
                        }
                        if (clicks > 1) {
                            doubleClickHandler.performClick();
                        }
                        clicks = 0;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            return true;
        } else {
            return super.performClick();
        }
    }

}
