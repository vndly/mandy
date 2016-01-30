package com.mauriciotogneri.mandy.resources;

import android.opengl.GLES20;

import com.mauriciotogneri.mandy.physics.Physics;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.util.ArrayList;
import java.util.List;

public class Structure
{
    private final BodyType type;
    private List<Shape> shapes;

    public Structure(BodyType type)
    {
        this.type = type;
    }

    public void load(List<Shape> shapes)
    {
        this.shapes = shapes;
    }

    public Body addTo(float x, float y, Physics physics)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = type;

        Body body = physics.createBody(bodyDef);

        for (Shape shape : shapes)
        {
            addFixture(body, shape);
        }

        return body;
    }

    private void addFixture(Body body, Shape shape)
    {
        Vec2[] vertices = shape.getVertices();

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.set(vertices, vertices.length);
        //polygonShape.m_centroid.set(0, 0); // polygon origin position relative to body position

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1;
        fixtureDef.friction = 0;
        fixtureDef.restitution = 0;

        body.createFixture(fixtureDef);
    }

    public List<Mesh> getShadows(int color)
    {
        List<Mesh> result = new ArrayList<>(shapes.size());

        for (Shape shape : shapes)
        {
            float[] buffer = shape.getShadow(color);

            Mesh mesh = new Mesh(GLES20.GL_TRIANGLE_FAN);
            mesh.load(buffer);

            result.add(mesh);
        }

        return result;
    }
}