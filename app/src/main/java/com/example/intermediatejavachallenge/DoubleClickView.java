package com.example.intermediatejavachallenge;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class DoubleClickView extends android.support.v7.widget.AppCompatButton implements DoubleClickInterface{

    private DoubleClickHandler doubleClickHandler;
    private int clicks;
    private Semaphore bgLock;
    private Drawable defaultDrawable;

    public DoubleClickView(Context context) { //Constructors
        super(context);
        doubleClickHandler = new DoubleClickHandler(this);
        clicks = 0;
        bgLock = new Semaphore(1);
        defaultDrawable = this.getBackground();
    }
    public DoubleClickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        doubleClickHandler = new DoubleClickHandler(this);
        clicks = 0;
        bgLock = new Semaphore(1);
        defaultDrawable = this.getBackground();

    }
    public DoubleClickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        doubleClickHandler = new DoubleClickHandler(this);
        clicks = 0;
        bgLock = new Semaphore(1);
        defaultDrawable = this.getBackground();
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
                        Activity activity  = (Activity)doubleClickHandler.view.getContext();
                        if (clicks < 2) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    doubleClickHandler.view.setBackgroundColor(getResources().getColor(R.color.colorButtonPress));
                                }
                            });
                            Thread.sleep(500);
                        }
                        if (clicks > 1) {
                            doubleClickHandler.performClick();
                        }
                        clicks = 0;
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                doubleClickHandler.view.setBackground(defaultDrawable);
                            }
                        });

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
