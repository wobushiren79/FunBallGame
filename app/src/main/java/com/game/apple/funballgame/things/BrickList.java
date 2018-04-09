package com.game.apple.funballgame.things;

import com.game.apple.funballgame.data.VertexArrary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2016/7/12.
 */
public class BrickList {


    private final static int HNUM = 7;
    private final static int VNUM = 6;

    float spaceH;
    float spaceW;

    float borderW;
    float borderH;

    float brickSizeW;
    float brickSizeH;

    float brickW;
    float brickH;


    List<Brick> dataList = new ArrayList<>();

    public List<Brick> getDataList() {
        return dataList;
    }

    public BrickList(int metricsW, int metricsH) {

        borderW = metricsH / 10;
        borderH = metricsH / 10;

        spaceH = metricsH / 40;
        spaceW = metricsH / 40;

        brickSizeW = metricsH - borderW * 2;
        brickSizeH = metricsH / 2 - borderH;

        brickW = (brickSizeW - ((HNUM - 1) * spaceW)) / HNUM;
        brickH = (brickSizeH - ((VNUM - 1) * spaceH)) / VNUM;

        for (int i = 0; i < VNUM; i++) {
            for (int j = 0; j < HNUM; j++) {
                float[] data = new float[12];
                data[0] = -brickSizeW / 2 + j * (brickW + spaceW);
                data[1] = i * (brickH + spaceH);

                data[2] = -brickSizeW / 2 + j * (brickW + spaceW) + brickW;
                data[3] = i * (brickH + spaceH);

                data[4] = -brickSizeW / 2 + j * (brickW + spaceW) + brickW;
                data[5] = i * (brickH + spaceH) + brickH;

                data[6] = -brickSizeW / 2 + j * (brickW + spaceW);
                data[7] = i * (brickH + spaceH);

                data[8] = -brickSizeW / 2 + j * (brickW + spaceW) + brickW;
                data[9] = i * (brickH + spaceH) + brickH;

                data[10] = -brickSizeW / 2 + j * (brickW + spaceW);
                data[11] = i * (brickH + spaceH) + brickH;
                Brick brick = new Brick(data, data[0], data[2], data[5], data[1], false, true);
                dataList.add(brick);

            }
        }
    }
}
