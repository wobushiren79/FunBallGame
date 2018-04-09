package com.game.apple.funballgame.program;

import android.content.Context;

import com.game.apple.funballgame.R;

import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform1f;

/**
 * Created by apple on 2016/5/27.
 */
public class FireShaderProgram extends ShaderProgram {

    private int colorLocation;
    private int vectorLocation;
    private int timeLocation;
    private int starttimeLocation;



    public FireShaderProgram(Context context) {
        super(context, R.raw.fire_vertex_shader, R.raw.fire_fragment_shader);


        colorLocation = glGetAttribLocation(mProgram, "Color");
        vectorLocation = glGetAttribLocation(mProgram, "Vector");
        starttimeLocation = glGetAttribLocation(mProgram, "StartTime");


        timeLocation = glGetUniformLocation(mProgram, "Time");
    }


    public void setUnifroms(float[] matrix, float elapsedTime) {
        super.setUnifroms(matrix);
        glUniform1f(timeLocation, elapsedTime);
    }






    public int getColorLocation() {
        return colorLocation;
    }

    public int getVectorLocation() {
        return vectorLocation;
    }

    public int getStarttimeLocation() {
        return starttimeLocation;
    }
}
