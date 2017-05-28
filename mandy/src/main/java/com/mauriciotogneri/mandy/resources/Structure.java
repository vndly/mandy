package com.mauriciotogneri.mandy.resources;

import android.opengl.GLES20;

import com.mauriciotogneri.mandy.physics.Physics;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class Structure
{
    private final Shape[] shapes;

    public Structure(Shape[] shapes)
    {
        this.shapes = shapes;
    }

    public Body addTo(float x, float y, BodyType type, Physics physics, int collisionCategory, int collisionMask, boolean isSensor)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = type;

        Body body = physics.createBody(bodyDef);

        for (Shape shape : shapes)
        {
            addFixture(body, shape, collisionCategory, collisionMask, isSensor);
        }

        return body;
    }

    private void addFixture(Body body, Shape shape, int collisionCategory, int collisionMask, boolean isSensor)
    {
        Vec2[] vertices = shape.vertices();

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.set(vertices, vertices.length);
        //polygonShape.m_centroid.set(0, 0); // polygon origin position relative to body position

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1;
        fixtureDef.friction = 0;
        fixtureDef.restitution = 0;
        fixtureDef.isSensor = isSensor;

        if ((collisionCategory != 0) || (collisionMask != 0))
        {
            fixtureDef.filter.categoryBits = collisionCategory;
            fixtureDef.filter.maskBits = collisionMask;
        }

        body.createFixture(fixtureDef);
    }

    public Mesh[] shadows(int color)
    {
        Mesh[] meshes = new Mesh[shapes.length];

        for (int i = 0; i < shapes.length; i++)
        {
            Shape shape = shapes[i];

            float[] buffer = shape.shadow(color);

            Mesh mesh = new Mesh(GLES20.GL_TRIANGLE_FAN, buffer);

            meshes[i] = mesh;
        }

        return meshes;
    }
}