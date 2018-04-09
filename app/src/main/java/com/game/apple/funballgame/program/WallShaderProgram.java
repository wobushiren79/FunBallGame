package com.game.apple.funballgame.program;

import android.content.Context;

import com.game.apple.funballgame.R;

/**
 * Created by apple on 2016/7/11.
 */
public class WallShaderProgram extends ShaderProgram {
    public WallShaderProgram(Context context) {
        super(context, R.raw.wall_vertex_shader, R.raw.wall_fragment_shader);
    }

    @Override
    public void setUnifroms(float[] matrix) {
        super.setUnifroms(matrix);
    }
}
