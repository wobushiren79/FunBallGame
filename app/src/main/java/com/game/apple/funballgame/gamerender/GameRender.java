package com.game.apple.funballgame.gamerender;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.game.apple.funballgame.activity.GameActivity;
import com.game.apple.funballgame.program.BallShaderProgram;
import com.game.apple.funballgame.program.BrickShaderProgram;
import com.game.apple.funballgame.program.FireShaderProgram;
import com.game.apple.funballgame.program.WallShaderProgram;
import com.game.apple.funballgame.program.WoodShaderProgram;
import com.game.apple.funballgame.things.Ball;
import com.game.apple.funballgame.things.BaseThing;
import com.game.apple.funballgame.things.Brick;
import com.game.apple.funballgame.things.BrickList;
import com.game.apple.funballgame.things.FireShooter;
import com.game.apple.funballgame.things.FireSystem;
import com.game.apple.funballgame.things.Geometry;
import com.game.apple.funballgame.things.Wall;
import com.game.apple.funballgame.things.Wood;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.*;
import static android.opengl.Matrix.*;

/**
 * Created by apple on 2016/7/11.
 */
public class GameRender implements GLSurfaceView.Renderer {
    private Context context;

    private int metricsW;
    private int metricsH;
    private int mGameLength;

    private List<BaseThing> CylinderBlockList = new ArrayList<>();

    private float[] projectionMatrix = new float[16];
    private float[] eyeMatrix = new float[16];
    private float[] viewMatrix = new float[16];

    private Wood mWood;
    private WoodShaderProgram woodShaderProgram;

    private Wall mWall;
    private WallShaderProgram wallShaderProgram;

    private Ball mBall;
    private BallShaderProgram ballShaderProgram;

    private BrickList mBrickList;
    private BrickShaderProgram brickShaderProgram;

    private FireSystem fireSystem;
    private FireShooter fireShooter;
    private FireShaderProgram fireShaderProgram;

    private float currentTime;
    private long globalStartTime;

    private float frieangleVarianceInDegrees = 180f;
    private float friespeedVariance = 1f;

    private upData upData;

    private boolean isGameEnd = false;
    private long Point = 0;

    public interface upData {
        void PlaySound(int type);

        void GameEnd(long Point);

        void UpPoint(long point);

    }


    public GameRender(Context context, int metricsW, int metricsH, upData upData) {
        this.context = context;
        this.metricsW = metricsW;
        this.metricsH = metricsH;
        this.upData = upData;
        if (metricsH > metricsW) {
            mGameLength = metricsW;
        } else {
            mGameLength = metricsH;
        }

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(0.968f, 0.949f, 0.694f, 1);
        glEnable(GL_DEPTH_TEST);

        mWood = new Wood(metricsW, metricsH);
        woodShaderProgram = new WoodShaderProgram(context);

        mWall = new Wall(metricsW, metricsH);
        wallShaderProgram = new WallShaderProgram(context);

        mBall = new Ball(metricsW, metricsH);
        ballShaderProgram = new BallShaderProgram(context);

        mBrickList = new BrickList(metricsW, metricsH);
        brickShaderProgram = new BrickShaderProgram(context);

        fireSystem = new FireSystem(1000);
        globalStartTime = System.nanoTime();
//        fireShooter = new FireShooter(new Geometry.Point(0, -metricsH / 4, 0), new Geometry.Vector(0, 1, 0), frieangleVarianceInDegrees, friespeedVariance);
        fireShaderProgram = new FireShaderProgram(context);

        init();
    }

    private void init() {
        CylinderBlockList.add(mWood);
        CylinderBlockList.add(new BaseThing(-metricsW / 2, -metricsH / 2, metricsW / 2, -metricsW / 2, false, false, false));
        CylinderBlockList.add(new BaseThing(metricsH / 2, metricsW / 2, metricsW / 2, -metricsW / 2, false, false, false));
        CylinderBlockList.add(new BaseThing(-metricsH / 2, metricsH / 2, metricsW / 2, metricsH / 2, false, false, false));
        CylinderBlockList.add(new BaseThing(-metricsH / 2, metricsH / 2, -metricsH / 2, -metricsW / 2, false, false, false));

        CylinderBlockList.addAll(mBrickList.getDataList());
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);
        Log.v("this", " width:" + width + " height:" + height);
        perspectiveM(projectionMatrix, 0, 90, (float) width / (float) height, 1f, mGameLength);
        setLookAtM(eyeMatrix, 0, 0, 0, mGameLength / 2, 0, 0, 0, 0, 1f, 0);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        multiplyMM(viewMatrix, 0, projectionMatrix, 0, eyeMatrix, 0);


