package com.nirigo.mobile.view.passcode;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class PasscodeIndicator extends LinearLayout {

    private int indicatorLength = 4;
    private int indicatorLevel = 0;
    private int indicatorSize = 12;
    private int indicatorMargin = 20;
    private int indicatorAnimation = R.anim.tremble_horizontal;

    private Animation wrongAnimation;
    private boolean animationInProgress;

    private Drawable indicatorBackground = null,
            indicatorBackgroundActive = null;

    public PasscodeIndicator(Context context) {
        super(context);
        init();
        initLevel();
    }

    public PasscodeIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs, 0);
        init();
        initLevel();
    }

    public PasscodeIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs, defStyleAttr);
        init();
        initLevel();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PasscodeIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttrs(context, attrs, defStyleAttr);
        init();
        initLevel();
    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PasscodeIndicator, defStyleAttr, 0);
        indicatorLength = a.getInteger(R.styleable.PasscodeIndicator_indicator_length, 4);
        indicatorLevel = a.getInt(R.styleable.PasscodeIndicator_indicator_level, 0);
        indicatorSize = a.getDimensionPixelSize(R.styleable.PasscodeIndicator_indicator_size, 12);
        indicatorMargin = a.getDimensionPixelOffset(R.styleable.PasscodeIndicator_indicator_margin, 10);
        indicatorBackground = a.getDrawable(R.styleable.PasscodeIndicator_indicator_background);
        indicatorBackgroundActive = a.getDrawable(R.styleable.PasscodeIndicator_indicator_background_active);

        if (indicatorBackground == null)
            indicatorBackground = createColorDrawable(Color.LTGRAY);
        if (indicatorBackgroundActive == null)
            indicatorBackgroundActive = createColorDrawable(Color.BLACK);

        indicatorAnimation = a.getResourceId(R.styleable.PasscodeIndicator_indicator_animation, R.anim.tremble_horizontal);

        a.recycle();
    }

    private void init() {
        setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutParams params = createChildLayoutParams();
        removeAllViewsInLayout();
        for (int i = 0; i < indicatorLength; i++) {
            View v = new View(getContext());
            v.setLayoutParams(params);
            addViewInLayout(v, i, params, true);
        }
    }

    private void initLevel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            for (int i = 0; i < indicatorLength; i++)
                getChildAt(i).setBackground(i < indicatorLevel ? indicatorBackgroundActive : indicatorBackground);
        else
            for (int i = 0; i < indicatorLength; i++)
                //noinspection deprecation
                getChildAt(i).setBackgroundDrawable(i < indicatorLevel ? indicatorBackgroundActive : indicatorBackground);
    }

    protected LayoutParams createChildLayoutParams() {
        LayoutParams params = new LayoutParams(indicatorSize, indicatorSize);
        params.leftMargin = indicatorMargin;
        params.topMargin = indicatorMargin;
        params.rightMargin = indicatorMargin;
        params.bottomMargin = indicatorMargin;
        return params;
    }

    protected ColorDrawable createColorDrawable(int color) {
        ColorDrawable drawable = new ColorDrawable();
        drawable.setColor(color);
        return drawable;
    }

    // Functions -----------------------------------------------------------------------------------
    public void wrongPasscode(){
        if(wrongAnimation == null){
            wrongAnimation = AnimationUtils.loadAnimation(getContext(), indicatorAnimation);
            wrongAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                    animationInProgress = true;
                }
                public void onAnimationRepeat(Animation animation) {}
                public void onAnimationEnd(Animation animation) {
                    animationInProgress = false;
                    setIndicatorLevel(0);
                }
            });
        }
        startAnimation(wrongAnimation);
    }

    public void clear(){
        if(wrongAnimation != null)
            wrongAnimation.cancel();
        setIndicatorLevel(0);
    }

    // Setters -------------------------------------------------------------------------------------
    public void setIndicatorLevel(int level) {
        if (level > indicatorLength) level = indicatorLength;
        else if(level < 0) level = 0;
        indicatorLevel = level;
        initLevel();
    }

    public void incrementIndicatorLevel(){
        setIndicatorLevel(getIndicatorLevel()+1);
    }

    public void decrementIndicatorLevel(){
        setIndicatorLevel(getIndicatorLevel()-1);
    }


    // Getters -------------------------------------------------------------------------------------
    public int getIndicatorLength() {
        return indicatorLength;
    }

    public int getIndicatorLevel() {
        return indicatorLevel;
    }

    public int getIndicatorSize() {
        return indicatorSize;
    }

    public int getIndicatorMargin() {
        return indicatorMargin;
    }

    public int getIndicatorAnimation() {
        return indicatorAnimation;
    }

    public boolean isAnimationInProgress() {
        return animationInProgress;
    }
}
