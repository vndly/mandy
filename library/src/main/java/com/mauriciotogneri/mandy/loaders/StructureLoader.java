package com.mauriciotogneri.mandy.loaders;

import com.mauriciotogneri.mandy.json.JsonBody;
import com.mauriciotogneri.mandy.json.JsonShape;
import com.mauriciotogneri.mandy.json.JsonVertex;
import com.mauriciotogneri.mandy.resources.Shape;
import com.mauriciotogneri.mandy.resources.Structure;

import org.jbox2d.common.Vec2;

public class StructureLoader
{
    public Structure load(JsonBody json)
    {
        Shape[] shapes = shapes(json.shapes);

        return new Structure(shapes);
    }

    private Shape[] shapes(JsonShape[] list)
    {
        Shape[] shapes = new Shape[list.length];

        for (int i = 0; i < list.length; i++)
        {
            JsonShape shape = list[i];

            Vec2[] vertices = vertices(shape.vertices);

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