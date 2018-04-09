package com.game.apple.funballgame.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.game.apple.funballgame.R;
import com.game.apple.funballgame.base.BaseActivity;
import com.game.apple.funballgame.gamerender.GameRender;

import java.util.HashMap;
import java.util.Map;

public class GameActivity extends BaseActivity implements GameRender.upData {
    private GLSurfaceView mGameSurfaceView;
    private GameRender mGameRender;

    private RelativeLayout mTitleLayout;
    private TextView mPoint;

    private SoundPool soundPool;
    private Map<Integer, Integer> soundMap;

    private long Point;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    AlertDialog dialog = new AlertDialog
                            .Builder(GameActivity.this)
                            .setTitle("游戏结束")
                            .setMessage("得分(" + Point + ")\n是否重新开始游戏?")
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mGameRender.setGameStart();
                                    mPoint.setText("0");
                                }
                            }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }).show();
                    break;
                case 1:
                    mPoint.setText((long) msg.obj + "");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);

        soundMap = new HashMap<Integer, Integer>();
        soundMap.put(1, soundPool.load(GameActivity.this, R.raw.bricksound3, 1));
        soundMap.put(2, soundPool.load(GameActivity.this, R.raw.woodsound2, 1));
        soundMap.put(3, soundPool.load(GameActivity.this, R.raw.gameendsound1, 1));


        init();
    }

    private void init() {
        mGameSurfaceView = (GLSurfaceView) findViewById(R.id.glsurfaceview);
        mTitleLayout = (RelativeLayout) findViewById(R.id.relativelayout);
        mPoint = (TextView) findViewById(R.id.point);

        setGameSurfaceView();
        mPoint.setTextColor(Color.parseColor("#D36538"));

    }

    private void setGameSurfaceView() {
        mGameRender = new GameRender(this, metrics.widthPixels, metrics.heightPixels, this);
        mGameSurfaceView.setEGLContextClientVersion(2);
        mGameSurfaceView.setRenderer(mGameRender);
        mGameSurfaceView.setOnTouchListener(new View.OnTouchListener() {
            float previousX, previousY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event != null) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        previousX = event.getX();
                        previousY = event.getY();
                    } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                        final float deltaX = event.getX() - previousX;
                        final float deltaY = event.getY() - previousY;
                        previousX = event.getX();
                        previousY = event.getY();
//                        mGameSurfaceView.queueEvent(new Runnable() {
//                            @Override
//                            public void run() {
//
//                            }
//                        });

                        mGameRender.setWoodMove(deltaX, deltaY);
                    }
                    return true;
                } else {
                    return false;
                }
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        mGameSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGameSurfaceView.onResume();
    }

    @Override
    public void PlaySound(int type) {

        soundPool.play(soundMap.get(type), 1, 1, 0, 0, 1);
    }

    @Override
    public void GameEnd(long Point) {
        handler.obtainMessage(0).sendToTarget();
        this.Point = Point;
    }

    @Override
    public void UpPoint(long point) {
        handler.obtainMessage(1, point).sendToTarget();
    }


}
