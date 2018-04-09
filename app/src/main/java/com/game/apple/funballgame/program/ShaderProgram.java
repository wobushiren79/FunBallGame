package com.game.apple.funballgame.program;

import android.content.Context;

import com.game.apple.funballgame.helper.ShaderHelper;

import static android.opengl.GLES20.*;

/**
 * Created by apple on 2016/7/11.
 */
public abstract class ShaderProgram {
    int mProgram;
    int mPositionLocation;
    int mMatrixLocation;

    protected ShaderProgram(Context context, int rawVertexShaderId, int rawFragmentShaderId) {
        mProgram = ShaderHelper.getProgramId(context, rawVertexShaderId, rawFragmentShaderId);

        mPositionLocation = glGetAttribLocation(mProgram, "Position");
        mMatrixLocation = glGetUniformLocation(mProgram, "Matrix");
    }

    protected void setUnifroms(float[] matrix) {
        glUniformMatrix4fv(mMatrixLocation, 1, false, matrix, 0);
    }

    public int getmPositionLocation() {
        return mPositionLocation;
    }

    public void useProgram() {
        glUseProgram(mProgram);
    }
}
