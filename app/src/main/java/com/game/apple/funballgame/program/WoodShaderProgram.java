package com.game.apple.funballgame.program;

import android.content.Context;
import android.util.Log;

import com.game.apple.funballgame.R;

import static android.opengl.GLES20.*;

/**
 * Created by apple on 2016/7/11.
 */
public class WoodShaderProgram extends ShaderProgram {

    int mEyeRLocation;
    int mLeftEyeLocation;
    int mRightEyeLocation;
    int mBallLocation;
    int mEyeBallRLocation;

    int mCenterLocation;
    int mWoodWidthLocation;
    int mHLocation;

    public WoodShaderProgram(Context context) {
        super(context, R.raw.wood_vertex_shader, R.raw.wood_fragment_shader);
        mEyeRLocation = glGetUniformLocation(mProgram, "EyeR");
        mLeftEyeLocation = glGetUniformLocation(mProgram, "LeftEyeLocation");
        mRightEyeLocation = glGetUniformLocation(mProgram, "RightEyeLocation");

        mBallLocation = glGetUniformLocation(mProgram, "BallLocation");
        mEyeBallRLocation = glGetUniformLocation(mProgram, "EyeBallR");

        mCenterLocation = glGetUniformLocation(mProgram, "Center");
        mWoodWidthLocation = glGetUniformLocation(mProgram, "WoodWidth");
        mHLocation=glGetUniformLocation(mProgram,"H");
    }

    public void setUnifroms(float[] matrix, float EyeR, float[] leftEyeLocation, float[] rightEyeLocation, float[] ballLocation, float EyeBallR,float center,float woodWidth,float h) {
        super.setUnifroms(matrix);
        glUniform1f(mEyeRLocation, EyeR);
        glUniform2f(mLeftEyeLocation, leftEyeLocation[0], leftEyeLocation[1]);
        glUniform2f(mRightEyeLocation, rightEyeLocation[0], rightEyeLocation[1]);

        glUniform2f(mBallLocation, ballLocation[0], ballLocation[1]);
        glUniform1f(mEyeBallRLocation, EyeBallR);

        glUniform1f(mCenterLocation, center);
        glUniform1f(mWoodWidthLocation, woodWidth);

       glUniform1f(mHLocation,h);
    }
}
