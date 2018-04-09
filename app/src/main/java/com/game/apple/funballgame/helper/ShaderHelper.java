package com.game.apple.funballgame.helper;

import android.content.Context;
import android.util.Log;

import static android.opengl.GLES20.*;

/**
 * Created by apple on 2016/7/11.
 */
public class ShaderHelper {
    public static int getVertexId(String code) {
        return complieShader(code, GL_VERTEX_SHADER);
    }

    public static int getFragmentId(String code) {
        return complieShader(code, GL_FRAGMENT_SHADER);
    }

    private static int complieShader(String code, int type) {
        int shaderId = glCreateShader(type);
        glShaderSource(shaderId, code);
        glCompileShader(shaderId);
        int[] compileStates = new int[1];
        glGetShaderiv(shaderId, GL_COMPILE_STATUS, compileStates, 0);
        if (compileStates[0] == 0) {
            if (type == GL_VERTEX_SHADER) {
                Log.v("this", "上传顶点着色器代码失败:" + glGetShaderInfoLog(shaderId));
            } else if (type == GL_FRAGMENT_SHADER) {
                Log.v("this", "上传片段着色器代码失败:" + glGetShaderInfoLog(shaderId));
            }
            glDeleteShader(shaderId);
            return 0;
        }
        return shaderId;
    }

    public static int getProgramId(Context context, int rawVertexId, int rawFragmentId) {
        String vertexCode = ReadRawHelper.getShaderCode(context, rawVertexId);
        String fragmentCode = ReadRawHelper.getShaderCode(context, rawFragmentId);

        int vertexId = getVertexId(vertexCode);
        int fragmentId = getFragmentId(fragmentCode);

        int program = glCreateProgram();
        glAttachShader(program, vertexId);
        glAttachShader(program, fragmentId);
        glLinkProgram(program);
        int[] linkStates = new int[1];
        glGetProgramiv(program, GL_LINK_STATUS, linkStates, 0);
        if (linkStates[0] == 0) {
            Log.v("this", "连接着色器程序失败:"+glGetProgramInfoLog(program));
            glDeleteProgram(program);
        }

        return program;
    }
}
