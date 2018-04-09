package com.game.apple.funballgame.things;

import android.graphics.Color;
import android.util.Log;

import com.game.apple.funballgame.data.VertexArrary;
import com.game.apple.funballgame.program.FireShaderProgram;


import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.glDrawArrays;

/**
 * Created by apple on 2016/5/27.
 */
public class FireSystem {

    private static final int POSITION_COMPONENT_COUNT = 3;
    private static final int COLOR_COMPONENT_COUNT = 3;
    private static final int VECTOR_COMPONENT_COUNT = 3;
    private static final int PARTICLE_START_TIME_COMPONENT_COUNT = 1;


    private static final int TOTAL_COMPONENT_COUNT =
            POSITION_COMPONENT_COUNT
                    + COLOR_COMPONENT_COUNT
                    + VECTOR_COMPONENT_COUNT
                    + PARTICLE_START_TIME_COMPONENT_COUNT;

    private static final int STRIDE = TOTAL_COMPONENT_COUNT * 4;

    private float[] particles;
    private VertexArrary vertexArrary;
    private int maxParticleCount;

    private int currentParticleCount;
    private int nextParticle;

    public FireSystem(int maxParticleCount) {
        this.maxParticleCount = maxParticleCount;
        particles = new float[maxParticleCount * TOTAL_COMPONENT_COUNT];
        vertexArrary = new VertexArrary(particles);
    }

    public void addParticle
            (Geometry.Point position, int color, Geometry.Vector vector, float particleStartTime) {
//        Log.v("this","particleStartTime:"+particleStartTime);
        int particleOffset = nextParticle * TOTAL_COMPONENT_COUNT;

        int currentOffset = particleOffset;
        nextParticle++;

        if (currentParticleCount < maxParticleCount) {
            currentParticleCount++;
        }
        if (nextParticle == maxParticleCount) {
            nextParticle = 0;
        }

        particles[currentOffset++] = position.x;
        particles[currentOffset++] = position.y;
        particles[currentOffset++] = position.z;

        particles[currentOffset++] = (Color.red(color) / 255f) ;
        particles[currentOffset++] = (Color.green(color) / 255f) ;
        particles[currentOffset++] = (Color.blue(color) / 255f) ;

        particles[currentOffset++] = vector.x;
        particles[currentOffset++] = vector.y;
        particles[currentOffset++] = vector.z;

        particles[currentOffset++] = particleStartTime;


        vertexArrary.updataBuffer(particles, particleOffset, TOTAL_COMPONENT_COUNT);
    }

    public void bindData(FireShaderProgram fireShaderProgram) {
        int dataoffset = 0;
        vertexArrary.setData(dataoffset,
                fireShaderProgram.getmPositionLocation(),
                POSITION_COMPONENT_COUNT,
                STRIDE);
        dataoffset += POSITION_COMPONENT_COUNT;

        vertexArrary.setData(dataoffset,
                fireShaderProgram.getColorLocation(),
                COLOR_COMPONENT_COUNT,
                STRIDE);
        dataoffset += COLOR_COMPONENT_COUNT;

        vertexArrary.setData(dataoffset,
                fireShaderProgram.getVectorLocation(),
                VECTOR_COMPONENT_COUNT,
                STRIDE);
        dataoffset += VECTOR_COMPONENT_COUNT;

        vertexArrary.setData(dataoffset,
                fireShaderProgram.getStarttimeLocation(),
                PARTICLE_START_TIME_COMPONENT_COUNT,
                STRIDE);





    }

    public void draw() {
        glDrawArrays(GL_POINTS, 0, currentParticleCount);
    }


}
