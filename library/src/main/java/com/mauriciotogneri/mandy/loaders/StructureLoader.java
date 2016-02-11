package com.mauriciotogneri.mandy.loaders;

import com.mauriciotogneri.mandy.json.JsonBody;
import com.mauriciotogneri.mandy.json.JsonShape;
import com.mauriciotogneri.mandy.json.JsonVertex;
import com.mauriciotogneri.mandy.resources.Shape;
import com.mauriciotogneri.mandy.resources.Structure;

import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import java.util.List;

public class StructureLoader
{
    public Structure load(JsonBody json)
    {
        Structure structure = new Structure();
        structure.load(getShapes(json.shapes));

        return structure;
    }

    private List<Shape> getShapes(JsonShape[] shapes)
    {
        List<Shape> result = new ArrayList<>(shapes.length);

        for (JsonShape shape : shapes)
        {
            List<Vec2> vertices = new ArrayList<>(shape.vertices.length);

            for (JsonVertex vertex : shape.vertices)
            {
                vertices.add(new Vec2(vertex.x, vertex.y));
            }

            result.add(new Shape(vertices));
        }

        return result;
    }
}