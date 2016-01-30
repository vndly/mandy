package com.mauriciotogneri.mandy.resources;

import android.graphics.Color;

import jbox2d.common.Vec2;

import java.util.List;

public class Shape
{
    private final List<Vec2> vertices;

    public Shape(List<Vec2> vertices)
    {
        this.vertices = vertices;
    }

    public Vec2[] getVertices()
    {
        return vertices.toArray(new Vec2[vertices.size()]);
    }

    public float[] getShadow(int color)
    {
        float[] buffer = new float[vertices.size() * (3 + 4)];

        int index = 0;

        for (Vec2 vertex : vertices)
        {
            buffer[index++] = vertex.x;
            buffer[index++] = vertex.y;
            buffer[index++] = 0;

            buffer[index++] = Color.red(color) / 255f;
            buffer[index++] = Color.green(color) / 255f;
            buffer[index++] = Color.blue(color) / 255f;
            buffer[index++] = Color.alpha(color) / 255f;
        }

        return buffer;
    }
}