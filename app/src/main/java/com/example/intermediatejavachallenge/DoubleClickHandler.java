package com.example.intermediatejavachallenge;

import android.app.Activity;
import android.view.View;

public class DoubleClickHandler implements DoubleClickInterface{
    private DoubleClickListener doubleClickListener;
    public View view;

    public DoubleClickHandler(View view) {
        this.view = view;
    }

    @Override
    public void SetOnClickListener(DoubleClickListener doubleClickListener) {
        this.doubleClickListener = doubleClickListener;
    }

    public boolean performClick() {
        if (doubleClickListener != null) {
            Activity activity = (Activity) view.getContext();
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    doubleClickListener.OnDoubleClick();
                }
            });
            return true;
        } else return false;
    }
}
