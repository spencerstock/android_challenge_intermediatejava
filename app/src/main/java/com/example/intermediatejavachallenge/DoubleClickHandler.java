package com.example.intermediatejavachallenge;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;

public class DoubleClickHandler implements DoubleClickInterface {
    public  View                view;
    private DoubleClickListener doubleClickListener;
    private static final int DOUBLE_CLICK_TIMER = 500;
    private int clicks;
    private Drawable defaultBackground;


    public DoubleClickHandler(View view) {
        this.view = view;
        defaultBackground = view.getBackground();
        clicks = 0;
    }

    @Override
    public void SetOnClickListener(DoubleClickListener doubleClickListener) {
        this.doubleClickListener = doubleClickListener;
    }

    public void processClick() {
        new Thread(new Runnable() {
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
                        Thread.sleep(DOUBLE_CLICK_TIMER);
                    }
                    if (clicks > 1) sendClickAction(true);
                    else if (clicks == 1) sendClickAction(false);
                    clicks = 0;
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
