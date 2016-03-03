package com.mauriciotogneri.mandy.kernel;

import android.graphics.Color;

import com.mauriciotogneri.mandy.graphics.Renderer;
import com.mauriciotogneri.mandy.input.Input;
import com.mauriciotogneri.mandy.physics.Physics;
import com.mauriciotogneri.mandy.resources.Mesh;
import com.mauriciotogneri.mandy.resources.Model;
import com.mauriciotogneri.mandy.screen.Camera;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

public class GameObject implements Entity
{
    protected final Body body;
    private final Mesh[] meshes;
    private final Mesh[] shadow;
    private final DisplayMode displayMode;

    public enum DisplayMode
    {
        NORMAL, //
        SHADOW, //
        BOTH;

        public boolean displayMeshes()
        {
            return (this == NORMAL) || (this == BOTH);
        }

        public boolean displayShadow()
        {
            return (this == SHADOW) || (this == BOTH);
        }
    }

    public GameObject(float x, float y, BodyType type, Model model, Physics physics, int category, int collisionMask, boolean isSensor)
    {
        this.body = model.structure().addTo(x, y, type, physics, category, collisionMask, isSensor);
        this.meshes = model.meshes();
        this.shadow = model.structure().shadows(Color.argb(255, 255, 100, 180)); // pink
        this.displayMode = DisplayMode.NORMAL;

        this.body.setUserData(this);
    }

    public Body body()
    {
        return body;
    }

    @Override
    public void update(float delta, Input input, Camera camera)
    {
    }

    @Override
    public void render(Renderer renderer)
    {
        if (displayMode.displayMeshes())
        {
            renderer.render(meshes, body.getPosition(), 1, 1, body.getAngle());
        }

        if (displayMode.displayShadow())
        {
            renderer.render(shadow, body.getPosition(), 1, 1, body.getAngle());
        }
    }

    public boolean is(Class<?> clazz)
    {
        return clazz.isInstance(this);
    }
}