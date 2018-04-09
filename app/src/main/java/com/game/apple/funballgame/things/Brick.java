package com.game.apple.funballgame.things;

import com.game.apple.funballgame.data.VertexArrary;
import com.game.apple.funballgame.program.BrickShaderProgram;
import com.game.apple.funballgame.program.WoodShaderProgram;

import java.util.ArrayList;
import java.util.List;

import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.GL_TRIANGLE_FAN;
import static android.opengl.GLES20.glDrawArrays;

/**
 * Created by apple on 2016/7/11.
 */
public class Brick extends BaseThing {
    VertexArrary vertexArrary;
    int InVisibilityTime = 0;
    boolean isInVisibility = false;
    float[] data = new float[12];



    public Brick(float[] theData, float left, float right, float top, float below, boolean isd, boolean v) {
        super(left, right, top, below, isd, v,false);
        data = theData;
        vertexArrary = new VertexArrary(data);
    }

    public void setInVisibility() {
        isInVisibility = true;
    }

    public void setVisibility() {
        InVisibilityTime = 0;
        isInVisibility = false;
    }

    public int getInVisibilityTime() {
        return InVisibilityTime;
    }

    public boolean isVisibility() {
        return isInVisibility;
    }

    public void setVisibility(boolean visibility) {
        isInVisibility = visibility;
    }

    public void setInVisibilityTime() {
        InVisibilityTime += 1;
    }

    public void bindData(BrickShaderProgram brickShaderProgram) {
        vertexArrary.setData(0, brickShaderProgram.getmPositionLocation(), 2, 2 * 4);
    }

    public void onDraw() {
        glDrawArrays(GL_TRIANGLES, 0, data.length / 2);
    }
}
