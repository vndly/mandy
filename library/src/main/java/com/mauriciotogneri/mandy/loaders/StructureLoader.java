package com.mauriciotogneri.mandy.loaders;

import com.mauriciotogneri.mandy.json.JsonTriangle;
import com.mauriciotogneri.mandy.json.JsonVertex;
import com.mauriciotogneri.mandy.resources.Shape;
import com.mauriciotogneri.mandy.resources.Structure;

import org.jbox2d.common.Vec2;

public class StructureLoader
{
    public Structure load(JsonVertex[][] json, JsonTriangle[][] list)
    {
        Shape[] shapes;

        if (json != null)
        {
            shapes = shapes(json);
        }
        else
        {
            JsonTriangle[] triangles = list[0];
            shapes = new Shape[triangles.length];

            for (int i = 0; i < triangles.length; i++)
            {
                JsonTriangle triangle = triangles[i];

                Vec2[] vertices = new Vec2[triangle.vertices.length];

                for (int j = 0; j < triangle.vertices.length; j++)
                {
                    JsonVertex vertex = triangle.vertices[j];

                    vertices[j] = new Vec2(vertex.x, vertex.y);
                }

                shapes[i] = new Shape(vertices);
            }
        }

        return new Structure(shapes);
    }

    private Shape[] shapes(JsonVertex[][] list)
    {
        Shape[] shapes = new Shape[list.length];

        for (int i = 0; i < list.length; i++)
        {
            JsonVertex[] shape = list[i];

            Vec2[] vertices = vertices(shape);

            shapes[i] = new Shape(vertices);
        }

        return shapes;
    }

    private Vec2[] vertices(JsonVertex[] list)
    {
        Vec2[] vertices = new Vec2[list.length];

        for (int i = 0; i < list.length; i++)
        {
            JsonVertex vertex = list[i];

            vertices[i] = new Vec2(vertex.x, vertex.y);
        }

        return vertices;
    }
}