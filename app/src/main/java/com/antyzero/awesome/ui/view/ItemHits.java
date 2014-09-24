package com.antyzero.awesome.ui.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.antyzero.awesome.R;

/**
 *
 */
public class ItemHits extends RelativeLayout implements ValueAnimator.AnimatorUpdateListener {

    private TextView textViewTitle;
    private TextView textViewSubTitle;
    private TextView textViewCounter;

    private View viewPercent;

    private float percent = 0f;

    @SuppressWarnings("UnusedDeclaration")
    public ItemHits(Context context) {
        super(context);
        init( context);
    }

    @SuppressWarnings("UnusedDeclaration")
    public ItemHits(Context context, AttributeSet attrs) {
        super(context, attrs);
        init( context);
    }

    @SuppressWarnings("UnusedDeclaration")
    public ItemHits(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init( context);
    }

    /**
     * Inflate layout
     *
     * @param context layout inflation
     */
    private void init(Context context) {

        LayoutInflater.from(context).inflate(R.layout.item_hits, this, true);

        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        textViewSubTitle = (TextView) findViewById(R.id.textViewSubTitle);
        textViewCounter = (TextView) findViewById(R.id.textViewCounter);

        viewPercent = findViewById(R.id.viewPercent);
    }

    public void setTitle(CharSequence  title) {
        textViewTitle.setText(title);
    }

    public void setSubTitle(CharSequence subTitle) {
        textViewSubTitle.setVisibility(VISIBLE);
        textViewSubTitle.setText(subTitle);
    }

    public void setCounter(CharSequence counter) {
        textViewCounter.setText(counter);
    }

    /**
     * Set progress bar amount
     *
     * @param percent float in range of [0f,1f], values outside range will be reduced / increased
     */
    public void setPercent(float percent){

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, percent);

        valueAnimator.addUpdateListener(this);
        valueAnimator.setDuration(1000l);
        valueAnimator.start();
    }

    /**
     * Bar animation
     *
     * {@inheritDoc}
     */
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {

        Float percent = (Float) animation.getAnimatedValue();

        viewPercent.getLayoutParams().width = (int) (((float) getWidth()) * percent);
        viewPercent.requestLayout();
    }
}
