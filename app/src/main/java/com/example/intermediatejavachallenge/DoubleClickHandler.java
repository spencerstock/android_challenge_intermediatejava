package com.example.intermediatejavachallenge;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class DoubleClickHandler implements DoubleClickInterface {
    private static final int DOUBLE_CLICK_TIMER = 600;

    public  View                view;
    private DoubleClickListener doubleClickListener;
    private int                 clicks;
    private Drawable            defaultBackground;
    private Semaphore lock;
    private static int sleepingThreads = 0;


    public DoubleClickHandler(View view) {
        this.view = view;
        defaultBackground = view.getBackground();
        clicks = 0;
        lock = new Semaphore(1);
    }

    @Override
    public void SetOnClickListener(DoubleClickListener doubleClickListener) {
        this.doubleClickListener = doubleClickListener;
    }

    public void processClick() {
        new Thread(new Runnable() { //every click that comes in is a new thread
            @Override
            public void run() {
                clicks++;
                try {
                    Activity activity = (Activity) view.getContext();
                    if (clicks < 2) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                view.setBackgroundColor(view.getResources().getColor(R.color.colorButtonPress));
                            }
                        });
                        sleepingThreads++;
                        Thread.sleep(DOUBLE_CLICK_TIMER);
                        sleepingThreads--;
                        if (sleepingThreads != 0) return;
                        lock.acquire();
                    }
                    if (clicks > 1) {
                        sendClickAction(true);
                    }
                    else if (clicks == 1) sendClickAction(false);
                    clicks = 0;
                    lock.release();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.setBackground(defaultBackground);
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private boolean sendClickAction(boolean doubleClicked) {
        Activity activity = (Activity) view.getContext();
        if (doubleClicked) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    doubleClickListener.OnDoubleClick();
                }
            });
        } else {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    doubleClickListener.OnSingleClick();
                }
            });
        }
        return true;
    }
}
