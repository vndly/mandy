package com.mauriciotogneri.mandy.physics;

import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.broadphase.BroadPhaseStrategy;
import org.jbox2d.collision.broadphase.DynamicTree;
import org.jbox2d.common.Color3f;
import org.jbox2d.common.OBBViewportTransform;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.particle.ParticleColor;
import org.jbox2d.pooling.IWorldPool;
import org.jbox2d.pooling.normal.DefaultWorldPool;

public class Physics
{
    private final World world;

    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;

    public Physics()
    {
        Vec2 gravity = new Vec2(0, 0);
        IWorldPool pool = new DefaultWorldPool(10, 10);
        BroadPhaseStrategy strategy = new DynamicTree();

        this.world = new World(gravity, pool, strategy);

        world.setDebugDraw(new DebugDraw(new OBBViewportTransform())
        {
            @Override
            public void drawPoint(Vec2 vec2, float v, Color3f color3f)
            {
            }

            @Override
            public void drawSolidPolygon(Vec2[] vec2s, int i, Color3f color3f)
            {
            }

            @Override
            public void drawCircle(Vec2 vec2, float v, Color3f color3f)
            {
            }

            @Override
            public void drawSolidCircle(Vec2 vec2, float v, Vec2 vec21, Color3f color3f)
            {
            }

            @Override
            public void drawSegment(Vec2 vec2, Vec2 vec21, Color3f color3f)
            {
            }

            @Override
            public void drawTransform(Transform transform)
            {
            }

            @Override
            public void drawString(float v, float v1, String s, Color3f color3f)
            {
            }

            @Override
            public void drawParticles(Vec2[] centers, float radius, ParticleColor[] colors, int count)
            {
            }

            @Override
            public void drawParticlesWireframe(Vec2[] centers, float radius, ParticleColor[] colors, int count)
            {
            }
        });
    }

    public void setContactListener(ContactListener contactListener)
    {
        world.setContactListener(contactListener);
    }

    public Body createBody(BodyDef bodyDef)
    {
        return world.createBody(bodyDef);
    }

    public void update(float delta)
    {
        world.step(delta, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }
}