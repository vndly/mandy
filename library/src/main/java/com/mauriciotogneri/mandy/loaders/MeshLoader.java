package com.mauriciotogneri.mandy.loaders;

import android.opengl.GLES20;

import com.mauriciotogneri.mandy.json.JsonMesh;
import com.mauriciotogneri.mandy.json.JsonTriangle;
import com.mauriciotogneri.mandy.resources.Mesh;

public class MeshLoader
{
    public Mesh load(JsonMesh json)
    {
        float[] buffer = buffer(json);

        return new Mesh(GLES20.GL_TRIANGLES, buffer);
    }

    private float[] buffer(JsonMesh json)
    {
        Triangle[] triangles = triangles(json.triangles);

        return buffer(triangles);
    }

    private Triangle[] triangles(JsonTriangle[] list)
    {
        Triangle[] triangles = new Triangle[list.length];

        for (int i = 0; i < list.length; i++)
        {
            JsonTriangle triangle = list[i];

            float red = triangle.color.red;
            float green = triangle.color.green;
            float blue = triangle.color.blue;
            float alpha = triangle.color.alpha;

            float x1 = triangle.vertices[0].x;
            float y1 = triangle.vertices[0].y;
            float z1 = 0;

            float x2 = triangle.vertices[1].x;
            float y2 = triangle.vertices[1].y;
            float z2 = 0;

            float x3 = triangle.vertices[2].x;
            float y3 = triangle.vertices[2].y;
            float z3 = 0;

            triangles[i] = new Triangle(x1, y1, z1, x2, y2, z2, x3, y3, z3, red, green, blue, alpha);
        }

        return triangles;
    }

    private float[] buffer(Triangle[] triangles)
    {
        float[] buffer = new float[triangles.length * Mesh.TRIANGLE_SIZE];

        int index = 0;

        for (Triangle triangle : triangles)
        {
            buffer[index++] = triangle.x1;
            buffer[index++] = triangle.y1;
            buffer[index++] = triangle.z1;
            buffer[index++] = triangle.red;
            buffer[index++] = triangle.green;
            buffer[index++] = triangle.blue;
            buffer[index++] = triangle.alpha;

            buffer[index++] = triangle.x2;
            buffer[index++] = triangle.y2;
            buffer[index++] = triangle.z2;
            buffer[index++] = triangle.red;
            buffer[index++] = triangle.green;
            buffer[index++] = triangle.blue;
            buffer[index++] = triangle.alpha;

            buffer[index++] = triangle.x3;
            buffer[index++] = triangle.y3;
            buffer[index++] = triangle.z3;
            buffer[index++] = triangle.red;
            buffer[index++] = triangle.green;
            buffer[index++] = triangle.blue;
            buffer[index++] = triangle.alpha;
        }

        return buffer;
    }

    private static class Triangle
    {
        public final float x1;
        public final float y1;
        public final float z1;
        public final float x2;
        public final float y2;
        public final float z2;
        public final float x3;
        public final float y3;
        public final float z3;
        public final float red;
        public final float green;
        public final float blue;
        public final float alpha;

        private Triangle(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float red, float green, float blue, float alpha)
        {
            this.x1 = x1;
            this.y1 = y1;
            this.z1 = z1;
            this.x2 = x2;
            this.y2 = y2;
            this.z2 = z2;
            this.x3 = x3;
            this.y3 = y3;
            this.z3 = z3;
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.alpha = alpha;
        }
    }
}