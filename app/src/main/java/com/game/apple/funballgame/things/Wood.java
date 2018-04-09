package com.game.apple.funballgame.things;

import com.game.apple.funballgame.data.VertexArrary;
import com.game.apple.funballgame.program.WoodShaderProgram;

import static android.opengl.GLES20.*;

/**
 * Created by apple on 2016/7/11.
 */
public class Wood extends BaseThing {
    VertexArrary vertexArrary;


    private final static int TEMPH = 20;
    private final static int TEMPW = 5;
    private int mWoodW;  //木板宽
    private int mWoodH;   //木板高

    float[] leftEyeLocation;
    float[] rightEyeLocation;


    private int mGameLength; //游戏主画面的大小

    private float centerNum;

    float[] wood;

    public Wood(int metricsW, int metricsH) {
        super(-metricsH / TEMPW / 2, metricsH / TEMPW / 2, -(metricsH / 2) + (metricsH / TEMPH), -metricsH / 2, false, false, true);
        if (metricsW > metricsH) {
            mWoodH = metricsH / TEMPH;
            mWoodW = metricsH / TEMPW;
            mGameLength = metricsH;
        } else {
            mWoodH = metricsW / TEMPH;
            mWoodW = metricsW / TEMPW;
            mGameLength = metricsW;
        }


        wood = new float[]{
                -mWoodW / 2, -mGameLength / 2,
                mWoodW / 2, -mGameLength / 2,
                mWoodW / 2, -mGameLength / 2 + mWoodH,
                -mWoodW / 2, -mGameLength / 2 + mWoodH,
                -mWoodW / 2, -mGameLength / 2
        };

        centerNum = 0;
        vertexArrary = new VertexArrary(wood);

        leftEyeLocation = new float[]{mLeft + getmWoodH() / 2 + mWoodH / 4, mBelow + getmWoodH() / 2};
        rightEyeLocation = new float[]{mRight - getmWoodH() / 2 - mWoodH / 4, mBelow + getmWoodH() / 2};

    }


    public float[] getRightEyeLocation() {
        return rightEyeLocation;
    }

    public float[] getLeftEyeLocation() {
        return leftEyeLocation;
    }

    public void bindData(WoodShaderProgram woodShaderProgram) {
        vertexArrary.setData(0, woodShaderProgram.getmPositionLocation(), 2, 2 * 4);
    }

    public void onDraw() {
        glDrawArrays(GL_TRIANGLE_FAN, 0, wood.length / 2);
    }


    public float getCenterNum() {
        return centerNum;
    }

    public void setCenterNum(float num) {

        centerNum += num;
        mLeft += num;
        mRight += num;
        if (centerNum > mGameLength / 2 - mWoodW / 2) {
            centerNum = mGameLength / 2 - mWoodW / 2;
            mLeft = mGameLength / 2 - mWoodW;
            mRight = mGameLength / 2;
        } else if (centerNum < -mGameLength / 2 + mWoodW / 2) {
            centerNum = -mGameLength / 2 + mWoodW / 2;
            mLeft = -mGameLength / 2;
            mRight = -mGameLength / 2 + mWoodW;
        }
    }

    public int getmWoodH() {
        return mWoodH;
    }

    public int getmWoodW() {
        return mWoodW;
    }
}
