package com.mauriciotogneri.mandy.physics;

import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.broadphase.BroadPhaseStrategy;
import org.jbox2d.collision.broadphase.DynamicTree;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.pooling.IWorldPool;
import org.jbox2d.pooling.normal.DefaultWorldPool;

public class Physics
{
    private World world;

    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;

    public Physics()
    {
        this.world = getWorld();
    }

    public void setContactListener(ContactListener contactListener)
    {
        world.setContactListener(contactListener);
    }

    public Body createBody(BodyDef bodyDef)
    {
        return world.createBody(bodyDef);
    }

    public void removeBody(Body body)
    {
        world.destroyBody(body);
    }

    public void reset()
    {
        world = getWorld();
    }

    private World getWorld()
    {
        Vec2 gravity = new Vec2(0, 0);
        IWorldPool pool = new DefaultWorldPool(10, 10);
        BroadPhaseStrategy strategy = new DynamicTree();

        return new World(gravity, pool, strategy);
    }

    public void update(float delta)
    {
        world.step(delta, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }
}