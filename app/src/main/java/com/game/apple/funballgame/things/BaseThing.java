package com.game.apple.funballgame.things;

/**
 * Created by apple on 2016/7/11.
 */
public class BaseThing {
    public float mLeft;
    public float mRight;
    public float mTop;
    public float mBelow;
    public boolean mIsDisappear;
    public boolean mInvisibility;

    public boolean mIsWood;

    public BaseThing(float mLeft, float mRight, float mTop, float mBelow, boolean mIsDisappear, boolean mInvisibility, boolean mIsWood) {
        this.mLeft = mLeft;
        this.mRight = mRight;
        this.mTop = mTop;
        this.mBelow = mBelow;
        this.mIsDisappear = mIsDisappear;
        this.mInvisibility = mInvisibility;
        this.mIsWood = mIsWood;
    }
}
