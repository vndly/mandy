package com.mauriciotogneri.mandy.graphics;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Mesh
{
    private final int mode;
    private final int numberOfTriangles;
    private final FloatBuffer bufferData;

    private static final int POSITION_DATA_SIZE = 3;
    private static final int POSITION_OFFSET = 0;
    private static final int COLOR_DATA_SIZE = 4;
    private static final int COLOR_OFFSET = 3;
    private static final int BYTES_PER_FLOAT = 4;
    private static final int STRIDE = (POSITION_DATA_SIZE + COLOR_DATA_SIZE) * BYTES_PER_FLOAT;

    public static final int TRIANGLE_SIZE = (POSITION_DATA_SIZE + COLOR_DATA_SIZE) * 3;

    public Mesh(int mode, float[] buffer)
    {
        this.mode = mode;

        this.numberOfTriangles = buffer.length / (POSITION_DATA_SIZE + COLOR_DATA_SIZE);

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(buffer.length * BYTES_PER_FLOAT);
        this.bufferData = byteBuffer.order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.bufferData.put(buffer);
    }

    public void render(int positionHandler, int colorHandler)
    {
        bufferData.position(0);

        bufferData.position(POSITION_OFFSET);
        GLES20.glVertexAttribPointer(positionHandler, POSITION_DATA_SIZE, GLES20.GL_FLOAT, false, STRIDE, bufferData);

        bufferData.position(COLOR_OFFSET);
        GLES20.glVertexAttribPointer(colorHandler, COLOR_DATA_SIZE, GLES20.GL_FLOAT, false, STRIDE, bufferData);

        GLES20.glDrawArrays(mode, 0, numberOfTriangles);
    }
}