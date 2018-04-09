package com.game.apple.funballgame.things;

import android.graphics.Color;
import android.util.Log;


import java.util.Random;

import static android.opengl.Matrix.multiplyMV;
import static android.opengl.Matrix.setRotateEulerM;

/**
 * Created by apple on 2016/5/27.
 */
public class FireShooter {

    private Geometry.Point position;
    private Geometry.Vector vector;

    private float angleVariance;
    private float speedVariance;

    private Random random = new Random();

    private float[] rotationMatrix = new float[16];
    private float[] directionVector = new float[4];
    private float[] resultVector = new float[4];



    public FireShooter(Geometry.Point position, Geometry.Vector vector, float angleVarianceInDegrees, float speedVariance) {
        this.angleVariance = angleVarianceInDegrees;
        this.speedVariance = speedVariance;

        directionVector[0] = vector.x;
        directionVector[1] = vector.y;
        directionVector[2] = vector.z;

        this.position = position;
        this.vector = vector;
    }

    public void addParticles(FireSystem fireSystem, float currentTime, int count) {

        int colorr = (int) (Math.random() * 255);
        int colorg = (int) (Math.random() * 255);
        int colorb = (int) (Math.random() * 255);
        int color = Color.rgb(colorr, colorg, colorb);
        for (int i = 0; i < count; i++) {
            setRotateEulerM(rotationMatrix, 0,
                    (random.nextFloat() - 0.5f) * angleVariance,
                    (random.nextFloat() - 0.5f) * angleVariance,
                    (random.nextFloat() - 0.5f) * angleVariance);

            multiplyMV(
                    resultVector, 0,
                    rotationMatrix, 0,
                    directionVector, 0);

            float speedAdjustment = 1f + random.nextFloat() * speedVariance;

            Geometry.Vector thisDirection = new Geometry.Vector(
                    resultVector[0] * speedAdjustment,
                    resultVector[1] * speedAdjustment,
                    resultVector[2] * speedAdjustment);


//                Log.v("this","tempTime:"+tempTime);
                fireSystem.addParticle(position, color, thisDirection, currentTime );

        }
    }
}
