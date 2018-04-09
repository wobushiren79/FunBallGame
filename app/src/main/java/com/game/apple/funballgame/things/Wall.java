package com.game.apple.funballgame.things;

import android.util.Log;

import com.game.apple.funballgame.data.VertexArrary;
import com.game.apple.funballgame.program.WallShaderProgram;
import com.game.apple.funballgame.program.WoodShaderProgram;

import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.GL_TRIANGLE_FAN;
import static android.opengl.GLES20.glDrawArrays;

/**
 * Created by apple on 2016/7/11.
 */
public class Wall {

    VertexArrary vertexArrary;
    private static final int TMEPW = 20;
    private int mWallH;
    private int mWallW;


    private int mGameLength; //游戏主画面的大小

    float[] wall;

    public Wall(int metricsW, int metricsH) {

        if (metricsW > metricsH) {
            mWallH = metricsH;
            mWallW = metricsH / TMEPW;
            mGameLength = metricsH;
        } else {
            mWallH = metricsH;
            mWallW = metricsH / TMEPW;
            mGameLength = metricsW;
        }

        wall = new float[]{
                -mGameLength / 2 - mWallW, -mGameLength,
                -mGameLength / 2, -mGameLength,
                -mGameLength / 2, mGameLength,

                -mGameLength / 2 - mWallW, -mGameLength,
                -mGameLength / 2, mGameLength,
                -mGameLength / 2 - mWallW, mGameLength,


                mGameLength / 2, -mGameLength,
                mGameLength / 2 + mWallW, -mGameLength,
                mGameLength / 2 + mWallW, mGameLength,

                mGameLength / 2, -mGameLength,
                mGameLength / 2 + mWallW, mGameLength,
                mGameLength / 2, mGameLength

        };

        vertexArrary = new VertexArrary(wall);
    }

    public void bindData(WallShaderProgram wallShaderProgram) {
        vertexArrary.setData(0, wallShaderProgram.getmPositionLocation(), 2, 2 * 4);
    }

    public void onDraw() {
        glDrawArrays(GL_TRIANGLES, 0, wall.length / 2);
    }
}
