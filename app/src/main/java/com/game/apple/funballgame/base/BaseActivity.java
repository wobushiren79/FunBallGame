package com.game.apple.funballgame.base;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by apple on 2016/7/11.
 */
public class BaseActivity extends FragmentActivity {
    //屏幕长宽
    public DisplayMetrics metrics = new DisplayMetrics();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //全屏显示 去掉状态栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //不待机
        getWindow().setFlags
                (WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //得到屏幕长宽
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Log.v("this", "Base:" + metrics.widthPixels + " " + metrics.heightPixels);

//        metrics.heightPixels += getStatusBarHeight();
    }

    private int getStatusBarHeight() {
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }
}
