package com.example.intermediatejavachallenge;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class DoubleClickView extends android.support.v7.widget.AppCompatButton implements DoubleClickInterface{

    private DoubleClickHandler doubleClickHandler;
    public Drawable defaultDrawable;

    public DoubleClickView(Context context) { //Constructors
        super(context);
        doubleClickHandler = new DoubleClickHandler(this);
        defaultDrawable = this.getBackground();
    }
    public DoubleClickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        doubleClickHandler = new DoubleClickHandler(this);
        defaultDrawable = this.getBackground();

    }
    public DoubleClickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        doubleClickHandler = new DoubleClickHandler(this);
        defaultDrawable = this.getBackground();
    }




    @Override
    public void SetOnClickListener(DoubleClickListener doubleClickListener) {
        doubleClickHandler.SetOnClickListener(doubleClickListener);
    }

    @Override
    public boolean performClick() {
        if (doubleClickHandler != null) {
            doubleClickHandler.processClick();
            return true;
        } else {
            return super.performClick();
        }
    }


}
