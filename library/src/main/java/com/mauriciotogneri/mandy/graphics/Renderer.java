package com.mauriciotogneri.mandy.graphics;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.mauriciotogneri.mandy.resources.Mesh;
import com.mauriciotogneri.mandy.screen.Camera;

import org.jbox2d.common.Vec2;

public class Renderer
{
    private final float[] modelMatrix = new float[16];
    private final float[] finalMatrix = new float[16];
    private final float[] projectionMatrix = new float[16];

    private int matrixHandler;
    private int positionHandler;
    private int colorHandler;

    private final String vertexShaderSource;
    private final String fragmentShaderSource;

    public Renderer(String vertexShaderSource, String fragmentShaderSource)
    {
        this.vertexShaderSource = vertexShaderSource;
        this.fragmentShaderSource = fragmentShaderSource;
    }

    public void onSurfaceCreated()
    {
        GLES20.glClearColor(0, 0, 0, 1);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        //GLES20.glEnable(GLES20.GL_BLEND);
        //GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        int vertexShader = compileShader(GLES20.GL_VERTEX_SHADER, vertexShaderSource);
        int fragmentShader = compileShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderSource);
        int program = linkProgram(vertexShader, fragmentShader);

        GLES20.glUseProgram(program);

        matrixHandler = GLES20.glGetUniformLocation(program, "u_Matrix");
        colorHandler = GLES20.glGetAttribLocation(program, "a_Color");
        positionHandler = GLES20.glGetAttribLocation(program, "a_Position");
    }

    public void onSurfaceChanged(int width, int height)
    {
        GLES20.glViewport(0, 0, width, height);
    }

    public void start(Camera camera)
    {
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);

        Matrix.orthoM(projectionMatrix, 0, camera.x, camera.x + camera.width, camera.y, camera.y + camera.height, 0f, 10f);

        GLES20.glEnableVertexAttribArray(positionHandler);
        GLES20.glEnableVertexAttribArray(colorHandler);
    }

    public void stop()
    {
        GLES20.glDisableVertexAttribArray(positionHandler);
    }

    public void render(Mesh mesh, float x, float y, float z, float scaleX, float scaleY, float angle)
    {
        moveTo(x, y, z, scaleX, scaleY, angle);

        mesh.render(positionHandler, colorHandler);
    }

    public void render(Mesh[] meshes, Vec2 position, float z, float scaleX, float scaleY, float angle)
    {
        render(meshes, position.x, position.y, z, scaleX, scaleY, angle);
    }

    public void render(Mesh[] meshes, float x, float y, float z, float scaleX, float scaleY, float angle)
    {
        moveTo(x, y, z, scaleX, scaleY, angle);

        for (Mesh mesh : meshes)
        {
            mesh.render(positionHandler, colorHandler);
        }
    }

    private void moveTo(float x, float y, float z, float scaleX, float scaleY, float angle)
    {
        Matrix.setIdentityM(modelMatrix, 0);
        Matrix.translateM(modelMatrix, 0, x, y, z);
        Matrix.scaleM(modelMatrix, 0, scaleX, scaleY, 1);
        Matrix.rotateM(modelMatrix, 0, (float) Math.toDegrees(angle), 0, 0, 1);
        Matrix.multiplyMM(finalMatrix, 0, projectionMatrix, 0, modelMatrix, 0);

        GLES20.glUniformMatrix4fv(matrixHandler, 1, false, finalMatrix, 0);
    }

    private int compileShader(int type, String source)
    {
        // create a new shader object
        int shaderObjectId = GLES20.glCreateShader(type);

        //pass in the shader source
        GLES20.glShaderSource(shaderObjectId, source);

        // compile the shader
        GLES20.glCompileShader(shaderObjectId);

        // get the compilation status
        final int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shaderObjectId, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

        // verify the compile status
        if (compileStatus[0] == 0)
        {
            // if it failed, delete the shader object
            GLES20.glDeleteShader(shaderObjectId);

            return 0;
        }

        // return the shader object ID
        return shaderObjectId;
    }

    private int linkProgram(int vertexShaderId, int fragmentShaderId)
    {
        // create a new program object
        int programObjectId = GLES20.glCreateProgram();

        // attach the vertex shader to the program
        GLES20.glAttachShader(programObjectId, vertexShaderId);

        // attach the fragment shader to the program
        GLES20.glAttachShader(programObjectId, fragmentShaderId);

        // link the two shaders together into a program
        GLES20.glLinkProgram(programObjectId);

        // get the link status
        final int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(programObjectId, GLES20.GL_LINK_STATUS, linkStatus, 0);

        // verify the link status
        if (linkStatus[0] == 0)
        {
            // if it failed, delete the program object
            GLES20.glDeleteProgram(programObjectId);
            return 0;
        }

        // return the program object ID
        return programObjectId;
    }
}