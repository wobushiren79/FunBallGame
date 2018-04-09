package com.game.apple.funballgame.program;

import android.content.Context;

import com.game.apple.funballgame.R;

import static android.opengl.GLES20.*;

/**
 * Created by apple on 2016/7/12.
 */
public class BrickShaderProgram extends ShaderProgram {
    int mBoomLocation;

    public BrickShaderProgram(Context context) {
        super(context, R.raw.brick_vertex_shader, R.raw.brick_fragment_shader);
        mBoomLocation = glGetUniformLocation(mProgram, "Boom");
    }

    public void setUnifroms(float[] matrix, int boom) {
        super.setUnifroms(matrix);
        glUniform1i(mBoomLocation, boom);
    }
}