        if (isGameEnd == true) {

        } else {
            onDrawWood();
            onDrawWall();
            onDrawBall();
            onDrawBrick();
            onDrawFire();
        }
    }

    private void onDrawFire() {
        currentTime = (System.nanoTime() - globalStartTime) / 1000000000f;

        fireShaderProgram.useProgram();
        fireShaderProgram.setUnifroms(viewMatrix, currentTime);
        fireSystem.bindData(fireShaderProgram);
        fireSystem.draw();
    }

    boolean brickboomStart = false;
    int brickboom = 0;

    private void onDrawBrick() {
        brickShaderProgram.useProgram();



        for (Brick brick : mBrickList.getDataList()) {
            float[] temp=new float[16];
            float[] temp2=new float[16];
            setIdentityM(temp,0);

            brick.bindData(brickShaderProgram);

            if (brick.isVisibility()) {
                brick.setInVisibilityTime();
                translateM(temp,0,10000,10000,10000);
            }
            multiplyMM(temp2,0,viewMatrix,0,temp,0);
            if (brick.getInVisibilityTime() > 1000) {
                brick.mIsDisappear = false;
                brick.setVisibility();
                CylinderBlockList.add(brick);
            }

            brickShaderProgram.setUnifroms(temp2, brickboom);
            brick.onDraw();

        }
        if (brickboom > 5) {
            brickboom = 0;
            brickboomStart = false;
        }
    }

    private void onDrawBall() {
        testCrash();
        ballShaderProgram.useProgram();
        ballShaderProgram.setUnifroms(viewMatrix, ballboom);
        mBall.bindData(ballShaderProgram);
        mBall.onDraw();
        mBall.setPoint();

        if (ballboom > 3) {
            ballboom = 0;
            ballboomStart = false;
        }

    }

    float oldLocationX = 0;
    float oldLocationY = 0;
    int ballboom = 0;
    boolean ballboomStart = false;

    private void testCrash() {
        float[] location = mBall.getData();
        float locationx = location[0];
        float locationy = location[1];
        Geometry.Vector vector = mBall.getVector();
        BaseThing tempbody = null;
        for (BaseThing body : CylinderBlockList) {
            if (locationx <= body.mRight && locationx >= body.mLeft && locationy <= body.mTop && locationy >= body.mBelow) {
                if (oldLocationX > body.mRight || oldLocationX < body.mLeft) {

                    if (locationy >= body.mTop || locationy <= body.mBelow) {
                        mBall.setVector(new Geometry.Vector(-vector.x, -vector.y, 0f));
                    } else {
                        mBall.setVector(new Geometry.Vector(-vector.x, vector.y, 0f));
                    }

                } else {
                    mBall.setVector(new Geometry.Vector(vector.x, -vector.y, 0f));

                    //游戏结束
                    if (locationy <= -metricsH / 2) {
                        isGameEnd = true;
                        upData.PlaySound(3);
                        upData.GameEnd(Point);


                    }
                }
                mBall.setLocation(oldLocationX, oldLocationY);
                if (body.mInvisibility) {
                    Brick brick = (Brick) body;
                    if (!body.mIsDisappear) {
                        body.mIsDisappear = true;
                        brick.setInVisibility();
                        tempbody = body;
                    }

                    //砖块被撞动一下
                    brickboomStart = true;
                    upData.PlaySound(1);
                    Point++;
                    upData.UpPoint(Point);
                    mBall.speed+=0.1f;
                } else {
                    if (body.mIsWood) {
                        upData.PlaySound(2);
                    }

                }


                fireShooter = new FireShooter
                        (new Geometry.Point(locationx, locationy, 0),
                                new Geometry.Vector(-mBall.getVector().x, -mBall.getVector().y, 0),
                                frieangleVarianceInDegrees,
                                friespeedVariance);
                fireShooter.addParticles(fireSystem, currentTime, 10);

                ballboomStart = true;
                break;
            }
        }

        if (ballboomStart) {
            ballboom++;
        }

        if (brickboomStart) {
            brickboom++;
        }

        if (tempbody != null) {
            CylinderBlockList.remove(tempbody);
        }

        oldLocationX = locationx;
        oldLocationY = locationy;
    }

    private void onDrawWall() {
        wallShaderProgram.useProgram();
        wallShaderProgram.setUnifroms(viewMatrix);
        mWall.bindData(wallShaderProgram);
        mWall.onDraw();
    }


    private void onDrawWood() {
        float[] tempwoodMatrix = new float[16];
        float[] tempwood = new float[16];

        setIdentityM(tempwoodMatrix, 0);
        translateM(tempwoodMatrix, 0, mWood.getCenterNum(), 0, 0);
        multiplyMM(tempwood, 0, viewMatrix, 0, tempwoodMatrix, 0);


        woodShaderProgram.useProgram();
        woodShaderProgram.setUnifroms(tempwood, mWood.getmWoodH(), mWood.getLeftEyeLocation(), mWood.getRightEyeLocation(), mBall.getData(), mWood.getmWoodH() / 1.5f,mWood.getCenterNum(),mWood.getmWoodW(),metricsH);
        mWood.bindData(woodShaderProgram);
        mWood.onDraw();

    }

    public void setWoodMove(float deltaX, float deltaY) {
        mWood.setCenterNum(deltaX);
        CylinderBlockList.set(0, mWood);
    }

    public void setGameStart() {
        Point = 0;
        CylinderBlockList.clear();
        isGameEnd = false;
        mBall=new Ball(metricsW,metricsH);
        mBrickList=new BrickList(metricsW,metricsH);
        init();
    }
}
