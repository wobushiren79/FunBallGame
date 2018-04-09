package com.game.apple.funballgame.things;

import com.game.apple.funballgame.data.VertexArrary;
import com.game.apple.funballgame.program.BallShaderProgram;
import com.game.apple.funballgame.program.WallShaderProgram;

import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.glDrawArrays;

/**
 * Created by apple on 2016/7/11.
 */
public class Ball {
    VertexArrary vertexArrary;
    Geometry.Vector vector;
    public  float speed = 10f;


    float[] data;

    public float[] getData() {
        return data;
    }

    public Geometry.Vector getVector() {
        return vector;
    }

    public void setVector(Geometry.Vector vector) {
        this.vector = vector;
    }

    public Ball(int metricsW, int metricsH) {
        data = new float[]{(metricsH / 2) - 1, (-metricsH / 2) + 1};
//        data = new float[]{0, 0};
        vertexArrary = new VertexArrary(data);
        vector = new Geometry.Vector((float) Math.random() / 2 + 0.5f, (float) Math.random() / 2 + 0.5f, 0f);
    }

    public void setLocation(float x, float y) {
        data = new float[]{x, y};
        vertexArrary = new VertexArrary(data);
    }


    public void setPoint() {
        data[0] += vector.x * speed;
        data[1] += vector.y * speed;
        vertexArrary = new VertexArrary(data);
    }

    public void bindData(BallShaderProgram ballShaderProgram) {
        vertexArrary.setData(0, ballShaderProgram.getmPositionLocation(), 2, 2 * 4);
    }

    public void onDraw() {
        glDrawArrays(GL_POINTS, 0, data.length / 2);
    }
}
